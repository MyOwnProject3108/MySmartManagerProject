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

		clickButton("Add Product set");
		verifyPageText(By.tagName("h4"), "Define Product set");
		setText(By.name("name"), title);

		int numberOfProduct = Integer.parseInt(number);

		setText(By.className("visual-input"), productPrefix);
		pressKey("Enter");
		elemementIsPresent(By.xpath("//ul[contains(@class,'tags')]"));
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
		elemementIsPresent(By.linkText(name));

	}

	public static void gotoProductSet(String name) {

		SmartMerchandising.selectMenuOption("Merchandising", "Define Product Sets");
		elemementIsPresent(By.linkText(name));
		clickLink(name);

	}

	public static void editProductset(String name) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elemementIsPresent(By.linkText(name));
		clickLink(name);
		clickElement(By.xpath("//td/a[text()='" + name
				+ "']//following::td[1]//div/a[contains(@class,'edit')]"));

	}

	public static void duplicateProductSet(String name) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elemementIsPresent(By.linkText(name));
		clickLink(name);
		clickElement(By.xpath("//td/a[text()='" + name
				+ "']//following::td[1]//div/a[contains(@class,'duplicate')]"));

	}

	public static void clearProducts(String productSet) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elemementIsPresent(By.linkText(productSet));
		clickLink(productSet);
		clickLink("Clear All Tags");

	}

	public static void deleteProductSet(String productSet) {

		Navigation
				.gotoURL("/smartmanager/sku/selectedproductsets/list.page?smartproduct=merchandising");
		elemementIsPresent(By.linkText(productSet));
		clickLink(productSet);
		clickElement(By.xpath("//td/a[text()='" + productSet
				+ "']//following::td[1]//div/a[contains(@class,'delete')]"));
		elemementIsPresent(By.linkText(productSet));

	}

}
