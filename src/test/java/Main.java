
import com.glacier.earthquake.monitor.browser.util.UserUtils;
import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.SpiderInfoMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.crawler.module.baidu_search.BaiduSearchCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.baidu_search.BaiduSearchDownloader;
import com.glacier.earthquake.monitor.server.crawler.module.bbs_search.BBSCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.bing_search.BingCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.tieba_search.TiebaCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.weibo_sina.WeiboCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.weixin_search.WeixinCrawler;
import com.glacier.earthquake.monitor.server.pojo.*;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import com.glacier.earthquake.monitor.server.util.PublicSentimentUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Data2Object.truncateDisaster();
    }

}
