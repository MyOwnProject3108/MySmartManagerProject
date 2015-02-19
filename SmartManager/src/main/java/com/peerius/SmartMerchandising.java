package com.peerius;

import org.openqa.selenium.By;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;


public class SmartMerchandising extends Context {
	
	
	
	public static void createCampaign(String name, String location, String widget, String ruleExpression){
		
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

}
