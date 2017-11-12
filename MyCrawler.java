package com.java1234.crawler;

import java.util.Set;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * �Զ�����������Ҫ�̳�WebCrawler�࣬������Щurl���Ա����Լ�������ȡ��ҳ����Ϣ
 * @author 
 *
 */
public class MyCrawler extends WebCrawler {

    Log log = Log;

	/**
	 * ����ƥ��ָ���ĺ�׺�ļ�
	 */
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");

     /**
      * ���������Ҫ�Ǿ�����Щurl������Ҫץȡ������true��ʾ��������Ҫ�ģ�����false��ʾ����������Ҫ��Url
      * ��һ������referringPage��װ�˵�ǰ��ȡ��ҳ����Ϣ
      * �ڶ�������url��װ�˵�ǰ��ȡ��ҳ��url��Ϣ
      */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();  // �õ�Сд��url
         return !FILTERS.matcher(href).matches()   // ����ƥ�䣬���˵����ǲ���Ҫ�ĺ�׺�ļ�
                && href.startsWith("http://www.java1234.com/");  // url������http://www.java1234.com/��ͷ���涨վ��
     }

     /**
      * ����������������Ҫ��ҳ�棬��������ᱻ���ã����ǿ��Ծ���Ĵ������ҳ��
      * page������װ������ҳ����Ϣ
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();  // ��ȡurl
         System.out.println("URL: " + url);

         if (page.getParseData() instanceof HtmlParseData) {  // �ж��Ƿ���html����
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // ǿ������ת������ȡhtml���ݶ���
             String text = htmlParseData.getText();  // ��ȡҳ�洿�ı�����html��ǩ��
             String html = htmlParseData.getHtml();  // ��ȡҳ��Html
             Set<WebURL> links = htmlParseData.getOutgoingUrls();  // ��ȡҳ���������
                        
             System.out.println("���ı�����: " + text.length());
             System.out.println("html����: " + html.length());
             System.out.println("������Ӹ���: " + links.size());
         }
    }
}