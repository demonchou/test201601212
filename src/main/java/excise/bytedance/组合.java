package excise.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 * @author hzzhouhongfei
 * @version $$ 组合, 2023/10/11 00:10 hzzhouhongfei $$
 */
public class 组合
{
	public List<List<Integer>> combine(int n, int m)
	{
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		dfs(n, m, 1, path, result);
		return result;
	}

	private void dfs(int n, int m, int curIndex, List<Integer> path, List<List<Integer>> result)
	{
		// 递归终止条件是：path 的长度等于 k
		if (path.size() == m)
		{
			result.add(new ArrayList<>(path));
			return;
		}
		// 在新一轮数据中寻找
		for (int i = curIndex; i <= n; i++)
		{
			// 向路径变量里添加一个数
			path.add(i);
			// 新一轮数据起点要加一，之前的被用过了
			// TODO 注意i+1，不可重复
			dfs(n, m, i + 1, path, result);
			// 加满了，就返回，把最后一个数删掉，重新来
			path.remove(path.size() - 1);

		}
	}

	public static void main(String[] args)
	{
		组合 solution = new 组合();
		int n = 4;
		int m = 3;
		List<List<Integer>> combinations = solution.combine(n, m);

		for (List<Integer> combination : combinations)
		{
			System.out.println(combination);
		}
	}
}
