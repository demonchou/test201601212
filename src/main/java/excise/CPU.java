package excise;

/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年7月4日下午9:40:40
 */
public class CPU
{
	public static void main(String[] args)
	{
		int i=81;
		while((i--)>0)
		{
			if ((i/9%3) == (i%9%3))
			{
				continue;
			}
			System.out.println("A:" + (i/9+1) + "," + "B:"+ (i%9+1));
		}
		
	}
}
