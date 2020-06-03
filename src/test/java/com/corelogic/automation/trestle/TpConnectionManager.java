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
public class TpConnectionManager extends Wraper {
	
	private static volatile TpConnectionManager tpconnectionmanager = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public TpConnectionManager setStringData(String stringData) {
		this.stringData = stringData;
		return TpConnectionManager.getInstance();
	}
	
	public TpConnectionManager setData(Map<String, String> data) {
		this.data = data;
		return TpConnectionManager.getInstance();
	}

	public static TpConnectionManager getInstance() {
		if (tpconnectionmanager == null) {
			synchronized (TpConnectionManager.class) {
				if (tpconnectionmanager == null) {
					tpconnectionmanager = new TpConnectionManager();
				}
			}
		}
		return tpconnectionmanager;
	}
	public void verifyTpConnectionManager(){
		verifyTpConnectionManagerForMlo();
		//verifyTpConnectionManagerForUdp();
	}
	
	
	public void verifyTpConnectionManagerForMlo(){
		String methodName="verifyMloConnectionManagerForTp";
		String tpColumn1="CONNECTION STATUS";
		String tpColumn2="MULTIPLE LISTING ORGANIZATION / LOCATION";
		String tpColumn3="PRODUCT";
		String tpColumn4="DATA FEED - DELIVERY";	
		String tpColumn5="CONTRACT TYPE";
		String tpColumn6="CONTRACT STATUS";
		String tpColumn7="ORIGINATING ID";
		String tpColumn8="SUB-BOARD";
		if(isObjectExist("//*[@class='alert alert-salmon']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Pending tasks alert is displayed on page");
		}
		else
			failLogInfo(methodName, "Pending tasks alert is not displayed on page");
		if(isObjectExist("//*[@class='btn btn-info disabled']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Add MLO Connection button is disabled");
		}
		else
			failLogInfo(methodName, "Add MLO Connection button is enabled");
		if(isObjectExist("//*[@id='tblMLSConnectionList']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "TP's Connection Manager table for MLO is available ");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[1]")).getText().equals(tpColumn1)) {
				passLogInfo(methodName, "Connection Status column is available");
			}
			else
				failLogInfo(methodName, "Connection Status column is not available");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[2]")).getText().equals(tpColumn2)) {
				passLogInfo(methodName, "Multiple Listing Organization column is available");
			}
			else
				failLogInfo(methodName, "Multiple Listing Organization column is not available");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[3]")).getAttribute("innerText").equals(tpColumn3)) {
				passLogInfo(methodName, "Product column is available");
			}
			else
				failLogInfo(methodName, "Product column is not available");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[4]")).getText().equals(tpColumn4)) {
				passLogInfo(methodName, "Data Feed - Delivery column is available");
			}
			else
				failLogInfo(methodName, "Data Feed - Delivery column is not available");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[5]")).getText().equals(tpColumn5)) {
				passLogInfo(methodName, "Contract Type column is available");
			}
			else
				failLogInfo(methodName, "Contract Type column is not available");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[6]")).getText().equals(tpColumn6)) {
				passLogInfo(methodName, "Contract Status column is available");
			}
			else
				failLogInfo(methodName, "Contract Status column is not available");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[7]")).getText().equals(tpColumn7)) {
				passLogInfo(methodName, "Originating ID column is available");
			}
			else
				failLogInfo(methodName, "Originating ID column is not available");
			if(findElement(By.xpath("//*[@id='tblMLSConnectionList']/thead/tr/th[8]")).getText().equals(tpColumn8)) {
				passLogInfo(methodName, "Sub-board column is available");
			}
			else
				failLogInfo(methodName, "Sub-board column is not available");
		}
		else
			failLogInfo(methodName, "TP's Connection Manager table for MLO is not available");
		
	}
	
	
	public void verifyTpConnectionManagerForUdp(){
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
