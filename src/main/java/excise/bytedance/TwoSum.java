package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ TwoSum, 2023/9/22 01:08 hzzhouhongfei $$
 */
public class TwoSum
{
	public int[] twoSum(int[] nums, int target)
	{

		int n = nums.length;

		int[] result = new int[2];

		for (int i = 0; i < n; i++)
		{
			for (int j = i + 1; j < n; j++)
			{
				if (nums[i] + nums[j] == target)
				{
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}

		return result;
	}

	public static void main(String[] args)
	{
		int[] nums = {2,7,11,15};
		TwoSum twoSum = new TwoSum();
		int[] result = twoSum.twoSum(nums, 9);
		System.out.println(result[0]+" "+result[1]);
	}
}
