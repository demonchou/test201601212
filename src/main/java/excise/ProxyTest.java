package excise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 动态代理
 * @author hzzhouhongfei 
 * 2016年6月15日下午9:35:57
 */

interface Person
{
	void walk();
	void sayHello(String aString);
}
class Teacher implements Person
{
	public void walk()
	{
		System.out.println("waliking");
	}
	public void sayHello(String name)
	{
		System.out.println(name);
	}
}
class myInoHandle implements InvocationHandler
{
	public Object invoke(Object proxy, Method method, Object[] args)
	{
		System.out.println("正在执行的方法"+ method);
		if (null != args)
		{
			System.out.println("传入的实参是"+ args);
			for (int i = 0; i < args.length; i++)
			{
				System.out.println(args[i]);
			}
		}
		else 
		{
			System.out.println("未传如实参");
		}
		System.out.println("Invoke END");
		return null;
	}
}
public class ProxyTest
{
	public static void main(String[] args)
	{
		Person teacher = (Person)Proxy.newProxyInstance(
                Teacher.class.getClassLoader(), new Class[] { Person.class}, new myInoHandle());
		teacher.walk();
		teacher.sayHello("孙猴子哎!");
	}
}
