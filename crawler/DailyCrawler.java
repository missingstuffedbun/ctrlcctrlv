package lily.crawler;


import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.regex.Pattern;

/**
 * Description: 继承WebCrawler类，决定哪些url可以被爬以及处理爬取的页面信息
 * User: temp
 * Date: 2017/2/18.
 * Time: 22:29
 */
public class DailyCrawler extends WebCrawler {

    static Logger logger = LoggerFactory.getLogger(DailyCrawler.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");

    /**
     * 是否爬取页面（规则）
     * @param referringPage
     * @param url
     * @return
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String rUrl = url.getURL().toLowerCase();
        return !FILTERS.matcher(rUrl).matches();
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();  // 获取url
        System.out.println("URL: " + url);

        System.out.println(page.getParseData().toString());

        if (page.getParseData() instanceof HtmlParseData) {  // 判断是否是html数据
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // 强制类型转换，获取html数据对象
            String text = htmlParseData.getText();  // 获取页面纯文本（无html标签）
//            String html = htmlParseData.getHtml();  // 获取页面Html
//            Set<WebURL> links = htmlParseData.getOutgoingUrls();  // 获取页面输出链接

            System.out.println(text);


//            System.out.println("纯文本长度: " + text.length());
//            System.out.println("html长度: " + html.length());
//            System.out.println("输出链接个数: " + links.size());

        }
    }

    Timestamp getDateTime(String text) {
        return Timestamp.valueOf(text);
    }

    String getPreview(String text) {

        return null;
    }
}
