

import java.net.MalformedURLException;

import com.peerius.COREManager;
import com.peerius.utils.Navigation;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestHook {

	@Before
	public void setUp(Scenario scenario) throws MalformedURLException{
	

		System.out.println("Starting --- Scenario --- " + scenario.getName());
		COREManager.openBrowser();
		COREManager.maximize();

	}

	
	@After
	public void tearDown(cucumber.api.Scenario scenario){
		
		COREManager.tearDownAndtakeErrorScreenshot(scenario);
		Navigation.closeWindow();
		Navigation.closeBrowser();
		
		
	}


	}


	

