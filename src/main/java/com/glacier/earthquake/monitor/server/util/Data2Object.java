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
import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public class Data2Object {

    private static Logger logger = Logger.getLogger(Data2Object.class.getName());
    private static Reader reader;
    private static SqlSessionFactory sessionFactory;
    private static SqlSession session;
    private static MysqlOperation mapper;

    static {
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

    public static Account account(String type) {
        try {
            Account accounts = mapper.getAccount(type);
            session.commit();
            mapper.updateAccount(accounts);
            session.commit();
            return accounts;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static List<FilterDisaster> filterRulesDisaster() {
        try {
            List<FilterDisaster> filterRules = mapper.getFilterRulesDisaster();
            session.commit();
            return filterRules;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static List<FilterPublicSentiment> filterRulesPubSentiment() {
        try {
            List<FilterPublicSentiment> filterRules = mapper.getFilterRulesPubSentiment();
            session.commit();
            return filterRules;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static List<FilterWhiteList> filterRulesWhiteList() {
        try {
            List<FilterWhiteList> filterRules = mapper.getFilterRulesWhiteList();
            session.commit();
            return filterRules;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static User getUserInfoByEmail(String email) {
        try {
            User user = mapper.getUserInfoByEmail(email);
            session.commit();
            return user;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static User getUserInfoByMobile(String mobile) {
        try {
            User user = mapper.getUserInfoByMobile(mobile);
            session.commit();
            return user;
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static User getUserInfoByUID(int uid) {
        try {
            User user = mapper.getUserInfoByUID(uid);
            session.commit();
            return user;
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static User checkPassword(User user) {
        try {
            user = mapper.checkPassword(user);
            session.commit();
            return user;
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static boolean isExistUser(User user) {
        try {
            user = mapper.isExistUser(user);
            if (user != null) {
                return true;
            }
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return false;
    }

    public static List<SpiderInfo> getSpiderInfoList() {
        try {
            List<SpiderInfo> spiderInfos = mapper.getSpiderInfoList();
            session.commit();
            return spiderInfos;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static List<SpiderInfo> getSpiderInfos_Type(int type) {
        try {
            List<SpiderInfo> spiderInfos = mapper.getSpiderInfos_Type(type);
            session.commit();
            return spiderInfos;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static List<SpiderInfo> getSpiderInfos_TypeAndStatus(SpiderInfo spiderInfo) {
        try {
            List<SpiderInfo> spiderInfos = mapper.getSpiderInfoList();
            session.commit();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

}
