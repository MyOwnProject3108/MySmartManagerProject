package com.stepdefinitions;

import com.peerius.PeeriusDebugInfo;

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

}
