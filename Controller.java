package com.java1234.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * ���������
 * @author 
 *
 */
public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "c:/crawl"; // �����������ݴ洢λ��
        int numberOfCrawlers = 7; // ����7�����棬Ҳ����7���߳�

        CrawlConfig config = new CrawlConfig(); // ������������
        config.setCrawlStorageFolder(crawlStorageFolder); // ���������ļ��洢λ��

        /*
         * ʵ�������������
         */
        PageFetcher pageFetcher = new PageFetcher(config); // ʵ����ҳ���ȡ��
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // ʵ����������������� ����������� user-agent
        
        // ʵ������������˶�Ŀ������������ã�ÿ����վ����һ��robots.txt�ļ� �涨�˸���վ��Щҳ�����������Щҳ���ֹ���������Ƕ�robots.txt�淶��ʵ��
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher); 
        // ʵ�������������
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /**
         * ������������ҳ�棬���ǹ涨�Ĵ����￪ʼ�����������ö������ҳ��
         */
        controller.addSeed("http://www.java1234.com/");
        controller.addSeed("http://www.java1234.com/a/kaiyuan/");
        controller.addSeed("http://www.java1234.com/a/bysj/");

        /**
         * �������棬����Ӵ˿̿�ʼִ���������񣬸�����������
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}