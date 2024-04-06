package excise.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * @author hzzhouhongfei
 * @version $$ 电话号码的字母组合, 2023/10/11 01:02 hzzhouhongfei $$
 */
public class 电话号码的字母组合
{
	Map<Character, String> phoneMap = new HashMap<Character, String>()
	{{
		put('2', "abc");
		put('3', "def");
		put('4', "ghi");
		put('5', "jkl");
		put('6', "mno");
		put('7', "pqrs");
		put('8', "tuv");
		put('9', "wxyz");
	}};

	public List<String> letterCombinations(String digits)
	{
		List<String> results = new ArrayList<>();
		if (digits.length() == 0)
		{
			return results;
		}

		dfs(digits, 0, new StringBuffer(), results);
		return results;
	}

	public void dfs(String digits, int curIndex, StringBuffer path, List<String> results)
	{
		if (curIndex == digits.length())
		{
			results.add(path.toString());
			return;
		}

		char digit = digits.charAt(curIndex);

		String letters = phoneMap.get(digit);
		int lettersCount = letters.length();

		for (int i = 0; i < lettersCount; i++)
		{
			path.append(letters.charAt(i));
			// 下一个字母来自下一个按键
			dfs(digits, curIndex + 1, path, results);
			path.deleteCharAt(curIndex);
		}
	}

	public static void main(String[] args)
	{
		电话号码的字母组合 solution = new 电话号码的字母组合();
		System.out.println(solution.letterCombinations("24"));
	}
}