package com.glacier.earthquake.monitor.server.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glacier on 15-5-1.
 */
public class StringUtils {

    /** 表示url中连续的8位日期，例如http://www.baidu.com/20140311/2356.html */
    private static String url_reg_whole= "([-|/|_]{1}20\\d{6})";
    /** 表示 用-或者/隔开的日期,有年月日的，例如 http://www.baidu.com/2014-3-11/2356.html  */
    private static String url_reg_sep_ymd = "([-|/|_]{1}20\\d{2}[-|/|_]{1}\\d{1,2}[-|/|_]{1}\\d{1,2})";
    /** 表示 用-或者/隔开的日期,只有年和月份的，例如 http://www.baidu.com/2014-3/2356.html  */
    private static String url_reg_sep_ym = "([-|/|_]{1}20\\d{2}[-|/|_]{1}\\d{1,2})";
    private static Calendar current = Calendar.getInstance();
    /** 格式正确的时间正则表达式*/
    private static String rightTimeReg = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

    /**
     * @param url
     * @param urlContent
     * @return
     */
    public static Date getPubTimeVarious(String url,String urlContent) {

        try {
            String pubTime = getPubTimeFromUrl(url);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //链接里面没有，匹配文本中的
            if (pubTime == null) {
                if (urlContent != null && !urlContent.trim().equals(""))
                    pubTime = extractPageDate(urlContent);
            }
            return format.parse(pubTime);
        }catch (Exception e) {
            return null;
        }
    }

    /**从url里面抽取出发布时间，返回YYYY-MM-DD HH:mm:ss格式的字符串
     * @param url
     * @return
     */
    public static String getPubTimeFromUrl(String url)
    {
        Pattern p_whole = Pattern.compile(url_reg_whole);
        Matcher m_whole = p_whole.matcher(url);
        if(m_whole.find(0)&&m_whole.groupCount()>0)
        {
            String time =  m_whole.group(0);
            time = time.substring(1,time.length());
            //每一步都不能够超出当前时间
            if(current.compareTo(strToCalendar(time, "yyyyMMdd"))>=0)
            {
                return time.substring(0,4)+"-"+time.substring(4,6)+"-"+
                        time.substring(6,8)+" "+"00:00:00";
            }
        }

        p_whole = null;
        m_whole = null;
        Pattern p_sep = Pattern.compile(url_reg_sep_ymd);
        Matcher m_sep = p_sep.matcher(url);
        if(m_sep.find(0)&&m_sep.groupCount()>0)
        {
            String time =  m_sep.group(0);
            time = time.substring(1,time.length());
            String[] seg = time.split("[-|/|_]{1}");
            Calendar theTime = Calendar.getInstance();
            theTime.set(Calendar.YEAR,Integer.parseInt(seg[0]));
            theTime.set(Calendar.MONTH, Integer.parseInt(seg[1]));
            theTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(seg[2]));
            if(current.compareTo(theTime)>=0)
            {

                return seg[0]+"-"+seg[1]+"-"+seg[2]+" "+"00:00:00";
            }
        }
        p_sep = null;
        m_sep = null;
        Pattern p_sep_ym = Pattern.compile(url_reg_sep_ym);
        Matcher m_sep_ym = p_sep_ym.matcher(url);
        if(m_sep_ym.find(0)&&m_sep_ym.groupCount()>0)
        {
            String time =  m_sep_ym.group(0);
            time = time.substring(1,time.length());
            Calendar theTime = Calendar.getInstance();
            String[] seg = time.split("[-|/|_]{1}");
            theTime.set(Calendar.YEAR,Integer.parseInt(seg[0]));
            theTime.set(Calendar.MONTH, Integer.parseInt(seg[1]));
            theTime.set(Calendar.DAY_OF_MONTH, 1);
            if(current.compareTo(theTime)>=0)
            {

                return seg[0]+"-"+seg[1]+"-"+"01"+" "+"00:00:00";
            }
        }

