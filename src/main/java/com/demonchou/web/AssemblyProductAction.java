package com.demonchou.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.demonchou.common.utils.ExcelUtil;
import com.demonchou.module.platformcert.domain.PlatformProductTemplate;

/**
 * CLOSE_TRADE_POWER
 * @author hzzhouhongfei
 * @version $$ AssemblyProductAction, 2019-06-12 17:33 hzzhouhongfei $$
 */
public class AssemblyProductAction
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
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		int totalLogicRowNum = excelUtil.getRowNum(0);
		String[] productDetail = null;

		List<PlatformProductTemplate> productTemplateList = new ArrayList<>();

		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				productDetail = excelUtil.getRowData(0, i);

				String templateName = StringUtils.trimToNull(productDetail[0]);
				String productId = StringUtils.trimToNull(productDetail[1]);
				String templateNameCn = StringUtils.trimToNull(productDetail[2]);
				String templateDoc = StringUtils.trimToNull(productDetail[3]);
				String templateDesc = StringUtils.trimToNull(productDetail[4]);
				String templateType = StringUtils.trimToNull(productDetail[5]);
				String productType = StringUtils.trimToNull(productDetail[6]);
				String isAble = StringUtils.trimToNull(productDetail[7]);
				String viewFlag = StringUtils.trimToNull(productDetail[8]);

				PlatformProductTemplate productTemplate = new PlatformProductTemplate();

				if (StringUtils.isNotBlank(productId))
				{
					productTemplate.setTemplateId(productId);
				}

				productTemplate.setTemplateName(templateName);
				productTemplate.setTemplateNameCn(templateNameCn);
				productTemplate.setTemplateDoc(templateDoc);
				productTemplate.setTemplateDesc(templateDesc);
				productTemplate.setTemplateType(templateType);
				productTemplate.setProductType(productType);
				productTemplate.setLastOperator("hzzhouhongfei");
				productTemplate.setViewFlag(viewFlag);
				if (StringUtils.equalsIgnoreCase(isAble, "1"))
				{
					productTemplate.setIsEnable(true);
				}
				else
				{
					productTemplate.setIsEnable(false);
				}

				productTemplateList.add(productTemplate);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);

		System.out.println("===>totalRowNumIncludeBlank:" + totalLogicRowNum);

		String productTemplateListStr = JSONObject.toJSONString(productTemplateList);

		System.err.println("===>productTemplateListStr：" + productTemplateListStr);

		List<PlatformProductTemplate> productTemplateList1 = JSONObject
				.parseArray(productTemplateListStr, PlatformProductTemplate.class);

		System.err.println(JSONObject.toJSONString(productTemplateList1));
	}

}
