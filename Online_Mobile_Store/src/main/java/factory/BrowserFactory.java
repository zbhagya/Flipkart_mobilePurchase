package factory;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {


	static WebDriver driver;
	static JavascriptExecutor js;

	public static WebDriver getBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", DataProviderFactory.getConfig().getIEPath());
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", DataProviderFactory.getConfig().getChromePath());
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;

	}
	//Scroll down
	public static void scrolldownbypixel() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
	}
	//Scroll up
	public static void scrollupbypixel() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-400)");

	}
	//scroll down to visibility of element 
	public static void scrollDownVisisbilityOfElement(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView();", element);
	}


	public static void handleWindows() {

		String parentwindow=driver.getWindowHandle();
		Set<String> handles=driver.getWindowHandles();

		for (String handle : handles) {

			if(!parentwindow.equals(handles)) {
				driver.switchTo().window(handle);
			}
		}
		driver.switchTo().window(parentwindow);
	}

	public static void closeBrowser(WebDriver localDriver) {
		driver.quit();
	}

	
}
