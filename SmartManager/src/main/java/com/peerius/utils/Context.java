package com.peerius.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.peerius.COREManager;

/**
 * @author zsuleiman
 *
 */
public class Context extends COREManager {

	
    public enum LocatorType {
        Xpath, cssSelector, id, LinkedText, className, partialLinkedText, Name, TagName
    }
	
	static {

	}

	public static void setText(By locator, String text) {

		WebElement textBox = new WebDriverWait(driverInstance, elementWaitTime).pollingEvery(elementWaitTime, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
		textBox.clear();
		textBox.sendKeys(text);

	}

	public static void clickLink(String link) {
		
		WebElement wait;
					
		try{

		wait = (WebElement) new WebDriverWait(COREManager.driverInstance,
				COREManager.elementWaitTime).until(ExpectedConditions
				.visibilityOfElementLocated(By.linkText(link)));
		}
		
		catch(Exception e){
			wait =  (WebElement) new WebDriverWait(COREManager.driverInstance,
					COREManager.elementWaitTime).until(ExpectedConditions
							.visibilityOfElementLocated(By.partialLinkText(link)));
		}
							
		Actions scrollToView = new Actions(driverInstance);
		scrollToView.moveToElement( wait).click().build().perform();
	}

	public static void selectDropList(By locator, String option) {

		WebElement selectElement = new WebDriverWait(
				COREManager.driverInstance, COREManager.elementWaitTime)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));

		Select droplistOptions = new Select(selectElement);
		
