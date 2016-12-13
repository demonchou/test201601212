package common;

import common.*;

@SuppressWarnings("serial")
public class BusinessException extends AppException
{

	public BusinessException()
	{
		super();
	}

	public BusinessException(Exception exception)
	{
		super(exception);
	}

	public BusinessException(String msg)
	{
		super(msg);
	}

	public BusinessException(String msg, Exception e)
	{
		super(msg, e);
	}

}
