package common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 积分错误码枚举
 *
 * @author hzxietao
 * @version $$ ErrorCodeEnum, 2020/12/24 hzxietao $$
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum
{
	/**
	 * 成功
	 */
	SUCCESS("0000", "SUCCESS", "成功"),
	/**
	 * 非法参数
	 */
	ILLEGAL_PARAM("0001", "ILLEGAL_PARAM", "非法参数"),
	/**
	 * 业务异常
	 */
	BUSINESS_EXCEPTION("0002", "BUSINESS_EXCEPTION", "业务异常"),
	/**
	 * 未知异常
	 */
	UNKNOWN_EXCEPTION("0003", "UNKNOWN_EXCEPTION", "未知异常");

	private final String code;
	private final String codeEn;
	private final String desc;

	/**
	 * 获取重载错误码，格式如"350000"，前两位为系统代码，后四位为积分内部错误码
	 * @return
	 */
	public String getCode()
	{
		return "BizType.JifenSystem.getBizCode()" + this.code;
	}

	@Override
	public String toString()
	{
		return code + "|" + codeEn + "|" + desc;
	}
}
