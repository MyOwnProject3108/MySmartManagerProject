package com.peerius;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

public class SmartContent extends Context
{
	
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

	public static void addNewCreative(String name, String imgUrl, String linkUrl, String relCat, String relAtt, String tag) throws InterruptedException
	{
		setText(By.id("name"), name);
		
		setText(By.id("image0"), imgUrl);
		
		setText(By.id("link0"), linkUrl);
		
		clickElement(By.xpath("(//input[@class='visual-input'])[1]"));
		
		List<WebElement> categories = new WebDriverWait(driverInstance,5L).until(ExpectedConditions.visibilityOfAllElementsLocatedBy
				(By.xpath("//ul[@class='visual-list context-menu hide']/li")));
				
		for (WebElement category : categories)
		{
		if (category.getText().equalsIgnoreCase(relCat))
			{
				category.click();
			}
		}
		
		clickElement(By.id("name"));
		
		clickElement(By.xpath("(//input[@class='visual-input'])[2]"));
		
		List<WebElement> attributes = new WebDriverWait(driverInstance,10L)
			.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".attr_list ul.visual-list li")));  //div[contains(@class,'attr_list')]//ul[@class='visual-list context-menu hide']/li
		
		for (WebElement attribute: attributes)
		{
			if(attribute.getText().equalsIgnoreCase(relAtt))
			{
				attribute.click();
				Thread.sleep(1000);
			}
						
		}
		
		//clickElement(By.id("name")); the name field was hidden behind the top bar so test was failing so commented it out
		clickElement(By.id("link0"));
		
		clickElement(By.xpath("(//input[@class='visual-input'])[3]"));
		
		setText(By.xpath("(//input[@class='visual-input'])[3]"), tag);
		
		clickElement(By.id("link0"));
		//clickElement(By.id("name")); the name field was hidden behind the top bar so test was failing so commented it out
		
		clickButton("Save creative");
	
	}
	
	public static void verifyCreative(String creative) 
	{

		Navigation.gotoURL("/smartmanager/creative/list.page");
		
		elementIsPresent(By.linkText(creative));

	}
	
	public static void goToCreative(String creative) 
	{

		Navigation.gotoURL("/smartmanager/creative/list.page");
		
		elementIsPresent(By.linkText(creative));
		
		clickLink(creative);

	}
	
	public static void editCreative(String creative)
	{
		Navigation.gotoURL("/smartmanager/creative/list.page");
		
		elementIsPresent(By.linkText(creative));
		
		clickElement(By.xpath("//td//a[contains(text(),'"+creative+"')]//following::td//a[@data-original-title='Edit creative']"));
		
		verifyURlText("edit.page");
		
	}
	
	public static void duplicateCreative(String creative)
	{
		Navigation.gotoURL("/smartmanager/creative/list.page");
		
		elementIsPresent(By.linkText(creative));
		
		clickElement(By.xpath("//td//a[contains(text(), '"+creative+"')]//following::td//a[@data-original-title='Duplicate creative']"));
		
		elementIsPresent(By.linkText(creative + " copy"));
		
	}
	
	public static void deleteCreative(String creative)
	{
		Navigation.gotoURL("/smartmanager/creative/list.page");
		
		elementIsPresent(By.linkText(creative));
		
		clickElement(By.xpath("//td//a[contains(text(), '"+creative+"')]//following::td//a[@data-original-title='Delete creative']"));
		
	}
	
	public static void addToBasket() throws InterruptedException{
		clickElement(By.xpath("//*[@id='dateBox1']"));
		  clickElement(By.xpath("//div[@id='CalendarForm1']//td[@class='colour1']"));
		  clickElement(By.xpath("//select[@id='numOfAdults1']"));
		  WebElement adult= driverInstance.findElement(By.xpath("//*[@id='ticket-panel']//select[@id='numOfAdults1']"));
		   adult.sendKeys("1");
		  adult.sendKeys(Keys.ENTER);
		   clickElement(By.id("BookTickets"));
		  Thread.sleep(1000);
	}

	
}
