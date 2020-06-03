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
import org.pdfbox.util.operator.pagedrawer.MoveTo;

import com.corelogic.automation.Global;
import com.corelogic.automation.Wraper;
import com.corelogic.automation.common.Common;
import com.corelogic.utaf.keyword.internal.selenium.GetAttribute;
import com.corelogic.utaf.keyword.internal.selenium.XPathCount;
import com.corelogic.utaf.main.internal.Using;
import java.util.regex.*;
import org.openqa.selenium.interactions.Actions;
public class LandingPage extends Wraper {
	
	private static volatile LandingPage landingpages = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public LandingPage setStringData(String stringData) {
		this.stringData = stringData;
		return LandingPage.getInstance();
	}
	
	public LandingPage setData(Map<String, String> data) {
		this.data = data;
		return LandingPage.getInstance();
	}

	public static LandingPage getInstance() {
		if (landingpages == null) {
			synchronized (LandingPage.class) {
				if (landingpages == null) {
					landingpages = new LandingPage();
				}
			}
		}
		return landingpages;
	}
	
	public void VerifyLandingPage(){
		//String methodName="VerifyLandingPage";
		MloLandingPage();
		TpLandingPage();
		BrokerLandingPage();
		VerifyHelp();
		VerifyForgotPasswordPage();
	}
	
	
	public void MloLandingPage() {
		String methodName="MloLandingPage";
		if(isObjectExist("//*[@href='/' and @class='active']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "MLO landing page is displayed by default");
			if(isObjectExist("//*[@class='header-dp']", Using.XPATH, TIMEOUT)) 
				passLogInfo(methodName, "Suit touching lock image is displayed on landing page");
			else
				failLogInfo(methodName, "Suit touching lock image is not displayed on landing page");
			if(isObjectExist("//*[contains(text(),'Discover Trestle')]", Using.XPATH, TIMEOUT)) 	
				passLogInfo(methodName, "\"Discover Trestle\" section is present on landing page");
			else
				failLogInfo(methodName, "\"Discover Trestle\" section is not present on landing page");
			if(isObjectExist("//*[contains(text(),'Trestle for Multiple Listing Organization')]", Using.XPATH, TIMEOUT)) 
				passLogInfo(methodName, "\"Trestle for Multiple Listing Organization\" section is present on landing page");
			else
				failLogInfo(methodName, "Trestle for Multiple Listing Organization section is not present on landing page");
			if(isObjectExist("//*[contains(text(),'What do you get with Trestle?')]", Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "\"What do you get with Trestle?\" section is present on landing page");
			else
				failLogInfo(methodName, "\"What do you get with Trestle?\" section is not present on landing page");
			/*if(isObjectExist("//*[@href='/Content/Trestle - Quick Start Guide - Release 1.1.pdf']", Using.XPATH, TIMEOUT)) 
				passLogInfo(methodName, "Download pdf link is available on landing page");
			else
				failLogInfo(methodName, "Download pdf link is not available on landing page");*/
			//VerifyMloVideos();
			if(isObjectExist("(//*[@id='signupSubmitButton'])[2]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Get Started footer button is present on landing page");
				click("(//*[@id='signupSubmitButton'])[2]", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='businessTypeMetatable']", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "User is navigated to \"The Subscribe to Trestle > Business Type page\"");
				else
					failLogInfo(methodName, "User is not navigated to \\\"The Subscribe to Trestle > Business Type page\\\"");
				click("//*[@id='btnCancel']", Using.XPATH, TIMEOUT);
				click("//*[@id='btnCancelYes']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@href='/' and @class='active']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is returned to MLO landing page");
			}
			else
				failLogInfo(methodName, "User is not returned to MLO landing page");	
			}
			else
				failLogInfo(methodName, "Get Started footer button is not present on landing page");
		}
		else
			failLogInfo(methodName, "MLO landing page is not displayed by default");
	}
	
	
	public void TpLandingPage() {
		String methodName="TpLandingPage";
		
		if(isObjectExist("//*[@href='/Home/Providers']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Technology Providers landing page tab is available");
			click("//*[@href='/Home/Providers']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/Home/Providers' and @class='active']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is navigated to Technology Providers landing page");
				if(isObjectExist("//*[@class='header-tech']", Using.XPATH, TIMEOUT)) 
					passLogInfo(methodName, "Over-the-shoulder whiteboard image is displayed on landing page");
				else
					failLogInfo(methodName, "Over-the-shoulder whiteboard image is not displayed on landing page");
				if(isObjectExist("//*[contains(text(),'Discover Trestle')]", Using.XPATH, TIMEOUT)) 
					passLogInfo(methodName, "\"Discover Trestle\" section is present on landing page");
				else
					failLogInfo(methodName, "\"Discover Trestle\" section is not present on landing page");
				if(isObjectExist("//*[contains(text(),'Trestle for Technology Providers')] ", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "\"Trestle for Technology Providers\" section is present on landing page");
				else
					failLogInfo(methodName, "Trestle for Technology Providers section is not present on landing page");
				if(isObjectExist("//*[contains(text(),'What do you get with Trestle?')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "\"What do you get with Trestle?\" section is present on landing page");
				}
				else
					failLogInfo(methodName, "\"What do you get with Trestle?\" section is not present on landing page");
				//VerifyTpVideos();
				if(isObjectExist("(//*[@id='signupSubmitButton'])[2]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Get Started footer button is present on landing page");
					click("(//*[@id='signupSubmitButton'])[2]", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[@id='businessTypeMetatable']", Using.XPATH, TIMEOUT))
						passLogInfo(methodName, "User is navigated to \"The Subscribe to Trestle > Business Type page\"");
					else
						failLogInfo(methodName, "User is not navigated to \\\"The Subscribe to Trestle > Business Type page\\\"");
					click("//*[@id='btnCancel']", Using.XPATH, TIMEOUT);
					click("//*[@id='btnCancelYes']", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[@href='/' and @class='active']", Using.XPATH, TIMEOUT))
						passLogInfo(methodName, "User is returned to MLO landing page");
					else
						failLogInfo(methodName, "User is not returned to MLO landing page");	
				}
				else
					failLogInfo(methodName, "Get Started footer button is not present on landing page");
			}
		}
		else
			failLogInfo(methodName, "Technology Providers landing page tab is not available");
	}
	
	
	public void BrokerLandingPage() {
		String methodName="BrokerLandingPage";
		
		if(isObjectExist("//*[@href='/Home/Brokers']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Brokers landing page tab is available");
			click("//*[@href='/Home/Brokers']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/Home/Brokers' and @class='active']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is navigated to Brokers landing page");
				if(isObjectExist("//*[@class='header-brokers']", Using.XPATH, TIMEOUT)) 
					passLogInfo(methodName, "Birds-eye view Roundabout image is displayed on landing page");
				else
					failLogInfo(methodName, "Birds-eye view Roundabout image is not displayed on landing page");
				if(isObjectExist("//*[contains(text(),'Discover Trestle')]", Using.XPATH, TIMEOUT)) 
					passLogInfo(methodName, "\"Discover Trestle\" section is present on landing page");
				else
					failLogInfo(methodName, "\"Discover Trestle\" section is not present on landing page");
				if(isObjectExist("//*[contains(text(),'Trestle for Brokers')]", Using.XPATH, TIMEOUT)) 
					passLogInfo(methodName, "\"Trestle for Brokers\" section is present on landing page");
				else
					failLogInfo(methodName, "Trestle for Brokers section is not present on landing page");
				if(isObjectExist("//*[contains(text(),'What do you get with Trestle?')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "\"What do you get with Trestle?\" section is present on landing page");
				else
					failLogInfo(methodName, "\"What do you get with Trestle?\" section is not present on landing page");
				//VerifyBrokerVideos();
				if(isObjectExist("(//*[@id='signupSubmitButton'])[2]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Get Started footer button is present on landing page");
					click("(//*[@id='signupSubmitButton'])[2]", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[@id='businessTypeMetatable']", Using.XPATH, TIMEOUT))
						passLogInfo(methodName, "User is navigated to \"The Subscribe to Trestle > Business Type page\"");
					else
						failLogInfo(methodName, "User is not navigated to \\\"The Subscribe to Trestle > Business Type page\\\"");
					click("//*[@id='btnCancel']", Using.XPATH, TIMEOUT);
					click("//*[@id='btnCancelYes']", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[@href='/' and @class='active']", Using.XPATH, TIMEOUT))
						passLogInfo(methodName, "User is returned to MLO landing page");
					else
						failLogInfo(methodName, "User is not returned to MLO landing page");	
				}
				else
					failLogInfo(methodName, "Get Started footer button is not present on landing page");
			}
		}
		else
			failLogInfo(methodName, "Technology Providers landing page tab is not available");
	}
	
	
	public void VerifyMloVideos(){
		String methodName="VerifyMloVideos";
		getDriver().switchTo().frame(findElement(By.xpath("//iframe[contains(@src='https://players.brightcove.net/75895570001/EJUJuZYOl_default/index.html?videoId=5278807116001')]")));
		if(isObjectExist("//*[@class='vjs-poster' and contains(@style,'5278807116001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Trestle Overview video is available on MLO's Landing Page");
			else
			failLogInfo(methodName, "Trestle Overview video is not available on MLO's Landing Page");
		getDriver().switchTo().defaultContent();
		/*getDriver().switchTo().frame(1);
		if(isObjectExist("//*[contains(@style,'5278841375001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "The Benefits of Trestle video is available on MLO's Landing Page");
		else
			failLogInfo(methodName, "The Benefits of Trestle video is not available on MLO's Landing Page");
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame(1);
		if(isObjectExist("//*[contains(@style,'5278847425001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "The Deep Dive video is available on MLO's Landing Page");
		else
			failLogInfo(methodName, "The Deep Dive video is not available on MLO's Landing Page");
		getDriver().switchTo().defaultContent();*/
		//click("(//*[@class='vjs-big-play-button'])[1]", Using.XPATH, TIMEOUT);
		//wait(1000);
		
		}
	
	
	public void VerifyTpVideos(){
		String methodName="VerifyTpVideos";
		getDriver().switchTo().frame(0);
		if(isObjectExist("//*[@class='vjs-poster' and contains(@style,'5278807116001')]", Using.XPATH, TIMEOUT)) 
			passLogInfo(methodName, "Trestle Overview video is available on Technology Provider's Landing Page");
			else
			failLogInfo(methodName, "Trestle Overview video is not available on Technology Provider's Landing Page");
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame(1);
		if(isObjectExist("//*[contains(@style,'5278866942001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "The Benefits of Trestle video is available on Technology Provider's Landing Page");
		else
			failLogInfo(methodName, "The Benefits of Trestle video is not available on Technology Provider's Landing Page");
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame(2);
		if(isObjectExist("//*[contains(@style,'5278864203001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "The Deep Dive video is available on Technology Provider's Landing Page");
		else
			failLogInfo(methodName, "The Deep Dive video is not available on Technology Provider's Landing Page");
		getDriver().switchTo().defaultContent();
		//click("(//*[@class='vjs-big-play-button'])[1]", Using.XPATH, TIMEOUT);
		//wait(1000);
		
		}
	
	
	public void VerifyBrokerVideos(){
		String methodName="VerifyBrokerVideos";
		getDriver().switchTo().frame(0);
		if(isObjectExist("//*[@class='vjs-poster' and contains(@style,'5278807116001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Trestle Overview video is available on Broker's Landing Page");
			else
			failLogInfo(methodName, "Trestle Overview video is not available on Broker's Landing Page");
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame(1);
		if(isObjectExist("//*[contains(@style,'5278847471001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "The Benefits of Trestle video is available on Broker's Landing Page");
		else
			failLogInfo(methodName, "The Benefits of Trestle video is not available on Broker's Landing Page");
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame(2);
		if(isObjectExist("//*[contains(@style,'5278858785001')]", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "The Deep Dive video is available on Broker's Landing Page");
		else
			failLogInfo(methodName, "The Deep Dive video is not available on Broker's Landing Page");
		getDriver().switchTo().defaultContent();
		//click("(//*[@class='vjs-big-play-button'])[1]", Using.XPATH, TIMEOUT);
		//wait(1000);
		
		}
	
	
	public void VerifyHelp(){
		String methodName="VerifyHelp";
		if(isObjectExist("//*[@class='fa fa-angle-down']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Help dropdown is available on Landing Page");
			click("//*[@class='fa fa-angle-down']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@href='/Content/Trestle for Multiple Listing Managers Release 1.1.pdf']", Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "Multiple Listing Organizations option is available under Help dropdown");
			else
				failLogInfo(methodName, "Multiple Listing Organizations option is not available under Help dropdown");	
			if(isObjectExist("//*[@href='/Content/Trestle for Technology Providers.pdf']", Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "Technology Providers option is available under Help dropdown");
			else
				failLogInfo(methodName, "Technology Providers option is not available under Help dropdown");	
			if(isObjectExist("//*[@href='https://www.youtube.com/channel/UCFm3atuRrN3H7s9kj1ghx5g/videos']", Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "Help Videos option is available under Help dropdown");
			else
				failLogInfo(methodName, "Help Videos option is not available under Help dropdown");
			if(isObjectExist("//*[@class='trestle-support-email']", Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "Email Address support detail is available under Help dropdown");
			else
				failLogInfo(methodName, "Support Email Address details is not available under Help dropdown");
			if(isObjectExist("//*[@class='trestle-support-phone']", Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "Phone Support detail is available under Help dropdown");
			else
				failLogInfo(methodName, "Phone Support detail is not available under Help dropdown");
		}
		else
			failLogInfo(methodName, "Help dropdown is not available on Landing Page");	
	}
	
	
	public void VerifyForgotPasswordPage(){
		String methodName="VerifyForgotPasswordPage";
		click("//*[@href='/Login']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[@href='/Account/ForgotPassword']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is redirected to login page and forgot password link is available");
			click("//*[@href='/Account/ForgotPassword']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@id='txtResetEmail']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "User is redirected to forgot password page");
				click("//*[@id='btnForgotPasswordCancel']", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@href='/Account/ForgotPassword']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is redirected back to login page");
				}
				else
					failLogInfo(methodName, "Cancel button is not working");
			}
			else
				failLogInfo(methodName, "Forgot password link is not working");
		}
		else
			failLogInfo(methodName, "Forgot password link is not available");
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
