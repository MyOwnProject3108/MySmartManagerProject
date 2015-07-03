package com.stepdefinitions;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import com.peerius.PeeriusDebugInfo;
import com.peerius.SmartMail;
import com.peerius.SmartMerchandising;
import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class EndToEndSteps extends PeeriusDebugInfo {

	@Then("^I should see \"(.*?)\" in the debug$")
	public void i_should_see_in_the_debug(String widget) {

		verifyWidgetName(widget);

	}

	@Then("^I should see Rule \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_see_Rule_in_Positions(String rule, String positions) {

		verifyRule(rule, positions);

	}
	
	@Then("^I should see Complex Rule \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_see_Complex_Rule_in_Positions(String rule, String positions) {
		
		verifyComplexRule(rule,positions);
	}


	@Given("^I navigate to URL \"(.*?)\"$")
	public void i_navigate_to_URL(String url){

		navigateToURl(url);

	}	
	
	@Then("^I should not see Rule \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_not_see_Rule_in_Positions(String rule, String positions)
			throws Throwable {

	    
	}
	
	@Given("^I Create AB Group with Details$")
	public void i_Create_AB_Group_with_Details(List<String> dataInfo) {
		
		String group = dataInfo.get(5);
		String page = dataInfo.get(6);
		String widget = dataInfo.get(7);
		String group_a_percent = dataInfo.get(8);
		String group_b_percent = dataInfo.get(9);
	
		SmartMerchandising.createABgroup(group, page, widget, group_a_percent, group_b_percent);

	    
	}
	
	@Given("^I goto URL \"(.*?)\"$")
	public void i_goto_URL(String url) {
		Navigation.gotoURL(url);
	}

	@Given("^I deactivate AB group$")
	public void i_deactivate_AB_group() {
		javaScriptExe("$(\"input[value='Deactivate']\").click()");
		acceptAlert();
		elementNotPresent(By.className("box widgets_A"));
		
	}
	
	@Then("^I Specify random Email address as \"(.*?)\"$")
	public void i_Specify_random_Email_address_as(String randomEmail)  {
		
		SmartMail.generateRandomEmail(randomEmail);
	} 
	
	@Given("^I Goto Random Inbox$")
	public void i_Goto_Random_Inbox() {
	 Navigation.gotoURL("http://mailinator.com/inbox.jsp?to="+SmartMail.emailgenerated);
	}

	
	@Then("^I should be on mailinator \"(.*?)\" page$")
	public void i_should_be_on_mailinator_page(String page){
		Context.verifyPageTitle(page);
	}
	
	@Then("^I click on the Email \"(.*?)\"$")
	public void i_click_on_the_Email(String emailName){
	   SmartMail.openEmail(emailName);
	}

	
	@Then("^Switch Frame \"(.*?)\"$")
	public void switch_Frame(String name) {
	   Navigation.switchToFrame(name);
	}
	
	@Then("^I should see GeneratioStrategy \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_see_GeneratioStrategy_in_Positions(String strategy, String positions){
	    PeeriusDebugInfo.verifyStrategy(strategy, positions);
	}
	

	@Given("^I search for \"(.*?)\" Fallback ProductSet$")
	public void i_search_for_Fallback_ProductSet(String fallbackset){
		SmartMail.searchFallBackSKU(fallbackset);
	}
	
	
	@Given("^I Enter Hint Parameter Text as \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_Enter_Hint_Parameter_Text_as_in_Positions(String hintParam, String position) {
		 setText(By.xpath("//*[@id='item"+position+"-hints']/div/input"),hintParam);
	   
	}
	
	
	@Then("^I should see Debug hint \"(.*?)\"$")
	public void i_should_see_Debug_in_Positions(String hint){
		PeeriusDebugInfo.verifyDebugHint(hint);
	}
	
	@Then("^I Set Rule for \"(.*?)\"$")
	public void i_Set_Rule_for(String exp){
		setText(By.className("advanced_expression"), exp);
	}
	
	@Given("^I Register with random Email address as \"(.*?)\"$")
	public void i_Register_with_random_Email_address_as(String registerRandomEmail) throws Throwable {
		SmartMail.registerRandomEmail(registerRandomEmail);
	   		
	}
	
	@Given("^I Goto Registered Random Inbox$")
	public void i_Goto_Registered_Random_Inbox() throws Throwable {
		   Navigation.gotoURL("http://mailinator.com/inbox.jsp?to="+SmartMail.registerRandomEmailGenerated);
		//   threadSleep(3000);
	}

	   
	
	
	
	}
	
	


	
	
	


