package excise.bytedance;

import java.util.PriorityQueue;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 数组中的第K个最大元素, 2023/9/27 22:13 hzzhouhongfei $$
 */
public class 数组中的第K个最大元素
{
	//	int quickselect(int[] nums, int l, int r, int k)
	//	{
	//		if (l == r)
	//		{
	//			return nums[k];
	//		}
	//
	//		// 以最左边的数为参考点
	//		int x = nums[l], i = l - 1, j = r + 1;
	//
	//		 // 进行一轮快速排序，l,r
	//		while (i < j)
	//		{
	//			do
	//			{
	//				i++;
	//			}
	//			while (nums[i] < x);
	//			do
	//			{
	//				j--;
	//			}
	//			while (nums[j] > x);
	//
	//			if (i < j)
	//			{
	//				int tmp = nums[i];
	//				nums[i] = nums[j];
	//				nums[j] = tmp;
	//			}
	//		}
	//
	//
	//		if (k <= j)
	//		{
	//			return quickselect(nums, l, j, k);
	//		}
	//		else
	//		{
	//			return quickselect(nums, j + 1, r, k);
	//		}
	//	}
	//
	//	public int findKthLargest(int[] _nums, int k)
	//	{
	//		int n = _nums.length;
	//		return quickselect(_nums, 0, n - 1, n - k);
	//	}
	public int findKthLargest(int[] nums, int k)
	{
		PriorityQueue queue = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++)
		{
			queue.offer(nums[i]);
		}

		for (int i = k; i < nums.length; i++)
		{
			int value = (Integer) queue.peek();
			if (nums[i] > value)
			{
				queue.poll();
				queue.offer(nums[i]);
			}
		}
		return (int) queue.peek();
	}

	public static void main(String[] args)
	{
		数组中的第K个最大元素 solution = new 数组中的第K个最大元素();
		int[] nums = new int[] { 4, 1, 3, 2, 6, 5 };
		System.out.println(solution.findKthLargest(nums, 2));
	}
}
