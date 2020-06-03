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
import com.corelogic.utaf.main.internal.Using;
import com.corelogic.utaf.utils.XMLUtil;

import java.util.regex.*;
public class MloUserManager extends Wraper {
	
	private static volatile MloUserManager mlousermanager = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloUserManager setStringData(String stringData) {
		this.stringData = stringData;
		return MloUserManager.getInstance();
	}
	
	public MloUserManager setData(Map<String, String> data) {
		this.data = data;
		return MloUserManager.getInstance();
	}

	public static MloUserManager getInstance() {
		if (mlousermanager == null) {
			synchronized (MloUserManager.class) {
				if (mlousermanager == null) {
					mlousermanager = new MloUserManager();
				}
			}
		}
		return mlousermanager;
	}
	
	public void verifyMloUserManager() throws Exception {
		createNewAdminUser();
		wait(2000);
		activateNewAdminUser();
		
	}
	Properties userStageTP = XMLUtil.loadUserConfig("StageTp");
	Properties userStageAdmin = XMLUtil.loadUserConfig("StageAdmin");
	BaseLogin baseLogin= new BaseLogin();
	
	public void createNewAdminUser() {
		String methodName="createNewAdminUser";
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/MLO/Account/UserManagerList']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@href='/MLO/Account/UserManagerAdd']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to User Manager page");
			click("//*[@href='/MLO/Account/UserManagerAdd']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(text(),'Add/Edit User Information')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is navigated to Add/Edit User Information page");
				findElement(By.xpath("//*[@id='FirstName']")).sendKeys(data.get("FirstName"));
				findElement(By.xpath("//*[@id='LastName']")).sendKeys(data.get("LastName"));
				findElement(By.xpath("//*[@id='Email']")).sendKeys(data.get("Email"));
				click("//*[@value='Save']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@class='fa fa-check-circle']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "A new Admin User is successfully added");
				}
				else
					failLogInfo(methodName, "Unable to add a new Admin User");
			}
			else
				failLogInfo(methodName, "User is unable to navigate to Add/Edit User Information page");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to User Manager page");
	}
	
	
	public void activateNewAdminUser() {
		String methodName="activateNewAdminUser";
		baseLogin.logout();
		try {
			baseLogin.login(userStageAdmin.getProperty("userID").trim(), userStageAdmin.getProperty("password").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isObjectExist("//*[contains(text(),'System Administration')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Successflly logged in as admin");
			if(isObjectExist("//*[@href='/ADM/TrestleAlertLogs']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User Alert Viewer button available");
				wait(5000);
				click("//*[@href='/ADM/TrestleAlertLogs']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='tblAlertList']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User Navigated to User Alert Viewer page");
					findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("Email"));
					click("//*[contains(text(),'Details')]", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[contains(text(),'TrestleAlertLog')]", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "New MLO Admin user activation alert email is opened");
						String linkText = findElement(By.xpath("//dd[3]")).getText();
						String linkText1 = StringUtils.substringBetween(linkText, "http://stage.trestle.corelogic.com", "\"");
						linkText = "http://stage.trestle.corelogic.com" + linkText1;
						baseLogin.logout();
						getDriver().navigate().to(linkText);
						wait(3000);
						if(isObjectExist("//*[contains(text(),'Getting Started')]", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "User is navigated to New MLO Admin's Password creation page");
							findElement(By.xpath("//*[@id='Password']")).sendKeys(data.get("Password"));
							findElement(By.xpath("//*[@id='ConfirmPassword']")).sendKeys(data.get("ConfirmPassword"));
							click("//*[@type='submit']", Using.XPATH, TIMEOUT);
							wait(3000);
							if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
								passLogInfo(methodName, "New MLO Admin user is successfully logged in");
							}
							else
								failLogInfo(methodName, "New MLO Admin user is unable to save password");
						}
						else
							failLogInfo(methodName, "User is unable to navigate to New MLO Admin's Password creation page");	
					}
					else	
						failLogInfo(methodName, "Unable to open New MLO Admin user Activation alert email");
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

	public void verifyNewAdminUser() throws Exception {
	}
	
	
	
	public void changeNewAdminToOwner() throws Exception {
		String methodName="changeNewAdminToOwner";
		baseLogin.logout();
		baseLogin.login(userStageTP.getProperty("userID").trim(), userStageTP.getProperty("password").trim());
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/MLO/Account/UserManagerList']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@href='/MLO/Account/UserManagerAdd']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to User Manager page");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to User Manager page");
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
