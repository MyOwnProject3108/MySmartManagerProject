package com.peerius;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;


public class SmartMerchandising extends Context {
	
	
	
	public static void createCampaignSimple(String name, String location, String widget, String ruleExpression){
		
		Navigation.gotoURL("/smartmanager/merchandising/edit.page");
		setText(By.id("name"), name);
		selectDropList(By.id("location"), location);
		selectDropList(By.id("widget"), widget);
		clickButton("Next");
		clickButton("Next");
		clickElement(By.xpath("//button[contains(@class,'edit-rule')]"));
		setText(By.id("advanced_btn_rec_default"), ruleExpression);
		clickLink("Apply to all");
		clickElement(By.xpath("//button[contains(.,' Save Campaign')]"));
		threadSleep(5000);
		Navigation.refreshPage();
	}
	
public static void createCampaign(String name, String position, String location, String widget, String ruleExpression){
		
		Navigation.gotoURL("/smartmanager/merchandising/edit.page");
		setText(By.id("name"), name);
		selectDropList(By.id("location"), location);
		selectDropList(By.id("widget"), widget);
		clickButton("Next");
		clickButton("Next");
		clickElement(By.xpath("//button[contains(@class,'edit-rule')]"));
		setText(By.id("advanced_btn_rec_default"), ruleExpression);
		dragAndDrop(By.xpath("//div[contains(@data-original-title,'Drag')]"), By.xpath("//ul[@class='rules-grid']/li["+position+"]"));
		clickButton("Save Campaign");
		threadSleep(5000);
		Navigation.refreshPage();
	}

	public static void deleteCampaign(String name){
		
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elemementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td/a//small[text()='"+name+"']//following::td//a[@data-original-title='Delete']"));
		clickElement(By.xpath("//div[contains(@class,'yes')]"));
		threadSleep(2000);
		Navigation.refreshPage();
		elementNotPresent(By.linkText(name));
	}
	
	public static void duplicateCampaign(String campaign){
				
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elemementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title='Duplicate']"));
		elemementIsPresent(By.linkText(campaign+" copy"));

	}
	
	public static void activateCampaign(String campaign){

		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elemementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title=' Activate it ']"));
		threadSleep(2000);
		Navigation.refreshPage();
		elemementIsPresent(By.xpath("//td//a[@data-original-title=' Pause it ']"));
		
	}
	
	public static void pauseCampaign(String campaign){

		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elemementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title=' Pause it ']"));
		threadSleep(2000);
		Navigation.refreshPage();
		elemementIsPresent(By.xpath("//td//a[@data-original-title=' Activate it ']"));
		
	}
	
	public static void editCampaign(String campaign){
		
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elemementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title='Edit']"));
		verifyURlText("edit.page");
		
		
	}
	
	public static void gotoCampaign(String campaign){
		
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elemementIsPresent(By.linkText(campaign));
		clickLink(campaign);
		
		
	}

	public static void verifyCampaign(String campaign){
		
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elemementIsPresent(By.linkText(campaign));
		
		
	}
	
	public static void verifyMandatoryField(){
		
		
	}
public static void gotoCreateCampaign(){
	
	Navigation.gotoURL("/smartmanager/merchandising/edit.page");
	
}

public static void selectPreviewProductList(String productPrefix, String number){
	
	int numberOfProduct = Integer.parseInt(number);

	setText(By.className("visual-input"), productPrefix);
	pressKey("Enter");
	elemementIsPresent(By.xpath("//ul[contains(@class,'tags')]"));
	List<WebElement> products = driverInstance.findElements(By.xpath("//textarea[@id='previewProducts']/following::ul[1]"));

	for (int i = 0; i < numberOfProduct; i++) {

		for (WebElement product : products) {

			Actions doubleClick = new Actions(driverInstance);

			doubleClick.moveToElement(product).click().sendKeys(Keys.ENTER).build().perform();
		}
	}
	clickLink("Finished");

}

public static void selectPreviewCategory(String category){
	
	setText(By.id("previewCategory"), category);
	
	List<WebElement> products = driverInstance.findElements(By.xpath("//input[@id='previewCategory']/following::ul[1]"));
	for (int i = 0; i < 5; i++) {
	
	for (WebElement product : products) {

		Actions doubleClick = new Actions(driverInstance);

		doubleClick.moveToElement(product).click().sendKeys(Keys.ENTER).build().perform();
	}
	}
}

}