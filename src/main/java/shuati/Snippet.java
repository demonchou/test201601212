package shuati;

import java.util.Scanner;

/**
 * @Description
 * @author hzzhouhongfei 2016年8月9日下午9:22:22
 */
public class Snippet
{
	public static boolean check(char[] words)
	{
		int num = words.length;
		for (int start = 0; start < num - 1; start++)
		{
			int start2 = start + 1;
			for (; start2 < num - 1; start2++)
			{
				if (words[start2] == words[start])
				{
					// find start2
					int st1 = start;
					int st2 = start2;
					for (int i = st1 + 1; i < start2; i++)
					{
						if (words[i] == words[++st2])
						{
							return false;
						}
						if (st2 >= num - 1)
							break;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext())
		{
			String line = scan.nextLine().replace("\n", "");
			char[] characters = line.toCharArray();
			int num = characters.length;

			boolean BD = false;
			char last = '\0';

			for (int i = 0; i < num; i++)
			{
				if (characters[i] < 'A' || characters[i] > 'Z')
				{
					BD = true;
					break;
				}
				if (last == characters[i])
				{
					BD = true;
					break;
				} else
				{
					last = characters[i];
				}
			}
			if (BD)
			{
				System.out.println("Dislikes");
			} else
			{
				if (check(characters))
					System.out.println("Likes");
				else
					System.out.println("Dislikes");
			}

		}
	}

}
