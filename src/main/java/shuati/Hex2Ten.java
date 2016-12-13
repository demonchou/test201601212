package shuati;

import java.util.Scanner;

/**
 * @Description  16进制转10进制
 * @author hzzhouhongfei 
 * 2016年8月18日下午2:36:36
 */
public class Hex2Ten
{


	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
//		scanner.useDelimiter("\n");
		String plainStr = scanner.next();
		String[] strings = plainStr.split("x|X");
		Integer.parseInt("0x123f");
		String rString = toUnsignedString(Integer.parseInt(strings[1]));
		System.out.println(rString);
		
	}
	public static String toUnsignedString(int i) {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[--charPos] = digits[i & mask];
            i >>>= 4;
        } while (i != 0);

        return new String(buf, charPos, (32 - charPos));
    }
	
	final static char[] digits = {
	        '0' , '1' , '2' , '3' , '4' , '5' ,
	        '6' , '7' , '8' , '9' , 'a' , 'b' ,
	        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
	        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
	        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
	        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
	    };

}
