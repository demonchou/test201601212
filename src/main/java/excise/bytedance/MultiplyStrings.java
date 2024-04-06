package excise.bytedance;

/**
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 循环遍历 num1 和 num2 中的每一位数字，计算出每一位的乘积，并将结果放入正确的位置。然后，我们将结果数组转换为字符串，并排除前导零。
 * @author hzzhouhongfei
 * @version $$ MultiplyStrings, 2023/9/18 05:24 hzzhouhongfei $$
 */
public class MultiplyStrings
{
	public String multiply(String num1, String num2)
	{
		int m = num1.length();
		int n = num2.length();
		int[] result = new int[m + n];

		for (int i = m - 1; i >= 0; i--)
		{
			for (int j = n - 1; j >= 0; j--)
			{
				int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				// 当前乘积+进位
				int sum = product + result[i + j + 1];
				// 当前位的结果
				result[i + j + 1] = sum % 10;
				// 进位存入高一位的结果中
				result[i + j] += sum / 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int digit : result)
		{
			if (!(sb.length() == 0 && digit == 0))
			{
				sb.append(digit);
			}
		}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	public static void main(String[] args)
	{
		MultiplyStrings solution = new MultiplyStrings();
		String num1 = "123";
		String num2 = "456";
		String result = solution.multiply(num1, num2);
		System.out.println(result); // 输出: "56088"
	}
}

