package excise.bytedance;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * @author hzzhouhongfei
 * @version $$ 最长连续序列, 2023/9/28 11:35 hzzhouhongfei $$
 */
public class 最长连续序列
{
	public int longestConsecutive(int[] nums)
	{
		int maxLen = Integer.MIN_VALUE;
		int n = nums.length;
		Set<Integer> set = new HashSet<>();

		for (int num : nums)
		{
			set.add(num);
		}

		for (int num : nums)
		{
			if (!set.contains(num - 1))
			{
				int deep = 0;
				int curNum = num;
				do
				{
					deep++;
				}
				while (set.contains(++curNum));
				maxLen = Math.max(deep, maxLen);
			}
		}
		return maxLen;
	}

	public static void main(String[] args)
	{
		int nums[] = { 8, 7, 6, 5, 0, 1, 2 };
		最长连续序列 ans = new 最长连续序列();
		System.out.println(ans.longestConsecutive(nums));
	}
}
