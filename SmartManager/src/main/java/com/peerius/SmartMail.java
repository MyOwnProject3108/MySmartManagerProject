package com.peerius;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

public class SmartMail extends Context {

	public static Random random = new Random();
	public static String emailgenerated;
	public static String registerRandomEmailGenerated;

	public static void createSimpleMailCampaign(String name, String strategy) {
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
		clickElement(By
				.xpath("//td/a[text()='"
						+ name
						+ "']//following::td//a[@data-original-title='Delete mail campaign']"));
		clickElement(By.xpath("//div[contains(@class,'yes')]"));
		verifyErrorMessage(By.className("notification"), "Successfully deleted");
		elementNotPresent(By.linkText(name));

	}

	public static void duplicateMailCampaign(String campaign) {

		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By
				.xpath("//td/a[text()='"
						+ campaign
						+ "']//following::td//a[@data-original-title='Duplicate mail campaign']"));
		elementIsPresent(By.linkText(campaign + " copy"));

	}

	public static void pauseMailCampaign(String campaign) {

		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By.xpath("//td/a[text()='" + campaign
				+ "']//following::td//a[@data-original-title=' Pause it ']"));
		Navigation.refreshPage();
		elementIsPresent(By
				.xpath("//td//a[@data-original-title=' Activate it ']"));

	}

	public static void editMailCampaign(String campaign) {

		Navigation.gotoURL("/smartmanager/mail/list.page");
		elementIsPresent(By.linkText(campaign));
		clickElement(By
				.xpath("//td/a[text()='"
						+ campaign
						+ "']//following::td//a[@data-original-title='Edit mail campaign']"));
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

		WebElement style = driverInstance.findElement(By
				.id("productInfoPreview"));
		String actual = style.getAttribute(attribute).trim().toString();

		Assert.assertEquals(value, actual);
	}

	public static void verifyProductPosition(String position) {

		int number = Integer.parseInt(position);

		for (int i = 1; i <= number; ++i) {

			elementIsPresent(By.id("mail-item" + i + ""));

		}
	}

	public static void setStrategyPerPosition(String position, String strategy) {

		elementIsPresent(By.id("mail-item" + position + ""));

		List<WebElement> elements = driverInstance.findElements(By
				.xpath("(//div[@class='visual-tag'])[" + position + "]"));

		for (WebElement element : elements) {

			if (element.isDisplayed()) {

				clickElement(By
						.xpath("//i[contains(@class,'visual-tags-close')]"));
				clickElement(By.xpath("(//div[@class='visual'])[" + position
						+ "]"));
			}

		}

		clickElement(By.xpath("(//div[@class='visual'])[" + position + "]"));
		setText(By.xpath("(//input[@class='visual-input'])[" + position + "]"),
				strategy);
		List<WebElement> strategies = driverInstance
				.findElements(By
						.xpath("//ul[@class='visual-list context-menu hide']/li[contains(@style,'list-item')]"));

		for (WebElement option : strategies) {

			if (option.getText().equalsIgnoreCase(strategy)) {

				Actions selectOption = new Actions(driverInstance);
				selectOption.doubleClick(option).build().perform();

			}

		}

	}

	public static void enableUserTopUps() {

		WebElement checkbox = driverInstance.findElement(By.id("useTopups"));
		if (checkbox.getAttribute("checked").equals(false)) {

			driverInstance.findElement(By.id("useTopups")).click();
		}

	}

	public static void verifyTopUpsDisabled() {

		WebElement imgHeight = driverInstance.findElement(By
				.xpath("//td[2]/a/img[@title='Just for you']"));

		// TODO This does not make much sense logic needs to be check
		// Passes with this Assert.assertEquals("300",
		// imgHeight.getAttribute("height"));

		Assert.assertEquals("1", imgHeight.getAttribute("height"));

	}

	public static void verifyTopUpsEnabled() {

		WebElement imgHeight = driverInstance.findElement(By
				.xpath("//td[2]/a/img[@title='Just for you']"));

		Assert.assertNotEquals("1", imgHeight.getAttribute("height"));

	}

	public static void verifyDuplicatePosition(String position,
			String strategy, String ruleValue, String ruleText,
			String hintOption, String operator) {
		// TODO Xpaths needs to be check
		int number = Integer.parseInt(position);

		for (int i = 0; i >= number; ++i) {

			elementIsPresent(By.id("mail-item" + i + ""));

			clickElement(By.xpath("//a[@href='#item" + i + "-rec']"));

			verifyInnerHTML(
					By.xpath("//li[@id='mail-item"+i+"']//div[@class='visual-tags-value']"),
					strategy);

			clickElement(By.xpath("//a[@href='#item" + i + "-exp']"));

			verifySelectOption(ruleText,
					By.xpath("(//select[@class='exp_left_hand'])[" + i + "]"));

			verifySelectOption(
					operator,
					By.xpath("(//select[@class='exp_op operatoroptions'])[" + i
							+ "]"));

			Assert.assertTrue(driverInstance
					.findElement(
							By.xpath("(//input[contains(@class, 'autosearch')])["
									+ i + "]")).getAttribute("value")
					.contains(ruleValue));

			clickElement(By.xpath("//a[@href='#item" + i + "-hints']"));

			verifySelectOption(
					hintOption,
					By.xpath("(//select[@class='mail_hint_select'])[" + i + "]"));
		}

	}

	public static void verifyTrackingCode(String trackingcodeUrl) {

		Navigation.selectWindowPopup();
		String currentUrl = driverInstance.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains(trackingcodeUrl));

	}

	public static void generateRandomEmail(String randomEmail) {
		int randomInt = random.nextInt(100000);
		emailgenerated = randomEmail + randomInt;
		setText(By.xpath("//input[@type='email']"), emailgenerated
				+ "@mailinator.com");

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

		List<WebElement> productsets = driverInstance.findElements(By
				.cssSelector(".sku_set_context.context-menu"));

		for (WebElement productset : productsets) {

			Actions selectFallback = new Actions(driverInstance);
			selectFallback.moveToElement(productset).click().build().perform();

		}
	}

	public static void setExpression(String rule, String position) {
		clickElement(By.partialLinkText("Toggle"));
		setText(By.xpath("//*[@id='item" + position + "-exp']/textarea"), rule);
	}

	public static void createESPConnection(String ESPname, String connectionName) {

		setText(By.id("name"), connectionName);

		if (ESPname.equalsIgnoreCase("Silverpop")) {

			Credential.espUserLogin("silverpop");
			selectDropList(By.name("esp"), "Silverpop");
			setText(By.id("realm"), "http://api2.silverpop.com/SoapApi");
		}

		if (ESPname.equalsIgnoreCase("Ecircle")) {

			Credential.espUserLogin("ecircle");
			selectDropList(By.id("esp"), "Teradata (ECircle)");
			setText(By.id("realm"), "http://peerius.cust-mta.com");

		}
		if (ESPname.equalsIgnoreCase("SmartCast")) {

			Credential.espUserLogin("smartcast");
			selectDropList(By.id("esp"), "SmartCast");
			setText(By.id("realm"), "http://uk56.em.sdlproducts.com");
		}

		clickButton("Test ESP Connection");
		verifyErrorMessage(By.className("notifications"),
				"Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));
		clickButton("Save ESP Connection");
		threadSleep(2000);
		elementIsPresent(By.className("notification"));
		verifyErrorMessage(By.className("notifications"), "Successfully saved.");
		elementIsPresent(By
				.xpath("//button[contains(@class,'btn-success disabled')]"));
	}

	public static void createESPAction(String actionName, String espConnection) {
		setText(By.id("action-name"), actionName);

		if (espConnection.equalsIgnoreCase("ECircle")) {
			selectDropList(By.id("connection-name"), "ECircle");
			clickButton("Send message");
			setText(By.name("Message ID"), "1800403818");
		}

		if (espConnection.equalsIgnoreCase("SmartCast")) {
			selectDropList(By.id("connection-name"), "SmartCast");
			clickButton("Send message");
			setText(By.name("Creative Name"), "74535_Abandon_201410v01");
			setText(By.name("Template ID"), "116");
			setText(By.name("List Category"), "74535 - Abandon Basket");
			setText(By.name("Sender Email"), "test@peerius.com");
		}

		if (espConnection.equalsIgnoreCase("Silverpop")) {
			selectDropList(By.id("connection-name"), "Silverpop");
			clickButton("Send message");
			setText(By.name("listID"), "4066625");
			setText(By.name("Message ID"), "7875507");

		}

		if (espConnection.equalsIgnoreCase("E2EConnection")) {
			selectDropList(By.id("connection-name"), "E2EConnection");
			clickButton("Send message");
			setText(By.name("Message ID"), "1800605946");
		}

		setText(By.name("email_address"), "webtest@mailinator.com");
		clickButton("Test ESP Action");
		verifyErrorMessage(By.className("notification"),
				"Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));
		clickButton("Save ESP Action");
		threadSleep(2000);
		elementIsPresent(By.className("notification"));
		verifyErrorMessage(By.className("notifications"), "Successfully saved.");
		elementIsPresent(By
				.xpath("//button[contains(@class,'btn-success disabled')]"));

	}

	public static void createESPTrigger(String triggerName,
			String espActionName, String triggerType, String inactivityPeriod) {
		setText(By.id("triggers_name"), triggerName);

		WebElement element = driverInstance.findElement(By
				.id("trigger_action_id"));
		List<WebElement> options = element.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().equals(espActionName)) {
				option.click();
			}

		}

		dragAndDrop(
				By.xpath("//ul[@id='available']//div[.='" + triggerType + "']"),
				By.id("in-use"));
		elementIsPresent(By.xpath("//ul[@id='in-use']/li/div[.='" + triggerType
				+ "']"));
		elementIsPresent(By.name("after"));
		setText(By.name("after"), inactivityPeriod);
		elementIsPresent(By.xpath("//button[contains(.,'Save Trigger')]"));
		clickButton("Save Trigger");
		elementIsPresent(By.className("notification"));
		verifyErrorMessage(By.className("notifications"), "Successfully saved");
		elementIsPresent(By
				.xpath("//div[@class='triggers-target']//a[contains(text(), '"
						+ triggerName + "')]"));

	}

	public static void verifyESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));

	}

	public static void createTestOnlyESPConnection(String connectionName,
			String ESPname) {
		setText(By.id("name"), connectionName);
		Credential.espUserLogin("ecircle");
		selectDropList(By.id("esp"), ESPname);
		setText(By.id("realm"), "http://peerius.cust-mta.com");
		clickButton("Test ESP Connection");
		verifyErrorMessage(By.className("notifications"),
				"Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));

	}

	public static void gotoESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickLink(connectionName);

	}

	public static void deleteESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickElement(By.xpath("//li[contains(@class,'item name')]/a[.='"
				+ connectionName + "']/following::i[2]"));
		clickElement(By.xpath("//div[contains(@class, 'yes')]"));
		threadSleep(2000);
		verifyErrorMessage(By.className("modal-body"),
				"Successfully deleted this item.");
		Navigation.refreshPage();

	}

	public static void noDeleteESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickElement(By
				.xpath("//ul/li[.='"
						+ connectionName
						+ "']/following-sibling::li/i[@data-original-title='Delete this item.']"));
		clickElement(By
				.xpath("//div[contains(@class, 'no btn btn-secondary')]"));
		elementIsPresent(By.linkText(connectionName));

	}

	public static void deactiveESPConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickElement(By
				.xpath("//ul/li[.='"
						+ connectionName
						+ "']/following-sibling::li//div[contains(@class,'btn-switcher')]"));

	}

	public static void activateESPConnection(String connectionName) {
		clickButton("ESP Connections");
		elementIsPresent(By.linkText(connectionName));
		clickElement(By
				.xpath("//ul/li[.='"
						+ connectionName
						+ "']/following-sibling::li//div[contains(@class,'btn-switcher')]"));

	}

	public static void clickEditConnection(String connectionName) {
		elementIsPresent(By.linkText(connectionName));
		clickElement(By
				.xpath("//ul/li[.='"
						+ connectionName
						+ "']/following-sibling::li/i[@data-original-title='Edit this item.']"));

	}

	public static void createTestOnlyESPAction(String actionName,
			String connection) {
		setText(By.id("action-name"), actionName);
		selectDropList(By.id("connection-name"), connection);
		clickButton("Send message");
		setText(By.name("Message ID"), "1800403818");
		setText(By.name("email_address"), "webtest@mailinator.com");
		clickButton("Test ESP Action");
		verifyErrorMessage(By.className("notification"),
				"Test passed successfully!");
		elementIsPresent(By.xpath("//button[contains(@class,'btn-success')]"));

	}

	public static void deleteESPAction(String actionName) {
		elementIsPresent(By.linkText(actionName));
		clickElement(By.xpath("//li[contains(@class,'item name')]/a[.='"
				+ actionName + "']/preceding::i[1]"));
		clickElement(By.xpath("//div[contains(@class, 'yes')]"));
		verifyErrorMessage(By.className("modal-body"),
				"Successfully deleted this item.");

	}

	public static void noDeleteESPAction(String actionName) {
		elementIsPresent(By.linkText(actionName));
		clickElement(By
				.xpath("//div[contains(@class, 'actions-target')]//li//a[contains(text(), '"
						+ actionName
						+ "')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'btn-delete')]"));
		clickElement(By
				.xpath("//div[contains(@class, 'no btn btn-secondary')]"));
		elementIsPresent(By.linkText(actionName));

	}

	public static void clickEditAction(String actionName) {
		elementIsPresent(By.linkText(actionName));
		clickElement(By
				.xpath("//div[contains(@class, 'actions-target')]//li//a[contains(text(), '"
						+ actionName
						+ "')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'btn-edit')]"));

	}

	public static void verifyESPTrigger(String triggerName) {
		elementIsPresent(By
				.xpath("//div[contains(@class, 'triggers-target')]//li//a[contains(text(), '"
						+ triggerName + "')]"));

	}

	public static void deleteESPTrigger(String triggerName) {
		elementIsPresent(By.linkText(triggerName));
		clickElement(By.xpath("//li[contains(@class,'item name')]/a[.='"
				+ triggerName + "']/following::i[2]"));
		clickElement(By.xpath("//div[contains(@class, 'yes')]"));

	}

	public static void noDeleteESPTrigger(String triggerName) {
		elementIsPresent(By.linkText(triggerName));
		clickElement(By
				.xpath("//li[contains(@class,'item name')]/a[contains(text(),'"
						+ triggerName
						+ "')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'btn-delete')]"));
		clickElement(By
				.xpath("//div[contains(@class, 'no btn btn-secondary')]"));
		Navigation.refreshPage();
		elementIsPresent(By.linkText(triggerName));

	}

	public static void activateESPTrigger(String triggerName) {
		elementIsPresent(By.linkText(triggerName));
		clickElement(By
				.xpath("//div[contains(@class, 'triggers-target')]//ul/li[.='"
						+ triggerName
						+ "']/following-sibling::li//div[contains(@class, 'ibw btn-switcher off ui-switcher')]"));

	}

	public static void deactivateESPTrigger(String triggerName) {
		elementIsPresent(By.linkText(triggerName));
		clickElement(By
				.xpath("//div[@class='triggers-target']//ul/li[.='"
						+ triggerName
						+ "']/following-sibling::li//div[contains(@class, 'btn-switcher on')]"));

	}

	public static void clickEditTrigger(String triggerName) {
		elementIsPresent(By.linkText(triggerName));
		clickElement(By
				.xpath("//ul//a[.= 'AbandonedBasket']/following::li[contains(@class, 'actions')][1]//i[contains(@class,'edit')]"));
		// clickElement(By.xpath("//li[contains(@class, 'item name')]/a[contains(text(), '"+triggerName+"')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'btn-edit')]"));

	}

	public static void setDataForValidations(String element) {

		if (element.equalsIgnoreCase("Connection")) {
			setText(By.id("name"), "Test");
			Credential.espUserLogin("ecircle");
			selectDropList(By.id("esp"), "Teradata (ECircle)");
			setText(By.id("realm"), "http://peerius.cust-mta.com");
		}

		if (element.equalsIgnoreCase("Action")) {
			setText(By.id("action-name"), "Test");
			selectDropList(By.id("connection-name"), "ECircle");
			clickButton("Send message");
			setText(By.name("Message ID"), "1800403818");
			setText(By.name("email_address"), "webtest@mailinator.com");
		}

		if (element.equalsIgnoreCase("Trigger")) {
			setText(By.id("triggers_name"), "Test");

			WebElement element1 = driverInstance.findElement(By
					.id("trigger_action_id"));
			List<WebElement> options = element1.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase("ECircleAction")) {
					option.click();
				}
				if (option.getText().equalsIgnoreCase("TestAction")) {
					option.click();
				}

			}
			dragAndDrop(By.xpath("//ul[@id='available']/li/div[.='Home']"),By.id("in-use"));
			setText(By.name("after"), "5");
			elementIsPresent(By.xpath("//ul[@id='in-use']/li/div[.='Home']"));

		}

	}

	public static void registerRandomEmail(String registerRandomEmail) {

		Navigation
				.gotoURL("http://showcase-dev.peerius.com/index.php/customer/account/create/");
		setText(By.id("firstname"), "Peerius");
		setText(By.id("lastname"), "Test");
		int randomInt = random.nextInt(100000);
		registerRandomEmailGenerated = registerRandomEmail + randomInt;
		setText(By.id("email_address"), registerRandomEmailGenerated
				+ "@mailinator.com");
		setText(By.id("password"), "password");
		setText(By.id("confirmation"), "password");
		clickButton("Submit");

	}

}
