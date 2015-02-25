package com.peerius;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.peerius.utils.Context;

public class PeeriusDebugInfo extends Context {
	
	public static void verifyRule(String rule, String positions){
		
	int number = Integer.parseInt(positions);
		
		if(number<2){
			
			elementIsPresent(By.xpath("//*[@id='peeriusDebug']//tr[contains(.,'Rule')]//td[contains(.,'"+rule+"']"));
			
			
		}
		
		List<WebElement> rulePositions =driverInstance.findElements(By.xpath("//*[@id='peeriusDebug']//tr[contains(.,'Rule')]//td[contains(.,'"+rule+"']"));
		
		for (int i =0;i<=number;++i){
			
			for(WebElement position: rulePositions){
				
				
				
				
			}
			
		}
		
		
		
	}

}
