package excise.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.weaver.ast.Literal;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ThreeSum, 2023/9/23 22:33 hzzhouhongfei $$
 */
public class ThreeSum
{
	public List<List<Integer>> threeSum(int[] nums)
	{
		int n = nums.length;
		List<List<Integer>> ans = new ArrayList<>();

		Arrays.sort(nums);

		for (int first = 0; first < n; first++)
		{
			if (first > 0 && nums[first] == nums[first - 1])
			{
				continue;
			}

			int third = n - 1;

			for (int second = first + 1; second < third; second++)
			{
				if (second > first + 1 && nums[second] == nums[second - 1])
				{
					continue;
				}

				while (second < third && (nums[first] + nums[second] + nums[third] > 0 || nums[third-1] == nums[third]))
				{
					third--;
				}

				if (second == third)
				{
					break;
				}

				if (nums[first] + nums[second] + nums[third] == 0)
				{
					List<Integer> ans1 = new ArrayList<>();
					ans1.add(nums[first]);
					ans1.add(nums[second]);
					ans1.add(nums[third]);
					ans.add(ans1);
				}

			}
		}

		return ans;
	}

	public static void main(String[] args)
	{
		int[] nums = new int[] { 1,2,-2,-1 };
		ThreeSum threeSum = new ThreeSum();
		System.out.println(threeSum.threeSum(nums));

	}
}
