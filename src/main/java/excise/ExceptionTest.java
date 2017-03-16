package excise;

import common.CommonUtil;

/**
 * Description: 异常测试
 * Author Created by zhouhongfei on 2017/3/6 19:04.
 * E-mail:zhouhfsix@foxmail.com
 */
public class ExceptionTest
{

	/**
     * @Description
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		funB("213df",0);
	}
	public static void funA(String sign, int i)
	{
		int j = 1;
		try
		{
			int h = j/i;
			String result = new String(CommonUtil.hexStrToBytes(sign));
			System.out.println(result + " | " + h);
		} 
		catch (NumberFormatException e)
		{
			throw new NumberFormatException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void funB(String sign, int i)
	{
		try
		{
			funA(sign, i);
		}
		catch(NumberFormatException e)
		{
			System.out.println("num");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("ex");
			e.printStackTrace();
		}
	}

}