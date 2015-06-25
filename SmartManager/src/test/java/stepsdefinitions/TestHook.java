package stepsdefinitions;


import java.net.MalformedURLException;

import com.peerius.COREManager;
import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestHook {

	@Before
	public void setUp(Scenario scenario) throws MalformedURLException{
	

		System.out.println("Starting --- Scenario --- " + scenario.getName());
		COREManager.openBrowser();
		COREManager.deleteCookie();
		Navigation.gotoLoginPage();
		COREManager.addCookie("'peerius_pass_peeriusdebug", "1");
		Context.javaScriptExe("document.cookie='peerius_pass_peeriusdebug=1; expires=Sat, 1 Jan 2050 12:00:00 UTC'; path=/';");
		COREManager.maximize();

	}

	
	@After
	public void tearDown(Scenario scenario){
		
		COREManager.tearDownAndtakeErrorScreenshot(scenario);
		Navigation.closeWindow();
		Navigation.closeBrowser();
		
		
	}


	}


	

