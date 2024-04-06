package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author hzzhouhongfei
 * @version $$ DateUtil, 2017/9/26 hzzhouhongfei $$
 */
public class DateUtil
{
	private static Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * 默认的日期格式
	 */
	public static final String FORMATE_STANDARD = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMATE_DATE = "yyyy-MM-dd";

	public static final String FMT_DATE_YYYY = "yyyy";
	public static final String FMT_DATE_YYYYMMDD = "yyyy-MM-dd";
	public static final String FMT_DATE_YYYY_DOT_MM_DOT_DD = "yyyy.MM.dd";
	public static final String FMT_DATE_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String FMT_DATE_HHMMSS = "HH:mm:ss";
	public static final String FMT_DATE_HHMM = "HH:mm";
	public static final String FMT_DATE_SPECIAL = "yyyyMMdd";
	public static final String FMT_DATE_MMDD = "MM-dd";
	public static final String FMT_DATE_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static final String FMT_DATE_MMDD_HHMM = "MM-dd HH:mm";
	public static final String FMT_DATE_MMMDDD = "MM月dd日";
	public static final String FMT_DATE_YYYYMMDDHHMM_NEW = "yyyyMMddHHmm";
	@Deprecated
	public static final String FMT_DATE_YYYY年MM月DD日 = "yyyy年MM月dd日";
	public static final String FMT_DATE_YYYYMMDD_CN = "yyyy年MM月dd日";
	public static final String FMT_DATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String FMT_DATE_YYYYMMDDHH = "yyyyMMddHH";
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String FMT_DATE_YYYY_SLASH_MM_SLASH_DD_HHmmss = "yyyy/MM/dd HH:mm:ss";

	public static final int DAYS_OF_A_WEEK = 7;
	public static final String LONG_TERM_TIMESTAMP = "9999-12-31 23:59:59";

	/**
	 * 请求账户相关数据，没有传入开始与结果时间时，默认查询天数
	 */
	public static final int ACCOUNT_QUERY_DATE_NUM = 1;

	private DateUtil()
	{
	}

