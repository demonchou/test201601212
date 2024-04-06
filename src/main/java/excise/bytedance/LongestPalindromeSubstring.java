package excise.bytedance;

/**
 * 最长回文子串
 * 使用中心扩展法来查找最长回文子串。它遍历字符串中的每个字符，并以每个字符为中心，分别向左右扩展，
 * 找到以当前字符为中心的最长回文子串的长度。然后，它将所有的回文子串长度进行比较，
 * 最终找到最长的回文子串的起始和结束位置，然后返回该子串。
 * @author hzzhouhongfei
 * @version $$ LongestPalindromeSubstring, 2023/9/7 13:32 hzzhouhongfei $$
 */
public class LongestPalindromeSubstring
{
	public String longestPalindrome(String s)
	{
		if (s == null || s.length() < 1)
		{
			return "";
		}

		int start = 0;
		int end = 0;

		for (int i = 0; i < s.length(); i++)
		{
			// 以当前字符为中心扩展
			int len1 = expandAroundCenter(s, i, i);
			// 以当前字符和下一个字符之间为中心扩展
			int len2 = expandAroundCenter(s, i, i + 1);
			int maxLen = Math.max(len1, len2);

			if (maxLen > end - start)
			{
				start = i - (maxLen - 1) / 2;
				end = i + maxLen / 2;
			}
		}

		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right)
	{
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
		{
			left--;
			right++;
		}
		return right - left - 1;
	}

	public static void main(String[] args)
	{
		String s = "dabbab";
		LongestPalindromeSubstring solution = new LongestPalindromeSubstring();
		String result = solution.longestPalindrome(s);
		// 输出: "abcba"
		System.out.println(result);
	}
}
