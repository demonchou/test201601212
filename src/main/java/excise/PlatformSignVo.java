package excise;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @Description  平台信息，主要面向二代及其以上的接口
 * @author hzzhouhongfei 
 * 2016年7月18日下午4:19:49
 */
public class PlatformSignVo implements Serializable
{
	private static final long serialVersionUID = 5403598357977904382L;
	
	private String platformId; //平台商户号
	private String signType; //商户签名方式支持
	private Timestamp ctreateTime; //创建时间
	private Timestamp updateTime; //修改时间
	private String signMode; //签名模式 DEFAULT(默认)、CA(CA证书)、COMPATIBLE(兼容)
	
	public PlatformSignVo()
	{
		super();
	}
	
	public PlatformSignVo(String platformId, String signType, Timestamp createTime, Timestamp updateTime, String signMode)
	{
		this.platformId = platformId;
		this.signType = signType;
		this.ctreateTime = createTime;
		this.updateTime = updateTime;
		this.signMode = signMode;
	}
	
	@Override
	public String toString()
	{
		return "PlatformSignVo [platformId=" + platformId + ", signType=" + signType + ", ctreateTime=" + ctreateTime
				+ ", updateTime=" + updateTime + ", signMode=" + signMode + "]";
	}

	public String getPlatformId() 
	{
		return platformId;
	}
	
	public void setPlatformId(String platformId) 
	{
		this.platformId = platformId;
	}
	
	public String getSignType() 
	{
		return signType;
	}
	
	public void setSignType(String signType) 
	{
		this.signType = signType;
	}
	
	
	public Timestamp getCtreateTime() 
	{
		return ctreateTime;
	}
	
	public void setCtreateTime(Timestamp ctreateTime) 
	{
		this.ctreateTime = ctreateTime;
	}
	
	public Timestamp getUpdateTime() 
	{
		return updateTime;
	}
	
	public void setUpdateTime(Timestamp updateTime) 
	{
		this.updateTime = updateTime;
	}
	
	public String getSignMode()
	{
		return signMode;
	}

	public void setSignMode(String signMode)
	{
		this.signMode = signMode;
	}
	
//	@Override
//	public int hashCode()
//	{
//		final int PRIME = 31;
//		return new HashCodeBuilder(Integer.valueOf(getPlatformId())%2==0?Integer.valueOf(getPlatformId())+1:Integer.valueOf(getPlatformId()), PRIME).
//				toHashCode();
//	}
//	
//	@Override
//	public boolean equals(Object o)
//	{
//		if (o == null)
//			return false;
//		if (o == this)
//			return true;
//		if (o.getClass() != getClass())
//			return false;
//		PlatformSignVo e = (PlatformSignVo) o;
//		return new EqualsBuilder().
//				append(getPlatformId(), e.getPlatformId()).
//				isEquals();
//	}
}