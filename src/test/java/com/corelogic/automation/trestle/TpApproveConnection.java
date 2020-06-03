package com.corelogic.automation.trestle;

import net.sf.saxon.functions.Parse;

import org.openqa.selenium.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.selenesedriver.SwitchToFrame;
import com.corelogic.automation.Global;
import com.corelogic.automation.Wraper;
import com.corelogic.utaf.keyword.internal.selenium.GetAttribute;
import com.corelogic.utaf.keyword.internal.selenium.XPathCount;
import com.corelogic.utaf.main.internal.Base;
import com.corelogic.utaf.main.internal.Using;
import java.util.regex.*;
public class TpApproveConnection extends Wraper {
	
	private static volatile TpApproveConnection approveconnection = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpApproveConnection setStringData(String stringData) {
		this.stringData = stringData;
		return TpApproveConnection.getInstance();
	}
	
	public TpApproveConnection setData(Map<String, String> data) {
		this.data = data;
		return TpApproveConnection.getInstance();
	}

	public static TpApproveConnection getInstance() {
		if (approveconnection == null) {
			synchronized (TpApproveConnection.class) {
				if (approveconnection == null) {
					approveconnection = new TpApproveConnection();
				}
			}
		}
		return approveconnection;
	}
	
	public void verifyapproveConnectionTP() {
		String methodName="verifyapproveConnectionTP";
		getDriver().navigate().to("https://www.mailinator.com/");
		wait(3000);
		ArrayList<String> newTab = new ArrayList<String> (getDriver().getWindowHandles());
		getDriver().switchTo().window(newTab.get(1));
		if(isObjectExist("//*[@id='addOverlay']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Navigated to Mailinator website");
		}
		else
			failLogInfo(methodName, "Unable to navigate to Mailinator website");
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
