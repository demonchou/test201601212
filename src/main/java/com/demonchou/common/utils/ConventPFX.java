package com.demonchou.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ConventPFX, 2018/11/29 10:17 hzzhouhongfei $$
 */
public class ConventPFX
{
	public static final String PKCS12 = "PKCS12";

	public static final String JKS = "JKS";

	/**
	 * //pfx�ļ�λ��
	 */
	private static final String PFX_KEYSTORE_FILE = "/Users/sars/doc4project/product-sign/����/wangyibao/RSAkey/991331001187.pfx";

	/**
	 * //����Ϊpfx�ļ����������
	 */
	private static final String KEYSTORE_PASSWORD = "1111";

	private static final String JKS_KEYSTORE_FILE = "/Users/sars/doc4project/product-sign/����/wangyibao/RSAkey/mycert2.jks";

	private static void coverTokeyStore()
	{
		try
		{
			KeyStore inputKeyStore = KeyStore.getInstance("PKCS12");

			FileInputStream fis = new FileInputStream(PFX_KEYSTORE_FILE);

			char[] nPassword = null;

			if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals(""))
			{
				nPassword = null;
			}
			else
			{

				nPassword = KEYSTORE_PASSWORD.toCharArray();

			}

			inputKeyStore.load(fis, nPassword);

			fis.close();

			KeyStore outputKeyStore = KeyStore.getInstance("JKS");

			outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());

			Enumeration enums = inputKeyStore.aliases();

			while (enums.hasMoreElements())
			{ // we are readin just one

				// certificate.

				String keyAlias = (String) enums.nextElement();

				System.out.println("alias=[" + keyAlias + "]");

				if (inputKeyStore.isKeyEntry(keyAlias))
				{

					Key key = inputKeyStore.getKey(keyAlias, nPassword);

					Certificate[] certChain = inputKeyStore

							.getCertificateChain(keyAlias);

					outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD

							.toCharArray(), certChain);

				}

			}

			FileOutputStream out = new FileOutputStream(JKS_KEYSTORE_FILE);

			outputKeyStore.store(out, nPassword);

			out.close();

		}
		catch (Exception e)
		{

			e.printStackTrace();

		}

	}

	public static void main(String[] args)
	{
		coverTokeyStore();
	}
}
