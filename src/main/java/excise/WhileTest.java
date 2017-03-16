package excise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: while语句的测试
 * @Author Created by zhouhongfei on 2017/3/6 19:04.
 * E-mail:zhouhfsix@foxmail.com
 */
public class WhileTest
{

	ExceptionTest exceptionTest = new ExceptionTest();

	/**
     * @Description
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		List<Integer> list = get();
		System.out.println(list.size());

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "Hello");
		map.put("2", "Jack");
		map.put("3", "and");
		map.put("4", "Tom");
	}

	public static List<Integer> get()
	{
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		int index = 0;
		while (index<5)
		{
			System.out.println(list.get(index));
			if(list.get(index)==3)
			{
//				return null;
				continue;
			}else
			{
				list2.add(list.get(index));
				index++;
			}
		}
		return list2;
	}
}