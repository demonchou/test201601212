package excise;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Description:数字测试
 * Created by hzzhouhongfei.
 * 2017/7/2 19:47
 */
public class NumberTest
{
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2);

	public static void main(String[] args)
	{

//		for (int j =0; j < 1000; j++)
//		{
//
//		}
//		for (int i = 0 ; i < 100000 ;i++)
//		{
//			Random rand = new Random();
//			String amount = new DecimalFormat("0.00").format(0.01 + 0.99 * rand.nextDouble()); //随机生成1元以内金额，e.g: 0.78]
////			System.out.println(amount);
//			if (amount.equals("0.01"))
//			{
//				System.out.println("orz-0.00");
//				System.out.println(i);
//				break;
//			}
//
//		}


		String mail = "2@126.com";

//		System.out.println(checkEmail(mail));
//		System.out.println(RandomStringUtils.random(3));



		for (int i = 0 ; i < 1000 ;i++)
		{
			String sequenceNum3 = String.valueOf((int) (Math.random() * 999));
			String seq = new DecimalFormat("000").format((int) (Math.random() * 999) + 1);
			if (sequenceNum3.equals(999))
			{
				System.out.println(sequenceNum3);
				System.out.println("orz-000");
				System.out.println(i);
				break;
			}
			System.out.println(seq);
		}
	}

	public static boolean checkEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
}
