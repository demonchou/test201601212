package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 接雨水, 2023/9/28 23:21 hzzhouhongfei $$
 */
public class 接雨水
{
	public int trap(int[] height)
	{
		int n = height.length;

		if (n == 0)
		{
			return 0;
		}

		// 左侧的最高列表
		int[] lMax = new int[n];
		lMax[0] = height[0];

		for (int i = 1; i < n; i++)
		{
			lMax[i] = Math.max(lMax[i - 1], height[i]);
		}


		// 右侧的最高列表
		int[] rMax = new int[n];
		rMax[n - 1] = height[n - 1];

		for (int j = n - 2; j >= 0; j--)
		{
			rMax[j] = Math.max(rMax[j + 1], height[j]);
		}

		int ans = 0;
		// 取得每个数的左右的最高中的最小值，减去自身的高度
		for (int i = 0; i < n; i++)
		{
			ans += Math.min(lMax[i], rMax[i]) - height[i];
		}

		return ans;
	}
}
