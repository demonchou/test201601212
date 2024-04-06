package excise.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 递归函数 dfs(segId,segStart) 表示我们正在从 s[segStart]的位置开始，搜索 IP 地址中的第 segId 段，其中 segId∈{0,1,2,3} segId∈{0,1,2,3}。由于 IP 地址的每一段必须是 [0,255] 中的整数，因此我们从 segStart 开始，从小到大依次枚举当前这一段 IP 地址的结束位置 segEnd。如果满足要求，就递归地进行下一段搜索，调用递归函数 dfs(segId+1,segEnd+1)。
 *
 * 特别地，由于 IP 地址的每一段不能有前导零，因此如果 s[segStart] 等于字符 0，那么 IP 地址的第 segId 段只能为 0，需要作为特殊情况进行考虑。
 *
 * 在搜索的过程中，如果我们已经得到了全部的 4 段 IP 地址（即 segId=4，并且遍历完了整个字符串（即 segStart=∣s∣，其中 ∣s∣ 表示字符串 s 的长度），那么就复原出了一种满足题目要求的 IP 地址，我们将其加入答案。在其它的时刻，如果提前遍历完了整个字符串，那么我们需要结束搜索，回溯到上一步。
 *
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/restore-ip-addresses/solutions/366426/fu-yuan-ipdi-zhi-by-leetcode-solution/
 *
 * @author hzzhouhongfei
 * @version $$ RestoreIpAddresses, 2023/9/22 22:10 hzzhouhongfei $$
 */
public class RestoreIpAddresses
{
	static final int SEG_COUNT = 4;

	// 所有有效段IP，即最终结果
	List<String> ans = new ArrayList<>();

	// 分4个段
	int[] segments = new int[SEG_COUNT];

	public List<String> restoreIpAddresses(String s)
	{
		dfs(s, 0, 0);
		return ans;
	}

	public void dfs(String s, int segId, int segStart)
	{
		// 目标
		// 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
		if (segId == SEG_COUNT)
		{
			if (segStart == s.length())
			{
				StringBuffer ipAddr = new StringBuffer();
				for (int i = 0; i < SEG_COUNT; ++i)
				{
					ipAddr.append(segments[i]);
					if (i != SEG_COUNT - 1)
					{
						ipAddr.append('.');
					}
				}
				ans.add(ipAddr.toString());
			}
			// 已经分了4组了，但是字符串没遍历完，回溯
			return;
		}

		// 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
		if (segStart == s.length())
		{
			return;
		}

		// 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
		if (s.charAt(segStart) == '0')
		{
			segments[segId] = 0;
			dfs(s, segId + 1, segStart + 1);
			return;
		}

		// 一般情况，枚举每一种可能性并递归
		int addr = 0;
		for (int segEnd = segStart; segEnd < s.length(); ++segEnd)
		{
			addr = addr * 10 + (s.charAt(segEnd) - '0');
			if (addr > 0 && addr <= 0xFF)
			{
				segments[segId] = addr;
				dfs(s, segId + 1, segEnd + 1);
			}
			else
			{
				break;
			}
		}
	}

	public static void main(String[] args)
	{
		RestoreIpAddresses solution = new RestoreIpAddresses();
		String s = "101123";
//		String s = "0000";
		List<String> result = solution.restoreIpAddresses(s);
		System.out.println(result);

		int COUNT_BITS = Integer.SIZE - 3;
		int CAPACITY   = (COUNT_BITS);
		// 536870912
		// 29
		System.out.println(CAPACITY);
	}

}
