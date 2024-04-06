package common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 券派发账号类型枚举
 * @author hzxietao
 * @version $$ DistributeAccountTypeEnum, 2019-07-19 hzxietao $$
 */
public enum DistributeAccountTypeEnum
{
	/**
	 * URS账号
	 */
	URS_ACCOUNT("URS_ACCOUNT", "URS账号"),
	/**
	 * 映射账号
	 */
	MAPPING_ACCOUNT("MAPPING_ACCOUNT", "映射账号");

	private String code;
	private String desc;

	DistributeAccountTypeEnum(String code, String desc)
	{
		this.code = code;
		this.desc = desc;
	}

    /**
     * 根据CODE查询相应枚举对象
     * @param code
     * @return
     */
	public static DistributeAccountTypeEnum getEnumByCode(String code)
	{
		if (StringUtils.isBlank(code))
		{
			return null;
		}

		for (DistributeAccountTypeEnum accountTypeEnum : values())
		{
			if (StringUtils.equalsIgnoreCase(code, accountTypeEnum.getCode()))
			{
				return accountTypeEnum;
			}
		}

		return null;
	}

	public String getCode()
	{
		return code;
	}

	public String getDesc()
	{
		return desc;
	}
}
