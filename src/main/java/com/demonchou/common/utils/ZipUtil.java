package com.demonchou.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.BusinessException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * zip文件的压缩生成
 *
 * @author hzzhongtingjie
 * @version $ ZipUtil, 2018/9/27 hzzhongtingjie $
 */
public final class ZipUtil
{
	private static Logger log = LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * 不允许实例化
	 */
	private ZipUtil()
	{
	}

	/**
	 * 生成（加密的）指定的压缩文件在指定目录
	 *
	 * @param originalFiles
	 * @param password
	 * @param path
	 * @param charset       编码格式
	 * @return
	 */
	public static File compressFilesWithEncryption(List<File> originalFiles, String password, String path,
			String charset)
	{
		try
		{
			ZipFile zipFile = new ZipFile(path);
			zipFile.setFileNameCharset(charset);
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			if (password != null)
			{
				hasPassword(parameters, password);
			}
			zipFile.addFiles((ArrayList<?>) originalFiles, parameters);
		}
		catch (ZipException e)
		{
			log.error("文件压缩失败", e);
		}
		return new File(path);
	}

	/**
	 * 生成（加密的）指定的压缩文件在指定目录
	 *
	 * @param originalFile
	 * @param password
	 * @param path
	 * @param charset      编码格式
	 * @return
	 */
	public static File compressFileWithEncryption(File originalFile, String password, String path, String charset)
	{
		try
		{
			ZipFile zipFile = new ZipFile(path);
			zipFile.setFileNameCharset(charset);
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			if (password != null)
			{
				hasPassword(parameters, password);
			}
			zipFile.addFile(originalFile, parameters);
		}
		catch (ZipException e)
		{
			log.error("文件压缩失败", e);
		}
		return new File(path);
	}

	/**
	 * 不需要加密的压缩生成
	 *
	 * @param originalFile
	 * @param path
	 * @param charset      编码格式
	 * @return
	 */
	public static File compressFileWithNoEncryption(File originalFile, String path, String charset)
	{
		return compressFileWithEncryption(originalFile, null, path, charset);
	}

	/**
	 * 不需要加密的压缩生成
	 *
	 * @param originalFileList
	 * @param path
	 * @param charset          编码格式
	 * @return
	 */
	public static File compressFileWithNoEncryption(List<File> originalFileList, String path, String charset)
	{
		return compressFilesWithEncryption(originalFileList, null, path, charset);
	}

	/**
	 * 删除相应目录的zip文件
	 *
	 * @param path
	 * @return
	 */
	public static boolean deleteZip(String path)
	{
		boolean isDelete = true;
		try
		{
			// 根据路径取得需要解压的Zip文件
			ZipFile zipFile = new ZipFile(path);
			// 删除压缩文件
			final File file = zipFile.getFile();
			if (file.exists())
			{ // 判断是否存在
				file.delete();
			}
		}
		catch (ZipException e)
		{
			log.error(path + "zip删除失败", e);
			isDelete = false;
		}
		return isDelete;
	}

	private static void hasPassword(ZipParameters parameters, String password)
	{
		parameters.setEncryptFiles(true);
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		parameters.setPassword(password);
	}

	/**
	 * 创建ZIP文件
	 *
	 * @param sourcePath 文件或文件夹路径
	 * @param zipPath    生成的zip文件存在路径（包括文件名）
	 * @param isDrop     是否删除原文件:true删除、false不删除
	 */
	public static void createZip(String sourcePath, String zipPath, Boolean isDrop)
	{
		ZipOutputStream zos = null;
		try
		{
			FileOutputStream fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			File sourceFile = new File(sourcePath);
			if (sourceFile.exists() && sourceFile.isDirectory())
			{
				File[] files = sourceFile.listFiles();
				if (files != null && files.length != 0)
				{
					for (File f : files)
					{
						writeZip(f, "", zos, isDrop);
					}
				}
			}
			else
			{
				writeZip(new File(sourcePath), "", zos, isDrop);

			}
		}
		catch (FileNotFoundException e)
		{
			log.error("创建ZIP文件失败", e);
		}
		finally
		{
			try
			{
				if (zos != null)
				{
					zos.close();
				}
			}
			catch (IOException e)
			{
				log.error("创建ZIP文件失败", e);
			}
		}
	}

	/**
	 * 清空文件和文件目录
	 *
	 * @param f
	 */
	public static void clean(File f) throws Exception
	{
		String cs[] = f.list();
		if (cs == null || cs.length <= 0)
		{
			boolean isDelete = f.delete();
			if (!isDelete)
			{
				throw new Exception(f.getName() + "文件删除失败！");
			}
		}
		else
		{
			for (int i = 0; i < cs.length; i++)
			{
				String cn = cs[i];
				String cp = f.getPath() + File.separator + cn;
				File f2 = new File(cp);
				if (f2.exists() && f2.isFile())
				{
					boolean isDelete = f2.delete();
					if (!isDelete)
					{
						throw new Exception(f2.getName() + "文件删除失败！");
					}
				}
				else if (f2.exists() && f2.isDirectory())
				{
					clean(f2);
				}
			}
			boolean isDelete = f.delete();
			if (!isDelete)
			{
				throw new Exception(f.getName() + "文件删除失败！");
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

			Enumeration<? extends ZipEntry> en = zip.entries();

			while (en.hasMoreElements())
			{
				ZipEntry entry = (ZipEntry) en.nextElement();

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

	private static void writeZip(File file, String parentPath, ZipOutputStream zos, Boolean isDrop)
	{
		if (file.exists())
		{
			if (file.isDirectory())
			{
				//处理文件夹
				parentPath += file.getName() + File.separator;
				File[] files = file.listFiles();
				if (files != null && files.length != 0)
				{
					for (File f : files)
					{
						writeZip(f, parentPath, zos, isDrop);
					}
				}
				else
				{
					//空目录则创建当前目录
					try
					{
						zos.putNextEntry(new ZipEntry(parentPath));
					}
					catch (IOException e)
					{
						log.error("创建ZIP文件失败", e);
					}
				}
			}
			else
			{
				FileInputStream fis = null;
				try
				{
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1)
					{
						zos.write(content, 0, len);
						zos.flush();
					}

				}
				catch (FileNotFoundException e)
				{
					log.error("创建ZIP文件失败", e);
				}
				catch (IOException e)
				{
					log.error("创建ZIP文件失败", e);
				}
				finally
				{
					try
					{
						if (fis != null)
						{
							fis.close();
						}
						if (isDrop)
						{
							clean(file);
						}
					}
					catch (IOException e)
					{
						log.error("创建ZIP文件失败", e);
					}
					catch (Exception e)
					{
						log.error("创建ZIP文件失败", e);
					}
				}
			}
		}
	}

	public static void main(String[] args)
	{
		File zipFile = new File("/Users/sars/Desktop/temp/kaola-20181221-001.zip");
		File destDir = new File("/Users/sars/Desktop/Platformcerts");
		ZipUtil.unzip(zipFile, destDir);
		Collection<File> destFileColl = FileUtils.listFiles(destDir, null, true);

		if (CollectionUtils.isEmpty(destFileColl))
		{
			System.out.println("临时目录无解压文件");
		}

		for (File destFile : destFileColl)
		{
			System.out.println(destFile.getName());
		}
	}
}
