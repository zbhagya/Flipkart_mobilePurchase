package testCases;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataProvider.Excel_DataProvider;
import factory.BrowserFactory;
import factory.DataProviderFactory;
import pageObjects.MobilesObjects;
import resources.utility;

public class purchaseMobilePhone {
	
	WebDriver driver;
	Logger Log = Logger.getLogger(MobilesObjects.class.getName());

	@BeforeTest
	public void setUp() {

		driver = BrowserFactory.getBrowser("chrome");
		Log.info("New driver instantiated");
		driver.get(DataProviderFactory.getConfig().getUrl());
		Log.info("Web application launched");
		DOMConfigurator.configure("log4j.xml");

	}
	
	@Test
	public void purchaseSamsungMobile( ) throws Exception
	{
		Excel_DataProvider.readExcelData();
		MobilesObjects obj = PageFactory.initElements(driver, MobilesObjects.class);
		obj.clickOnSearchBox();
		obj.searchMobile();
		
		Thread.sleep(2000);
		obj.pressKeyDown();
		
		Thread.sleep(2000);
		BrowserFactory.scrolldownbypixel();	
		
		obj.selectMinRange(Excel_DataProvider.range);
		
		obj.selectPrice(Excel_DataProvider.price);
		
		obj.selectRam();

		BrowserFactory.scrolldownbypixel();

		obj.selectProcessorBrand(Excel_DataProvider.brand);

		utility.getMobileList();
		System.out.println();
		
		
	}

	@AfterTest
	public void tearDown() {
		BrowserFactory.closeBrowser(driver);
		Log.info("Browser closed");

	}

	
	

}
