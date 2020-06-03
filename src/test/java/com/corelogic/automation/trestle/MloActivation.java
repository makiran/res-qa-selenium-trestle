package com.corelogic.automation.trestle;

import net.sf.saxon.functions.Parse;

import org.openqa.selenium.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.selenesedriver.SwitchToFrame;
import com.corelogic.automation.Global;
import com.corelogic.automation.Wraper;
import com.corelogic.utaf.keyword.internal.selenium.GetAttribute;
import com.corelogic.utaf.keyword.internal.selenium.XPathCount;
import com.corelogic.utaf.main.internal.Using;
import java.util.regex.*;
public class MloActivation extends Wraper {
	
	private static volatile MloActivation activation = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloActivation setStringData(String stringData) {
		this.stringData = stringData;
		return MloActivation.getInstance();
	}
	
	public MloActivation setData(Map<String, String> data) {
		this.data = data;
		return MloActivation.getInstance();
	}

	public static MloActivation getInstance() {
		if (activation == null) {
			synchronized (MloActivation.class) {
				if (activation == null) {
					activation = new MloActivation();
				}
			}
		}
		return activation;
	}
	
	public void activationMLO() {
		String methodName="activationnMLO";
		if(isObjectExist("//*[contains(text(),'System Administration')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Successflly logged in as admin");	
			if(isObjectExist("//*[@href='/ADM/UserVetting']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User Verification button available");
				click("//*[@href='/ADM/UserVetting']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'User Verification')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User Navigated to User Verification page");
					findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("MLOname"));
					click("(//*[@class='btn-link btn-info'])[1]", Using.XPATH, TIMEOUT);
					if(isObjectExist("//span[contains(@class, 'filter-option pull-left') and text() = 'Pending']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "MLO user is currently set to Pending Status");
						click("//*[@data-id='flag']", Using.XPATH, TIMEOUT);
						click("//span[contains(@class, 'text') and text() = 'Approved']", Using.XPATH, TIMEOUT);
						if(isObjectExist("//span[contains(@class, 'filter-option pull-left') and text() = 'Approved']", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "MLO verification status changed to Approved");
							click("//*[@id='btnUpdateStatus']", Using.XPATH, TIMEOUT);
							if(isObjectExist("//*[contains(text(),'User Verification')]", Using.XPATH, TIMEOUT)) {
								passLogInfo(methodName, "User Navigated back to User Verification page");
								click("(//*[@class='btn-link btn-info'])[1]", Using.XPATH, TIMEOUT);
								if(isObjectExist("//span[contains(@class, 'filter-option pull-left') and text() = 'Approved']", Using.XPATH, TIMEOUT)) {
									passLogInfo(methodName, "MLO is now successfully activated");
									serverConfigMLO();
								}
								else
									failLogInfo(methodName, "Unable to activate MLO");
							}
							else
								failLogInfo(methodName, "User unable to navigate back to User Verification page");
						}
						else
							failLogInfo(methodName, "Unable to change MLO verification status to Approved");
					}
					else
						failLogInfo(methodName, "MLO user is currently not set to Pending Status");
				}
				else
					failLogInfo(methodName, "Unable to navigate to User Verification page");
			}
			else
				failLogInfo(methodName, "User Verification button not available");
				
		}
		else
			assertError("Login attempt as admin failed", true);
	}
	
	
	public void serverConfigMLO() {
		String methodName="serverConfigMLO";
		click("(//*[@href='/ADM'])[1]", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'System Administration')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is successfully redirected back to Admin Dashboard");	
			if(isObjectExist("//*[@href='/ADM/MlsServerAssignment']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "MLO Server Manager button is available");	
				click("//*[@href='/ADM/MlsServerAssignment']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'ML Organization Server Manager')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is navigated to MLO Server Manager page");
					findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("MLOname"));
					click("//*[@class='btn-link btn-info']", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[contains(text(),' CoreMLS Stage')]", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "User is navigated to Select Server for MLS page");
						click("(//*[@class='btn-link btn-info'])[1]", Using.XPATH, TIMEOUT);
						/*for(int i=1;i<9;i++) {
							wait(2000);
							findElement(By.xpath("(//*[@class='form-control'])["+i+"]")).sendKeys(data.get("OSN"));
						}*/
						findElement(By.xpath("(//*[@class='form-control'])[1]")).sendKeys(data.get("OSN"));
						click("//*[@type='submit']", Using.XPATH, TIMEOUT);
						click("//*[@class='dropdown-toggle user-info-fullname']", Using.XPATH, TIMEOUT);
						click("//*[contains(text(),'Sign Out')]", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@href='/Login']", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "user is logged out and redirected to landing page");
						}
						else
							assertError("User is unable to logout", true);
					}
					else
						failLogInfo(methodName, "User is unable to naviagte to Select Server for MLS page");
				}
				else
					failLogInfo(methodName, "User is unable to navigate to MLO Server Manager page");
			}
			else
				failLogInfo(methodName, "User Verification button not available");
		}
		else
			assertError("MLO Server Manager button is not available", true);
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
