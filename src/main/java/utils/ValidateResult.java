package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by hzzhouhongfei.
 * 2017/6/9 下午11:19
 */
public class ValidateResult
{
	private boolean hasErrors;

	private Map<String, String> errors;
	private String error;

	public ValidateResult()
	{
		this(false);
	}

	public ValidateResult(boolean hasError)
	{
		this.hasErrors = hasError;
		errors = new HashMap<String,String>();
	}

	public boolean hasErrors()
	{
		return hasErrors || !errors.isEmpty();
	}

	public void setHasErrors(boolean hasErrors)
	{
		this.hasErrors = hasErrors;
	}

	public Map<String, String> getErrors()
	{
		return errors;
	}

	public void setErrors(Map<String, String> errors)
	{
		this.errors = errors;
	}

	public void setError(String key, String value)
	{
		errors.put(key, value);
	}

	public String getError(String key)
	{
		return errors.get(key);
	}

	public int errorSize()
	{
		return errors.size();
	}

}
