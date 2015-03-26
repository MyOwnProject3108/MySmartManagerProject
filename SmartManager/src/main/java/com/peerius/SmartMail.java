package com.peerius;


import java.util.List;

import org.junit.Assert;
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
		clickElement(By.xpath("//input[@class='visual-input']"));
		setText(By.xpath("//input[@class='visual-input']"), strategy);
		pressKey("Enter");
		clickElement(By.id("mail_submit_btn"));
		
		elementIsPresent(By.id("ajax_progress_bar"));
	
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
	
	public static void goToMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickLink(campaign);
		
	}
	
	public static void verifyMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		
	}
	
	public static void setStyle(String value) {
		
		setText(By.id("productInfoWidth"), value);
		setText(By.id("productInfoHeight"), value);
		
	}
	
	public static void verifyStyleAttribute(String attribute, String value) {
		
		WebElement style = driverInstance.findElement(By.id("productInfoPreview"));
		Boolean visible =style.getAttribute(attribute).equals(value);
		
		Assert.assertTrue(visible);
	}
	
	public static void verifyProductPosition(String position){
		
		
		int number = Integer.parseInt(position);
				
		for(int i=1;i<=number;++i){
			
			elementIsPresent(By.id("mail-item"+i+""));
			
		}
	}
	
	public static void setStrategyPerPosition(String position, String strategy) {
		
		
			elementIsPresent(By.id("mail-item"+position+""));
			
			List<WebElement> elements= driverInstance.findElements(By.xpath("//div[@class='visual-tag']"));
			
			for (WebElement element:elements){
				
				if (element.isDisplayed()){
					
					clickElement(By.xpath("//i[@class='visual-tags-close close sm-icon-cancel']"));
				}
				
			}
			
			clickElement(By.xpath("(//div[@class='visual'])["+position+"]"));	
			setText(By.xpath("(//input[@class='visual-input'])["+position+"]"), strategy);
			pressKey("Enter");
				
	}
	
	public static void enableUserTopUps() {
		
		WebElement checkbox = driverInstance.findElement(By.id("useTopups"));
		if (checkbox.getAttribute("checked").equals(false)) {
			
			driverInstance.findElement(By.id("useTopups")).click();
		}
		
	}
	
	public static void verifyDuplicatePosition(String position, String strategy, String ruleValue, String ruleText, String hintOption, String operator ) {
		
		int number = Integer.parseInt(position);
		
		for(int i=1;i<=number;++i){
			
			elementIsPresent(By.id("mail-item"+i+""));
			
			clickElement(By.xpath("//a[@href='#item"+i+"-rec']"));	
			
			verifyInnerHTML(By.xpath("(//div[@class='visual-tags-value'])["+i+"]"), strategy);
			
			clickElement(By.xpath("//a[@href='#item"+i+"-exp']"));
			
			verifySelectOption(ruleText, By.xpath("(//select[@class='exp_left_hand'])["+i+"]"));
		
			verifySelectOption(operator, By.xpath("(//select[@class='exp_op operatoroptions'])["+i+"]"));
		
			//verifytextContent(By.xpath("(//input[contains(@class, 'autosearch')])["+i+"]"), ruleValue);
			Assert.assertTrue(driverInstance.findElement(By.xpath("(//input[contains(@class, 'autosearch')])["+i+"]")).getAttribute("value").contains(ruleValue));
		
			clickElement(By.xpath("//a[@href='#item"+i+"-hints']"));
		
			verifySelectOption(hintOption, By.xpath("(//select[@class='mail_hint_select'])["+i+"]"));
		
		}
		
	}
		}
	


