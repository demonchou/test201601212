package excise;
/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年6月24日下午5:40:29
 */
public class SubString
{

	public static void main(String[] args)
	{
		String aString = "";
		String accountId = "zhouhongfei@wyb.163.com";
		String sub = accountId.substring(0,accountId.length()-12);
		int a = accountId.indexOf("@");
		String sub2 = accountId.substring(a);
		System.out.println(sub);
		System.out.println(sub2);
	}

}
