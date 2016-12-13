package excise;

import excise.PlatformSignVo;

import java.sql.Timestamp;

/**
 * Description:  测试finally 用法
 *
 * @author hzzhouhongfei
 *         2016年6月4日下午3:44:26
 */
public class FinallyTest
{
    private static String aString;

    public static void main(String[] a)
    {
        String aString = "hello";
        PlatformSignVo platformSignVo1 = new PlatformSignVo("platformSignVo1", "Rsa", Timestamp.valueOf("2016-02-16 00:00:00"),
                Timestamp.valueOf("2016-02-24 10:10:23"), "DEFAULT");
        PlatformSignVo platformSignVo2 = new PlatformSignVo("platformSignVo2", "Rsa", Timestamp.valueOf("2016-02-16 00:00:00"),
                Timestamp.valueOf("2016-02-24 10:10:23"), "DEFAULT");
        PlatformSignVo platformSignVo = test1(platformSignVo1, platformSignVo2);
        System.out.println(test2());
        System.out.println(platformSignVo);
    }

    public static PlatformSignVo test1(PlatformSignVo platformSignVo1, PlatformSignVo platformSignVo2)
    {
        PlatformSignVo platformSignVo = new PlatformSignVo();
        try
        {
            System.out.println("return 之前");
            platformSignVo = platformSignVo2;
            platformSignVo = platformSignVo1;
            return platformSignVo;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("final return 之前");
//			return aString = "final return result";
            platformSignVo = platformSignVo2;
        }
        return platformSignVo;
    }

    public static String test2()
    {
        String aString = "aa";
        try
        {
            System.out.println("return 之前----try");
            aString = "result----try";
            return aString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("return 之前----finally");
            aString = "result----finally";
        }
        return aString;
    }
}
