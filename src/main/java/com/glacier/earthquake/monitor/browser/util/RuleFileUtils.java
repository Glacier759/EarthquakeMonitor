package com.glacier.earthquake.monitor.browser.util;

import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glacier on 15-5-22.
 */
public class RuleFileUtils {

    private static Logger logger = Logger.getLogger(RuleFileUtils.class.getName());

    public List<FilterDisaster> getFilterDisaster(File file) {
        try {
            List<String> fileList = FileUtils.readLines(file);
            return getFilterDisaster(fileList);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public List<FilterDisaster> getFilterDisaster(List<String> fileList) {
        List<FilterDisaster> filterList = new ArrayList<FilterDisaster>();
        for ( String filter : fileList ) {
            try {
                FilterDisaster filterDisaster = new FilterDisaster();
                filterDisaster.setFilterRule(filter);
                filterList.add(filterDisaster);
            }catch (Exception e) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(baos));
                logger.error(baos.toString());
            }
        }
        return filterList;
    }

    public List<FilterPublicSentiment> getFilterPubSentiment(File file) {
        try {
            List<String> fileList = FileUtils.readLines(file);
            return getFilterPubSentiment(fileList);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public List<FilterPublicSentiment> getFilterPubSentiment(List<String> fileList) {
        List<FilterPublicSentiment> filterList = new ArrayList<FilterPublicSentiment>();
        try {
            for ( String filter : fileList ) {
                try {
                    String[] filters = filter.split("\t");
                    FilterPublicSentiment filterPublicSentiment = new FilterPublicSentiment();
                    filterPublicSentiment.setName(filters[0]);
                    filterPublicSentiment.setMatcher(filters[1]);
                    filterPublicSentiment.setUnexist(filters[2]);
                    filterList.add(filterPublicSentiment);
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                }
            }
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return filterList;
    }

    public List<FilterWhiteList> getFilterWhiteList(File file) {
        try {
            List<String> fileList = FileUtils.readLines(file);
            return getFilterWhiteList(fileList);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public List<FilterWhiteList> getFilterWhiteList(List<String> fileList) {
        List<FilterWhiteList> filterList = new ArrayList<FilterWhiteList>();
        try {
            for ( String filter : fileList ) {
                try {
                    FilterWhiteList filterWhiteList = new FilterWhiteList();
                    filterWhiteList.setUrl(filter);
                    filterList.add(filterWhiteList);
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                }
            }
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return filterList;
    }

    public List<String> insertFilterDisaster(List<FilterDisaster> filterList) {
        List<String> errorFilter = new ArrayList<String>();
        try {
            for ( FilterDisaster filterDisaster : filterList ) {
                try {
                    Object2Data.addFilterRulesDisaster(filterDisaster);
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                    errorFilter.add(filterDisaster.toString());
                }
            }
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return errorFilter;
    }

    public List<String> insertFilterPublicSentiment(List<FilterPublicSentiment> filterList) {
        List<String> errorFilter = new ArrayList<String>();
        try {
            for ( FilterPublicSentiment filterPublicSentiment : filterList ) {
                try {
                    Object2Data.addFilterRulesPubSentiment(filterPublicSentiment);
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                    errorFilter.add(filterPublicSentiment.toString());
                }
            }
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return errorFilter;
    }

    public List<String> insertFilterWhiteList(List<FilterWhiteList> filterList) {
        List<String> errorFilter = new ArrayList<String>();
        try {
            for ( FilterWhiteList filterWhiteList : filterList ) {
                try {
                    Object2Data.addFilterRulesWhite(filterWhiteList);
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                    errorFilter.add(filterWhiteList.toString());
                }
            }
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return errorFilter;
    }
}
