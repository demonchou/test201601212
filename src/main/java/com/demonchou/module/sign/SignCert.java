package com.demonchou.module.sign;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * ǩ��֤��ʵ����
 * @author hzzhouhongfei
 * @version $$ SignCert, 2018/12/27 20:06 hzzhouhongfei $$
 */
@Data
@Slf4j
public class SignCert implements Serializable, Comparable<SignCert>
{
	private static final long serialVersionUID = 4017343335355822366L;

	/**
	 * ֤��DN��֤�����⣩
	 */
	private String certDN;
	/**
	 * ����ҵ��ID���磺�����кţ�bankCode
	 */
	private String bizKey;
	/**
	 * ֤�����õ�ҵ������
	 */
	private String bizType;
	/**
	 * ֤������
	 */
	private String cert;
	/**
	 * ֤�����ݣ����ܺ��
	 */
	private String certEncrypted;
	/**
	 * ֤���õ��㷨����
	 */
	private String algorithmType;
	/**
	 * ״̬
	 */
	private String status;
	/**
	 * ��ע
	 */
	private String note;
	/**
	 * ֤����Чʱ��
	 */
	private Timestamp effectiveTime;
	/**
	 * ֤�����ʱ��
	 */
	private Timestamp expireTime;
	/**
	 * ��¼����ʱ��
	 */
	private Timestamp createTime;
	/**
	 * ֤���¼����ʱ��
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
