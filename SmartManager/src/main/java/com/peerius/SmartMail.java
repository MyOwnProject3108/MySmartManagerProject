package com.peerius;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;


public class SmartMail extends Context {
	
	public static void createSimpleMailCampaign(String name, String strategy)
	{
		Navigation.gotoURL("/smartmanager/mail/edit.page");
		setText(By.id("the_email_campaign_name"), name);
		clickButton("Next");
		setText(By.xpath("//input[@class='visual-input']"), strategy);
		selectStrategy(strategy);
		clickElement(By.id("mail_submit_btn"));
		
		elementIsPresent(By.id("ajax_progress_bar"));
	
	}
	
	public static void selectStrategy(String strategy) {
		
		List<WebElement> strategyList = driverInstance.findElements(By.cssSelector(".visual-list.context-menu"));
		
			for(WebElement singleStrategy: strategyList){
			
			if(singleStrategy.getText().contains(strategy)){
			singleStrategy.click();
		}
		
	}
	
	}
	
	
	public static void deleteMailCampaign(String name) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td/a[text()='"+name+"']//following::td//a[@data-original-title='Delete mail campaign']"));
		clickElement(By.xpath("//div[contains(@class,'yes')]"));
		threadSleep(2000);
		Navigation.refreshPage();
		elementNotPresent(By.linkText(name));
		
	}
	
	public static void duplicateMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a[text()='"+campaign+"']//following::td//a[@data-original-title='Duplicate mail campaign']"));
		threadSleep(2000);
		elementIsPresent(By.linkText(campaign+"copy"));
		
	}
	
	public static void pauseMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a[text()='"+campaign+"']//following::td//a[@data-original-title=' Pause it ']"));
		threadSleep(2000);
		Navigation.refreshPage();
		elementIsPresent(By.xpath("//td//a[@data-original-title='Activate it']"));
				
	}
	
	public static void editMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a[text()='"+campaign+"']//following::td//a[@data-original-title='Edit mail campaign']"));
		verifyURlText("edit.page");
		
	}
	
	public void goToMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickLink(campaign);
		
	}
	
	public void verifyMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		
	}
	
	}
	


