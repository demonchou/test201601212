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
 * @version $$ AssemblyFeeProductMapAction, 2019-08-08 17:49 hzzhouhongfei $$
 */
public class AssemblyFeeProductMapAction
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

		Map<String, String> feeAndProductMap = new HashMap<>(16);

		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				productPowerDetail = excelUtil.getRowData(0, i);

				String productCode = StringUtils.trimToEmpty(productPowerDetail[0]);
				String productId = StringUtils.trimToEmpty(productPowerDetail[1]);
				feeAndProductMap.put(productCode, productId);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);
		System.out.println("===>totalRowNumIncludeBlank:" + totalLogicRowNum);

		String feeAndProductMapStr = JSONObject.toJSONString(feeAndProductMap);
		System.err.println("===>oldAndNewMapStr：" + feeAndProductMapStr);

		Map feeAndProductMap2 = JSONObject.parseObject(feeAndProductMapStr, Map.class);

		System.err.println(feeAndProductMap2.get("BANK_PRIVATE"));
	}
}
