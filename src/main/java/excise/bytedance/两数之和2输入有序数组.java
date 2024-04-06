package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 两数之和2输入有序数组, 2023/10/9 14:32 hzzhouhongfei $$
 */
public class 两数之和2输入有序数组
{
	public int[] twoSum(int[] numbers, int target)
	{
		int i = 0;
		int j = numbers.length - 1;
		while (i < j)
		{
			int sum = numbers[i] + numbers[j];
			if (sum > target)
			{
				j--;
			}
			else if (sum < target)
			{
				i++;
			}
			else
			{
				return new int[] { i + 1, j + 1 };
			}
		}
		return new int[0];
	}

	public static void main(String[] args)
	{
		两数之和2输入有序数组 solution = new 两数之和2输入有序数组();
		int[] numbers = { 2, 7, 11, 15 };
		int target = 9;
		int[] ints = solution.twoSum(numbers, target);

		System.out.println(ints[0] + "," + ints[1]);
	}
}
