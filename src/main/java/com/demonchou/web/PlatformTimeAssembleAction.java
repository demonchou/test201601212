package com.demonchou.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.demonchou.common.utils.ExcelUtil;

import utils.DateUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ PlatformTimeAssembleAction, 2020/11/4 13:51 hzzhouhongfei $$
 */
public class PlatformTimeAssembleAction
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
			excelUtil = new ExcelUtil(new File("/Users/sars/doc4project/web/合规需求/合规提供的资料/拟调整注销日期的商户清单.xlsx"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		int totalLogicRowNum = excelUtil.getRowNum(0);
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		String[] platformAndTimeDetail = null;

		Map<String, String > platformAndTimeMap = new HashMap<>(16);

		StringBuffer sb = new StringBuffer();
		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				platformAndTimeDetail = excelUtil.getRowData(0, i);

				String platformId = StringUtils.trimToEmpty(platformAndTimeDetail[0]);
				String time = StringUtils.trimToEmpty(platformAndTimeDetail[1]);
				platformAndTimeMap.put(platformId,
						DateUtil.dateFormat(DateUtil.formatToTimestamp(time, DateUtil.FMT_DATE_YYYYMMDD),
								DateUtil.FMT_DATE_SPECIAL));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.err.println("===>PLATFORM_AND_TIME_MAP：" + JSONObject.toJSONString(platformAndTimeMap));
	}
}
