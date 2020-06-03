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
public class MloSubscriptionAgreement extends Wraper {
	
	private static volatile MloSubscriptionAgreement mlosubscriptionagreement = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloSubscriptionAgreement setStringData(String stringData) {
		this.stringData = stringData;
		return MloSubscriptionAgreement.getInstance();
	}
	
	public MloSubscriptionAgreement setData(Map<String, String> data) {
		this.data = data;
		return MloSubscriptionAgreement.getInstance();
	}

	public static MloSubscriptionAgreement getInstance() {
		if (mlosubscriptionagreement == null) {
			synchronized (MloSubscriptionAgreement.class) {
				if (mlosubscriptionagreement == null) {
					mlosubscriptionagreement = new MloSubscriptionAgreement();
				}
			}
		}
		return mlosubscriptionagreement;
	}
	
	
	public void acceptAgreement() {
		String methodName="acceptAgreement";
		if(isObjectExist("//*[contains(text(),'Trestle Subscriber Agreement')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Trestle Subscriber Agreement page");
		}
		else
			assertError("User is unable to navigate to Trestle Subscriber Agreement page", true);
		wait(5000);
		getDriver().switchTo().frame(1);
		if(isObjectExist("//*[@id='pageContainer1']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "MLO Subscription Agreement is loaded");
		}
		else
			assertError("Unable to load MLO Subscription Agreement", true);
		getDriver().switchTo().defaultContent();
		click("//*[@id='btnEULASave']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "MLO user is successfully logged in");
		}
		else
			assertError("MLO user is unable to login", true);
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
