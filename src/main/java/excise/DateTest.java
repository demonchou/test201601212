package excise;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONArray;

import excise.weatherInfoSpider.WeatherInfo;

/**
 * Description:
 * Created by hzzhouhongfei.
 * 2017/6/11 下午3:03
 */
public class DateTest
{
	public static void main(String[] args)
	{
		Date currentTime = new Date();
		List<Dog> dogList1 = new ArrayList<>();
		List<Dog> dogList2 = new ArrayList<>();
		Name name1 = new Name("jack", "tom1");
		Name name2 = new Name("jack", "chen2");
		Name name3 = new Name("jerry", "hali3");
		Dog dog1 = new Dog(name1);
		Dog dog2 = new Dog(name2);
		Dog dog3 = new Dog(name3);
		dogList1.add(dog2);
		dogList1.add(dog1);
		dogList2.add(dog1);
		dogList2.add(dog2);
		dogList2.add(dog3);
		List<WeatherInfo> weatherInfos = new ArrayList<>();
		WeatherInfo weatherInfo = new WeatherInfo();
//		weatherInfos.add(weatherInfo);




		if (null == weatherInfo)
		{
			System.out.println("weatherInfo is null");
		}

		if (CollectionUtils.isEmpty(dogList1))
		{
			System.out.println("dogList is empty");
		}

		if (null != weatherInfos)
		{
		}
		for (WeatherInfo weather : weatherInfos)
		{
			System.out.println("FEIKONG");
		}

		String collectionsString1 = JSONArray.toJSONString(dogList1);
		String collectionsString2 = JSONArray.toJSONString(dogList2);
		if (CollectionUtils.isEmpty(weatherInfos))
		{
			System.out.println("weatherInfos is empty");
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		System.out.println(currentTime);
		System.out.println(dateString);
		System.out.println("collectionsString1" + collectionsString1);
		System.out.println("collectionsString2" + collectionsString2);

	}
}
