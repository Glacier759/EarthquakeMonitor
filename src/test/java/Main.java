
import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
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
        UserMonitor userMonitor = new UserMonitor(user);
        System.out.println("login success.");
        FilterRuleMonitor filterRuleMonitor = userMonitor.getFilterRuleMonitor();
        System.out.println("Filter Disaster.");
        for ( FilterDisaster filterDisaster : filterRuleMonitor.getRuleDisasterList() ) {
            System.out.println(filterDisaster);
        }
        System.out.println("Filter White List");
        for ( FilterWhiteList filterWhiteList : filterRuleMonitor.getRuleWhiteLists() ) {
            System.out.println(filterWhiteList);
        }
        System.out.println("Filter Public Sentiment.");
        for ( FilterPublicSentiment publicSentiment : filterRuleMonitor.getRulePubSentimentList() ) {
            System.out.println(publicSentiment);
        }
    }

}
