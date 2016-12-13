package shuati;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


import excise.Buffer;

/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年8月11日下午9:56:18
 */
public class MinminRand
{

	/**
	 * @Description 
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String plainStr = scanner.next();
		scanner.useDelimiter("\n");
		String[] strings = plainStr.split("\\s");
		Set<Integer> set = new TreeSet<Integer>();
		for (String string : strings)
		{
			set.add(Integer.valueOf(string));
		}
		int[] aa = new int[set.size()];
		int j = aa.length-1;
		Iterator<Integer> iterator = set.iterator();
		if (iterator.hasNext())
		{
			aa[j--] = iterator.next();
		}
		for (int i = 0; i < aa.length; i++)
		{
			for (int k = i+1; k < aa.length; k++)
			{
				if(aa[i] > aa [k])
				{
					aa[i] = aa[k];
				}
			}
		}
		for (int i = 0; i < aa.length; i++)
		{
			System.out.println(aa[i]);
		}
		
		
		

	}

}
