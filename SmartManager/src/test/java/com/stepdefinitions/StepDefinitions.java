package com.stepdefinitions;

import org.openqa.selenium.By;

import com.peerius.ProductSets;
import com.peerius.SmartMail;
import com.peerius.SmartMerchandising;
import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions extends SmartMerchandising {

	@Given("^I login as \"([^\"]*)\"$")
	public void I_login_as(String arg1) throws Throwable {
		Credential.smartManagerLogin(arg1);
	}

	@Then("^I should be on \"([^\"]*)\" page$")
	public void I_should_be_on_page(String arg1) throws Throwable {
		Context.verifyPageTitle(arg1);
	}

	@Then("^I search for site \"([^\"]*)\"$")
	public void I_search_for_site(String arg1) throws Throwable {
		Navigation.searchSite(arg1);
	}

	@When("^I click on \"([^\"]*)\" option in \"([^\"]*)\"$")
	public void I_click_on_option_in(String parent, String child) throws Throwable {
		selectMenuOption(child, parent);

	}

	@Then("^I should be on Merchandising \"([^\"]*)\" page$")
	public void I_should_be_on_Merchandising_page(String page) {

		verifyMerchandisingPage(page);

	}

	@Then("^I Create Simple Campaign with name \"(.*?)\"$")
	public void I_Create_Simple_Campaign(String campaign) throws Throwable {

		createCampaignSimple(campaign, "Product Page", "producthorizontal",
				"(r.pricerange=\"expensive\")");

	}
	
	@When("^I create simple camapign \"(.*?)\" with no rule$")
	public void i_create_simple_camapign_with_no_rule(String name) throws Throwable {
	    
		SmartMerchandising.createCampaignSimpleNoRule(name, "Product Page", "producthorizontal");
	}
	
	@When("^I add \"(.*?)\" for exclusion$")
	public void i_add_for_exclusion(String refcode) throws Throwable {
		SmartMerchandising.createExclusions(refcode);
		
	}
	
	
	@Then("^I Create invalid Campaign with name \"(.*?)\"$")
	public void i_Create_invalid_Campaign_with_name(String campaign) throws Throwable {
		createCampaignSimple(campaign, "Product Page", "producthorizontal",
				"(r.productset<>\"AJ10043\")");
	    
	}
	
	@Then("^I Should See Campaign \"([^\"]*)\" on Overview Page$")
	public void I_Should_See_Campaign_on_Overview_Page(String campaign)
			throws Throwable {
		verifyCampaign(campaign);
	}

	@Given("^I goto Campaign \"([^\"]*)\"$")
	public void I_goto_Campaign(String campaign) throws Throwable {
		gotoCampaign(campaign);

	}

	@Given("^Edit Campaign \"([^\"]*)\"$")
	public void Edit_Campaign(String campaign) throws Throwable {
		editCampaign(campaign);

	}

	@Then("^I should be on \"([^\"]*)\" Page$")
	public void I_should_be_on_Page(String arg1) throws Throwable {

		verifyURlText("edit");

	}

	@Given("^I activate Campaign \"([^\"]*)\"$")
	public void I_activate_Campaign(String campaign) throws Throwable {

		activateCampaign(campaign);

	}

	@Then("^Campaign should be Activated$")
	public void campaign_should_be_Activated() throws Throwable {
		Navigation.refreshPage();
		elementIsPresent(By
				.xpath("//td//a[@data-original-title=' Pause it ']"));

	}

	@Given("^I Duplicate Campaign \"([^\"]*)\"$")
	public void I_Duplicate_Campaign(String campaign) throws Throwable {

		duplicateCampaign(campaign);
	}

	@Given("^I Delete Campaign \"([^\"]*)\"$")
	public void I_Delete_Campaign(String name) throws Throwable {
			deleteCampaign(name);

	}

	@Then("^I should not see \"([^\"]*)\"$")
	public void I_should_not_see(String campaign) throws Throwable {

		elementNotPresent(By.linkText(campaign));

	}

	@Given("^I Pause Campaign \"(.*?)\"$")
	public void i_Pause_Campaign(String campaign) throws Throwable {
		pauseCampaign(campaign);
	}

	@Then("^Campaign Should be Paused$")
	public void campaign_Should_be_Paused() throws Throwable {
		elementIsPresent(By
				.xpath("//td//a[@data-original-title=' Activate it ']"));

	}
	
	@Then("^I should see Message \"(.*?)\"$")
	public void i_should_see_Message(String message) throws Throwable {
		verifyErrorMessage(By.className("notifications"), message);
	  
	}
	
	@Given("^I Create Campaign with \"(.*?)\"$")
	public void i_Create_Campaign_with_Test_Selenium(String name) throws Throwable {
		SmartMerchandising.gotoCreateCampaign();
		setText(By.id("name"), name);
		clickButton("Save Campaign");
		}
	
	@When("^I click on link \"(.*?)\"$")
	public void i_click_on_link(String linkName) throws Throwable {
		clickLink(linkName);
	}
	
	@When("^I click on button \"(.*?)\"$")
	public void i_click_on_button(String name) throws Throwable {
	   clickButton(name);
	}
	
	@When("^I Enter Text \"(.*?)\"$")
	public void i_enter_as(String text) throws Throwable {
	   setText(By.xpath("//textarea[contains(@id,'rec')]"), text);
		
	}
	
	
	
	@Then("^I should preview Message \"(.*?)\"$")
	public void i_should_preview_Message(String text) throws Throwable {
	 verifyInnerHTML(By.className("prev-ajax-error"), text);
	}


	@Given("^I Save the Campaign$")
	public void i_Save_the_Campaign() throws Throwable {
	    
	}

	@Then("^I Should Verify \"(.*?)\"$")
	public void i_Should_Verify(String position) throws Throwable {
		
		elementIsPresent(By.xpath("//li["+position+"]//div[contains(@class,'rule')]/p"));

	}
	
	@Given("^I Create Campaign \"(.*?)\" For \"(.*?)\"$")
	public void i_Create_Campaign_For(String campaign, String position) throws Throwable {
		createCampaign(campaign, position, "Product Page", "producthorizontal", "(r.gender=\"male\")");

	}
	
	@When("^I enter title \"(.*?)\"$")
	public void i_enter_title(String text) throws Throwable {
	   setText(By.id("sku_title"), text);
	}

	@Then("^I should see Master Rule \"(.*?)\"$")
	public void i_should_see_Master_Rule(String rule) throws Throwable {
	verifytextContent(By.id("master_advanced"), rule);
	
	}
	
	@Then("^I should see Rule \"(.*?)\" on Position \"(.*?)\"$")
	public void i_shoud_see_rule(String rule, String position){
		
		verifytextContent(By.xpath("//div/textarea[contains(@id,'advanced_btn_rec_"+position+"')]"), rule);
		
	}
	
	@When("^I Set Master Rule \"(.*?)\"$")
	public void i_Set_Master_Rule(String rule) throws Throwable {
	 setText(By.id("master_advanced"), rule);
		
	}

	
	@When("^I Create Product Set \"(.*?)\" and products number \"(.*?)\" with Suffix \"(.*?)\"$")
	public void i_Create_Product_Set_and_products_number_with_Suffix(String name, String productNumber, String productSuffix) throws Throwable {
				ProductSets.createProductSet(name, productSuffix,productNumber);
	
	}

	
	@Then("^I Should verify Product Sets \"(.*?)\"$")
	public void i_Should_verify_Product_Sets(String name) throws Throwable {
		SmartMerchandising.selectMenuOption("Merchandising", "Define Product Sets");
		clickLink(name);
		elementIsPresent(By.xpath("//div[@class='visual-tags']//descendant::div"));
		
	}
	
	@Given("^I Delete Product Set \"(.*?)\"$")
	public void i_Delete_Product_Set(String productSet) throws Throwable {
	   ProductSets.deleteProductSet(productSet);
	}

	@Then("^Product Set \"(.*?)\" Should be Deleted$")
	public void product_Set_Should_be_Deleted(String productSet) throws Throwable {
	 SmartMerchandising.selectMenuOption("Merchandising", "Define Product Sets");
	 elementNotPresent(By.linkText(productSet));
	}
	
	@Then("^Product Set \"(.*?)\" is not Deleted$")
	public void product_Set_is_not_Deleted(String productset) throws Throwable {
		SmartMerchandising.selectMenuOption("Merchandising", "Define Product Sets");
		elementIsPresent(By.linkText(productset));
	}

	
	
	@Then("^I Add New Rule \"(.*?)\" with Rule \"(.*?)\"$")
	public void i_Add_New_Rule(String ruleNumber, String rule) throws Throwable {
	   clickElement(By.xpath("//ul/li["+ruleNumber+"]//div[contains(@class,'actions')]/button[contains(.,'Edit Rule')]"));
	   clickElement(By.partialLinkText("Toggle"));
	   setText(By.xpath("//div/textarea[contains(@id,'advanced_btn_rec_"+ruleNumber+"')]"), rule	);
	   clickButton("Save Campaign");	
	}


	@When("^I select option \"(.*?)\"$")
	public void i_select_option(String rule) throws Throwable {
		clickElement(By.xpath("//option[.='"+rule+"']"));
	}

	
	@When("^I select operator \"(.*?)\"$")
	public void i_select_operator(String condOperator) throws Throwable {
		clickElement(By.xpath("//*[@id='sidebar']/ul/li//option[.='"+condOperator+"']"));
		
	} 
	
	@When("^I click Delete On Product Set \"(.*?)\"$")
	public void click_delete_button_productset(String productset){
		
		ProductSets.deleteButton(productset);
		clickElement(By.className("yes"));
	
	}
	
	@When("^I click Edit On Product Set \"(.*?)\"$")
	public void i_click_Edit_On_Product_Set(String productset) throws Throwable {
	  ProductSets.editProductset(productset);
	}
	
	
	@When("^I Enter rule Text \"(.*?)\"$")
	public void i_Enter_rule_Text(String text) throws Throwable {
		setText(By.xpath("//*[@id='sidebar']//input[@type='text']"),text);
	}
	
	@When("^Select Preview Products \"(.*?)\" with Ref \"(.*?)\"$")
	public void select_Preview_Products_with_Ref(String numberofproduct, String refcode) throws Throwable {
		
		SmartMerchandising.selectPreviewProductList(refcode, numberofproduct);
	   
	}

	@When("^Select Preview Category \"(.*?)\"$")
	public void select_Preview_Category(String category) throws Throwable {
		
		SmartMerchandising.selectPreviewCategory(category);
	 
	}
	
		@Then("^I Should See Preview with \"(.*?)\" Products$")
	public void i_Should_See_Preview_with_Products(String productNum) throws Throwable {
	    ProductSets.verifyPreview(productNum);
	}
		
		@Given("^I goto ProductSet \"(.*?)\"$")
		public void i_goto_ProductSet(String productset) throws Throwable {
			
			ProductSets.gotoProductSet(productset);
		
		}
			

		@Given("^I Add \"(.*?)\" Products with Suffix \"(.*?)\"$")
		public void i_Add_Products_with_Suffix(String productnumber, String productSuffix) throws Throwable {
			
			ProductSets.addProductsTo(productnumber, productSuffix);
		  
		}
		

		@Then("^I should see \"(.*?)\" products in \"(.*?)\"$")
		public void i_should_see_products_in(String products, String productSet) throws Throwable {
			ProductSets.verifyProductsInset(productSet, products);
		}


		@Given("^I Duplicate ProductSet \"(.*?)\"$")
		public void i_Duplicate_ProductSet(String productset) throws Throwable {
		   ProductSets.duplicateProductSet(productset);
		}

		@Then("^Apply Rule \"(.*?)\" To Position \"(.*?)\"$")
		public void apply_To_Position(String ruleNumber, String position) throws Throwable {
			
				clickLink("3. Recommendation Rules");
				 clickElement(By.xpath("//ul/li["+ruleNumber+"]//div[contains(@class,'actions')]/button[contains(.,'Edit Rule')]"));

				dragAndDrop(By.xpath("//div[contains(@data-original-title,'Drag')]/p[contains(.,'"+ruleNumber+"')]"), 
						By.xpath("//ul[@class='rules-grid']/li["+position+"]/div[contains(@id,'rule-target')]"));
				
				clickButton("Save Campaign");
				
		}
		
		@Then("^I Should Verify Rule  \"(.*?)\" at \"(.*?)\"$")
		public void should_verify_Rule_at_position(String position, String ruleNumber){
			
			elementIsPresent(By.xpath("//ul[@class='rules-grid']/li["+position+"]//p[contains(.,'"+ruleNumber+"')]"));
			
		}
		
		@Then("^I should be on Mail \"(.*?)\" page$")
		public void i_should_be_on_Mail_page(String page) throws Throwable {
		    
			Context.verifyMailPage(page);
		}

		@When("^I Set Name as \"(.*?)\"$")
		public void i_Set_Name_as(String name) throws Throwable {
		    
			setText(By.id("the_email_campaign_name"), name);
		}

		@Given("^I Create Simple Mail Campaign with name \"(.*?)\"$")
		public void i_Create_Simple_Mail_Campaign_with_name(String name) throws Throwable {
		   
			SmartMail.createSimpleMailCampaign(name, "Best Sellers by Conversion (last 90 days)");
		}
		
		@Then("^I Should See Mail Campaign \"(.*?)\" on Mail Overview Page$")
		public void i_Should_See_Mail_Campaign_on_Mail_Overview_Page(String campaign) throws Throwable {
		
			SmartMail.verifyMailCampaign(campaign);
		}
		
		@Given("^I Edit Mail campaign \"(.*?)\"$")
		public void i_Edit_Mail_campaign(String campaign) throws Throwable {
		    
			SmartMail.editMailCampaign(campaign);
		}
		
		@Given("^I goto Mail Campaign \"(.*?)\"$")
		public void i_goto_Mail_Campaign(String campaign) throws Throwable {
		    
			SmartMail.goToMailCampaign(campaign);
		}

		@Given("^I Duplicate Mail Campaign \"(.*?)\"$")
		public void i_Duplicate_Mail_Campaign(String campaign) throws Throwable {
		    
			SmartMail.duplicateMailCampaign(campaign);
		}

		@Given("^I Pause Mail campaign \"(.*?)\"$")
		public void i_pause_Mail_campaign(String campaign) throws Throwable {
		    
			SmartMail.pauseMailCampaign(campaign);
		}
		
		@Then("^Mail Campaign Should be Paused$")
		public void mail_Campaign_Should_be_Paused() throws Throwable {
			
			elementIsPresent(By.xpath("//*[@class='relative']/a[@data-original-title=' Activate it ']"));
		}

		@Given("^I Delete Mail Campaign \"(.*?)\"$")
		public void i_Delete_Mail_Campaign(String name) throws Throwable {
		    
			SmartMail.deleteMailCampaign(name);
		}
		
		@When("^I Set style with \"(.*?)\"$")
		public void i_Set_style_with(String value) throws Throwable {
		    
			SmartMail.setStyle(value);
		}

		@Then("^I should see the style applied with \"(.*?)\" in \"(.*?)\" in Widget Content Preview$")
		public void i_should_see_the_style_applied_with_in_in_Widget_Content_Preview(String attribute, String value) throws Throwable {
		    
			SmartMail.verifyStyleAttribute(attribute, value);
		}
		
		@When("^I Set style with value \"(.*?)\" for clientHeight and ClientWidth$")
		public void i_Set_style_with_value_for_clientHeight_and_ClientWidth(String value) throws Throwable {
			SmartMail.setStyle(value);
		}

		@Then("^I should see the style applied with value \"(.*?)\" in \"(.*?)\" in Widget Content Preview$")
		public void i_should_see_the_style_applied_with_value_in_in_Widget_Content_Preview(String value, String attribute) throws Throwable {
		    
			SmartMail.verifyStyleAttribute(value, attribute);
		}
		
		@Then("^I should see the HTML code for Styling$")
		public void i_should_see_the_HTML_code_for_Styling() throws Throwable {
		    
			elementIsPresent(By.id("mail_config_advanced"));
		}

		@Then("^The Link \"(.*?)\" should be visible$")
		public void the_Link_should_be_visible(String link) throws Throwable {
		   elementIsPresent(By.linkText(link));
		}
		
		@When("^I Set the Number of Products as \"(.*?)\"$")

		public void i_Set_the_Number_of_Products_as(String num) throws Throwable {

			setText(By.id("howMany"), num);
			pressKey("Enter");
		}
		
		@Then("^I Should see \"(.*?)\" Product Positions$")
		public void i_Should_see_Product_Positions(String position) throws Throwable {
		    
			SmartMail.verifyProductPosition(position);
		}

				
		@When("^I Set \"(.*?)\" at position \"(.*?)\"$")
		public void i_Set_at_position(String strategy, String position) throws Throwable {
		    
			SmartMail.setStrategyPerPosition(position, strategy);
		}


		@When("^I Uncheck the checkbox for User-Top ups$")
		public void i_Uncheck_the_checkbox_for_User_Top_ups() throws Throwable {
		    Context.clickElement(By.id("useTopups"));
		}
		
		@When("^I Specify Email address as \"(.*?)\"$")
		public void i_Specify_Email_address_as(String emailId) throws Throwable {
		    
			setText(By.xpath("//input[@type='email']"), emailId);
		}
		
		@Then("^Preview should Show Second Position Blank with No Email Rec$")
		public void preview_should_Show_Second_Position_Blank_with_No_Email_Rec() throws Throwable {
			
			SmartMail.verifyTopUpsDisabled();
		}    
			
		
		@When("^I Enable User-Top ups$")
		public void i_Enable_User_Top_ups() throws Throwable {
		    
			Context.clickElement(By.id("useTopups"));
			//SmartMail.enableUserTopUps();
		}
		
		@Then("^Preview should Show Second Position Topped up with Default Email Rec$")
		public void preview_should_Show_Second_Position_Topped_up_with_Default_Email_Rec() throws Throwable {
		    
			SmartMail.verifyTopUpsEnabled();
		}
		
		@When("^I select operator as \"(.*?)\"$")
		public void i_select_operator_as(String operator) throws Throwable {
		    
			clickElement(By.xpath("//option[.='"+operator+"']"));
		}
		
		@When("^I Enter rule Text as \"(.*?)\"$")
		public void i_Enter_rule_Text_as(String ruleText) throws Throwable {
		    
			setText(By.xpath("//input[contains(@class, 'autosearch')]"), ruleText);
		}

		
		@Then("^I Should See \"(.*?)\" Positions With Same Strategy, Expression and Hint$")
		public void i_Should_See_Positions_With_Same_Strategy_Expression_and_Hint(String position) throws Throwable {
		    
			SmartMail.verifyDuplicatePosition(position, "Product Catalog", "20", "Sale Price", "sale-product", "less than");
		}
		
		
		@Then("^I should see the HTML code for Email Recs$")
		public void i_should_see_the_HTML_code_for_Email_Recs() throws Throwable {
			
			elementIsPresent(By.xpath("//div[@class='clear clearfix']/div[@class='CodeMirror CodeMirror-wrap']"));
		}
		
		@When("^I Enter Text \"(.*?)\" in Email Placeholder$")
		public void i_Enter_Text_in_Email_Placeholder(String placeholder) throws Throwable {
		   setText(By.xpath("//input[contains(@id,'emailplaceholder')]"), placeholder);
		}
		
				
		@When("^I Set Tracking Code as \"(.*?)\"$")
		public void i_Set_Tracking_Code_as(String trackingcode) throws Throwable {
			setText(By.id("trackingCode"), trackingcode);
		    
		}
		
		@Then("^I Should see Tracking Code \"(.*?)\" Added In The Product url$")
		public void i_Should_see_Tracking_Code_Added_In_The_Product_url(String trackingcode) throws Throwable {
		  SmartMail.verifyTrackingCode(trackingcode);
		}
		
		@When("^I click on Email Rec \"(.*?)\"$")
		public void i_click_on_Email_Rec(String arg1) throws Throwable {
		   driverInstance.findElement(By.xpath("//td[1]/a[1]/img")).click();
		}
		
		@When("^I set Custom Email Attribute \"(.*?)\"$")
		public void i_set_Custom_Email_Attribute(String attribute){
		    
			driverInstance.findElement(By.xpath("//button[@value='" + attribute +"']")).click();
		}
				
		@Then("^I should see the saved Custom Email Attribute setting$")
		public void i_should_see_the_saved_Custom_Email_Attribute_setting(){
		    
			elementIsPresent(By.xpath("//button[@value='genre']/i[@class='sm-icon-check-checked']"));
		}
		
		@When("^I Create Connection \"(.*?)\" for ESP \"(.*?)\"$")
		public void i_Create_Connection_for_ESP(String connectionName, String esp) throws Throwable {
		
			SmartMail.createESPConnection(esp, connectionName);
		
		}
		
		@When("^I Create Action \"(.*?)\" for ESP \"(.*?)\"$")
		public void i_Create_Action_for_ESP(String actionName, String connection) throws Throwable {
		    SmartMail.createESPAction(actionName,connection);
		}
		
		@When("^I Create Trigger with name \"(.*?)\" for \"(.*?)\" from \"(.*?)\"$")
		public void i_Create_Trigger_with_name_for_from(String triggerName, String espActionName, String position) throws Throwable {
		   SmartMail.createESPTrigger(triggerName,espActionName,position);
		}
		
		@Then("^I Should See ESP Connection \"(.*?)\" on Mail Triggers Page$")
		public void i_Should_See_ESP_Connection_on_Mail_Triggers_Page(String connectionName) throws Throwable {
			elementIsPresent(By.linkText(connectionName));
		}

		
		@Then("^I Should See ESP Action \"(.*?)\" on Mail Triggers Page$")
		public void i_Should_See_ESP_Action_on_Mail_Triggers_Page(String actionName) throws Throwable {
			elementIsPresent(By.linkText(actionName));
		}
		
		@When("^I Create Test Connection \"(.*?)\" for ESP \"(.*?)\"$")
		public void i_Create_Test_Connection_for_ESP(String esp, String connectionName) throws Throwable {
		  SmartMail.createTestOnlyESPConnection(esp,connectionName);
		}

		@Then("^I Should not See ESP Connection \"(.*?)\" on Mail Triggers Page$")
		public void i_Should_not_See_ESP_Connection_on_Mail_Triggers_Page(String connectionName) throws Throwable {
			
			//Need to modify xpath
			clickButton("ESP Connections");
			elementNotPresent(By.xpath("//li[contains(@class,'item name')]/a[.='"+connectionName+"']"));
			
		}
		
		@Given("^I goto ESP Connection \"(.*?)\"$")
		public void i_goto_ESP_Connection(String connectionName) throws Throwable {
		    SmartMail.gotoESPConnection(connectionName);
		}
			
		@Given("^I Delete ESP Connection \"(.*?)\"$")
		public void i_Delete_ESP_Connection(String connectionName) throws Throwable {
		    SmartMail.deleteESPConnection(connectionName);
		}
		
		@When("^I click No to Delete ESP Connection \"(.*?)\"$")
		public void i_click_No_to_Delete_ESP_Connection(String connectionName) throws Throwable {
			SmartMail.noDeleteESPConnection(connectionName);
		}
		
		@When("^I deactivate \"(.*?)\" ESP Connection$")
		public void i_deactivate_ESP_Connection(String connectionName) throws Throwable {
		   SmartMail.deactiveESPConnection(connectionName);
		}
		
		@When("^I activate \"(.*?)\" ESP Connection$")
		public void i_activate_ESP_Connection(String connectionName) throws Throwable {
		    SmartMail.activateESPConnection(connectionName);
		}
		
		@When("^I click Edit button for \"(.*?)\" ESP Connection$")
		public void i_click_Edit_button_for_ESP_Connection(String connectionName) throws Throwable {
		 SmartMail.clickEditConnection(connectionName);
		}
		
		
		@Then("^I Should See disabled delete button for \"(.*?)\" ESP Connection$")
		public void i_Should_See_disabled_delete_button_for_ESP_Connection(String esp) throws Throwable {
		   elementIsPresent(By.xpath("//ul/li[.='"+esp+"']/following-sibling::li//i[contains(@class,'disabled')]"));
		}
		


		@When("^I Create Test Action \"(.*?)\" for ESP \"(.*?)\"$")
		public void i_Create_Test_Action_for_ESP(String actionName, String connection) throws Throwable {
			SmartMail.createTestOnlyESPAction(actionName,connection);
		}

		@Then("^I Should not See ESP Action \"(.*?)\" on Mail Triggers Page$")
		public void i_Should_not_See_ESP_Action_on_Mail_Triggers_Page(String ActionName) throws Throwable {
			elementNotPresent(By.xpath("//div[contains(@class, 'actions-target')]//li/a[contains(text(), '"+ActionName+"')]"));
		}
		
			
		@Given("^I Delete ESP Action \"(.*?)\"$")
		public void i_Delete_ESP_Action(String actionName) throws Throwable {
		  SmartMail.deleteESPAction(actionName);
		}
		
		@When("^I click No to Delete ESP Action \"(.*?)\"$")
		public void i_click_No_to_Delete_ESP_Action(String actionName) throws Throwable {
		    SmartMail.noDeleteESPAction(actionName);
		}
		
		
		@Given("^I click Edit button for \"(.*?)\" ESP Action$")
		public void i_click_Edit_button_for_ESP_Action(String actionName) throws Throwable {
			SmartMail.clickEditAction(actionName);
		}

		@Then("^I Should See disabled delete button for \"(.*?)\" ESP Action$")
		public void i_Should_See_disabled_delete_button_for_ESP_Action(String esp) throws Throwable {
			  elementIsPresent(By.xpath("//ul/li[.='"+esp+"']/following-sibling::li//i[contains(@class,'disabled')]"));
		}
		
			
		@Then("^I Should See ESP Trigger \"(.*?)\" on Mail Triggers Page$")
		public void i_Should_See_ESP_Trigger_on_Mail_Triggers_Page(String triggerName) throws Throwable {
		   SmartMail.verifyESPTrigger(triggerName);
		}
		
		@Given("^I Delete ESP Trigger \"(.*?)\"$")
		public void i_Delete_ESP_Trigger(String triggerName) throws Throwable {
		    SmartMail.deleteESPTrigger(triggerName);
		}
		
		@Then("^I Should not See ESP Trigger \"(.*?)\" on Mail Triggers Page$")
		public void i_Should_not_See_ESP_Trigger_on_Mail_Triggers_Page(String triggerName) throws Throwable {
			elementNotPresent(By.xpath("//ul/li//a[contains(text(), '"+triggerName+"')]"));
		}
		
	
		@Given("^I click No to Delete ESP Trigger \"(.*?)\"$")
		public void i_click_No_to_Delete_ESP_Trigger(String triggerName) throws Throwable {
		    SmartMail.noDeleteESPTrigger(triggerName);
		}
		
		@Given("^I activate \"(.*?)\" ESP Trigger$")
		public void i_activate_ESP_Trigger(String triggerName) throws Throwable {
		    SmartMail.activateESPTrigger(triggerName);
		}
		
		@Given("^I deactivate \"(.*?)\" ESP Trigger$")
		public void i_deactivate_ESP_Trigger(String triggerName) throws Throwable {
		   SmartMail.deactivateESPTrigger(triggerName);
		}
		
		@Given("^I click Edit button for \"(.*?)\" ESP Trigger$")
		public void i_click_Edit_button_for_ESP_Trigger(String triggerName) throws Throwable {
		    SmartMail.clickEditTrigger(triggerName);
		}

		@Then("^I Should See disabled delete button for \"(.*?)\" ESP Trigger$")
		public void i_Should_See_disabled_delete_button_for_ESP_Trigger(String triggerName) throws Throwable {
		  elementIsPresent(By.xpath("//ul/li/a[contains(text(), '"+triggerName+"')]/following::li[contains(@class, 'actions')]/i[contains(@class, 'disabled')]"));
		  		    
		}

		@Given("^I set data for new \"(.*?)\"$")
		public void i_set_data_for_new(String element) throws Throwable {
				    
			SmartMail.setDataForValidations(element);
		}

		@Given("^I set \"(.*?)\" field as \"(.*?)\"$")
		public void i_set_field_with_id_as(String field, String value) throws Throwable {
		  
			setText(By.id(field), value);
		}

	
		@Then("^I should see Action Types Menu$")
		public void i_should_see_Action_Types_Menu() throws Throwable {
				    
			elementIsPresent(By.xpath("//div[@class='cf control-action-type']"));
		}
				
		@Given("^I select ESP connection as \"(.*?)\"$")
		public void i_select_ESP_connection_as(String ConnectionName) throws Throwable {
				    
			selectDropList(By.id("connection-name"), ConnectionName);
		}
				
		@When("^I enter \"(.*?)\" in field \"(.*?)\"$")
		public void i_enter_in_field_name(String value, String field) throws Throwable {
				    
			setText(By.name(field), value);
		}
				
		
		@When("^I delete the Criteria$")
		public void i_delete_the_Criteria() throws Throwable {
			
			clickElement(By.xpath("//ul[@id='in-use']//i"));	
			elementNotPresent(By.xpath("//ul[@id='in-use']//i"));

}
		//Use to Wait for Element with ClassName
		@Then("^I Wait for Element \"(.*?)\"$")
		public void i_Wait_for_Element(String className) throws Throwable {
			elementIsPresent(By.xpath("//*[contains(@class,'"+className+"')]"));
		  
		}

		
}
