package com.corelogic.automation;

import com.corelogic.utaf.main.WebDriverApplication;

public class Utilities extends WebDriverApplication {
	public static void executeAutoIt(String fileName, String DestinationFolder) {

		// String path ="cmd /C call C:\\AutoItScripts\\"+fileName;
		String path = "cmd /C call AutoIt3.exe C:\\java-workspace\\UTAF-PROJECTS\\LDCModified\\src\\test\\resources\\AutoItScripts\\"
				+ fileName;
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec(path + DestinationFolder);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception!! " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	public static void openAutoIT(String fileName)	{
		String path = "cmd /C call AutoIt3.exe C:\\java-workspace\\UTAF-PROJECTS\\LDC\\src\\test\\resources\\AutoItScripts\\"
				+ fileName;
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec(path);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception!! " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void KillPROCESS() {
		Runtime runtime = Runtime.getRuntime();
		String[] strProcess = { "AutoIt3.exe" };
		for (String Process : strProcess) {
			String[] killWPargs = { "TASKKILL", "/IM", Process, "/f" };
			try {
				Process kill = runtime.exec(killWPargs);
				kill.waitFor();
			} catch (Exception e) {

			}
		}
	}
}
