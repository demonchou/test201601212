package excise.bytedance;

import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ReverseWords, 2023/9/22 01:18 hzzhouhongfei $$
 */
public class ReverseWords
{
	public String reverseWords(String s)
	{
		if (null == s || s.length() == 0)
		{
			return s;
		}

		String[] strs =  s.split(" ");

		Stack<String> words = new Stack<>();

		Collections.addAll(words, strs);

		StringBuilder result = new StringBuilder();

		do
		{
			String temp = words.pop().trim();
			if (temp.length() > 0)
			{
				result.append(temp);
				result.append(" ");
			}

		}
		while (!words.isEmpty());

		return result.toString().trim();
	}

	public static void main(String[] args)
	{
		ReverseWords reverseWords = new ReverseWords();
		String s = " the sky   is blue  ";
		String s1 = "blue is sky the";
		System.out.println(s1.equals(reverseWords.reverseWords(s)));
	}

}
