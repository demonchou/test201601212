package filter;

import filter.FilterChain;
import filter.HtmlFilter;
import filter.MsgProcessor;
import filter.SensitiveFilter;

public class MainClass
{
	public static void main(String[] args)
	{
		// 需要被过滤的语句
		String msg = "被就业了：），敏感信息，<script>";// 就业了：），信息，&lt;script&gt;

		// 搞一个过过滤链
		FilterChain chain = new FilterChain();
		chain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());
		// 实例化处理类
		MsgProcessor mp = new MsgProcessor(msg, chain);
		String r = mp.process();

		System.out.println(r);
	}
}