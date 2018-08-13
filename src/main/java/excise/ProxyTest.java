package excise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ProxyTest, 2018/8/12 18:07 hzzhouhongfei $$
 */
public class ProxyTest
{
	public static void main(String[] args)
	{
		System.out.println("--------------");

		Person stranger = (Person) Proxy.newProxyInstance(Teacher.class.getClassLoader(), new Class[] { Person.class },
				new MyInoHandle(new Teacher()));
		stranger.walk();
		System.out.println("--------------");
		stranger.sayHello("孙猴子哎!");
	}

}

class MyInoHandle implements InvocationHandler
{

	private Object poxObject;

	MyInoHandle(Object realObject)
	{
		this.poxObject = realObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws InvocationTargetException, IllegalAccessException
	{
		System.out.println("invoke ====start====");

		System.out.println("正在执行的方法" + method);

		if (null != args)
		{
			System.out.println("传入的实参是" + Arrays.toString(args));
		}
		else
		{
			System.out.println("未传入实参");
		}
		method.invoke(poxObject, args);

		System.out.println("invoke ====end====");

		return null;
	}

}

interface Person
{

	/**
	 * walk
	 */
	void walk();

	/**
	 *  say
	 * @param aString s
	 */
	void sayHello(String aString);

}

class Teacher implements Person
{

	@Override
	public void walk()
	{
		System.out.println("I am walking");
	}

	@Override
	public void sayHello(String name)
	{
		System.out.println("I am saying");
	}

}
