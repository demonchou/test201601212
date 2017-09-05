package excise.notify.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by hzzhouhongfei.
 * 2017/7/22 17:13
 */
public class NotifyMaintainMessageDTO implements Serializable
{
	private static final long serialVersionUID = 427208285031033298L;
	private List<MaintainBean> maintainList;
	private List<String> keyList = new ArrayList<>();

	public List<MaintainBean> getMaintainList()
	{
		return maintainList;
	}

	public void setMaintainList(List<MaintainBean> maintainList)
	{
		this.maintainList = maintainList;
	}

	public List<String> getKeyList()
	{
		return keyList;
	}

}
