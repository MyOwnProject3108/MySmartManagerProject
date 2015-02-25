package com.peerius;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.peerius.utils.Context;

public class PeeriusDebugInfo extends Context {
	
	public static void verifyRule(String rule, String positions){
	
		
	int number = Integer.parseInt(positions);
		
		
		
		List<WebElement> rulePositions =driverInstance.findElements(By.xpath("//tr[contains(.,'Rule')]//td"));

		//tr[contains(.,'Rule')]//td[contains(.,'"+rule+"']
		int size = rulePositions.size();
		
		if(number==size){
			
			for(WebElement singleElement: rulePositions){
				
					Assert.assertTrue(singleElement.getAttribute("innerHTML").contains(rule));
			}
		}
			else{
				
				Assert.fail("Expected "+size+"But Rule "+"Was Found");
				
			}		
		}


	public static void verifyWidgetName(String widget){
		
			elementIsPresent(By.xpath("//h2[contains(.,'"+widget+"')]"));
			
			
	}
		
	
	public static void navigateToURl(String url){
		
		driverInstance.get(url);
		
	}
		
		
	}


