package excise;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description  有关Map的测试
 * @author hzzhouhongfei
 * 2016年8月31日下午5:02:48
 */
public class MapTest
{

	/**
	 * @Description 有关Map的测试
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		Map<String, String > map = new HashMap<>();
		map.put("1", "Hello");
		map.put("2", "Jack");
		map.put("3", "and");
		map.put("4", "Tom");
		map.put("5", null);
		for (Map.Entry<String, String > entry : map.entrySet())
		{
			if(entry.getValue() == null)
			{
				System.out.println(entry.getKey() + " 的值是空！");
			}
			System.out.println(entry.getKey() + "|" + entry.getValue());
		}
	}

}
