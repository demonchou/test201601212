package excise.bytedance;

/**
 * 2. 两数相加
 *
 * @author hzzhouhongfei
 * @version $$ 两数相加, 2023/10/4 20:12 hzzhouhongfei $$
 */
public class 两数相加
{
	public ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{

		ListNode pre = new ListNode(0);
		ListNode res = pre;

		int carry = 0;

		while (l1 != null || l2 != null)
		{
			int curRes = 0;

			curRes += carry;

			if (l1 != null)
			{
				curRes += l1.val;
			}
			if (l2 != null)
			{
				curRes += l2.val;
			}

			carry = curRes / 10;
			curRes = curRes % 10;

			res.next = new ListNode(curRes);
			res = res.next;

			if (l1 != null)
			{
				l1 = l1.next;
			}
			if (l2 != null)
			{
				l2 = l2.next;
			}
		}

		if (carry == 1)
		{
			res.next = new ListNode(carry);
		}

		return pre.next;
	}
}
