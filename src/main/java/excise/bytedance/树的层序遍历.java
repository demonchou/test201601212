package excise.bytedance;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 树的层序遍历, 2023/10/5 21:06 hzzhouhongfei $$
 */
public class 树的层序遍历
{
	public List<List<Integer>> levelOrder(TreeNode root)
	{
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> queue = new ArrayDeque<>();

		if (root != null)
		{
			queue.add(root);
		}
		while (!queue.isEmpty())
		{
			int size = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0;i<size;i++)
			{
				TreeNode treeNode = queue.poll();

				level.add(treeNode.val);
				if (treeNode.left != null)
				{
					queue.add(treeNode.left);
				}

				if (treeNode.right != null)
				{
					queue.add(treeNode.right);
				}

			}
			res.add(level);
		}
		return res;

	}

	public class TreeNode
	{
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode()
		{
		}

		TreeNode(int val)
		{
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right)
		{
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
