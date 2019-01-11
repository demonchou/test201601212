package excise.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池测试
 * @author hzzhouhongfei
 * @version $$ UserServiceWithThreadPool, 2018/12/13 13:46 hzzhouhongfei $$
 */
public class UserServiceWithThreadPool
{
	private static int success = 0;
	private static int fail = 0;
	private ExecutorService executorService;

	public int count() throws Exception
	{
		Future<Integer> future1 = executorService.submit(new Callable<Integer>()
		{

			@Override
			public Integer call()
			{
				return count1();
			}
		});
		return future1.get();
	}

	private int count1()
	{
		try
		{
			Thread.sleep(1000L);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public ExecutorService getExecutorService()
	{
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService)
	{
		this.executorService = executorService;
	}

	public static void main(String[] args) throws Exception
	{
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		UserServiceWithThreadPool userService = new UserServiceWithThreadPool();
		userService.setExecutorService(executorService);
		for (int j = 0; j < 5; j++)
		{
			int k = userService.count();
			if (k == 0)
			{
				success++;
			}
			else
			{
				fail++;
			}

		}

		System.out.println(success + "：" + fail);

		executorService.shutdown();

	}
}
