package excise;
/**
 * Description:  单例模式
 * @author hzzhouhongfei 
 * 2016年5月22日下午5:50:04
 */
class Base
{
//	//返回这个类的实例.
	private static Base base;
//	
	private Base()
    {
        System.out.println("created an instance");
    }

	public static Base getBase()
{
		return InstanceHolder.base;
	}
	
    public static void creatString()
    {
        System.out.println("creating an instance");
    }
	
	public void test()
	{
		System.out.println(getBase());
	}

//	//生成对象的工厂 synchronized
//	public static synchronized Base getBase()
//	{
//		if (null == base)
//		{
//			base = new Base();
//		}
//		return base;
//	}
    //solve the problem of synchronized
	public static class InstanceHolder
	{
		private static final Base base = new Base();
	}
}
public class Singleton
{
	public static void main(String args[])
	{
        Base.creatString();
	}
}
