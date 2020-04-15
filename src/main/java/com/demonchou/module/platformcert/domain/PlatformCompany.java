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
	/** 公司ID */
	private String companyId;
	/** 公司名称 */
	private String companyName;
	/** 公司地址 */
	private String businessAddress;
	/** 业务用途说明【营业范围】 */
	private String businessDescription;
	/** 公司网站 */
	private String businessUrl;
	/** 注册资本  */
	private String registeredCapital;
	/** 公司状态 */
	private String status;
	/** 公司归属 */
	private String companyAssign;
	/** 公司主体类型  */
	private String companyType;
	/** 公司属性 */
	private String companyAttribute;
	/**
	 * 网络商户 ICP备案/许可证号
	 */
	private String icpNo;
	private Timestamp createTime;
	private Timestamp updateTime;
}
