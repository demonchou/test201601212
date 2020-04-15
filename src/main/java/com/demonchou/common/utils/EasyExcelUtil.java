package com.demonchou.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于easyexcel实现的EXCEL工具类
 * 详见https://github.com/alibaba/easyexcel
 * @author hzzhouhongfei
 * @version $$ ExcelUtil, 2019/12/2 16:32 hzzhouhongfei $$
 */
@Slf4j
public class EasyExcelUtil
{
	/**
	 * 读取2007版本EXCEL文件
	 * @param filePath
	 * @return 返回的Objec对象本质是List<String>类型对象，可通过强转以方便处理返回值
	 */
	public static List<Object> read2List(String filePath)
	{
		return read2List(filePath, 1, 0);
	}

	public static List<Object> read2List(String filePath, int sheetNo, int headLineMun)
	{
		List<Object> parsedExcelRowObjList = Lists.newArrayList();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try
		{
			fis = FileUtils.openInputStream(new File(filePath));
			bis = new BufferedInputStream(fis);
			parsedExcelRowObjList = EasyExcelFactory.read(bis, new Sheet(sheetNo, headLineMun));
		}
		catch (IOException e)
		{
			throw new RuntimeException(String.format("EXCEL文件解析异常，异常原因：%s", e.getMessage()), e);
		}
		finally
		{
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(bis);
		}

		return parsedExcelRowObjList;
	}

	public static List<Object> read2List(File file)
	{
		return read2List(file, 1, 0);
	}

	public static List<Object> read2List(File file, int sheetNo, int headLineMun)
	{
		List<Object> parsedExcelRowObjList = Lists.newArrayList();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try
		{
			fis = FileUtils.openInputStream(file);
			bis = new BufferedInputStream(fis);
			parsedExcelRowObjList = EasyExcelFactory.read(bis, new Sheet(sheetNo, headLineMun));
		}
		catch (IOException e)
		{
			log.error("EXCEL文件解析异常，异常原因：{}", e.getMessage(), e);
		}
		finally
		{
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(bis);
		}
		return parsedExcelRowObjList;
	}

	public static List<Object> read2List(InputStream inputStream)
	{
		return EasyExcelFactory.read(inputStream, new Sheet(1, 0));
	}

	public static List<Object> read2List(InputStream inputStream, int sheetNo, int headLineMun)
	{
		return EasyExcelFactory.read(inputStream, new Sheet(sheetNo, headLineMun));
	}

	/**
	 * 生成简单的excel文件，sheet数为1, 单行标题
	 *
	 * @param singleHead 每列的标题
	 * @param data 数据，一行一行写
	 * @return byteArrayOutputStream
	 */
	public static OutputStream writeExcel(List<String> singleHead, List<List<Object>> data)
	{
		List<List<String>> multiHead = new ArrayList<>();
		for (String head : singleHead)
		{
			multiHead.add(Collections.singletonList(head));
		}
		return writeExcelWithMultiHead(multiHead, data);
	}

	/**
	 * 用于生成简单的excel文件，sheet数为1
	 *
	 * @param headColumn 表头,一行一行写，每列相邻表头重复会合并
	 * @param data 数据，一行一行写
	 * @return byteArrayOutputStream
	 */
	public static OutputStream writeExcelWithMultiHead(List<List<String>> headColumn, List<List<Object>> data)
	{
		OutputStream outputStream = new ByteArrayOutputStream();

		ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);

		Sheet sheet = new Sheet(1, 0);

		Table table = new Table(1);
		//添加表头
		table.setHead(headColumn);
		//写数据
		writer.write1(data, sheet, table);
		writer.finish();
		return outputStream;
	}

	/**
	 * 过滤EXCEL空行
	 * @param rowList
	 * @return
	 */
	public static List<Object> filterEmptyRows(List<Object> rowList)
	{
		Iterable<Object> notEmptyRowIter = Iterables.filter(MoreObjects.firstNonNull(rowList, Lists.newArrayList()),
				new Predicate<Object>()
				{
					@Override
					public boolean apply(@Nullable Object o)
					{
						if (o == null)
						{
							return false;
						}

						for (Object rowElem : (List) o)
						{
							if (StringUtils.isNotBlank((String) rowElem))
							{
								return true;
							}
						}

						return false;
					}
				});

		return Lists.newArrayList(notEmptyRowIter);
	}
}
