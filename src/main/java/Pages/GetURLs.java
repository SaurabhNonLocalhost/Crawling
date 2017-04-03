<<<<<<< HEAD
package Pages;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class GetURLs {
	WebDriver driver;
	ExcelManipulation excelobj;


	
	public GetURLs() {
		System.setProperty("webdriver.chrome.driver", "D:\\Software Testing\\chromedriver.exe");
		
		//System.setProperty("webdriver.ie.driver", "D:\\Software Testing\\IEDriverServer.exe");
		//System.setProperty("webdriver.gecko.driver", "C://Users//Saurabh//Desktop//Personal//ff//geckodriver.exe");
		driver=new ChromeDriver();
		//driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		excelobj = new ExcelManipulation();

	}

	public List<String> extractLinks(String url) throws IOException {

		ArrayList<String> result = new ArrayList<String>();
		result.clear();

		// Document doc = Jsoup.connect(url).get();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

		// Elements links = doc.select("a[href]");
		// Elements media = doc.select("[src]");
		// Elements imports = doc.select("link[href]");

		// href ...
		// if (!links.isEmpty()) {
		// for (Element link : links) {
		// result.add(link.attr("abs:href"));
		// }

		// }

		// img ...
		/*
		 * for (Element src : media) { result.add(src.attr("abs:src")); }
		 * 
		 * // js, css, ... for (Element link : imports) {
		 * result.add(link.attr("abs:href")); }
		 */
		List<WebElement> myLinks = null;
		boolean done = false;
		int attempts = 0;
		while (attempts < 5) {
			try {
				myLinks = driver.findElements(By.tagName("a"));
				if (!myLinks.isEmpty()) {
					for (WebElement link : myLinks) {
						//System.out.println("Current link href attribute is: "+link.getAttribute("href"));
						if ((link.getAttribute("href"))!=null && (link.getAttribute("href")).contains("learningexchange") )
							result.add(link.getAttribute("href"));
					}
				}
				done = true;
				break;
			} catch (Exception e) {
				System.out.println("Exception caught is : -");
				e.printStackTrace();
				result.clear();
			}
			attempts++;
		}
		return result;

	}


	public void navigateAndAdd() throws Exception {
		try {
			//GetURLs obj = new GetURLs();
			// ExcelManipulation excelobj = new ExcelManipulation();
			String parentURL = "https://learningexchange.ptc.com/";
			List<String> mainList = extractLinks(parentURL);
			if (!mainList.isEmpty()) {
				// ExcelManipulation excelobj2 = new ExcelManipulation();
				List<String> sub = new ArrayList<String>();
				//ListIterator<String> parentIterator = mainList.listIterator();
				// ListIterator<String> parentIterator2 =
				// mainList.listIterator();
				int i;

				for (int j = 0; j < mainList.size(); j++) {
					String current = mainList.get(j);
					System.out.println("Current list of elements are ");
					for (int k = 0; k < mainList.size(); k++) {
						//int count=k+1;
						System.out.println( mainList.get(k));
						//excelobj.excelWrite(mainList.get(k));
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
								excelobj.excelWrite(child);

								// parentIterator.add(child);
								mainList.add(mainList.size() - 1, child);

							}
						}
					}
					System.out.println("CRAWLING IS OVER FOR CURRENT PARENT LINK: " + current);

				}
				System.out.println("CRAWLING IS OVER FOR ALL THE LINKS");
				// testOtherThan200();

			}
		}

		catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}


	public void testOtherThan200() {
		try {

			ArrayList<String> URLsToVerify = excelobj.excelRead();
			// String URLToVerify = "https://mail.google.com";

			for (int i = 0; i < URLsToVerify.size(); i++) {
				String current = URLsToVerify.get(i);
				URL url = new URL(current);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				// conn.setRequestProperty(“Accept”, “application/json”);l

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("HTTP error code:" + conn.getResponseCode());
				} else {

					System.out.println("The response code is" + conn.getResponseCode());

					String currentDir = System.getProperty("user.dir");

					File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(src, new File(currentDir + "\\screenshots\\" + Math.random() + ".png"));

				}

				driver.quit();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			driver.quit();
			e.printStackTrace();
		}
		

	}
	
	public static void main(String[] args) throws Exception
	{
		GetURLs obj = new GetURLs();
		obj.navigateAndAdd();
		//obj.testOtherThan200();
		//obj.last();
	}
	
	
	public void last()
	{
		try {
			excelobj.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
=======
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
>>>>>>> origin/master
