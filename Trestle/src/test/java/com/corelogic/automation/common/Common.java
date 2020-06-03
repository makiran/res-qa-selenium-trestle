package com.corelogic.automation.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.corelogic.automation.BaseLogin;
import com.corelogic.automation.Global;
import com.corelogic.automation.Wraper;
import com.corelogic.utaf.main.WebDriverApplication;
import com.corelogic.utaf.main.internal.Using;


public class Common extends WebDriverApplication {

	private static volatile Common common = null;
	public String stringData = "";
	public Map<String,String> data = null;
	Wraper wraper = new Wraper();
	BaseLogin baseLogin = new BaseLogin();

	/**Common Search by Violation Search Criteria**//*
	public void searchByViolation() {

		String methodName = "searchByViolation";
		wraper.clickOnIcon("search");
		wraper.clickOnTab("Search Criteria");
		wraper.clickOnButton("Clear Form", "Clear Form");
		if(isObjectExist(Global.strVioState, Using.XPATH, TIMEOUT)) {
			select(Global.strVioState, Using.XPATH, Keywords.strVioState, TIMEOUT);
			passLogInfo(methodName, "Selected '"+Keywords.strVioState+"' from Violation State dropdown");
		} else
			assertError("Violation State dropdown does not exists on 'Search Listing/Violation' Form", true);
		if(isObjectExist(Global.strVioName, Using.XPATH, TIMEOUT)) {
			select(Global.strVioName, Using.XPATH, Keywords.strVioName, TIMEOUT);
			passLogInfo(methodName, "Selected '"+Keywords.strVioName+"' from Violation Name dropdown");
		} else
			assertError("Violation Name dropdown does not exists on 'Search Listing/Violation' Form", true);
		if(isObjectExist(Global.strVioSearch, Using.XPATH, TIMEOUT)) {
			click(Global.strVioSearch, Using.XPATH, TIMEOUT);
			passLogInfo(methodName, "clicked on Violation Search button");
		} else
			assertError("Violation Name dropdown does not exists on 'Search Listing/Violation' Form", true);
		pageLoad(Global.strTabOn, "Search Results");
		navigateToResultsPage("Search Results");

	}*/

	/**Search Criteria By Quick LookUp**/
	public void quickLookUpCriteria() {
		wraper.clickOnIcon("home");
		if(isObjectExist(Global.strQuickLookUpValue, Using.XPATH, TIMEOUT)) {
			type(Global.strQuickLookUpValue, Using.XPATH, data.get("ListingNumber"), TIMEOUT);
			wait(1000);
		} else
			assertError("'Quick Look Up Listing' test box does not exists on Home Page", true);
		if(isObjectExist(Global.strQuickLookUpGo, Using.XPATH, TIMEOUT)) {
			click(Global.strQuickLookUpGo, Using.XPATH, TIMEOUT);
			wait(2000);
		} else
			assertError("'Quick Look Up Go' button does not exists on Home Page", true);
		pageLoad(Global.strTabOn, "Search Results");
		navigateToResultsPage("Search Results");
	}

	/**Wait for page to load for tab/subtab**/
	public void pageLoad(String tabON, String tabName) {
		int i =1;
		if(isObjectExist(tabON, Using.XPATH, 7000)) {
			while(!getText(tabON, Using.XPATH, TIMEOUT).contains(tabName)) {
				wait(1000);
				if(i>41) {
					assertError("Failed to load '"+ tabName +"' page in "+i+" seconds", true);
					break;
				}
				i++;
			}
		} 
	}
	
	/**Wait for tab to load**/
	public void tabLoad(String tabName) {
		int i =1;
			while(!isObjectExist(Global.strLoadTab.replace("@@@", tabName), Using.XPATH, 1000)) {
				wait(1000);
				if(i>41) {
					assertError("Failed to load '"+ tabName +"' page in "+i+" seconds", true);
					break;
				}
				i++;
		} 
	}

	/**To Validate control navigated to Search Results page**/
	public void navigateToResultsPage(String tabName) {
		String methodName = "navigateToResultsPage";
		if(getText("//*[@class='tab_on'] | //*[@class='subtab_on']", Using.XPATH, TIMEOUT).contains(tabName)) {
			passLogInfo(methodName, "Control navigated to '" +tabName+ "' page");
		}		
		else
			assertError("Control did not navigate to '" +tabName+ "' page", true);
	}

