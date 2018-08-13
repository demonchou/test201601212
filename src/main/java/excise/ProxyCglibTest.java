package excise;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ProxyCglibTest, 2018/8/12 18:07 hzzhouhongfei $$
 */
public class ProxyCglibTest
{
	public static void main(String[] args)
	{
		CglibMethodInterceptor cglibProxy = new CglibMethodInterceptor();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallback(cglibProxy);

		UserService o = (UserService) enhancer.create();
		System.out.println(o.getName(1));
		System.out.println("----------------");
		System.out.println(o.getAge(1));
	}

}

class CglibMethodInterceptor implements MethodInterceptor
{
	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable
	{
		System.out.println("===>before " + methodProxy.getSuperName() + "------");
		Object o1 = methodProxy.invokeSuper(o, args);
		System.out.println("===>after " + methodProxy.getSuperName() + "------");
		System.out.println(o1);
		return null;
	}

}

interface UserService
{
	/**
	 * dd
	 * @param id d
	 * @return d
	 */
	String getName(int id);

	/**
	 * dd
	 * @param id sdd
	 * @return d
	 */
	Integer getAge(int id);
}

class UserServiceImpl implements UserService
{
	@Override
	public String getName(int id)
	{
		System.out.println("------getName------");
		return "Tom";
	}

	@Override
	public Integer getAge(int id)
	{
		System.out.println("------getAge------");
		return 10;
	}

}

