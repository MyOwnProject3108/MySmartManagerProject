package com.peerius;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

public class PeeriusDebugInfo extends Context {
	
	public static void verifyRule(String rule, String positions){
	int number = Integer.parseInt(positions);
			try{
				List<WebElement> rulePositions =driverInstance.findElements(By.xpath("//tr[contains(.,'Rules')]//td[contains(text(),'Product matched rule \""+rule+"')]"));
			int size = rulePositions.size();
			if(number==size){
			for(WebElement singleElement: rulePositions){
				Assert.assertTrue(singleElement.getAttribute("textContent").contains(rule));
			}
		}
			else{
				Assert.fail("Expected "+number+" But Rule "+size+" Was Found");
				}		
		}
			catch(NoSuchElementException e){
		Navigation.refreshPage();
	}
		
	}
	
	
	public static void verifyStrategy(String strategy, String positions){
		int number = Integer.parseInt(positions);
		try{
			List<WebElement> strategyPositions =driverInstance.findElements(By.xpath(" //tr[contains(.,'GeneratioStrategy')]//td[contains(.,'"+strategy+"')]"));
			int size = strategyPositions.size();
			if(number==size){
				for(WebElement singleElement:strategyPositions){
					Assert.assertTrue(singleElement.getAttribute("textContent").contains(strategy));
				}
			}
			else{
				Assert.fail("Expected "+number+" But strategy " +size+ " Was Found");
			}
		}catch(NoSuchElementException e){
			Navigation.refreshPage();
		}
			
		}
	
	public static void verifyComplexRule(String rule, String positions){
		
	List<WebElement> rulePositions =driverInstance.findElements(By.xpath(" //tr[contains(.,'Rules')]//td"));
	//tr[contains(.,'Rules')]//td/text()[2][contains(.,'Product matched rule \""+rule+"')]
		
		int number = Integer.parseInt(positions);
		int size = rulePositions.size();
			
		if(number==size |number!=size){
			
			for(WebElement singleElement: rulePositions){
			
				Assert.assertTrue(singleElement.getAttribute("textContent").contains(rule));
			}
		}
			else{
				
				Assert.fail("Expected "+number+" But Rule "+size+" Was Found");
				
			}		

	}
		

	
	

	public static void verifyWidgetName(String widget){
		
			addCookie("'peerius_pass_peeriusdebug", "1");
			Navigation.refreshPage();
			elementIsPresent(By.xpath("//h2[contains(.,'"+widget+"')]"));
	}

	
	public static void navigateToURl(String url){
		
		driverInstance.get(url);
		
	}
		
		
	}


