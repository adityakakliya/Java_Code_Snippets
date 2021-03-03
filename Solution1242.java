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
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Thread t = new WebCrawler(getHostname(startUrl), startUrl, set, htmlParser);
         t.start();                
        try {
            t.join();   
        } catch(InterruptedException e) {
            
        }  
        return new ArrayList<>(set);
    }
    class WebCrawler extends Thread {
    String host;
    String startUrl;
    Set<String> visited;
    HtmlParser htmlParser;
    
    WebCrawler (String host, String startUrl, Set<String> visited, HtmlParser htmlParser) {
        this.host = host;
        this.startUrl = startUrl;
        this.visited = visited;
        this.htmlParser = htmlParser;
    }
    
    public void run() {
        if (!visited.add(startUrl)) return;

        List<Thread> threads = new ArrayList<>();
        for (String childUrl : htmlParser.getUrls(startUrl)) {
            if (!visited.contains(childUrl) && getHostname(childUrl).equals(host)) {
                Thread t = new WebCrawler(host, childUrl, visited, htmlParser);
                threads.add(t);
                t.start();
            }
        }

            for (Thread t : threads) {
                try {t.join();} catch(Exception e) {};
            }
        }
    }
    
        public static String getHostname(String url) {
        String hostname = "";
        try {
            hostname = new URL(url).getHost();
        } catch(Exception e) {            
        }
        return hostname;
    }
}
