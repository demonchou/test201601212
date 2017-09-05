package excise.notify.exe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

/**
 * Description:测试商户通知数据处理方法
 * Created by hzzhouhongfei.
 * 2017/7/22 17:24
 */
public class PlatformNotify
{
	public static void main(String[] args)
	{
		Date d = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(simpleDateFormat.format(d));
		List<Integer> years = new ArrayList<>();
		for (int i = year ; i < year + 20 ; ++i)
		{
			years.add(i);
		}
		System.out.println();
		System.out.println(years);

	}
}
