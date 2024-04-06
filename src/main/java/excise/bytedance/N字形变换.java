package excise.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * @author hzzhouhongfei
 * @version $$ N字形变换, 2023/10/8 19:51 hzzhouhongfei $$
 */
public class N字形变换
{
	public String convert(String s, int numRows)
	{
		if (null == s || s.length() == 0 || numRows < 2)
		{
			return s;
		}
		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < numRows; i++)
		{
			rows.add(new StringBuilder());
		}

		int flag = -1;
		int i = 0;

		for (Character c : s.toCharArray())
		{
			rows.get(i).append(c);
			if (i == 0 || i == numRows - 1)
			{
				flag = -flag;
			}
			i += flag;
		}

		StringBuilder ansStringBuilder = new StringBuilder();

		for (StringBuilder stringBuilder:rows)
		{
			ansStringBuilder.append(stringBuilder.toString());
		}

		return ansStringBuilder.toString();
	}

	public static void main(String[] args)
	{
		N字形变换 solution = new N字形变换();
		System.out.println(solution.convert("PAYPALISHIRING", 3));
	}
}
