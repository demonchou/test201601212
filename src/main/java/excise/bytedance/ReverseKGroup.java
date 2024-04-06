package excise.bytedance;

public class ReverseKGroup
{
	public ListNode reverseKGroup(ListNode head, int k)
	{
		if (head == null || k == 1)
		{
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = dummy;
		int count = 0;

		// 计算链表长度
		while (current.next != null)
		{
			current = current.next;
			count++;
		}

		while (count >= k)
		{
			current = dummy;
			ListNode prevTail = current;
			current = current.next;
			ListNode nextHead = current.next;

			for (int i = 1; i < k; i++)
			{
				ListNode temp = nextHead.next;
				nextHead.next = current;
				current = nextHead;
				nextHead = temp;
			}

			prevTail.next.next = nextHead;
			prevTail.next = current;

			prevTail = prevTail.next;
			count -= k;
		}

		return dummy.next;
	}

	public static void main(String[] args)
	{
		ListNode head = new ListNode(1);
		ListNode current = head;
		for (int i = 2; i <= 6; i++)
		{
			current.next = new ListNode(i);
			current = current.next;
		}

        ListNode plainHead = head;

        while (plainHead != null)
        {
            System.out.print(plainHead.val + " ");
            plainHead = plainHead.next;
        }

        System.out.println("");
        System.out.println("===========");

		ReverseKGroup solution = new ReverseKGroup();
		ListNode reversedHead = solution.reverseKGroup(head, 3);

		// 打印倒序后的链表
		while (reversedHead != null)
		{
			System.out.print(reversedHead.val + " ");
			reversedHead = reversedHead.next;
		}
	}
}