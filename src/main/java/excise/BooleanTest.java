package excise;
/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年7月1日下午9:59:00
 */
public class BooleanTest
{
	public static void main(String[] args)
	{
		boolean bool = fun();
		System.out.println(bool);
	}
	
	public static boolean  fun()
	{
		int a = 1;
		int b = 1;
		if (a == b)
		{
			return false;
		}
		
		return true ;
	}

}
