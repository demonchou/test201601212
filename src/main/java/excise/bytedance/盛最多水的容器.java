package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 盛最多水的容器, 2023/10/8 20:09 hzzhouhongfei $$
 */
public class 盛最多水的容器
{
	public int maxArea(int[] height) {

		int ans = 0;
		int n = height.length;
		int i = 0, j = n - 1;
		while (i < j)
		{
			ans = Math.max(ans, height[i] < height[j] ? (i - j) * height[i++] : (i - j) * height[j--]);
		}


		return ans;
	}
}
