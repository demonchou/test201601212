package com.demonchou.exception;

/**
 * 参数非法
 * @author hzzhouhongfei
 * @version $$ IllegalArgumentException, 2022/9/20 16:08 hzzhouhongfei $$
 */
public class IllegalArgumentException extends RuntimeException
{
	public IllegalArgumentException(String message)
	{
		super(message);
	}
}
