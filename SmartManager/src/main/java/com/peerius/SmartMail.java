package com.peerius;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;




public class SmartMail extends Context {
	
	public static Random random = new Random();
	public static String emailgenerated;
	
	public static void createSimpleMailCampaign(String name, String strategy)
	{
		Navigation.gotoURL("/smartmanager/mail/edit.page");
		setText(By.id("the_email_campaign_name"), name);
		clickButton("Next");
		clickElement(By.xpath("//input[@class='visual-input']"));
		setText(By.xpath("//input[@class='visual-input']"), strategy);
		pressKey("Enter");
		clickElement(By.id("mail_submit_btn"));
		
	}
		
	
	public static void deleteMailCampaign(String name) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td/a[text()='"+name+"']//following::td//a[@data-original-title='Delete mail campaign']"));
		clickElement(By.xpath("//div[contains(@class,'yes')]"));
		verifyErrorMessage(By.className("notification"), "Successfully deleted");
		elementNotPresent(By.linkText(name));
		
	}
	
	public static void duplicateMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a[text()='"+campaign+"']//following::td//a[@data-original-title='Duplicate mail campaign']"));
		elementIsPresent(By.linkText(campaign+" copy"));
		
	}
	
	public static void pauseMailCampaign(String campaign) {
		
		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a[text()='"+campaign+"']//following::td//a[@data-original-title=' Pause it ']"));
		Navigation.refreshPage();
		elementIsPresent(By.xpath("//td//a[@data-original-title=' Activate it ']"));
				
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
		Context.pressKey("Enter");
		setText(By.id("productInfoHeight"), value);
		Context.pressKey("Enter");
	}
	
	
	public static void verifyStyleAttribute(String value, String attribute) {
		
		WebElement style = driverInstance.findElement(By.id("productInfoPreview"));
		String actual = style.getAttribute(attribute).trim().toString();
		
	
		Assert.assertEquals(value, actual);
	}
	
	public static void verifyProductPosition(String position){
		
		
		int number = Integer.parseInt(position);
				
		for(int i=1;i<=number;++i){
			
			elementIsPresent(By.id("mail-item"+i+""));
			
		}
	}
	
	public static void setStrategyPerPosition(String position, String strategy) {
		
		
			elementIsPresent(By.id("mail-item"+position+""));
			
			List<WebElement> elements= driverInstance.findElements(By.xpath("(//div[@class='visual-tag'])["+position+"]"));
			
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
	
	public static void verifyTopUpsDisabled(){
		
		WebElement imgHeight = driverInstance.findElement(By.xpath("//td[2]/a/img[@title='Just for you']"));
		
		Assert.assertEquals("1", imgHeight.getAttribute("height"));
		
	}
	
	public static void verifyTopUpsEnabled(){
		
		WebElement imgHeight = driverInstance.findElement(By.xpath("//td[2]/a/img[@title='Just for you']"));
		
		Assert.assertNotEquals("1", imgHeight.getAttribute("height"));
		
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

			Assert.assertTrue(driverInstance.findElement(By.xpath("(//input[contains(@class, 'autosearch')])["+i+"]")).getAttribute("value").contains(ruleValue));
		
			clickElement(By.xpath("//a[@href='#item"+i+"-hints']"));
		
			verifySelectOption(hintOption, By.xpath("(//select[@class='mail_hint_select'])["+i+"]"));
				}
		
	}


	public static void verifyTrackingCode(String trackingcodeUrl) {
		
		Navigation.selectWindowPopup();
		String currentUrl=driverInstance.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains(trackingcodeUrl));
		
	}

	public static void generateRandomEmail(String randomEmail) {
		int randomInt = random.nextInt(100000);
		emailgenerated = randomEmail + randomInt;
		setText(By.xpath("//input[@type='email']"),emailgenerated +"@mailinator.com");
			
	}


	public static void searchRandomEmail() {
		setText(By.id("inboxfield"), emailgenerated);
		
	}


	public static void openEmail(String emailName) {
	
		elementIsPresent(By.id("InboxCtrl"));
		clickElement(By.partialLinkText(emailName));
		
		
		}


	public static void searchFallBackSKU(String fallbackset) {
		elementIsPresent(By.className("sku_set_searcher"));
		clickElement(By.className("sku_set_searcher"));
		setText(By.className("sku_set_searcher"), fallbackset);
		
		List<WebElement> productsets=driverInstance.findElements(By.cssSelector(".sku_set_context.context-menu"));
		
		for (WebElement productset : productsets) {

			Actions selectFallback = new Actions(driverInstance);
			selectFallback.moveToElement(productset).click().build().perform();
			
		}
	}


	public static void setExpression(String rule, String position) {
		   clickElement(By.partialLinkText("Toggle"));
		   setText(By.xpath("//*[@id='item"+position+"-exp']/textarea"),rule);
	}
	
	
	
	
	public static void createESPConnection(String ESPname, String connectionName  ){
	
	setText(By.id("name"), connectionName);
	
	
	if(ESPname.equalsIgnoreCase("Silverpop")){
		
			Credential.espUserLogin("silverpop");
			selectDropList(By.name("esp"), "Silverpop");
			setText(By.id("realm"), "http://api2.silverpop.com/SoapApi");
	}
	
	if(ESPname.equalsIgnoreCase("Ecircle")){
		
			Credential.espUserLogin("ecircle");
			selectDropList(By.id("esp"), "Teradata (ECircle)");
			setText(By.id("realm"), "http://peerius.cust-mta.com");
				
	}
		if(ESPname.equalsIgnoreCase("SmartCast")){
			
			Credential.espUserLogin("smartcast");
			selectDropList(By.id("esp"), "SmartCast");
			setText(By.id("realm"), "http://uk56.em.sdlproducts.com");	
		}

		clickButton("Test ESP Connection");
		verifyErrorMessage(By.className("notifications"), "Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));
		clickButton("Save ESP Connection");
		threadSleep(2000);
		elementIsPresent(By.className("notification"));
		verifyErrorMessage(By.className("notifications"), "Successfully saved.");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success disabled')]"));
		}
	
	
	
	
	public static void createESPAction(String actionName, String espConnection) {
		setText(By.id("action-name"),actionName);
		
		if(espConnection.equalsIgnoreCase("ECircle")){
			selectDropList(By.id("connection-name"), "ECircle");
			clickButton("SEND_MESSAGE_TO_USER");
			setText(By.name("Message ID"), "1800403818");
		}
		
		if(espConnection.equalsIgnoreCase("SmartCast")){
			selectDropList(By.id("connection-name"), "SmartCast");
			clickButton("SEND_MESSAGE_TO_USER");
			setText(By.name("Creative Name"), "74535_Abandon_201410v01");
			setText(By.name("Template ID"), "116");
			setText(By.name("List Category"), "74535 - Abandon Basket");
		}
		
		if(espConnection.equalsIgnoreCase("Silverpop")){
			selectDropList(By.id("connection-name"), "Silverpop");
			clickButton("SEND_MESSAGE_TO_USER");
			setText(By.name("listID"), "4066625");
			setText(By.name("Message ID"), "4079469");
				
		}		
		
		setText(By.name("email_address"), "webtest@mailinator.com");
		clickButton("Test ESP Action");
		verifyErrorMessage(By.className("notification"), "Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));
		clickButton("Save ESP Action");
		threadSleep(2000);
		elementIsPresent(By.className("notification"));
		verifyInnerHTML(By.xpath("//div[@class='notifications']"), "Successfully saved.");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success disabled')]"));
		
	}
	


	public static void createESPTrigger(String triggerName, String espActionName, String position) {
		setText(By.id("triggers_name"), triggerName);
		setText(By.xpath("//div[contains(@class, 'triggers_after_field')]/input"), "5");
		
		WebElement element =driverInstance.findElement(By.id("trigger_action_id"));
		List<WebElement> options= element.findElements(By.tagName("option"));
		for(WebElement option : options){
			if(option.getText().equals(espActionName)){
				option.click();
			} 
			
		}
			
		dragAndDrop(By.xpath("//*[@id='available']/li["+position+"]"), By.id("in-use"));
		threadSleep(2000);
		elementIsPresent(By.xpath("//div[contains(@class, 'row cf')]/div/button[@data-original-title='Save this trigger campaign.']"));
		clickButton("Save Trigger");
//		threadSleep(2000);
		elementIsPresent(By.className("notification"));
		verifyInnerHTML(By.xpath("//div[@class='notifications']"), "Successfully saved.");
		elementIsPresent(By.xpath("//div[contains(@class, 'triggers-target')]//li[contains(@class, 'item name')]/a[contains(text(), '"+triggerName+"')]"));
			
	}
	
	
	public static void espActions() {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Actions");
		
	}


	public static void espConnections() {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Connections");
		
	}


	public static void verifyESPAction(String actionName) {
		elementIsPresent(By.linkText(actionName));
		}


	public static void verifyESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		
	}


	public static void createTestOnlyESPConnection(String connectionName,String ESPname) {
		setText(By.id("name"), connectionName);
		Credential.espUserLogin("ecircle");
		selectDropList(By.id("esp"), "Teradata (ECircle)");
		setText(By.id("realm"), "http://peerius.cust-mta.com");
		clickButton("Test ESP Connection");
		verifyErrorMessage(By.className("notifications"), "Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));
		
	}
	

	public static void gotoESPConnection(String connectionName) {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Connections");
		elementIsPresent(By.linkText(connectionName));
		clickLink(connectionName);
		
	}


	public static void editESPConnection(String connectionName) {
		
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Connections");
		elementIsPresent(By.linkText(connectionName));
		clickElement(By.xpath("//ul/li[.='"+connectionName+"']/following-sibling::li/i[@data-original-title='Edit this item.']"));
		elementIsPresent(By.xpath("//button[contains(@class,'btn btn-primary disabled')]"));
		setText(By.id("name"), "AutoECircleConnection");
		clickButton("Test ESP Connection");
		verifyErrorMessage(By.className("notifications"), "Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));
		clickButton("Save ESP Connection");
		verifyErrorMessage(By.className("notifications"), "Successfully saved.");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success disabled')]"));

		
	}


	public static void deleteESPConnection(String connectionName) {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Connections");
		elementIsPresent(By.linkText(connectionName));
		clickElement(By.xpath("//ul/li[.='"+connectionName+"']/following-sibling::li/i[@data-original-title='Delete this item.']"));
		clickElement(By.xpath("//div[contains(@class, 'yes')]"));
		verifyErrorMessage(By.className("notification"), "Successfully deleted this item");
	//	elementNotPresent(By.linkText(connectionName));
		
		
		
	}


	public static void noDeleteESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickElement(By.xpath("//ul/li[.='"+connectionName+"']/following-sibling::li/i[@data-original-title='Delete this item.']"));
		clickElement(By.xpath("//div[contains(@class, 'no btn btn-secondary')]"));
		elementIsPresent(By.linkText(connectionName));
		
	}


	public static void deactiveESPConnection(String connectionName) {
	 elementIsPresent(By.linkText(connectionName));
	 clickElement(By.xpath("//ul/li[.='"+connectionName+"']/following-sibling::li//div[contains(@class, 'ibw btn-switcher on ui-switcher')]"));
		
	}


	public static void activateESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickElement(By.xpath("//ul/li[.='"+connectionName+"']/following-sibling::li//div[contains(@class, 'ibw btn-switcher off ui-switcher')]"));
		
	}


	public static void clickEditConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickElement(By.xpath("//ul/li[.='"+connectionName+"']/following-sibling::li/i[@data-original-title='Edit this item.']"));
		
	}



	public static void createTestOnlyESPAction(String actionName,String connection) {
		setText(By.id("action-name"),actionName);
		selectDropList(By.id("connection-name"), "ECircle");
		clickButton("SEND_MESSAGE_TO_USER");
		setText(By.name("Message ID"), "1800403818");
		setText(By.name("email_address"), "webtest@mailinator.com");
		clickButton("Test ESP Action");
		verifyErrorMessage(By.className("notification"), "Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));
		
		
	}


	public static void editESPAction(String actionName) {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Actions");
		elementIsPresent(By.linkText(actionName));
		
	//	clickElement(By.xpath("//ul/li[.='"+actionName+"']/following-sibling::li/i[@data-original-title='Edit this item.']"));
		clickElement(By.xpath("//ul/li[.='"+actionName+"']/preceding::li[1]/i[@data-original-title='Edit this item.']"));
		
		setText(By.id("action-name"), "AutoECircleConnection");
	   setText(By.name("email_address"), "webtest@mailinator.com");
	   clickButton("Test ESP Action");
	   verifyErrorMessage(By.className("notification"), "Test passed successfully!");
	   elementIsPresent(By.xpath("//button[contains(@class, 'btn-success')]"));
	   clickButton("Save ESP Action");
	   threadSleep(2000);
	   elementIsPresent(By.className("notification"));
	   verifyInnerHTML(By.className("notifications"), "Successfully saved.");
	   elementIsPresent(By.xpath("//button[contains(@class, 'btn-success')]"));
	   	
		
		
	}


	public static void deleteESPAction(String actionName) {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Actions");
		elementIsPresent(By.linkText(actionName));
		clickElement(By.xpath("//ul/li[.='"+actionName+"']/preceding::li[1]/i[@data-original-title='Delete this item.']"));
		clickElement(By.xpath("//div[contains(@class, 'yes')]"));
		verifyErrorMessage(By.className("notification"), "Successfully deleted this item");
		
	}


	public static void noDeleteESPAction(String actionName) {
		elementIsPresent(By.linkText(actionName));
		clickElement(By.xpath("//ul/li[.='"+actionName+"']/preceding::li[1]/i[@data-original-title='Delete this item.']"));
		clickElement(By.xpath("//div[contains(@class, 'no btn btn-secondary')]"));
		elementIsPresent(By.linkText(actionName));
		
	}


	public static void clickEditAction(String actionName) {
		elementIsPresent(By.linkText(actionName));
		clickElement(By.xpath("//ul/li[.='"+actionName+"']/preceding::li[1]/i[@data-original-title='Edit this item.'])"));
		
	}


	public static void editESPTrigger(String triggerName) {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Triggers");
		elementIsPresent(By.linkText(triggerName));
		clickElement(By.xpath("//li[contains(@class, 'item name')]/a[contains(text(), '"+triggerName+"')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'btn-edit')]"));
		setText(By.id("triggers_name"), "Edit Abandoned Browse");
		elementIsPresent(By.xpath("//div[contains(@class, 'row cf')]/div/button[@data-original-title= 'Save this trigger campaign.']"));
		clickButton("Save Trigger");
		threadSleep(2000);
		elementIsPresent(By.className("notification"));
		verifyInnerHTML(By.xpath("//div[@class='notifications']"), "Successfully saved.");
		
	}


	public static void verifyESPTrigger(String triggerName) {
		elementIsPresent(By.xpath("//div[contains(@class, 'triggers-target')]//li[contains(@class, 'item name')]/a[contains(text(), '"+triggerName+"')]"));
		
	}


	public static void deleteESPTrigger(String triggerName) {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Triggers");
		elementIsPresent(By.linkText(triggerName));
		clickElement(By.xpath("//li[contains(@class,'item name')]/a[contains(text(),'"+triggerName+"')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'btn-delete')]"));
		clickElement(By.xpath("//div[contains(@class, 'yes')]"));
		threadSleep(2000);
		verifyErrorMessage(By.className("modal-body"), "Successfully deleted this item.");
	
		
	}


	public static void espTriggers() {
		Navigation.gotoURL("smartmanager/mail/triggers.page");
		clickButton("ESP Triggers");
		
	}


	public static void noDeleteESPTrigger(String triggerName) {
		elementIsPresent(By.linkText(triggerName));
		clickElement(By.xpath("//li[contains(@class,'item name')]/a[contains(text(),'"+triggerName+"')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'btn-delete')]"));
		clickElement(By.xpath("//div[contains(@class, 'no btn btn-secondary')]"));
		elementIsPresent(By.linkText(triggerName));
				
	}

	
		
	}

	

		
	

	

