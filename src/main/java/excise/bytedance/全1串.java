package excise.bytedance;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 全1串, 2024/3/12 5:53 PM hzzhouhongfei $$
 */
public class 全1串
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[] str = new int[n];
		for (int i = 0; i < n; i++)
		{
			str[i] = scanner.nextInt();
		}
		if (str.length == 0)
		{
			System.out.println(0);
			return;
		}

		int num = 0;
		int max = 0;

		ArrayDeque<Integer> integers = new ArrayDeque<>();
		for (int i = 0; i < n; i++)
		{
			integers.offer(str[i]);
			if (str[i] == 0)
			{
				if (num < k)
				{
					num++;
				}
				else
				{
					while (integers.poll() != 0)
					{
					}
				}

			}
			max = Math.max(max, integers.size());

		}
		System.out.println(max);
	}
}
