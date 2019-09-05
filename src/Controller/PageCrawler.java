package Controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageCrawler {
	private static final int MAX_DEPTH = 3;
	private HashSet<String> links;
	private static int page=1;
	public PageCrawler() {
		links = new HashSet<>();
	}

	public void getPageLinks(String URL, int depth) {
		if ((!links.contains(URL) && (depth <= MAX_DEPTH))) {
			
			try {
				if (!URL.contains(".htm"))
					return;
				if(page >=2000)
				return;
				
				System.out.println(">> Depth: " + depth + " [" + URL + "]");
				links.add(URL);
				page++;
				Document document = Jsoup.connect(URL).get();
				Elements linksOnPage = document.select("a[href]");

				depth++;
				for (Element page : linksOnPage) {
					getPageLinks(page.attr("abs:href"), depth);
				}
			} catch (IOException e) {
			}
		}
	}

	public void printLinks() {
		
		Iterator<String> itr = links.iterator();
		HTMLtoText ht=new HTMLtoText();
		while(itr.hasNext()){
			  String filename=itr.next();
			ht.htmlToText(filename);
		}
		
	}
	
	
	
	public static void main(String[] args) {

		String urlPattern_a = "http(s)?://([\\w-]+.)+[\\w-]+(/[\\w- ./?%&=])?";

		Pattern url = Pattern.compile(urlPattern_a);
		Matcher mURL;
		String strURL = "";
		PageCrawler objCrawler = new PageCrawler();
		File fileEntry = new File("C:\\Users\\MEERA\\Documents\\MAC\\W3C Web Pages\\W3C Web Pages\\About W3C Standards.htm");

		try {

			Document doc = Jsoup.parse(fileEntry, "UTF-8");

			String html = doc.html();
			
			mURL = url.matcher(html);

			while (mURL.find()) {
				strURL = mURL.group(0);
				objCrawler.getPageLinks(strURL, 1);
			}
			objCrawler.printLinks();
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}