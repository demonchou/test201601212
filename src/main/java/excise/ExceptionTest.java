package excise;

import util.CommonUtil;

/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年8月23日下午10:13:34
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
