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
		
		elementIsPresent(By.id("ajax_progress_bar"));
			
		
	}
	
	public static void createCampaignSimpleNoRule(String name, String location, String widget){
		
		Navigation.gotoURL("/smartmanager/merchandising/edit.page");
		setText(By.id("name"), name);
		selectDropList(By.id("location"), location);
		selectDropList(By.id("widget"), widget);
	//	clickElement(By.xpath("//button[contains(.,' Save Campaign')]"));
		
	//	elementIsPresent(By.id("ajax_progress_bar"));
		
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
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td/a//small[text()='"+name+"']//following::td//a[@data-original-title='Delete']"));
		clickElement(By.xpath("//div[contains(@class,'yes')]"));
		threadSleep(2000);
		Navigation.refreshPage();
		elementNotPresent(By.linkText(name));
	}
	
	public static void duplicateCampaign(String campaign){
				
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title='Duplicate']"));
		elementIsPresent(By.linkText(campaign+" copy"));

	}
	
	public static void activateCampaign(String campaign){

		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title=' Activate it ']"));
		threadSleep(2000);
		Navigation.refreshPage();
		elementIsPresent(By.xpath("//td//a[@data-original-title=' Pause it ']"));
		
	}
	
	public static void pauseCampaign(String campaign){

		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title=' Pause it ']"));
		threadSleep(2000);
		Navigation.refreshPage();
		elementIsPresent(By.xpath("//td//a[@data-original-title=' Activate it ']"));
		
	}
	
	public static void editCampaign(String campaign){
		
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a//small[text()='"+campaign+"']//following::td//a[@data-original-title='Edit']"));
		verifyURlText("edit.page");
		
		
	}
	
	public static void gotoCampaign(String campaign){
		
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elementIsPresent(By.linkText(campaign));
		clickLink(campaign);
		
		
	}

	public static void verifyCampaign(String campaign){
		
		Navigation.gotoURL("/smartmanager/merchandising/list.page");
		elementIsPresent(By.linkText(campaign));
		
		
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
		elementIsPresent(By.xpath("//ul[contains(@class,'tags')]"));
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
	
		elementIsPresent(By.xpath("//input[@id='previewCategory']/following::ul[1]"));
		
		List<WebElement> categories = driverInstance.findElements(By.xpath("//input[@id='previewCategory']/following::ul[1]"));
	
		for (WebElement cat : categories) {

			Actions doubleClick = new Actions(driverInstance);
			doubleClick.moveToElement(cat).doubleClick(cat).click(cat).build().perform();
		}
	}

	public static void createABgroup(String group, String page, String widget, String group_a_percent, String group_b_percent){
	
		Navigation.gotoURL("/shop-admin/abtesting/abtests.page");
		setText(By.id("group_a"), group_a_percent);
		clickElement(By.xpath("//input[@value='Create']"));
		clickElement(By.xpath("//*[@class='box widgets_"+group+"']//em[.='"+page+"']/following::select[@name='self[PRODUCT-"+group+"-0]']/option[.='"+widget+"']"));

		javaScriptExe("$(\"#group_b\").val("+group_b_percent+")");
		javaScriptExe("window.scrollBy(0,290);");
		clickElement(By.xpath("//select[@name='self[PRODUCT-"+group+"-0]']/option[.='"+widget+"']"));
	
	
		COREManager.threadSleep(2000);
		elementNotPresent(By.xpath("//input[@value='Create']"));
		Navigation.refreshPage();
		clickElement(By.xpath("//*[@class='box widgets_"+group+"']//em[.='"+page+"']/following::select[@name='self[PRODUCT-"+group+"-0]']/option[.='"+widget+"']"));
		javaScriptExe("window.scrollBy(0,290);");
		clickElement(By.xpath("//input[@value='Update']"));
	
		acceptAlert();
		elementIsPresent(By.xpath("//em[contains(.,'A/B Test Updated')]"));
	
	}

	public static void createExclusions(String refcode) {
		elementIsPresent(By.xpath("//*[@id='manual']//textarea[2]"));
		clickElement(By.xpath("//*[@id='manual']//textarea[2]"));
		setText(By.xpath("//*[@id='manual']//textarea[2]"), refcode);
		elementIsPresent(By.xpath("//*[@id='manual']//ul"));
		List<WebElement> elements = driverInstance.findElements(By.xpath("//*[@id='manual']//ul"));
		for (WebElement element:elements){
			Actions doubleClick = new Actions(driverInstance);
			doubleClick.moveToElement(element).doubleClick().sendKeys(Keys.ENTER).build().perform();
			}
		clickLink("Finished");
	
	}

public static void createCampaignDuplicate(String name, String location, String widget, String ruleExpression){
	
	
	
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
		
	}

	
}

