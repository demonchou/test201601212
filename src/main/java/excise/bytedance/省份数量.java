package excise.bytedance;

/**
 * 547. 省份数量
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 * @author hzzhouhongfei
 * @version $$ 省份数量, 2023/9/28 18:34 hzzhouhongfei $$
 */
public class 省份数量
{
	public int findCircleNum(int[][] isConnected)
	{
		int n = isConnected.length;
		boolean[] visited = new boolean[n];
		int provinces = 0;
		for (int i = 0; i < n; i++)
		{
			if (!visited[i])
			{
				dfs(isConnected, visited, n, i);
				provinces++;
			}
		}
		return provinces;
	}

	public void dfs(int[][] isConnected, boolean[] visited, int n, int i)
	{
		for (int j = 0; j < n; j++)
		{
			if (isConnected[i][j] == 1 && !visited[j])
			{
				visited[j] = true;
				dfs(isConnected, visited, n, j);
			}
		}
	}
}
