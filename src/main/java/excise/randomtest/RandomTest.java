package excise.randomtest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

/**
 * 模拟随机数
 * @author hzzhouhongfei
 * @version $$ RandomTest, 2018/10/19 10:57 hzzhouhongfei $$
 */
public class RandomTest
{
	public static void main(String[] args)
	{
		int average = 0;
		int variance = 4;
		Random random = new Random();
		Multimap<Integer, Double> doubleDoubleMultimap = TreeMultimap.create();
		Map<Integer, Integer> map = new HashMap<>(16);
		Map<Integer, String> showMap = new TreeMap<>();
		Map<Integer, String> showCountMap = new TreeMap<>();

		for (int i = 0; i < 10000; i++)
		{
			double randomValue = Math.sqrt(variance) * random.nextGaussian() + average;
			int intValue = (int) randomValue;
			doubleDoubleMultimap.put(intValue, randomValue);
		}
		for (Integer key : doubleDoubleMultimap.keySet())
		{
			map.put(key, doubleDoubleMultimap.get(key).size());
		}

		for (int key2 : map.keySet())
		{
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < map.get(key2); j++)
			{
				sb.append("#");
			}
			showMap.put(key2, sb.toString());
			showCountMap.put(key2, String.valueOf(map.get(key2)));
		}
		for (int key2 : showMap.keySet())
		{
			System.out.print(key2 + ":" + "\t");
			System.out.println(showMap.get(key2));
		}
		System.out.println("================================================");
		System.out.println("========== average = " + average + "; variance = " + variance + "  ==========");
		System.out.println("================================================");
		System.out.println("================ 正态分布 =======================");
		System.out.println("================================================");
		for (int key2 : showCountMap.keySet())
		{
			System.out.print(key2 + " count :" + "\t");
			System.out.println(showCountMap.get(key2));
		}
	}
}
