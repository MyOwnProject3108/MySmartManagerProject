package com.peerius;

import org.openqa.selenium.By;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

public class SmartContent extends Context{
	
	public static void createSetupNoCondition(String name,String placement,String creative,String size,String criteria){
		setText(By.id("name"),name);
		clickElement(By.xpath("//*[@id='htmlInjectionPoint']/option[contains(text(), '"+placement+"')]"));
		clickElement(By.xpath("//div[contains(@id,'traffic')]//select[contains(@class,'chooseimage')]/option[contains(text(),'"+creative+"')]"));
		clickElement(By.xpath("//select[contains(@class, 'choosesize')]/option[contains(text(), '"+size+"')]"));
		clickElement(By.xpath("//div[contains(@class, 'rules-panel')]//ul//div[contains(@class,'rule-content')]//option"
				+ "[contains(text(),'"+criteria+"')]"));
		clickElement(By.xpath("//button[contains(.,' Save campaign')]"));
		
				
	}
	
	
	public static void createSimpleCampaign(String name, String placement, String creative, String size, String rule) {
		setText(By.id("name"),name);
		clickElement(By.xpath("//*[@id='htmlInjectionPoint']/option[contains(text(), '"+placement+"')]"));
		clickElement(By.xpath("//div[contains(@id,'traffic')]//select[contains(@class,'chooseimage')]/option[contains(text(),'"+creative+"')]"));
		clickElement(By.xpath("//select[contains(@class, 'choosesize')]/option[contains(text(), '"+size+"')]"));
		clickElement(By.xpath("//div[contains(@class, 'rules-panel')]//ul//div[contains(@class,'rule-content')]//option"
				+ "[contains(text(),'"+rule+"')]"));
		clickElement(By.xpath("//button[contains(.,' Save campaign')]"));
		
	}
	

	public static void createSimpleCampaignWithCondition(String name, String placement, String creative, String size,String rule, String operator, String attribute) {
		
		setText(By.id("name"),name);
		clickElement(By.xpath("//*[@id='htmlInjectionPoint']/option[contains(text(), '"+placement+"')]"));
		clickElement(By.xpath("//div[contains(@id,'traffic')]//select[contains(@class,'chooseimage')]/option[contains(text(),'"+creative+"')]"));
		clickElement(By.xpath("//select[contains(@class, 'choosesize')]/option[contains(text(), '"+size+"')]"));
		clickElement(By.xpath("//div[contains(@class, 'rules-panel')]//ul//div[contains(@class,'rule-content')]//option"
				+ "[contains(text(),'"+rule+"')]"));
		clickElement(By.xpath("//div/select[contains(@class, 'exp_op operatoroptions')]/option[.='"+operator+"']"));
		clickElement(By.xpath("//div[contains(@class, 'autosearch')]//input"));
		setText(By.xpath("//div[contains(@class, 'autosearch ')]/input"), attribute);
		clickElement(By.xpath("//button[contains(.,' Save campaign')]"));
	}
	
		

	public static void gotoContentCampaign(String campaignName) {
		Navigation.refreshPage();
		Navigation.gotoURL("smartmanager/adaptivecontent/list.page");
		elementIsPresent(By.linkText(campaignName));
		clickLink(campaignName);
				
	}
	
	

	public static void deleteContentCampaign(String name) {
		Navigation.refreshPage();
		Navigation.gotoURL("smartmanager/adaptivecontent/list.page");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td//small/a[text()= '"+name+"']//following::td//a[@data-original-title='Delete setup']"));
		clickElement(By.xpath("//div[contains(@class, 'yes')]"));
		verifyErrorMessage(By.className("notification"), "Successfully deleted");
				
	}

	public static void activateContentCampaign(String name) {
		Navigation.gotoURL("smartmanager/adaptivecontent/list.page");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td//small/a[text()= '"+name+"']//following::td//a[@data-original-title=' Activate it ']"));
		verifyInnerHTML(By.tagName("div"),"Switched on");
		
	}

	public static void duplicateContentCampaign(String name) {
		Navigation.gotoURL("smartmanager/adaptivecontent/list.page");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td//small/a[text()= '"+name+"']//following::td//a[@data-original-title='Duplicate setup']"));
		
		
	}

	public static void verifyContentCampaign(String name) {
		Navigation.gotoURL("smartmanager/adaptivecontent/list.page");
		elementIsPresent(By.linkText(name));
				
	}

	public static void editContentCampaign(String name) {
		Navigation.gotoURL("smartmanager/adaptivecontent/list.page");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td//small/a[text()= '"+name+"']//following::td//a[@data-original-title='Edit setup']"));
		
	}

	public static void doNotDeleteContentCampaign(String name) {
		Navigation.gotoURL("smartmanager/adaptivecontent/list.page");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td//small/a[text()= '"+name+"']//following::td//a[@data-original-title='Delete setup']"));
		clickElement(By.xpath("//div[contains(@class, 'no')]"));
		
		
	}





	
	
	
	
	
}
