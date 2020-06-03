package com.corelogic.automation;


import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.corelogic.automation.common.LDCXpaths;
import com.corelogic.utaf.main.WebDriverApplication;
import com.corelogic.utaf.main.internal.Using;



public class Wraper extends WebDriverApplication {
	private static volatile Wraper wraper = null;
	
	/**Verify fields on form**/
	public void verifyForm(String input, String formName) {
		//String methodName = "verifyForm";
		String fields[] = input.split("&");
		for(int i=0;i<fields.length;i++)  {
			String fieldNames[] = fields[i].split("=");
			String fieldValues[] = fieldNames[1].split("~");
			for(int j=0;j<fieldValues.length;j++) {
				String formFields[] = fieldValues[j].split("-");
				String xPath = LDCXpaths.getInstance().getXpath("xpathfor"+fieldNames[0], formFields[1].trim());
				if(isObjectExist(xPath, Using.XPATH, TIMEOUT)) {
					passLogInfo(fieldNames[0], "'" +formFields[0]+ "' exists on '" +formName+ "'");
				}
				else
					failLogInfo(fieldNames[0], "'" +formFields[0]+ "' does not exists on '" +formName+ "'");
			}
		}
	}
	
	/**Verify Tab Active**/
	public void tabActive(String input, String formName) {
		//String methodName = "verifyForm";
		String fields[] = input.split("&");
		for(int i=0;i<fields.length;i++)  {
			String fieldNames[] = fields[i].split("=");
			String fieldValues[] = fieldNames[1].split("~");
			for(int j=0;j<fieldValues.length;j++) {
				String formFields[] = fieldValues[j].split("-");
				String xPath = LDCXpaths.getInstance().getXpath("xpathfor"+fieldNames[0], formFields[1].trim());
				if(isObjectExist(xPath, Using.XPATH, TIMEOUT))
					passLogInfo(fieldNames[0], "'" +formFields[0]+ "' is  active on '" +formName+ "'");
				else
					warnLogInfo(fieldNames[0], "'" +formFields[0]+ "' is not active on '" +formName+ "'");
			}
		}
	}

	/**Verify Tab InActive**/
	public void tabInActive(String input, String formName) {
		//String methodName = "verifyForm";
		String fields[] = input.split("&");
		for(int i=0;i<fields.length;i++)  {
			String fieldNames[] = fields[i].split("=");
			String fieldValues[] = fieldNames[1].split("~");
			for(int j=0;j<fieldValues.length;j++) {
				String formFields[] = fieldValues[j].split("-");
				String xPath = LDCXpaths.getInstance().getXpath("xpathfor"+fieldNames[0], formFields[1].trim());
				if(isObjectExist(xPath, Using.XPATH, TIMEOUT)) {
					passLogInfo(fieldNames[0], "'" +formFields[0]+ "' is  inactive on '" +formName+ "'");
				}
				else
					warnLogInfo(fieldNames[0], "'" +formFields[0]+ "' is not inactive on '" +formName+ "'");
			}
		}
	}

	/**Verify Subtab Active**/
	public void subTabActive(String input, String formName) {
		//String methodName = "verifyForm";
		String fields[] = input.split("&");
		for(int i=0;i<fields.length;i++)  {
			String fieldNames[] = fields[i].split("=");
			String fieldValues[] = fieldNames[1].split("~");
			for(int j=0;j<fieldValues.length;j++) {
				String formFields[] = fieldValues[j].split("-");
				String xPath = LDCXpaths.getInstance().getXpath("xpathfor"+fieldNames[0], formFields[1].trim());
				if(isObjectExist(xPath, Using.XPATH, TIMEOUT)) {
					passLogInfo(fieldNames[0], "'" +formFields[0]+ "' is  active on '" +formName+ "'");
				}
				else
					warnLogInfo(fieldNames[0], "'" +formFields[0]+ "' is not active on '" +formName+ "'");
			}
		}
	}

	/**Verify Subtab Active**/
	public void subTabInActive(String input, String formName) {
		//String methodName = "verifyForm";
		String fields[] = input.split("&");
		for(int i=0;i<fields.length;i++)  {
			String fieldNames[] = fields[i].split("=");
			String fieldValues[] = fieldNames[1].split("~");
			for(int j=0;j<fieldValues.length;j++) {
				String formFields[] = fieldValues[j].split("-");
				String xPath = LDCXpaths.getInstance().getXpath("xpathfor"+fieldNames[0], formFields[1].trim());
				if(isObjectExist(xPath, Using.XPATH, TIMEOUT)) {
					passLogInfo(fieldNames[0], "'" +formFields[0]+ "' is  inactive on '" +formName+ "'");
				}
				else
					warnLogInfo(fieldNames[0], "'" +formFields[0]+ "' is not inactive on '" +formName+ "'");
			}
		}
	}

