package common.domain.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import common.constant.ResultStatusConstant;
import common.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * 返回结果信息包装
 *
 * @author hzzhongtingjie
 * @version $ ResponseDto, 2018/9/16 hzzhongtingjie $
 */
@Data
public class ResponseDto<T> implements Serializable
{

	private static final long serialVersionUID = 4165559515556511902L;

	/**
	 * 返回状态码
	 * @see com.netease.epay.coupon.app.constant.ResultStatusConstant
	 */
	private int status = ResultStatusConstant.SUCCESS;

	/**
	 * 错误状态码
	 */
	@Deprecated
	private String errorCode;

	/**
	 * 返回错误信息
	 */
	@Deprecated
	private String message;

	/**
	 * 返回码
	 * @see ErrorCodeEnum#getCode()
	 */
	private String code;

	/**
	 * 返回码英文描述
	 * @see ErrorCodeEnum#getCodeEn()
	 */
	private String codeEn;

	/**
	 * 返回码描述
	 * @see ErrorCodeEnum#getDesc()
	 */
	private String msg;

	/**
	 * 返回数据
	 */
	private T result;

	/**
	 * 表单校验
	 */
	private Map<String, String> errors = new HashMap<>();

	/**
	 * 用于分页查询时，返回总记录数
	 */
	private int totalSize;

	/**
	 * 当前页
	 */
	private int queryPageNo;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 判断业务是否执行成功
	 * fixme 当前为兼容逻辑，status字段 或 code字段任一满足，即认为成功
	 * @return
	 */
	public boolean isSuccess()
	{
		return this.status == ResultStatusConstant.SUCCESS
				|| StringUtils.equalsIgnoreCase(this.code, ErrorCodeEnum.SUCCESS.getCode());
	}
}