	/**To clear form**/
	public void clearForm() {
		String methodName = "clearForm";
		String input = "Clear Form";
		String xPath = LDCXpaths.getInstance().getXpath(
				"xpathforbutton", input.trim());
		String xPathText = LDCXpaths.getInstance().getXpath(
				"xpathforcontainstext", "There is currently no search criteria".trim());
		if(isObjectExist(xPath, Using.XPATH, TIMEOUT)) {
			click(xPath, Using.XPATH, TIMEOUT);
			wait(2000);
			if(isObjectExist(xPathText, Using.XPATH, TIMEOUT)) 
				passLogInfo(methodName, "Form is cleared when clicked on 'Clear Form' button");
			else
				warnLogInfo(methodName, "Form is not cleared when clicked on 'Clear Form' button");
		}
		else
			failLogInfo(methodName, "'Clear Form' button does not exit on Form");
	}

	/***To get time based on Time Zone***/
	public String getTimeZone(String timeZone) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		String s=sdf.format(date);
		return s;
	}

	/**Count of number of Results for a given Criteria**/
	public int resultsCount() {
		int count = 0;
		if(isObjectExist(Global.strNextArrow, Using.XPATH, TIMEOUT)) {
			String results[] = getText("(//*[@class='subheaderBlackNoSpace'])[1]", Using.XPATH, TIMEOUT).split("to");
			String count1[] = results[1].split(" ");
			count = Integer.parseInt(count1[1].trim());
		}
		else if(isObjectExist("(//*[@class='subheaderBlackNoSpace'])[1]", Using.XPATH, TIMEOUT)) {
			String results[] = getText("(//*[@class='subheaderBlackNoSpace'])[1]", Using.XPATH, TIMEOUT).split("of");
			count = Integer.parseInt(results[1].trim());
		}
		return count;
	}

	/**Page Load Verification Using Object Verification**/
	public void waitForPageToLoad(String xPath, String tabName) {
		int i =1;
		while(!isObjectExist(xPath, Using.XPATH, TIMEOUT)) {
			wait(1000);
			if(i>41) {
				assertError("Failed to load '"+ tabName +"' page in "+i+" seconds", true);
				break;
			}
			i++;
		}
	}

	/**Method to Switch Between Windows**/
	public void selectWindow(int index) {
		try {
			Set<String> WindowHandles;
			WebDriver driver = getDriver();
			WindowHandles = driver.getWindowHandles();
			String[] array = WindowHandles.toArray(new String[0]);
			window(array[index]);
		} catch (Exception e) {
			//e.printStackTrace();
			assertError(e.getMessage(), true);
		}
	}	

	/***Method to get Date for a particular Time Zone***/
	public String getDateForTimeZone(String strTimeZone) {
		/*Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));

		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.setTimeZone(cal.getTimeZone());
		String s = df.format(cal.getTime());*/
		String input = "MM/dd/yyyy";
  		DateTime time = new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(strTimeZone)));
  		String s = time.toString(input);
		return s;
	}

	/**Common Listing/Violation Search Criteria**/
	public void searchByListingVio(Map<String, String> data) {
		String methodName = "searchByListingVio";
		wraper.clickOnIcon("search");
		wraper.clickOnTab("Search Criteria");
		wraper.clickOnButton("Clear Form", "Clear Form");
		if(data.containsKey("VioState")) {
			objectVerification(Global.strVioState, "VioState");
			//select(Global.strVioState, Using.XPATH, data.get("VioState"), TIMEOUT);
			wraper.selectFromDropdown("Violation State", Global.strVioState, data.get("VioState"));
			//wait(1000);
			passLogInfo(methodName, "Selected Violation State: "+data.get("VioState")+"");
		}
		if(data.containsKey("VioName")) {
			objectVerification(Global.strVioName, "VioName");
			select(Global.strVioName, Using.XPATH, data.get("VioName"), TIMEOUT);
			passLogInfo(methodName, "Selected Violation Name: "+data.get("VioName")+"");
		}
		if(data.containsKey("ListStatus")) {
			objectVerification(Global.strListStatus, "ListStatus");
			select(Global.strListStatus, Using.XPATH, data.get("ListStatus"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected List Status: "+data.get("ListStatus")+"");
		}
		if(data.containsKey("PropType")) {
			objectVerification(Global.strPropType, "Property Type");
			select(Global.strPropType, Using.XPATH, data.get("PropType"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Property Type: "+data.get("PropType")+"");
		}
		if(data.containsKey("Agent")) {
			objectVerification(Global.strAgent, "Agent");
			type(Global.strAgent, Using.XPATH, data.get("Agent"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent: "+data.get("Agent")+"");
		}
		if(data.containsKey("AgentPubID")) {
			objectVerification(Global.strAgentPubID, "Agent PUB ID");
			type(Global.strAgentPubID, Using.XPATH, data.get("AgentPubID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent Pub Id: "+data.get("AgentPubID")+"");
		}
		if(data.containsKey("Email")) {
			objectVerification(Global.strEmail, "Email");
			type(Global.strEmail, Using.XPATH, data.get("Email"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Email: "+data.get("Email")+"");
		}
		if(data.containsKey("Office")) {
			objectVerification(Global.strOffice, "Office");
			type(Global.strOffice, Using.XPATH, data.get("Office"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Office: "+data.get("Office")+"");
		}
		if(data.containsKey("OfficePubID")) {
			objectVerification(Global.strOfficePubID, "Office Pub ID");
			type(Global.strOfficePubID, Using.XPATH, data.get("OfficePubID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Office Pub ID: "+data.get("OfficePubID")+"");
		}
		if(data.containsKey("VioDatePeriod")) {
			objectVerification(Global.strVioDatePeriod, "Violation Date");
			select(Global.strVioDatePeriod, Using.XPATH, data.get("VioDatePeriod"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Violation Date Period: "+data.get("VioDatePeriod")+"");
		}
		if(data.containsKey("VioDateFrom") && data.containsKey("VioDateTo")) {
			objectVerification(Global.strVioDateRange, "Violation Date Range");
			click(Global.strVioDateRange, Using.XPATH, TIMEOUT);
			wait(3000);
			type(Global.strVioDateFrom, Using.XPATH, data.get("VioDateFrom"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Violation Date From: "+data.get("VioDateFrom")+"");
			type(Global.strVioDateTo, Using.XPATH, data.get("VioDateTo"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Violation Date To: "+data.get("VioDateTo")+"");
		}
		if(data.containsKey("GraceDatePeriod")) {
			objectVerification(Global.strGraceDatePeriod, "Grace Date");
			select(Global.strGraceDatePeriod, Using.XPATH, data.get("GraceDatePeriod"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Grace Date Priod: "+data.get("GraceDatePeriod")+"");
		}
		if(data.containsKey("GraceDateFrom") && data.containsKey("GraceDateTo")) {
			objectVerification(Global.strGraceDateRange, "Grace Date Range");
			click(Global.strGraceDateRange, Using.XPATH, TIMEOUT);
			wait(3000);
			type(Global.strGraceDateFrom, Using.XPATH, data.get("GraceDateFrom"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Grace Date From: "+data.get("GraceDateFrom")+"");
			type(Global.strGraceDateTo, Using.XPATH, data.get("GraceDateTo"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Grace Date To: "+data.get("GraceDateTo")+"");
		}
		if(findElement(By.xpath(Global.strIncludingHistory)).isSelected()) {
			if(data.containsKey("VioHisDateFrom") && data.containsKey("VioHisDateTo")) {
				type(Global.strVioHisFrom, Using.XPATH, data.get("VioHisDateFrom"), TIMEOUT);
				wait(1000);
				passLogInfo(methodName, "Selected Vio History Date From: "+data.get("VioHisDateFrom")+"");
				type(Global.strVioHisTo, Using.XPATH, data.get("VioHisDateTo"), TIMEOUT);
				wait(1000);
				passLogInfo(methodName, "Selected Vio History Date To: "+data.get("VioHisDateTo")+"");
			}
		}
		if(data.containsKey("TextNote")) {
			objectVerification(Global.strTextWithinNote, "Text Within Note");
			type(Global.strTextWithinNote, Using.XPATH, data.get("TextNote"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Text Within Note: "+data.get("TextNote")+"");
		}
		if(data.containsKey("SaveSearchName")) {
			objectVerification(Global.strSaveSearchName, "Save Search Name");
			type(Global.strSaveSearchName, Using.XPATH, data.get("SaveSearchName"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Save Search Name: "+data.get("SaveSearchName")+"");
		}
		if(data.containsKey("ConditionOR")) {
			objectVerification(Global.strConditionOR, "OR Condtion");
			click(Global.strConditionOR, Using.XPATH, TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected 'OR' Radio button");
		}
		if(isObjectExist(Global.strVioSearch, Using.XPATH, TIMEOUT)) {
			click(Global.strVioSearch, Using.XPATH, TIMEOUT);
			passLogInfo(methodName, "clicked on Violation Search button");
			wait(1000);
			if(isAlertPresent()) {
				alert().accept();
			}
			tabLoad("Search Results");
			navigateToResultsPage("Search Results");
		} else
			assertError("Search button does not exists on 'Search Listing/Violation' Form", true);
	}
	
	/**Search By Agent Details Or Violation Number**/
	public void searchByAgent(Map<String, String> data) {
		String methodName = "searchByAgent";
		wraper.clickOnIcon("search");
		wraper.clickOnTab("Search Criteria");
		wraper.clickOnSubTab("Search Agent Form");
		if(data.containsKey("AgentID")) {
			objectVerification(Global.strAgtID, "Agent ID");
			type(Global.strAgtID, Using.XPATH, data.get("AgentID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent ID: "+data.get("AgentID")+"");
		}
		if(data.containsKey("AgentPubID")) {
			objectVerification(Global.strAgtPubID, "Agent Pub ID");
			type(Global.strAgtPubID, Using.XPATH, data.get("AgentPubID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent Pub ID: "+data.get("AgentPubID")+"");
		}
		if(data.containsKey("AgtLastName")) {
			objectVerification(Global.strAgtLastname, "Agent Last Name");
			type(Global.strAgtLastname, Using.XPATH, data.get("AgtLastName"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent Last name: "+data.get("AgtLastName")+"");
		}
		if(data.containsKey("AgtEmailID")) {
			objectVerification(Global.strAgtEmailID, "Agent Email ID");
			type(Global.strAgtEmailID, Using.XPATH, data.get("AgtEmailID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent Email ID: "+data.get("AgtEmailID")+"");
		}
		if(data.containsKey("OfficeID")) {
			objectVerification(Global.strAgtOfficeID, "Agent Email ID");
			type(Global.strAgtOfficeID, Using.XPATH, data.get("OfficeID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent Office ID: "+data.get("OfficeID")+"");
		}
		if(data.containsKey("AgtVio")) {
			objectVerification(Global.strAgtVio, "Agent Vio");
			type(Global.strAgtVio, Using.XPATH, data.get("AgtVio"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent Vio ID: "+data.get("AgtVio")+"");
		}
		if(isObjectExist(Global.strAgtSearch, Using.XPATH, TIMEOUT)) {
			click(Global.strAgtSearch, Using.XPATH, TIMEOUT);
			passLogInfo(methodName, "clicked on Agent Search button");
			pageLoad(Global.strTabOn, "Search Results");
			navigateToResultsPage("Search Results");
		} else
			assertError("Agent Search button does not exists on 'Search Agent' Form", true);
	}

	
	/**Search By Office Details Or Violation Number**/
	public void searchByOffice(Map<String, String> data) {
		String methodName = "searchByOffice";
		wraper.clickOnIcon("search");
		wraper.clickOnTab("Search Criteria");
		wraper.clickOnSubTab("Search Office Form");
		
		if(data.containsKey("OfficeName")) {
			objectVerification(Global.strOfcName, "Office Name");
			type(Global.strOfcName, Using.XPATH, data.get("OfficeName"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent ID: "+data.get("OfficeName")+"");
		}
		if(data.containsKey("OfficeID")) {
			objectVerification(Global.strOfcID, "Office ID");
			type(Global.strOfcID, Using.XPATH, data.get("OfficeID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent ID: "+data.get("OfficeID")+"");
		}
		if(data.containsKey("OfficePubID")) {
			objectVerification(Global.strOfcPubID, "Office Pub ID");
			type(Global.strOfcPubID, Using.XPATH, data.get("OfficePubID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent ID: "+data.get("OfficePubID")+"");
		}
		if(data.containsKey("OfficeEmailID")) {
			objectVerification(Global.strOfcEmailID, "Office Email ID");
			type(Global.strOfcEmailID, Using.XPATH, data.get("OfficeEmailID"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent ID: "+data.get("OfficeEmailID")+"");
		}
		if(data.containsKey("OfficeViolation")) {
			objectVerification(Global.strOfcViolation, "Office Violation");
			type(Global.strOfcViolation, Using.XPATH, data.get("OfficeViolation"), TIMEOUT);
			wait(1000);
			passLogInfo(methodName, "Selected Agent ID: "+data.get("OfficeViolation")+"");
		}
		if(isObjectExist(Global.strOfcSearch, Using.XPATH, TIMEOUT)) {
			click(Global.strOfcSearch, Using.XPATH, TIMEOUT);
			passLogInfo(methodName, "clicked on Office Search button");
			pageLoad(Global.strTabOn, "Search Results");
			navigateToResultsPage("Search Results");
		} else
			assertError("Office Search button does not exists on 'Search Office Form'", true);
		
	}
	/**Search Criteria By Quick LookUp**/
	public void quickLookUpCriteria(Map<String, String> data) {
		wraper.clickOnIcon("home");
		if(isObjectExist(Global.strQuickLookUpValue, Using.XPATH, TIMEOUT)) {
			type(Global.strQuickLookUpValue, Using.XPATH, data.get("ListingNumber"), TIMEOUT);
			wait(1000);
		} else
			assertError("'Quick Look Up Listing' test box does not exists on Home Page", true);
		if(isObjectExist(Global.strQuickLookUpGo, Using.XPATH, TIMEOUT)) {
			click(Global.strQuickLookUpGo, Using.XPATH, TIMEOUT);
			wait(2000);
		} else
			assertError("'Quick Look Up Go' button does not exists on Home Page", true);
	}

	/**Method for new Window Handle**/
	public String findNewWindowHandle(Set<String> existingHandles) {
		String foundHandle = "";
		while (foundHandle.isEmpty()) {
			Set<String> currentHandles = getWindowHandles();
			if (currentHandles.size() != existingHandles.size()) {
				for (String currentHandle : currentHandles) {
					if (!existingHandles.contains(currentHandle)) {
						foundHandle = currentHandle;
						break;
					}
				}
			}
		}
		return foundHandle;
	}	

	public void violationDetailsCommon(String value) {
		String methodName="ViolationDetailsCommon";
		String base=getWindowHandle();
		String textContent = "";
		List<WebElement> list = listExists(Global.strVioDetailsData,Using.XPATH,TIMEOUT);
		if (list.size()!=0) {
			for(int i=0;i<list.size();i++) {	
				List<WebElement> linkClick = listExists(Global.strVioDetailsData,Using.XPATH, 10000);
				textContent=linkClick.get(i).getText();
				if(!textContent.isEmpty())
					break;
			}	
		}
		else 
			failLogInfo(methodName, "List size is Zero");
		if(textContent.startsWith("Assoc. Edit"))
			click(Global.strVioDetailsAssocEdit,Using.XPATH,TIMEOUT);
		else
			click(Global.strVioDetailsRadio,Using.XPATH,TIMEOUT);
		if(value=="Update and Move to Next Record")	{
			click(Global.strUpdateAndMoveToNextRecord, Using.XPATH, TIMEOUT);
			waitForPageToLoad();
		}
		if(value=="Update") {
			click(Global.strUpdate, Using.XPATH, TIMEOUT); 
			waitForPageToLoad();
		}
		ArrayList<String> childWindow = new ArrayList<String>(getWindowHandles()); 
		if(childWindow.size()>1) {
			childWindow.remove(base); 
			window(childWindow.get(0));
			String pageTitle=getTitle(); 
			String currentURL=getCurrentUrl(); 
			if((currentURL.contains("viewReportItViolations"))||(pageTitle.contains("viewReportItViolations"))||(currentURL.contains("viewManualFineViolationList"))||(pageTitle.contains("viewManualFineViolationList"))) 
				passLogInfo("New Window opened successfully", "viewReportItViolations Popup window opened"); 
			else 
				failLogInfo("New Window open is unsuccessful", "viewReportItViolations Popup window not opened"); 		 
			click(Global.strUpdate, Using.XPATH, TIMEOUT); 
			window(base);
			if(value=="Update and Move to Next Record") {
				clickBackArrow();
				passLogInfo(methodName, "Control moved to next listing");
				vioStatesVioDetails(list, textContent);
			}
			else if(value=="Update")
				vioStatesVioDetails(list, textContent);
		}
		else
			vioStatesVioDetails(list, textContent);
	}

	/** To click on Back Arrow in Listing/Violation tab**/
	public void clickBackArrow() {
		String methodName = "clickBackArrow";
		if(isObjectExist(Global.strSearchDetailsBackArrow, Using.XPATH, TIMEOUT)) {
			click(Global.strSearchDetailsBackArrow,Using.XPATH, TIMEOUT,"Back arrow button clicked");
			passLogInfo(methodName, "clicked on Back Arrow button on 'Listing/Violation details' tab");
			wait(2000);
			waitForPageToLoad();

		} else
			warnLogInfo(methodName, "Back Arrow button does not exists on 'Listing/Violation details' tab");
	}

	/**To Retrieve Violation States from Violation details section **/
	public void vioStatesVioDetails(List<WebElement> list, String textContent) {
		String methodName = "vioStatesVioDetails";
		list = listExists(Global.strSearchDetailsVioStates,Using.XPATH,TIMEOUT);
		if (list.size()!=0)	{
			for(int i=0;i<list.size();i++) {	
				List<WebElement> linkClick = listExists(Global.strSearchDetailsVioStates,Using.XPATH, 10000);
				String vioStatestatus=linkClick.get(i).getText();
				if(textContent.toLowerCase().contains(vioStatestatus.toLowerCase())) {
					passLogInfo(methodName, "Violation state is updated with selected manual state");
					break;
				}
			}
		}
		else
			failLogInfo(methodName, "No Violation States exists in Violation details section on listing/violation details tab");
	}

	/**Object Verification**/
	public void objectVerification(String objectPath, String objectName) {
		String methodName = "objectVerification";
		if(isObjectExist(objectPath, Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, ""+objectName+" exists");
		} else
			assertError(""+objectName+" does not exist", true);
	}

	/**To get the Row of a webelement**/
	public int getWebElementRow(String webElementTable, String attributeValue) {
		WebElement rows = findElement(By.xpath(webElementTable));
		List<WebElement> row = rows.findElements(By.tagName("tr"));
		int i = 1;
		while(i<row.size()) {
			if(isObjectExist("//tr["+i+"]/td/input[contains(@id,'"+attributeValue+"')]", Using.XPATH, 1000)) {
				break;
			}
			i++;
		}
		return i;	
	}

	/*Checks for alert box and returns true if present and false if not*/
	public boolean isAlertPresent() { 
		try { 
			getDriver().switchTo().alert(); 
			return true; 
		}   
		catch (NoAlertPresentException Ex) { 
			return false; 
		}    
	}

	public void selectQuickLookUP(String dropdownVal, String option) {

		if(isObjectExist(Global.strSelectQuickLookUp.replace("@@@", dropdownVal), Using.XPATH, TIMEOUT)) {
			click(Global.strSelectQuickLookUp.replace("@@@", dropdownVal), Using.XPATH, TIMEOUT);
		} else
			assertError("Quick LookUp section doesn't exists with dropdown val: "+dropdownVal+"", true);
	}

	public String getListingNumber() {
		String listingNumber = "getListingNumber";
		frame(0);
		if(isObjectExist(Global.strSearchDetailsMLSNum, Using.XPATH, TIMEOUT)) {
			listingNumber = getText(Global.strSearchDetailsMLSNum, Using.XPATH, TIMEOUT);
		} else
			assertError("MLS Listing number does not exists on Search Details tab", true);
		return listingNumber;
	}

	public void activateBatchProcess() {
		String methodName = "activateBatchProcess";
		if(isObjectExist(Global.strCustomNotifyBatch, Using.XPATH, TIMEOUT)) {
			wraper.clickOnIcon("batch");
			if(isObjectExist(Global.strCustomNotifyExpand, Using.XPATH, TIMEOUT)) {
				click(Global.strCustomNotifyExpand, Using.XPATH, TIMEOUT);
				wait(2000);
				waitForPageToLoad();
				if(isObjectExist(Global.strBatchProcessButton, Using.XPATH, TIMEOUT)) {
					click(Global.strBatchProcessButton, Using.XPATH, TIMEOUT);
					wait(2000);
					waitForPageToLoad();
					if(isObjectExist(Global.strBatchExecutionSuccessfull, Using.XPATH, TIMEOUT)) 
						passLogInfo(methodName, "Batch has been executed successfully");
					else 
						failLogInfo(methodName, "Batch has not been executed successfully");
				}
			} else
				failLogInfo(methodName, "Expand button does not exists");
		} else 
			warnLogInfo(methodName, "Custom Notify batch is not in active state");	
	}
	
	
	public void tabORSubTabON(String tabName) {
		String methodName = "tabON";
		if(isObjectExist(Global.strTabOrSubTabActive.replace("@@@", tabName), Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, ""+tabName+" tab is selected");
		} else
			assertError(""+tabName+" tab is not selected", true);
	}
	
	
	public static void waitForElementToBeClickable(WebElement element, int timeout) {

        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
 }

	
	
	public static void test(){
		System.out.println("tesT");
	}
}
