package com.glacier.earthquake.monitor.server.util;

import com.glacier.earthquake.monitor.server.configure.crawler.SpiderInfoManager;
import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by glacier on 15-6-7.
 */
public class PublicSentimentUtils {

    private static Logger logger = Logger.getLogger(PublicSentimentUtils.class.getName());

    public static boolean publicSentimentJudge(Document document, int ruleID, SpiderInfo spiderInfo) {

        FilterRuleMonitor monitor = new FilterRuleMonitor();
        FilterDisaster disaster = monitor.getFilterDisasterByID(ruleID);
        System.out.println("找到的disaster " + disaster);
        List<FilterPublicSentiment> sentiments = monitor.getFilterPubSentimentByName(disaster.getFilterRule());
        System.out.println("sentiments size = " + sentiments.size());
        logger.info("[舆情监测] - 正在进行舆情监测判断 " + document.baseUri());
        if ( sentiments != null ) {
            for (FilterPublicSentiment sentiment : sentiments) {
                logger.info("[舆情监测] - 匹配到 ruleID = " + ruleID + " 对应的舆情监测规则 " + sentiment.getId());
                //判断dom中是否不包含指定的关键字
                boolean ans = true;
                for ( String keyword : sentiment.getUnexist().split(" ") ) {
                    if ( document.toString().contains(keyword) ) {
                        ans = false;
                    }
                }
                if ( ans ) {
                    try {
                        Pattern pattern = Pattern.compile(sentiment.getMatcher());
                        Matcher matcher = pattern.matcher(document.toString());
                        if (matcher.find()) {
                            spiderInfo.setType(SpiderInfo.FILTER_PUBSENTIMENT);
                            SpiderInfoManager.insertSpiderInfo(spiderInfo);
                            Scheduler.insertRecord(Scheduler.SIGN_URL, spiderInfo.getUrl(), spiderInfo.getOrigin(), spiderInfo.getRule_id(), SpiderInfo.FILTER_PUBSENTIMENT);
                            logger.info("[正则匹配] - 正则匹配成功 " + document.baseUri());
                        } else {
                            logger.info("[正则匹配] - 正则匹配失败 " + document.baseUri());
                        }
                    }catch ( PatternSyntaxException e ) {
                        logger.error("[正则错误] - 正则表达式语法错误 " + sentiment.toString() );
                    }
                }
                else {
                    logger.info("[舆情监测] - 文章包含了指明不允许存在的字符集");
                }
            }
            return true;
        }
        return false;
    }

}
