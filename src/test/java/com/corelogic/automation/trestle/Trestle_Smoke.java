package com.corelogic.automation.trestle;
import net.sf.saxon.functions.Parse;
import org.openqa.selenium.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.*;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.selenesedriver.SwitchToFrame;
import com.corelogic.automation.Global;
import com.corelogic.automation.Wraper;
import com.corelogic.automation.common.Common;
import com.corelogic.utaf.keyword.internal.selenium.GetAttribute;
import com.corelogic.utaf.main.internal.Using;
import java.util.regex.*;

public class Trestle_Smoke extends Wraper{
	private static volatile Trestle_Smoke trestlesmoke = null;
	public String stringData = "";
	public Map<String, String> data = null;

	public Trestle_Smoke setStringData(String stringData) {
		this.stringData = stringData;
		return Trestle_Smoke.getInstance();
	}

	public Trestle_Smoke setData(Map<String, String> data) {
		this.data = data;
		return Trestle_Smoke.getInstance();
	}

	public static Trestle_Smoke getInstance() {
		if (trestlesmoke == null) {
			synchronized (Trestle_Smoke.class) {
				if (trestlesmoke == null) {
					trestlesmoke = new Trestle_Smoke();
				}
			}
		}
		return trestlesmoke;
	}


	public void verifyServers() {
		String methodName = "verifyServers";
		String version;
		wait(10000);
		if(isObjectExist("//*[@class='footer-image']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Corelogic Footer is present");
			version=findElement(By.xpath("//*[@class='footer-image']")).getAttribute("title").toString();
			//if(version.contains(data.get("Version")))
			passLogInfo(methodName, "The current verion "+version+" is displayed on Corelogic footer");
			//else
			//assertError("Incorrect version "+version+" is displayed on Corelogic footer ", true);
		}
		else
			assertError("Corelogic Footer is not present", true);
	}


	public void verifyMLO() {
		//String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
		//findElement(By.linkText("https://www.google.co.in")).sendKeys(selectLinkOpeninNewTab);
		verifyMLOconneciton();
		verifyDataFeed();
		verifyMLOAccount();
	}


