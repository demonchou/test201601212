package com.demonchou.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Enumeration;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import common.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author hzzhouhongfei
 * @version $$ FileUtil, 2018/11/14 16:51 hzzhouhongfei $$
 */
@Data
@Slf4j
public class FileUtils
{
	/**
	 * 压缩文件或目录
	 * @param srcDirName 压缩的根目录
	 * @param fileName 根目录下的待压缩的文件名或文件夹名，其中*或""表示跟目录下的全部文件
	 * @param descFileName 目标zip文件
	 */
	public static void zipFiles(String srcDirName, String fileName,
			String descFileName)
	{
		// 判断目录是否存在
		if (srcDirName == null)
		{
			log.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
			return;
		}
		File fileDir = new File(srcDirName);
		if (!fileDir.exists() || !fileDir.isDirectory())
		{
			log.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
			return;
		}
		String dirPath = fileDir.getAbsolutePath();
		File descFile = new File(descFileName);
		try
		{
			ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(
					descFile));
			if ("*".equals(fileName) || "".equals(fileName))
			{
				FileUtils.zipDirectoryToZipFile(dirPath, fileDir, zouts);
			}
			else
			{
				File file = new File(fileDir, fileName);
				if (file.isFile())
				{
					FileUtils.zipFilesToZipFile(dirPath, file, zouts);
				}
				else
				{
					FileUtils.zipDirectoryToZipFile(dirPath, file, zouts);
				}
			}
			zouts.close();
			log.debug(descFileName + " 文件压缩成功!");
		}
		catch (Exception e)
		{
			log.debug("文件压缩失败：" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 解压缩ZIP文件，将ZIP文件里的内容解压到descFileName目录下
	 * @param zipFileName 需要解压的ZIP文件
	 * @param descFileName 目标文件
	 */
	public static boolean unZipFiles(String zipFileName, String descFileName)
	{
		String descFileNames = descFileName;
		if (!descFileNames.endsWith(File.separator))
		{
			descFileNames = descFileNames + File.separator;
		}
		try
		{
			// 根据ZIP文件创建ZipFile对象
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry entry = null;
			String entryName = null;
			String descFileDir = null;
			byte[] buf = new byte[4096];
			int readByte = 0;
			// 获取ZIP文件里所有的entry
			@SuppressWarnings("rawtypes")
			Enumeration enums = zipFile.getEntries();
			// 遍历所有entry
			while (enums.hasMoreElements())
			{
				entry = (ZipEntry) enums.nextElement();
				// 获得entry的名字
				entryName = entry.getName();
				descFileDir = descFileNames + entryName;
				if (entry.isDirectory())
				{
					// 如果entry是一个目录，则创建目录
					new File(descFileDir).mkdirs();
					continue;
				}
				else
				{
					// 如果entry是一个文件，则创建父目录
					new File(descFileDir).getParentFile().mkdirs();
				}
				File file = new File(descFileDir);
				// 打开文件输出流
				OutputStream os = new FileOutputStream(file);
				// 从ZipFile对象中打开entry的输入流
				InputStream is = zipFile.getInputStream(entry);
				while ((readByte = is.read(buf)) != -1)
				{
					os.write(buf, 0, readByte);
				}
				os.close();
				is.close();
			}
			zipFile.close();
			log.debug("文件解压成功!");
			return true;
		}
		catch (Exception e)
		{
			log.debug("文件解压失败：" + e.getMessage());
			return false;
		}
	}

	/**
	 * 将目录压缩到ZIP输出流
	 * @param dirPath 目录路径
	 * @param fileDir 文件信息
	 * @param zouts 输出流
	 */
	public static void zipDirectoryToZipFile(String dirPath, File fileDir, ZipOutputStream zouts)
	{
		if (fileDir.isDirectory())
		{
			File[] files = fileDir.listFiles();
			// 空的文件夹
			if (files.length == 0)
			{
				// 目录信息
				ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
				try
				{
					zouts.putNextEntry(entry);
					zouts.closeEntry();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return;
			}

			for (File file : files)
			{
				if (file.isFile())
				{
					// 如果是文件，则调用文件压缩方法
					FileUtils
							.zipFilesToZipFile(dirPath, file, zouts);
				}
				else
				{
					// 如果是目录，则递归调用
					FileUtils.zipDirectoryToZipFile(dirPath, file,
							zouts);
				}
			}
		}
	}

	/**
	 * 将文件压缩到ZIP输出流
	 * @param dirPath 目录路径
	 * @param file 文件
	 * @param zouts 输出流
	 */
	public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts)
	{
		FileInputStream fin = null;
		ZipEntry entry = null;
		// 创建复制缓冲区
		byte[] buf = new byte[4096];
		int readByte = 0;
		if (file.isFile())
		{
			try
			{
				// 创建一个文件输入流
				fin = new FileInputStream(file);
				// 创建一个ZipEntry
				entry = new ZipEntry(getEntryName(dirPath, file));
				// 存储信息到压缩文件
				zouts.putNextEntry(entry);
				// 复制字节到压缩文件
				while ((readByte = fin.read(buf)) != -1)
				{
					zouts.write(buf, 0, readByte);
				}
				zouts.closeEntry();
				fin.close();
				System.out
						.println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * 将Zip文件中符合指定命名格式的文件解压到目标文件夹内
	 *
	 * @param zipFile 待解压Zip文件路径
	 * @param destDir 目标文件夹
	 */
	public static void unzip(File zipFile, File destDir)
	{
		unzip(zipFile, destDir, null);
	}

	/**
	 * 将Zip文件中符合指定命名格式的文件解压到目标文件夹内
	 *
	 * @param zipFile    待解压Zip文件路径
	 * @param destDir    目标文件夹
	 * @param charset    解压charset
	 */
	public static void unzip(File zipFile, File destDir, Charset charset)
	{
		java.util.zip.ZipFile zip = null;
		try
		{
			if (null == zipFile)
			{
				throw new BusinessException("Zip解压指定文件对象引用不允许为空!");
			}

			if (null == destDir)
			{
				throw new BusinessException("Zip解压指定文件对象引用不允许为空!");
			}

			try
			{
				if (charset == null)
				{
					zip = new java.util.zip.ZipFile(zipFile);
				}
				else
				{
					zip = new java.util.zip.ZipFile(zipFile, charset);
				}
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}

			Enumeration<? extends java.util.zip.ZipEntry> en = zip.entries();

			while (en.hasMoreElements())
			{
				java.util.zip.ZipEntry entry = (java.util.zip.ZipEntry) en.nextElement();

				File file = (destDir != null) ? new File(destDir, entry.getName()) : new File(entry.getName());
				if (entry.isDirectory())
				{
					if (!file.mkdirs())
					{
						if (!file.isDirectory())
						{
							throw new RuntimeException("创建目录失败: " + file);
						}
					}
				}
				else
				{
					File parent = file.getParentFile();
					if (parent != null && !parent.exists())
					{
						if (!parent.mkdirs())
						{
							if (!file.isDirectory())
							{
								throw new RuntimeException("创建目录失败: " + parent);
							}
						}
					}

					InputStream in = null;
					OutputStream out = null;
					try
					{
						in = zip.getInputStream(entry);
						out = new FileOutputStream(file);
						IOUtils.copy(in, out);
					}
					catch (IOException e)
					{
						throw new RuntimeException(e);
					}
					finally
					{
						IOUtils.closeQuietly(out);
						IOUtils.closeQuietly(in);
					}
				}
			}
		}
		finally
		{
			if (null != zip)
			{
				try
				{
					zip.close();
				}
				catch (IOException e)
				{
					log.warn("关闭文件流失败", e);
				}
			}
		}

	}

	/**
	 * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
	 * @param dirPath 目录名
	 * @param file entry文件名
	 * @return
	 */
	private static String getEntryName(String dirPath, File file)
	{
		String dirPaths = dirPath;
		if (!dirPaths.endsWith(File.separator))
		{
			dirPaths = dirPaths + File.separator;
		}
		String filePath = file.getAbsolutePath();
		// 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
		if (file.isDirectory())
		{
			filePath += "/";
		}
		int index = filePath.indexOf(dirPaths);

		return filePath.substring(index + dirPaths.length());
	}

	public static void main(String[] args)
	{
		String zipFileName = "/Users/sars/Desktop/temp/归档.zip";
		String descFileName = "/Users/sars/Desktop/temp/desc";
		unZipFiles(zipFileName, descFileName);
		Collection<File> destFileColl = org.apache.commons.io.FileUtils
				.listFiles(new File(descFileName), null, false);
		if (CollectionUtils.isNotEmpty(destFileColl))
		{
			for (File file : destFileColl)
			{
				System.out.println(file.getName());
				System.out.println(getFileName(file.getName()));
				System.out.println(StringUtils.substringBeforeLast(file.getName(), "."));
			}
		}
	}

	private static String getFileName(String fileName)
	{
		return StringUtils.isBlank(fileName) ? "" : (fileName.substring(0, fileName.lastIndexOf(".")));
	}
}
