package excise.notify.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * Created by hzzhouhongfei.
 * 2017/7/22 17:15
 */
public enum IniConstant
{
	PLATFORM_TYPE("PLATFORM_TYPE", "支付活动"),
	EXCLUDE_PLATFORM_FILTER("EXCLUDE_PLATFORM_FILTER", "抽奖活动");
	private String type;
	private String desc;

	IniConstant(String type, String desc)
	{
		this.type = type;
		this.desc = desc;
	}

	public static IniConstant getIniConstantByValue(String value)
	{
		for (IniConstant IniConstant : values())
		{
			if (StringUtils.equals(IniConstant.getType(), value))
			{
				return IniConstant;
			}
		}
		return null;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}
}
