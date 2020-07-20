package algorithm.singleton;

/**
 *
 * @author hzzhouhongfei
 * @version $$ SingletonLazy, 2020/6/10 23:52 hzzhouhongfei $$
 */
public class SingletonLazy
{
	private static SingletonLazy INSTANCE = null;

	public static SingletonLazy getInstance()
	{
		if (null == INSTANCE)
		{
			synchronized (SingletonLazy.class){
				if (null == INSTANCE)
				{
					INSTANCE = new SingletonLazy();
				}
			}
		}
		return INSTANCE;
	}

}
