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

    public static Account account(String type) {
        try {
            init();
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
            init();
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
            init();
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
            init();
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
            init();
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
            init();
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
            init();
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
}
