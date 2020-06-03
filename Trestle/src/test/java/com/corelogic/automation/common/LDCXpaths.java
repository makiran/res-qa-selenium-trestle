package com.corelogic.automation.common;


import com.corelogic.utaf.main.WebDriverApplication;



	public class LDCXpaths extends WebDriverApplication {
		private static LDCXpaths instance = null;

		public static LDCXpaths getInstance() {
			if (instance == null) {
				synchronized (LDCXpaths.class) {
					if (instance == null) {
						instance = new LDCXpaths();
					}
				}
			}
			return instance;
		}
		
		public String getIdForFields(String tabName, String fieldName) {
			String id = "";
			switch (tabName.toLowerCase()) {
			case "account & project":
				id = "sidebar_" + fieldName + "";
				break;
			case "create an order":
				id = "co_" + fieldName + "";
				break;
			case "doc track create an order":
				id = "" + fieldName + "";
				break;
			case "searchcriteria":
				id = "lblCriteria" + fieldName + "";
				break;
			case "orderinfo":
				id = "lbl" + fieldName + "";
				break;
			case "recordingmethod":
				id = "lblRecordingMethod" + fieldName + "";
				break;
			case "textarea":
				id = "txt" + fieldName + "";
				break;
			case "shippingaddress":
				id = "" + fieldName + "";
				break;
			case "find my order":
				id = "by" + fieldName + "";
				break;
			case "express search":
				id = "IS" + fieldName + "";
				break;
			case "co_requestcode":
				id = "co_requestcode";
				break;
			case "co_":
				id = "" + fieldName + "";
				break; 
			default:
				failLogInfo("getIdForFields", "default case eneterd");
				break;
			}
			return id;
		}

		public String getXpath(String xpathType, String dynamicValue) {
			String xpath = "";
			switch (xpathType.toLowerCase()) {
			case "xpathforbutton":
				xpath = "(//*[@class='formButton' and @value='"+dynamicValue+"'])[1]";
				break;
			case "xpathfortabbutton":
				xpath = "(//*[@type='button' and @value='"+dynamicValue+"'])[1]";
				break;
			case "xpathfortab":
				xpath = "//*[contains(@class,'tab')][contains(text(),'"+dynamicValue+"')] | //*[contains(@class,'tab')]//*[contains(text(),'"+dynamicValue+"')]";
				break;
			case "xpathfortabactive":
				xpath = "//*[contains(@class,'tab_on')][contains(text(),'"+dynamicValue+"')]";
				break;
			case "xpathfortabinactive":
				xpath = "//*[contains(@class,'tab_inactive')][contains(text(),'"+dynamicValue+"')]";
				break;
			case "xpathforsubtab":
				xpath = "//*[contains(@class,'subtab')][contains(text(),'"+dynamicValue+"')] | //*[contains(@class,'subtab')]//*[contains(text(),'"+dynamicValue+"')]";
				break;
			case "xpathforsubtabactive":
				xpath = "//*[contains(@class,'subtab_on')][contains(text(),'"+dynamicValue+"')]";
				break;
			case "xpathforsubtabinactive":
				xpath = "//*[contains(@class,'subtab_off')]/a[contains(text(),'"+dynamicValue+"')]";
				break;
			case "xpathfortextbox":
				xpath = "//input[@name='"+dynamicValue+"']";
				break;	
			case "xpathfordropdown":
				xpath = "//select[@name='"+dynamicValue+"']";
				break;
			case "xpathforradiobutton":
				xpath = "//*[@type='radio' and @value='"+dynamicValue+"']";
				break;	
			case "xpathforgraceradiobutton":
				xpath = "//*[@type='radio' and @value='"+dynamicValue+"' and @name='graceDateType']";
				break;
			case "xpathforcheckbox":
				xpath = "(//*[@type='checkbox' and @name='"+dynamicValue+"'])[1]";
				break;	
			case "xpathforlink":
				xpath = "(//a[contains(text(),'" + dynamicValue + "')])[1] | (//*[contains(@href,'" + dynamicValue + "')])[1]";
				break;
			case "xpathforicon":
				xpath = "//*[contains(@src,'/DataChecker/images/"+dynamicValue+"')]";
				break;
			case "xpathforcontainstext":
				xpath = "//*[contains(text(),'"+dynamicValue+"')]";
				break;
			default:
				failLogInfo("getXpath", "default case eneterd");
				break;
			}
			return xpath;
		}
}
