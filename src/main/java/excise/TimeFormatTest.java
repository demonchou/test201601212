package excise;

import utils.DateUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ TimeFormatTest, 2017/11/16 hzzhouhongfei $$
 */
public class TimeFormatTest
{

	public static void main(String[] args)
	{
		System.out.println(DateUtil.dateFormat(
				DateUtil.formatToTimestamp("2017-12-12 00:00:11", DateUtil.FMT_DATE_YYYYMMDD_HHMMSS),
				DateUtil.FMT_DATE_YYYYMMDDHHMMSS));
		System.out.println(DateUtil
				.formatToTimestamp("2017-12-12 00:00:11", DateUtil.FMT_DATE_YYYYMMDD_HHMMSS));
		System.out.println(DateUtil.dateFormat(
				DateUtil.formatToTimestamp("20171016135957", DateUtil.FMT_DATE_YYYYMMDDHHMMSS),
				DateUtil.FMT_DATE_YYYYMMDD));
		System.out.println(DateUtil.dateFormat(
				DateUtil.formatToTimestamp("20171016135957", DateUtil.FMT_DATE_YYYYMMDDHHMMSS),
				DateUtil.FMT_DATE_YYYYMMDD_HHMMSS));

		System.out.println(DateUtil.dateFormat(DateUtil.getCurrentTime(), DateUtil.FMT_DATE_YYYYMMDD_HHMMSS));
	}
}
