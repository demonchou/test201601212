package excise.bytedance;

/**
 * // 可以使用滑动窗口的方法。
 * 首先，我们需要统计 s1 中每个字符的出现次数，然后在 s2 中使用一个固定大小的窗口来统计相同大小的字符子串的字符出现次数，
 * 如果窗口内的字符出现次数与 s1 相同，就表示找到了一个 s1 的排列。
 * @author hzzhouhongfei
 * @version $$ CheckInclusion, 2023/9/7 15:28 hzzhouhongfei $$
 */
public class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        // 通过统计数量，表明字符出现的个数，即，参加了统计
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // 先统计s1的每个字符出现个数，s2 的前 s1.length() 个字符中统计字符出现次数
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        //然后使用一个窗口在 s2 的后续字符中滑动，不断更新 s2Count。
        // 如果窗口内的字符出现次数与 s1 相同，就返回 true。
        for (int i = s1.length(); i < s2.length(); i++) {
            if (matches(s1Count, s2Count)) {
                return true;
            }

            // 如果到这步，说明在这区间不存在，最左边的这个字符减去1，表明最左边的这个字符不在统计范围了。
            s2Count[s2.charAt(i - s1.length()) - 'a']--;
            // 最右边的这个字符统计加1，说明这个字符加入统计了
            s2Count[s2.charAt(i) - 'a']++;
        }

        return matches(s1Count, s2Count);
    }

    private boolean matches(int[] s1Count, int[] s2Count) {
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] != s2Count[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckInclusion solution = new CheckInclusion();
        String s1 = "ab";
        String s2 = "eidbaooo";
        boolean result = solution.checkInclusion(s1, s2);
        // 输出: true
        System.out.println(result);
    }
}
