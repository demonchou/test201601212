package shuati;

import java.util.Scanner;

/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年8月8日下午9:54:20
 */
public class LastLenth
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
		String[] plainStrs = plainStr.split("\\s");
		String latStr = plainStrs[plainStrs.length - 1];
		int lenth = latStr.length();
		System.out.println(lenth);

	}

}

