package excise;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import excise.weatherInfoSpider.WeatherInfo;
import lombok.Data;

/**
 * Description:json数据测试
 * Created by hzzhouhongfei.
 * 2017/7/21 10:15
 */
public class JsonDataTest
{
	public static void main(String[] args)
	{

		List<WeatherInfo> weatherInfoList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Map<String, Object>> mapMap = new HashMap<>();
		Hello hello1 = new Hello("1001", "0");
		Hello hello2 = new Hello("2001", "1");
		WeatherInfo weatherInfo = new WeatherInfo();
		weatherInfo.setSo2("1");
		WeatherInfo weatherInfo1 = new WeatherInfo();
		weatherInfo1.setSo2("3");
		weatherInfoList.add(weatherInfo);
		weatherInfoList.add(weatherInfo1);

		map.put("1001", hello1);
//		map.put("2001", hello2);
		mapMap.put("content", map);
		String json = JSON.toJSONString(map);
		String jsons = JSON.toJSONString(mapMap);
		Map<String, Object> map1 = JSON.parseObject(json);
		String jstring = JSON.toJSONString(weatherInfoList);

		//转成成数组
		ArrayList<WeatherInfo> list = JSON.parseObject(jstring, new TypeReference<ArrayList<WeatherInfo>>(){});
		for (WeatherInfo w : list)
		{
			System.out.println(w.getSo2());
		}
//		System.out.println(map1.get("1"));
		for (String key : map1.keySet())
		{
			Hello hello3 = JSON.parseObject(map1.get(key).toString(),Hello.class);
			System.out.println(hello3.getFlag());
			System.out.println(hello3.getId());
			System.out.println(hello3);
		}
		String js = "{\"notifyConfig\":{\"notifyType\":\"EMAIL\",\"notifyMethod\":\"ONE_TO_ONE\",\"templateName\":\"gate_maintain\",\"subject\":\"测试\",\"templateContent\":\"www.163.com\",\"contactType\":\"MAIN\",\"otherContacts\":\"\"}}";
		String js1 = "{\"notifyType\":\"EMAIL\",\"notifyMethod\":\"ONE_TO_ONE\",\"templateName\":\"gate_maintain\",\"subject\":\"测试\",\"templateContent\":\"www.163.com\",\"contactType\":\"MAIN\",\"otherContacts\":\"\"}";
		JSONObject jsonObject = JSON.parseObject(js);

		ConfigDto configDto = JSON.parseObject(js1, ConfigDto.class);
		ConfigDto1 configDto1 = JSON.parseObject(js1, ConfigDto1.class);
		System.out.println(configDto.getContactType());
		System.out.println(configDto1.getContactType());
	}

}

class Hello
{
	private String id;
	private String flag;

	Hello(String id, String flag)
	{
		this.id = id;
		this.flag = flag;
	}

	Hello()
	{

	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}
}

@Data
class ConfigDto
{
	private String notifyId; //数据库ID
	private String notifyType; //通知类型：("EMAIL", "邮件通知"; "SMS", "短信通知");
	private String notifyTypeDesc;//通知类型描述
	private String notifyMethod; // 通知方式（"MANY_TO_ONE",多个商户一次通知；"ONE_TO_ONE"、一个商户一次通知）
	private String notifyMethodDesc;//通知方式描述
	private String templateName; //模板名
	private String subject; // 主题
	private String contactType; // 联系人类型
	private String contactTypeDesc; // 联系人类型描述
	private String otherContacts; // 其它待通知的联系人，多个以","号隔开
	private String templateContent; // 模板内容
	private String createUser; //模板创建人
	private Timestamp createTime;// 模板创建时间
	private String updateUser; //模板更新人
	private Timestamp updateTime;// 模板更新时间
}
@Data
class ConfigDto1
{
	private String TemplateId;
	private String notifyId; //数据库ID
	private String notifyType; //通知类型：("EMAIL", "邮件通知"; "SMS", "短信通知");
	private String notifyTypeDesc;//通知类型描述
	private String notifyMethod; // 通知方式（"MANY_TO_ONE",多个商户一次通知；"ONE_TO_ONE"、一个商户一次通知）
	private String notifyMethodDesc;//通知方式描述
	private String templateName; //模板名
	private String subject; // 主题
	private String contactType; // 联系人类型
	private String contactTypeDesc; // 联系人类型描述
	private String otherContacts; // 其它待通知的联系人，多个以","号隔开
	private String templateContent; // 模板内容
	private String createUser; //模板创建人
	private Timestamp createTime;// 模板创建时间
	private String updateUser; //模板更新人
	private Timestamp updateTime;// 模板更新时间

}
