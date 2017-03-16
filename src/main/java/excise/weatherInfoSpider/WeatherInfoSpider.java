package excise.weatherInfoSpider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:将北京空气质量数据拉去到本地，并保存到excel中
 * Created by zhouhongfei on 2017/3/10 10:49.
 * ||E-mail:zhouhfsix@foxmail.com
 */
public class WeatherInfoSpider
{
	public static void main(String args[]) throws Exception
	{
		//1.1获取当前Unix时间戳
		long date = System.currentTimeMillis();
		String sDate = String.valueOf(date);
		//当前普通格式的时间
		String commonDate = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date(date));
		//文件生成的时间戳
		String fileDate = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date(date));

		//1.2拼接访问的网址，并访问获取信息流
		URL u = new URL("http://zx.bjmemc.com.cn/getAqiList.shtml?timestamp=" + sDate);
		InputStream in = u.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try
		{
			byte buf[] = new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0)
			{
				out.write(buf, 0, read);
			}
            System.out.println("拉取网页数据成功！！！");
        }catch (Exception e)
        {
            System.out.println("拉取网页数据失败！！！");
            e.printStackTrace();
        }
		finally
		{
			if (in != null)
			{
				in.close();
			}
		}

		//1.3截取信息流
		byte[] b = out.toByteArray();
		String oragMes = new String(b, "utf-8");

		//2.1获取含有空气质量的信息
		String str1 = "W3si";//正则表达式前缀
		String str2 = "'";//正则表达式后缀
		String regex1 = "(?<=" + str1 + ")(.*?)(?=" + str2 + ")";
		String subString1 = getRegexedString(oragMes, regex1);
		StringBuffer str3 = new StringBuffer();
		str3.append(str1).append(subString1);
		byte[] decoded = Base64.decode(str3.toString());
		String res2 = new String(decoded, "utf-8");
		System.out
				.println("******************************************$***********************************************");
		//System.out.println(res2);

		//3.1将解析后的信息放在数组中
		ArrayList<WeatherInfo> wi = getWeatherInfos(commonDate, res2);

		//4将空气质量信息输出到本地文件
		FileOutputStream fo = null;
		try
		{
			//4.1获取表格对象
			HSSFWorkbook wkb = getSheets(wi);

			if (null == wkb)
			{
				System.out.println("没有获取有效数据！");
			}
			else
			{
				//4.2将表格对象写入本地文件中
                System.out.println("数据写入本地-开始！！！");
                fo = new FileOutputStream("result\\北京空气质量监测结果" + fileDate + ".xls");
				wkb.write(fo);
                System.out
                        .println("******************************************$$***********************************************");
				System.out.println("数据写入本地-成功！！！");
                System.out.println("结果路径：" + "result\\北京空气质量监测结果" + fileDate + ".xls");
            }
		}
		catch (IOException e)
		{
            System.out.println("数据写入本地-失败！！！");
            e.printStackTrace();
		}
		finally
		{
			if (null != fo)
			{
				fo.close();
			}
		}
    }

	/**
	 * 将各个地点的空气质量信息放在数组中
	 * @param commonDate 普通格式的时间
	 * @param res2 信息源串
	 * @return 信息数据
	 */
	private static ArrayList<WeatherInfo> getWeatherInfos(String commonDate, String res2)
	{
		ArrayList<WeatherInfo> wi = new ArrayList<>();
		JSONArray ja = JSONObject.parseArray(res2);
		for (Object jo : ja)
		{
			WeatherInfo weatherInfo = new WeatherInfo();
			weatherInfo.setId(JSONObject.parseObject(jo.toString()).get("id").toString());
			weatherInfo.setStation(
					StationConstant.stationMap.get(JSONObject.parseObject(jo.toString()).get("id").toString()));
			weatherInfo.setDate_time(commonDate);
			weatherInfo.setPm2(JSONObject.parseObject(jo.toString()).get("pm2").toString());
			weatherInfo.setSo2(JSONObject.parseObject(jo.toString()).get("so2").toString());
			weatherInfo.setO3(JSONObject.parseObject(jo.toString()).get("o3").toString());
			weatherInfo.setPm10(JSONObject.parseObject(jo.toString()).get("pm10").toString());
			weatherInfo.setNo2(JSONObject.parseObject(jo.toString()).get("no2").toString());
			weatherInfo.setCo(JSONObject.parseObject(jo.toString()).get("co").toString());
			if (null != weatherInfo.getStation())
			{
				wi.add(weatherInfo);
				System.out.println(weatherInfo.getId() + "|| " + weatherInfo.toString());
			}
		}
		return wi;
	}

	/**
	 * 获取表格
	 * @param wi 空气质量信息数据
	 * @return sheet 表格
	 */
	private static HSSFWorkbook getSheets(ArrayList<WeatherInfo> wi)
	{
		if (null == wi)
		{
			return null;
		}
		else
		{
			//4.1 创建HSSFWorkbook(excel)对象
			HSSFWorkbook wkb = new HSSFWorkbook();

			//4.2 创建sheet对象
			HSSFSheet sheet = wkb.createSheet("result");

			//格式
			HSSFCellStyle cellStyle = wkb.createCellStyle();
			HSSFFont font = wkb.createFont();
			font.setFontName("宋体");
			cellStyle.setFont(font);

			//4.3 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFRow row1 = sheet.createRow(0);

			//4.4 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			row1.createCell(0).setCellValue("Date_Time");
			row1.createCell(1).setCellValue("Station");
			row1.createCell(2).setCellValue("PM2.5");
			row1.createCell(3).setCellValue("SO2");
			row1.createCell(4).setCellValue("O3");
			row1.createCell(5).setCellValue("PM10");
			row1.createCell(6).setCellValue("NO2");
			row1.createCell(7).setCellValue("CO");

			//4.5 创建第二行及以后的行
			int i = 1;
			for (WeatherInfo wInfo : wi)
			{
				HSSFRow row2 = sheet.createRow(i);
				row2.createCell(0).setCellValue(wInfo.getDate_time());
				row2.createCell(1).setCellValue(wInfo.getStation());
				row2.createCell(2).setCellValue(wInfo.getPm2());
				row2.createCell(3).setCellValue(wInfo.getSo2());
				row2.createCell(4).setCellValue(wInfo.getO3());
				row2.createCell(5).setCellValue(wInfo.getPm10());
				row2.createCell(6).setCellValue(wInfo.getNo2());
				row2.createCell(7).setCellValue(wInfo.getCo());
				i++;
			}
			return wkb;
		}
	}

	/**
	 * 根据正则表达式获得有用信息
	 * @param oragMes 源串
	 * @param regex 正则表达式
	 * @return 最终的有用的信息串
	 * @throws Base64DecodingException
	 * @throws UnsupportedEncodingException
	 */
	private static String getRegexedString(String oragMes, String regex)
			throws Base64DecodingException, UnsupportedEncodingException
	{
		String res = "";//有用信息串
		Pattern p = Pattern.compile(regex);//正则表达式
		Matcher m = p.matcher(oragMes);
		while (m.find())
		{
			res = m.group(1);
			if (res != null)
			{
				break;
			}
		}
		return res;
	}


}
