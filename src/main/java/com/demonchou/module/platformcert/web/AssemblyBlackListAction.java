package com.demonchou.module.platformcert.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.demonchou.common.utils.ExcelUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ AssemblyBlackListAction, 2019-07-01 00:56 hzzhouhongfei $$
 */
public class AssemblyBlackListAction
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/线下批量数据处理 (190531).xlsx"));
//			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/一键开户模板（new）.xlsx"));
//			excelUtil = new ExcelUtil(new File("/Users/sars/tempFiles/考拉种草商家开户0628.xlsx"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		int totalLogicRowNum = excelUtil.getRowNum(0);
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		String[] blackListDetail = null;

		Map<String, List<String>> productPowerListMap = new HashMap<>(16);

		StringBuffer sb = new StringBuffer();
		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				blackListDetail = excelUtil.getRowData(0, i);

				String functionName = StringUtils.trimToEmpty(blackListDetail[0]);
				String version = StringUtils.trimToEmpty(blackListDetail[1]);
				String platformIds = StringUtils.trimToEmpty(blackListDetail[2]);

				sb.append(functionName).append(version).append(";").append(platformIds).append("|");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);
		System.out.println("===>totalRowNumIncludeBlank:" + totalLogicRowNum);

		System.err.println("===>blackListStr：" + sb.toString());
	}
}
