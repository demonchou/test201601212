package excise.notify.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import excise.notify.constant.IniConstant;

/**
 * Description:
 * Created by hzzhouhongfei.
 * 2017/7/22 17:14
 */
public class NotifyMaintainMessageTypeDTO implements Serializable
{
	private static final long serialVersionUID = 6739924642570535835L;

	private List<NotifyMaintainMessageDTO> messageList;
	private String messageType; // 消息类型：银行维度和商户维度
	private String notifyType; // 通知类型：发送维护，取消维护

	public List<NotifyMaintainMessageDTO> getMessageList()
	{
		return messageList;
	}

	public void setMessageList(List<NotifyMaintainMessageDTO> messageList)
	{
		this.messageList = messageList;
	}

	public String getMessageType()
	{
		return messageType;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public String getNotifyType()
	{
		return notifyType;
	}

	public void setNotifyType(String notifyType)
	{
		this.notifyType = notifyType;
	}

	public String toMessage(String messageType)
	{
		if (StringUtils.equals(messageType, IniConstant.PLATFORM_TYPE.getType()))
		{
//			return JSON.toJSONString(this, IniConstant.EXCLUDE_PLATFORM_FILTER, SerializerFeature.DisableCircularReferenceDetect);
			return null;
		}
		return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
	}

}
