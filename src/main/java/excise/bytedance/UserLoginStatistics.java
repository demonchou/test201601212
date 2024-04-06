package excise.bytedance;

public class UserLoginStatistics {
    private int[] bitmap;

    public UserLoginStatistics(int numUsers) {
        // 根据用户数量初始化位图大小，每个整数可以表示 32 个用户的登录状态
        int bitmapSize = (int) Math.ceil((double) numUsers / 32);
        bitmap = new int[bitmapSize];
    }

    // 用户登录，将用户的登录状态置为1
    public void login(int userId) {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        int index = userId / 32; // 计算整数数组的索引
        int bitPosition = userId % 32; // 计算位在整数中的位置
        bitmap[index] |= (1 << bitPosition); // 设置位
    }

    // 用户退出登录，将用户的登录状态置为0
    public void logout(int userId) {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        int index = userId / 32; // 计算整数数组的索引
        int bitPosition = userId % 32; // 计算位在整数中的位置
        bitmap[index] &= ~(1 << bitPosition); // 清除位
    }

    // 检查用户是否已登录
    public boolean isLoggedin(int userId) {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        int index = userId / 32; // 计算整数数组的索引
        int bitPosition = userId % 32; // 计算位在整数中的位置
        return (bitmap[index] & (1 << bitPosition)) != 0; // 检查位的值
    }

    public static void main(String[] args) {
        int numUsers = 100; // 假设有100个用户
        UserLoginStatistics loginStatistics = new UserLoginStatistics(numUsers);

        // 用户登录
        loginStatistics.login(5);
        loginStatistics.login(10);
        loginStatistics.login(3);

        // 用户退出登录
        loginStatistics.logout(10);

        // 检查用户登录状态
        System.out.println("User 5 is logged in: " + loginStatistics.isLoggedin(5)); // true
        System.out.println("User 10 is logged in: " + loginStatistics.isLoggedin(10)); // false
        System.out.println("User 3 is logged in: " + loginStatistics.isLoggedin(3)); // true
    }
}
