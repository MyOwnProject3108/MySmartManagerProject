package com.peerius;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.opera.core.systems.OperaDriver;
import com.peerius.utils.Navigation;

/**
 * CORE Operations for Browser operations.
 * 
 * @author Zach Suleiman
 */

public class COREManager {

	public static WebDriver driverInstance;
	private static String currentDir;
	public static String siteUrl;
	private static BrowserName defaultBrowserName;
	public static ImmutableMap<String, Credential> userProfiles;
	public static Properties prop;
	public String browserName;
	public static String remoteDriverUrl;
	public static String remoteBrowserName;
	public static String remoteBrowserVersion;
	public static String remotePlatform;
	public static int pageLoadTime;
	public static int scriptLoadTime;
	public static int elementWaitTime;
	public static int implicitWait;

	static {
		currentDir = System.getProperty("user.dir");
		System.out.println(currentDir);
		prop = new Properties();
		ImmutableMap.Builder<String, Credential> builder = ImmutableMap
				.builder();
		try (InputStream input = new FileInputStream(
				currentDir
				+ "\\src\\main\\resources\\config.properties")) {
			prop.load(input);

			// siteUrl
			siteUrl = prop.getProperty("siteUrl", "").trim();

			// remoteHubUrl and remoteBrowserName
			remoteDriverUrl = prop.getProperty("remoteDriverUrl", "").trim();
			remoteBrowserName = prop.getProperty("remoteBrowserName", "")
					.trim();

			// Remote Driver Platform and Browser Version
			remotePlatform = prop.getProperty("remotePlatform", "").trim();
			remoteBrowserVersion = prop.getProperty("remoteBrowserVersion", "")
					.trim();
			
			//Browser Wait Times
			pageLoadTime  = Integer.parseInt( prop.getProperty("pageToLoad", "").trim());
			scriptLoadTime = Integer.parseInt( prop.getProperty("scriptToLoad", "").trim());
			elementWaitTime = Integer.parseInt( prop.getProperty("elementToLoad", "").trim());
			implicitWait = Integer.parseInt(prop.getProperty("browserImplicitWait", "").trim());
			
			// User profiles
			for (String profile : prop.getProperty("userprofile", "")
					.split(",")) {
				String username = prop.getProperty(profile + ".username");
				String password = prop.getProperty(profile + ".password");
				builder.put(profile, new Credential(username, password));
			}
			userProfiles = builder.build();

			// Defaults Driver to Firefox
			defaultBrowserName = BrowserName.findByName(
					prop.getProperty("browser")).or(BrowserName.FIREFOX);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public enum BrowserName {
		FIREFOX("firefox"), CHROME("chrome"), OPERA("opera"), SAFARI("safari"), IE(
				"ie"), HTMLUNIT("htmlunit"), PHANTOMJS("phantomjs"), REMOTEDRIVER(
						"remoteMachine");

		String name;

		BrowserName(String name) {
			this.name = name;
		}

		public static Optional<BrowserName> findByName(String browserName) {
			for (BrowserName bName : values()) {
				if (bName.name.equalsIgnoreCase(browserName)) {
					return Optional.of(bName);
				}
			}
			return Optional.absent();
		}

	}

	/**
	 * openBrowser method initiates any specified browser Browsers available
	 * HTMLUNIT, CHROME,IE, SAFARI, & OPERA !!!When running Chrome & IE in a
	 * machine driver executable must be specified
	 */
	public static void openBrowser(BrowserName browserName) {

		try {

			switch (browserName) {

			case FIREFOX:

				FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(true);
				profile.setPreference(
						"browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"image/jpg,text/csv,text,application/excel,application/pdf");
				profile.setPreference("browser.download.dir",
						System.getProperty("user.home"));
				driverInstance = new FirefoxDriver(profile);
				driverInstance.manage().timeouts()
				.pageLoadTimeout(5, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.setScriptTimeout(5, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.implicitlyWait(10, TimeUnit.SECONDS);
				break;

			case OPERA:
				driverInstance = new OperaDriver();
				break;

			case CHROME:

				if (System.getProperty("os.name").contains("Windows")) {

					System.setProperty("webdriver.chrome.driver", currentDir
							+ "/drivers/chromedriver.exe");
				}

				else {
					System.setProperty("webdriver.chrome.driver", currentDir
							+ "/drivers/chromedriver");
				}
				driverInstance = new ChromeDriver();
				DesiredCapabilities capability = new DesiredCapabilities();
				capability.setJavascriptEnabled(true);
				capability.setCapability("acceptSslCerts", true);
				capability.setCapability("handlesAlerts", true);

				driverInstance.manage().timeouts()
				.pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.setScriptTimeout(scriptLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.implicitlyWait(implicitWait, TimeUnit.SECONDS);
				break;

			case IE:
				System.setProperty("webdriver.ie.driver", currentDir
						+ "/drivers/IEDriverServer.exe");
				DesiredCapabilities ie = new DesiredCapabilities();
				ie.setJavascriptEnabled(true);
				ie.setCapability("handlesAlerts", true);
				driverInstance = new InternetExplorerDriver();
				driverInstance.manage().timeouts()
				.pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.setScriptTimeout(scriptLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.implicitlyWait(implicitWait, TimeUnit.SECONDS);
				break;

			case HTMLUNIT:
				driverInstance = new HtmlUnitDriver();
				driverInstance.manage().timeouts()
				.pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.setScriptTimeout(scriptLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.implicitlyWait(implicitWait, TimeUnit.SECONDS);
				break;

			case PHANTOMJS:
				DesiredCapabilities phantom = new DesiredCapabilities();
				phantom.setJavascriptEnabled(true);
				phantom.setCapability("takesScreenshot", true);
				phantom.setCapability("acceptSslCerts", false);
				phantom.setCapability("handlesAlerts", true);

				if (System.getProperty("os.name").contains("Mac")) {
					phantom.setCapability(
							PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
							currentDir + "/phantomjs/bin/phantomjs");
				}

				if (System.getProperty("os.name").contains("Windows")) {
					phantom.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
							currentDir + "\\phantomjs\\phantomjs.exe");
				}
				
				

				else if (System.getProperty("os.name").contains("Linux")) {
					phantom.setCapability(
							PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
							"/usr/local/bin/phantomjs");
				} 
				driverInstance = new PhantomJSDriver(phantom);
				driverInstance.manage().timeouts()
				.pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.setScriptTimeout(scriptLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.implicitlyWait(implicitWait, TimeUnit.SECONDS);
				break;

			case SAFARI:

				DesiredCapabilities safari = new DesiredCapabilities();
				safari.setJavascriptEnabled(true);
				safari.setCapability("handlesalert", true);

				driverInstance = new SafariDriver(safari);

				driverInstance.manage().timeouts()
				.pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.setScriptTimeout(scriptLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.implicitlyWait(implicitWait, TimeUnit.SECONDS);
				break;

			case REMOTEDRIVER:

				DesiredCapabilities remoteDriver = new DesiredCapabilities();
				remoteDriver.setPlatform(Platform.valueOf(remotePlatform));
				remoteDriver.setBrowserName(remoteBrowserName);
				remoteDriver.setVersion(remoteBrowserVersion);
				driverInstance = new RemoteWebDriver(new URL(remoteDriverUrl),
						remoteDriver);
				((RemoteWebDriver) driverInstance)
				.setFileDetector(new LocalFileDetector());

				driverInstance.manage().timeouts()
				.pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.setScriptTimeout(scriptLoadTime, TimeUnit.SECONDS);
				driverInstance.manage().timeouts()
				.implicitlyWait(implicitWait, TimeUnit.SECONDS);
				break;
			}

		} catch (WebDriverException e) {

			System.out.println("Cannot Be Open Browser\n" + e.getMessage());

		} catch (NullPointerException e) {
			System.out
			.println("No Driver Found, Check for Approriate DRIVERS (Internet Explorer & Chrome)\n");

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage()
					+ "Check RemoteDriver Information\n");

		}

	}

	public static void openBrowser() {
		if (driverInstance != null) {
			Navigation.closeBrowser();
			openBrowser(defaultBrowserName);

		} else {
			openBrowser(defaultBrowserName);
		}
	}

	/**
	 * Gets current Url or empty string.
	 *
	 * @return Returns current Url or empty string.
	 */
	public static String getCurrentUrl() {
		String fullUrl = driverInstance.getCurrentUrl();
		try {
			URL url = new URL(fullUrl);
			return url.getFile();
		} catch (Exception e) {
			return "";
		}

	}

	public static class Credential {
		public String username;
		public String password;

		public Credential(String username, String password) {
			this.username = username;
			this.password = password;
		}
		
		public static void smartManagerLogin(String userprofile) {

			Credential userCredentials = userProfiles.get(userprofile);

			if (userCredentials != null) {

				Navigation.gotoLoginPage();
				driverInstance.findElement(By.id("emailaddress")).sendKeys(
						userCredentials.username);
				driverInstance.findElement(By.id("password")).sendKeys(
						userCredentials.password);
				driverInstance.findElement(By.tagName("button")).click();

				Boolean pageTitle = new WebDriverWait(driverInstance, 15L)
				.until(ExpectedConditions
						.titleContains("Peerius Smart Manager"));
				Assert.assertTrue("Page Title Exist", pageTitle);

			}
	}
		
		public static void logOutUser(){
			
			WebElement accountLink = driverInstance.findElement(By.xpath("//button[contains(@class,'dropdown')]"));
			
			Actions logout = new Actions(driverInstance);
			
			logout.moveToElement(accountLink).click().build().perform();
			WebElement logoutLink = driverInstance.findElement(By.linkText("Log Out"));
			
			logout.moveToElement(logoutLink).click().build().perform();
			
		}

	

	}
	
	
	public static void maximize(){
		
		driverInstance.manage().window().maximize();
		
	}
	
	public static void threadSleep(int time){
		
		try{
			Thread.sleep(time);
			driverInstance.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		}
		
		catch(Exception e){
			
			System.err.println("Error In Thread - Sleep");
		}
	}

}
