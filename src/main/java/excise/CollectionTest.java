package excise;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年9月1日上午10:44:35
 */
//1.获取老缓存
//2.刷新缓存
//3.获取新缓存、并拼装成功信息【成功信息：影响数据条数，明细【商户id】】
public class CollectionTest
{

	/**
	 * @Description 
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		Map<String, PlatformSignVo> newMap = buildNewMap();
		long lSysTime2 = System.currentTimeMillis();
		System.out.println("Time long : " + lSysTime2);
		Date edDate = getLastingTime(newMap);
		System.out.println(edDate);
		
		
		Date date = newMap.get("1").getUpdateTime() == null
				? new Date(946656000) : newMap.get("1").getUpdateTime();
		Date date2 = newMap.get("2").getUpdateTime() == null
				? new Date(946656000) : newMap.get("2").getUpdateTime();
		System.out.println("date: " + date);
		System.out.println("date2: " + date2);
		System.out.println("更新时间是否相等： " + DateUtils.isSameInstant(date, date2));
		System.out.println("更新时间是否同一天： " + DateUtils.isSameDay(date, date2));

		Set<String> set1 = new TreeSet<>(Arrays.asList("001","002","004","003"));
		Set<String> set2 = new TreeSet<>(Arrays.asList("001","002","009","008"));
		Set<String> resultSet = new TreeSet<>();
		resultSet.addAll(set1);
		resultSet.retainAll(set2);
		System.out.println("交集：" + resultSet);
		resultSet.clear();
		resultSet.addAll(set1);
		resultSet.addAll(set2);
		System.out.println("并集：" + resultSet);
		resultSet.clear();
		resultSet.addAll(set1);
		resultSet.removeAll(set2);
		System.out.println("差集：" + resultSet);
	}

	/**
	 * @Description 
	 * @return
	 * @return Map<String,PlatformSignVo>
	 */
	private static Map<String, PlatformSignVo> buildOldMap()
	{
		Map<String, PlatformSignVo > oldMap = new HashMap<>();
		oldMap.put("1", new PlatformSignVo("123456", "Rsa", Timestamp.valueOf("2016-02-16 00:00:00"), Timestamp.valueOf("2016-02-16 00:00:00"), "DEFAULT"));
		oldMap.put("2", new PlatformSignVo("123457", "Rsa", Timestamp.valueOf("2016-02-17 00:00:00"), Timestamp.valueOf("2016-02-17 00:00:00"), "DEFAULT"));
		return oldMap;
	}
	private static Map<String, PlatformSignVo> buildNewMap()
	{
		Map<String, PlatformSignVo > newMap = new HashMap<>();
		newMap.put("1", new PlatformSignVo("123456", "Rsa", Timestamp.valueOf("2016-02-16 00:00:00"),
				Timestamp.valueOf("2016-02-24 10:10:23"), "DEFAULT"));
		newMap.put("2", new PlatformSignVo("123457", "Rsa", Timestamp.valueOf("2016-02-17 00:00:00"), 
				Timestamp.valueOf("2016-02-24 00:10:22"), "DEFAULT"));
		newMap.put("3", new PlatformSignVo("123458", "Rsa", Timestamp.valueOf("2016-02-20 00:00:00"),
				Timestamp.valueOf("2016-02-25 00:00:02"), "DEFAULT"));
		return newMap;
	}

	/**
	 * @Description 
	 * @param oldMap
	 * @return void
	 */
	private static void showMaps(Map<String, PlatformSignVo> oldMap)
	{
		for (Map.Entry<String, PlatformSignVo > entry : oldMap.entrySet())
		{
			if(entry.getValue() == null)
			{
				System.out.println(entry.getKey() + " 的值是空！");
			}
			System.out.println(entry.getKey() + "|" + entry.getValue());
		}
	}
	
	/**
	 * Description 获取缓存Map中的最新的更新时间
	 * @param map 缓存Map
	 * @return Date 返回最新的更新时间
	 */
	public static Date getLastingTime(Map<String, PlatformSignVo> map)
	{
		Date date = new Date(946656000);// 比对的时间：2000/1/1 00:00:00
		for (Map.Entry<String, PlatformSignVo> entry : map.entrySet())
		{
			Date tempDate = entry.getValue().getUpdateTime();
			if (date.before(tempDate))
			{
				date = tempDate;
			}
		}
		return date;
	}
	
	/**
	 * Description 发挥刷新的结果
	 * @param oldMap 刷新之前的缓存Map
	 * @param newMap 刷新之后的缓存Map
	 * @return String 结果
	 */
	public String refreshResult2(Map<String, PlatformSignVo> oldMap, Map<String, PlatformSignVo> newMap)
	{
		
		StringBuffer sbResult = new StringBuffer();
		StringBuffer platforIdBuf = new StringBuffer();
		int count = 0;
		Date oldDate = getLastingTime(oldMap);
		Date newDate = getLastingTime(newMap);
		
		if (newMap.size() > oldMap.size())
		{
			count = newMap.size() - oldMap.size();
			sbResult = sbResult.append(count + "条数据添加成功！");
		}
		else 
		{
			if (oldDate.before(newDate))
			{
				
			}
		}
		return sbResult.toString();
	}
	
	/**
	 * Description 获取新增或者更新的PlatformId;
	 * @return String 更新的具体信息
	 */
	public String refreshResult(Map<String, PlatformSignVo> oldMap, Map<String, PlatformSignVo> newMap)
	{
		StringBuffer resultBuf = new StringBuffer();
		boolean flag =false;
		int count = 0;
		//1.新增数据刷新结果
		if (oldMap.size() < newMap.size())
		{
			for (Map.Entry<String, PlatformSignVo> newEntry : newMap.entrySet())
			{
				for (Map.Entry<String, PlatformSignVo> oldEntry : oldMap.entrySet())
				{
					if (oldEntry.getKey().equals(newEntry.getValue()))
					{
						flag = true;
					}
				}
				if (!flag)
				{
					resultBuf.append(newEntry.getKey()).append(" | ");
					count ++;
				}
			}
			resultBuf.append("共" + count + "条数据添加成功！");
		}
		//2.删除数据刷新结果
		if (oldMap.size() > newMap.size())
		{
			for (Map.Entry<String, PlatformSignVo> oldEntry : oldMap.entrySet())
			{
				for (Map.Entry<String, PlatformSignVo> newEntry : newMap.entrySet())
				{
					if (oldEntry.getKey().equals(newEntry.getKey()))
					{
						flag = true;
					}
				}
				if (!flag)
				{
					resultBuf.append(oldEntry.getKey()).append(" | ");
					count ++;
				}
			}
			resultBuf.append("共" + count + "条数据删除成功！");
		}
		//3.原有数据的更新结果
		else
		{
			for (Map.Entry<String, PlatformSignVo> oldEntry : oldMap.entrySet())
			{
				for (Map.Entry<String, PlatformSignVo> newEntry : newMap.entrySet())
				{
					if (oldEntry.getKey().equals(newEntry.getKey()))
					{
						Date oldDate = oldEntry.getValue().getUpdateTime();
						Date newDate = newEntry.getValue().getUpdateTime();
						if (!DateUtils.isSameInstant(oldDate, newDate))
						{
							resultBuf.append(oldEntry.getKey()).append(" | ");
							count ++;
						}
					}
				}
			}
			if (count == 0 )
			{
				resultBuf.append("刷新成功！变更数据条数为：0 ");
			}
			else 
			{
				resultBuf.append("共" + count + "条数据更新成功！");
			}
		}
		return resultBuf.toString();
	}

}
