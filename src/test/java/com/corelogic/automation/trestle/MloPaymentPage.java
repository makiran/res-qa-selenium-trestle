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
public class MloPaymentPage extends Wraper {
	
	private static volatile MloPaymentPage mlopaymentpage = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloPaymentPage setStringData(String stringData) {
		this.stringData = stringData;
		return MloPaymentPage.getInstance();
	}
	
	public MloPaymentPage setData(Map<String, String> data) {
		this.data = data;
		return MloPaymentPage.getInstance();
	}

	public static MloPaymentPage getInstance() {
		if (mlopaymentpage == null) {
			synchronized (MloPaymentPage.class) {
				if (mlopaymentpage == null) {
					mlopaymentpage = new MloPaymentPage();
				}
			}
		}
		return mlopaymentpage;
	}
	public void verifyMloPaymentPage(){
		String methodName="verifyMloPaymentPage";
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/MLO/Account/EditPaymentInformation']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@for='ChargeNameOnCard']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Payment Information page");
			findElement(By.xpath("//*[@id='ChargeNameOnCard']")).sendKeys(data.get("CreditCardName"));
			click("//*[@id='cardNumber-element']", Using.XPATH, TIMEOUT);
			getDriver().switchTo().frame(1);
			wait(3000);
			findElement(By.xpath("//*[@name='cardnumber']")).sendKeys(data.get("CreditCardNumber"));
			findElement(By.xpath("//*[@name='exp-date']")).sendKeys(data.get("ExpDate"));
			findElement(By.xpath("//*[@name='cvc']")).sendKeys(data.get("CVC"));
			getDriver().switchTo().defaultContent();
			click("//*[@id='btnSave']", Using.XPATH, TIMEOUT);
			wait(5000);
		}
		else
			failLogInfo(methodName, "User is unable navigate to Payment Information page");	
		verifyPaymentnotification();
	}
	
	
	public void verifyPaymentnotification(){
		String methodName="verifyPaymentnotification";
		click("//*[@class='notification-bell-alert']", Using.XPATH, TIMEOUT);
		if(!isObjectExist("//*[@href='/MLO/Account/EditPaymentInformation#description']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Payment Information Required notification is now removed");
		else
			failLogInfo(methodName, "Payment Information Required notification is still there");
		
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
