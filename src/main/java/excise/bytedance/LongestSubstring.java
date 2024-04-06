package excise.bytedance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最长不重复子串
 * 使用滑动窗口的形式保存子串。并记录当前最大子串的长度
 * 使用了一个滑动窗口来维护一个不含重复字符的子串，并在遇到重复字符时移动窗口。
 * 最终，它返回最长子串的长度。在你的示例中，输入字符串 "bbbbb" 的最长不含重复字符的子串是 "b"，其长度为 1。
 * @author hzzhouhongfei
 * @version $$ LongestSubstring, 2023/9/7 11:43 hzzhouhongfei $$
 */
public class LongestSubstring
{
	public int lengthOfLongestSubstring(String s)
	{
		if (s == null || s.length() == 0)
		{
			return 0;
		}

		Map<Integer, Set<Character>> maxLenAndSubStringSetMap = new HashMap<>();
		Set<Character> charSet = new HashSet<>();
		int maxLen = 0;
		int left = 0;
		int right = 0;

		while (right < s.length())
		{
			// right 指向的下一个判断字符
			char currentChar = s.charAt(right);
			// 判断窗口中是否含有下一个字符
			// 没有，加入到窗口中，同时更新最长子串长度以及right指向下一个位置
			if (!charSet.contains(currentChar))
			{
				charSet.add(currentChar);
				// 更新最大子串长度
				maxLen = Math.max(maxLen, right - left + 1);
				maxLenAndSubStringSetMap.put(maxLen, charSet);
				right++;
			}
			// 有了，说明，当前窗口中的子串有部分重合，被破坏了。去掉该字符，同时，left指针右移。继续寻找下一个最长子串。
			else
			{
				charSet.remove(s.charAt(left));
				left++;
			}
		}

		System.out.println(maxLenAndSubStringSetMap);
		System.out.println(maxLenAndSubStringSetMap.get(maxLen));
		return maxLen;
	}

	public static void main(String[] args)
	{
		String s = "abcbacdabc";
		LongestSubstring solution = new LongestSubstring();
		int result = solution.lengthOfLongestSubstring(s);
		System.out.println(result); // 输出: 1
	}
}
