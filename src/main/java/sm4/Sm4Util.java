package sm4;

import static com.demonchou.common.utils.commonutils.convertToMd5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * sm4加密算法工具类
 * @explain sm4加密、解密与加密结果验证
 *          可逆算法
 * @author Marydon
 * @creationTime 2018年7月6日上午11:46:59
 * @version 1.0
 * @since
 * @email marydon20170307@163.com
 */
@Slf4j
public class Sm4Util
{

	static
	{
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)
		{
			Security.addProvider(new BouncyCastleProvider());
		}
	}

	private static final String ENCODING = "UTF-8";
	public static final String ALGORITHM_NAME = "SM4";
	/**
	 * 加密算法/分组加密模式/分组填充方式
	 * PKCS5Padding-以8个字节为一组进行分组加密
	 * 定义分组加密模式使用：PKCS5Padding
	 */
	public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS7Padding";
	/**
	 * 128-32位16进制；256-64位16进制
	 */
	public static final int DEFAULT_KEY_SIZE = 128;

	public static void main(String[] args)
	{
		try
		{
			String json = "12345678942234567894323456789442345678945234567894623456789472345678948234567894";
			// 自定义的32位16进制密钥
			String key = "86C63180C2806ED1F47B859DE501215B";
			String cipher = Sm4Util.encryptEcb(key, json);
			//05a087dc798bb0b3e80553e6a2e73c4ccc7651035ea056e43bea9d125806bf41c45b4263109c8770c48c5da3c6f32df444f88698c5c9fdb5b0055b8d042e3ac9d4e3f7cc67525139b64952a3508a7619
			System.err.println(cipher);
			System.err.println("base64 cipher : " + Base64.encodeBase64String(cipher.getBytes()));

			// true
			//			System.out.println(Sm4Util.verifyEcb(key, cipher, json));
			String json2 = Sm4Util.decryptEcb(key, cipher);
			System.out.println(json2);

			System.out.println(ByteUtils.toHexString(generateKey()));

			String sourceFileBasePath = "/Users/sars/doc4project/web/e签宝/电子签章服务";

			String sourceFilePath = sourceFileBasePath + ".pdf";
			String encFilePath = sourceFilePath + ".enc";
			File sourceFile = new File(sourceFilePath);
			sm4FileEncryptEcb2(sourceFile, new File(encFilePath), "123312312321");
			sm4FileDecryptEcb2(new File(encFilePath), new File(sourceFileBasePath + "2.pdf"), "123312312321");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 生成ECB暗号
	 * @explain ECB模式（电子密码本模式：Electronic codebook）
	 * @param algorithmName
	 *            算法名称
	 * @param mode
	 *            模式
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception
	{
		Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
		Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
		cipher.init(mode, sm4Key);
		return cipher;
	}

	/**
	 * 自动生成密钥
	 * @explain
	 * @return
	 * @throws Exception
	 */
	public static byte[] generateKey() throws Exception
	{
		return generateKey(DEFAULT_KEY_SIZE);
	}

	/**
	 * @explain
	 * @param keySize
	 * @return
	 * @throws Exception
	 */
	public static byte[] generateKey(int keySize) throws Exception
	{
		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
		kg.init(keySize, new SecureRandom());
		return kg.generateKey().getEncoded();
	}

	/**
	 * sm4加密
	 * @explain 加密模式：ECB
	 *          密文长度不固定，会随着被加密字符串长度的变化而变化
	 * @param hexKey
	 *            16进制密钥（忽略大小写）
	 * @param paramStr
	 *            待加密字符串
	 * @return 返回16进制的加密字符串
	 * @throws Exception
	 */
	public static String encryptEcb(String hexKey, String paramStr) throws Exception
	{
		String cipherText = "";
		// 16进制字符串-->byte[]
		byte[] keyData = ByteUtils.fromHexString(getHexKey(hexKey));
		// String-->byte[]
		byte[] srcData = paramStr.getBytes(ENCODING);
		// 加密后的数组
		byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
		// byte[]-->hexString
		cipherText = ByteUtils.toHexString(cipherArray);
		return cipherText;
	}

	/**
	 * 加密模式之Ecb
	 * @explain
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception
	{
		Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * sm4解密
	 * @explain 解密模式：采用ECB
	 * @param key
	 *            16进制密钥
	 * @param cipherText
	 *            16进制的加密字符串（忽略大小写）
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decryptEcb(String key, String cipherText) throws Exception
	{
		// 用于接收解密后的字符串
		String decryptStr = "";
		// hexString-->byte[]
		byte[] keyData = ByteUtils.fromHexString(getHexKey(key));
		// hexString-->byte[]
		byte[] cipherData = ByteUtils.fromHexString(cipherText);
		// 解密
		byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
		// byte[]-->String
		decryptStr = new String(srcData, ENCODING);
		return decryptStr;
	}

	/**
	 * 解密
	 * @explain
	 * @param key
	 * @param cipherText
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception
	{
		Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(cipherText);
	}

	/**
	 * 校验加密前后的字符串是否为同一数据
	 * @explain
	 * @param key
	 *            16进制密钥（忽略大小写）
	 * @param cipherText
	 *            16进制加密后的字符串
	 * @param paramStr
	 *            加密前的字符串
	 * @return 是否为同一数据
	 * @throws Exception
	 */
	public static boolean verifyEcb(String key, String cipherText, String paramStr) throws Exception
	{
		// 用于接收校验结果
		boolean flag = false;
		// hexString-->byte[]
		byte[] keyData = ByteUtils.fromHexString(key);
		// 将16进制字符串转换成数组
		byte[] cipherData = ByteUtils.fromHexString(cipherText);
		// 解密
		byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
		// 将原字符串转换成byte[]
		byte[] srcData = paramStr.getBytes(ENCODING);
		// 判断2个数组是否一致
		flag = Arrays.equals(decryptData, srcData);
		return flag;
	}

	/**
	 * 加密文件
	 * @param sourceFile
	 * @param encryptFile
	 * @param password
	 * @return
	 */
	public static File sm4FileEncryptEcb(File sourceFile, File encryptFile, String password)
	{
		try (InputStream inputStream = new FileInputStream(sourceFile);
			 OutputStream outputStream = new FileOutputStream(encryptFile);)
		{
			// 获取加密方案
			Cipher cipher = Cipher.getInstance(ALGORITHM_NAME_ECB_PADDING, BouncyCastleProvider.PROVIDER_NAME);
			Key sm4Key = new SecretKeySpec(ByteUtils.fromHexString(getHexKey(password)), ALGORITHM_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, sm4Key);

			// 以加密流写入文件
			CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
			byte[] cache = new byte[1024];
			int nRead = 0;
			while ((nRead = cipherInputStream.read(cache)) != -1)
			{
				outputStream.write(cache, 0, nRead);
				outputStream.flush();
			}
			cipherInputStream.close();
		}
		catch (Exception e)
		{
			log.error("【E2】使用SM4对文件加密失败，原因：{}", e.getMessage(), e);
		}
		return encryptFile;
	}

	/**
	 * 解密密文件
	 * @param sourceFile
	 * @param encryptFile
	 * @param password
	 * @return
	 */
	public static File sm4FileDecryptEcb(File sourceFile, File decryptFile, String password)
	{
		try (InputStream inputStream = new FileInputStream(sourceFile);
			 OutputStream outputStream = new FileOutputStream(decryptFile);)
		{
			// 获取解密方案
			Cipher cipher = Cipher.getInstance(ALGORITHM_NAME_ECB_PADDING, BouncyCastleProvider.PROVIDER_NAME);
			Key sm4Key = new SecretKeySpec(ByteUtils.fromHexString(getHexKey(password)), ALGORITHM_NAME);
			cipher.init(Cipher.DECRYPT_MODE, sm4Key);

			// 以解密流写入文件
			CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = inputStream.read(buffer)) >= 0)
			{
				cipherOutputStream.write(buffer, 0, r);
			}
			cipherOutputStream.close();
		}
		catch (Exception e)
		{
			log.error("【E2】使用SM4对文件解密失败，原因：{}", e.getMessage(), e);
		}
		return decryptFile;
	}

	/**
	 * SM4加密文件
	 *
	 * @param sourceFile  原始文件
	 * @param encryptFile 加密后的文件
	 * @param password    十六进制密钥
	 */
	public static void sm4FileEncryptEcb2(File sourceFile, File encryptFile, String password)
	{
		try (InputStream inputStream = new FileInputStream(sourceFile);
			 OutputStream outputStream = new FileOutputStream(encryptFile);)
		{
			byte[] byteKey = ByteUtils.fromHexString(getHexKey(password));
			byte[] bytes = new byte[1048576];
			int bytesRead;
			while ((bytesRead = inputStream.read(bytes)) != -1)
			{
				byte[] encryptedBytes;
				if (bytesRead == bytes.length)
				{
					encryptedBytes = encrypt_Ecb_Padding(byteKey, bytes);
				}
				else
				{
					byte[] tempBytes = new byte[bytesRead];
					System.arraycopy(bytes, 0, tempBytes, 0, bytesRead);
					encryptedBytes = encrypt_Ecb_Padding(byteKey, tempBytes);
				}
				outputStream.write(encryptedBytes, 0, encryptedBytes.length);
			}
		}
		catch (Exception e)
		{
			log.error("【E2】使用SM4对文件加密失败，原因：{}", e.getMessage(), e);
		}
	}

	/**
	 * SM4解密密文件
	 *
	 * @param sourceFile  原始文件
	 * @param decryptFile 加密后的文件
	 * @param password    密钥
	 */
	public static void sm4FileDecryptEcb2(File sourceFile, File decryptFile, String password)
	{
		try (InputStream inputStream = new FileInputStream(sourceFile);
			 OutputStream outputStream = new FileOutputStream(decryptFile);)
		{
			byte[] byteKey = ByteUtils.fromHexString(getHexKey(password));
			byte[] bytes = new byte[1048592];
			int bytesRead;
			while ((bytesRead = inputStream.read(bytes)) != -1)
			{
				byte[] decryptedBytes;
				if (bytesRead == bytes.length)
				{
					decryptedBytes = decrypt_Ecb_Padding(byteKey, bytes);
				}
				else
				{
					byte[] tempBytes = new byte[bytesRead];
					System.arraycopy(bytes, 0, tempBytes, 0, bytesRead);
					decryptedBytes = decrypt_Ecb_Padding(byteKey, tempBytes);
				}
				outputStream.write(decryptedBytes, 0, decryptedBytes.length);
			}
		}
		catch (Exception e)
		{
			System.out.println("使用SM4对文件解密失败" + e);
			log.error("【E2】使用SM4对文件解密失败，原因：{}", e.getMessage(), e);
		}
	}

	private static String getHexKey(String seed)
	{
		String secureKey = "p2zJ0r:~Vlmw3V:SyJ^Y1DmuFT4gQAwlF7)aLOYIIG4H>|&Z'$[LxM}*Xn(]11&I.JY'<bPOy2z";
		return convertToMd5(secureKey + seed).toLowerCase();
	}
}