package com.demonchou.module.platformcert.web;

import java.io.File;
import java.util.ArrayList;
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
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		int totalLogicRowNum = excelUtil.getRowNum(0);
		int totalColNum = excelUtil.getColumnNum(0, 4);
		String[] certDetail = null;

		Multimap<String, PlatformCert> platformMsgMap = HashMultimap.create();
		List<PlatformCert> platformCerts = new ArrayList<>();

		Map<String, String> companyNameCertsMap = new HashMap<>(16);
		Map<String, String> newCompanyNamePlatformIdsMap = new HashMap<>(16);

		newCompanyNamePlatformIdsMap
				.put("渤海人寿保险股份有限公司", "2015092414PT68067908,2015092414PT68096962,2015092414PT68119146");
		newCompanyNamePlatformIdsMap
				.put("恒大人寿保险有限公司", "2016010416PT06616973,2016010416PT06641264,2016010416PT06661421");
		newCompanyNamePlatformIdsMap.put("江苏乐希科技有限公司", "2010041917PT81799091");
		newCompanyNamePlatformIdsMap.put("清控紫荆（北京）教育科技股份有限公司", "2015110414PT51495612");
		newCompanyNamePlatformIdsMap.put("网易博乐科技（舟山）有限公司", "2015091818PT33523535");
		newCompanyNamePlatformIdsMap.put("网易乐得科技有限公司北京分公司",
				"2010122411PT99922794,2011012011PT44375874,2011012117PT47052782,2013112720PT96388849,2014030514PT18590322,2014062711PT19366732,2014090520PT79653458,2015070316PT42257561,2015070615PT54816184,2015073119PT68254907,2015120516PT32274974,2015120516PT32312626");
		newCompanyNamePlatformIdsMap.put("优佳电子商务有限公司", "2014101711PT74048466,2014101715PT77692433");
		newCompanyNamePlatformIdsMap.put("友聚惠（北京）科技有限公司", "2013121615PT01742148");
		newCompanyNamePlatformIdsMap.put("珠江人寿保险股份有限公司", "2016022510PT80627400");

		try
		{
			for (int i = 0; i < totalRowNum; i++)
			{
				certDetail = excelUtil.getRowData(0, i + 1);

				String companyName = StringUtils.trimToEmpty(certDetail[0]);
				String certType = StringUtils.trimToEmpty(certDetail[1]);
				String certNo = StringUtils.trimToEmpty(certDetail[2]);
				String certName = StringUtils.trimToEmpty(certDetail[3]);
				String validDate = StringUtils.EMPTY;
				String certImgFrontUrl = StringUtils.EMPTY;
				String certImgBackUrl = StringUtils.EMPTY;
				String companyId = StringUtils.EMPTY;
				if (certDetail.length >= 4)
				{
					validDate = StringUtils.trimToEmpty(certDetail[4]);
					certImgFrontUrl = StringUtils.trimToEmpty(certDetail[5]);
					certImgBackUrl = StringUtils.trimToEmpty(certDetail[6]);
					companyId = StringUtils.trimToEmpty(certDetail[7]);
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
				platformCert.setCompanyId(companyId);
				platformCerts.add(platformCert);

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

		System.out.println("certListStr BATCH_UPDATE_CRTS_INFO : " + JSONObject.toJSONString(platformCerts));
		String companyNameCertsMapStr = JSONObject.toJSONString(companyNameCertsMap);
		System.err.println(
				"放在diamond上的数据 NEW_COMPANY_NAME_AND_CERTS_MAP_STR：companyNameCertsMapStr===>" + companyNameCertsMapStr);

		String newCompanyNamePlatformIdsMapStr = JSONObject.toJSONString(newCompanyNamePlatformIdsMap);
		System.err.println(
				"放在diamond上的数据，需要新增公司的商户 NEW_COMPANY_NAME_PLATFORM_IDS_MAP_STR：newCompanyNamePlatformIdsMapStr===>"
						+ newCompanyNamePlatformIdsMapStr);
	}
}
