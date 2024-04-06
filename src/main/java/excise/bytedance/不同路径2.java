package excise.bytedance;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 * @author hzzhouhongfei
 * @version $$ 不同路径, 2023/10/8 16:22 hzzhouhongfei $$
 */
public class 不同路径2
{
	public int uniquePathsWithObstacles(int[][] obstacleGrid)
	{
		if (obstacleGrid == null || obstacleGrid.length == 0) {
			return 0;
		}

		// 定义 dp 数组并初始化第 1 行和第 1 列。
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
			dp[i][0] = 1;
		}
		for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
			dp[0][j] = 1;
		}

		// 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 0) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}
		return dp[m - 1][n - 1];
	}

	public static void main(String[] args)
	{
		不同路径2 solution = new 不同路径2();
		int[][] obstacleGrid = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } };
		System.out.println(solution.uniquePathsWithObstacles(obstacleGrid));
	}
}
