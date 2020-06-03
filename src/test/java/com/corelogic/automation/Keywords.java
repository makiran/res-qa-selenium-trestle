package com.corelogic.automation;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import com.corelogic.automation.common.Common;
import com.corelogic.automation.trestle.*;
import com.corelogic.utaf.interfaces.Command;
import com.corelogic.utaf.main.internal.Using;
import com.corelogic.utaf.utils.XMLUtil;


/*
 * Intend of this class to provide user(BP team a flexibility to add new
 * keyword).
 * 
 * @author Vishal Singh Raghuwanshi
 * @version 1.0
 */
public class Keywords extends Wraper implements Command {

	// ~ Methods
	// --------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 * 
	 * @param row
	 *            DOCUMENT ME!
	 * @param data
	 *            DOCUMENT ME!
	 */

	public static String parentWindowHandle = "";
	public static String childWindowHandle = "";
	public static Set<String> existingWindowHandles;
	public static List<String> predefinedNotValidNotes = new ArrayList<String>();	
	Properties userMLO = XMLUtil.loadUserConfig("ProdMlo");
	Properties userTP = XMLUtil.loadUserConfig("ProdTp");
	Properties userStageMLO = XMLUtil.loadUserConfig("StageMlo");
	Properties userStageTP = XMLUtil.loadUserConfig("StageTp");
	Properties userStageAdmin = XMLUtil.loadUserConfig("StageAdmin");
	Properties userProdAdmin = XMLUtil.loadUserConfig("ProdAdmin");
	
	BaseLogin baseLogin= new BaseLogin();


