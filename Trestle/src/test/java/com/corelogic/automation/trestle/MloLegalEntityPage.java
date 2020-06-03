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
public class MloLegalEntityPage extends Wraper {
	
	private static volatile MloLegalEntityPage mlolegalentitypage = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloLegalEntityPage setStringData(String stringData) {
		this.stringData = stringData;
		return MloLegalEntityPage.getInstance();
	}
	
	public MloLegalEntityPage setData(Map<String, String> data) {
		this.data = data;
		return MloLegalEntityPage.getInstance();
	}

	public static MloLegalEntityPage getInstance() {
		if (mlolegalentitypage == null) {
			synchronized (MloLegalEntityPage.class) {
				if (mlolegalentitypage == null) {
					mlolegalentitypage = new MloLegalEntityPage();
				}
			}
		}
		return mlolegalentitypage;
	}
	public void verifyMloLegalEntityPage(){
		String methodName="verifyMloLegalEntityPage";
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/MLO/Account/DepositIdentityVerification']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@id='stripeConnectOnboarding']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Legal Entity Verification Information page");
			click("//*[@id='stripeConnectOnboarding']", Using.XPATH, TIMEOUT);
			wait(5000);
			if(isObjectExist("//*[contains(text(),'Get started')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is navigated to Stripe");
				click("//*[contains(text(),'Get started')]", Using.XPATH, TIMEOUT);
				click("//*[@id='radio2']", Using.XPATH, TIMEOUT);
				click("//*[contains(text(),'Next')]", Using.XPATH, TIMEOUT);
				wait(5000);
				findElement(By.xpath("//*[@id='company[name]']")).sendKeys(data.get("MLOname"));
				findElement(By.xpath("//*[@name='address']")).sendKeys(data.get("StreetAddress"));
				findElement(By.xpath("//*[@name='locality']")).sendKeys(data.get("City"));
				select("//*[@id='subregion']", Using.XPATH, "Texas", TIMEOUT);
				findElement(By.xpath("//*//*[@name='zip']")).sendKeys(data.get("Zip"));
				findElement(By.xpath("//*[@id='company[tax_id]']")).sendKeys(data.get("EIN"));
				findElement(By.xpath("//*[@id='company[phone]']")).sendKeys(data.get("Phone"));
				findElement(By.xpath("//*[@id='business_profile[url]']")).sendKeys(data.get("Website"));
				click("//*[contains(text(),'Next')]", Using.XPATH, TIMEOUT);
				wait(3000);
				findElement(By.xpath("//*[@id='first_name']")).sendKeys(data.get("FirstName"));
				findElement(By.xpath("//*[@id='last_name']")).sendKeys(data.get("LastName"));
				findElement(By.xpath("//*[@id='email']")).sendKeys(data.get("EmailAddress"));
				findElement(By.xpath("//*[@id='relationship[title]']")).sendKeys(data.get("Title"));
				findElement(By.xpath("//*[@name='dob-month']")).sendKeys(data.get("MM"));
				findElement(By.xpath("//*[@name='dob-day']")).sendKeys(data.get("DD"));
				findElement(By.xpath("//*[@name='dob-year']")).sendKeys(data.get("YYYY"));
				findElement(By.xpath("//*[@name='address']")).sendKeys(data.get("StreetAddress"));
				findElement(By.xpath("//*[@name='locality']")).sendKeys(data.get("City"));
				select("//*[@id='subregion']", Using.XPATH, "Texas", TIMEOUT);
				findElement(By.xpath("//*//*[@name='zip']")).sendKeys(data.get("Zip"));
				findElement(By.xpath("//*[@id='phone']")).sendKeys(data.get("Phone"));
				findElement(By.xpath("//*[@id='ssn_last_4']")).sendKeys(data.get("SSN"));
				click("//*[@name='relationship.owner']", Using.XPATH, TIMEOUT);
				click("//*[contains(text(),'Next')]", Using.XPATH, TIMEOUT);
				wait(3000);
				click("(//*[contains(text(),'Done')])[2]", Using.XPATH, TIMEOUT);
				wait(3000);
				if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is back on Connection Manager page");
					verifyLegalEntityNotification();
				}
				else
					failLogInfo(methodName, "User is not redirected back to Connection Manager page");
				
			}
			else
				failLogInfo(methodName, "User is unable to navigate to Stripe");
		}
		else
			failLogInfo(methodName, "User is unable navigate to Legal Entity Verification Information page");	
		//verifyLegalEntityNotification();
	}
	
	
	public void verifyLegalEntityNotification(){
		String methodName="verifyLegalEntityNotification";
		click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
		if(!isObjectExist("//*[@href='/MLO/Account/DepositIdentityVerification#description']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Legal Entity Verification Required notification is now removed");
		else
			failLogInfo(methodName, "Legal Entity Verification Required notification is still there");
		
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
