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
 * @version $$ AssemblyCertsAction, 2018/9/11 20:55 hzzhouhongfei $$
 */
public class AssemblyCertsAction
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/线下批量数据处理.xlsx"));
//			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/一键开户模板（new）.xlsx"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 2;
		int totalLogicRowNum = excelUtil.getRowNum(0);
		int totalColNum = excelUtil.getColumnNum(0, 4);
		String[] certDetail = null;

		Multimap<String, PlatformCert> platformMsgMap = HashMultimap.create();

		Map<String, String> companyNameCertsMap = new HashMap<>(16);
		Map<String, String> newCompanyNamePlatformIdsMap = new HashMap<>(16);

		newCompanyNamePlatformIdsMap.put("杭州悦动信息科技有限公司","2014021118PT40432714");
		newCompanyNamePlatformIdsMap.put("华安财产保险股份有限公司","2015122817PT60791069");
		newCompanyNamePlatformIdsMap.put("前海人寿保险股份有限公司","2015112311PT64229280");
		newCompanyNamePlatformIdsMap.put("阳光人寿保险股份有限公司北京分公司", "2012081317PT83847987,2012081317PT83866399,2014103113PT59648372");
		newCompanyNamePlatformIdsMap.put("中国平安财产保险股份有限公司","2014041015PT53986694,2014072311PT01146728");


		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				certDetail = excelUtil.getRowData(0, i + 1);

				String companyName = StringUtils.trimToEmpty(certDetail[0]);
				String certType = StringUtils.trimToEmpty(certDetail[1]);
				String certNo = StringUtils.trimToEmpty(certDetail[2]);
				String certName = StringUtils.trimToEmpty(certDetail[3]);
				String validDate = StringUtils.EMPTY;
				String certImgFrontUrl = StringUtils.EMPTY;
				String certImgBackUrl = StringUtils.EMPTY;
				if (certDetail.length >= 4)
				{
					validDate = StringUtils.trimToEmpty(certDetail[4]);
					certImgFrontUrl = StringUtils.trimToEmpty(certDetail[5]);
					certImgBackUrl = StringUtils.trimToEmpty(certDetail[6]);
				}

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

		String companyNameCertsMapStr = JSONObject.toJSONString(companyNameCertsMap);
		System.err.println("放在diamond上的数据：companyNameCertsMapStr===>" + companyNameCertsMapStr);

		String newCompanyNamePlatformIdsMapStr = JSONObject.toJSONString(newCompanyNamePlatformIdsMap);
		System.err.println("放在diamond上的数据，需要新增公司的商户：newCompanyNamePlatformIdsMapStr===>" + newCompanyNamePlatformIdsMapStr);

		Map companyNameCertsMapDb = JSONObject.parseObject(companyNameCertsMapStr, Map.class);

		List<PlatformCert> platformCerts = JSONObject
				.parseArray((String) companyNameCertsMapDb.get("广州博冠信息科技有限公司"), PlatformCert.class);

		System.out.println("广州博冠信息科技有限公司===>" + JSONObject.toJSONString(platformCerts));
	}
}
