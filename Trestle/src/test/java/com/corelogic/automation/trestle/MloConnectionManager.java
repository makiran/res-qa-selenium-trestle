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
public class MloConnectionManager extends Wraper {
	
	private static volatile MloConnectionManager mloconnectionmanager = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public MloConnectionManager setStringData(String stringData) {
		this.stringData = stringData;
		return MloConnectionManager.getInstance();
	}
	
	public MloConnectionManager setData(Map<String, String> data) {
		this.data = data;
		return MloConnectionManager.getInstance();
	}

	public static MloConnectionManager getInstance() {
		if (mloconnectionmanager == null) {
			synchronized (MloConnectionManager.class) {
				if (mloconnectionmanager == null) {
					mloconnectionmanager = new MloConnectionManager();
				}
			}
		}
		return mloconnectionmanager;
	}
	public void verifyMloConnectionManager(){
		verifyMloConnectionManagerForTp();
		verifyMloConnectionManagerForBroker();
		verifyMloTPConnectionDetails();
	}
	
	
	public void verifyMloConnectionManagerForTp(){
		String methodName="verifyMloConnectionManagerForTp";
		String tpColumn1="CONNECTION STATUS";
		String tpColumn2="TECHNOLOGY PROVIDER";
		String tpColumn3="PRODUCT";
		String tpColumn4="DATA FEED - DELIVERY";	
		String tpColumn5="CONTRACT TYPE";
		String tpColumn6="CONTRACT STATUS";
		if(isObjectExist("//*[@id='tblTechProviderConnectionList']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "MLO's Connection Manager table for TP is available ");
			if(findElement(By.xpath("//*[@id='tblTechProviderConnectionList']/thead/tr/th[1]")).getText().equals(tpColumn1)) {
				passLogInfo(methodName, "Connection Status column is available");
			}
			else
				failLogInfo(methodName, "Connection Status column is not available");
			if(findElement(By.xpath("//*[@id='tblTechProviderConnectionList']/thead/tr/th[2]")).getText().equals(tpColumn2)) {
				passLogInfo(methodName, "Technology Provider column is available");
			}
			else
				failLogInfo(methodName, "Technology Provider column is not available");
			if(findElement(By.xpath("//*[@id='tblTechProviderConnectionList']/thead/tr/th[3]")).getAttribute("innerText").equals(tpColumn3)) {
				passLogInfo(methodName, "Product column is available");
			}
			else
				failLogInfo(methodName, "Product column is not available");
			if(findElement(By.xpath("//*[@id='tblTechProviderConnectionList']/thead/tr/th[4]")).getText().equals(tpColumn4)) {
				passLogInfo(methodName, "Data Feed - Delivery column is available");
			}
			else
				failLogInfo(methodName, "Data Feed - Delivery column is not available");
			if(findElement(By.xpath("//*[@id='tblTechProviderConnectionList']/thead/tr/th[5]")).getText().equals(tpColumn5)) {
				passLogInfo(methodName, "Contract Type column is available");
			}
			else
				failLogInfo(methodName, "Contract Type column is not available");
			if(findElement(By.xpath("//*[@id='tblTechProviderConnectionList']/thead/tr/th[6]")).getText().equals(tpColumn6)) {
				passLogInfo(methodName, "Contract Status column is available");
			}
			else
				failLogInfo(methodName, "Contract Status column is not available");
		}
		else
			failLogInfo(methodName, "MLO's Connection Manager table for TP is not available");
		
	}
	
	
	public void verifyMloConnectionManagerForBroker(){
		String methodName="verifyMloConnectionManagerForBroker";
		String tpColumn1="CONNECTION STATUS";
		String tpColumn2="BROKER";
		String tpColumn3="PRODUCT";
		String tpColumn4="DATA FEED DELIVERY";	
		String tpColumn5="CONTRACT TYPE";
		String tpColumn6="CONTRACT STATUS";
		if(isObjectExist("//*[@id='tblBrokerConnectionList']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "MLO's Connection Manager table for Broker is available ");
			if(findElement(By.xpath("//*[@id='tblBrokerConnectionList']/thead/tr/th[1]")).getText().equals(tpColumn1)) {
				passLogInfo(methodName, "Connection Status column is available");
			}
			else
				failLogInfo(methodName, "Connection Status column is not available");
			if(findElement(By.xpath("//*[@id='tblBrokerConnectionList']/thead/tr/th[2]")).getText().equals(tpColumn2)) {
				passLogInfo(methodName, "Technology Provider column is available");
			}
			else
				failLogInfo(methodName, "Technology Provider column is not available");
			if(findElement(By.xpath("//*[@id='tblBrokerConnectionList']/thead/tr/th[3]")).getAttribute("innerText").equals(tpColumn3)) {
				passLogInfo(methodName, "Product column is available");
			}
			else
				failLogInfo(methodName, "Product column is not available");
			if(findElement(By.xpath("//*[@id='tblBrokerConnectionList']/thead/tr/th[4]")).getText().equals(tpColumn4)) {
				passLogInfo(methodName, "Data Feed - Delivery column is available");
			}
			else
				failLogInfo(methodName, "Data Feed - Delivery column is not available");
			if(findElement(By.xpath("//*[@id='tblBrokerConnectionList']/thead/tr/th[5]")).getText().equals(tpColumn5)) {
				passLogInfo(methodName, "Contract Type column is available");
			}
			else
				failLogInfo(methodName, "Contract Type column is not available");
			if(findElement(By.xpath("//*[@id='tblBrokerConnectionList']/thead/tr/th[6]")).getText().equals(tpColumn6)) {
				passLogInfo(methodName, "Contract Status column is available");
			}
			else
				failLogInfo(methodName, "Contract Status column is not available");
		}
		else
			failLogInfo(methodName, "MLO's Connection Manager table for Broker is not available");
	}
	
	
	public void verifyMloTPConnectionDetails(){
		String tab1="Connection Details";
		String tab2="Product Details";
		String tab3="Fees";
		String tab4="Contracts";
		String tab5="Messages";
		String methodName="verifyMloTPConnectionDetails";
		click("//*[@id='tblTechProviderConnectionList']/tbody/tr[1]/td[2]/a", Using.XPATH, TIMEOUT);
		if(findElement(By.xpath("//*[@class='active']/a")).getText().equals(tab1)) {
			passLogInfo(methodName, "Connection Details tab is displayed");
		}
		else
			failLogInfo(methodName, "Connection Details tab is not displayed");
		click("//*[@href='#productDetails']", Using.XPATH, TIMEOUT);
		if(findElement(By.xpath("//*[@class='active']/a")).getText().equals(tab2)) {
			passLogInfo(methodName, "Product Details tab is displayed");
		}
		else
			failLogInfo(methodName, " Product Details tab is not displayed");
		click("//*[@href='#feeDetails']", Using.XPATH, TIMEOUT);
		if(findElement(By.xpath("//*[@class='active']/a")).getText().equals(tab3)) {
			passLogInfo(methodName, "Fees tab is displayed");
		}
		else
			failLogInfo(methodName, "Fees tab is not displayed");
		click("//*[@href='#contracts']", Using.XPATH, TIMEOUT);
		if(findElement(By.xpath("//*[@class='active']/a")).getText().equals(tab4)) {
			passLogInfo(methodName, "Contracts tab is displayed");
		}
		else
			failLogInfo(methodName, "Contracts tab is not displayed");
		click("//*[@href='#messages']", Using.XPATH, TIMEOUT);
		if(findElement(By.xpath("//*[@class='active']/a")).getText().equals(tab5)) {
			passLogInfo(methodName, "Messages tab is displayed");
		}
		else
			failLogInfo(methodName, "Messages tab is not displayed");
		
		click("//*[@href='/MLO/Connections']", Using.XPATH, TIMEOUT);
		if(isObjectExist("//*[contains(text(),'Connection Manager')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "User is back on Connection Manager page");
		}
		else
			assertError("Connections button is not working", true);
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
