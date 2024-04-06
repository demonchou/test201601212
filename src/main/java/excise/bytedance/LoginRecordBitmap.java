package excise.bytedance;

import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;

public class LoginRecordBitmap {
    private BitSet loginBitmap;

    public LoginRecordBitmap() {
        // 创建一个包含30位的位图，分别对应近一个月的每一天
        loginBitmap = new BitSet(30);
    }

    public void recordLogin(Date loginDate) {
        // 计算日期之间的天数，然后设置对应的位
        int daysAgo = calculateDaysAgo(loginDate);
        if (daysAgo >= 0 && daysAgo < 30) {
            loginBitmap.set(daysAgo);
        }
    }

    public int countLoginsInLastMonth() {
        // 统计位图中设置为1的位的数量，即登录次数
        return loginBitmap.cardinality();
    }

    private int calculateDaysAgo(Date loginDate) {
        // 计算登录日期距离当前日期的天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loginDate);

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        long diff = today.getTimeInMillis() - calendar.getTimeInMillis();
        return (int) (diff / (24 * 60 * 60 * 1000)); // 转换为天数
    }

    public static void main(String[] args) {
        LoginRecordBitmap loginRecordBitmap = new LoginRecordBitmap();

        // 模拟记录登录
        Date loginDate1 = new Date(); // 当前日期
        Date loginDate2 = new Date(System.currentTimeMillis() - 15 * 24 * 60 * 60 * 1000); // 15天前
        Date loginDate3 = new Date(System.currentTimeMillis() - 35 * 24 * 60 * 60 * 1000); // 35天前

        loginRecordBitmap.recordLogin(loginDate1);
        loginRecordBitmap.recordLogin(loginDate2);
        loginRecordBitmap.recordLogin(loginDate3);

        // 统计近一个月的登录次数
        int loginCount = loginRecordBitmap.countLoginsInLastMonth();
        System.out.println("近一个月的登录次数: " + loginCount);
    }
}
