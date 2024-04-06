package excise.thread;


import org.apache.commons.lang3.StringUtils;
import utils.LogMDCUtil;

import java.util.concurrent.Callable;

/**
 *
 * @author hzzhouhongfei
 * @version $$ InheritableContextRunnable, 2023/5/13 17:14 hzzhouhongfei $$
 */
public abstract class InheritableContextCallable<T> implements Callable<T>
{
	private volatile String requestId = null;

	private volatile long pThreadId;

	public InheritableContextCallable()
	{
		pThreadId = Thread.currentThread().getId();
		requestId = (String) LogMDCUtil.getLogMDCParam(LogMDCUtil.LOG_MDC_REQUEST_ID_KEY);
		if (StringUtils.isBlank(requestId))
		{
			requestId = "-";
		}
	}

	/**
	 * Computes a result, or throws an exception if unable to do so.
	 *
	 * @return computed result
	 * @throws Exception if unable to compute a result
	 */
	@Override
	public T call() throws Exception
	{
		LogMDCUtil.setLogMDCParam(LogMDCUtil.LOG_MDC_REQUEST_ID_KEY, requestId);
		try
		{
			return execute();
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
	 * 业务方法,替换Callable的call方法
	 *
	 * @return
	 * @throws Exception
	 */
	public abstract T execute() throws Exception;
}
