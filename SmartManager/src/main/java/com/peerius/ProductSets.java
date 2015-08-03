package com.peerius;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.peerius.utils.Context;
import com.peerius.utils.Navigation;

public class ProductSets extends Context {

	public static void createProductSet(String title, String productPrefix,	String number) {

		clickButton("Add product set");
		verifyPageText(By.tagName("h4"), "Define product set");
		setText(By.name("name"), title);

		int numberOfProduct = Integer.parseInt(number);

		setText(By.className("visual-input"), productPrefix);
		pressKey("Enter");
		elementIsPresent(By.xpath("//ul[contains(@class,'tags')]"));
		List<WebElement> products = driverInstance.findElements(By.cssSelector(".tags-list.context-menu"));

		for (int i = 0; i < numberOfProduct; i++) {

			for (WebElement product : products) {

				Actions doubleClick = new Actions(driverInstance);

				doubleClick.moveToElement(product).doubleClick().sendKeys(Keys.ENTER).build().perform();
			}
		}
		clickLink("Finished");

		clickButton("Save product set");

	}
	

	public static void createProductSetCSV(String title, String filePath) {

		clickButton("Add Product set");
		verifyPageText(By.tagName("h4"), "Define Product set");
		setText(By.name("name"), title);
		setText(By.tagName("dz-message muted"), filePath);
		clickButton("Save Product set");

	}

	public static void verifyProductSet(String name) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elementIsPresent(By.linkText(name));

	}

	public static void gotoProductSet(String name) {

		SmartMerchandising.selectMenuOption("Merchandising", "Define product sets");
		elementIsPresent(By.linkText(name));
		clickLink(name);

	}

	public static void editProductset(String name) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td/a[text()='" + name
				+ "']//following::td[1]//div/a[contains(@class,'edit')]"));

	}

	public static void duplicateProductSet(String name) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elementIsPresent(By.linkText(name));
		clickElement(By.xpath("//td/a[text()='" + name+ "']//following::td[1]//div/a[contains(@class,'duplicate')]"));
		elementIsPresent(By.linkText(name+" copy"));

	}



	public static void deleteProductSet(String productSet) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elementIsPresent(By.linkText(productSet));
		clickElement(By.xpath("//td/a[text()='" + productSet
				+ "']//following::td[1]//div/a[contains(@class,'delete')]"));
		clickElement(By.xpath("//div[contains(@class,'yes')]"));
		verifyErrorMessage(By.className("notification"), "Successfully deleted");
		elementNotPresent(By.linkText(productSet));

	}
		
	public static void deleteButton(String productSet){
		
		clickElement(By.xpath("//td/a[text()='"+productSet+"']//following::td[1]//div/a[contains(@class,'delete')]"));
		
	}

	public static void verifyPreview(String numberoFpreview) {
		List<WebElement> previewList = driverInstance.findElements(By.cssSelector(".preview-ajax-products"));
		
		int number =Integer.parseInt(numberoFpreview);
	
	
		if(previewList.size()<=number){
			
			for(WebElement preview: previewList){
				
				System.out.println(preview.getText());
			}
		}
	}
	
	public static void verifyProductsInset(String productSet, String products){
		
		Navigation
		.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
			elementIsPresent(By.linkText(productSet));
		
		int number = Integer.parseInt(products);
		List<WebElement> productList = driverInstance.findElements(By.className("visual-tags"));
		
		if(number<=productList.size()){
			
			for(WebElement product: productList){
				
				System.out.println(product.getText());	
			}
		}
		
	}

	public static void addProductsTo(String productnumber, String productprefix) {
		
		int numberOfProduct = Integer.parseInt(productnumber);
		setText(By.className("visual-input"), productprefix);
		pressKey("Enter");
		elementIsPresent(By.xpath("//ul[contains(@class,'tags')]"));
		
		List<WebElement> products = driverInstance.findElements(By.cssSelector(".tags-list.context-menu"));

		for (int i = 0; i < numberOfProduct; i++) {

			for (WebElement product : products) {

				Actions doubleClick = new Actions(driverInstance);

				doubleClick.moveToElement(product).doubleClick().sendKeys(Keys.ENTER).build().perform();
			}
		}
		clickLink("Finished");
		clickButton("Save Product set");
		
	}

}
