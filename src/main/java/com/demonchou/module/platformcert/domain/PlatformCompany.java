package com.demonchou.module.platformcert.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 *
 * @author hzzhouhongfei
 * @version $$ PlatformCompany, 2018/9/11 20:52 hzzhouhongfei $$
 */
@Data
public class PlatformCompany
{
	private String companyId;
	private String companyName;
	private String businessAddress;
	private String businessDescription;
	private String businessUrl;
	private String registeredCapital;
	private String status;
	private String companyAssign;
	private Timestamp createTime;
	private Timestamp updateTime;
}
