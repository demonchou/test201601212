package shuati;
import java.util.Scanner;

public class test
{
	public static void main(String[] args)
	{

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		String s = scanner.next();
		for (int r = 0; r < s.length() - 1; r++)
		{
			for (int i = r + 1; i <= s.length(); i++)
			{
				if (i <= s.length())
				{
					String dd = s.substring(r, i);
					if (s.indexOf(dd) != -1)
					{
						String tmps = s.replaceAll(dd, "");
						int len = (s.length() - tmps.length()) / dd.length();
						if (len > 1)
						{// ֻ�д���һ�εĲż�¼
							System.out.println(dd + "�ظ����ֵĴ�����" + len);
						}

					}
				}
			}
		}
		scanner.close();
	}
}