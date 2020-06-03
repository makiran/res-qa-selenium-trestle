package com.corelogic.automation;



import com.corelogic.utaf.main.internal.Base;

public class Global  {
	
	public static String strUserid =  "";
	public static String strUser =  "";
	public static String strPwd =  "";
	public static String strContainsText =  Base.get("CONTAINSTEXT").toString();
	public static String strLinkText =  Base.get("LINKTEXT").toString();
	public static String strTabOrSubTabActive =  Base.get("TABORSUBTABACTIVE").toString();
	
	
	/**Accounts Page**/
	public static String strTrackOffenseForPast =  Base.get("TRACKOFFENSESFORPAST").toString();
	public static String strTrackOffenseForPastTextBox =  Base.get("TRACKOFFENSESFORPASTTEXTBOX").toString();
	public static String strRollingDays =  Base.get("ROLLINGDAYS").toString();
	public static String strSaveForm =  Base.get("SAVEFORM").toString();
	public static String strPreDefinedNotValidNotes =  Base.get("PREDEFINEDNOTVALIDNOTES").toString();
	public static String strAccountID =  Base.get("ACCOUNTID").toString();
	public static String strUserRolesProfiles=  Base.get("USERROLESPROFILES").toString();
	public static String strUserRole =  Base.get("USERROLE").toString();
	public static String strFineExportFilesViewableAdmin =  Base.get("FINEEXPORTFILESVIEWABLEADMIN").toString();
	public static String strFineExportFilesNotViewableAdmin =  Base.get("FINEEXPORTFILESNOTVIEWABLEADMIN").toString();
	public static String strFineExportFilesViewableModerator =  Base.get("FINEEXPORTFILESVIEWABLEMODERATOR").toString();
	public static String strFineExportFilesNotViewableModerator =  Base.get("FINEEXPORTFILESNOTVIEWABLEMODERATOR").toString();
	public static String strsaveformbutton = Base.get("SAVEFORMBUTTON").toString();
	public static String strCaretsMlsDropDown = Base.get("CARETSMLSDROPDOWN").toString();
	public static String strCaretsAssocDropDown = Base.get("CARETSASSOCDROPDOWN").toString();
	public static String strMlsDropDownOption = Base.get("MLSDROPDOWNOPTION").toString();    
	public static String strAssocDropDownOption = Base.get("ASSOCDROPDOWNOPTION").toString();
	public static String strUserRoleUpdateMessage = Base.get("USERROLEUPDATEMESSAGE").toString();
	public static String strUserRoleDropDownOption = Base.get("USERROLEDROPDOWNOPTION").toString();
	public static String strUserPrivileges = Base.get("USERPRIVILEGES").toString();
	
	/**Home Page**/
	public static String strLogout = Base.get("LOGOUT").toString();
	public static String strQuickLookUpValue =  Base.get("QUICKLOOKUPVALUE").toString();
	public static String strQuickLookUpGo =  Base.get("QUICKLOOKUPGO").toString();
	public static String strSelectQuickLookUp = Base.get("SELECTQUICKLOOKUP").toString();
	public static String strReportITCountLink = Base.get("REPORTITCOUNTLINK").toString();
	public static String strVioSummaryVioName = Base.get("VIOSUMMARYVIONAME").toString();
	public static String strCustomNotifyExpand = Base.get("CUSTOMNOTIFYEXPAND").toString();
	public static String strCustomNotifyBatch = Base.get("CUSTOMNOTIFYBATCH").toString();
	public static String strBatchProcessButton = Base.get("BATCHPROCESSBUTTON").toString();
	public static String strBatchExecutionSuccessfull = Base.get("BATCHEXECUTIONSUCCESSFULL").toString();
	public static String strIconFineExportFiles = Base.get("ICONFINEEXPORTFILES").toString();
	
	/**Email Settings**/
	public static String strDoNotMailSettings =  Base.get("DONOTMAILSETTINGS").toString();
	public static String strDoNotAgentEmail= Base.get("ADDEDAGENTEMAIL").toString();
	
