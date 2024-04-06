package common.constant;

/**
 * 返回结果状态码
 *
 * Created by hzguohongguang on 2016/5/18.
 */
public class ResultStatusConstant
{
    public static final int SUCCESS = 200;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int SERVER_ERROR = 500;
    public static final int UNAUTHORIZED = 401;
    public static final int SESSION_TIMEOUT = 402;
    /**
     * 业务异常
     */
    public static final int BUSINESS_ERROR = 700;
    /**
     * 校验失败
     */
    public static final int VALIDATE_FAILED = 422;
}
