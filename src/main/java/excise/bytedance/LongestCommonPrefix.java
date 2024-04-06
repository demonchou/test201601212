package excise.bytedance;

/**
 * 我们首先将第一个字符串作为基准前缀（prefix），然后遍历字符串数组中的每个字符串，
 * 逐一比较它们与基准前缀的公共前缀，
 * 如果不匹配，就将基准前缀的最后一个字符去掉，然后再次比较。
 * 重复这个过程，直到找到最长的公共前缀或者没有公共前缀时返回空字符串。
 * @author hzzhouhongfei
 * @version $$ LongestCommonPrefix, 2023/9/7 15:28 hzzhouhongfei $$
 */
public class LongestCommonPrefix
{
	public String longestCommonPrefix(String[] strs)
	{
		if (strs == null || strs.length == 0)
		{
			return "";
		}

		// 以第一个字符串为基准
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
		{
			while (strs[i].indexOf(prefix) != 0)
			{
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
				{
					return "";
				}
			}
		}

		return prefix;
	}

	public static void main(String[] args)
	{
		String[] strs = { "flower", "flow", "flight" };
		LongestCommonPrefix solution = new LongestCommonPrefix();
		String result = solution.longestCommonPrefix(strs);
		// 输出: "fl"
		System.out.println(result);
	}
}
