package com.demonchou.module.platformcert.web;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.demonchou.common.utils.ExcelUtil;
import com.demonchou.module.platformcert.domain.PlatformCert;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import utils.DateUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ AssambleCertsAction, 2018/9/11 20:55 hzzhouhongfei $$
 */
public class AssambleCertsAction
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/platformCerts.xlsx"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		int totalLogicRowNum = excelUtil.getRowNum(0);
		String[] certDetail = null;

		Multimap<String, PlatformCert> platformMsgMap = HashMultimap.create();

		Map<String, String> companyNameCertsMap = new HashMap<>(16);
		Map<String, String> newCompanyNamePlatformIdsMap = new HashMap<>(16);

		newCompanyNamePlatformIdsMap.put("测试新增公司","2016061616PT00444056,2016061319PT00443176");


		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				certDetail = excelUtil.getRowData(0, i);

				String companyName = StringUtils.trimToEmpty(certDetail[0]);
				String certType = StringUtils.trimToEmpty(certDetail[1]);
				String certNo = StringUtils.trimToEmpty(certDetail[2]);
				String certName = StringUtils.trimToEmpty(certDetail[3]);
				String validDate = StringUtils.trimToEmpty(certDetail[4]);
				String certImgFrontUrl = StringUtils.trimToEmpty(certDetail[5]);
				String certImgBackUrl = StringUtils.trimToEmpty(certDetail[6]);

				PlatformCert platformCert = new PlatformCert();
				platformCert.setCertType(certType);
				platformCert.setCertNo(certNo);
				platformCert.setCertName(certName);
				platformCert.setExpireDate(DateUtil.formatToTimestamp(validDate, DateUtil.FMT_DATE_YYYYMMDD));
				platformCert.setLongTerm("N");
				if (StringUtils.equals("长期", validDate))
				{
					platformCert.setLongTerm("Y");
				}
				platformCert.setCertImgFrontUrl(certImgFrontUrl);
				platformCert.setCertImgBackUrl(certImgBackUrl);

				platformMsgMap.put(companyName, platformCert);
			}

			for (String companyName : platformMsgMap.keySet())
			{
				Collection<PlatformCert> platformCertList = platformMsgMap.get(companyName);
				companyNameCertsMap.put(companyName, JSONObject.toJSONString(platformCertList));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);
		System.out.println("===>totalRowNumIncludeBlank:" + totalLogicRowNum);


		System.out.println("platformMsgMap.entries()===>" + JSONObject.toJSONString(platformMsgMap.entries()));

		System.out.println("========");

		String companyNameCertsMapStr = JSONObject.toJSONString(companyNameCertsMap);
		System.err.println("放在diamond上的数据：companyNameCertsMapStr===>" + companyNameCertsMapStr);

		System.out.println("========");

		String newCompanyNamePlatformIdsMapStr = JSONObject.toJSONString(newCompanyNamePlatformIdsMap);
		System.err.println("放在diamond上的数据，需要新增公司的商户：newCompanyNamePlatformIdsMapStr===>" + newCompanyNamePlatformIdsMapStr);

		System.out.println("========");

		Map companyNameCertsMapDb = JSONObject.parseObject(companyNameCertsMapStr, Map.class);

		List<PlatformCert> platformCerts = JSONObject
				.parseArray((String) companyNameCertsMapDb.get("广州博冠信息科技有限公司"), PlatformCert.class);

		System.out.println("========");

		System.out.println("广州博冠信息科技有限公司===>" + JSONObject.toJSONString(platformCerts));
	}
}
