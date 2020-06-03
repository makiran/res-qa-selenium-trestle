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
public class MloSubscription extends Wraper {
	
	private static volatile MloSubscription subscriptions = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloSubscription setStringData(String stringData) {
		this.stringData = stringData;
		return MloSubscription.getInstance();
	}
	
	public MloSubscription setData(Map<String, String> data) {
		this.data = data;
		return MloSubscription.getInstance();
	}

	public static MloSubscription getInstance() {
		if (subscriptions == null) {
			synchronized (MloSubscription.class) {
				if (subscriptions == null) {
					subscriptions = new MloSubscription();
				}
			}
		}
		return subscriptions;
	}
	
	
	public void subscriptionOption() {
		String methodName="subscriptionOption";
		if(isObjectExist("//*[@id='loginLink']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Login button is available");
			click("//*[@id='loginLink']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/SubscriptionWizard']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Sign Up option is available");
				click("//*[@href='/SubscriptionWizard']", Using.XPATH, TIMEOUT);
			}
			else
				assertError("Sign Up option is not available", true);
		}
		else
			assertError("Login button is not available", true);
	}
	
	
	public void subscriptionMLO() {
		String methodName="subscriptionMLO";
		subscriptionOption();
		if(isObjectExist("//*[@id='btnMloSelect']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "MLS subscription option is available");	
			click("//*[@id='btnMloSelect']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(text(),'Primary Contact Information')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Primary Contact Information form is displayed");
				findElement(By.xpath("//*[@id='UserFirstName']")).sendKeys(data.get("FirstName"));
				findElement(By.xpath("//*[@id='UserLastName']")).sendKeys(data.get("LastName"));
				findElement(By.xpath("//*[@id='UserPrimaryPhone']")).sendKeys(data.get("PrimaryPhone"));
				findElement(By.xpath("//*[@id='UserEmailAddress']")).sendKeys(data.get("EmailAddress"));
				findElement(By.xpath("//*[@id='Password']")).sendKeys(data.get("Password"));
				findElement(By.xpath("//*[@id='ConfirmPassword']")).sendKeys(data.get("ConfirmPassword"));	
				click("//*[@id='btnContactNext']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Company Information')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Company Information form is displayed");
					findElement(By.xpath("//*[@id='BusinessEntityName']")).sendKeys(data.get("MLOname"));
					findElement(By.xpath("//*//*[@id='StreetAddress1']")).sendKeys(data.get("StreetAddress"));
					findElement(By.xpath("//*[@id='City']")).sendKeys(data.get("City"));
					click("//*[@data-id='ddlCompanyStateUS']", Using.XPATH, TIMEOUT);
					findElement(By.xpath("//*[@data-id='ddlCompanyStateUS']/parent::*//input")).sendKeys(data.get("State"));
					click("//*[@data-id='ddlCompanyStateUS']/parent::*//li[contains(@class,'active')]/a[1]/span[@class='text']/parent::*", Using.XPATH, TIMEOUT);
					findElement(By.xpath("//*[@id='ZipPostalCode']")).sendKeys(data.get("ZIP"));
					findElement(By.xpath("//*[@id='SignerFirstName']")).sendKeys(data.get("SignFirstName"));
					findElement(By.xpath("//*[@id='SignerLastName']")).sendKeys(data.get("SignLastName"));
					findElement(By.xpath("//*[@id='SignerTitle']")).sendKeys(data.get("SignTitle"));
					findElement(By.xpath("//*[@id='SignerEmail']")).sendKeys(data.get("SignEmail"));
					click("//*[@data-id='ddlIncorporationState']", Using.XPATH, TIMEOUT);
					findElement(By.xpath("//*[@data-id='ddlIncorporationState']/parent::*//input")).sendKeys(data.get("State"));
					click("//*[@data-id='ddlIncorporationState']/parent::*//li[contains(@class,'active')]/a[1]/span[@class='text']/parent::*", Using.XPATH, TIMEOUT);
					click("//*[@id='btnCompanyNext']", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[contains(text(),'Thank you for initiating the Trestle subscription process! We will send you an email soon notifying you that your Trestle account has been enabled.')]", Using.XPATH, TIMEOUT))
						passLogInfo(methodName, "Subscription successful");
					else
						assertError("Subscription unsuccessful", true);
				}
				else
					assertError("Company Information form is not displayed", true);
			}
			else
				assertError("Primary Contact Information form is not displayed", true);
		}
		else
			assertError("MLS subscription option is not available", true);
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
