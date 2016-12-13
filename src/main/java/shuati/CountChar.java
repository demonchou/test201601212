package shuati;

import java.util.Scanner;

/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年8月8日下午10:19:13
 */
public class CountChar
{

	/**
	 * @Description 
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		String plainStr = scanner.next();
		String[] strings = plainStr.split(";");
		int count = 0;
		
		char[] chars = new char[strings[0].length()];
		for (int i = 0; i < strings[0].length(); i++)
		{
			chars[i] = strings[0].charAt(i);
		}
		for (char c : chars)
		{
			if (c == strings[1].charAt(0))
			{
				count++;
			}
		}
		scanner.close();
		System.out.println(count);
		

	}

}
