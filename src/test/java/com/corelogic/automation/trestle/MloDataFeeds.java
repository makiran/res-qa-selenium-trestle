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
public class MloDataFeeds extends Wraper {
	
	private static volatile MloDataFeeds mlodatafeeds = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloDataFeeds setStringData(String stringData) {
		this.stringData = stringData;
		return MloDataFeeds.getInstance();
	}
	
	public MloDataFeeds setData(Map<String, String> data) {
		this.data = data;
		return MloDataFeeds.getInstance();
	}

	public static MloDataFeeds getInstance() {
		if (mlodatafeeds == null) {
			synchronized (MloDataFeeds.class) {
				if (mlodatafeeds == null) {
					mlodatafeeds = new MloDataFeeds();
				}
			}
		}
		return mlodatafeeds;
	}
	public void verifyMloDataFeed(){
		String methodName="verifyMloDataFeed";
		click("//*[@href='/MLO/DataFeeds']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='tblDataFeeds']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Data Feed Manager page");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to Data Feed Manager page");
		findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("DataFeedName"));
		click("//*[@id='tblDataFeeds']/tbody/tr[1]/td[6]/a", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='tabsDataFeeds']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Data Feed Configuration page");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to Data Feed Configuration page");
		click("//*[@id='IsActiveYes']", Using.XPATH, TIMEOUT);
		click("//*[@id='btnSaveOptions']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@class='row text-center']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Data Feed Enabled");
		else
			failLogInfo(methodName, "Save button not working");
		click("//*[@id='tabfeedFees']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='tblTPPricingPlans']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "User is navigated to Fees configuration page");
		else
			failLogInfo(methodName, "User is unable to navigate to Fees configuration page");
		findElement(By.xpath("//*[@id='MLSFees_0__LiquidatedDamages']")).clear();
		findElement(By.xpath("//*[@id='MLSFees_0__LiquidatedDamages']")).sendKeys(data.get("Liquidated Damages"));
		findElement(By.xpath("//*[@id='MLSFees_0__SetupFee']")).clear();
		findElement(By.xpath("//*[@id='MLSFees_0__SetupFee']")).sendKeys(data.get("Setup Fee"));
		click("//*[@id='btnTPAddPlanRow']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='tbodyTPPricingPlans']/tr[2]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "New Pricing Plan row added");
		else
			failLogInfo(methodName, "Unable to add New Pricing Plan row");
		click("//*[@id='tbodyTPPricingPlans']/tr[2]/td[2]/i", Using.XPATH, TIMEOUT);
		wait(3000);
		getDriver().switchTo().activeElement();
		click("//*[contains(text(),'Delete this plan')]", Using.XPATH, TIMEOUT);
		wait(3000);
		getDriver().switchTo().defaultContent();
		if(!isObjectExist("//*[@id='tbodyTPPricingPlans']/tr[2]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Deleted New Pricing Plan row");
		else
			failLogInfo(methodName, "Unable to delete New Pricing Plan row");
		findElement(By.xpath("(//*[@id='tbodyTPPricingPlans']/tr/td[5]/child::*)[1]")).clear();
		findElement(By.xpath("(//*[@id='tbodyTPPricingPlans']/tr/td[5]/child::*)[1]")).sendKeys(data.get("TpPlanPrice"));
		click("//*[@id='btnSaveOptions']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@class='row text-center']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Pricing Plan saved");
		else
			failLogInfo(methodName, "Save button not working");
		click("//*[@id='tabfeedContracts']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='DataContractsList']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "User is navigated to Contacts configuration page");
		else
			failLogInfo(methodName, "User is unable to navigate to Contracts configuration page");
		click("(//*[@id='DataFeedContract'])[1]", Using.XPATH, TIMEOUT);
		click("//*[@type='submit']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@class='row text-center']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Contract is selected");
		else
			failLogInfo(methodName, "Save button not working");
		click("//*[@id='tabfeedCustomize']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='tblAvailableResources']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "User is navigated to Customize Data configuration page");
		else
			failLogInfo(methodName, "User is unable to navigate to Customize Data configuration page");
		
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
