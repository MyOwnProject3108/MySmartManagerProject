package stepsdefinitions;
import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.peerius.COREManager;
import com.peerius.utils.Navigation;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestHook {

	@Before
	public void setUp() throws MalformedURLException{
		
		
		System.out.println("Starting");
		COREManager.openBrowser();
		COREManager.maximize();
		/**DesiredCapabilities capability = new DesiredCapabilities();
        capability.setPlatform(Platform.LINUX);
        capability.setBrowserName( "firefox" );
        capability.setVersion("34");
        COREManager.driverInstance = new RemoteWebDriver( new URL( "http://192.168.0.231:5555/wd/hub" ), capability );
		
		**/
	}

	
	
	public void tearDown(){
		
		
	}
	//@After
	public void shutdownBrowser(){
		Navigation.closeBrowser();
	}
	//@AfterClass
	//TODO 
	//Need to check driverInstance and Enable ScreenShot Accordingly
	@After
	public  void tearDownAndtakeErrorScreenshot(Scenario scenario) {
        scenario.write("Current Page URL is " + COREManager.getCurrentUrl());
        try {
        	
        	byte[] screenshot =((TakesScreenshot)COREManager.driverInstance).getScreenshotAs(OutputType.BYTES);
          scenario.embed(screenshot, "image/png");
        } catch (WebDriverException screenShotException) {
            System.err.println(screenShotException.getMessage());
        }
        
    	Navigation.closeBrowser();
        
    }}


	

