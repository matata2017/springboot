package com.xxy.springboot.Utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 货币工具类
 */
public class PriceUtil {

    /**
     * 相加，返回double
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 多个相加，返回double
     */
    public static double addAll(Double... d) {
        double a = 0.0d;
        for (int i = 0; i < d.length; i++) {
            if (d[i] == null) {
                d[i] = 0.0d;
            }
            a = add(a, d[i]);
        }
        return a;
    }

    /**
     * 相减，返回double
     */
    public static double subtract(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 相乘，返回double
     */
    public static double multiply(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 相除，返回double
     */
    public static double divide(double d1, double d2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 比较大小
     */
    public static int compareTo(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.compareTo(b2);
    }

    /**
     * 金额格式化
     */
    public static String format(double d, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(d);
    }

    /**
     * 金额格式化
     */
    public static String format(double d) {
        return format(d, "0.##");
    }

    public static String toChinese(String string) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String result = "";
        int n = string.length();
        for (int i = 0; i < n; i++) {

            int num = string.charAt(i) - '0';

            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }
}
