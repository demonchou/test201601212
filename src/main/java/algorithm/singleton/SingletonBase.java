package algorithm.singleton;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 基本款
 * @author hzzhouhongfei
 * @version $$ SingletonBase, 2020/6/10 23:50 hzzhouhongfei $$
 */
public class SingletonBase
{
	private final static SingletonBase INSTANCE = new SingletonBase();

	public static SingletonBase getInstance()
	{
		return INSTANCE;
	}

	public static void main(String[] args)
	{
		int tom = 0;
		List<Integer> counts = new ArrayList<>();
		long beg = System.currentTimeMillis();
		for (int i = 0; i < 2000; i++)
		{
			for (int j = 0; j < 1000; j++)
			{
				for (int k = 0; k < 100; k++)
				{
					tom++;
					counts.add(k);
				}
			}
		}

		long end = System.currentTimeMillis();

		System.out.println(end - beg);

		System.out.println(JSONObject.toJSONString(counts));

	}
}
