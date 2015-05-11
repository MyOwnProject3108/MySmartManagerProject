package com.stepdefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.peerius.PeeriusDebugInfo;
import com.peerius.SmartMail;
import com.peerius.SmartMerchandising;
import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EndToEndSteps extends PeeriusDebugInfo {

	@Then("^I should see \"(.*?)\" in the debug$")
	public void i_should_see_in_the_debug(String widget) throws Throwable {

		verifyWidgetName(widget);

	}

	@Then("^I should see Rule \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_see_Rule_in_Positions(String rule, String positions)
			throws Throwable {

		verifyRule(rule, positions);

	}
	
	@Then("^I should see Complex Rule \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_see_Complex_Rule_in_Positions(String rule, String positions) throws Throwable {
		verifyComplexRule(rule,positions);
	}


	@Given("^I navigate to URL \"(.*?)\"$")
	public void i_navigate_to_URL(String url) throws Throwable {

		navigateToURl(url);

	}	
	
	@Then("^I should not see Rule \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_not_see_Rule_in_Positions(String rule, String positions)
			throws Throwable {

	    
	}
	
	@Given("^I Create AB Group with Details$")
	public void i_Create_AB_Group_with_Details(List<String> dataInfo) throws Throwable {
		
		String group = dataInfo.get(5);
		String page = dataInfo.get(6);
		String widget = dataInfo.get(7);
		String group_a_percent = dataInfo.get(8);
		String group_b_percent = dataInfo.get(9);
	
		SmartMerchandising.createABgroup(group, page, widget, group_a_percent, group_b_percent);

	    
	}
	
	@Given("^I goto URL \"(.*?)\"$")
	public void i_goto_URL(String url) throws Throwable {
		Navigation.gotoURL(url);
	}

	@Given("^I deactivate AB group$")
	public void i_deactivate_AB_group() throws Throwable {
		javaScriptExe("$(\"input[value='Deactivate']\").click()");
		acceptAlert();
		elementNotPresent(By.className("box widgets_A"));
		
	}
	
	@Then("^I Specify random Email address as \"(.*?)\"$")
	public void i_Specify_random_Email_address_as(String randomEmail) throws Throwable {
		
		SmartMail.generateRandomEmail(randomEmail);
	} 
	
	@Given("^I Goto Random Inbox$")
	public void i_Goto_Random_Inbox() throws Throwable {
	 Navigation.gotoURL("http://mailinator.com/inbox.jsp?to="+SmartMail.emailgenerated);
	}

	
	@Then("^I should be on mailinator \"(.*?)\" page$")
	public void i_should_be_on_mailinator_page(String page) throws Throwable {
		Context.verifyPageTitle(page);
	}
	
	@Then("^I click on the Email \"(.*?)\"$")
	public void i_click_on_the_Email(String emailName) throws Throwable {
	   SmartMail.openEmail(emailName);
	}

	
	@Then("^Switch Frame \"(.*?)\"$")
	public void switch_Frame(String name) throws Throwable {
	   Navigation.switchToFrame(name);
	}
	
	@Then("^I should see GeneratioStrategy \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_should_see_GeneratioStrategy_in_Positions(String strategy, String positions) throws Throwable {
	    PeeriusDebugInfo.verifyStrategy(strategy, positions);
	}
	

	@Given("^I search for \"(.*?)\" Fallback ProductSet$")
	public void i_search_for_Fallback_ProductSet(String fallbackset) throws Throwable {
		SmartMail.searchFallBackSKU(fallbackset);
	}
	
	
	@Given("^I Enter Hint Parameter Text as \"(.*?)\" in \"(.*?)\" Positions$")
	public void i_Enter_Hint_Parameter_Text_as_in_Positions(String hintParam, String position) throws Throwable {
		 setText(By.xpath("//*[@id='item"+position+"-hints']/div/input"),hintParam);
	   
	}
	

}
