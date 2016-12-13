package filter;

import filter.*;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter
{

	public List<Filter> filters = new ArrayList<Filter>();

	public FilterChain()
	{
		super();
	}

	public FilterChain addFilter(Filter f)
	{
		filters.add(f);
		return this;
	}

	// 执行filters中的doFilter方法即可
	public String doFilter(String msg)
	{
		String r = msg;
		for (Filter f : filters)
		{
			r = f.doFilter(r);
		}
		return r;
	}
}