package com.corelogic.automation.common;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;




public class DateUtils {
	final static String dateFormat="MM/dd/yyyy";
	final static DateFormat formatter = new SimpleDateFormat(dateFormat);
	
	// List of all date formats that we want to parse.
    // Add your own format here.
    @SuppressWarnings("serial")
	private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
    	{
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("MM/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
            add(new SimpleDateFormat("MMM dd, yyyy"));
        }
    };
		
	public static long millis(){
		return com.corelogic.utaf.utils.DateUtils.millis();
	}
	
	/**
     * Convert String with various formats into java.util.Date
     * 
     * @param input
     *            Date as a string
     * @return java.util.Date object if input string is parsed 
     *          successfully else returns null
     */
    public static Date getDate(String input) {
        Date date = null;
        if(null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                //Shhh.. try other formats
            }
            if (date != null) {
                break;
            }
        } 
        return date;
    }	
	
	public static String getYear(String strDate){
		Date date=getDate(strDate);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return String.valueOf(cal.get(Calendar.YEAR));
	}	
	
	public static String getDateDifference(String date1,String date2,String interval)	throws ParseException {
		Date parDate1 = getDate(date1);
		Date parDate2 = getDate(date2);

		if (interval.equalsIgnoreCase("Months")) {
			Months months = Months.monthsBetween(new DateTime(parDate1), new DateTime(parDate2));
			return String.valueOf(months.getMonths());
		} else if (interval.equalsIgnoreCase("Days"))	{
			Days days = Days.daysBetween(new DateTime(parDate1), new DateTime(parDate2));
			return String.valueOf(days.getDays());
		} else if (interval.equalsIgnoreCase("Years")) {
			Years years = Years.yearsBetween(new DateTime(parDate1), new DateTime(parDate2));
			return String.valueOf(years.getYears());
		} else {
			return String.valueOf(new Exception("interval parameter is invalid"));
		}
	}
	
	public static int getDaysDifference(String date1,String date2)
	{
		int diffDays=-0;
	
		try {
			Date dateObj1 = getDate(date1);
			Date dateObj2 = getDate(date2);
			
			long diff = dateObj2.getTime() - dateObj1.getTime();
			diffDays =  (int) (diff / (24* 1000 * 60 * 60));
			return diffDays;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return diffDays;
	}
	
	public static String getDateAndDayOfWeek(int noOFDays)
	{
		return getDateAndDayOfWeek(dateFormat,noOFDays);
	}

	public static String getDateAndDayOfWeek(String formatType,int noOFDays) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, noOFDays);
		return getDayOfWeek(cal.getTime())+", "+formatter.format(cal.getTime()); 
	}

	public static String getDayOfWeek(Date date) {
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
		return dayOfWeek;
	}
	
	/*
	 * If passed as 07/26/2013 returns value as Friday 
	 */
	
	public static String getDayOfWeek(String strDate) throws ParseException {
		return getDayOfWeek(getDate(strDate));
	}
	/*
	 * If passed as 07/26/2013 returns value as 6 (which is Friday)
	 */
	public static int getDayOfWeek_InInt(String strDate) {
		Calendar cal  = Calendar.getInstance();
		cal.setTime(getDate(strDate));	
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/*
	 * String strDate - 07/26/2013
	 * CalendarType calType= Calendar.Year or Calender.Month or Calendar.FRIDAY
	 * int addNo- used to addNo of years or Months or days as given CalendarType passed
	 * 
	 * return - new strDate with format MM/dd/yyyy
	 */
	public static String getChangeDate(String strDate,int calType,int addNo) {
		Calendar cal = Calendar.getInstance();		
		cal.setTime(getDate(strDate));		
		cal.add(calType, addNo);	//cal.add(Calendar.MONTH, 2);		
		return formatter.format(cal.getTime());
	}
	
	
	/*
	 * String strDate - 07/26/2013 
	 * weekDay - Pass any Calendar weekdays 
	 * 
	 * isNextWeekDate -- pass true for next immediate week day match date from given date
	 * 					 pass false for last week day match date from given date  
	 * 
	 ** 
	 * return - new strDate of weekDay matched with format MM/dd/yyyy 
	 */
	public static String getDateForGivenDay(String strDate,int weekDay, boolean isNextWeekDate)
	{
		int weekDayAdjust= isNextWeekDate ? 1 :-1;
		Calendar cal  = Calendar.getInstance();
		cal.setTime(getDate(strDate));	
		int currentWeek= cal.get(Calendar.DAY_OF_WEEK);
		while(weekDay!=currentWeek)
		{
			cal.add(Calendar.DATE, weekDayAdjust);
			currentWeek= cal.get(Calendar.DAY_OF_WEEK);
		}
		return formatter.format(cal.getTime());
	} 
}
