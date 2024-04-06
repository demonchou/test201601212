package excise.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ThreadLocalTest, 2023/5/13 17:14 hzzhouhongfei $$
 */
public class ThreadLocalTest
{

	public static void main(String[] args)
	{
		Thread thread = Thread.currentThread();
		ThreadLocal<Studet> studetThreadLocal = new ThreadLocal<>();
		Studet studet = studetThreadLocal.get();
		if (null == studet)
		{
			studetThreadLocal.set(new Studet(160, 15));
		}
		Studet studet1 = studetThreadLocal.get();
		studet1.setAge(100);
		Studet studet3 = studetThreadLocal.get();
		System.out.println(studet3.getAge());

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 50, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(80));

	}
}
