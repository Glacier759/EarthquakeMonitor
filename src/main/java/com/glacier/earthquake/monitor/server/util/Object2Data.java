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

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    private static void init() {
        try {
            session = sessionFactory.openSession();
            mapper = session.getMapper(MysqlOperation.class);
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    private static void destory() {
        session.close();
        mapper = null;
    }

    public static void setFilterWhiteList(FilterWhiteList filterWhiteList) {
        init();
        try {
            mapper.setFilterRulesWhite(filterWhiteList);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void delFilterWhiteList(FilterWhiteList filterWhiteList) {
        init();
        try {
            mapper.delFilterRulesWhite(filterWhiteList);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void addFilterRulesWhite(String url) {
        FilterWhiteList filterWhiteList = new FilterWhiteList();
        filterWhiteList.setUrl(url);
        addFilterRulesWhite(filterWhiteList);
    }

    public static void addFilterRulesWhite(FilterWhiteList filterWhiteList) {
        init();
        try {
            mapper.addFilterRulesWhite(filterWhiteList);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void setFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment) {
        init();
        try {
            mapper.setFilterRulesPubSentiment(filterRulesPubSentiment);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void delFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment) {
        init();
        try {
            mapper.delFilterRulesPubSentiment(filterRulesPubSentiment);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void addFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment) {
        init();
        try {
            mapper.addFilterRulesPubSentiment(filterRulesPubSentiment);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void setFilterRulesDisaster(FilterDisaster filterRulesDisaster) {
        init();
        try {
            mapper.setFilterRulesDisaster(filterRulesDisaster);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void delFilterRulesDisaster(FilterDisaster filterRulesDisaster) {
        init();
        try {
            mapper.delFilterRulesDisaster(filterRulesDisaster);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void addFilterRulesDisaster(FilterDisaster filterRulesDisaster) {
        init();
        try {
            mapper.addFilterRulesDisaster(filterRulesDisaster);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void modifyUserInfo(User user) {
        init();
        try {
            mapper.modifyUserInfo(user);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void addUser(User user) {
        init();
        try {
            mapper.addUser(user);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void delUser(User user) {
        init();
        try {
            mapper.delUser(user);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void approvedThrough(SpiderInfo spiderInfo) {
        init();
        try {
            mapper.approvedThrough(spiderInfo);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void insertSpiderInfo(SpiderInfo spiderInfo) {
        init();
        try {
            mapper.insertSpiderInfo(spiderInfo);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

    public static void deleteSpiderInfo(SpiderInfo spiderInfo) {
        init();
        try {
            mapper.deleteSpiderInfo(spiderInfo);
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
    }

}
