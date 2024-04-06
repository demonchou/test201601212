package excise.thread;


import org.apache.commons.lang3.StringUtils;
import utils.LogMDCUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ InheritableContextRunnable, 2023/5/13 17:14 hzzhouhongfei $$
 */
public abstract class InheritableContextRunnable implements Runnable
{
	/** 请求id */
	private volatile String requestId = null;

	private volatile long pThreadId;

	public InheritableContextRunnable()
	{
		pThreadId = Thread.currentThread().getId();
		requestId = (String) LogMDCUtil.getLogMDCParam(LogMDCUtil.LOG_MDC_REQUEST_ID_KEY);
		if (StringUtils.isBlank(requestId))
		{
			requestId = "-";
		}
	}

	@Override
	public void run()
	{
		LogMDCUtil.setLogMDCParam(LogMDCUtil.LOG_MDC_REQUEST_ID_KEY, requestId);
		try
		{
			execute();
		}
		finally
		{
			//当前工作线程与父线程为同一线程，不需要清除requestId
			if (pThreadId != Thread.currentThread().getId())
			{
				LogMDCUtil.clearLogMDCParams();
			}
		}
	}

	/**
	 * 业务方法,替换Runnable的run方法
	 */
	public abstract void execute();
}