	/**To populate data into the text field**/
	public void typeData(String input) {
		String[] inputs = input.split("&");
		for (int i = 0; i < inputs.length; i++) {
			String data[] = inputs[i].split("=");
			String xpath = LDCXpaths.getInstance().getXpath(
					"xpathfortextbox", data[0].trim());
			if (isObjectExist(xpath, Using.XPATH, 5000)) {
				if (getDriver().findElement(By.xpath(xpath)).isEnabled()) {
					type(xpath, Using.XPATH, data[1], 5000);
					passLogInfo("typeData", "'" + data[1].trim()
							+ "' is entered into '" + data[0].trim()
							+ "' Textbox");
				} else
					failLogAndAssert("typeData", "'" + data[1].trim()
							+ " is not enabled '" + data[0].trim()
							+ "' Textbox");
			} else
				assertError("'" + data[1].trim()
						+ " is Not entered into '" + data[0].trim()
						+ "' Textbox", true);
		}
	}

	/**To select value from the dropdown**/
	public void selectFromDropdown(String input) {
		String[] inputs = input.split("&");
		for (int i = 0; i < inputs.length; i++) {
			String data[] = inputs[i].split("=");
			String xpath = LDCXpaths.getInstance().getXpath(
					"xpathfordropdown", data[0].trim());
			if (isObjectExist(xpath, Using.XPATH, 5000)) {
				if (getDriver().findElement(By.xpath(xpath)).isEnabled()) {
					String dropDownData[] = data[1].split("~");
					for(int j=0;j<dropDownData.length;j++) {
						select(xpath, Using.XPATH, dropDownData[j], 5000);
						passLogInfo("selectFromDropdown", "'" + data[1].trim()
								+ "' is entered properly into '" + data[0].trim()
								+ "' dropdown");
					}
				}
				else
					assertError("'" +data[0]+ "' dropdown is not enabled", true);
			} else
				assertError("'" +data[0]+ "'dropdown does not exist", true);
		}
	}
	
	/**To select multiple values from a dropdown**/
	public void selectFromDropdown(String dropDownName, String xpath, String input) {
		String[] data = input.split(",");
		for (int i = 0; i < data.length; i++) {
			if (exist(xpath, Using.XPATH, TIMEOUT)) {
				select(xpath, Using.XPATH, data[i], TIMEOUT);
				//wait(1000);
				passLogInfo("selectFromDropDown", "'" + data[i].trim()
						+ "' is selected from the '" + dropDownName
						+ "' DropDown");
			} else
				failLogAndAssert("selectFromDropDown", "'" + data[i].trim()
						+ "' is not selected from the '" + dropDownName
						+ "' DropDown");
		}
	}