			droplistOptions.selectByVisibleText(option);
				
	}
	
	public static void selectMenuOption(String mainMenuLabel, String subMenu) {

		mainMenuLabel = new String(mainMenuLabel).toLowerCase();
		
		
		Actions hover = new Actions(driverInstance);
		String mainMenu = "//li[contains(@section,'" + mainMenuLabel + "')]";

		WebElement menu = driverInstance.findElement(By.xpath(mainMenu));

		WebElement subMenuOptions = driverInstance.findElement(By
				.xpath("//li[contains(@section,'" + mainMenuLabel + "')]"
						+ "//parent::a[text()='" + subMenu + "']"));

		hover.moveToElement(menu).build().perform();

		WebElement waitForMainMenu = new WebDriverWait(driverInstance, 10)
				.pollingEvery(5, TimeUnit.SECONDS).until(
						ExpectedConditions.visibilityOf(subMenuOptions));

		if (waitForMainMenu != null) {

			hover.moveToElement(waitForMainMenu).click().build().perform();

		} else {

			Assert.fail("Failed Selecting Option" + mainMenuLabel + ">"
					+ subMenu); 
		}

	}

	public static void clickButton(String name) {

		WebElement button = COREManager.driverInstance.findElement(By
				.xpath("//button[contains(.,'"+name+"')]"));

		button = new WebDriverWait(COREManager.driverInstance,
				COREManager.elementWaitTime).until(ExpectedConditions
				.visibilityOf(button));

		button.click();

	}

	public static void clickElement(By locator) {

		WebElement click = new WebDriverWait(driverInstance, elementWaitTime)
				.until(ExpectedConditions.visibilityOfElementLocated((locator)));

		Actions moveToandClik = new Actions(driverInstance);

		moveToandClik.moveToElement(click).click().build().perform();

	}

	public static void rightClick(By locator) {

		WebElement click = new WebDriverWait(driverInstance, elementWaitTime)
				.until(ExpectedConditions.visibilityOfElementLocated((locator)));

		Actions rightClick = new Actions(driverInstance);

		rightClick.contextClick(click).build().perform();
	}

	public static void doubleClick(By locator) {

		WebElement click = new WebDriverWait(driverInstance, elementWaitTime)
				.until(ExpectedConditions.visibilityOfElementLocated((locator)));

		Actions doubleClick = new Actions(driverInstance);

		doubleClick.doubleClick(click).build().perform();

	}

	public static void verifyMerchandisingPage(String page) {

		if (page.equalsIgnoreCase("Overview")) {

			driverInstance.manage().timeouts()
					.implicitlyWait(implicitWait, TimeUnit.SECONDS);
			Assert.assertTrue(driverInstance.getCurrentUrl().contains("list"));

		}

		if (page.equalsIgnoreCase("Create a New Campaign")) {

			Assert.assertTrue(driverInstance.getCurrentUrl().contains("edit"));
		}

		else if (page.equalsIgnoreCase("Define Product Sets")) {
			Assert.assertTrue(driverInstance.getCurrentUrl().contains(
					"selectedproductsets"));
		}

	}
	
	public static void verifyMailPage(String page) {

		if (page.equalsIgnoreCase("Overview")) {

			driverInstance.manage().timeouts()
					.implicitlyWait(implicitWait, TimeUnit.SECONDS);
			Assert.assertTrue(driverInstance.getCurrentUrl().contains("list"));

		}

		if (page.equalsIgnoreCase("Create a New Campaign")) {

			Assert.assertTrue(driverInstance.getCurrentUrl().contains("edit"));
		}
		
		else if (page.equalsIgnoreCase("Triggers")) {
			
			Assert.assertTrue(driverInstance.getCurrentUrl().contains(
					"triggers"));
		}

		else if (page.equalsIgnoreCase("Define Product Sets")) {
			Assert.assertTrue(driverInstance.getCurrentUrl().contains(
					"selectedproductsets"));
		}
		
		else if (page.equalsIgnoreCase("Customise Email Attributes")) {
			
			Assert.assertTrue(driverInstance.getCurrentUrl().contains(
					"customattributes"));
		}
	}


    public static void verifyErrorMessage(By by, String errorMesage) {
        Locator locator = Locator.by(by);
            
       ArrayList<String>  allText = new ArrayList<String>();
                     
       if (locator.isPresent()|locator.hasClass("error")|locator.hasClass("notification")|locator.hasClass("success")) {
              
              allText.add(locator.getElement().getAttribute("textContent"));    
       
              System.out.println(locator.getElement().getAttribute("textContent")+"\n"); 
              
              String exactText =allText.get(0).toString();
              Assert.assertTrue(exactText.contains(errorMesage));
              
       }
    }

	public static void verifyPageText(By locator, String text) {
		
		try{
			Boolean textVisible = new WebDriverWait(driverInstance, elementWaitTime)
			.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
			Assert.assertTrue(textVisible);
			
		}
		
		catch (AssertionError| NoSuchElementException e){
		
			System.out.println("Element Text is " + locator.findElement(driverInstance).getText());
			
		}
	
	}

	public static void javaScriptExe(String jscode) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) Context.driverInstance;
			js.executeScript(jscode);
			System.out.println("JavaScript Code ---- Executed");
		} catch (Exception e) {

		}
	}

	public static boolean isVisible(WebElement element) {

		element = new WebDriverWait(Context.driverInstance, elementWaitTime)
				.until(ExpectedConditions
						.visibilityOfElementLocated((By) element));

		return true;
	

	}

	public static void dismissAlert() {
		
		driverInstance.switchTo().alert().dismiss();
	}
	
	public static void acceptAlert() {
		
		driverInstance.switchTo().alert().accept();
	}
	
	public static void verifyInnerHTML(By elementlocator, String text) {
		
		WebElement innerHTML = driverInstance.findElement(elementlocator);
		
		boolean innerText =	innerHTML.getAttribute("innerHTML").contains(text);
			
		Assert.assertTrue("Text Does Not Exist -- "+text+"",innerText);

	}
	
