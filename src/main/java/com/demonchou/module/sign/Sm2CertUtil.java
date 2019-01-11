package com.demonchou.module.sign;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cfca.sadk.util.Base64;
import cfca.sadk.x509.certificate.X509Cert;
import cfca.svs.api.ClientEnvironment;
import cfca.svs.api.SVBusiness;
import cfca.svs.api.util.IOUtil;
import cfca.svs.api.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author hzzhouhongfei
 * @version $$ Sm2CertUtil, 2019/1/8 21:53 hzzhouhongfei $$
 */
@Slf4j
public class Sm2CertUtil
{
	public static void main(String[] args) throws Exception
	{
		String filePath = "/Users/sars/Desktop/temp/CCMSZDT0414_20181225.XML";

		// 批量上传证书
		String uploadCerts = IOUtil.readFile2(filePath);

		if (StringUtils.isBlank(uploadCerts))
		{
			log.warn("【SM2加密机】初始化用户证书列表为空");
			return;
		}

		String[] certs = uploadCerts.split("</ROW>");

		if (certs.length == 0)
		{
			log.warn("【SM2加密机】解析证书内容失败");
			return;
		}

		List<SignCert> signCertList = new ArrayList<>();
		for (String cert : certs)
		{
			SignCert signCert = new SignCert();
			String bankCode = XmlUtil.getNodeText(cert,"BANKCODE");
			if (StringUtils.isBlank(bankCode))
			{
				continue;
			}
			String certBody = XmlUtil.getNodeText(cert, "CERTBODY");
			if (StringUtils.isBlank(certBody))
			{
				log.warn("bankCode :【{}】certBody is empty", bankCode);
				continue;
			}
			String certStr = new String(Base64.decode(certBody));
			String p7Sign = certStr.substring(certStr.indexOf("S:") + 2, certStr.lastIndexOf("}"));
			X509Cert x509Cert = SVBusiness
					.getCertFromP7SignData(p7Sign.getBytes(ClientEnvironment.DEFAULT_CHARSET));
			String certBase64 = new String(cfca.sadk.util.Base64.encode(x509Cert.getEncoded()), ClientEnvironment.DEFAULT_CHARSET);
			String certDN = x509Cert.getSubject();

			signCert.setCertDN(certDN);
			signCert.setBizKey(bankCode);
			signCert.setCert(certBase64);
			signCert.setBizType("NUCC");
			signCert.setAlgorithmType("SM3SM2");
			signCert.setEffectiveTime(new Timestamp(x509Cert.getNotBefore().getTime()));
			signCert.setExpireTime(new Timestamp(x509Cert.getNotAfter().getTime()));
			signCertList.add(signCert);
		}

		if (CollectionUtils.isEmpty(signCertList))
		{
			log.warn("【SM2加密机】需要入库的证书列表为空");
			return;
		}

		log.info("【SM2加密机】待初始化【{}】", signCertList.size());

		System.out.println(signCertList.size());
		System.out.println(JSONObject.toJSONString(signCertList));
	}
}
