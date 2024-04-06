package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 删除排序链表中的重复元素2, 2023/10/9 17:01 hzzhouhongfei $$
 */
public class 删除排序链表中的重复元素2
{
	public ListNode deleteDuplicates(ListNode head)
	{
		ListNode dumpy = new ListNode(-1);
		dumpy.next = head;
		ListNode cur = dumpy;

		while (cur.next != null && cur.next.next != null)
		{
			if (cur.next.val == cur.next.next.val)
			{
				int x = cur.next.val;
				while (cur.next != null && cur.next.val == x)
				{
					cur.next = cur.next.next;
				}
			}
			else
			{
				cur = cur.next;
			}
		}

		return dumpy.next;
	}


	public static void main(String[] args)
	{
		删除排序链表中的重复元素2 solution = new 删除排序链表中的重复元素2();
		ListNode head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(2);
		solution.deleteDuplicates(head);
	}
}