	/**To click on Tab**/
	public void clickOnTab(String input) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathfortab", input.trim());
		if (isObjectExist(xpath, Using.XPATH, 5000)) {
			click(xpath, Using.XPATH, 5000);
			passLogInfo("clickOnTab", "clicked on the '" +input+ "' Tab");
			wait(2000);
			pageLoad(Global.strTabOn, input);
		} else
			assertError("'" +input+ "' Tab is not available", true);
	}

	/**To click on Subtab**/
	public void clickOnSubTab(String input) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathforsubtab", input.trim());
		if (isObjectExist(xpath, Using.XPATH, 5000)) {
			click(xpath, Using.XPATH, 5000);
			passLogInfo("clickOnSubTab", "clicked on the '" +input+ "' subtab");
			wait(2000);
			waitForPageToLoad();
			if(getText("//*[@class='subtab_on']", Using.XPATH, TIMEOUT).contains(input)) 
				passLogInfo("clickOnSubTab", "Control moved to "+input+" sub tab");
			else
				assertError("Control did not move to "+input+" sub tab", true);
		} else
			assertError("'" +input+ "' subtab is not available", true);
	}

	/**To click on Radio Button**/
	public void clickOnRadioButton(String input) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathforradiobutton", input.trim());
		if (isObjectExist(xpath, Using.XPATH, 5000)) {
			click(xpath, Using.XPATH, 5000);
			passLogInfo("clickOnRadioButton", "clicked on the '" +input+ "' radio button");
			wait(2000);
		} else
			assertError("'" +input+ "' radio button is not available", true);
	}

	/**To click on Check box**/
	public void clickOnCheckBox(String input) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathforcheckbox", input.trim());
		if (isObjectExist(xpath, Using.XPATH, 5000)) {
			if(findElement(By.xpath(xpath)).isSelected()) {
				passLogInfo("clickOnCheckBox", "'" +input+ "' checkbox is already checked by default");
				wait(2000);
			} else {
				click(xpath, Using.XPATH, 5000);
				passLogInfo("clickOnCheckBox", "clicked on the '" +input+ "' checkbox");
				wait(2000);
			}
		} else
			assertError("'" +input+ "' checkbox is not available", true);
	}

	/**To click on Icon**/
	public void clickOnIcon(String input) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathforicon", input.trim());
		if (isObjectExist(xpath, Using.XPATH, 5000)) {
			click(xpath, Using.XPATH, 5000);
			passLogInfo("clickOnIcon", "clicked on the '" +input+ "' icon");
			wait(2000);
			waitForPageToLoad();
		} else
			assertError("" +input+ "' icon is not available", true);
	}

	/**To click on Button**/
	public void clickOnButton(String input, String buttonName) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathforbutton", input.trim());
		if(buttonName.contains("Violation Search")) {
			xpath = xpath.replace("1", "2");
		}
		if (isObjectExist(xpath, Using.XPATH, 5000)) {
			click(xpath, Using.XPATH, 5000);
			passLogInfo("clickOnButton", "clicked on the '" +buttonName+ "' button");
			wait(2000);
			waitForPageToLoad();
		} else
			assertError("'" +buttonName+ "' button is not available", true);
	}
	
	/**To click on Button inside a tab**/
	public void clickOnTabButton(String input, String buttonName) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathfortabbutton", input.trim());
		if (isObjectExist(xpath, Using.XPATH, 5000)) {
			click(xpath, Using.XPATH, 5000);
			passLogInfo("clickOnButton", "clicked on the '" +buttonName+ "' button");
			wait(2000);
			waitForPageToLoad();
		} else
			assertError("'" +buttonName+ "' button is not available", true);
	}

	/**To click on Link**/
	public void clickOnLink(String input, String linkName) {
		String xpath = LDCXpaths.getInstance().getXpath(
				"xpathforlink", input.trim());
		if (isObjectExist(xpath, Using.XPATH, 10000)) {
			click(xpath, Using.XPATH, 10000);
			passLogInfo("clickOnLink", "clicked on the '" +linkName+ "' link");
			wait(2000);
			waitForPageToLoad();
		} else
			assertError("'" +input+ "' link is not available", true);
	}
	
	
	/**To Validate Text **/
	public void validateText(String value) {
		String methodName="validateText";		
		String[] splitData=value.split(",");
		for(int i=0; i<splitData.length; i++) {
			String xpath=Global.strContainsText.replace("@@@", splitData[i]);		
			if(isObjectExist(xpath, Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "'"+splitData[i]+"' Text exists");
			else
				warnLogInfo(methodName, "'"+splitData[i]+"' Text does not exists");		
		}	
	}
	
	/**To Validate Links**/
	public void validateLink(String value) {
		String methodName="validateLink";	
		String[] splitData=value.split(",");
		for(int i=0; i<splitData.length; i++) {
			String xpath=Global.strLinkText.replace("@@@", splitData[i]);		
			if(isObjectExist(xpath, Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "'"+splitData[i]+"' Link exists");
			else
				warnLogInfo(methodName, "'"+splitData[i]+"' Link does not exists");		
		}	
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
	
	/**To Validate control navigated to Search Results page**/
	public void navigateToResultsPage(String tabName) {
		String methodName = "navigateToResultsPage";
		if(getText("//*[@class='tab_on'] | //*[@class='subtab_on']", Using.XPATH, TIMEOUT).contains(tabName)) 
			passLogInfo(methodName, "Control navigated to '" +tabName+ "' page");	
		else
			failLogInfo(methodName, "Control did not navigate to '" +tabName+ "' page");
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
			failLogInfo(methodName, "'Clear Form' button does not exists on Form");
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
		 	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));
		   SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		   df.setTimeZone(cal.getTimeZone());
		   String s = df.format(cal.getTime());
		   return s;
	}
	
	/**Method for new Window Handle**/
	public String findNewWindowHandle(Set<String> existingHandles) {
		String foundHandle = "";
		while (foundHandle.isEmpty())  {
	        Set<String> currentHandles = getWindowHandles();
	        if (currentHandles.size() != existingHandles.size())  {
	            for (String currentHandle : currentHandles)   {
	                if (!existingHandles.contains(currentHandle))  {
	                    foundHandle = currentHandle;
	                    break;
	                }
	            }
	        }
	    }
		 return foundHandle;
	}	
	
	/**Verify Tab**/
	
	public void verifyTab(String tabElement, String tabName) {
		if(isObjectExist(tabElement, Using.XPATH, TIMEOUT)) 
			passLogInfo("verifyTab", ""+tabName+" tab is present");
		else
			assertError(""+tabName+" tab is missing", true);
	}
	
	public static Wraper getInstance() {
		if (wraper == null) {
			synchronized (Wraper.class) {
				if (wraper == null) {
					wraper = new Wraper();
				}
			}
		}
		return wraper;
	}
	public boolean waitForPageToLoad(WebElement element) {
		try {
			isVisible(element);
			return true;
		} catch (Exception e) {
			return false;
		}
		 
	}
}
