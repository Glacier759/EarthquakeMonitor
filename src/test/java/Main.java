
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;

import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public class Main {

    public static void main(String[] args) {

        User user = UserMonitor.checkPassword("glacier@xiyoulinux.org", "private info");
        System.out.println(user);
        UserMonitor userMonitor = new UserMonitor(user);
        user.setMobile("13289212979");
        System.out.println(user);
        userMonitor.modifyUserInfo(user);
        User addUser = new User("戴着眼镜的马甲", "spider@xiyoulinux.org", "133333333333", "password_lalala");
        userMonitor.addUser(addUser);
        System.out.println(userMonitor.isExistUser(addUser));
        userMonitor.delUser(addUser);
        System.out.println(userMonitor.isExistUser(addUser));
    }

}
