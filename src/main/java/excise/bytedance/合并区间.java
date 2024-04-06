package excise.bytedance;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * @author hzzhouhongfei
 * @version $$ 合并区间, 2023/9/28 20:54 hzzhouhongfei $$
 */
public class 合并区间
{
	public int[][] merge(int[][] intervals)
	{

		int n = intervals.length;

		// 先按照区间起始位置排序
		Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

		int[][] res = new int[n][2];

		int resIndex = -1;

		// 遍历
		for (int i = 0; i < n; i++)
		{

			if (resIndex == -1 || res[resIndex][1] < intervals[i][0])
			{
				res[++resIndex] = intervals[i];
			}
			else
			{
				res[resIndex][1] = Math.max(intervals[i][1], res[resIndex][1]);
			}

		}

		return Arrays.copyOf(res, resIndex + 1);

	}

	public static void main(String[] args)
	{

	}
}
