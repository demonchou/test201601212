package common;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtil
{

	private static final char[] bcdLookup =
	{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static boolean isInteger(String str)
	{
		try
		{
			new Integer(str);
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}

	public static boolean isLong(String str)
	{
		try
		{
			new Long(str);
		}
		catch (Exception e)
		{

			return false;
		}

		return true;
	}

	/*
	 * 将16进制字符串转换为字符数组
	 */
	public static final byte[] hexStrToBytes(String s)
	{
		byte[] bytes;

		bytes = new byte[s.length() / 2];

		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}

	/*
	* 将字符数组转换为16进制字符串
	*/
	public static final String bytesToHexStr(byte[] bcd)
	{
		StringBuffer s = new StringBuffer(bcd.length * 2);

		for (int i = 0; i < bcd.length; i++)
		{
			s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(bcdLookup[bcd[i] & 0x0f]);
		}

		return s.toString();
	}

	/**
	 * 进行MD5转换
	 * 
	 * @param pass
	 * @return
	 */
	public static String convertToMd5(String passToConvert)
	{
		byte passToConvertByte[] = passToConvert.getBytes();
		String cryptograph = null;
		try
		{
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			byte gottenPassByte[] = messagedigest.digest(passToConvertByte);
			cryptograph = "";
			for (int i = 0; i < gottenPassByte.length; i++)
			{
				String temp = Integer.toHexString(gottenPassByte[i] & 0x000000ff);
				if (temp.length() < 2)
					temp = "0" + temp;
				cryptograph += temp;
			}
		}
		catch (Exception e)
		{
			cryptograph = null;
		}
		return cryptograph;
	}
	
	public static String convertToMd5(String str,String charset) {
		try {
			byte newByte1[] = str.getBytes(charset);
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			byte newByte2[] = messagedigest.digest(newByte1);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < newByte2.length; i++) {
				String temp = Integer.toHexString(newByte2[i] & 0x000000ff);
				if (temp.length() < 2)
					sb.append("0");
				sb.append(temp);
			}
			String cryptograph = sb.toString();
			return cryptograph;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将数字格式化成6位,小于6位的,数字前面用0补足
	 * 
	 * @param num
	 * @return
	 */
	public static String formateNumberToSixLength(Long num)
	{
		if (num == null)
		{
			return "";
		}
		NumberFormat nf = new DecimalFormat("000000");
		String numFormatted = nf.format(num.intValue());

		return numFormatted;
	}

	/**
	 * 将数字格式化成10位,小于10位的,数字前面用0补足
	 * 
	 * @param num
	 * @return
	 */
	public static String formateNumberToTenLength(long num)
	{
		if (num == 0)
		{
			return "";
		}
		NumberFormat nf = new DecimalFormat("0000000000");
		String numFormatted = nf.format(num);

		return numFormatted;
	}

	/**
	 * 将BigDecimal 格式化为 0.00的格式
	 * 
	 * @param big
	 * @return
	 */
	public static String formatBigDecimal(BigDecimal big)
	{
		if (big == null)
		{
			return "";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(big);
	}

	/**
	 * 校验日期格式，格式为YYYY-MM-DD或YYYY/MM/DD
	 * 
	 * @param date
	 *            日期
	 * @return 校验通过true，否则false
	 */
	public static boolean isValidDateFormat(String date)
	{
		boolean flag = false;
		if (date == null || "".equals(date.trim()))
		{
			return false;
		}
		date = date.replace('-', '/');
		String regEx = "^((\\d{2}(([02468][048])|([13579][26]))[\\/\\/\\s]?((((0?"
				+ "[13578])|(1[02]))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
				+ "|(((0?[469])|(11))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|"
				+ "(0?2[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"
				+ "35679])|([13579][01345789]))[\\/\\/\\s]?((((0?[13578])|(1[02]))"
				+ "[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
				+ "[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/\\/\\s]?((0?[" + "1-9])|(1[0-9])|(2[0-8]))))))$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(date);
		flag = m.matches();

		return flag;
	}

	/**
	 * 检查是否是正浮点数 该方法用于判断金额是否是非0，且是否是0.00或则0.0或则 0 格式
	 * 
	 * @param srcToCheck
	 * @return
	 */
	public static boolean ifPositiveFloat(String srcToCheck)
	{
		try
		{
			String regEx = "^[1-9]*[1-9][0-9]*$|^(([0]\\.[0-9]{1,2})|([1-9]{1,}[0-9]*\\.[0-9]{1,2}))$";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(srcToCheck);
			if (!m.matches())
			{
				return false;
			}

			BigDecimal big = new BigDecimal(srcToCheck);
			if (big.compareTo(new BigDecimal(0)) <= 0)
			{
				return false;
			}

			return true;

		}
		catch (Exception e)
		{
			return false;
		}

	}

	/**
	 * 将金额转化为分 并且补足需要的长度
	 * cctao
	 * @param num
	 * @param length
	 * @return
	 */
	public static String formateNumberToAppointLength(BigDecimal num, int length)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			sb.append("0");
		}
		String str = sb.toString();
		NumberFormat nf = new DecimalFormat(str);
		String numFormatted = nf.format(num);
		return numFormatted;
	}

	/**
	 * 
	 * @param orgStr
	 * @param codeType
	 */
	public static String getEncodeStr(String orgStr, String orgCode, String desCode)
	{
		try
		{
			byte[] orgByte = orgStr.getBytes(orgCode);
			String encodedStr = new String(orgByte, desCode);
			return encodedStr;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 获得完整的错误信息
	 *
	 * @param e
	 * @return
	 */
	public static String getFullStackError(Throwable e)
	{

		StringWriter sw = new StringWriter();
		PrintWriter ps = new PrintWriter(sw);
		e.printStackTrace(ps);
		return sw.toString();
	}
	
	/**
	 * 把orderAmount乘以100 然后返回一个字符串
	 * @param orderAmount
	 * @return
	 */
	public static String times100(BigDecimal orderAmount)
	{
		return String.valueOf(orderAmount.movePointRight(2).longValue());
	}
	

	//读取文件流
	public static ByteArrayOutputStream readFile(String filename)
	{
		try
		{
			FileInputStream fileInStream = new FileInputStream(filename);
			ByteArrayOutputStream fileByteStream = new ByteArrayOutputStream();
			int i = 0;
			while ((i = fileInStream.read()) != -1)
			{
				fileByteStream.write(i);
			}
			fileInStream.close();
			return fileByteStream;
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return null;
	}
	
	/*
	 * 检查是否是合法platformId
	 *
	 * @param pId
	 * @return
	 */
	public static boolean isValidPlatformId(String pId)
	{
		try
		{
			String regEx = "^[0-9]{10}PT[0-9]{8}$";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(pId);
			if (!m.matches())
			{
				return false;
			}

			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * sha1算法
	 * @param str
	 * @return
	 */
	public static String getSha1(String str)
	{
		if (str == null || str.length() == 0)
		{
			return null;
		}

		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try
		{
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		}
		catch (Exception e)
		{
			return null;
		}
	}



}
