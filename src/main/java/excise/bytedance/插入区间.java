package excise.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 * @author hzzhouhongfei
 * @version $$ 插入区间, 2023/10/8 20:28 hzzhouhongfei $$
 */
public class 插入区间
{
	public int[][] insert(int[][] intervals, int[] newInterval)
	{
		//           |_____newInterval_|
		// |___|  |______|  |___|   |____|  |_____|

		List<int[]> res = new ArrayList<>();
		int len = intervals.length;
		int i = 0;
		// 判断左边不重合
		while (i < len && intervals[i][1] < newInterval[0])
		{
			res.add(intervals[i]);
			i++;
		}

		// 判断重合
		while (i < len && intervals[i][0] <= newInterval[1])
		{
			newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
			newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
			i++;
		}

		res.add(newInterval);
		// 判断右边不重合
		while (i < len && intervals[i][0] > newInterval[1])
		{
			res.add(intervals[i]);
			i++;
		}

		return res.toArray(new int[0][]);

	}
}
