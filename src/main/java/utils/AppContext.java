package utils;

/**
 * 应用上下文
 *
 * @author hzzhongtingjie
 * @version $ AppContext.java, 2017年3月11日 hzzhongtingjie $
 */
public class AppContext
{
	/** RPC请求ID标识的key */
	public static final String RPC_REQ_ID_KEY = "req_id";

	/** HTTP请求ID标识的key */
	public static final String HTTP_REQ_ID_KEY = "Request-Id";

	private static final ThreadLocal<AppContext> LOCAL = new ThreadLocal<AppContext>() {
		@Override
		protected AppContext initialValue()
		{
			return new AppContext();
		}
	};

	/**请求唯一id，uuid */
	private String reqId;

	/**
	 * get context.
	 *
	 * @return context
	 */
	public static AppContext getContext()
	{
		return LOCAL.get();
	}

	/**
	 * remove context.
	 */
	public static void removeContext()
	{
		LOCAL.remove();
	}

	public String getReqId()
	{
		return reqId;
	}

	public void setReqId(String reqId)
	{
		this.reqId = reqId;
	}

	public void clean()
	{
		reqId = null;
	}

	@Override
	public String toString()
	{
		return "AppContext [reqId=" + reqId + "]";
	}
}