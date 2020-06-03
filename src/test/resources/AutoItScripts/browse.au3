; sleep statements are added only to illustrate the focus on buttons
;read arguments
;Local $pathToSave="d:\test\test.pdf"	
Local $pathToSave=$CmdLine[1];

; get the handle of main window
Local $windHandle=WinGetHandle("[Class:IEFrame]", "")
Local $winTitle = "[HANDLE:" & $windHandle & "]"; 
;get coordinates of default HWND 
Local $ctlText=ControlGetPos ($winTitle, "", "[Class:DirectUIHWND;INSTANCE:1]")
sleep(3000)
MsgBox(0,"1",$CmdLine[1],2); 
; wait till the notification bar is displayed
Local $color= PixelGetColor ($ctlText[0],$ctlText[1])
 
;while $color <> 0
;sleep(500)
;$ctlText=ControlGetPos ($winTitle, "", "[Class:DirectUIHWND;INSTANCE:1]") 
;$color= PixelGetColor ($ctlText[0],$ctlText[1]) 
;wend
; Select save as option
		
WinActivate ($winTitle, "")
Send($pathToSave)
sleep(500)
Send("{ENTER}")
sleep(500)
;close the notification bar
WinActivate ($winTitle, "")

