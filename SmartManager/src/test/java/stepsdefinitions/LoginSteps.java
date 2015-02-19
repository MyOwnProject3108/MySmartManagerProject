package stepsdefinitions;

import org.openqa.selenium.By;

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

		createCampaign(campaign, "Product Page", "product",
				"(r.gender=\"male\")");

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
}
