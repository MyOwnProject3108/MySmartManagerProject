package com.peerius;

import org.openqa.selenium.By;

import com.peerius.utils.Context;

public class ProductSets extends Context{
	
	public static void createProductSet(String title, String productSKU){
		
		clickButton("Add Product set");
		verifyPageText(By.tagName("h4"), "Define Product set");
		setText(By.name("name"), title);
		setText(By.className("visual-input"), productSKU);
		clickButton("Save Product set");

	}
	
	public static void createProductSetCSV(String title, String filePath){
		
		
	}

	public static void verifyProductSet(String name){
		
	}

	public static void gotoProductSet(String name){
		
		
	}

	public static void editProductset(String name){
		
	}
	
	public static void duplicateProductSet(String name){
		
		
	}
	
	public static void clearProducts(){
		
		
	}
	
	public static void deleteProductSet(){
		
	}

	}



