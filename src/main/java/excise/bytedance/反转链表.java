package excise.bytedance;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 反转链表, 2023/10/4 20:02 hzzhouhongfei $$
 */
public class 反转链表
{
	public ListNode reverseList(ListNode head)
	{
		ListNode prev = null;
		ListNode current = head;

		while (current != null)
		{
			// 增加临时下个节点，指向当前节点的下一个
			ListNode nextTemp = current.next;
			// 当前节点的下一个节点指向前一个节点 pre
			current.next = prev;
			// pre节点指向当前节点
			prev = current;
			// 当前节点后移一个到临时下个节点
			current = nextTemp;
		}
		return prev;
	}

	public static void main(String[] args)
	{
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		ReverseLinkedList solution = new ReverseLinkedList();
		ListNode reversedHead = solution.reverseList(head);

		// 打印反转后的链表
		while (reversedHead != null)
		{
			System.out.print(reversedHead.val + " ");
			reversedHead = reversedHead.next;
		}
	}
}
