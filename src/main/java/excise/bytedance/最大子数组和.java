package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 最大子数组和, 2023/10/8 23:04 hzzhouhongfei $$
 */
public class 最大子数组和
{
	public int maxSubArray(int[] nums) {
		int len = nums.length;
		// dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
		int[] dp = new int[len];
		dp[0] = nums[0];
		int res = dp[0];

		for (int i = 1; i < len; i++) {
			if (dp[i - 1] > 0) {
				dp[i] = dp[i - 1] + nums[i];
			} else {
				dp[i] = nums[i];
			}
			res = Math.max(res, dp[i]);
		}
		return res;

		// int pre = 0;
		// int res = nums[0];
		// for (int num : nums) {
		//     pre = Math.max(pre + num, num);
		//     res = Math.max(res, pre);
		// }
		// return res;
	}
}
