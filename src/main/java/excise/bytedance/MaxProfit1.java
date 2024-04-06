package excise.bytedance;

/**
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 *
 * 遍历股票价格数组，不断更新最低购买价格 minPrice 和当前的最大利润 maxProfit。
 * 如果找到更低的购买价格，就更新 minPrice，如果找到更大的利润，就更新 maxProfit。
 * 最后返回 maxProfit 即可。
 *
 * @author hzzhouhongfei
 * @version $$ MaxProfit1, 2023/9/21 21:51 hzzhouhongfei $$
 */
public class MaxProfit1
{
	public int maxProfit(int[] prices)
	{
		int cost = Integer.MAX_VALUE;
		int profit = 0;

		for (int price : prices)
		{
			cost = Math.min(cost, price);
			profit = Math.max(profit, price - cost);
		}

		return profit;

	}

	public static void main(String[] args)
	{
		MaxProfit1 solution = new MaxProfit1();
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		int result = solution.maxProfit(prices);
		// 输出: 5 (在第二天买入，第五天卖出，获得最大利润5)
		System.out.println(result);
	}
}
