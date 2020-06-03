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
public class TpNotifications extends Wraper {
	
	private static volatile TpNotifications tpnotifications = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpNotifications setStringData(String stringData) {
		this.stringData = stringData;
		return TpNotifications.getInstance();
	}
	
	public TpNotifications setData(Map<String, String> data) {
		this.data = data;
		return TpNotifications.getInstance();
	}

	public static TpNotifications getInstance() {
		if (tpnotifications == null) {
			synchronized (TpNotifications.class) {
				if (tpnotifications == null) {
					tpnotifications = new TpNotifications();
				}
			}
		}
		return tpnotifications;
	}
	public void verifyTpNotifications(){
		String methodName="verifyTpNotifications";
		int notificationCount=0;
		if(isObjectExist("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT)) {
			notificationCount=Integer.parseInt(findElement(By.xpath("//*[@class='notification-count-alert']")).getText());
			passLogInfo(methodName, "The notification bell icon is present displaying "+notificationCount+" pending tasks");
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/TP/Account/EditComp#description']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Company Description Required notification is available");
				click("//*[@href='/TP/Account/EditComp#description']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@class='active' and @id='tabDescription']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to About Us page with Company Description tab as selected after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable to navigate to About Us page with Company Description tab as selected after clicking on the notification");	
			}
			else {
				failLogInfo(methodName, "Company Description Required notification is missing");
				click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			}
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/TP/Account/EditPaymentInformation#description']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Payment Information Required notification is available");
				click("//*[@href='/TP/Account/EditPaymentInformation#description']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@for='ChargeNameOnCard']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to Payment Information page after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable to navigate to Payment Information page after clicking on the notification");	
			}
			else
				failLogInfo(methodName, "Payment Information Required notification is missing");
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("(//*[@href='/TP/Products#description'])[1]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Product Information Required notification is available");
				click("(//*[@href='/TP/Products#description'])[1]", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='TPProductsList']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to Product Manager page after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable to navigate to Product Manager page after clicking on the notification");	
			}
			else
				failLogInfo(methodName, "Product Information Required notification is missing");
			
			click("//*[@href='/TP/Connections']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is back on Connection Manager page");
			}
			else
				assertError("Connections button is not working", true);
			click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
			if(isObjectExist("(//*[@href='/TP/Products#description'])[2]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Product Information Required notification is available");
				click("(//*[@href='/TP/Products#description'])[2]", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='TPProductsList']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to Product Manager page after clicking on the notification");
				}
				else
					failLogInfo(methodName, "User is unable to navigate to Product Manager page after clicking on the notification");	
			}
			else
				failLogInfo(methodName, "Product Information Required notification is missing");
			verifyAddDataFeedDisabled();
			click("//*[@href='/TP/Connections']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is back on Connection Manager page");
			}
			else
				assertError("Connections button is not working", true);
		}
		else
			failLogInfo(methodName, "The notification bell icon is not displayed");
	}
	
	
	public void verifyAddDataFeedDisabled() {
		String methodName="verifyTpNotifications";
		click("//*[@href='/TP/Products/Create']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'ADD TRESTLE FEED')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Add/Edit Product page");
			if(findElement(By.xpath("//*[contains(text(),'ADD TRESTLE FEED')]")).isEnabled())
				failLogInfo(methodName, "Add Trestle Feed button is enabled");
			else
				passLogInfo(methodName, "Add Trestle Feed button is disabled");
			if(findElement(By.xpath("//*[contains(text(),'ADD NATIVE MATRIX FEED')]")).isEnabled())
				failLogInfo(methodName, "Add Native Matrix Feed button is enabled");
			else
				passLogInfo(methodName, "Add Native Matrix Feed button is disabled");
			click("//*[@href='/TP/Products' and @role='button']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@id='TPProductsList']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is navigated back to Product Manager page");
				}
			else
				failLogInfo(methodName, "User is unable to navigate back to Product Manager page");
			}
		else
			failLogInfo(methodName, "User is unable to navigate to Add/Edit Product page");	
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
