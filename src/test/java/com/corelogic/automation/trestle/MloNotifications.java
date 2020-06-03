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
public class MloNotifications extends Wraper {
	
	private static volatile MloNotifications mlonotifications = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloNotifications setStringData(String stringData) {
		this.stringData = stringData;
		return MloNotifications.getInstance();
	}
	
	public MloNotifications setData(Map<String, String> data) {
		this.data = data;
		return MloNotifications.getInstance();
	}

	public static MloNotifications getInstance() {
		if (mlonotifications == null) {
			synchronized (MloNotifications.class) {
				if (mlonotifications == null) {
					mlonotifications = new MloNotifications();
				}
			}
		}
		return mlonotifications;
	}
	public void verifyMloNotifications(){
		String methodName="verifyMloNotifications";
		int notificationCount=0;
		if(isObjectExist("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT)) {
			notificationCount=Integer.parseInt(findElement(By.xpath("//*[@class='notification-count-alert']")).getText());
			passLogInfo(methodName, "The notification bell icon is present displaying "+notificationCount+" pending tasks");
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/MLO/Account/EditPaymentInformation#description']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Payment Information Required notification is available");
				click("//*[@href='/MLO/Account/EditPaymentInformation#description']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@for='ChargeNameOnCard']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to Payment Information page after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable navigate to Payment Information page after clicking on the notification");	
			}
			else
				failLogInfo(methodName, "Payment Information Required notification is missing");
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/MLO/Account/DepositIdentityVerification#description']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Legal Entity Verification Required notification is available");
				click("//*[@href='/MLO/Account/DepositIdentityVerification#description']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='stripeConnectOnboarding']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to Legal Entity Verification Information page after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable navigate to Legal Entity Verification Information page after clicking on the notification");	
			}
			else
				failLogInfo(methodName, "Legal Entity Verification Required notification is missing");
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/MLO/Account/EditComp#description']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Company Description Required notification is available");
				click("//*[@href='/MLO/Account/EditComp#description']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@class='active' and @id='tabDescription']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to About Us page with Company Description tab as selected after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable navigate to About Us page with Company Description tab as selected after clicking on the notification");	
			}
			else
				failLogInfo(methodName, "Company Description Required notification is missing");
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/MLO/DataFeeds#description']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Data Feed and Contract Required notification is available");
				click("//*[@href='/MLO/DataFeeds#description']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Data Feed Manager')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to Data Feed Manager page after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable navigate to Data Feed Manager page after clicking on the notification");	
			}
			else
				failLogInfo(methodName, "Data Feed and Contract Required notification is missing");
			click("//*[@href='/MLO/Connections']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is back on Connection Manager page");
			}
			else
				assertError("Connections button is not working", true);
		}
		else
			failLogInfo(methodName, "The notification bell icon is not displayed");
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
