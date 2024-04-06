package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ Pow, 2023/10/10 20:38 hzzhouhongfei $$
 */
public class Pow
{
	public double myPow(double x, int n)
	{
		long N = n;
		return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
	}

	public double quickMul(double x, long N)
	{
		if (N == 0)
		{
			return 1.0;
		}
		double y = quickMul(x, N / 2);
		// 前面的结果的平方或者x乘以前面的结果
		return N % 2 == 0 ? y * y : y * y * x;
	}

	public static void main(String[] args)
	{
		Pow pow = new Pow();
		pow.myPow(3, 4);
	}
}
