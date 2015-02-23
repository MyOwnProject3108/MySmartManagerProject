package stepsdefinitions;

import org.openqa.selenium.By;

import com.peerius.ProductSets;
import com.peerius.SmartMerchandising;
import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps extends SmartMerchandising {

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
	public void I_click_on_option_in(String arg1, String arg2) throws Throwable {
		selectMenuOption(arg2, arg1);

	}

	@Then("^I should be on Merchandising \"([^\"]*)\" page$")
	public void I_should_be_on_Merchandising_page(String page) {

		verifyMerchandisingPage(page);

	}

	@Then("^I Create Simple Campaign with name \"([^\"]*)\"$")
	public void I_Create_Simple_Campaign(String campaign) throws Throwable {

		createCampaignSimple(campaign, "Product Page", "product",
				"(r.gender=\"male\")");

	}
	
	@Then("^I Create invalid Campaign with name \"(.*?)\"$")
	public void i_Create_invalid_Campaign_with_name(String campaign) throws Throwable {
		createCampaignSimple(campaign, "Product Page", "product",
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

		elemementIsPresent(By
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
		elemementIsPresent(By
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


	
	@When("^click on button \"(.*?)\"$")
	public void click_on_button(String SaveCampaign) throws Throwable {
	  clickButton(SaveCampaign);
	
	}
	
	@Given("^I Save the Campaign$")
	public void i_Save_the_Campaign() throws Throwable {
	    
	}

	@Then("^I Should Verify \"(.*?)\"$")
	public void i_Should_Verify(String position) throws Throwable {
		
		elemementIsPresent(By.xpath("//li["+position+"]//div[contains(@class,'selected')]"));

	}
	
	@Given("^I Create Campaign \"(.*?)\" For \"(.*?)\"$")
	public void i_Create_Campaign_For(String campaign, String position) throws Throwable {
		createCampaign(campaign, position, "Product Page", "product", "(r.gender=\"male\")");

	}
	
	@When("^I enter title \"(.*?)\"$")
	public void i_enter_title(String text) throws Throwable {
	   setText(By.id("sku_title"), text);
	}

	@Then("^I should see Master Rule \"(.*?)\"$")
	public void i_should_see_Master_Rule(String rule) throws Throwable {
	verifyInnerHTML(By.id("master_advanced"), rule);
	
	}
	
	@Then("^I should see Rule \"(.*?)\" on Position \"(.*?)\"$")
	public void i_shoud_see_rule(String rule, String position){
		
		verifyInnerHTML(By.xpath("//div/textarea[contains(@id,'advanced_btn_rec_"+position+"')]"), rule);
		
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
		elemementIsPresent(By.xpath("//div[@class='visual-tags']//descendant::div"));
		
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
		elemementIsPresent(By.linkText(productset));
	}

	
	
	@Then("^I Add New Rule \"(.*?)\" with Rule \"(.*?)\"$")
	public void i_Add_New_Rule(String ruleNumber, String rule) throws Throwable {
	   clickButton("Edit Rule...");
	   clickLink("Toggle advanced");
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


}

