package excise.thread;

import java.util.concurrent.*;

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
		Future<Integer> future1 = executorService.submit(new InheritableContextCallable<Integer>()
		{
			@Override
			public Integer execute() throws Exception {
				return count1();
			}
		});
		return future1.get();
	}

	private int count1()
	{
		System.out.println("累加1");
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
//		ExecutorService executorService = Executors.newFixedThreadPool(5);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(80));
		UserServiceWithThreadPool userService = new UserServiceWithThreadPool();
		userService.setExecutorService(threadPoolExecutor);
		for (int j = 0; j < 7; j++)
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

		threadPoolExecutor.shutdown();

	}
}
