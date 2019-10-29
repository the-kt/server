package com.kumquat.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 13260 on 2019/5/11.
 */
public final class StrUtil {

    /**
     * 从当前字符串的开头和结尾删除所出现的所有 trimChars 参数中的字符后剩余的字符串。
     *
     * @param str
     * @param trimChars
     * @return
     */
    public final static String trim(String str, final String trimChars) {
        int startIndex = str.startsWith(trimChars) ? 1 : 0;
        int endIndex = str.endsWith(trimChars) ? str.length() - 1 : str.length();
        str = str.substring(startIndex, endIndex);
        if (str.startsWith(trimChars) || str.endsWith(trimChars)) {
            str = trim(str, trimChars);
        }
        return str;
    }

    /**
     * 从当前字符串的结尾移除所出现的所有 trimChars 参数中的字符后剩余的字符串。
     *
     * @param str
     * @param trimChars
     * @return
     */
    public final static String trimEnd(String str, final String trimChars) {
        int endIndex = str.endsWith(trimChars) ? str.length() - 1 : str.length();
        str = str.substring(0, endIndex);
        if (str.endsWith(trimChars)) {
            str = trimEnd(str, trimChars);
        }
        return str;
    }

    /**
     * 数组用指定字符连接成字符串
     *
     * @param join
     * @param list
     * @return
     */
    public final static <T> String join(final String join, final List<T> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == (list.size() - 1)) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i)).append(join);
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是为null或者值为空
     *
     * @param str
     * @return
     */
    public final static boolean isNullOrEmpty(final String str) {
        return (str == null || "".equals(str));
    }

    /**
     * 将字符串转换为数字。默认值为0
     *
     * @param str
     * @return
     */
    public final static int ConvertToInt(final String str) {
        if (isNullOrEmpty(str))
            return 0;

        if (!(isInteger(str)))
            return 0;

        return Integer.parseInt(str);
    }

    /**
     * 将字符串转换为long类型数字。默认值为0
     *
     * @param str
     * @return
     */
    public final static long ConvertToLong(final String str) {
        if (isNullOrEmpty(str))
            return 0;

        if (!(isInteger(str)))
            return 0;

        return Long.parseLong(str); // Integer.getInteger(str);
    }

    /**
     * 判断字符串是否为正整数。在使用方法前先判断字符串是否为null。
     *
     * @param str
     * @return
     */
    public final static boolean isInteger(final String str) {
        Pattern pattern = Pattern.compile("[0-9]*"); // 如果需要验证包含负整数，需要修改验证规则为^-?[0-9]+
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
            return false;
        return true;
    }

    /**
     * 判断字符串是否为数字，包含小数、负数等。在使用方法前先判断字符串是否为null
     *
     * @param str
     * @return
     */
    public final static boolean isNumeric(final String str) {
        String regex = "-?[0-9]+.?[0-9]+";

        if (!Pattern.matches(regex,str)) {
            return false;
        }
        return true;
    }

    /**
     * 将/Date(1496937600000+0800)/格式的时间字符串，转换成时间
     *
     * @param str
     * @return
     */
    public final static Date convertToDate(final String str) {
        if (isNullOrEmpty(str))
            return null;
        int index = str.indexOf("(");
        int last = str.indexOf("+");
        String timeStr = str.substring(index, last);
        return new Date(Long.parseLong(timeStr));
    }

    /**
     * 将/Date(1496937600000+0800)/格式的时间字符串，转换成yyyy年MM月dd日时间格式
     *
     * @param str
     * @return
     */
    public final static String formatCNDate(final String str) {
        if (isNullOrEmpty(str))
            return "";
        int index = str.indexOf("(");
        int last = str.indexOf("+");
        String timeStr = str.substring(index + 1, last);
        Date date = new Date(Long.parseLong(timeStr));
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    /**
     * 将geometry转为坐标
     * @param gml
     * @return
     */
    public final static String toCoordinateString(final String gml) {
        String k1 = "<gml:coordinates decimal=\".\" cs=\",\" ts=\" \">";
        String k2 = "</gml:coordinates>";
        String coordinate = "";
        if (gml != null) {
            coordinate = gml;
            int m = coordinate.indexOf(k1);
            int n = coordinate.indexOf(k2);
            coordinate = coordinate.substring(m + k1.length(), n).replace(" ", ",");
            if (coordinate.endsWith(",")) {
                coordinate = coordinate.substring(0, coordinate.length() - 1);
            }
        }
        return coordinate;
    }

    /**
     * get请求数据中编码转换
     * @param str
     * @return
     */
    public final static String convertCharacter(String str) {
        try {
            str = new String(str.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 将字符串的首字符变为小写
     * @param str
     * @return
     */
    public final static String firstCharsetLower(String str) {
        if (isNullOrEmpty(str))
            return str;

        char c = str.charAt(0);
        if (c >= 'A' && c <= 'Z')
            str = str.replaceFirst(c + "", (c + "").toLowerCase());

        return str;
    }
}
