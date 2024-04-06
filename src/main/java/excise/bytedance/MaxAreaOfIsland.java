package excise.bytedance;

/**
 * 登陆
 * 登陆后，用零标记为已经登陆
 * 登陆后，向四面深入，如果遇到边界情况，返回0
 * @author hzzhouhongfei
 * @version $$ MaxAreaOfIsland, 2023/9/23 23:41 hzzhouhongfei $$
 */
public class MaxAreaOfIsland
{
	public int maxAreaOfIsland(int[][] grid)
	{
		int maxAre = 0;
		int row = grid.length;
		int col = grid[0].length;

		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				// 登陆
				if (grid[i][j] == 1)
				{
					//取出最大的面积
					maxAre = Math.max(maxAre, dfs(grid, i, j));
				}
			}
		}
		return maxAre;

	}

	public int dfs(int[][] grid, int i, int j)
	{
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
		{
			return 0;
		}

		// 标记已经登陆过
		grid[i][j] = 0;

		int sum = 1;

		sum += dfs(grid, i - 1, j);
		sum += dfs(grid, i + 1, j);
		sum += dfs(grid, i, j - 1);
		sum += dfs(grid, i, j + 1);
		return sum;
	}

	public static void main(String[] args)
	{
		int[][] grid = { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				{ 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };

		MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
		System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
	}
}
