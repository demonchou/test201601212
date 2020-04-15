package excise;

class Info
{ // 定义信息类
	/**
	 * // 定义name属性，为了与下面set的name属性区别开
	 */
	private String name = "name";
	/**
	 * // 定义content属性，为了与下面set的content属性区别开
	 */
	private String content = "content";
	/**
	 * // 设置标志位,初始时先生产
	 */
	private boolean flag = true;
	
	public synchronized void set(String name, String content)
	{
		while (!flag)
		{
			try
			{
				super.wait();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		// 设置名称
		this.setName(name);
		try
		{
			Thread.sleep(300);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		// 设置内容
		this.setContent(content);
		// 改变标志位，表示可以取走
		flag = false;
		super.notify();
	}
	
	public synchronized void get()
	{
		while (flag)
		{
			try
			{
				super.wait();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			Thread.sleep(300);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(this.getName() + " --> " + this.getContent());
		// 改变标志位，表示可以生产
		flag = true;
		super.notify();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}

class Producer implements Runnable
{
	// 通过Runnable实现多线程
	private Info info = null; // 保存Info引用

	public Producer(Info info)
	{
		this.info = info;
	}

	public void run()
	{
		// 定义标记位
		boolean flag = true;
		for (int i = 0; i < 10; i++)
		{
			if (flag)
			{
				// 设置名称
				this.info.set("姓名--1", "内容--1");
				flag = false;
			} else
			{
				// 设置名称
				this.info.set("姓名--2", "内容--2");
				flag = true;
			}
		}
	}
}

class Consumer implements Runnable
{
	private Info info = null;

	public Consumer(Info info)
	{
		this.info = info;
	}

	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			this.info.get();
		}
	}
}

public class ThreadCaseDemo03
{
	public static void main(String args[])
	{
		Info info = new Info(); // 实例化Info对象
		Producer pro = new Producer(info); // 生产者
		Consumer con = new Consumer(info); // 消费者
		new Thread(pro).start();
		// 启动了生产者线程后，再启动消费者线程
		try
		{
			Thread.sleep(500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		new Thread(con).start();
	}
}