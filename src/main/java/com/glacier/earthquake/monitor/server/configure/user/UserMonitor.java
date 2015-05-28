package com.glacier.earthquake.monitor.server.configure.user;

import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by glacier on 15-5-2.
 */
public class UserMonitor {

    public static Logger logger = Logger.getLogger(UserMonitor.class.getName());

    public static final int USER_ROOT = 2;
    public static final int USER_ADMINISTATOR = 1;
    public static final int USER_ORDINARY = 0;

    /**
     * 为每个当前登录用户实例化一个UserMonitor类，user表示当前登录用户的信息
     * */
    private User user;

    public static UserMonitor getUserMonitor(HttpServletRequest request) {
        return new UserMonitor((User)request.getSession().getAttribute("login_user"));
    }

    public static UserMonitor getUserMonitor(User user) {
        return new UserMonitor(user);
    }

    public UserMonitor(User user) {
        this.user = user;
    }

    /**
     * 判断用户情况，参数传入email或mobile都可以，查找到则返回该用户的一个pojo
     * */
    public static User checkPassword(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setMobile(email);
        user.setPassword(password);
        user = Data2Object.checkPassword(user);
        return user;
    }

    /**
     * 修改用户资料，依据登陆用户的权限，管理员可以修改所有用户的资料，非管理员只能修改自己的资料
     * @param modifyUser 参数为修改后的User实例
     * @return boolean true表示修改成功，false表示修改失败
     * */
    public boolean modifyUserInfo( User modifyUser ) {
        if ( user.getPrivilege() == USER_ORDINARY ) {
            if ( user.getUid() == modifyUser.getUid() ) {
                Object2Data.modifyUserInfo(modifyUser);
                this.user = modifyUser;
                return true;
            }
        } else if ( user.getPrivilege() >= USER_ADMINISTATOR ) {
            Object2Data.modifyUserInfo(modifyUser);
            if ( user.getUid() == modifyUser.getUid() ) {
                this.user = modifyUser;
            }
            return true;
        }
        return false;
    }

    /**
     * 添加用户，只有管理员有权限
     * */
    public boolean addUser( User addUser ) {
        if ( user.getPrivilege() >= USER_ADMINISTATOR ) {
            if ( !isExistUser(addUser) ) {  //如果需要添加的用户不存在才能添加
                Object2Data.addUser(addUser);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除用户，只有管理员有权限
     * */
    public boolean delUser( User delUser ) {
        if ( user.getPrivilege() >= USER_ADMINISTATOR ) {
            if ( isExistUser(delUser) ) {   //需要删除的用户存在才能删除
                Object2Data.delUser(delUser);
                return true;
            }
        }
        return false;
    }

    public boolean isAdministor() {
        if ( user.getPrivilege() >= USER_ADMINISTATOR ) {
            return true;
        }
        return false;
    }

    public boolean changePassword(User changeUser) {
        if ( user.getPrivilege() == USER_ORDINARY ) {
            if ( user.getUid() == changeUser.getUid() ) {
                Object2Data.changePassword(changeUser);
                this.user = changeUser;
                return true;
            }
        } else if ( user.getPrivilege() >= USER_ADMINISTATOR ) {
            Object2Data.changePassword(changeUser);
            if ( user.getUid() == changeUser.getUid() ) {
                this.user = changeUser;
            }
            return true;
        }
        return false;
    }

    public List<User> getUserList() {
        if ( user.getPrivilege() >= USER_ADMINISTATOR ) {
            return Data2Object.getUserList();
        }
        return null;
    }

    /**
     * 判断用户是否存在
     * */
    public boolean isExistUser( User existUser ) {
        return Data2Object.isExistUser(existUser);
    }

    public boolean hasFullInfo() {
        if ( user.getEmail() != null && user.getMobile() != null && user.getNickname() != null
                && user.getRealname() !=null && user.getPosition() != null && user.getWorkplace() != null
                && user.getQqnumber() != null ) {
            return true;
        }
        return false;
    }

    public static User getUserInfoByMobile(String mobile) {
        logger.info("[info] - mobile " + mobile);
        mobile = mobile.replace("\"", "");
        return Data2Object.getUserInfoByMobile(mobile);
    }

    public static User getUserInfoByEmail(String email) {
        logger.info("[info] - email " + email);
        email = email.replace("\"", "");
        return Data2Object.getUserInfoByEmail(email);
    }

    public static User getUserInfoByUID(int uid) {
        return Data2Object.getUserInfoByUID(uid);
    }

    public static void registUser(User user) {
        Object2Data.addUser(user);
    }

    public FilterRuleMonitor getFilterRuleMonitor() {
        return new FilterRuleMonitor(this.user);
    }

    public SpiderInfoMonitor getSpiderInfoMonitor() {
        return new SpiderInfoMonitor(this.user);
    }

    public static void setConfigStatusByType(int type, int status) {
        SystemConfig config = new SystemConfig(type, status);
        Object2Data.setConfigStatusByType(config);
    }

    public static int getConfigStatusByType(int type) {
        return Data2Object.getConfigStatusByType(type).getStatus();
    }

}
