package excise.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 3
 * 输出："213"
 * @author hzzhouhongfei
 * @version $$ 排列序列, 2023/9/28 16:34 hzzhouhongfei $$
 */
public class 排列序列
{
	public String getPermutation(int n, int k)
	{
		List<Integer> nums = new ArrayList<>();
		for (int i = 1; i <= n; i++)
		{
			nums.add(i);
		}

		StringBuilder result = new StringBuilder();

		k--; // 将k转换为0-based索引
		int fact = 1;
		for (int i = 1; i <= n - 1; i++)
		{
			fact *= i;
		}

		for (int i = n - 1; i >= 0; i--)
		{
			int index = k / fact;
			result.append(nums.get(index));
			nums.remove(index);
			k %= fact;
			if (i > 0)
			{
				fact /= i;
			}
		}

		return result.toString();
	}

	public static void main(String[] args)
	{
		排列序列 solution = new 排列序列();
		int n = 3;
		int k = 3;
		String result = solution.getPermutation(n, k);
		// 输出: "213"
		System.out.println(result);
	}
}
