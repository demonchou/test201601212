package excise.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * @author hzzhouhongfei
 * @version $$ 组合总和, 2024/3/17 5:43 PM hzzhouhongfei $$
 */
public class 组合总和
{
	public List<List<Integer>> combinationSum(int[] candidates, int target)
	{
		int len = candidates.length;
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		dfs(candidates, 0, len, target, path, res);

		return res;
	}

	/**
	 * @param candidates 候选数组
	 * @param curIndex      搜索起点
	 * @param len        冗余变量，是 candidates 里的属性，可以不传
	 * @param target     每减去一个元素，目标值变小
	 * @param path       从根结点到叶子结点的路径，是一个栈
	 * @param res        结果集列表
	 */
	private void dfs(int[] candidates, int curIndex, int len, int target, List<Integer> path, List<List<Integer>> res)
	{
		// target 为负数和 0 的时候不再产生新的孩子结点
		if (target < 0)
		{
			return;
		}
		if (target == 0)
		{
			res.add(new ArrayList<>(path));
			return;
		}

		// 重点理解这里从 begin 开始搜索的语意
		for (int i = curIndex; i < len; i++)
		{
			path.add(candidates[i]);

			// TODO 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
			dfs(candidates, i, len, target - candidates[i], path, res);

			// 状态重置
			path.remove(path.size() - 1);
		}
	}

	public static void main(String[] args)
	{
		组合总和 solution = new 组合总和();
		int[] nums = new int[] { 2, 3, 6, 7 };
		List<List<Integer>> combinations = solution.combinationSum(nums, 7);

		for (List<Integer> combination : combinations)
		{
			System.out.println(combination);
		}
	}
}
