package com.demonchou.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.demonchou.common.utils.ExcelUtil;
import com.demonchou.module.platformcert.domain.PlatformCompany;

/**
 *
 * @author hzzhouhongfei
 * @version $$ AssemblyBlackListAction, 2019-07-01 00:56 hzzhouhongfei $$
 */
public class AssemblyCompanyListAction
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
//			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/线下批量数据处理 (190531).xlsx"));
			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/ICP号.xlsx"));
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

		Map<String, PlatformCompany> nameAndCompanyMap = new HashMap<>(16);

		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				blackListDetail = excelUtil.getRowData(0, i);

				PlatformCompany platformCompany = new PlatformCompany();

				platformCompany.setCompanyName(StringUtils.trimToEmpty(blackListDetail[0]));
				platformCompany.setBusinessUrl(StringUtils.trimToEmpty(blackListDetail[1]));
				platformCompany.setIcpNo(StringUtils.trimToEmpty(blackListDetail[2]));
				nameAndCompanyMap.put(platformCompany.getCompanyName(), platformCompany);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);
		System.out.println("===>totalLogicRowNum:" + totalLogicRowNum);

		System.err.println("===> COMPANY_NAME_AND_COMPANY_INFO_MAP：" + JSONObject.toJSONString(nameAndCompanyMap));
	}
}
