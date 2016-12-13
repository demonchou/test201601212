package filter;

import filter.*;

public class MsgProcessor
{
	private String msg;
	private FilterChain chain;

	public MsgProcessor()
	{
		super();
	}

	public MsgProcessor(String msg, FilterChain chain)
	{
		this.msg = msg;
		this.chain = chain;
	}

	public String process()
	{
		return chain.doFilter(msg);
	}
}