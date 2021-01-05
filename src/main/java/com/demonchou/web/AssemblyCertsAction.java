package com.demonchou.web;

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
				.put("中国人民人寿保险股份有限公司北京市分公司", "2013092320PT58079264,2013102910PT04223297,2014091120PT83927829");
		newCompanyNamePlatformIdsMap
				.put("光大永明人寿保险有限公司", "2013030517PT85033886");

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
				String expireDate = StringUtils.EMPTY;
				String certImgFrontUrl = null;
				String certImgBackUrl = null;
				String companyId = null;
				String certId = null;
				String certCategory = null;
				String status = null;

				if (certDetail.length >= 4)
				{
					validDate = StringUtils.trimToEmpty(certDetail[4]);
					expireDate = StringUtils.trimToEmpty(certDetail[5]);
					certImgFrontUrl = StringUtils.trimToEmpty(certDetail[6]);
					certImgBackUrl = StringUtils.trimToEmpty(certDetail[7]);
					companyId = StringUtils.trimToEmpty(certDetail[8]);
					certId = StringUtils.trimToEmpty(certDetail[9]);
					certCategory = StringUtils.trimToEmpty(certDetail[10]);
					status = StringUtils.trimToEmpty(certDetail[11]);
				}

				PlatformCert platformCert = new PlatformCert();
				platformCert.setCertType(certType);
				platformCert.setCertNo(certNo);
				platformCert.setCertName(certName);
				platformCert.setValidDate(DateUtil.formatToTimestamp(validDate, DateUtil.FMT_DATE_YYYYMMDD));
				platformCert.setLongTerm("N");
				if (StringUtils.equals("长期", expireDate))
				{
					platformCert.setLongTerm("Y");
				}
				else
				{
					platformCert.setExpireDate(DateUtil.formatToTimestamp(expireDate, DateUtil.FMT_DATE_YYYYMMDD));

				}
				platformCert.setCertImgFrontUrl(certImgFrontUrl);
				platformCert.setCertImgBackUrl(certImgBackUrl);
				platformCert.setCompanyId(companyId);
				platformCert.setCertId(certId);
				platformCert.setCertCategory(certCategory);
				platformCert.setStatus(status);
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
