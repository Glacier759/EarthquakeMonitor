
import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.SpiderInfoMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.crawler.module.weibo_sina.Crawler;
import com.glacier.earthquake.monitor.server.pojo.*;
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
        UserMonitor userMonitor = new UserMonitor(user);

        SpiderInfoMonitor spiderInfoMonitor = userMonitor.getSpiderInfoMonitor();
        for (SpiderInfo spiderInfo : spiderInfoMonitor.getSpiderInfos_Type(SpiderInfo.FILTER_DISASTER)) {
            System.out.println(spiderInfo.getId() + "[]" + spiderInfo.getStatus() + "[]" + spiderInfo.getTitle());
        }
    }

}
