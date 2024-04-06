package excise.bytedance;

import java.util.Stack;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 最小栈, 2023/10/5 23:37 hzzhouhongfei $$
 */
public class 最小栈
{
	private Stack<Node> stack;

	public 最小栈()
	{
		stack = new Stack<>();
	}

	public void push(int x)
	{
		if (stack.isEmpty())
		{
			stack.push(new Node(x, x));
		}
		else
		{
			stack.push(new Node(x, Math.min(stack.peek().min, x)));
		}
	}

	public void pop()
	{
		stack.pop();
	}

	public int top()
	{
		return stack.peek().val;
	}

	public int getMin()
	{
		return stack.peek().min;
	}

	class Node
	{
		int val;
		int min;

		public Node(int val, int min)
		{
			this.val = val;
			this.min = min;
		}
	}

}
