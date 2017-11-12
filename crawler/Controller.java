package lily.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Description:
 * User: temp
 * Date: 2017/2/18.
 * Time: 22:06
 */
public class Controller {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "C:\\Users\\temp\\Desktop\\smap4ever\\log"; // 定义爬虫数据存储位置
        int numberOfCrawlers = 4; // 定义爬虫（线程）数量

        // 定义爬虫配置
        CrawlConfig config = new CrawlConfig();
        config.setMaxDepthOfCrawling(1); // 设置爬取深度
        config.setMaxPagesToFetch(100); // 设置最大爬去页面数量

        config.setCrawlStorageFolder(crawlStorageFolder); // 设置爬虫文件存储位置
        PageFetcher pageFetcher = new PageFetcher(config); // 实例化页面获取器
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // 实例化爬虫机器人配置 比如可以设置 user-agent

        // 实例化爬虫机器人对目标服务器的配置(对robots.txt规范的实现)
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        // 实例化爬虫控制器
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        // 配置爬虫种子页面（多个）

        controller.addSeed("http://www.tv-asahi.co.jp/ss/index_top.html");
        controller.addSeed("http://www.fujitv.co.jp/ojamap/");

        // 启动爬虫
        controller.start(DailyCrawler.class, numberOfCrawlers);
    }
}
