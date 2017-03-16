package excise;

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
        String accountId = "zhouhongfei@wyb.163.com";
        String sub = accountId.substring(0,accountId.length()-12);
        int a = accountId.indexOf("@");
        String sub2 = accountId.substring(a);
        System.out.println(sub);
        System.out.println(a);
        System.out.println(sub2);
    }
}
