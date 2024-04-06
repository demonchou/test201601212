package excise.bytedance;

import org.apache.zookeeper.data.Id;

/**
 *
 * 统一使用 while (left < right) ：
 *
 * 寻找第一个满足XXX的位置：
 * int left = 0, right = nums.length - 1;
 * while (left < right) {
 *   int middle = left + (right - left) / 2;
 *   if (满足XXX) {
 *     right = middle;
 *   } else {
 *     left = middle + 1;
 *   }
 * }
 * 寻找最后一个满足XXX的位置：
 * int left = 0, right = nums.length - 1;
 * while (left < right) {
 *   // +1是为了让相除结果向上取整，这个地方就是两套模板的区别之一
 *   int middle = left + (right - left + 1) / 2;
 *   if (满足XXX) {
 *     left = middle;
 *   } else {
 *     right = middle - 1;
 *   }
 * }
 * @author hzzhouhongfei
 * @version $$ RaftAndFindMin, 2023/9/24 16:07 hzzhouhongfei $$
 */
public class RaftAndFindMin
{
	public int findMin(int[] nums)
	{
		int n = nums.length;
		int l = 0;
		int r = n - 1;

		while (l < r)
		{
			int mid = (l + r) / 2;

			if (nums[mid] < nums[r])
			{
				r = mid;
			}
			else
			{
				l = mid + 1;
			}
		}
		return nums[l];
	}

	public static void main(String[] args)
	{
		int[] nums = { 3, 1, 2 };
		RaftAndFindMin raftAndFindMin = new RaftAndFindMin();
		System.out.println(raftAndFindMin.findMin(nums));
	}

}
