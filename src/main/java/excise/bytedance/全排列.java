package excise.bytedance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 全排列, 2023/10/11 00:28 hzzhouhongfei $$
 */
public class 全排列
{
	public List<List<Integer>> permute(int[] nums)
	{
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		List<Integer> path = new ArrayList<Integer>();
		for (int num : nums)
		{
			path.add(num);
		}

		int n = nums.length;
		dfs(n, 0, path, res);
		return res;
	}

	public void dfs(int n, int curIndex, List<Integer> path, List<List<Integer>> res)
	{
		// 所有数都填完了
		if (curIndex == n)
		{
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = curIndex; i < n; i++)
		{
			// 动态维护数组
			Collections.swap(path, curIndex, i);
			// 继续递归填下一个数
			dfs(n, curIndex + 1, path, res);
			// 撤销操作
			Collections.swap(path, curIndex, i);
		}
	}

	public static void main(String[] args)
	{
		全排列 solution = new 全排列();
		int[] nums = { 2, 3, 5 };
		System.out.println(solution.permute(nums));
	}

}
