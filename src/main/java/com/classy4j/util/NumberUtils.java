package com.classy4j.util;

/**
 * 数字工具类
 */
public class NumberUtils {
    public static int orZero(Integer num) {
        return num == null ? 0 : num;
    }
}