	/** Search Listing/Violation Form **/
	public static String strVioState =  Base.get("VIOLATIONSTATE").toString();
	public static String strVioName =  Base.get("VIOLATIONNAME").toString();
	public static String strIncludingHistory =  Base.get("INCLUDINGHISTORY").toString();
	public static String strVioHisFrom =  Base.get("VIOHISTORYFROM").toString();
	public static String strListStatus =  Base.get("LISTSTATUS").toString();
	public static String strPropType =  Base.get("PROPERTYTYPE").toString();
	public static String strAgent =  Base.get("AGENT").toString();
	public static String strAgentPubID =  Base.get("AGENTPUBID").toString();
	public static String strEmail =  Base.get("EMAIL").toString();
	public static String strOffice =  Base.get("OFFICE").toString();
	public static String strOfficePubID =  Base.get("OFFICEPUBID").toString();
	public static String strVioDatePeriod =  Base.get("VIODATEPERIOD").toString();
	public static String strVioDateRange =  Base.get("VIOLATIONDATERANGE").toString();
	public static String strVioDateFrom =  Base.get("VIOLATIONDATEFROM").toString();
	public static String strVioDateTo =  Base.get("VIOLATIONDATETO").toString();
	public static String strGraceDatePeriod =  Base.get("GRACEDATEPERIOD").toString();
	public static String strGraceDateRange =  Base.get("GRACEDATERANGE").toString();
	public static String strGraceDateFrom =  Base.get("GRACEDATERANGEFROM").toString();
	public static String strGraceDateTo =  Base.get("GRACEDATERANGETO").toString();
	public static String strVioHisTo =  Base.get("VIOHISTORYTO").toString();
	public static String strTextWithinNote =  Base.get("TEXTWITHINANOTE").toString();
	public static String strSaveSearchName =  Base.get("SAVESEARCHNAME").toString();
	public static String strConditionOR =  Base.get("CONDITIONOR").toString();
	public static String strVioSearch =  Base.get("VIOLATIONSEARCH").toString();
	
	/** Search Agent Form **/
	
	public static String strAgtID =  Base.get("AGTID").toString();
	public static String strAgtPubID =  Base.get("AGTPUBID").toString();
	public static String strAgtLastname =  Base.get("LASTNAME").toString();
	public static String strAgtEmailID =  Base.get("AGTEMAILID").toString();
	public static String strAgtOfficeID =  Base.get("AGTOFFICEID").toString();
	public static String strAgtVio =  Base.get("AGTVIO").toString();
	public static String strAgtSearch =  Base.get("AGENTSEARCH").toString();
	public static String strAgtVioSearch =  Base.get("AGENTVIOSEARCH").toString();
	
	/** Search Office Form **/
	
	public static String strOfcName =  Base.get("OFCNAME").toString();
	public static String strOfcID =  Base.get("OFCID").toString();
	public static String strOfcPubID =  Base.get("OFCPUBID").toString();
	public static String strOfcEmailID =  Base.get("OFCEMAILID").toString();
	public static String strOfcViolation =  Base.get("OFCVIOLATION").toString();
	public static String strOfcSearch =  Base.get("OFCSEARCH").toString();
	public static String strOfcVioSearch =  Base.get("OFCVIOSEARCH").toString();
	
	/**Search Results**/

	public static String strShowDetailsNote =  Base.get("SHOWDETAILSNOTE").toString();
	public static String strShowDetailsProcessAll = Base.get("SHOWDETAILSPROCESSALL").toString();
	public static String strNextArrow = Base.get("NEXTARROW").toString();
	public static String strNextArrowDown = Base.get("NEXTARROWDOWN").toString();
	public static String strResultsVioName = Base.get("RESULTSPAGEVIOLATIONNAME").toString();
	public static String strNotesBelongsToDropDown = Base.get("NOTEBELONGSTODROPDOWN").toString();
	public static String strNotesBelongsToDropDownListing = Base.get("NOTEBELONGSTODROPDOWNLISTING").toString();
	public static String strNotesBelongsToDropDownViolation = Base.get("NOTEBELONGSTODROPDOWNVIOLATION").toString();
	public static String strNotesFollowUpFlag = Base.get("NOTESFOLLOWUPFLAG").toString();
	public static String strSendFollowUpNotice = Base.get("SENDFOLLOWUPNOTICE").toString();
	public static String strNoticeTypeDropDown = Base.get("NOTICETYPEDROPDOWN").toString();
	public static String strNoticeTypeDropDownValue = Base.get("NOTICETYPEDROPDOWNVALUE").toString();
	public static String strNoteEditorFrame = Base.get("NOTEEDITORFRAME").toString();
	public static String strTextEditor = Base.get("NOTETEXTEDITOR").toString();
	public static String strAddNote = Base.get("ADDNOTE").toString();
	public static String strAddedNote = Base.get("ADDEDNOTE").toString();
	public static String strIncludedCheckMark = Base.get("INCLUDEBLUECHECKMARK").toString();
	public static String strReportITVioSummary = Base.get("REPORTITVIOSUMMARY").toString();
	

	/**Search Details**/
	
