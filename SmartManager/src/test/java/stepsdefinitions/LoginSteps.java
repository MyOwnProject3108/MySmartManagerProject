package stepsdefinitions;


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
	selectMenuOption(arg2,arg1);

	}
	
	@Then("^I should be on Merchandising \"([^\"]*)\" page$")
	public void I_should_be_on_Merchandising_page(String page){
		
		//deleteCampaign("AutoCreate");
		verifyMerchandisingPage(page);

	}
	
	@Then("^I Create Simple Campaign with name \"([^\"]*)\"$")
	public void I_Create_Simple_Campaign(String campaign) throws Throwable {
		
		createCampaign(campaign, "Product Page", "product", "(r.gender=\"male\")");
	    
	}

	@Then("^I Should See Campaign \"([^\"]*)\" on Overview Page$")
	public void I_Should_See_Campaign_on_Overview_Page(String campaign) throws Throwable {
	    verifyCampaign(campaign);
	}

	
}

