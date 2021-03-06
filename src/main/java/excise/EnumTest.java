package excise;
/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年6月28日下午8:59:08
 */

enum PlatformRequestType
{
    CHANGE_BANK("bankChange", "申请变更提现银行卡","Hello"),
    COMMON_REQUEST("commonRequest", "通用服务请求","hello"),
    OPEN_SUB_PLATFORM("openSubPlatform", "申请开通集团子账户", "hello"),
    CHANGE_CERT("certChange", "申请变更资质信息","hello"),
    ADD_TRADE_FEERATE("addTradeFeerate", "新增交易费率","hello"),
    ADD_WITHDRAW_FEERATE("addWithdrawFeerate", "新增提现费率","hello");

    private String value;
    private String desc;
    private String index;

    private PlatformRequestType(String value, String desc, String index)
    {
      this.value = value;
      this.desc = desc;
      this.index = index;
    }

    public String getValue()
    {
      return this.value;
    }

    public void setValue(String value)
    {
      this.value = value;
    }

    public String getDesc()
    {
      return this.desc;
    }

    public void setDesc(String desc)
    {
      this.desc = desc;
    }
}

public class EnumTest
{
	public static void main(String[] args)
	{
		System.out.println(PlatformRequestType.COMMON_REQUEST.getValue());
		System.out.println(PlatformRequestType.COMMON_REQUEST.getDesc());
	}

}
