package com.glacier.earthquake.monitor.server.util;

import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glacier on 15-5-1.
 */
public class JudgeFilter {

    public static Logger logger = Logger.getLogger(JudgeFilter.class.getName());

    public static int isMeetDisaster(Document document) {

        List<FilterDisaster> filterDisasters = Data2Object.filterRulesDisaster();
        for ( FilterDisaster filterDisaster : filterDisasters ) {
            String filterRule = filterDisaster.getFilterRule();
            String[] ruleArray = filterRule.split("\\*");
            boolean ans = true;
            for ( String rule : ruleArray ) {
                ans = ans && document.text().contains(rule);
            }
            if ( ans ) {    return filterDisaster.getId(); }
        }
        return 0;
    }

    public static int isMeetPublicSentiment(Document document) {

        List<FilterPublicSentiment> filterPublicSentiments = Data2Object.filterRulesPubSentiment();
        for ( FilterPublicSentiment filterPublicSentiment : filterPublicSentiments ) {
            Pattern pattern = Pattern.compile(filterPublicSentiment.getMatcher());
            Matcher matcher = pattern.matcher(document.text());
            if ( matcher.find() ) {
                String[] unexistArray = filterPublicSentiment.getUnexist().split(" ");
                boolean ans = true;
                for ( String unexist : unexistArray ) {
                    ans = ans && (!document.text().contains(unexist));
                }
                if ( ans ) {    return filterPublicSentiment.getId(); }
            }
        }
        return 0;

    }

    public static boolean isMeetWhiteList(String url) {
        List<FilterWhiteList> filterWhiteLists = Data2Object.filterRulesWhiteList();
        for (FilterWhiteList filterWhiteList : filterWhiteLists) {
            if ( filterWhiteList.getUrl().equals(url) ) {
                return true;
            }
        }
        return false;
    }

}
