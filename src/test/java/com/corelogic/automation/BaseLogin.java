package com.corelogic.automation;

import java.util.Properties;
import com.corelogic.utaf.interfaces.IBaseLogin;
import com.corelogic.utaf.main.internal.Using;
import com.corelogic.utaf.utils.XMLUtil;

/**
 * 
 * @author Vishal Singh Raghuwanshi
 * @version 1.0
 */
public class BaseLogin extends Wraper implements IBaseLogin {

	@Override
	public void login(String username, String password) throws Exception {
		String methodName = "login";
		String currentURL=getCurrentUrl();
		passLogInfo(methodName, "Navigated to "+currentURL+"");
		waitForPageToLoad();
		if(isObjectExist("//*[@id='loginLink']", Using.XPATH, TIMEOUT)) {
			click("//*[@id='loginLink']", Using.XPATH, TIMEOUT);
			passLogInfo(methodName, "Clicked on Login");
			wait(5000);
			waitForPageToLoad();
			
			if(isObjectExist("//*[@id='txtLoginEmail']", Using.XPATH, TIMEOUT))	{
				type("//*[@id='txtLoginEmail']", Using.XPATH, username, TIMEOUT);
				passLogInfo(methodName, "Entered User Name : '"+username+"'");
				
				if(isObjectExist("//*[@id='Password']", Using.XPATH, TIMEOUT))	{
					type("//*[@id='Password']", Using.XPATH, password, TIMEOUT);	
					passLogInfo(methodName, "Entered password : '"+password+"'");
					
					if(isObjectExist("//*[@id='btnSignIn']", Using.XPATH, TIMEOUT)) {
						click("//*[@id='btnSignIn']", Using.XPATH, TIMEOUT);
						passLogInfo(methodName, "Clicked on Sign in");
						wait(5000);
						waitForPageToLoad();
					}
					else 
						assertError("Sign in button does not exists on Login Page", true);
				}
				else 
					assertError("Password text box does not exists on Login Page", true);	
			}
			else 
				assertError("UserName text box does not exists on Login Page", true);
		}
		//else 
			//assertError("Login button does not exists on Home Page", true);
		
		
		
		
		
		
//		if(isObjectExist("//*[contains(@name,'user')]", Using.XPATH, TIMEOUT))	{
//			type("//*[contains(@name,'user')]", Using.XPATH, username, TIMEOUT);
//			passLogInfo(methodName, "Entered user name : '"+username+"'");
//			if(isObjectExist("password", Using.NAME, TIMEOUT))	{
//				type("password", Using.NAME, password, TIMEOUT);	
//				passLogInfo(methodName, "Entered password name : '"+password+"'");
//				if(isObjectExist("//*[@value='Login']", Using.XPATH, TIMEOUT)) {
//					click("//*[@value='Login']", Using.XPATH, TIMEOUT);
//					passLogInfo(methodName, "Clicked on submit");
//					wait(5000);
//					waitForPageToLoad();
//				}
//				else 
//					assertError("Login button does not exists on Login Page", true);
//			}
//			else 
//				assertError("Password text box does not exists on Login Page", true);	
//		}
//		else 
//			assertError("UserName text box does not exists on Login Page", true);
	}

	@Override
	public void logout() {
		String methodName = "logout";
		
		if(isObjectExist("//*[@class='dropdown-toggle user-info-fullname']", Using.XPATH, TIMEOUT)) 
			click("//*[@class='dropdown-toggle user-info-fullname']", Using.XPATH, TIMEOUT);
		wait(1000);
		click("//*[contains(@href,'logoutForm')]", Using.XPATH, TIMEOUT);
		/*if(isObjectExist("//*[contains(@href,'logoutForm')]", Using.XPATH, TIMEOUT)) {
			click("(//*[contains(@href,'logoutForm')]", Using.XPATH, TIMEOUT);
			wait(2000);
			passLogInfo(methodName, "Logged out successfully");	
		}*/
		
//		if(isObjectExist("//*[contains(@src,'logout_icon.jpg')]", Using.XPATH, TIMEOUT)) {
//			click("//*[contains(@src,'logout_icon.jpg')]", Using.XPATH, TIMEOUT);
//			wait(2000);
//			passLogInfo(methodName, "Logged out successfully");
//		}
	}
	
	public void checkLogout() {	
		String methodName = "BaseLogin-CheckLogout";		
		logout();
	if(isObjectExist("//*[@href='/DataChecker/index.htm']", Using.XPATH, TIMEOUT)){
		click("//*[@href='/DataChecker/index.htm']", Using.XPATH, TIMEOUT);
		passLogInfo(methodName, "Clicked on Link 'Click here'");
		if (isObjectExist("//*[@value='Login']", Using.XPATH, TIMEOUT ))	{
			passLogInfo(methodName, "Successfully redirected to LoginPage");
		}else
			assertError("Control not Redirected to Login page", true);
	}
	else
		assertError("'Click Here' link does not exits", true);
	} 

	@Override
	public void setDefaultBaseState(final String user) throws Exception {
		if(null != user && !user.isEmpty()){
			Properties userProp = XMLUtil.loadUserConfig(user);
		//	Global.strUserid = userProp.getProperty("userID");
			Global.strUser=user;
			Global.strPwd = userProp.getProperty("password");	
			//login(userProp.getProperty("userID"),userProp.getProperty("password"));
		}
	}
}
