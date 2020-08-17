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
			excelUtil = new ExcelUtil(new File("/Users/sars/tempFiles/全量商户.xlsx"));
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
				String seed = encryptDetail[14];
				if (StringUtils.isBlank(seed))
				{
					System.out.println("公司ID为空");
					continue;
				}
				String content = encryptDetail[23];
				if (StringUtils.isBlank(content))
				{
					System.out.println("密文信息为空");
					continue;
				}
				String result = Sm4Util.decryptEcb(seed, content);
				if (StringUtils.isBlank(result))
				{
					System.out.println("解密后明文为空");
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
