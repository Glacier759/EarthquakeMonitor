
import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.SpiderInfoMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.crawler.module.baidu_search.BaiduSearchCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.baidu_search.BaiduSearchDownloader;
import com.glacier.earthquake.monitor.server.crawler.module.bing_search.BingCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.tieba_search.TiebaCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.weibo_sina.WeiboCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.weixin_search.WeixinCrawler;
import com.glacier.earthquake.monitor.server.pojo.*;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public class Main {

    public static void main(String[] args) {

        List<Crawler> crawlers = new ArrayList<Crawler>();
        crawlers.add(new WeixinCrawler());
        for ( Crawler crawler : crawlers ) {
            crawler.start();
        }
//        String url = "http://www.baidu.com/link?url=PSHs6jPu3YNiqbZkVYSHEP8QuouimFH2MW48VoVVKtWQimFWdawH1HNNSZU0f_X0";
//        DefaultHttpClient defaultHttpClient = MyHttpConnectionManager.getNewHttpClient();
//        BaiduSearchDownloader.setClient(defaultHttpClient);
//        MyHttpConnectionManager.setHandleRedirect(defaultHttpClient, false);
//
//        Document document = BaiduSearchDownloader.document(url, Downloader.HTTP_GET);
//        System.out.println(document);
    }

}
