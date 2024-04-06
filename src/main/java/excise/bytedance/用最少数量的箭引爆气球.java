package excise.bytedance;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 用最少数量的箭引爆气球, 2023/10/8 21:56 hzzhouhongfei $$
 */
public class 用最少数量的箭引爆气球
{
	public int findMinArrowShots(int[][] points)
	{
		if (points.length == 0)
		{
			return 0;
		}

		Arrays.sort(points, (p1, p2) -> p1[1] < p2[1] ? -1 : 1);

		// 箭的位置，起始位置为第一个气球的右边界
		int pos = points[0][1];
		int ans = 1;
		for (int[] balloon : points)
		{
			// 如果当前气球的左边界超出了上次射出的箭的位置，说明需要重新发一箭，
			// 而且这一箭的位置在当前气球的最右边
			if (balloon[0] > pos)
			{
				pos = balloon[1];
				++ans;
			}
		}
		return ans;
	}

	public static void main(String[] args)
	{
		用最少数量的箭引爆气球 solution = new 用最少数量的箭引爆气球();
		int[][] points = new int[][] { {10,16},{2,8},{1,6},{7,12}};
		points = new int[][] { { -2147483646, -2147483645 }, { 2147483646, 2147483647 } };
		System.out.println(solution.findMinArrowShots(points));
	}
}
