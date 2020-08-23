package resources;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.csvreader.CsvWriter;

public class utility {
	public static JavascriptExecutor js;
	public static WebDriver driver;
	public static WebDriverWait wait = new WebDriverWait(driver, 20);
	public static String getMobilesList = null;

	// Below method is used to get the main window handle. we will the driver as
	// parameter.


	public static void writeDataToCSVFile(String productData)
	{
		//define output file name
		String csvOutputFile = "writetest.csv";
		
		//check if file exist
		boolean isFileExist = new File(csvOutputFile).exists();
		
		try {
			
			//create a FileWriter constructor to open a file in appending mode
			CsvWriter testcases = new CsvWriter(new FileWriter(csvOutputFile, true), ',');
			
			//write header column if the file did not already exist
			if(!isFileExist)
			{
				testcases.write(getMobilesList);
				
				//end the record
				testcases.endRecord();
			}

			//end the record
			testcases.endRecord();
			//close the file
			testcases.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void getMobileList() {
		
		List<WebElement> getData=driver.findElements(By.xpath("//div[@class='bhgxx2 col-12-12']"));
		int count=getData.size();
		
		for(int i=0;i<count-2;i++) {
			String getMobilesList=getData.get(i).getText();
			System.out.println(getMobilesList);
	
		}
	}

	public static String getMainWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}
	// Below method is used to get the current window title

	public static String getCurrentWindowTitle() {
		String windowTitle = driver.getTitle();
		return windowTitle;
	}
	// Below method is used to close all the other windows except the main window.

	public static boolean closeAllOtherWindows(WebDriver driver, String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}

		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	// Below method is used to wait for the new window to be present and switch to
	// it.

	public static void waitForNewWindowAndSwitchToIt(WebDriver driver) throws InterruptedException {
		String cHandle = driver.getWindowHandle();
		String newWindowHandle = null;
		Set<String> allWindowHandles = driver.getWindowHandles();

		// Wait for 20 seconds for the new window and throw exception if not found
		for (int i = 0; i < 20; i++) {
			if (allWindowHandles.size() > 1) {
				for (String allHandlers : allWindowHandles) {
					if (!allHandlers.equals(cHandle))
						newWindowHandle = allHandlers;
				}
				driver.switchTo().window(newWindowHandle);
				break;
			} else {
				Thread.sleep(1000);
			}
		}
		if (cHandle == newWindowHandle) {
			throw new RuntimeException("Time out - No window found");
		}
	}

	public static void sendkeysJavaScript(String text, WebElement element) {

		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Sending text with  java script ");

				((JavascriptExecutor) driver).executeScript("arguments[0].value='text';", element);
			} else {
				System.out.println("Unable to perform sendkeys");
			}
		} catch (Exception e) {

			System.out.println("Unable to perform sendkeys " + e.getStackTrace());
		}
	}


	public void safeSelectCheckBoxes(int waitTime, WebElement... elements) throws Exception {
		WebElement checkElement = null;
		try {
			if (elements.length > 0) {
				for (WebElement currentElement : elements) {
					checkElement = currentElement;
					WebDriverWait wait = new WebDriverWait(driver, waitTime);
					wait.until(ExpectedConditions.elementToBeClickable(currentElement));

					WebElement checkBox = currentElement;
					if (checkBox.isSelected())
						System.out.println("CheckBox " + currentElement + " is already selected");
					else
						checkBox.click();
				}
			} else {
				System.out.println("Expected atleast one element as argument to safeSelectCheckboxes function");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + checkElement + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + checkElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to select checkbox " + e.getStackTrace());
		}
	}

	public static void waitUntilVisibilityOfElement(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void clickOnButton(WebElement element) {

		try {
			if (element.isDisplayed() || element.isEnabled())
				element.click();
			else {
				System.out.println("Element not found");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void enterTextinTexBox(WebElement element, String text) {

		try {
			if (element.isDisplayed())
				element.sendKeys(text);

			else {
				System.out.println("Element not found");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void doubleClick(WebElement element) {
		try {
			Actions action = new Actions(driver).doubleClick(element);
			action.build().perform();

			System.out.println("Double clicked the element");

		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable " + e.getStackTrace());

		}
	}

	public static void rightClick(WebElement element) {
		try {
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();

			System.out.println("Sucessfully Right clicked on the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable " + e.getStackTrace());
		}
	}

	public static void dragAndDrop(WebElement sourceElement, WebElement destinationElement) {
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				System.out.println("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + sourceElement + "or" + destinationElement
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}
	}

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) {
		try {
			// Setting clipboard with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static void select_Option_In_DropDown_ByVisibleText(WebElement element, String VisibleTextOptionToSelect) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(VisibleTextOptionToSelect);
			System.out.println(select.getFirstSelectedOption());

		} 
		catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");

		}
	}

	public static void select_Option_In_DropDown_ByIndexVal(WebElement element, WebElement i) {
		try {
			Select select = new Select(i);
			select.selectByValue("nokia");

		} 
		catch (NoSuchElementException e) {
			System.out.println("Value not find in dropdown");
		}
	}

	public static void select_Option_In_DropDown_ByValue(WebElement element, int indexVal) {
		try {
			Select select = new Select(element);
			select.selectByIndex(indexVal);
		} catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");
		}
	}

	public void Select_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected()) {
				System.out.println("Checkbox: " + element + "is already selected");
			} else {
				// Select the checkbox
				element.click();
			}
		} catch (Exception e) {
			System.out.println("Unable to select the checkbox: " + element);
		}

	}

	public void DeSelect_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected()) {
				// De-select the checkbox
				element.click();
			} else {
				System.out.println("Checkbox: " + element + "is already deselected");
			}
		} catch (Exception e) {
			System.out.println("Unable to deselect checkbox: " + element);
		}
	}

	// Below method is used to select the checkbox with the specified value from
	// multiple checkboxes.

	public void Select_The_CheckBox_from_List(WebElement element, String valueToSelect) {
		List<WebElement> allOptions = element.findElements(By.tagName("input"));
		for (WebElement option : allOptions) {

			System.out.println("Option value " + option.getText());
			if (valueToSelect.equals(option.getText())) {
				option.click();
				break;
			}
		}
	}

	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollToWebElement(WebDriver driver, WebElement element) {
		if (element.isEnabled() && element.isDisplayed()) {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		}

	}

	public static void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
		}
	}

	public static void switchToFrameWithIndex(int frame) {
		try {
			driver.switchTo().frame(frame);
			System.out.println("Navigated to frame with id " + frame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + frame + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with id " + frame + e.getStackTrace());
		}
	}

	public static void switchToFrameWithName(String frame) {
		try {
			driver.switchTo().frame(frame);
			System.out.println("Navigated to frame with name " + frame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + frame + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with id " + frame + e.getStackTrace());
		}
	}

	public static void switchToFrameWithWebelement(WebElement frameElement) {
		try {
			if (isElementPresent(frameElement)) {
				driver.switchTo().frame(frameElement);
				System.out.println("Navigated to frame with element " + frameElement);
			} else {
				System.out.println("Unable to navigate to frame with element " + frameElement);
			}
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frameElement + e.getStackTrace());
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element with " + frameElement + "is not attached to the page document" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with element " + frameElement + e.getStackTrace());
		}
	}

	//we need to first switch to the parent frame and then we need to switch to the child frame. below is the code snippet to work with multiple frames.

	public static void switchToFrame(String ParentFrame, String ChildFrame) {
		try {
			driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			System.out.println("Navigated to innerframe with id " + ChildFrame + "which is present on frame with id"
					+ ParentFrame);
		} catch (NoSuchFrameException e) {
			System.out
			.println("Unable to locate frame with id " + ParentFrame + " or " + ChildFrame + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to innerframe with id " + ChildFrame
					+ "which is present on frame with id" + ParentFrame + e.getStackTrace());
		}
	}

	public static void switchtoDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("Navigated back to webpage from frame");
		} catch (Exception e) {
			System.out.println("unable to navigate back to main webpage from frame" + e.getStackTrace());
		}
	}

	private static boolean isElementPresent(WebElement frameElement) {
		// TODO Auto-generated method stub
		return false;
	}

}
