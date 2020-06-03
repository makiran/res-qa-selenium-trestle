AutoItSetOption("WinTitleMatchMode","2") ; set the select mode to select using substring
 WinActivate("Print")
; wait Until dialog box appears
$s = WinWait("Print","",10) ; match the window with substring
if $s= 0 Then
   ConsoleWrite("false print window does not exist")
   Break(1)
   EndIf
$title = WinGetTitle("Print") ; retrives whole window title
WinActivate($title)
WinActivate($title)
WinActivate($title)
WinWaitActive($title)
ControlClick($title,"","[CLASS:Button; Text:&Print]")
ControlClick($title,"","[CLASS:Button; Text:Print]")
ControlClick($title,"","[CLASS:Button; Text:&Print]")
ControlClick($title,"","[CLASS:Button; Text:Print]")

 
