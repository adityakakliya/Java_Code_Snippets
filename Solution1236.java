/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

import java.net.*;
class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        WebCrawler wc = new WebCrawler (startUrl, htmlParser);
        wc.crawl(startUrl, "-1");
        return wc.result;
    }
}

class WebCrawler {
    HtmlParser htmlParser;
    Set<String> crawledUrls;
    List<String> result;
    String hostName;
    WebCrawler(String startUrl, HtmlParser htmlParser) {
        crawledUrls = new HashSet<>();
        this.htmlParser = htmlParser;
        hostName = getHostName(startUrl);
        result = new ArrayList<>();
    }
    
    public void crawl(String startUrl, String parent) {
        if (!getHostName(startUrl).equals(hostName)) return;
        if (!crawledUrls.add(startUrl)) return;
        result.add(startUrl);
        //System.out.println(startUrl + " - parent " + parent + " - hostname " + getHostName(startUrl));
        htmlParser.getUrls(startUrl).forEach(url -> crawl(url, startUrl));
    }

    public static String getHostName(String string) {
        String temp = "";
        try {
            temp = new URL(string).getHost();
            System.out.println(string + " host " + temp);
        } finally {
            return temp;
        }
    }
}
