package excise;

/**
 * Description:传参
 * Created by hzzhouhongfei.
 * 2017/6/6 上午12:54
 */
public class ChancanTest
{
	public static void main(String[] args)
	{
		A a = new A();
		B b = new B();
		int size = 2;
		for (int i = 0; i <= (size-1) && i <= 2; i++ )
		{
			System.out.println("成功！");
		}
		String as = "a";
		if ("a".equals(as))
		{
			a.setName("jack");
			System.out.println(a.getName());
		}



	}



}

class A
{
	String name ;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}

class B
{
	String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
