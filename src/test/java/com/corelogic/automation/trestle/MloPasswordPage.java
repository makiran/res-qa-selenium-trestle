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
public class MloPasswordPage extends Wraper {
	
	private static volatile MloPasswordPage mlopasswordpage = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloPasswordPage setStringData(String stringData) {
		this.stringData = stringData;
		return MloPasswordPage.getInstance();
	}
	
	public MloPasswordPage setData(Map<String, String> data) {
		this.data = data;
		return MloPasswordPage.getInstance();
	}

	public static MloPasswordPage getInstance() {
		if (mlopasswordpage == null) {
			synchronized (MloPasswordPage.class) {
				if (mlopasswordpage == null) {
					mlopasswordpage = new MloPasswordPage();
				}
			}
		}
		return mlopasswordpage;
	}
	public void verifyMloPasswordPage(){
		String methodName="verifyMloPasswordPage";
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/MLO/Account/ResetPass']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'Reset password.')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is navigated to Reset Password page");
			if(findElement(By.xpath("//*[@id='Email']")).getAttribute("value").equals(data.get("EmailAddress")))
				passLogInfo(methodName, "Correct email address is displayed");
			else
				failLogInfo(methodName, "Incorrect email address is displayed");
			findElement(By.xpath("//*[@id='Password']")).sendKeys(data.get("NewPassword"));
			findElement(By.xpath("//*[@id='ConfirmPassword']")).sendKeys(data.get("ConfirmNewPassword"));
			click("//*[@id='btnResetPassword']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(text(),'Reset password confirmation.')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User Password is changed");
				click("(//*[@id='loginLink'])[1]", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='txtLoginEmail']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User redirected to Login page");
					findElement(By.xpath("//*[@id='txtLoginEmail']")).sendKeys(data.get("EmailAddress"));
					findElement(By.xpath("//*[@id='Password']")).sendKeys(data.get("NewPassword"));
					click("//*[@id='btnSignIn']", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "User is able to login with new password");
						changePasswordBack();
					}
					else
						failLogInfo(methodName, "User is unable to login with new password");
				}
				else
					failLogInfo(methodName, "User is not redirected to Login page");
			}
			else
				failLogInfo(methodName, "Unable to change user password");
		}
		else
			failLogInfo(methodName, "User is unable to navigate to Reset Password page");		
	}
	
	
	public void changePasswordBack(){
		click("//*[@id='navbar-collapse']/ul[1]/li[3]/a", Using.XPATH, TIMEOUT);
		click("//*[@href='/MLO/Account/ResetPass']", Using.XPATH, TIMEOUT);
		findElement(By.xpath("//*[@id='Password']")).sendKeys(data.get("OldPassword"));
		findElement(By.xpath("//*[@id='ConfirmPassword']")).sendKeys(data.get("OldPassword"));
		click("//*[@id='btnResetPassword']", Using.XPATH, TIMEOUT);
		click("(//*[@id='loginLink'])[1]", Using.XPATH, TIMEOUT);
		findElement(By.xpath("//*[@id='txtLoginEmail']")).sendKeys(data.get("EmailAddress"));
		findElement(By.xpath("//*[@id='Password']")).sendKeys(data.get("OldPassword"));
		click("//*[@id='btnSignIn']", Using.XPATH, TIMEOUT);
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
