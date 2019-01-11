package com.demonchou.module.sign;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 签名证书实体类
 * @author hzzhouhongfei
 * @version $$ SignCert, 2018/12/27 20:06 hzzhouhongfei $$
 */
@Data
@Slf4j
public class SignCert implements Serializable, Comparable<SignCert>
{
	private static final long serialVersionUID = 4017343335355822366L;

	/**
	 * 证书DN（证书主题）
	 */
	private String certDN;
	/**
	 * 关联业务ID，如：银行行号，bankCode
	 */
	private String bizKey;
	/**
	 * 证书适用的业务类型
	 */
	private String bizType;
	/**
	 * 证书内容
	 */
	private String cert;
	/**
	 * 证书内容，加密后的
	 */
	private String certEncrypted;
	/**
	 * 证书用的算法类型
	 */
	private String algorithmType;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 证书生效时间
	 */
	private Timestamp effectiveTime;
	/**
	 * 证书过期时间
	 */
	private Timestamp expireTime;
	/**
	 * 记录创建时间
	 */
	private Timestamp createTime;
	/**
	 * 证书记录更新时间
	 */
	private Timestamp updateTime;

	public String getCert()
	{
		if (StringUtils.isNotBlank(cert))
		{
			return cert;
		}
		return StringUtils.EMPTY;
	}

	@Override
	public int compareTo(SignCert signCert)
	{
		return this.getExpireTime().compareTo(signCert.getExpireTime());
	}

}
