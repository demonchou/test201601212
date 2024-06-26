package optimial;

import java.lang.reflect.Proxy;

public class JdkProxyFactory
{
	public static void main(String[] args)
	{
		SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
		smsService.send("JdkProxy");
	}

	public static Object getProxy(Object target)
	{
		return Proxy.newProxyInstance(
				// 目标类的类加载
				target.getClass().getClassLoader(),
				// 代理需要实现的接口，可指定多个
				target.getClass().getInterfaces(),
				// 代理对象对应的自定义 InvocationHandler
				new DebugInvocationHandler(target)
		);
	}
}