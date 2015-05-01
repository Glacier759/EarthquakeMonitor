
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;

import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public class Main {

    public static void main(String[] args) {
        List<FilterDisaster> filterDisasters = Data2Object.filterRulesDisaster();

        FilterDisaster disaster = new FilterDisaster();
        disaster.setFilterRule("这是一条过滤规则");
        System.out.println(disaster);
        Object2Data.addFilterRulesDisaster(disaster);

        for ( FilterDisaster filterDisaster : filterDisasters ) {
            System.out.println(filterDisaster);
            if ( filterDisaster.getId() == 1 ) {
                filterDisaster.setFilterRule("过滤规则被修改ulead");
                Object2Data.setFilterRulesDisaster(filterDisaster);
            }
            if ( filterDisaster.getId() > 2 ) {
                Object2Data.delFilterRulesDisaster(filterDisaster);
            }
        }
    }

}
