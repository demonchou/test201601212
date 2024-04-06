package excise.bytedance;

import java.util.Collection;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

/**
 * 生成随机红包 - (http://www.open-open.com/lib/view/open1392943229641.html)
 *
 * Created by hzguohongguang on 2016/5/5.
 */
public class RandomValueGenerator
{
	private Random random = null;

	public RandomValueGenerator()
	{
		random = new Random(System.currentTimeMillis());
	}

	public static void main(String[] args)
	{
		RandomValueGenerator randomValueGenerator = new RandomValueGenerator();
		randomValueGenerator.generate(100000000, 5000000, 1, 1000);
	}

	/**
	 * 以分为单位，返回随机生成的介于min 和 max 之间的红包
	 *
	 * @param total 总金额
	 * @param count 生成数量
	 * @param min 最小金额
	 * @param max 最大金额
	 * @return 金额列表
	 */
	public long[] generate(long total, int count, int min, int max)
	{
		long orginalTotalAmount = total;
		long[] result = new long[count];

		//边界检查
		if (min * count > total || max * count < total)
		{
			// todo 统一返回错误码
			throw new IllegalArgumentException(String.format("参数不合理。total:%s, count:%s, min:%s, max:%s", total, count, min, max));
		}

		if (min * count == total)
		{
			for (int i = 0; i < count; i++)
			{
				result[i] = min;
			}
			return result;
		}

		if (max * count == total)
		{
			for (int i = 0; i < count; i++)
			{
				result[i] = max;
			}
			return result;
		}

		long average = total / count;

		for (int i = 0; i < result.length; i++)
		{
			//因为小红包的数量通常是要比大红包的数量要多的，这里的概率要调换过来。
			//当随机数>平均值，则产生小红包
			//当随机数<平均值，则产生大红包
			if (nextLong(min, max) > average)
			{
				// 在平均线上减钱
				long temp = min + xRandom(min, average);
				result[i] = temp;
				total -= temp;
			}
			else
			{
				// 在平均线上加钱
				long temp = max - xRandom(average, max);
				result[i] = temp;
				total -= temp;
			}
		}

		// 如果还有余钱，则尝试加到小红包里，如果加不进去，则尝试下一个。
		while (total > 0)
		{
			for (int i = 0; i < result.length; i++)
			{
				if (total > 0 && result[i] < max)
				{
					result[i]++;
					total--;
				}
			}
		}

		// 如果钱是负数了，还得从已生成的小红包中抽取回来
		while (total < 0)
		{
			for (int i = 0; i < result.length; i++)
			{
				if (total < 0 && result[i] > min)
				{
					result[i]--;
					total++;
				}
			}
		}

		long resultSum = sum(result);
		if (resultSum != orginalTotalAmount)
		{
			// todo 统一返回错误码
			throw new IllegalArgumentException(
					String.format("生成随机数组之和与总金额不一致，total:%s，resultSum:%s, count:%s, min:%s, max:%s", orginalTotalAmount,
							resultSum, count, min, max));
		}
		Multimap<Long, Long> multimap = ArrayListMultimap.create();

		for (long value : result)
		{
			multimap.put(value, value);
		}

		for (Long key : multimap.keySet())
		{
			Collection<Long> longs = multimap.get(key);
			System.out.println(key + "|" + longs.size());
		}

		return result;
	}

	private long sum(long[] resultArray)
	{
		long resultSum = 0;
		for (long resultItem : resultArray)
		{
			resultSum += resultItem;
		}

		return resultSum;
	}

	/**
	 * 生产min和max之间的随机数，但是概率不是平均的，从min到max方向概率逐渐加大。
	 * 先平方，然后产生一个平方值范围内的随机数，再开方，这样就产生了一种“膨胀”再“收缩”的效果。
	 */
	private long xRandom(long min, long max)
	{
		if (min == max)
		{
			max++;
		}

		return sqrt(nextLong(sqr(max - min)));
	}

	private long sqrt(long n)
	{
		return (long) Math.sqrt(n);
	}

	private long sqr(long n)
	{
		return n * n;
	}

	private long nextLong(long n)
	{
		return (long) (Math.random() * n);
	}

	private long nextLong(long min, long max)
	{
		return random.nextInt((int) (max - min + 1)) + min;
	}
}