	/**
	 * 获取系统当前时间
	 *
	 * @return
	 */
	public static Timestamp getCurrentTime()
	{
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 将Timestamp对象格式化
	 *
	 * @param time
	 * @param format
	 * @return 格式化后的日期字符串，如果传入的Timestamp对象为NULL，返回空字符串
	 */
	public static String formatTimestamp(Timestamp time, String format)
	{
		if (time == null)
		{
			return "";
		}
		return new SimpleDateFormat(format).format(time);
	}

	/**
	 * 判断传入日期是否是今天
	 */
	public static boolean isToday(Date date)
	{
		return isSameDay(date, new Date());
	}

	/**
	 * 判断传入的两个日期是否是同年同月同日
	 *
	 * @param source 要判断的体日期
	 * @param target 目标日期
	 * @return boolean 是否同一天
	 */
	public static boolean isSameDay(Date source, Date target)
	{
		if (source == null || target == null)
		{
			return false;
		}
		SimpleDateFormat sourceFmt = new SimpleDateFormat(FORMATE_DATE);
		String sourceDate = sourceFmt.format(source);
		String targetDate = sourceFmt.format(target);
		return StringUtils.equals(sourceDate, targetDate);
	}

	/**
	 * 得到当天开始的Timestamp
	 */
	public static Timestamp getBeginOfToday()
	{
		return getBeginOfThisDay(new Date());
	}

	public static Timestamp getBeginOfThisDay(Date time)
	{
		SimpleDateFormat form = new SimpleDateFormat(FORMATE_DATE);
		String dateStr = form.format(time);
		Date date = null;
		try
		{
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 得到当天结束的Timestamp
	 */
	public static Timestamp getEndOfToday()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat(FORMATE_DATE);
		String dateStr = form.format(cal.getTime()) + " 23:59:59";
		Date date = null;
		try
		{
			form = new SimpleDateFormat(FMT_DATE_YYYYMMDD_HHMMSS);
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}

	public static Timestamp getEndOfDay(Date now)
	{
		SimpleDateFormat form = new SimpleDateFormat(FORMATE_DATE);
		String dateStr = form.format(now.getTime()) + " 23:59:59";
		Date date = null;
		try
		{
			form = new SimpleDateFormat(FMT_DATE_YYYYMMDD_HHMMSS);
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}

	public static Timestamp getBeginOfThisMonth()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DATE, 1);
		String dateStr = form.format(cal.getTime()) + " 00:00:00";
		Date date = null;
		try
		{
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * @param date2Get 将日期按照指定的天数增加或者减少，并转换为需要的日期格式
	 * @param format   需要转换为的格式
	 * @param days     时间间隔
	 * @return date2Get 成功：转换后的日期，失败：can't format your input
	 */
	public static String getIntervalDate(Date date2Get, String format, int days)
	{
		Date d = DateUtil.getIntervalDate(date2Get, days);
		return new SimpleDateFormat(format).format(d);
	}

	/**
	 * 指定时间以后的几年
	 *
	 * @param date
	 * @param years
	 * @return
	 */
	public static Timestamp getIntervalYear(Date date, int years)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * 指定时间以后的几天
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getIntervalDate(Date date, int days)
	{
		return new Date(date.getTime() + TimeUnit.DAYS.toMillis(days));
	}

	/**
	 * 指定时间以后的几天
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getIntervalDate(Timestamp date, int days)
	{
		return new Date(date.getTime() + TimeUnit.DAYS.toMillis(days));
	}

	/**
	 * <p>得到传入时间后几天的开始时间</p>
	 *
	 * @return
	 * @author ztjie
	 * @date 2013-12-5 下午9:03:54
	 * @see
	 */
	public static Date beforeDateStartTime(Date date, int before)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + before, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * <p>得到传入时间后几天的结束时间</p>
	 *
	 * @return
	 * @author ztjie
	 * @date 2013-12-5 下午9:04:18
	 * @see
	 */
	public static Date beforeDateLastTime(Date date, int before)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + before, 23, 59, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 调整时间为得到传入时间所在的天最后一秒
	 *
	 * @param date
	 * @return
	 */
	public static Date oneDateLastTime(Date date)
	{
		if (date == null)
		{
			return date;
		}
		return DateUtil.beforeDateLastTime(date, 0);
	}

	/**
	 * 调整时间为得到传入时间所在的天第一秒
	 *
	 * @param date
	 * @return
	 */
	public static Date oneDateStartTime(Date date)
	{
		if (date == null)
		{
			return date;
		}
		return DateUtil.beforeDateStartTime(date, 0);
	}

	/**
	 * <p>得到几年前的当前时间</p>
	 *
	 * @param date
	 * @param before
	 * @return
	 * @author Think
	 * @date 2013-12-7 上午10:16:13
	 * @see
	 */
	public static Date beforeYearTime(Date date, int before)
	{
		Calendar cal = Calendar.getInstance();//得到一个Calendar的实例
		cal.setTime(date); //设置时间为当前时间
		cal.add(Calendar.YEAR, before); //年份减1
		return cal.getTime();
	}

	/**
	 * 格式化时间
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateFormat(Date date, String format)
	{
		String str = "";
		if (date == null)
		{
			return str;
		}
		if (StringUtils.isBlank(format))
		{
			format = "yyyy年MM月dd日 HH:mm:ss";
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * 指定时间以后的几天
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Timestamp getIntervalTimestamp(Timestamp date, int days)
	{
		return new Timestamp(getIntervalDate(date, days).getTime());
	}

	/**
	 * 获取某个时间的前几个月时间
	 *
	 * @param date
	 * @param month
	 * @return
	 */
	public static Timestamp getDateBeforeMonth(Timestamp date, int month)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -month);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * 获取某个时间的后几个月时间
	 *
	 * @param date
	 * @param month
	 * @return
	 */
	public static Timestamp getDateAfterMonth(Timestamp date, int month)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * 将字符串格式化为日期对象
	 *
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Timestamp formatToTimestamp(String dateStr, String format)
	{
		try
		{
			SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
			return new Timestamp(sorceFmt.parse(dateStr).getTime()); // 一天的时间24*3600*1000
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 获取两个时间的间隔秒数
	 *
	 * @param startTime 开始时间
	 * @param endTime   结束时间
	 * @return 间隔秒数
	 */
	public static long getIntervalSeconds(final Timestamp startTime, final Timestamp endTime)
	{
		long interval = startTime.getTime() - endTime.getTime();
		long intervalSeconds = interval / TimeUnit.SECONDS.toMillis(1L);
		return Math.abs(intervalSeconds);
	}

	/**
	 * 得到间隔天数,请求参数类型为Date
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(Date start, Date end)
	{
		Date beginOfStart = DateUtils.truncate(start, Calendar.DATE);
		Date beginOfEnd = DateUtils.truncate(end, Calendar.DATE);
		return (int) ((beginOfEnd.getTime() - beginOfStart.getTime()) / DateUtils.MILLIS_PER_DAY);
	}

	/**
	 * 得到间隔天数，请求参数类型为Timestamp
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(Timestamp start, Timestamp end)
	{
		Date beginOfStart = DateUtils.truncate(start, Calendar.DATE);
		Date beginOfEnd = DateUtils.truncate(end, Calendar.DATE);
		return (int) ((beginOfEnd.getTime() - beginOfStart.getTime()) / DateUtils.MILLIS_PER_DAY);
	}

	/**
	 * 获取某个时间的前几天的时间
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Timestamp getDateBeforeDays(Timestamp date, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * 获取某个时间的前几天的时间，并格式化为需要的时间格式
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Timestamp getDateBeforeDaysFormat(Timestamp date, int days, String format)
	{
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -days); //得到前一天
		String resultDate = new SimpleDateFormat(format).format(calendar.getTime());
		return Timestamp.valueOf(resultDate);
	}

	/**
	 * 获取某个时间的前几年的时间，并格式化为需要的时间格式
	 *
	 * @param date
	 * @param years
	 * @return
	 */
	public static Timestamp getDateBeforeYears(Timestamp date, int years)
	{
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -years);
		String resultDate = new SimpleDateFormat(FORMATE_STANDARD).format(calendar.getTime());
		return Timestamp.valueOf(resultDate);
	}

	/**
	 * 获取某个时间的前几年的时间，并格式化为需要的时间格式
	 *
	 * @param date
	 * @param months
	 * @return
	 */
	public static Timestamp getDateBeforeMonths(Timestamp date, int months)
	{
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -months);
		String resultDate = new SimpleDateFormat(FORMATE_STANDARD).format(calendar.getTime());
		return Timestamp.valueOf(resultDate);
	}

	/**
	 * 格式化为需要的时间格式
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static Timestamp getDateFormat(Timestamp date, String format)
	{
		String resultDate = new SimpleDateFormat(format).format(date);
		return Timestamp.valueOf(resultDate);
	}

	/**
	 * 获取某个时间的前几月的时间，并格式化为需要的时间格式
	 *
	 * @param date
	 * @param months
	 * @return
	 */
	public static Timestamp getDateBeforeMonthsFormat(Timestamp date, int months, String format)
	{
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.MONTH, -months);
		String resultDate = new SimpleDateFormat(format).format(calendar.getTime());
		return Timestamp.valueOf(resultDate);
	}

	/**
	 * 将timestamp数据库的数据转化成相应格式的字符串日期输出
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateFromTimestamp(Timestamp date, String format)
	{
		DateFormat sdf = new SimpleDateFormat(format);
		return date == null ? "" : sdf.format(date);
	}

	/**
	 * 计算两个日期相差的天数
	 *
	 * @param firstTime
	 * @param lastTime
	 * @return
	 */
	public static Integer differentDaysByMillisecond(Timestamp firstTime, Timestamp lastTime)
	{
		if (firstTime.after(lastTime))
		{
			return 0;
		}
		return (int) ((lastTime.getTime() - firstTime.getTime()) / (1000 * 3600 * 24));
	}

	/**
	 * 检验日期字符串是否为期望的格式
	 */
	public static boolean checkDateFormat(String dateStr, String dateFormat)
	{
		if (dateStr == null || dateStr.equals(StringUtils.EMPTY))
		{
			return false;
		}

		try
		{
			new SimpleDateFormat(dateFormat).parse(dateStr);
		}
		catch (ParseException e)
		{
			return false;
		}

		return true;
	}

	/**
	 * 遍历时间段内的日期，并按期望格式返回List
	 *
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @param formatStr 期望格式
	 * @return 时间段内的日期list
	 */
	public static List<String> getDateListInTimeInterval(Timestamp beginDate, Timestamp endDate, String formatStr)
	{
		List<String> dateList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		DateFormat toFormat = new SimpleDateFormat(formatStr);

		Date date = beginDate;

		if (date.after(endDate))
		{
			return null;
		}

		String resultDate = toFormat.format(date);
		dateList.add(resultDate);
		while (date.before(endDate))
		{
			c.setTime(date);
			// 日期加1天
			c.add(Calendar.DATE, 1);
			date = c.getTime();
			resultDate = toFormat.format(date);
			dateList.add(resultDate);
		}
		return dateList;
	}

	/**
	 * 获取一天24小时的毫秒数
	 * @return 毫秒数
	 */
	public static Long getOneDayMilliseconds()
	{
		return 24L * 60L * 60L * 1000L;
	}

	/**
	 * 获取今天的日期String YYYYMMDDHH
	 */
	public static String getTodayStringYYYYMMDDHH()
	{
		return formatTimestamp(getCurrentTime(), FMT_DATE_YYYYMMDDHH);
	}

	/**
	 * 获取默认的长期timestamp
	 */
	public static Timestamp getDefaultLongTermTimestamp()
	{
		return formatToTimestamp(LONG_TERM_TIMESTAMP, FMT_DATE_YYYYMMDD_HHMMSS);
	}

	/**
	 * 获取当前的年份
	 * @return String 当前的年份
	 */
	public static String getCurrentYear()
	{
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "";
	}

	/**
	 * 获取指定日期所在月份开始的时间戳
	 * @param date 指定日期
	 * @return
	 */
	public static Timestamp getMonthBegin(Timestamp date)
	{
		Calendar monthBegin = Calendar.getInstance();
		monthBegin.setTime(date);
		monthBegin.set(Calendar.DAY_OF_MONTH, 1);
		monthBegin.set(Calendar.HOUR_OF_DAY, 0);
		monthBegin.set(Calendar.MINUTE, 0);
		monthBegin.set(Calendar.SECOND, 0);
		monthBegin.set(Calendar.MILLISECOND, 0);
		return new Timestamp(monthBegin.getTimeInMillis());
	}

	/**
	 * 获取指定日期所在月份结束的时间戳
	 * @param date 指定日期
	 * @return
	 */
	public static Timestamp getMonthEnd(Timestamp date)
	{
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.setTime(date);
		monthEnd.set(Calendar.DAY_OF_MONTH, monthEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
		monthEnd.set(Calendar.HOUR_OF_DAY, 23);
		monthEnd.set(Calendar.MINUTE, 59);
		monthEnd.set(Calendar.SECOND, 59);
		monthEnd.set(Calendar.MILLISECOND, 999);
		return new Timestamp(monthEnd.getTimeInMillis());
	}

	/**
	 * 判断传入时间是否大于当前时间
	 * @param time 指定时间
	 * @return
	 */
	public static boolean isGreaterThanNow(Timestamp time)
	{
		if (time != null)
		{
			return time.getTime() - DateUtil.getCurrentTime().getTime() > 0;
		}
		return false;
	}

	/**
	 * 判断结束时间是否大约开始时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static boolean compareStartAndEndTime(Timestamp startTime, Timestamp endTime)
	{
		if (startTime != null && endTime != null)
		{
			return endTime.getTime() - startTime.getTime() > 0;
		}
		return false;
	}

	public static void main(String[] args)
	{
		Timestamp beginOfToday = getBeginOfToday();
//		Timestamp endOfToday = DateUtil.getIntervalTimestamp(DateUtil.getCurrentTimestamp(), 1);
		Timestamp endOfToday = DateUtil.getEndOfDay(getIntervalDate(new Date(), 3));
		Timestamp yestoday = DateUtil.getIntervalTimestamp(DateUtil.getCurrentTime(), -2);

		System.out.println(beginOfToday.toString());
		System.out.println(endOfToday.toString());
		System.out.println(yestoday.toString());
	}
}
