package com.demonchou.exception;

/**
 * 非法业务参数
 * @author hzzhouhongfei
 * @version $$ IllegalBusinessArgumentException, 2022/9/20 16:07 hzzhouhongfei $$
 */
public class IllegalBusinessArgumentException extends java.lang.IllegalArgumentException
{
	public IllegalBusinessArgumentException(String message)
	{
		super(message);
	}
}
