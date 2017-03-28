package Pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetURLs {
	WebDriver driver;
	ExcelManipulation excelobj;

	public GetURLs() {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		excelobj = new ExcelManipulation();

	}

	public List<String> extractLinks(String url) throws IOException {

		ArrayList<String> result = new ArrayList<String>();
		result.clear();

		Document doc = Jsoup.connect(url).get();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		// href ...
		if (!links.isEmpty()) {
			for (Element link : links) {
				result.add(link.attr("abs:href"));
			}

		}

		// img ...
		/*
		 * for (Element src : media) { result.add(src.attr("abs:src")); }
		 * 
		 * // js, css, ... for (Element link : imports) {
		 * result.add(link.attr("abs:href")); }
		 */
		return result;

	}

	public static void main(String[] args) throws Exception {
		GetURLs obj = new GetURLs();
		// ExcelManipulation excelobj = new ExcelManipulation();
		String parentURL = "https://learningexchange.ptc.com";
		List<String> links = obj.extractLinks(parentURL);
		if (!links.isEmpty()) {

			obj.navigateAndAdd(links);
		}

	}

	public void navigateAndAdd(List<String> mainList) throws Exception {
		try {
			// ExcelManipulation excelobj2 = new ExcelManipulation();
			List<String> sub = new ArrayList<String>();
			ListIterator<String> parentIterator = mainList.listIterator();
			// ListIterator<String> parentIterator2 = mainList.listIterator();
			int i;

			for (int j = 0; j < mainList.size(); j++) {
				String current = mainList.get(j);
				excelobj.excelWrite("Current list of elements are ");
				for (int k = 0; k < mainList.size(); k++) {

					System.out.println("<" + (k + 1) + ">" + mainList.get(k));
					excelobj.excelWrite("<" + (k + 1) + ">" + mainList.get(k));
				}

				if (current.contains("learningexchange")) {

					System.out.println("Traversing Parent link" + current);
					excelobj.excelWrite("------Parent link is----- " + current);
					sub = extractLinks(current);

					ListIterator<String> childIterator = sub.listIterator();
					while (childIterator.hasNext()) {
						String child = childIterator.next();
						if (!mainList.contains(child) && child.contains("learningexchange")) {

							System.out.println("Adding this new link " + child);
							excelobj.excelWrite("Adding this new link " + child);

							// parentIterator.add(child);
							mainList.add(mainList.size() - 1, child);

						}
					}
				}
				System.out.println("CRAWLING IS OVER FOR CURRENT PARENT LINK: " + current);

			}
			System.out.println("CRAWLING IS OVER FOR ALL THE LINKS");
			driver.quit();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
