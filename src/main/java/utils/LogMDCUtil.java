package utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;

/**
 * log4j日志MDC工具类
 *
 * @author hzzhangliang1
 * @version $ LogMDCUtil.java, 2017年3月11日 hzzhangliang1 $
 */
public class LogMDCUtil
{
	/**
	 * 放到log4j MDC的key
	 */
	public static final String LOG_MDC_REQUEST_ID_KEY = "requestId";

	/**
	 * 将需要在日志中打印的东西放到MDC, 使用完需进行清楚, 否则可能造成OOM
	 */
	public static void setLogMDCParams()
	{
		String requestId = null;

		AppContext context = AppContext.getContext();
		if (context != null)
		{
			requestId = context.getReqId();
		}

		if (StringUtils.isBlank(requestId))
		{
			requestId = "-";
		}

		MDC.put(LOG_MDC_REQUEST_ID_KEY, requestId);
	}

	/**
	 * 将数据存放到log4j的MDC中
	 *
	 * @param key 键
	 * @param value 值
	 */
	public static void setLogMDCParam(final String key, final String value)
	{
		if (StringUtils.isNotBlank(key))
		{
			MDC.put(key, value);
		}
	}

	/**
	 * 从MDC中获取键对应的值
	 *
	 * @param key 键
	 */
	public static Object getLogMDCParam(final String key)
	{
		return MDC.get(key);
	}

	/**
	 * 清空MDC内容
	 */
	public static void clearLogMDCParams()
	{
		if (MDC.getContext() != null)
		{
			MDC.getContext().clear();
		}
	}
}