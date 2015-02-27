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
		List<WebElement> rulePositions =driverInstance.findElements(By.xpath("//tr[contains(.,'Rules')]//td[contains(.,'Product matched')]"));

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
	

	public static void verifyWidgetName(String widget){
		
			elementIsPresent(By.xpath("//h2[contains(.,'"+widget+"')]"));
			
			
	}
		
	
	public static void navigateToURl(String url){
		
		driverInstance.get(url);
		
	}
		
		
	}


