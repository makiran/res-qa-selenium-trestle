package com.corelogic.automation.trestle;

import net.sf.saxon.functions.Parse;
import net.sourceforge.jtds.jdbc.Driver;

import org.openqa.selenium.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.selenesedriver.SwitchToFrame;
import org.openqa.selenium.support.FindBy;

import com.corelogic.automation.Global;
import com.corelogic.automation.Wraper;
import com.corelogic.utaf.keyword.internal.selenium.GetAttribute;
import com.corelogic.utaf.keyword.internal.selenium.XPathCount;
import com.corelogic.utaf.main.internal.Using;
import java.util.regex.*;
public class TpProductsPage extends Wraper {
	
	private static volatile TpProductsPage tpproductspage = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpProductsPage setStringData(String stringData) {
		this.stringData = stringData;
		return TpProductsPage.getInstance();
	}
	
	public TpProductsPage setData(Map<String, String> data) {
		this.data = data;
		return TpProductsPage.getInstance();
	}

	public static TpProductsPage getInstance() {
		if (tpproductspage == null) {
			synchronized (TpProductsPage.class) {
				if (tpproductspage == null) {
					tpproductspage = new TpProductsPage();
				}
			}
		}
		return tpproductspage;
	}
	public void verifyTpProductsPage(){
		String methodName="verifyTpProductsPage";
		click("//*[@href='/TP/Products']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='TPProductsList']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Product Manager page after clicking on the notification");
			verifyProductTableColumns();
			addNewProduct();
			click("//*[@href='/TP/Connections']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is back on Connection Manager page");
				verifyNotifications();
			}
			else
				assertError("Connections button is not working", true);
		}
		else
			failLogInfo(methodName, "User is unable to navigate to Product Manager page after clicking on the notification");
	}
	
	
	public void verifyProductTableColumns(){
		String methodName="verifyProductTableColumns";
		String tpColumn1="PRODUCT NAME";
		String tpColumn2="DATA FEED - DELIVERY METHOD";
		String tpColumn3="STATUS";
		if(findElement(By.xpath("//*[@id='TPProductsList']/thead/tr/th[1]")).getText().equals(tpColumn1)) {
			passLogInfo(methodName, "Product Name column is available");
		}
		else
			failLogInfo(methodName, "Product Name column is not available");
		if(findElement(By.xpath("//*[@id='TPProductsList']/thead/tr/th[2]")).getText().trim().equals(tpColumn2)) {
			passLogInfo(methodName, "Data Feed - Delivery Method column is available");
		}
		else
			failLogInfo(methodName, "Data Feed - Delivery Method is not available");
		if(findElement(By.xpath("//*[@id='TPProductsList']/thead/tr/th[3]")).getText().equals(tpColumn3)) {
			passLogInfo(methodName, "Status column is available");
		}
		else
			failLogInfo(methodName, "Status column is not available");
	}
	
	
	
	public void addNewProduct(){
		String methodName="addNewProduct	";
		click("//*[@href='/TP/Products/Create']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'ADD TRESTLE FEED')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Add/Edit Product page");
			if(findElement(By.xpath("//*[contains(text(),'ADD TRESTLE FEED')]")).isEnabled()) {
				passLogInfo(methodName, "Add Trestle Feed button is enabled");
				findElement(By.xpath("//*[@id='Name']")).sendKeys(data.get("ProductName"));
				findElement(By.xpath("//*[@id='ProductURL']")).sendKeys(data.get("ProductURL"));
				findElement(By.xpath("//*[@id='Description']")).sendKeys(data.get("Description"));
				click("//*[@value='Save']", Using.XPATH, TIMEOUT);
				findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("ProductName"));
				if(isObjectExist("//*[contains(text(),'@@@')]".replace("@@@", data.get("ProductName")), Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "New product "+data.get("ProductName")+" is added");
					findElement(By.xpath("//*[contains(text(),'Edit')]")).click();
					if(findElement(By.xpath("//*[@id='Name']")).getAttribute("value").equals(data.get("ProductName"))) {
						passLogInfo(methodName, "User is able to Edit the product");
						click("//*[contains(text(),'ADD TRESTLE FEED')]", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@data-id='ddlDataFeeds']", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "User is navigated to Add Data Feed page of the product");
							select("//*[@id='ddlDataFeeds']", Using.XPATH, "IDX - RETS", TIMEOUT);
							click("//*[@value='Save']", Using.XPATH, TIMEOUT);
							wait(3000);
							getDriver().switchTo().activeElement();
							if(isObjectExist("//*[@class='modal-backdrop fade in']", Using.XPATH, TIMEOUT))
								passLogInfo(methodName, "New Password pop up message is displayed");
							else
								failLogInfo(methodName, "New Password pop up message is not displayed");	
							if(isObjectExist("//*[@id='mdlBtnYes']", Using.XPATH, TIMEOUT))
								passLogInfo(methodName, "Yes button is displayed");
							else
								failLogInfo(methodName, "Yes button is not displayed");
							click("//*[@id='mdlBtnYes']", Using.XPATH, TIMEOUT);
							passLogInfo(methodName, "Cicked on Yes button");
							wait(3000);
							getDriver().switchTo().activeElement();
							if(isObjectExist("//*[@id='passwordDisplay']", Using.XPATH, TIMEOUT))
								passLogInfo(methodName, "Feed Password is displayed");
							else
								failLogInfo(methodName, "Feed Password is not displayed");
							click("//*[@id='passwordDisplayClose']", Using.XPATH, TIMEOUT);
							passLogInfo(methodName, "Cicked on Close button");
							wait(3000);
							getDriver().switchTo().defaultContent();
							if(isObjectExist("//*[contains(text(),'IDX - RETS')]", Using.XPATH, TIMEOUT)) {
								passLogInfo(methodName, "Data Feed is added to the product");
								click("//*[@value='Save']", Using.XPATH, TIMEOUT);
								if(isObjectExist("//*[contains(text(),'@@@')]".replace("@@@", data.get("ProductName")), Using.XPATH, TIMEOUT))
									passLogInfo(methodName, "New product "+data.get("ProductName")+" is added and ready for connection");
								else
									failLogInfo(methodName, "Unable to add new product");
							}
							else
								failLogInfo(methodName, "Unable to add Data Feed to the product");
						}
						else
							failLogInfo(methodName, "User is unable to navigate to Add Data Feed page of the product");
					}
					else
						failLogInfo(methodName, "User is unable to Edit the product");
				}
				else
					failLogInfo(methodName, "Unable to add new product");
			}
			else
				failLogInfo(methodName, "Add Trestle Feed button is disabled");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to Add/Edit Product page");
		
	}
	
	
	public void verifyNotifications(){
		String methodName="verifyNotifications";
		if(!isObjectExist("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "All pending tasks are completed and the notifications are no longer visible");
		else
			failLogInfo(methodName, "All pending tasks are still not completed");
		
	}
	
	
	private boolean isAttributePresent(WebElement element, String attribute) {
		Boolean result = false;
		String value = element.getAttribute(attribute);
		if (value != null) {
			result = true;
		}
		return result;
	}


	public Boolean check1() {
		try {
			wait(2000);
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

}
