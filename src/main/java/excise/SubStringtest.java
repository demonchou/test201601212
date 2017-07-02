package excise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import common.CommonUtil;

/**
 * @Description:
 * @Author Created by zhouhongfei on 2017/3/6 19:10.
 * E-mail:zhouhfsix@foxmail.com
 */
public class SubStringtest
{
    public static void main(String[] args)
    {
        String aString = "";
        String amountA = "9.001";
        String amountB = "0.901";
        String plainString = "127,312,123,123,12,21312,123,123";

        String accountId = "zhouhongfei@wyb.163.com";
        String accountId2 = "周洪飞";
        String account = "1";
        String phone = "18795958220";
        String aa = null;
        int i = Integer.valueOf(account)+1;
        String sub = accountId.substring(0,accountId.length()-12);
        int a = accountId.indexOf("@");
        String sub2 = accountId.substring(a);
        String sub3 = accountId2.substring(1);
        String emailMask = accountId.substring(0,1) + "***" ;
//        String email = accountId.replaceAll("(\\w)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
        String email0 = CommonUtil.maskAcountIdForMessage(accountId);
        String email1 = accountId.substring(accountId.indexOf("@") -1);
        String phoneMask = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        List<String> plainList = Arrays.asList(plainString.split(","));
        Set<String> set = new HashSet<>();
        set.addAll(plainList);
        List<String> list = new ArrayList<>(set);
        System.out.println(sub);
        System.out.println(a);
        System.out.println(sub2);
        System.out.println("*" + sub3);
        System.out.println(phoneMask);
        System.out.println(email0 + email1);
        System.out.println(i);
        System.out.println((accountId.substring(accountId.length()-4)));
        System.out.println(!Objects.equals(Double.valueOf(amountA), Double.valueOf(amountB)));
        System.out.println(Double.valueOf(amountA));
        System.out.println(Double.valueOf(amountB));
        Double balance = NumberUtils.toDouble(amountA);
        System.out.println(balance <= new BigDecimal("10").doubleValue());
        System.out.println(balance);
        System.out.println(null == aa);
        System.out.println(list.toString() + "size: " + list.size() + list.getClass());

    }
}
