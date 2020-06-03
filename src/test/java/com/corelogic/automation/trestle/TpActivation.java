package com.corelogic.automation.trestle;

import net.sf.saxon.functions.Parse;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.selenesedriver.SwitchToFrame;

import com.corelogic.automation.BaseLogin;
import com.corelogic.automation.Global;
import com.corelogic.automation.Wraper;
import com.corelogic.utaf.keyword.internal.selenium.GetAttribute;
import com.corelogic.utaf.keyword.internal.selenium.XPathCount;
import com.corelogic.utaf.main.internal.Base;
import com.corelogic.utaf.main.internal.Using;
import java.util.regex.*;
public class TpActivation extends Wraper {
	
	private static volatile TpActivation activation = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpActivation setStringData(String stringData) {
		this.stringData = stringData;
		return TpActivation.getInstance();
	}
	
	public TpActivation setData(Map<String, String> data) {
		this.data = data;
		return TpActivation.getInstance();
	}

	public static TpActivation getInstance() {
		if (activation == null) {
			synchronized (TpActivation.class) {
				if (activation == null) {
					activation = new TpActivation();
				}
			}
		}
		return activation;
	}
	BaseLogin baseLogin= new BaseLogin();
	public void activationTP() {
		String methodName="activationTP";
		if(isObjectExist("//*[contains(text(),'System Administration')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Successflly logged in as admin");	
			if(isObjectExist("//*[@href='/ADM/TrestleAlertLogs']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User Alert Viewer button available");
				click("//*[@href='/ADM/TrestleAlertLogs']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='tblAlertList']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User Navigated to User Alert Viewer page");
					findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("Email"));
					click("//*[contains(text(),'Details')]", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[contains(text(),'TrestleAlertLog')]", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "TP Activation alert email is opened");
						String linkText = findElement(By.xpath("//dd[3]")).getText();
						String linkText1 = StringUtils.substringBetween(linkText, "http://stage.trestle.corelogic.com", "<br");
						linkText = "http://stage.trestle.corelogic.com" + linkText1;
						baseLogin.logout();
						getDriver().navigate().to(linkText);
					}
					else	
						failLogInfo(methodName, "Unable to open TP Activation alert email");
				}
				else
					failLogInfo(methodName, "Unable to navigate to User Alert Viewer page");
			}
			else
				failLogInfo(methodName, "User Alert Viewer button not available");
				
		}
		else
			assertError("Login attempt as admin failed", true);
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
