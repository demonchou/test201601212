package excise.bytedance;

/**
 *221. 最大正方形
 *
 * dp(i,j) 表示以 (i,j) 为右下角，且只包含 1 的正方形的边长最大值。
 * 如果我们能计算出所有 dp(i,j) 的值，那么其中的最大值即为矩阵中只包含 1 的正方形的边长最大值，其平方即为最大正方形的面积。
 * 那么如何计算 dp 中的每个元素值呢？对于每个位置 (i,j)，检查在矩阵中该位置的值：
 * 如果该位置的值是 0，则 dp(i,j)=0，因为当前位置不可能在由 1 组成的正方形中；
 * 如果该位置的值是 1，则 dp(i,j) 的值由其上方、左方和左上方的三个相邻位置的 dp 值决定。
 * 具体而言，当前位置的元素值等于三个相邻位置的元素中的最小值加 1，状态转移方程如下：
 *
 * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
 *
 *
 * @author hzzhouhongfei
 * @version $$ MaximalSquare, 2023/9/22 00:25 hzzhouhongfei $$
 */
public class MaximalSquare
{
	public int maximalSquare(char[][] matrix)
	{
		int maxSide = 0;
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
		{
			return maxSide;
		}
		int rows = matrix.length, columns = matrix[0].length;
		int[][] dp = new int[rows][columns];

		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				if (matrix[i][j] == '1')
				{
					if (i == 0 || j == 0)
					{
						dp[i][j] = 1;
					}
					else
					{
						dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					}
					maxSide = Math.max(maxSide, dp[i][j]);
				}
			}
		}
		int maxSquare = maxSide * maxSide;
		return maxSquare;
	}
}
