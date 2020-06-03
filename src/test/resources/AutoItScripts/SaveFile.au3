AutoItSetOption("WinTitleMatchMode","2") ; set the select mode to select using substring
Local $fileDir = "\temp"
Local $file = $CmdLine[1]
;Local $file = "test.pdf"
Local $handle = ''
;MsgBox(0,"1",$CmdLine[1],2); 

; Wait for the new dialogbox to open

 Local $sd = WinWait("Save","",10)
 
 
  if $sd =0 Then
	 ConsoleWrite("false save as dialog was not open")
	 Break(1)
	 EndIf
   $title = WinGetTitle("Save")
  
   
   if WinExists($title) Then
   		WinActivate($title)
		Sleep(2000)
	If FileExists($fileDir) Then
		If FileExists($file) Then
			FileDelete($file)	    	
		EndIf				 
	Else
	    if(DirCreate ($fileDir))THEN
		Else
				WinWait($title,"",1000) ;  
		EndIf
   EndIf   
	   	
	  
		if (StringInStr($file,";") > -1) Then
		 $file = StringReplace($file,";",":")
		 EndIf
        ControlSetText ($title, "", "[CLASS:Edit; INSTANCE:1]",  StringReplace($file,";",":"))
		Send("{ENTER}")
		Sleep(2000)		 
		
	  if WinExists($title) =1 Then
	    Send("{ENTER}")
		Sleep(1000)
		 ControlSetText ($title, "", "[CLASS:Edit; INSTANCE:1]",  StringReplace($file,";",":"))
		Send("{ENTER}")
		Sleep(1000)		 
		Send("{ENTER}")
		
	  EndIf
	 
	 if WinExists($title) = 1 Then
      Sleep(1000)	 
		 Send("{ESC}")
	 EndIf
	 
		ConsoleWrite("Enter the path into save as dialog was sucessfull")
   EndIf
   


