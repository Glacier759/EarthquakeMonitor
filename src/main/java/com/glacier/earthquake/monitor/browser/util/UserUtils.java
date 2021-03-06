package com.glacier.earthquake.monitor.browser.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glacier on 15-5-26.
 */
public class UserUtils {

    private static Logger logger = Logger.getLogger(UserUtils.class.getName());

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean isMobile(String mobile) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.find();
    }

    public static String privilegeToString( int privilege ) {
        if ( privilege == 0 ) {
            return "user";
        }
        else if ( privilege == 1 ) {
            return "admin";
        }
        else if ( privilege == 2 ) {
            return "root";
        }
        return "error";
    }

    public static String dateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

}