        return null;
    }


    /** 从网页源码中取出发布时间
     *  java中正则表达式提取字符串中日期实现代码
     *  2013年12月19日15:58:42
     *  读取出2013-12-19 15:48:33或者2013-12-19或者2012/3/05形式的时间
     * @param text 待提取的字符串
     * @return 返回日期
     * @author: oschina
     * @Createtime: Jan 21, 2013
     */
    public static String extractPageDate(String text) {
        boolean  containsHMS =false;
        String dateStr = text.replaceAll("r?n", " ");
        try {
            List matches = null;
            Pattern p_detail = Pattern.compile("(20\\d{2}[-/]\\d{1,2}[-/]\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2})|(20\\d{2}年\\d{1,2}月\\d{1,2}日)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            //如果是仅仅抽取年月日，则按照上面的，如果是抽取年月日-时分秒，则按照下面的
            Pattern p = Pattern.compile("(20\\d{2}[-/]\\d{1,2}[-/]\\d{1,2})|(20\\d{2}年\\d{1,2}月\\d{1,2}日)", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
            //Matcher matcher = p.matcher(dateStr);
            Matcher matcher_detail = p_detail.matcher(dateStr);

            if(!(matcher_detail.find(0) && matcher_detail.groupCount() >= 1))
            {
                matcher_detail = p.matcher(dateStr);
                containsHMS  = true;
            }else
                matcher_detail = p_detail.matcher(dateStr);
            if (matcher_detail.find() && matcher_detail.groupCount() >= 1) {
                matches = new ArrayList();
                for (int i = 1; i <= matcher_detail.groupCount(); i++) {
                    String temp = matcher_detail.group(i);
                    matches.add(temp);
                }
            } else {
                matches = Collections.EMPTY_LIST;
            }

            if (matches.size() > 0) {
                for(int i=0;i<matches.size();i++)
                {
                    String pubTime = matches.get(i).toString().trim();
                    //取出第一个值
                    pubTime = pubTime.replace("/", "-").replace("年", "-").replace("月", "-").replace("日", "-");
                    if(current.compareTo(strToCalendar(pubTime, "yyyy-MM-dd"))>=0)
                    {
                        if(containsHMS)
                            pubTime+=" "+"00:00:00";
                        if(pubTime.matches(rightTimeReg))
                        {
                            return pubTime;
                        }
                    }
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 字符串转换成日期(date)
     * @param pubTime, format
     * @return
     * @throws Exception
     */
    public static Calendar strToCalendar(String pubTime, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date d = dateFormat.parse(pubTime);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return c;
        }catch (Exception e) {
            return null;
        }
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

    /**
     * @param page 页面信息
     * @param keywords 关键字
     * @return null表示不匹配 否则返回匹配的句子
     * */
    public static final String examinePageKeywords( String page, String keywords ) {

        String[] keywordsArr = keywords.split("\\*");
        String patternStr = "";
        for ( String keyword : keywordsArr ) {
            patternStr += keyword + "[\\u4e00-\\u9fa5\\w\\Sm][^，。]*";
        }
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(page);
        System.out.println(pattern.pattern());
        if ( matcher.find() ) {
            return matcher.group(0);
        }
        return null;
    }

    /**
     * @param page 页面信息
     * @param keywords 关键字
     * @return null表示不匹配 否则返回匹配的句子
     * */
    public static final String examinePageKeywords( String page, String[] keywords ) {

        String patternStr = "";
        for ( String keyword : keywords ) {
            patternStr += keyword + "[\\u4e00-\\u9fa5\\w\\Sm]*";
        }
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(page);

        if ( matcher.find() ) {
            return matcher.group(0);
        }
        return null;
    }

    public static final String summaryDispose(String summary, String keywords) {

        try {
            String[] keywordsArr = keywords.split("\\*");
            for (String keyword : keywordsArr) {
                summary = summary.replace(keyword, "<font color=\"red\">" + keyword + "</font>");
            }
            return summary;
        }catch (Exception e) {
            return null;
        }
    }

    public static final String summaryDispose(String summary, String[] keywords) {

        try {
            for (String keyword : keywords) {
                summary = summary.replace(keyword, "<font color=\"red\">" + keyword + "</font>");
            }
            return summary;
        }catch (Exception e) {
            return null;
        }
    }

}
