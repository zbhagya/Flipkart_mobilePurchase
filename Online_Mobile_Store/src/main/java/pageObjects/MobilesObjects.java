package pageObjects;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import dataProvider.Excel_DataProvider;
import factory.BrowserFactory;

public class MobilesObjects 

{
	public WebDriver driver;

	public MobilesObjects(WebDriver driver)
	{
		driver=this.driver;
	}

	@FindBy(xpath="//div[@class='O8ZS_U']/input") WebElement searchbox ;
	@FindBy(xpath="//select[@class='fPjUPw']/option[@value='Min']") WebElement minrange;
	@FindBy(xpath="//div[@class='_1YoBfV']//select[@class='fPjUPw']/option[@value='10000']")  WebElement price ;
	@FindBy(xpath="//div[@class='bhgxx2 col-12-12']/div/section[4]/div[2]/div//div[4]") WebElement selectram ;
	@FindBy(xpath = "//div[@class='bhgxx2 col-12-12']/div/section[16]/div/div/div[3]") WebElement selectbrand;

	public void clickOnSearchBox() {
		searchbox.click();
	}
	public void searchMobile() throws IOException {

		searchbox.sendKeys(Excel_DataProvider.mobilename);
	}

	public void pressKeyDown()
	{
		searchbox.sendKeys(Keys.ARROW_DOWN);
		searchbox.sendKeys(Keys.ENTER);
	}
	public WebElement selectMinRange(String minimumrange) throws InterruptedException {
		Assert.assertTrue(minrange.isSelected());
		Thread.sleep(2000);
		return minrange;
	}

	public WebElement selectPrice(String mobileprice) throws InterruptedException
	{
		BrowserFactory.scrolldownbypixel();
		price.click();
		Thread.sleep(2000);
		return price;
	}

	public WebElement selectRam( ) throws Exception {
		BrowserFactory.scrolldownbypixel();
		//BrowserFactory.scrollDownVisisbilityOfElement(selectram);
		selectram.click();
		return selectram;
	}
	
	public WebElement selectProcessorBrand(String band)
	{
		BrowserFactory.scrolldownbypixel();
		selectbrand.click();
		return selectbrand;
		
	}
	


	
}
