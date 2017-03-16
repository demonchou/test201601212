package excise;
/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年7月31日下午8:46:00
 */
public class StaticTest
{
	static int x,y;

	static
	{
		int x = 5;
	}
	
	public static void main(String[] args)
	{
		x--;
		myMthod();
		System.out.println(x+y+ ++x);
	}
	public static void myMthod()
	{
		
		y = x++ + ++x;
	}
	

}
