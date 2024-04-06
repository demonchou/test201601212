package utils;

import java.util.regex.Pattern;

/**
 * 加密机助手类
 */
public class EMUtil {

    /**
     * 对数据数组进行追加操作
     * 
     * @param source      要追加数据的源数组
     * @param appendIndex 要追加数据的起始索引
     * @param appendData  要追加的数据数组
     * @return
     */
    public static byte[] arrayAppend(final byte[] source, int appendIndex, final byte[] appendData) {
        if (appendData.length + appendIndex > source.length) {
            throw new IndexOutOfBoundsException("追加数据超出原数据数组容量");
        }
        System.arraycopy(appendData, 0, source, appendIndex, appendData.length);
        return source;
    }

    /**
     * 合并字节数组
     * 
     * @param bt1 字节数组1
     * @param bt2 字节数组2
     * @return
     * @throws Exception
     */
    public static byte[] mergeByte(byte[] bt1, byte[] bt2) {
        byte[] dest = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, dest, 0, bt1.length);
        int offset = bt1.length;
        System.arraycopy(bt2, 0, dest, offset, bt2.length);
        return dest;
    }

    /**
     * 整数转十六进制字符串
     * 
     * @param num        整数值
     * @param hexByteLen 目标十六进制数据字节长度
     * @return
     */
    public static String intToHexString(int num, int hexByteLen) {
        return String.format("%0" + hexByteLen * 2 + "x", num).toUpperCase();
    }

    /**
     * 读取数据域
     * 
     * @param data       数据
     * @param fieldIndex 数据域起始索引
     * @param fieldLen   数据域数据长度
     * @return
     */
    public static byte[] readField(byte[] data, int fieldIndex, int fieldLen) {
        byte[] filedData = new byte[fieldLen];
        System.arraycopy(data, fieldIndex, filedData, 0, fieldLen);
        return filedData;
    }

    /**
     * 高字节序 - 由高位到低位
     * 
     * @param bytes
     * @return
     */
    public static int byteToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {
            int shift = (bytes.length - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;
        }
        return value;
    }

    /**
     * 是否为空字符串
     * @param value
     * @return
     */
    public static boolean isEmpty(String value){
        return value == null || "".equals(value);
    }

    /**
     * 是否不为空字符串
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value){
        return !isEmpty(value);
    }

    /**
     * 是否为空白字符串
     * @param value
     * @return
     */
    public static boolean isBlank(String value){
        return value == null || Pattern.matches("\\s*", value);
    }

    /**
     * 是否不为空白字符串
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value){
        return !isBlank(value);
    }

    /**
     * NVL ，如果字符串为空白，则取默认值
     * 
     * @param value        字符串值
     * @param defaultValue 默认值
     * @return
     */
    public static String nvl(String value, String defaultValue) {
        return isBlank(value) ? defaultValue : value;
    }

    /**
     * NVL ，如果数值小于等于0，则取默认值
     * 
     * @param value        数值
     * @param defaultValue 默认值
     * @return
     */
    public static int nvlNum(int value, int defaultValue) {
        return value <= 0 ? defaultValue : value;
    }

    /**
     * 对象转字符串
     */
    public static String valueOf(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    /**
     * 左补值，值为空时不补
     * 
     * @param value
     * @param padValue
     * @return
     */
    public static String valueLeftPad(String value, String padValue) {
        return isNotBlank(value) ? padValue + value : "";
    }

    /**
     * 右补值，值为空时不补
     * 
     * @param value
     * @param padValue
     * @return
     */
    public static String valueRightPad(String value, String padValue) {
        return isNotBlank(value) ? padValue + value : "";
    }

    /**
     * 是否包含字符串(不区分大小写)
     *
     * @param str           验证字符串
     * @param searchStrings 字符串组
     * @return boolean
     */
    public static boolean inStringIgnoreCase(String str, String... searchStrings) {
        if (str != null && searchStrings != null) {
            for (String s : searchStrings) {
                if (str.equalsIgnoreCase(s == null ? null : s.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 数值补0
     * 
     * @param length 长度
     * @param num    数值
     * @return
     */
    public static String paddingNumber(int length, int num) {
        return String.format("%0" + length + "d", num);
    }
}