	@Override
	public boolean execute(Map<String, String> data) throws Exception {

		Properties user = XMLUtil.loadUserConfig(Global.strUser);

		/**Login and Logout of the Application**/
		
		if("checkLogin".equalsIgnoreCase(data.get("keyword")) ) {	
			if(isObjectExist(Global.strLogout, Using.XPATH, 5000)) {
				baseLogin.checkLogout();
			}
		}
		if("login".equalsIgnoreCase(data.get("keyword"))) {	
			baseLogin.login(user.getProperty("userID").trim(), user.getProperty("password").trim());	
		}
		//if("logout".equalsIgnoreCase(data.get("keyword")) && data.containsKey("Logout")) {
		//	baseLogin.logout();
		//}
		if ("checkLogout".equalsIgnoreCase(data.get("keyword"))) {
			baseLogin.checkLogout();
		}

		/** To click on icon**/

		if ("clickOnIcon".equalsIgnoreCase(data.get("keyword")) && data.containsKey("iconName")) {
			clickOnIcon(data.get("iconName"));
		}

		/**Field Validations on Application**/

		if ("verifyForm".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifyTabFields")) {
			verifyForm(data.get("verifyTabFields"), data.get("subTabName"));
		}
		if ("verifyIcon".equalsIgnoreCase(data.get("keyword")) && data.containsKey("icon")) {
			verifyForm(data.get("icon"), "LDC #2 application");
		}
		if ("verifyTextBox".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifyTextbox")) {
			verifyForm(data.get("verifyTextbox"), data.get("subTabName"));
		}
		if ("verifyButton".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifyButton")) {
			verifyForm(data.get("verifyButton"), data.get("subTabName"));
		}
		if ("verifyDropdown".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifyDropdown")) {
			verifyForm(data.get("verifyDropdown"), data.get("subTabName"));
			if(Global.strUserid.contains("CARETS")) {
				verifyForm(data.get("caretsMLSDropdown"), data.get("subTabName"));
			}
		}
		if ("verifyCheckbox".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifyCheckbox")) {
			verifyForm(data.get("verifyCheckbox"), data.get("subTabName"));
		}
		if ("verifyRadiobutton".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifyRadiobutton")) {
			verifyForm(data.get("verifyRadiobutton"), data.get("subTabName"));
		}
		if ("verifyTab".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifyTab")) {
			verifyForm(data.get("verifyTab"), data.get("subTabName"));
		}
		if ("verifyTabActive".equalsIgnoreCase(data.get("keyword")) && data.containsKey("tabactive")) {
			tabActive(data.get("tabactive"), data.get("subTabName"));
		}
		if ("verifyTabInactive".equalsIgnoreCase(data.get("keyword")) && data.containsKey("tabinactive")) {
			tabInActive(data.get("tabinactive"), data.get("subTabName"));
		}
		if ("verifySubTab".equalsIgnoreCase(data.get("keyword")) && data.containsKey("verifySubtab")) {
			verifyForm(data.get("verifySubtab"), data.get("subTabName"));
		}
		if ("verifySubTabActive".equalsIgnoreCase(data.get("keyword")) && data.containsKey("subtabactive")) {
			subTabActive(data.get("subtabactive"), data.get("subTabName"));
		}
		if ("verifySubTabInactive".equalsIgnoreCase(data.get("keyword")) && data.containsKey("subtabinactive")) {
			subTabInActive(data.get("subtabinactive"), data.get("subTabName"));
		}
		if ("verifyText".equalsIgnoreCase(data.get("keyword")) && data.containsKey("validateText")) {
			verifyForm(data.get("validateText"), data.get("subTabName"));
		}

		/**Actions on the Application**/

		if ("typeData".equalsIgnoreCase(data.get("keyword")) && data.containsKey("typeTextValue")) {
			typeData(data.get("typeTextValue"));
		}
		if ("clickOnButton".equalsIgnoreCase(data.get("keyword")) && data.containsKey("buttonValue")) {
			clickOnButton(data.get("buttonValue"), data.get("buttonName"));
		}
		if ("clickOnTab".equalsIgnoreCase(data.get("keyword")) && data.containsKey("tabName")) {
			clickOnTab(data.get("tabName"));
		}
		if ("clickOnSubTab".equalsIgnoreCase(data.get("keyword")) && data.containsKey("subTabName")) {
			clickOnTab(data.get("subTabName"));
		}
		if ("selectFromDropdown".equalsIgnoreCase(data.get("keyword")) && data.containsKey("dropdown")) {
			selectFromDropdown(data.get("dropdown"));
		}	
		
		if ("clearForm".equalsIgnoreCase(data.get("keyword")) && data.containsKey("ClearForm")) {
			clearForm();
		}

		/**Scenario**/

		
		/** Trestle Prod Smoke Test **/
		
		if ("loginAsMLO".equalsIgnoreCase(data.get("keyword"))) {
			Global.strUserid = userMLO.getProperty("userID").trim();
			baseLogin.login(userMLO.getProperty("userID").trim(), userMLO.getProperty("password").trim());	
		}
		
		if ("loginAsTP".equalsIgnoreCase(data.get("keyword"))) {
			Global.strUserid = userTP.getProperty("userID").trim();
			baseLogin.login(userTP.getProperty("userID").trim(), userTP.getProperty("password").trim());	
		}
		
		if ("verifyMLO".equalsIgnoreCase(data.get("keyword")))	{
			Trestle_Smoke.getInstance().setData(data).setStringData(data.get("keyword")).verifyMLO();
		}
		if ("verifyTP".equalsIgnoreCase(data.get("keyword")))	{
			Trestle_Smoke.getInstance().setData(data).setStringData(data.get("keyword")).verifyTP();
		}
		if("logout".equalsIgnoreCase(data.get("keyword"))){
					baseLogin.logout();
			}
		if ("loginAsStageMLO".equalsIgnoreCase(data.get("keyword"))) {
			Global.strUserid = userStageMLO.getProperty("userID").trim();
			baseLogin.login(userStageMLO.getProperty("userID").trim(), userStageMLO.getProperty("password").trim());	
		}
		if ("loginAsStageTP".equalsIgnoreCase(data.get("keyword"))) {
			Global.strUserid = userStageTP.getProperty("userID").trim();
			baseLogin.login(userStageTP.getProperty("userID").trim(), userStageTP.getProperty("password").trim());	
		}
		if ("loginAsStageAdmin".equalsIgnoreCase(data.get("keyword"))) {
			Global.strUserid = userStageAdmin.getProperty("userID").trim();
			baseLogin.login(userStageAdmin.getProperty("userID").trim(), userStageAdmin.getProperty("password").trim());	
		}
		if ("loginAsProdAdmin".equalsIgnoreCase(data.get("keyword"))) {
			Global.strUserid = userProdAdmin.getProperty("userID").trim();
			baseLogin.login(userStageAdmin.getProperty("userID").trim(), userStageAdmin.getProperty("password").trim());	
		}
		if ("verifyServer".equalsIgnoreCase(data.get("keyword")))	{
			Trestle_Smoke.getInstance().setData(data).setStringData(data.get("keyword")).verifyServers();
		}
		if ("verifyLandingPage".equalsIgnoreCase(data.get("keyword")))	{
			LandingPage.getInstance().setData(data).setStringData(data.get("keyword")).VerifyLandingPage();
		}
		if ("verifyMloSubscription".equalsIgnoreCase(data.get("keyword")))	{
			MloSubscription.getInstance().setData(data).setStringData(data.get("keyword")).subscriptionMLO();
		}
		if ("verifyMloActivation".equalsIgnoreCase(data.get("keyword")))	{
			MloActivation.getInstance().setData(data).setStringData(data.get("keyword")).activationMLO();
		}
		if ("verifyMloAcceptAgreement".equalsIgnoreCase(data.get("keyword")))	{
			MloSubscriptionAgreement.getInstance().setData(data).setStringData(data.get("keyword")).acceptAgreement();
		}
		if ("verifyMloNotifications".equalsIgnoreCase(data.get("keyword")))	{
			MloNotifications.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloNotifications();
		}
		if ("verifyMloConnectManager".equalsIgnoreCase(data.get("keyword")))	{
			MloConnectionManager.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloConnectionManager();
		}
		if ("verifyMloDataFeed".equalsIgnoreCase(data.get("keyword")))	{
			MloDataFeeds.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloDataFeed();
		}
		if ("verifyMloContactPage".equalsIgnoreCase(data.get("keyword")))	{
			MloContactPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloContactPage();
		}
		if ("verifyMloCompanyPage".equalsIgnoreCase(data.get("keyword")))	{
			MloCompanyPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloCompanyPage();
		}
		if ("verifyMloPasswordPage".equalsIgnoreCase(data.get("keyword")))	{
			MloPasswordPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloPasswordPage();
		}
		if ("verifyMloLegalEntityPage".equalsIgnoreCase(data.get("keyword")))	{
			MloLegalEntityPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloLegalEntityPage();
		}
		if ("verifyMloUserManager".equalsIgnoreCase(data.get("keyword")))	{
			MloUserManager.getInstance().setData(data).setStringData(data.get("keyword")).verifyMloUserManager();
		}
		if ("verifyTpSubscription".equalsIgnoreCase(data.get("keyword")))	{
			TpSubscription.getInstance().setData(data).setStringData(data.get("keyword")).subscriptionTP();
		}
		if ("verifyTpAcceptAgreement".equalsIgnoreCase(data.get("keyword")))	{
			TpSubscriptionAgreement.getInstance().setData(data).setStringData(data.get("keyword")).acceptAgreement();
		}
		if ("verifyTpActivation".equalsIgnoreCase(data.get("keyword")))	{
			TpActivation.getInstance().setData(data).setStringData(data.get("keyword")).activationTP();
		}
		if ("verifyTpConnectManager".equalsIgnoreCase(data.get("keyword")))	{
			TpConnectionManager.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpConnectionManager();
		}
		if ("verifyTpNotifications".equalsIgnoreCase(data.get("keyword")))	{
			TpNotifications.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpNotifications();
		}
		if ("verifyTpContactPage".equalsIgnoreCase(data.get("keyword")))	{
			TpContactPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpContactPage();
		}
		if ("verifyTpCompanyPage".equalsIgnoreCase(data.get("keyword")))	{
			TpCompanyPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpCompanyPage();
		}
		if ("verifyTpPasswordPage".equalsIgnoreCase(data.get("keyword")))	{
			TpPasswordPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpPasswordPage();
		}
		if ("verifyTpPaymentPage".equalsIgnoreCase(data.get("keyword")))	{
			TpPaymentPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpPaymentPage();
		}
		if ("verifyTpProductsPage".equalsIgnoreCase(data.get("keyword")))	{
			TpProductsPage.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpProductsPage();
		}
		if ("verifyTpNewConnection".equalsIgnoreCase(data.get("keyword")))	{
			TpNewConnection.getInstance().setData(data).setStringData(data.get("keyword")).verifyTpNewConnection();
		}
		if ("verifyTpConnectionApproval".equalsIgnoreCase(data.get("keyword")))	{
			TpApproveConnection.getInstance().setData(data).setStringData(data.get("keyword")).verifyapproveConnectionTP();
		}
				
		return true;
	}
}
