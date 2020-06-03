AutoItSetOption("WinTitleMatchMode","2") ; set the select mode to select using substring
 WinActivate("Print")
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
ControlClick($title,"","[CLASS:Edit; INSTANCE:4]")
ControlClick($title,"","[CLASS:Button; Text:Cancel]")
ControlClick($title,"","[CLASS:Button; INSTANCE:14]")
  
