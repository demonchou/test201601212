package excise.bytedance;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 71. 简化路径
 *
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * 请注意，返回的 规范路径 必须遵循下述格式：
 *
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 *
 * 我们首先将给定的字符串 path 根据 / 分割成一个由若干字符串组成的列表，记为 names。根据题目中规定的「规范路径的下述格式」，names 中包含的字符串只能为以下几种：
 *
 * 空字符串。例如当出现多个连续的 /，就会分割出空字符串；
 *
 * 一个点 .；
 *
 * 两个点 ..；
 *
 * 只包含英文字母、数字或 _ 的目录名。
 *
 * 对于「空字符串」以及「一个点」，我们实际上无需对它们进行处理，因为「空字符串」没有任何含义，而「一个点」表示当前目录本身，我们无需切换目录。
 *
 * 对于「两个点」或者「目录名」，我们则可以用一个栈来维护路径中的每一个目录名。当我们遇到「两个点」时，需要将目录切换到上一级，因此只要栈不为空，我们就弹出栈顶的目录。当我们遇到「目录名」时，就把它放入栈。
 *
 * 这样一来，我们只需要遍历 names 中的每个字符串并进行上述操作即可。在所有的操作完成后，我们将从栈底到栈顶的字符串用 / 进行连接，再在最前面加上 /表示根目录，就可以得到简化后的规范路径。
 *
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/simplify-path/solutions/1193258/jian-hua-lu-jing-by-leetcode-solution-aucq/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @author hzzhouhongfei
 * @version $$ SimplifyPath, 2023/9/22 16:45 hzzhouhongfei $$
 */
public class SimplifyPath
{
	public String simplifyPath(String path)
	{
		if (null == path || path.length() == 0)
		{
			return path;
		}

		String[] dirs = path.trim().split("/");

		Deque<String> stack = new ArrayDeque<>();

		for (String dir : dirs)
		{
			if ("..".equals(dir))
			{
				if (!stack.isEmpty())
				{
					stack.pollLast();
				}
			}
			else if (!".".equals(dir) && dir.length() > 0)
			{
				stack.offerFirst(dir);
			}
		}

		StringBuffer ans = new StringBuffer();
		if (stack.isEmpty())
		{
			ans.append('/');
		}
		else
		{
			while (!stack.isEmpty())
			{
				ans.append('/');
				ans.append(stack.pollFirst());
			}
		}
		return ans.toString();
	}

	public static void main(String[] args)
	{
		SimplifyPath simplifyPath = new SimplifyPath();
		String path = "/a/./b/../../c/";
		String ans = "/c";
		System.out.println(ans.equals(simplifyPath.simplifyPath(path)));
		System.out.println(simplifyPath.simplifyPath(path));
	}
}
