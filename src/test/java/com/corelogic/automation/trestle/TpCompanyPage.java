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
public class TpCompanyPage extends Wraper {
	
	private static volatile TpCompanyPage tpcompanypage = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpCompanyPage setStringData(String stringData) {
		this.stringData = stringData;
		return TpCompanyPage.getInstance();
	}
	
	public TpCompanyPage setData(Map<String, String> data) {
		this.data = data;
		return TpCompanyPage.getInstance();
	}

	public static TpCompanyPage getInstance() {
		if (tpcompanypage == null) {
			synchronized (TpCompanyPage.class) {
				if (tpcompanypage == null) {
					tpcompanypage = new TpCompanyPage();
				}
			}
		}
		return tpcompanypage;
	}
	public void verifyTpCompanyPage(){
		String methodName="verifyTpCompanyPage";
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/TP/Account/EditComp']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'Company Information')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Company Information page");
			if(isObjectExist("//*[@class='active' and @id='tabCompany']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is currently on Company Info tab");
				if(findElement(By.xpath("//*[@id='BusinessName']")).getAttribute("value").equals(data.get("TPname")))
					passLogInfo(methodName, "Company name value is correct");
				else
					failLogInfo(methodName, "Company name value is incorrect");
				if(findElement(By.xpath("//*[@id='SignerFirstName']")).getAttribute("value").equals(data.get("SignFirstName")))
					passLogInfo(methodName, "Signatory First name value is correct");
				else
					failLogInfo(methodName, "Signatory First name value is incorrect");
				if(findElement(By.xpath("//*[@id='SignerLastName']")).getAttribute("value").equals(data.get("SignLastName")))
						passLogInfo(methodName, "Signatory Last Name value is correct");
				else
					failLogInfo(methodName, "Signatory Last Name value is incorrect");
				if(findElement(By.xpath("//*[@id='SignerEmail']")).getAttribute("value").equals(data.get("SignEmail")))
					passLogInfo(methodName, "Signatory Email Address value is correct");
				else
					failLogInfo(methodName, "Signatory Email Address value is incorrect");
				}
			else
				failLogInfo(methodName, "User is currently not on Company Info tab");
			}
		else
			failLogInfo(methodName, "User is unable to navigate to Company Information page");
		verifyTpCompanyDesc();
		click("//*[@href='/TP/Connections']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='tblMLSConnectionList']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is back on Connection Manager page");
		}
		else
			failLogInfo(methodName, "User is not redirected back to Connection Manager page");
		verifyCompanyDescnotification();
		verifyCompanyLogo();
		}
	
	
	public void verifyTpCompanyDesc(){
		String methodName="verifyTpCompanyDesc";
		click("//*[@id='tabDescription']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@class='active' and @id='tabDescription']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is currently on Company Description tab");
			findElement(By.xpath("//*[@id='Description']")).sendKeys(data.get("Description"));
			click("//*[@id='btnDescriptionSave']", Using.XPATH, TIMEOUT);
			wait(2000);
			}
		else
			failLogInfo(methodName, "User is unable to go on Company Description tab");	
		}
	
	
	public void verifyCompanyDescnotification(){
		String methodName="verifyCompanyDescnotification";
		click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
		if(!isObjectExist("//*[@href='/TP/Account/EditComp#description']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Company Description Required notification is now removed");
		else
			failLogInfo(methodName, "Company Description Required notification is still there");
		
	}
	
	
	public void verifyCompanyLogo(){
		String methodName="verifyCompanyLogo";
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/TP/Account/EditComp']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'Company Information')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Company Information page");
			click("//*[@id='tabCompanyLogo']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@class='active' and @id='tabCompanyLogo']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is currently on Company Logo tab");
				if(isObjectExist("//*[@id='divNewLogo']/div[1]/div/button", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Upload Logo button is available");
				else
					failLogInfo(methodName, "Upload Logo button is not available");
				if(isObjectExist("//*[@id='btnLogoDelete']", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Clear Logo button is available");
				else
					failLogInfo(methodName, "Clear Logo button is available");
			}
			else
				failLogInfo(methodName, "User is currently not on Company Logo tab");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to Company Information page");
		
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
