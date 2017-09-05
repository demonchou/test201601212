package excise.notify.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description:维护信息
 * Created by hzzhouhongfei.
 * 2017/7/22 17:12
 */
public class MaintainBean implements Serializable
{

	private static final long serialVersionUID = 7694789013826233276L;

	private String platformId;
	private String gateId;
	private String gateCode;
	private String gateTypeName;
	private String bankName;
	private BigDecimal originBankOrderLimit;
	private BigDecimal originBankDayLimit;
	private BigDecimal originBankMonthLimit;
	private String maintainStart;
	private String maintainEnd;
	private boolean tmpMaintain;
	private boolean hasBackupChannel;
	private BigDecimal banckupBankOrderLimit;
	private BigDecimal banckupBankDayLimit;
	private BigDecimal backupBankMonthLimit;
	private String backupChannelCode;
	private String backupChannelNote;

	public static MaintainBean cloneOf(MaintainBean source)
	{
		MaintainBean target = new MaintainBean();

		target.setPlatformId(source.getPlatformId());
		target.setGateId(source.getGateId());
		target.setGateCode(source.getGateCode());
		target.setGateTypeName(source.getGateTypeName());
		target.setBankName(source.getBankName());
		target.setOriginBankOrderLimit(source.getOriginBankOrderLimit());
		target.setOriginBankDayLimit(source.getOriginBankDayLimit());
		target.setOriginBankMonthLimit(source.getOriginBankMonthLimit());
		target.setMaintainStart(source.getMaintainStart());
		target.setMaintainEnd(source.getMaintainEnd());
		target.setTmpMaintain(source.isTmpMaintain());
		target.setHasBackupChannel(source.isHasBackupChannel());
		target.setBanckupBankOrderLimit(source.getBanckupBankOrderLimit());
		target.setBanckupBankDayLimit(source.getBanckupBankDayLimit());
		target.setBackupBankMonthLimit(source.getBackupBankMonthLimit());
		target.setBackupChannelCode(source.getBackupChannelCode());
		target.setBackupChannelNote(source.getBackupChannelNote());

		return target;
	}

	public String getPlatformId()
	{
		return platformId;
	}

	public void setPlatformId(String platformId)
	{
		this.platformId = platformId;
	}

	public String getGateId()
	{
		return gateId;
	}

	public void setGateId(String gateId)
	{
		this.gateId = gateId;
	}

	public String getGateCode()
	{
		return gateCode;
	}

	public void setGateCode(String gateCode)
	{
		this.gateCode = gateCode;
	}

	public String getGateTypeName()
	{
		return gateTypeName;
	}

	public void setGateTypeName(String gateTypeName)
	{
		this.gateTypeName = gateTypeName;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public BigDecimal getOriginBankOrderLimit()
	{
		return originBankOrderLimit;
	}

	public void setOriginBankOrderLimit(BigDecimal originBankOrderLimit)
	{
		this.originBankOrderLimit = originBankOrderLimit;
	}

	public BigDecimal getOriginBankDayLimit()
	{
		return originBankDayLimit;
	}

	public void setOriginBankDayLimit(BigDecimal originBankDayLimit)
	{
		this.originBankDayLimit = originBankDayLimit;
	}

	public BigDecimal getOriginBankMonthLimit()
	{
		return originBankMonthLimit;
	}

	public void setOriginBankMonthLimit(BigDecimal originBankMonthLimit)
	{
		this.originBankMonthLimit = originBankMonthLimit;
	}

	public String getMaintainStart()
	{
		return maintainStart;
	}

	public void setMaintainStart(String maintainStart)
	{
		this.maintainStart = maintainStart;
	}

	public String getMaintainEnd()
	{
		return maintainEnd;
	}

	public void setMaintainEnd(String maintainEnd)
	{
		this.maintainEnd = maintainEnd;
	}

	public boolean isTmpMaintain()
	{
		return tmpMaintain;
	}

	public void setTmpMaintain(boolean tmpMaintain)
	{
		this.tmpMaintain = tmpMaintain;
	}

	public boolean isHasBackupChannel()
	{
		return hasBackupChannel;
	}

	public void setHasBackupChannel(boolean hasBackupChannel)
	{
		this.hasBackupChannel = hasBackupChannel;
	}

	public BigDecimal getBanckupBankOrderLimit()
	{
		return banckupBankOrderLimit;
	}

	public void setBanckupBankOrderLimit(BigDecimal banckupBankOrderLimit)
	{
		this.banckupBankOrderLimit = banckupBankOrderLimit;
	}

	public BigDecimal getBanckupBankDayLimit()
	{
		return banckupBankDayLimit;
	}

	public void setBanckupBankDayLimit(BigDecimal banckupBankDayLimit)
	{
		this.banckupBankDayLimit = banckupBankDayLimit;
	}

	public BigDecimal getBackupBankMonthLimit()
	{
		return backupBankMonthLimit;
	}

	public void setBackupBankMonthLimit(BigDecimal backupBankMonthLimit)
	{
		this.backupBankMonthLimit = backupBankMonthLimit;
	}

	public String getBackupChannelCode()
	{
		return backupChannelCode;
	}

	public void setBackupChannelCode(String backupChannelCode)
	{
		this.backupChannelCode = backupChannelCode;
	}

	public String getBackupChannelNote()
	{
		return backupChannelNote;
	}

	public void setBackupChannelNote(String backupChannelNote)
	{
		this.backupChannelNote = backupChannelNote;
	}

}
