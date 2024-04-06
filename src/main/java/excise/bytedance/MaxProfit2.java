package excise.bytedance;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。

 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 *      总利润为 4 + 3 = 7 。
 *
 * 动态规划：
 * 1、初始状态
 * 2、状态转移方程
 * 3、最终结果
 * @author hzzhouhongfei
 * @version $$ MaxProfit2, 2023/9/21 21:54 hzzhouhongfei $$
 */
public class MaxProfit2
{
	public int maxProfit(int[] prices)
	{
		int n = prices.length;
		int[][] dp = new int[n][2];

		// 初始状态
		// 没有持有
		dp[0][0] = 0;
		// 持有
		dp[0][1] = -prices[0];

		for (int j = 1; j < n; j++)
		{
			// 状态转移方程。考虑前一天的情况，前一天持有和没持有两种状态
			// 这一天交易完后手里没有股票，可能前一天已经没有股票，即 dp[i−1][0]，
			// 或者前一天结束持有一支股票，即 dp[i−1][1]，这时我们要将其卖出，并获得 prices[i]的收益。

			dp[j][0] = Math.max(dp[j - 1][0], dp[j - 1][1] + prices[j]);

			// 可能前一天持有一支股票，即 dp[i−1][1]，
			// 或者前一天结束时没有股票，即 dp[i−1][0]，这时我们将其买入，并减少 prices[i] 的收益

			dp[j][1] = Math.max(dp[j - 1][0]-prices[j], dp[j - 1][1]);
		}

		// 最终结果
		return dp[n-1][0];
	}

	public static void main(String[] args)
	{
		MaxProfit2 solution = new MaxProfit2();
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		int result = solution.maxProfit(prices);
		// 输出: 7 (在第二天买入，第三天卖出，第四天买入，第五天卖出)
		System.out.println(result);
	}
}
