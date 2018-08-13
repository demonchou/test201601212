package common.domain.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import lombok.Data;

/**
 * @author sars
 */
@Data
public class ApayHttpResponseDto<T> implements Serializable
{
	/** 调用响应码 */
	private String responseCode;
	/** 调用响应信息 */
	private String responseMsg;
	/** 业务数据 */
	private String data;
	/** 签名 */
	private String signature;
	/** 业务数据 */
	private T bizData;

	public T getBizData()
	{
		return this.bizData = JSON.parseObject(data, new TypeReference<T>() {
		}.getType());
	}
}