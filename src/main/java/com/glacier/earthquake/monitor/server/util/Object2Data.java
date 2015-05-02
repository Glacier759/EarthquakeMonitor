package com.glacier.earthquake.monitor.server.util;

import com.glacier.earthquake.monitor.server.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;

/**
 * Created by glacier on 15-5-1.
 */
public class Object2Data {

    private static Logger logger = Logger.getLogger(Object2Data.class.getName());
    private static Reader reader;
    private static SqlSessionFactory sessionFactory;
    private static SqlSession session;
    private static MysqlOperation mapper;

    private static void init() {
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            mapper = session.getMapper(MysqlOperation.class);
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void setFilterWhiteList(FilterWhiteList filterWhiteList) {
        try {
            init();
            mapper.setFilterRulesWhite(filterWhiteList);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void delFilterWhiteList(FilterWhiteList filterWhiteList) {
        try {
            init();
            mapper.delFilterRulesWhite(filterWhiteList);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void addFilterRulesWhite(String url) {
        FilterWhiteList filterWhiteList = new FilterWhiteList();
        filterWhiteList.setUrl(url);
        addFilterRulesWhite(filterWhiteList);
    }

    public static void addFilterRulesWhite(FilterWhiteList filterWhiteList) {
        try {
            init();
            mapper.addFilterRulesWhite(filterWhiteList);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void setFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment) {
        try {
            init();
            mapper.setFilterRulesPubSentiment(filterRulesPubSentiment);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void delFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment) {
        try {
            init();
            mapper.delFilterRulesPubSentiment(filterRulesPubSentiment);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void addFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment) {
        try {
            init();
            mapper.addFilterRulesPubSentiment(filterRulesPubSentiment);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void setFilterRulesDisaster(FilterDisaster filterRulesDisaster) {
        try {
            init();
            mapper.setFilterRulesDisaster(filterRulesDisaster);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void delFilterRulesDisaster(FilterDisaster filterRulesDisaster) {
        try {
            init();
            mapper.delFilterRulesDisaster(filterRulesDisaster);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void addFilterRulesDisaster(FilterDisaster filterRulesDisaster) {
        try {
            init();
            mapper.addFilterRulesDisaster(filterRulesDisaster);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void modifyUserInfo(User user) {
        try {
            init();
            mapper.modifyUserInfo(user);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void addUser(User user) {
        try {
            init();
            mapper.addUser(user);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void delUser(User user) {
        try {
            init();
            mapper.delUser(user);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void approvedThrough(SpiderInfo spiderInfo) {
        try {
            init();
            mapper.approvedThrough(spiderInfo);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void insertSpiderInfo(SpiderInfo spiderInfo) {
        try {
            init();
            mapper.insertSpiderInfo(spiderInfo);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    public static void deleteSpiderInfo(SpiderInfo spiderInfo) {
        try {
            init();
            mapper.deleteSpiderInfo(spiderInfo);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

}
