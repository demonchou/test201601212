package excise.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hzzhouhongfei
 * @version $$ 字母异位词分组, 2023/10/9 14:51 hzzhouhongfei $$
 */
public class 字母异位词分组
{
	public List<List<String>> groupAnagrams(String[] strs)
	{
		Map<String, List<String>> ans = new HashMap<>();
		for (String str :strs)
		{
			char[] array = str.toCharArray();
			Arrays.sort(array);
			String key = new String(array);
			List<String> list = ans.getOrDefault(key, new ArrayList<>());
			list.add(str);
			ans.put(key, list);
		}

		return new ArrayList<>(ans.values());
	}
}
