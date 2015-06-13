package com.glacier.earthquake.monitor.server.util;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Created by glacier on 15-5-1.
 */
public class StringUtils {

    public static boolean hasDate( String text ) {
        Pattern pattern_1 = Pattern.compile("");

        return false;
    }

    /**
     * 判断字符串是否为空或空字符串
     * @param str 原字符串
     * @return true表示为空, false表示非空
     * */
    public static boolean isEmpty( String str ) {
        return str == null || "".equals(str);
    }

    /**
     * 全角转半角
     * @param fullStr 全角字符串
     * @return 半角字符串
     * */
    public static final String full2half( String fullStr ) {
        if ( isEmpty(fullStr) ) {
            return fullStr;
        }
        char[] chars = fullStr.toCharArray();
        for ( int i = 0; i < chars.length; i ++ ) {
            if ( chars[i] >= 65281 && chars[i] <= 65374 ) {
                chars[i] = (char)(chars[i] - 65248);
            }
            else if ( chars[i] == 12288 ) {     //空格
                chars[i] = (char)32;
            }
        }
        return new String(chars);
    }

    /**
     * 半角转全角
     * @param halfStr 半角字符串
     * @return 全角字符串
     * */
    public static final String half2full( String halfStr ) {
        if ( isEmpty(halfStr) ) {
            return halfStr;
        }
        char[] chars = halfStr.toCharArray();
        for ( int i = 0; i < chars.length; i ++ ) {
            if ( chars[i] == 32 ) {
                chars[i] = (char)12288;
            }
            else if ( chars[i] < 127 ) {
                chars[i] = (char)(chars[i] + 65248);
            }
        }
        return new String(chars);
    }

    /**
     * 忽略大小写情况判断两个字符串是否一致
     * */
    public static final boolean startsWithIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(str1, str2);
    }

}
