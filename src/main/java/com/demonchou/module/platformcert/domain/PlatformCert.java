package com.demonchou.module.platformcert.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 *
 * @author hzzhouhongfei
 * @version $$ PlatformCert, 2018/9/11 20:50 hzzhouhongfei $$
 */
@Data
public class PlatformCert
{
	private String certId;
	private String certType;
	private String certNo;
	private String certName;
	private Timestamp validDate;
	private Timestamp expireDate;
	private String status;
	private String longTerm;
	private String certImgFrontUrl;
	private String certImgBackUrl;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String platformId;
	private String companyId;
}
