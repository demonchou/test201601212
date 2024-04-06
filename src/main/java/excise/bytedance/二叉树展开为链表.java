package excise.bytedance;

import java.util.ArrayList;
import java.util.List;

import excise.bytedance.common.TreeNode;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 二叉树展开为链表, 2023/10/10 15:41 hzzhouhongfei $$
 */
public class 二叉树展开为链表
{
	public void flatten(TreeNode root)
	{
		List<TreeNode> list = new ArrayList<TreeNode>();
		// 前序遍历，保存结果
		preorderTraversal(root, list);
		int size = list.size();

		for (int i = 1; i < size; i++)
		{
			TreeNode prev = list.get(i - 1), curr = list.get(i);
			prev.left = null;
			prev.right = curr;
		}
	}

	public void preorderTraversal(TreeNode root, List<TreeNode> list)
	{
		if (root != null)
		{
			list.add(root);
			preorderTraversal(root.left, list);
			preorderTraversal(root.right, list);
		}
	}
}