public static void verifytextContent(By elementlocator, String text) {
		
		WebElement innerContent = driverInstance.findElement(elementlocator);
		
		boolean innerText =	innerContent.getAttribute("textContent").contains(text);
		Assert.assertTrue("Text Does Not Exist -- "+text+"--Actual Text",innerText);

	}

	public static void elementIsPresent(By locator) {
		
		boolean elementPresent = new WebDriverWait(driverInstance, elementWaitTime)
		.pollingEvery(implicitWait, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOfElementLocated(locator)).isEnabled();
				
		Assert.assertTrue(elementPresent);
				
	}
	
	

	public static void elementNotPresent(By locator) {
	
		boolean elementNotPresent = new WebDriverWait(driverInstance, 1)
		.pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
				
		Assert.assertTrue(elementNotPresent);
				

	}

	public static void verifySelectOption(String option, By by) {
		
		Select optionText = new  Select(driverInstance.findElement(by));
		
		List<WebElement> selectedOptions =optionText.getAllSelectedOptions();
		
		for (WebElement singleOption: selectedOptions){
				
			singleOption.getText().contains(option);
			
			
		}
		
				
	}

	public static void isChecked(By locatorType, String locator) {
		
		 try {
				            
	            WebElement checkBox = new WebDriverWait(driverInstance, elementWaitTime)
	            .until(ExpectedConditions.elementToBeClickable(locatorType));
	            
	            
	            if( checkBox.isSelected()!=true ) {
	              
	            	checkBox.click();
	            }
	          
	        } catch( AssertionError | WebDriverException e ) {
	        	
	        	System.out.println("Can't Check Box");
	                   }
	    }
	
	 public static void uncheckBox( By locatorType, String locator) {
		    
	        try {
	        	
	        	WebElement checkBox = new WebDriverWait(driverInstance, elementWaitTime)
            .until(ExpectedConditions.elementToBeClickable(locatorType));
	        	
	            if( checkBox.isSelected() ) {
	                checkBox.click();
	            
	            }
	            if( !checkBox.isSelected() ) {
	            	
	            System.out.println("Element Unchecked");
	               
	            }
	        } catch(WebDriverException | AssertionError e ) {
	        	
	        	Assert.fail("Fail to uncheck Box");
	           
	        }
	    }


	public static void pressKey(String key) {
		
		
		try{
		
		Keys keys = null;
         switch(key) {
         case "Up":
             keys = Keys.ARROW_UP;
             break;
         case "Down":
             keys = Keys.ARROW_DOWN;
             break;
         case "Left":
             keys = Keys.ARROW_LEFT;
             break;
         case "Right":
             keys = Keys.ARROW_RIGHT;
             break;
         case "Delete":
             keys = Keys.DELETE;
             break;
         case "Enter":
             keys = Keys.ENTER;
             break;
         case "Control":
             keys = Keys.CONTROL;
             break;
         case "Escape":
             keys = Keys.ESCAPE;
             break;
         }
    Actions Keybutton = new Actions(Context.driverInstance);
         Keybutton.sendKeys(keys).build().perform();}
		
		catch(WebDriverException e){
			
			Assert.fail("Failed to press " + key);
		}
	}

	public static void verifyPageTitle(String title) {
	
		driverInstance.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		boolean titlePresent =driverInstance.getTitle().contains(title);
		Assert.assertTrue(titlePresent);
	}
	
	public static void verifyURlText(String text){
		
		driverInstance.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		boolean textPresent = driverInstance.getCurrentUrl().contains(text);
		Assert.assertTrue(textPresent);

	}
	
	public static void dragAndDrop(By sourceTo, By targetTo){
		
		WebElement source = new WebDriverWait(driverInstance, elementWaitTime)
		.until(ExpectedConditions.presenceOfElementLocated(sourceTo));
				
		WebElement target = new WebDriverWait(driverInstance, elementWaitTime)
		.until(ExpectedConditions.presenceOfElementLocated(targetTo));
		
		Actions dragAndDrop = new Actions(driverInstance);
	
		dragAndDrop.clickAndHold(source).moveToElement(target).build().perform();

		dragAndDrop.moveToElement(target, 142, 624).release(target).perform();
		
	
	}
	
	public static void uploadFile(By by, String filePath){
		
		WebElement fileBrowser = driverInstance.findElement(by);
		fileBrowser.sendKeys(filePath);
		
		
	}
	

//Locator class to find elements. Will contain helper methods.

static class Locator extends Context {

	private WebDriver webDriver;
	private WebElement webElement;

	Locator() {
		webDriver = Context.driverInstance;
		webElement = null;

	}

	private WebDriver getDriver() {
		return webDriver;
	}

	public WebElement getElement() {
		return webElement;
	}

	public boolean isPresent() {
		webElement = new WebDriverWait(driverInstance, elementWaitTime).
				until(ExpectedConditions.visibilityOf(webElement));
		return webElement != null;
	}

	public Locator find(By by) {
		if (webElement != null) {
			try {
				webElement = webElement.findElement(by);
			} catch (Exception e) {
				webElement = null;
			}
		}
		return this;
	}

	public static Locator by(By by) {
		Locator locator = new Locator();
		try {
			locator.webElement = locator.getDriver().findElement(by);
		} catch (Exception e) {

		}
		return locator;
	}

	public static Locator byId(String id) {
		return Locator.by(By.id(id));
	}

	public static Locator byCss(String css) {
		return Locator.by(By.cssSelector(css));
	}

	public static Locator byXPath(String xpath) {
		return Locator.by(By.xpath(xpath));
	}

	public boolean hasClass(String className) {
		return webElement != null
				&& webElement.getAttribute("class").contains(className);
	}

	}


}