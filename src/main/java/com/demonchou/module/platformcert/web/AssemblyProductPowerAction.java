package com.demonchou.module.platformcert.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.demonchou.common.utils.ExcelUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ AssemblyProductPowerAction, 2019-05-27 10:50 hzzhouhongfei $$
 */
public class AssemblyProductPowerAction
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
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		String[] productPowerDetail = null;

		Map<String, List<String>> productPowerListMap = new HashMap<>(16);

		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				productPowerDetail = excelUtil.getRowData(0, i);

				String productId = StringUtils.trimToEmpty(productPowerDetail[0]);
				String powerName = StringUtils.trimToEmpty(productPowerDetail[1]);
				String powerType = StringUtils.trimToEmpty(productPowerDetail[2]);

				String powerNameStr = powerName + "|" + powerType;

				List<String> powerNameStrList = productPowerListMap.get(productId);

				if (CollectionUtils.isEmpty(powerNameStrList))
				{
					powerNameStrList = new ArrayList<>();
					powerNameStrList.add(powerNameStr);
					productPowerListMap.put(productId, powerNameStrList);
				}
				else if (!powerNameStrList.contains(powerNameStr))
				{
					powerNameStrList.add(powerNameStr);
					productPowerListMap.put(productId, powerNameStrList);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);
		System.out.println("===>totalRowNumIncludeBlank:" + totalLogicRowNum);

		String productPowerListMapStr = JSONObject.toJSONString(productPowerListMap);
		System.err.println("===>productPowerListMapStr：" + productPowerListMapStr);

		Map<String, List<String>> productPowerMap = JSONObject
				.parseObject(productPowerListMapStr, new TypeReference<Map<String, List<String>>>()
				{
				});

		List<String> powerNameStrList = productPowerMap.get("2016011110TM97488436");

		System.err.println(JSONObject.toJSONString(powerNameStrList));
	}
}
