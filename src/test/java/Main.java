
import com.glacier.earthquake.monitor.server.util.Data2Object;

import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public class Main {

    public static void main(String[] args) {
        List<String> filterRules = Data2Object.filterRules();
        System.out.println(filterRules.size());
        for ( String rule : filterRules ) {
            System.out.println(rule);
        }
    }

}
