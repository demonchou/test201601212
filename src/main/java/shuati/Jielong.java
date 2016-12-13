package shuati;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


/**
 * @Description  是否符合成语接龙
 * @author hzzhouhongfei 
 * 2016年8月10日下午9:28:13
 */
public class Jielong
{

	/**
	 * @Description 是否符合成语接龙
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		String plainStr = scanner.next();
//		String[] strings = plainStr.split(";");
//		String[] words = strings[1].split("\\s");
		String[] words = plainStr.split("\\s");
		
		Set<Character> sets = new TreeSet<Character>();
		
		char[] ch = new char[words.length*2];
		int j = ch.length -1;
		while (j > 0)
		{
			for (int i = 0; i < words.length; i++)
			{
				ch[j] = words[i].charAt(0);
				ch[--j] = words[i].charAt(words[i].length() -1);
				j--;
			}
		}
		int chLenth = ch.length;
		for (int i = 0; i < ch.length; i++)
		{
			sets.add(ch[i]);
		}
		int currentLenth = sets.size();
		
		if (chLenth - currentLenth >= (words.length-1))
		{
			System.out.println("Yes!!!");
		}
		else 
		{
			System.out.println("No!!!");
		}
	}
}
