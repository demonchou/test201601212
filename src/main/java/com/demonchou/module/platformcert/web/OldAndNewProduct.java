package com.demonchou.module.platformcert.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.demonchou.common.utils.ExcelUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ OldAndNewProduct, 2019-05-31 07:14 hzzhouhongfei $$
 */
public class OldAndNewProduct
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/线下批量数据处理 (190531).xlsx"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		int totalLogicRowNum = excelUtil.getRowNum(0);
		String[] productPowerDetail = null;
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;

		Map<String, String> oldAndNewMap = new HashMap<>(16);

		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				productPowerDetail = excelUtil.getRowData(0, i);

				String oldId = StringUtils.trimToEmpty(productPowerDetail[0]);
				String oldName = StringUtils.trimToEmpty(productPowerDetail[1]);
				String newId = StringUtils.trimToEmpty(productPowerDetail[2]);
				String newName = StringUtils.trimToEmpty(productPowerDetail[3]);
				oldAndNewMap.put(oldId, newId);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);
		System.out.println("===>totalRowNumIncludeBlank:" + totalLogicRowNum);

		String oldAndNewMapStr = JSONObject.toJSONString(oldAndNewMap);
		System.err.println("===>oldAndNewMapStr：" + oldAndNewMapStr);

		Map oldAndNewMap2 = JSONObject.parseObject(oldAndNewMapStr, Map.class);

		System.err.println(JSONObject.toJSONString(oldAndNewMap2));
	}
}
