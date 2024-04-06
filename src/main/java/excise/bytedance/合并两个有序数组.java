package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 合并两个有序数组, 2023/9/26 22:26 hzzhouhongfei $$
 */
public class 合并两个有序数组
{
	public void merge(int[] nums1, int m, int[] nums2, int n)
	{

		int p1 = 0;
		int p2 = 0;

		int cur = 0;

		int[] sorted = new int[m + n];

		while (p1 < m || p2 < n)
		{
			if (p1 == m)
			{
				cur = nums2[p2++];
			}
			else if (p2 == n)
			{
				cur = nums1[p1++];
			}
			else if (nums1[p1] <= nums2[p2])
			{
				cur = nums1[p1++];
			}
			else
			{
				cur = nums2[p2++];
			}

			sorted[p1 + p2 - 1] = cur;

		}

		for (int i = 0; i < m + n; i++)
		{
			nums1[i] = sorted[i];
		}

	}
}