	public static String strLoadTab=  Base.get("LOADTAB").toString();
	public static String strTabOn=  Base.get("TABON").toString();
	public static String strSubTabOn=  Base.get("SUBTABON").toString();
	public static String strSubTabs=  Base.get("SUBTABS").toString();
	public static String[] strSearchDetailsTabNames =  {"Listing/Violation Details", "Notes", "Listing History", "Notification History", "Violation History", "Links", "Violation Photos", "Violation Pdfs"};
	public static String strViewListing =  Base.get("VIEWLISTING").toString();
	public static String strSearchDetailsMLSNum =  Base.get("SEARCHDETAILSMLSNUMBER").toString();
	public static String strManualVioDetailsSection =  Base.get("MANUALVIOLATIONDETAILSSECTION").toString();
	public static String strScrolling =  Base.get("SCROLLING").toString();
	public static String strPaidLedger =  Base.get("PAIDLEDGER").toString();
	public static String strInvalidSession =  Base.get("INVALIDSESSION").toString();
	public static String strLedgerPopUpFieldValues = Base.get("LEDGERPOPUPFIELDVALUES").toString();
	public static String strPopUpClose = Base.get("POPUPCLOSE").toString();
	public static String strLedgerPopUpPaidCheckBox = Base.get("LEDGERPOPUPPAIDCHECKBOX").toString();
	public static String strLedgerPopUpUpdate = Base.get("LEDGERPOPUPUPDATE").toString();
	public static String strPaidLedgerBesideNotes = Base.get("PAIDLEDGERBESIDENOTES").toString();
	public static String strUpdate = Base.get("UPDATE").toString();
	public static String strUpdateAndMoveToNextRecord = Base.get("UPDATEANDMOVETONEXTRECORD").toString();
	public static String strErrorMessage = Base.get("ERRORMESSAGE").toString();
	public static String strVioDetailsRadio = Base.get("VIORADIOBUTTON").toString();
	public static String strVioDetailsAssocEdit = Base.get("VIODETAILSASSOCEDIT").toString();
	public static String strVioDetailsData = Base.get("VIODETAILSDATA").toString();
	public static String strSearchDetailsBackArrow = Base.get("SEARCHDETAILSBACKARROW").toString();
	public static String strSearchDetailsVioStates = Base.get("SEARCHDETAILSVIOSTATES").toString();
	public static String strNotificationCountHis = Base.get("NOTIFICATIONCOUNTHIS").toString();
	public static String strViolationROCount = Base.get("VIOLATIONROCOUNT").toString();
	public static String strROCreatedDate = Base.get("ROCREATEDDATE").toString();
	public static String strReportITNotValidTable = Base.get("REPORTITNOTVALIDTABLE").toString();
	public static String strReportITNotValid = Base.get("REPORTITNOTVALID").toString();
	public static String strIncludeInNotifications = Base.get("INCLUDEINNOTIFICATIONS").toString();
	public static String strReportITNotesTextArea = Base.get("REPORTITNOTESTEXTAREA").toString();
	public static String strReportITMaxChar = Base.get("REPORTITMAXCHAR").toString();
	public static String strVioDetailsVioState = Base.get("VIODETAILSVIOLATIONSTATE").toString();
	public static String strVioDetailsVioNo = Base.get("VIODETAILSVIONO").toString();
	public static String strReportITNoteRadioButton = Base.get("REPORTITNOTERADIOBUTTON").toString();
	public static String strReturnToListing = Base.get("RETURNTOLISTING").toString();
	public static String strNotValidToNewState = Base.get("REPORTITNEWSTATE").toString();
	public static String strVioDetailsOrgState = Base.get("VIODETAILSORIGINALSTATE").toString();
	public static String strDetailsAgentID = Base.get("DETAILSAGENTID").toString();
	public static String strVioReportedByMeText = Base.get("VIOREPORTEDBYMETEXT").toString();
	public static String strVioDetectedBy = Base.get("VIOLATIONDETECTEDBY").toString();
	public static String strNotesDetectedBy = Base.get("NOTESDETECTEDBY").toString();
	public static String strDoNotEmailSymbol = Base.get("DONOTEMAILSYMBOL").toString();
	public static String strCourtesyNotifyRadButton = Base.get("COURTESYNOTIFYRADIOBUTTON").toString();
	public static String strNotificationDeliveredStatus = Base.get("NOTIFICATIONDELIVEREDSTATUS").toString();
	public static String strViolationPdfDropdown = Base.get("VIOLATIONPDFDROPDOWN").toString();
	public static String strNoVioText = Base.get("NOVIOTEXT").toString();
	public static String strNoPdfText = Base.get("NOPDFTEXT").toString();
	public static String strAvailablePdf = Base.get("AVAILBALEPDF").toString();
	public static String strViolationPdfBrowse = Base.get("VIOLATIONPDFBROWSE").toString();
	public static String strPdfView = Base.get("PDFVIEW").toString();
	public static String strPaperClip = Base.get("PAPERCLIP").toString();
	public static String strPdfDescription = Base.get("PDFDESCRIPTION").toString();
	public static String strPdfUpload = Base.get("PDFUPLOAD").toString();
	public static String strBrowsePdf = Base.get("BROWSEPDF").toString();
	public static String strVioPdfDelete = Base.get("VIOPDFDELETE").toString();
	public static String strNoVioMessage = Base.get("NOVIOLATIONSMESSAGE").toString();

	
	/**Fine Export Files**/
	public static String strSingleDayTab = Base.get("SINGLEDAYTAB").toString();
	public static String strDateRangeTab = Base.get("DATERANGETAB").toString();
	public static String strFtpTab = Base.get("FTPTAB").toString();
	public static String strSingleDayTabColumns = Base.get("SINGLEDAYTABCOLUMNS").toString();
	public static String strFineExportOpen = Base.get("FINEEXPORTOPEN").toString();
	public static String strFineExportResults = Base.get("FINEEXPORTRESULTS").toString();
	public static String strExportDateDropDown = Base.get("EXPORTDATEDROPDOWN").toString();
	public static String strExportDateDropDownOption = Base.get("EXPORTDATEDROPDOWNOPTION").toString();
	public static String strFineExportTabNoResults = Base.get("FINEEXPORTTABNORESULTS").toString();
	public static String strExportDateValue = Base.get("EXPORTDATEVALUE").toString();
	public static String strExportedFileValue = Base.get("EXPORTEDFILEVALUE").toString();
	public static String strDownloadedValue = Base.get("DOWNLOADEDVALUE").toString();
	public static String strDeleteFlagValue = Base.get("DELETEFLAGVALUE").toString();
	public static String strExportedFileDropDownValue1 = Base.get("EXPORTEDFILEDROPDOWNVALUE1").toString();
	public static String strExportedFileDropDownValue2 = Base.get("EXPORTEDFILEDROPDOWNVALUE2").toString();
	public static String strFilereport = Base.get("FILEREPORT").toString();
	public static String strFilereportValue = Base.get("FILEREPORTVALUE").toString();
	
	
	/**Group Processing**/
	public static String strSearchDetailsTab = Base.get("SEARCHDETAILSTAB").toString();
	public static String strListingCountInDetailsTab = Base.get("LISTINGCOUNTINDETAILSTAB").toString();
	public static String strSearchResultsTab = Base.get("SEARCHRESULTSTAB").toString();
	public static String strProcessGroupButton = Base.get("PROCESSGROUPBUTTON").toString();
	public static String strMlsCheckBox = Base.get("MLSCHECKBOX").toString();
	public static String strSelectAllRecordsLink = Base.get("SELECTALLRECORDSLINK").toString();
	public static String strUnselectAllRecordsLink = Base.get("UNSELECTALLRECORDSLINK").toString();
	public static String strGroupProcessViolations = Base.get("GROUPPROCESSVIOLATIONS").toString();
	public static String strGroupProcessViolationsOption = Base.get("GROUPPROCESSVIOLATIONSOPTION").toString();
	public static String strViolationStates = Base.get("VIOLATIONSTATES").toString();
	public static String strClearbutton = Base.get("CLEARBUTTON").toString();
	public static String strGroupProcessAddedNote = Base.get("GROUPPROCESSADDEDNOTE").toString();
	public static String strCountOflistingsForGroupProcess = Base.get("COUNTOFLISTINGSFORGROUPPROCESS").toString();
	public static String strAddNoteButton = Base.get("ADDNOTEBUTTON").toString();
	public static String strIncludeInNotificationsCheckBox = Base.get("INCLUDEINNOTIFICATIONSCHECKBOX").toString();
	public static String strAddFollowUpFlagCheckBox = Base.get("ADDFOLLOWUPFLAGCHECKBOX").toString();
	public static String strNotesFrame = Base.get("NOTESFRAME").toString();
	public static String strSaveNoteButton = Base.get("SAVENOTEBUTTON").toString();
	public static String strViolationStatesOption = Base.get("VIOLATIONSTATESOPTION").toString();
	public static String strViolationUpdatedMessage = Base.get("VIOLATIONUPDATEDMESSAGE").toString();
	public static String strFirstMlsLinkInResults = Base.get("FIRSTMLSLINKINRESULTS").toString();
	public static String strViolationsAppliedOnListing = Base.get("VIOLATIONSAPPLIEDONLISTING").toString();
	public static String strAppliedViolation = Base.get("APPLIEDVIOLATION").toString();
	public static String strNotesAddedToViolation = Base.get("NOTESADDEDTOVIOLATION").toString();
	public static String strGoToRecordTextBox = Base.get("GOTORECORDTEXTBOX").toString();
	public static String strSearchResultGoButton = Base.get("SEARCHRESULTGOBUTTON").toString();
	public static String strAllMlsCheckBoxes = Base.get("ALLMLSCHECKBOXES").toString();
	public static String strNumberOfListingResults = Base.get("NUMBEROFLISTINGRESULTS").toString();
}
