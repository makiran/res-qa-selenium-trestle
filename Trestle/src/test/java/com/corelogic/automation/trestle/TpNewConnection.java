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
public class TpNewConnection extends Wraper {
	
	private static volatile TpNewConnection tpnewconnection = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpNewConnection setStringData(String stringData) {
		this.stringData = stringData;
		return TpNewConnection.getInstance();
	}
	
	public TpNewConnection setData(Map<String, String> data) {
		this.data = data;
		return TpNewConnection.getInstance();
	}

	public static TpNewConnection getInstance() {
		if (tpnewconnection == null) {
			synchronized (TpNewConnection.class) {
				if (tpnewconnection == null) {
					tpnewconnection = new TpNewConnection();
				}
			}
		}
		return tpnewconnection;
	}
	public void verifyTpNewConnection(){
		String methodName="verifyTpNewConnection";
		click("//*[@href='/TP/Connections/ChooseProduct']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='TPProductsList']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Step 1- Select a product page");
			click("(//*[contains(text(),'Select')])[2]", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@id='tblAvailableMLSList']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is navigated to Step 2 - Select a Multiple Listing Organization page");
				findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("MLOname"));
				click("(//*[contains(text(),'Select')])[2]", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Step 3 - Select a Data Feed')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to Step 3 - Select a Data Feed page");
					findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("FeedName"));
					click("(//*[contains(text(),'Select')])[2]", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[contains(text(),'Step 4 - Review fees & requirements')]", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "User is navigated to Step 4 - Review fees & requirements page");
						click("//*[contains(text(),'Preview Contract')]", Using.XPATH, TIMEOUT);
						wait(5000);
						getDriver().switchTo().frame(1);
						if(isObjectExist("//*[@id='pageContainer1']", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "Data Access Agreement is loaded");
							getDriver().switchTo().defaultContent();
							click("//*[@id=\"btnSendContract\"]", Using.XPATH, TIMEOUT);
							if(isObjectExist("//*[contains(text(),'Connect to a Multiple Listing Organization')]", Using.XPATH, TIMEOUT)) {
								passLogInfo(methodName, "Connection successfully created");
								click("//*[contains(text(),' OK ')]", Using.XPATH, TIMEOUT);
								if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
									passLogInfo(methodName, "User is navigated back to Connection Manager page");
								}
								else
									failLogInfo(methodName, "OK button is not working");
							}
							else
								failLogInfo(methodName, "Connection process failed");	
						}
						else	
							failLogInfo(methodName, "Unable to load Data Access Agreement");
						
					}
					else
						failLogInfo(methodName, "User is unable to select a Data Feed");
				}
				else
					failLogInfo(methodName, "User is unable to select an MLO");
			}
			else
				failLogInfo(methodName, "User is unable to select a product");
		}
		else
			failLogInfo(methodName, "Add MLO Connection button is not working");
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
