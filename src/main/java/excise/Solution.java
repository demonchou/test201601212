package excise;

// Remove Duplicates from Sorted Array // 时间复杂度O(n)，空间复杂度O(1)
class Solution
{
	public static int removeDuplicates(int[] nums)
	{
		if (nums.length == 0)
        {
            return 0;
        }
		int index = 1;
		for (int i = 1; i < nums.length; i++)
		{
			if (nums[i] != nums[index - 1])
            {
                nums[index++] = nums[i];
            }
		}
		return index;
	}

    public static void main(String[] args)
    {
        int[] nums = new int[] { 1, 2, 2, 2, 2, 3 };
        System.out.println(removeDuplicates(nums));
    }
};