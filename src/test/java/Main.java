
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

        User user = Data2Object.getUserInfoByEmail("glacier@xiyoulinux.org");
        System.out.println(user);
        user = Data2Object.getUserInfoByMobile("13289212979");
        System.out.println(user);
        user = Data2Object.getUserInfoByUID(1);
        System.out.println(user);

    }

}
