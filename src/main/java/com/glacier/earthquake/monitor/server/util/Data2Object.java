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

    public static Account account(String type) {
        init();
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
        }finally {
            destory();
        }
        return null;
    }

    public static List<FilterDisaster> filterRulesDisaster() {
        init();
        try {
            List<FilterDisaster> filterRules = mapper.getFilterRulesDisaster();
            session.commit();
            return filterRules;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static List<FilterPublicSentiment> filterRulesPubSentiment() {
        init();
        try {
            List<FilterPublicSentiment> filterRules = mapper.getFilterRulesPubSentiment();
            session.commit();
            return filterRules;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static List<FilterWhiteList> filterRulesWhiteList() {
        init();
        try {
            List<FilterWhiteList> filterRules = mapper.getFilterRulesWhiteList();
            session.commit();
            return filterRules;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static User getUserInfoByEmail(String email) {
        init();
        try {
            User user = mapper.getUserInfoByEmail(email);
            System.out.println("mapper = " + user);
            session.commit();
            System.out.println("mapper = " + user);
            return user;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static User getUserInfoByMobile(String mobile) {
        init();
        try {
            User user = mapper.getUserInfoByMobile(mobile);
            System.out.println("mapper = " + user);
            session.commit();
            System.out.println("mapper = " + user);
            return user;
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static User getUserInfoByUID(int uid) {
        init();
        try {
            User user = mapper.getUserInfoByUID(uid);
            session.commit();
            return user;
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static User checkPassword(User user) {
        init();
        try {
            user = mapper.checkPassword(user);
            session.commit();
            return user;
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static boolean isExistUser(User user) {
        init();
        try {
            user = mapper.isExistUser(user);
            if (user != null) {
                return true;
            }
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return false;
    }

    public static List<SpiderInfo> getSpiderInfoList() {
        init();
        try {
            List<SpiderInfo> spiderInfos = mapper.getSpiderInfoList();
            session.commit();
            return spiderInfos;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static List<SpiderInfo> getSpiderInfos_Type(int type) {
        init();
        try {
            List<SpiderInfo> spiderInfos = mapper.getSpiderInfos_Type(type);
            session.commit();
            return spiderInfos;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static List<SpiderInfo> getSpiderInfos_TypeAndStatus(SpiderInfo spiderInfo) {
        init();
        try {
            List<SpiderInfo> spiderInfos = mapper.getSpiderInfoList();
            session.commit();
            return spiderInfos;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

    public static List<User> getUserList() {
        init();
        try {
            List<User> userList = mapper.getUserList();
            session.commit();
            return userList;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }finally {
            destory();
        }
        return null;
    }

}
