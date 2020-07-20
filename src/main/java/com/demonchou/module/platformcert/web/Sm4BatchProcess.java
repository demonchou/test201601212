package com.demonchou.module.platformcert.web;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.demonchou.common.utils.ExcelUtil;

import sm4.Sm4Util;

/**
 *
 * @author hzzhouhongfei
 * @version $$ Sm4BatchProcess, 2020/7/19 21:07 hzzhouhongfei $$
 */
public class Sm4BatchProcess
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
			excelUtil = new ExcelUtil(new File("/Users/sars/doc4project/web/合规需求/特约商户.xlsx"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		String[] encryptDetail = null;

		for (int i = 0; i < totalRowNum; i++)
		{
			try
			{
				encryptDetail = excelUtil.getRowData(0, i + 1);
				String seed = encryptDetail[0];
				String content = encryptDetail[1];
				if (StringUtils.isBlank(content))
				{
					System.out.println("解密失败");
					continue;
				}
				String result = Sm4Util.decryptEcb(seed, content);
				if (StringUtils.isBlank(result))
				{
					System.out.println("解密失败");
					continue;
				}
				System.out.println(result);
			}
			catch (Exception e)
			{
				System.out.println("解密失败");

			}
		}

	}
}
