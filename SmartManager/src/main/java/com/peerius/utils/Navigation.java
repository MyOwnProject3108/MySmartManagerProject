package com.peerius.utils;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.peerius.COREManager;

public class Navigation extends COREManager {

	public static String gotoClientUrl(String url) {
		url = prop.getProperty(url, "").trim();
		driverInstance.navigate().to(url);

		return url;
	}
	
	public static void gotoURL(String url){
		
		driverInstance.get(siteUrl+"/"+url+"");
		
		
	}

	public static void gotoSite(String sitename) {

		driverInstance.navigate()
				.to(siteUrl + "?switchToSite=" + sitename + "");
		Assert.assertTrue(driverInstance.getCurrentUrl().contains(sitename));

	}

	public static void searchSite(String sitename) {
	
		WebElement droplist = driverInstance.findElement(By.xpath("//*[@class='select2-choice']"));
		WebElement searchBox = driverInstance.findElement(By.xpath("//input[contains(@class,'input')]"));
		
		droplist.click();
		
		Actions searchText = new Actions(driverInstance);
		
		searchText.sendKeys(searchBox, sitename).sendKeys(Keys.ENTER).build().perform();
		
		driverInstance.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		Assert.assertTrue("Site Not Switched or Failed to Change",driverInstance.getCurrentUrl().contains(sitename));

	}

	public static void gotoLoginPage() {

		driverInstance.navigate().to(COREManager.siteUrl);
		Assert.assertTrue(driverInstance.getPageSource().contains("Login"));
	}

	public void navigatePage(String direction) {

		WebDriver.Navigation navigatePage = COREManager.driverInstance
				.navigate();

		if (direction.equals("back")) {

			navigatePage.back();
		}

		if (direction.equals("forward")) {

			navigatePage.forward();

		} else {

			Assert.fail("Failed to Navigate");
		}
	}

	public static void switchToFrame(String name) {
		try {
			COREManager.driverInstance.switchTo().frame(name);
		} catch (NoSuchFrameException e) {

			WebDriverWait wait = new WebDriverWait(COREManager.driverInstance,
					10L);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));

			Assert.fail("Failed to switchToFrame - " + name);
		}
	}

	public static void closeBrowser() {
		
		final Thread KILL_BROWSER_THREAD = new Thread() {
			
			
			public void run(){
				driverInstance.quit();
		
			}
		};
			
		Runtime.getRuntime().addShutdownHook( KILL_BROWSER_THREAD);
		
	}
	
	public static void refreshPage() {
		COREManager.driverInstance.navigate().refresh();
	
	}

		
	public static void setBrowserResolution(int width, int height){
    	//Setting Browser Resolution 
    	try {
    	Dimension size = new Dimension(width, height);
    	Dimension resolution; 
    	Context.driverInstance.manage().window().setSize(size);
    	resolution = Context.driverInstance.manage().window().getSize();
      	Assert.assertEquals(resolution, size);
    	}
    	
    	catch(AssertionError e){
    		
    		System.out.println("Resolution Not Set");
    		Assert.assertFalse(false);
    		
    	}
    	
}
	
	public static void closeWindow(){
		
		driverInstance.close();
	}
	
}