	public void verifyMLOconneciton() {
		String methodName = "verifyMLOconneciton";
		if(isObjectExist("//*[@class='active' and @href='/MLO/Connections']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Connections module is displayed");
		else
			assertError("Connections module is not displayed", true);
		if(isObjectExist("(//*[@class='sorting'])[5]", Using.XPATH, TIMEOUT)){
			click("(//*[@class='sorting'])[5]", Using.XPATH, TIMEOUT);
			wait(1000);
			click("//*[@class='sorting_asc']", Using.XPATH, TIMEOUT);
			passLogInfo(methodName, "Clicked on Contract Status sorting twice");
		}
		else
			failLogInfo(methodName, "Unable to find column sorting");
		if(isObjectExist("(//*[@title='Approved'])[1]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Approved connection available");
			if(isObjectExist("(//*[@class='btn-link tb-link'])[1]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Details link available for the approved connection");
				click("(//*[@class='btn-link tb-link'])[1]", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='tabConnectionDetails']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is redirected to Connection Details page");
					if(isObjectExist("//*[@id='tabConnectionDetails' and @class='active']", Using.XPATH, TIMEOUT))
						passLogInfo(methodName, "User is navigated to Connection Details tab");
					else
						failLogInfo(methodName, "User is not navigated to Connection Details tab by default");
					if(isObjectExist("//*[@id='tabProductDetails']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Product Details tab is available");
						click("//*[@id='tabProductDetails']", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabProductDetails' and @class='active']", Using.XPATH, TIMEOUT)) 
							passLogInfo(methodName, "User is navigated to Product Details tab");
						else
							failLogInfo(methodName, "User is not navigated to Product Details tab");
					}
					if(isObjectExist("//*[@id='tabFeeDetails']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Fees tab is available");
						click("//*[@id='tabFeeDetails']", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabFeeDetails' and @class='active']", Using.XPATH, TIMEOUT)) 
							passLogInfo(methodName, "User is navigated to Fees tab");
						else
							failLogInfo(methodName, "User is not navigated to Fees tab");
					}
					if(isObjectExist("//*[@id='tabContracts']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Fees tab is available");
						click("//*[@id='tabContracts']", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabContracts' and @class='active']", Using.XPATH, TIMEOUT)) 
							passLogInfo(methodName, "User is navigated to Contracts tab");
						else
							failLogInfo(methodName, "User is not navigated to Contracts tab");
					}						
				}
				else
					assertError("User is not redirected to Connection Details page", true);			
			}
			else
				failLogInfo(methodName, "Details link is not available for the approved connection");		
		}
		else
			failLogInfo(methodName, "No approved connection available");
	}


	public void verifyDataFeed() {
		String methodName = "verifyDataFeed";
		if(isObjectExist("//*[@href='/MLO/DataFeeds']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Data module is present");
			click("//*[@href='/MLO/DataFeeds']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@class='active' and @href='/MLO/DataFeeds']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Data module is displayed");
				if(isObjectExist("(//*[@id='btnEditFeed'])[1]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Edit button is available for data feed");
					click("(//*[@id='btnEditFeed'])[1]", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[@id='tabfeedDetails' and @class='active']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Data Feed details page is displayed");
						click("//*[@id='IsActiveYes']", Using.XPATH, TIMEOUT);
						click("//*[@id='btnSaveOptions']", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@class='row text-center']", Using.XPATH, TIMEOUT))
							passLogInfo(methodName, "Clicked on Save button");
						else
							failLogInfo(methodName, "Save button not working");
						if(isObjectExist("//*[@id='IsActiveYes' and @checked='checked']", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "Data Feed is enabled");
							click("//*[@id='IsActiveNo']", Using.XPATH, TIMEOUT);
							click("//*[@id='btnSaveOptions']", Using.XPATH, TIMEOUT);
							if(isObjectExist("//*[@id='IsActiveNo' and @checked='checked']", Using.XPATH, TIMEOUT))
								passLogInfo(methodName, "Data Feed is disabled");
							else
								failLogInfo(methodName, "Unable to disable data feed");
						}	
						else
							failLogInfo(methodName, "Unable to enable data feed");	
					}
					else
						failLogInfo(methodName, "Details tab is not displayed");
					if(isObjectExist("//*[@id='tabfeedFees']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Fees tab is available");
						click("//*[@id='tabfeedFees']", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabfeedFees' and @class='active']", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "Navigated to Fees tab");
							if(isObjectExist("//*[@id='MLSFees_0__SetupFee']", Using.XPATH, TIMEOUT)){
								findElement(By.xpath("//*[@id='MLSFees_0__SetupFee']")).clear();
								findElement(By.xpath("//*[@id='MLSFees_0__SetupFee']")).sendKeys("200");

								if(isObjectExist("//*[@id='btnSaveOptions']", Using.XPATH, TIMEOUT)) {


									WebElement element = getDriver().findElement(By.xpath("//*[@id='btnSaveOptions']"));
									mouseMove(element);

									Common.waitForElementToBeClickable(element, 10);
									click(element);


									//click("//*[@id='btnSaveOptions']",Using.XPATH, TIMEOUT);
								}
								else
									failLogInfo(methodName, "Save button not available");
								if(isObjectExist("//*[@class='row text-center']", Using.XPATH, TIMEOUT)) {
									passLogInfo(methodName, "Clicked on Save button");
									if(findElement(By.xpath("//*[@id='MLSFees_SetupFee']")).getAttribute("value").toString().equals("200.00"))
										passLogInfo(methodName, "Setup Fee value changed to 200");							
									else
										failLogInfo(methodName, "Unable to change Setup Fee value");
								}
								else
									failLogInfo(methodName, "Save button not working");				
							}	
							else
								failLogInfo(methodName, "Setup Fee field is missing");
						}
						else
							failLogInfo(methodName, "Unable to navigate to Fees tab");						
					}
					else
						failLogInfo(methodName, "Fees tab is not available");
					/*if(isObjectExist("//*[@id='tabfeedCustomize']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Customize Data tab is available");
					click("//*[@id='tabfeedCustomize']", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[@id='tabfeedCustomize' and @class='active']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Navigated to Customize Data tab");
						if(isObjectExist("(//*[contains(@href,'Property')])[2]", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "Edit Field button is present for Property");
							click("(//*[contains(@href,'Property')])[2]",Using.XPATH, TIMEOUT);
							if(isObjectExist("//*[@id='tblAvailableFields']", Using.XPATH, TIMEOUT)) {
								passLogInfo(methodName, "Clicked on Edit Field button");
								click("//*[@name='ArchitecturalStyle' and @checked='checked']", Using.XPATH, TIMEOUT);
								click("//*[@name='AssociationAmenities' and @checked='checked']", Using.XPATH, TIMEOUT);
								click("//*[@value='Save']", Using.XPATH, TIMEOUT);
								if(isObjectExist("//*[contains(text(),'Changes Saved')]", Using.XPATH, TIMEOUT)) {
									passLogInfo(methodName, "Clicked on Save button");
									click("(//*[contains(@href,'Property')])[2]",Using.XPATH, TIMEOUT);
									if(!isObjectExist("//*[@name='ArchitecturalStyle' and @	checked='checked']", Using.XPATH, TIMEOUT)) {
										passLogInfo(methodName, "Changes to property fields is saved");
										click("//*[@name='ArchitecturalStyle']", Using.XPATH, TIMEOUT);
										click("//*[@name='AssociationAmenities']", Using.XPATH, TIMEOUT);
										click("//*[@value='Save']", Using.XPATH, TIMEOUT);
										click("(//*[contains(@href,'Property')])[2]",Using.XPATH, TIMEOUT);
										if(isObjectExist("//*[@name='ArchitecturalStyle' and @	checked='checked']", Using.XPATH, TIMEOUT))
											passLogInfo(methodName, "Changes to property fields is reverted back");	
										else
											failLogInfo(methodName, "Unable to revert back the changes to Property fields");	
									}
									else
										failLogInfo(methodName, "Changes to property fields is not saved");	
								}
								else
									failLogInfo(methodName, "Save button not working");	
							}
							else
								failLogInfo(methodName, "Edit Field button not working");														
						}
						else
							failLogInfo(methodName, "Edit Field button is not present for Property");								
					}
					else
						failLogInfo(methodName, "Unable to navigate to Customize Data tab");	
				}
				else
					failLogInfo(methodName, "Customize Data tab is not available");	*/
				}
				else
					failLogInfo(methodName, "Edit button is not available for data feed");
			}		
			else
				assertError("Data module is not displayed", true);
		}	
		else
			assertError("Data module is not available", true);	
	}


	public void verifyMLOAccount() {
		String methodName = "verifyMLOAccount";
		if(isObjectExist("//*[@class='dropdown-toggle ' and contains(text(),'Account')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Account module is available");
			click("//*[@class='dropdown-toggle ' and contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@title='Manage']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Contact option is available under Account module");
				click("//*[@title='Manage']",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Primary Contact Information')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Primary Contact Information page");
				else
					failLogInfo(methodName, "Unable to navigate to Primary Contact Information page");	
			}
			else
				failLogInfo(methodName, "Contact option is not available under Account module");	
			click("//*[@class='dropdown-toggle active' and contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("(//*[contains(@href,'EditComp')])[1]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Company option is available under Account module");
				click("(//*[contains(@href,'EditComp')])[1]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Company Information')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Successfully navigated to Company Information page");
					if(isObjectExist("//*[@href='#description']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Company description tab is available");
						click("//*[@href='#description']",Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabDescription' and @class='active']", Using.XPATH, TIMEOUT))
							passLogInfo(methodName, "Successfully navigated to Company Description page");
						else
							failLogInfo(methodName, "Unable to navigate to Company Description page");	
					}
					else
						failLogInfo(methodName, "Company description tab is not available");
					if(isObjectExist("//*[@href='#theLogo']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Logo tab is available");	
						click("//*[@href='#theLogo']",Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabCompanyLogo' and @class='active']", Using.XPATH, TIMEOUT))
							passLogInfo(methodName, "Successfully navigated to My Company Logo page");
						else
							failLogInfo(methodName, "Unable to navigate to My Company Logo page");	
					}
					else
						failLogInfo(methodName, "Logo tab is not available");
				}
				else
					failLogInfo(methodName, "Unable to navigate to Company Information page");	
			}
			else
				failLogInfo(methodName, "Company option is not available under Account module");	
			click("//*[@class='dropdown-toggle active' and contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@title='Reset Password']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Password option is available under Account module");	
				click("//*[@title='Reset Password']",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Reset password')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Reset password page");
				else
					failLogInfo(methodName, "Unable to navigate to Reset password page");	
			}
			else
				failLogInfo(methodName, "Password option is not available under Account module");
			click("//*[@class='dropdown-toggle active' and contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'DepositInfo')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Bank Deposit option is available under Account module");	
				click("//*[contains(@href,'DepositInfo')]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Bank Deposit')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Bank Deposit page");
				else
					failLogInfo(methodName, "Unable to navigate to Bank Deposit page");	
			}
			else
				failLogInfo(methodName, "Bank Deposit option is not available under Account module");
			click("//*[@class='dropdown-toggle active' and contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'PaymentInfo')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Payment Information option is available under Account module");	
				click("//*[contains(@href,'PaymentInfo')]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Payment Information')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Payment Information page");
				else
					failLogInfo(methodName, "Unable to navigate to Payment Information page");	
			}
			else
				failLogInfo(methodName, "Payment Information option is not available under Account module");
			click("//*[@class='dropdown-toggle active' and contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'ViewTrestleAgreement')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Subscription option is available under Account module");	
				click("//*[contains(@href,'ViewTrestleAgreement')]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Trestle Subscriber Agreement')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Trestle Subscriber Agreement page and pdf file is displayed");
				else
					failLogInfo(methodName, "Unable to navigate to Trestle Subscriber Agreement page");	
			}
			else
				failLogInfo(methodName, "Subscription option is not available under Account module");
			click("//*[@class='dropdown-toggle active' and contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'/MLO/BillingReport')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Billing option is available under Account module");	
				click("//*[contains(@href,'/MLO/BillingReport')]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),' Monthly Receipts ')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Billiing Report page");
				else
					failLogInfo(methodName, "Unable to navigate to Billing Report page");	
			}
			else
				failLogInfo(methodName, "Billing option is not available under Account module");
		}
		else
			assertError("Account module is not available", true);	
		if(isObjectExist("//*[@class='dropdown-toggle' and contains(text(),'Help')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Help module is available");
			click("//*[@class='dropdown-toggle' and contains(text(),'Help')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'Multiple Listing Managers Release')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Multiple Listing Organizations option is available under Help module");	
				/*click("//*[contains(@href,'Multiple Listing Managers Release')]",Using.XPATH, TIMEOUT);

			wait(5000);
			selectWindow(1);

			if(isObjectExist("//*[@id='viewer']", Using.XPATH, TIMEOUT))
				passLogInfo(methodName, "MLO support pdf file is displayed");
			else
				failLogInfo(methodName, "MLO support pdf file is not displayed");
			wait(1000);
			selectWindow(0);*/
			}
			else
				failLogInfo(methodName, "Multiple Listing Organizations option is not available under Help module");

		}
		else
			assertError("Help module is not available", true);	
	}



	public void verifyTP(){
		verifyTPconnection();
		addProduct();
		deleteProduct();
		verifyTPAccount();
	}


	public void verifyTPconnection(){
		String methodName = "verifyTPconnection";
		if(isObjectExist("//*[@class='active' and @href='/TP/Connections']", Using.XPATH, TIMEOUT))
			passLogInfo(methodName, "Connections module is displayed");
		else
			assertError("Connections module is not displayed", true);
		if(isObjectExist("(//*[@class='sorting'])[5]", Using.XPATH, TIMEOUT)){
			click("(//*[@class='sorting'])[5]", Using.XPATH, TIMEOUT);
			wait(1000);
			click("(//*[@class='sorting_asc'])[1]", Using.XPATH, TIMEOUT);
			passLogInfo(methodName, "Clicked on Contract Staus sorting twice");
		}
		else
			failLogInfo(methodName, "Unable to find column sorting");
		if(isObjectExist("(//*[@title='Approved'])[1]", Using.XPATH, TIMEOUT)){
			passLogInfo(methodName, "Approved connection available");
			if(isObjectExist("(//*[contains(@href,'MLODetails')])[1]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Details link available for the approved connection");
				click("(//*[contains(@href,'MLODetails')])[1]", Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[@id='tabsMLSDetail']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "User is redirected to Connection Details page");
					if(isObjectExist("//*[@class='active']/a[@href='#detail']", Using.XPATH, TIMEOUT)) 
						passLogInfo(methodName, "User is navigated to Connection Details tab");
					else
						failLogInfo(methodName, "User is not navigated to Connection Details tab by default");
					if(isObjectExist("//*[@href='#contracts']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Fees tab is available");
						click("//*[@href='#contracts']", Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@class='active']/a[@href='#contracts']", Using.XPATH, TIMEOUT)) 
							passLogInfo(methodName, "User is navigated to Contracts tab");
						else
							failLogInfo(methodName, "User is not navigated to Contracts tab");
					}						
				}
				else
					assertError("User is not redirected to Connection Details page", true);			
			}
			else
				failLogInfo(methodName, "Details link is not available for the approved connection");		
		}
		else
			failLogInfo(methodName, "No approved connection available");
	}


	public void addProduct() {
		String methodName = "addProduct";
		String currentURL=null;
		if(isObjectExist("//*[@href='/TP/Products']", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Products tab is available");
			click("//*[@href='/TP/Products']", Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@class='active' and @href='/TP/Products']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Navigated to Products tab");
				if(isObjectExist("//*[@href='/TP/Products/Create']", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Add button is available");
					click("//*[@href='/TP/Products/Create']", Using.XPATH, TIMEOUT);
					if(isObjectExist("//*[contains(text(),'Add/Edit Product')]", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Navigated to Add/Edit Product form");
						if(isObjectExist("//*[@id='Name']", Using.XPATH, TIMEOUT)) {
							passLogInfo(methodName, "Product Name text field is available");
							findElement(By.xpath("//*[@id='Name']")).sendKeys(data.get("ProductName"));
							if(isObjectExist("//*[@id='Description']", Using.XPATH, TIMEOUT)) {
								passLogInfo(methodName, "Product Description text field is available");
								findElement(By.xpath("//*[@id='Description']")).sendKeys(data.get("ProductDesc"));
								if(isObjectExist("//*[@id='ProductURL']", Using.XPATH, TIMEOUT)) {
									passLogInfo(methodName, "Product URL field is available");
									findElement(By.xpath("//*[@id='ProductURL']")).sendKeys(data.get("ProductURL"));


									//if(isObjectExist("//*[@id='btnSaveBeforeEdit']", Using.XPATH, TIMEOUT)) {
									//passLogInfo(methodName, "Data Feed Add button is available");
									click("//*[@value='Save']", Using.XPATH, TIMEOUT);			
									findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("ProductName"));
									if(isObjectExist("//*[contains(text(),'@@@')]".replace("@@@", data.get("ProductName")), Using.XPATH, TIMEOUT))
										passLogInfo(methodName, "New product "+data.get("ProductName")+" is added");
									findElement(By.xpath("//*[contains(text(),'Edit')]")).click();
									passLogInfo(methodName, "Clicked on Edit button for the new Product");
									currentURL=getCurrentUrl();
									currentURL=currentURL.replaceAll("Edit/", "EditCredentials?pid=");
									getDriver().navigate().to(currentURL);


									/*	WebElement element = getDriver().findElement(By.xpath("//*[@id='btnSaveBeforeEdit']"));
								mouseMove(element);

                           Common.waitForElementToBeClickable(element, 10);
                           click(element);	*/

									if(isObjectExist("//*[@id='ddlDataFeeds']", Using.XPATH, TIMEOUT)) {
										passLogInfo(methodName, "Data Feed dropdown is available");
										select("//*[@id='ddlDataFeeds']", Using.XPATH, "IDX - RETS", TIMEOUT);
										//findElement(By.xpath("//*[@id='DataFeedCredential_UserName']")).sendKeys(data.get("ClientID"));
										//findElement(By.xpath("//*[@id='password']")).sendKeys(data.get("ClientSecret"));
										click("//*[@value='Save']", Using.XPATH, TIMEOUT);
										wait(3000);
										getDriver().switchTo().activeElement();
										if(isObjectExist("//*[@class='modal-backdrop fade in']", Using.XPATH, TIMEOUT))
											passLogInfo(methodName, "New Password pop up message is displayed");
										else
											failLogInfo(methodName, "New Password pop up message is not displayed");	
										if(isObjectExist("//*[@id='mdlBtnYes']", Using.XPATH, TIMEOUT))
											passLogInfo(methodName, "Yes button is displayed");
										else
											failLogInfo(methodName, "Yes is not displayed");
										click("//*[@id='mdlBtnYes']", Using.XPATH, TIMEOUT);
										passLogInfo(methodName, "Cicked on Yes button");
										wait(3000);
										getDriver().switchTo().activeElement();
										if(isObjectExist("//*[@id='passwordDisplay']", Using.XPATH, TIMEOUT))
											passLogInfo(methodName, "Feed Password is displayed");
										else
											failLogInfo(methodName, "Feed Password is not displayed");
										click("//*[@id='passwordDisplayClose']", Using.XPATH, TIMEOUT);
										passLogInfo(methodName, "Cicked on Close button");
										wait(3000);
										getDriver().switchTo().defaultContent();
										if(isObjectExist("//*[contains(text(),'IDX - RETS')]", Using.XPATH, TIMEOUT)) {
											passLogInfo(methodName, "Data Feed is added to the product");
											click("//*[@value='Save']", Using.XPATH, TIMEOUT);
											//findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("ProductName"));
											if(isObjectExist("//*[contains(text(),'@@@')]".replace("@@@", data.get("ProductName")), Using.XPATH, TIMEOUT))
												passLogInfo(methodName, "New product "+data.get("ProductName")+" is added");
										}
									}
									else
										assertError("Data Feed dropdown is not available", true);
									//}
									//else
									//assertError("Data Feed Add button is not available", true);
								}
								else
									assertError("Product URL field is not available", true);
							}
							else
								assertError("Product Description text field is not available", true);
						}
						else
							assertError("Product Name text field is not available", true);					
					}	
					else
						assertError("Add button is not working", true);
				}
				else
					assertError("Add button is not available", true);
			}	
			else
				assertError("Unable to navigate to Products tab", true);
		}
		else
			assertError("Products tab is not available", true);

	}


	public void deleteProduct() {
		String methodName = "deleteProduct";
		if(isObjectExist("//*[contains(text(),'@@@')]".replace("@@@", data.get("ProductName")), Using.XPATH, TIMEOUT)) {
			findElement(By.xpath("//*[contains(text(),'Edit')]")).click();
			passLogInfo(methodName, "Clicked on Edit button for the new Product");
			if(isObjectExist("//*[contains(text(),'Delete')]", Using.XPATH, TIMEOUT)) {
				findElement(By.xpath("//*[contains(text(),'Delete')]")).click();
				if(isObjectExist("//*[@type='submit']", Using.XPATH, TIMEOUT))
					findElement(By.xpath("//*[@type='submit']")).click();
				//findElement(By.xpath("//*[@type='search']")).sendKeys(data.get("ProductName"));
				if(!isObjectExist("//*[contains(text(),'@@@')]".replace("@@@", data.get("ProductName")), Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "The new Product is successfully deleted");		
			}
			else
				assertError("Delete button is missing", true);
		}



	}


	public void verifyTPAccount() {
		String methodName = "verifyTPAccount";
		if(isObjectExist("//*[contains(text(),'Account')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Account module is available");
			click("//*[contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@title='Manage']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Contact option is available under Account module");
				click("//*[@title='Manage']",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Primary Contact Information')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Primary Contact Information page");
				else
					failLogInfo(methodName, "Unable to navigate to Primary Contact Information page");	
			}
			else
				failLogInfo(methodName, "Contact option is not available under Account module");	
			click("//*[contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("(//*[contains(@href,'EditComp')])[1]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Company option is available under Account module");
				click("(//*[contains(@href,'EditComp')])[1]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Company Information')]", Using.XPATH, TIMEOUT)) {
					passLogInfo(methodName, "Successfully navigated to Company Information page");
					if(isObjectExist("//*[@href='#description']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Company description tab is available");
						click("//*[@href='#description']",Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabDescription' and @class='active']", Using.XPATH, TIMEOUT))
							passLogInfo(methodName, "Successfully navigated to Company Description page");
						else
							failLogInfo(methodName, "Unable to navigate to Company Description page");	
					}
					else
						failLogInfo(methodName, "Company description tab is not available");
					if(isObjectExist("//*[@href='#theLogo']", Using.XPATH, TIMEOUT)) {
						passLogInfo(methodName, "Logo tab is available");	
						click("//*[@href='#theLogo']",Using.XPATH, TIMEOUT);
						if(isObjectExist("//*[@id='tabCompanyLogo' and @class='active']", Using.XPATH, TIMEOUT))
							passLogInfo(methodName, "Successfully navigated to My Company Logo page");
						else
							failLogInfo(methodName, "Unable to navigate to My Company Logo page");	
					}
					else
						failLogInfo(methodName, "Logo tab is not available");
				}
				else
					failLogInfo(methodName, "Unable to navigate to Company Information page");	
			}
			else
				failLogInfo(methodName, "Company option is not available under Account module");		
			click("//*[contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[@title='Reset Password']", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Password option is available under Account module");	
				click("//*[@title='Reset Password']",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Reset password')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Reset password page");
				else
					failLogInfo(methodName, "Unable to navigate to Reset password page");	
			}
			else
				failLogInfo(methodName, "Password option is not available under Account module");
			click("//*[contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'PaymentInfo')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Payment Information option is available under Account module");	
				click("//*[contains(@href,'PaymentInfo')]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Payment Information')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Payment Information page");
				else
					failLogInfo(methodName, "Unable to navigate to Payment Information page");	
			}
			else
				failLogInfo(methodName, "Payment Information option is not available under Account module");
			click("//*[contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'ViewTrestleAgreement')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Subscription option is available under Account module");	
				click("//*[contains(@href,'ViewTrestleAgreement')]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),'Trestle Subscriber Agreement')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Trestle Subscriber Agreement page and pdf file is displayed");
				else
					failLogInfo(methodName, "Unable to navigate to Trestle Subscriber Agreement page");	
			}
			else
				failLogInfo(methodName, "Subscription option is not available under Account module");
			click("//*[contains(text(),'Account')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'/TP/BillingReport')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Billing option is available under Account module");	
				click("//*[contains(@href,'/TP/BillingReport')]",Using.XPATH, TIMEOUT);
				if(isObjectExist("//*[contains(text(),' Monthly Receipts ')]", Using.XPATH, TIMEOUT))
					passLogInfo(methodName, "Successfully navigated to Billing Report page");
				else
					failLogInfo(methodName, "Unable to navigate to Billing Report page");	
			}
			else
				failLogInfo(methodName, "Billing option is not available under Account module");
		}
		else
			assertError("Account module is not available", true);	
		if(isObjectExist("//*[@class='dropdown-toggle' and contains(text(),'Help')]", Using.XPATH, TIMEOUT)) {
			passLogInfo(methodName, "Help module is available");
			click("//*[@class='dropdown-toggle' and contains(text(),'Help')]",Using.XPATH, TIMEOUT);
			if(isObjectExist("//*[contains(@href,'/Content/Trestle for Technology Providers.pdf')]", Using.XPATH, TIMEOUT)) {
				passLogInfo(methodName, "Technology Providers option is available under Help module");	
				//click("//*[contains(@href,'Technology Providers Release')]",Using.XPATH, TIMEOUT);
			}

			else
				failLogInfo(methodName, "Technology Providers option is not available under Help module");

		}
		else
			assertError("Help module is not available", true);	

	}


	public void selectWindow(int index) {
		WebDriver driver= getDriver();
		Set<String> ids=driver.getWindowHandles();
		Iterator<String> it=ids.iterator();
		String parentID=it.next();
		String childID=it.next();
		switch (index) {
		case 0 : driver.switchTo().window(parentID);
		break;
		case 1 : driver.switchTo().window(childID);
		break;
		}
		//System.out.println(driver.getTitle());

		//driver.switchTo().window(childID);
		//System.out.println(driver.getTitle());
		/* try {
           Set<String> WindowHandles;
           WebDriver driver = getDriver();
           WindowHandles = driver.getWindowHandles();
           String[] array = WindowHandles.toArray(new String[0]);
           window(array[index]);
    } catch (Exception e) {
           // e.printStackTrace();
           assertError(e.getMessage(), true);
    }*/
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
