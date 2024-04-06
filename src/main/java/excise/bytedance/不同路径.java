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
public class 不同路径
{
	public int uniquePaths(int m, int n) {
		int[][] f = new int[m][n];
		// 边上的都是只有1条路
		for (int i = 0; i < m; ++i) {
			f[i][0] = 1;
		}
		// 边上的都是只有1条路
		for (int j = 0; j < n; ++j) {
			f[0][j] = 1;
		}
		// 从1开始
		for (int i = 1; i < m; ++i)
		{
			for (int j = 1; j < n; ++j)
			{
				// 左边或者上边过来
				f[i][j] = f[i - 1][j] + f[i][j - 1];
			}
		}
		return f[m - 1][n - 1];
	}

	public static void main(String[] args)
	{
		不同路径 solution = new 不同路径();
		System.out.println(solution.uniquePaths(5, 4));
	}
}
