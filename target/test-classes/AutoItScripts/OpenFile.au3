If WinExists("View Downloads - Windows Internet Explorer ","") Then 
WinWait("View Downloads - Windows Internet Explorer ","",20) ; match the window with substring
$title = WinGetTitle("View Downloads - Windows Internet Explorer ") ; retrives whole window title
MsgBox(1,"adsd",$title)
WinActivate($title)
WinWaitActive($title)
ControlClick ('Opening ', 'Cancel', 'Button')
$e = ControlClick("Opening", "", "[CLASS:Button; TEXT:&Save; INSTANCE:1]")
Send("{SPACE}")
ControlClick ('Data Checker - Windows Internet Explorer', 'Open', 'Button1')
MsgBox(1,"ddd",$e)
Else
WinActivate('Data Checker - Windows Internet Explorer')
WinWaitActive('Data Checker - Windows Internet Explorer') 
Local $windHandle1=WinGetHandle("[Class:IEFrame]", "")
Local $winTitle1 = "[HANDLE:" & $windHandle1 & "]"; 
Local $ctlText1=ControlGetPos ($winTitle1, "", "[Class:DirectUIHWND;INSTANCE:1]")
sleep(3000)
WinActivate ($winTitle1, "")
Send("{F6}")
sleep(500) 
Send("{DOWN}")
sleep(500)
Send("a")
sleep(500)
Send("{ENTER}")
EndIf

	 
	 