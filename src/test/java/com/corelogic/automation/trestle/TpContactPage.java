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
public class TpContactPage extends Wraper {
	
	private static volatile TpContactPage tpcontactpage = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpContactPage setStringData(String stringData) {
		this.stringData = stringData;
		return TpContactPage.getInstance();
	}
	
	public TpContactPage setData(Map<String, String> data) {
		this.data = data;
		return TpContactPage.getInstance();
	}

	public static TpContactPage getInstance() {
		if (tpcontactpage == null) {
			synchronized (TpContactPage.class) {
				if (tpcontactpage == null) {
					tpcontactpage = new TpContactPage();
				}
			}
		}
		return tpcontactpage;
	}
	public void verifyTpContactPage(){
		String methodName="verifyTpContactPage";
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/TP/Account/EditUser']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'Primary Contact Information')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Primary Contact Information page");
			if(findElement(By.xpath("//*[@id='FirstName']")).getAttribute("value").equals(data.get("FirstName")))
				passLogInfo(methodName, "First name value is correct");
			else
				failLogInfo(methodName, "First name value is incorrect");
			if(findElement(By.xpath("//*[@id='LastName']")).getAttribute("value").equals(data.get("LastName")))
				passLogInfo(methodName, "Last Name value is correct");
			else
				failLogInfo(methodName, "Last Name value is incorrect");
			if(findElement(By.xpath("//*[@id='EmailAddress']")).getAttribute("value").equals(data.get("EmailAddress")))
				passLogInfo(methodName, "Email Address value is correct");
			else
				failLogInfo(methodName, "Email Address value is incorrect");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to Primary Contact Information page");		
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
