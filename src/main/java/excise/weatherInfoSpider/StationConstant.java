package excise.weatherInfoSpider;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: stationMap表示 数字<-->地点名称
 * Created by zhouhongfei on 2017/3/10 13:11.
 * ||E-mail:zhouhfsix@foxmail.com
 */
public class StationConstant
{
	public final static Map<String, String> stationMap = new HashMap<>();

	static
	{
		stationMap.put("1", "永定门");
		stationMap.put("2", "京南");
		stationMap.put("3", "香山");
		stationMap.put("5", "丰台花园");
		stationMap.put("6", "顺义");
		stationMap.put("7", "延庆");
		stationMap.put("8", "平谷");
		stationMap.put("9", "房山");
		stationMap.put("10", "亦庄");
		stationMap.put("11", "云岗");
		stationMap.put("12", "京东北");
		stationMap.put("13", "怀柔");
		stationMap.put("14", "京西北");
		stationMap.put("15", "万寿西宫");
		stationMap.put("17", "昌平");
		stationMap.put("18", "门头沟");
		stationMap.put("19", "通州");
		stationMap.put("20", "大兴");
		stationMap.put("21", "定陵");
		stationMap.put("23", "前门");
		stationMap.put("24", "东四");
		stationMap.put("25", "天坛");
		stationMap.put("26", "奥体中心");
		stationMap.put("27", "农展馆");
		stationMap.put("28", "密云");
		stationMap.put("29", "古城");
		stationMap.put("32", "官园");
		stationMap.put("34", "南三环");
		stationMap.put("37", "北部新区");
		stationMap.put("38", "万柳");
		stationMap.put("40", "京东南");
		stationMap.put("41", "京西南");
		stationMap.put("43", "京东");
		stationMap.put("46", "京西环");
		stationMap.put("47", "西直门");
	}

}
