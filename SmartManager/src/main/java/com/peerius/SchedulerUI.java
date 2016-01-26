package com.peerius;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

public class SchedulerUI extends Context {
	
	
	public void gotoScheduler(String schedulerURL){
		
		Navigation.gotoURL(schedulerURL);
				
	}
	
	
	public void selectMenu(String parent, String child){

		
		WebElement parentElement = new WebDriverWait(driverInstance, elementWaitTime)
		.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='v-menubar-menuitem-caption'][.='"+parent+"']")));
		
		parentElement.click();
		
		WebElement childElement = new WebDriverWait(driverInstance, elementWaitTime)
		.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='v-menubar-menuitem-caption'][.='"+child+"']")));
		
		
		Actions selectMenu = new Actions(driverInstance);
		
		selectMenu.moveToElement(childElement).click().build().perform();
	
	}
	
	
	public void setParameters(String parameters){
		
		setText(By.tagName("textarea"), parameters);
		
		
	}
	
	public static void clickSchedulerButton(String button){
		
		clickElement(By.xpath("//div[@class='v-button']/span[.='"+button+"']"));
		
	}
	
	
	public static void checkTaskFinished(String task){
		
		CharSequence searchtext =task.subSequence(0, 3);
		
		clickElement(By.xpath("//div[@class='v-captiontext'][.='Completed']"));
		setText(By.xpath("(//input)[2]"), searchtext.toString().toLowerCase());
		
		if(task.equalsIgnoreCase("Merger.scala")){
			
			task ="scala:offline.bigdata.Merger";
		}
		
		clickElement(By.xpath("//td/div[.='"+task+"']"));
		elementIsPresent(By.xpath("//div[@class='v-label'][contains(.,'finished')]"));
		
	}
	
	
	public static void startTask(String task){
		
		clickElement(By.cssSelector((".v-tree-node")));
		clickElement(By.cssSelector((".v-tree-node:nth-child(18)")));
		clickElement(By.cssSelector(".v-tree-node:nth-child(18) :nth-child(4)"));
		clickElement(By.xpath("//div//span[.='"+task+"']"));
		clickElement(By.xpath("//div[@class='v-captiontext'][.='Running']"));
		clickSchedulerButton("Refresh");
		
	}
	
	
	
	
	

}
