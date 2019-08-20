package com.demonchou.module.platformcert.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 产品模板
 * @author hzyanglingyun
 * Date: 2015-01-07
 */
@Data
public class PlatformProductTemplate implements Serializable
{
	private static final long serialVersionUID = 7685181135586060292L;

	/** 模板ID */
	private String templateId;
	/** 模板名称 */
	private String templateName;
	/** 产品中文名 */
	private String templateNameCn;
	/** 产品接入文档  */
	private String templateDoc;
	/** 模板描述 */
	private String templateDesc;
	/** 创建时间 */
	private Timestamp createTime;
	/** 修改时间 */
	private Timestamp updateTime;
	/** 是否启用*/
	private Boolean isEnable;
	/**最后操作员*/
	private String lastOperator;
	/**产品所属类型,非空时,作为产品大类信息以供前端显示;NULL时,为“虚拟产品”类型*/
	private String templateType;
	/**
	 * 产品类别
	 */
	private String productType;

	private String viewFlag;
}
