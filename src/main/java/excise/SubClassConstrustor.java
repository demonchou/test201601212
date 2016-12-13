package excise;
/**
 * Description:  
 * @author hzzhouhongfei 
 * 2016年5月20日下午9:46:35
 */

class BaseClass
{
	public BaseClass ()
	{
		System.out.println("base con");
		//在产生子类实例时，先调用父类的构造函数，如果有下面的这句话，将会调用子类的test();方法，
		//由于此时子类还没有生成实例，因而会报空指针异常
		test();
	}
	public void test()
	{
		System.out.println("base test");
	}
	
}
public class SubClassConstrustor extends BaseClass
{
	String aString ="Hello";
	public void test()
	{
		System.out.println("sub test");
		System.out.println(aString.length());
	}
	public static void main (String arg[])
	{
		SubClassConstrustor subClassConstrustor = new SubClassConstrustor();
	}

}
