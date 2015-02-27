package com.stepdefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.peerius.PeeriusDebugInfo;
import com.peerius.SmartMerchandising;
import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

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

	
	
	


}
