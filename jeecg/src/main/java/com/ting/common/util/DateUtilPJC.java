package com.ting.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/*import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;*/
import java.util.Calendar;
import java.util.Date;

public class DateUtilPJC {

 /* public static LocalDate normalDate2LocalData(Date date) {
    Instant instant = date.toInstant();
    ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
    LocalDate localDate = zdt.toLocalDate();

    return localDate;

  }

  public static Date LocalDateToUdate(LocalDate localDate) {

    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
    java.util.Date date = Date.from(instant);
    return date;
  }

  public static Date DatesPlus(Date date, int plusDays) {
	    
	    LocalDate  localDate=normalDate2LocalData(date);
	    
	    LocalDate  newDate =localDate.plusDays(plusDays);
	        
	        return  LocalDateToUdate(newDate);
	  }*/

  public static String getNowDefault() {
    return getNow("yyyyMMdd HH:mm:ss");
  }

  public static String getNow(String format) {
    Date today = getToday();
    return new SimpleDateFormat(format).format(today);
  }

  public static String getTodayWithYYYYMMDD() {

    return getToday("yyyy-MM-dd");
  }

  public static String getToday(String format) {
    Date today = getToday();
    return new SimpleDateFormat(format).format(today);
  }

  /**
   *  返回当前日期  Date 类型
   * @return 
   */
  public static Date getToday() {
    Calendar cal = Calendar.getInstance();
    return cal.getTime();
  }
  
  public static String date2String(Date date, String format) {
    
    return new SimpleDateFormat(format).format(date);
  }
  
 public static String date2String(Date date) {
    
    return new SimpleDateFormat("yyyyMMdd").format(date);
  }
 
 public static Date getDateByString(String str) {
	 Date date = new Date();
	    try {
	    	date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return date;
	  }
 public static Date getDateByString(String str,String format) {
	 Date date = new Date();
	    try {
	    	date = new SimpleDateFormat(format).parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return date;
	  }

  /**
   * 在当前的日期上加入一定的偏移量，计算新的日期
   * shift 支持 1M，2M，3M
   *          1D, 2D
   *          1Y, 2Y
   */
  /*public  static LocalDate  addShift2Date(Date date,String shift)
  {
     if(StringUtils.isEmpty(shift)) return null;
     
     if(StringUtils.length(shift)<2) return null; 
     
     LocalDate localDate =normalDate2LocalData(date);
     
     char unit=shift.charAt(shift.length()-1);
     
     String  numStr=shift.substring(0, shift.length()-1);
     
     LocalDate endDate=null;
     
     if(unit=='Y'||unit=='y')
     {
       endDate=localDate.plus(Long.parseLong(numStr), ChronoUnit.YEARS);
        
     }
     
     if(unit=='D'||unit=='d')
     {
        
       endDate= localDate.plus(Long.parseLong(numStr), ChronoUnit.DAYS);
        
     }
     if(unit=='M'||unit=='m')
     {
       endDate= localDate.plus(Long.parseLong(numStr), ChronoUnit.MONTHS);
     }
    
    return endDate;
    
  }
  */

  public static void main(String[] args) {

    Date now = new Date();

    // System.out.println(DateUtil.addShift2Date(now, "6M"));

    //System.out.println(DateUtil.dateAddTenor(now, "6M"));

  }
  
  public static String milliSecond2Duration(Date StartDate,Date endDate) {

	  long duration = endDate.getTime() - StartDate.getTime();
	  
	  int milliSecondTime = (int) duration;
	  int hour = milliSecondTime /(60*60*1000);
	  int minute = (milliSecondTime - hour*60*60*1000)/(60*1000);
	  int seconds = (milliSecondTime - hour*60*60*1000 - minute*60*1000)/1000;
	  
	  if(seconds >= 60 )
	  {
	   seconds = seconds % 60;
	      minute+=seconds/60;
	  }
	  if(minute >= 60)
	  {
	    minute = minute % 60;
	    hour  += minute/60;
	  }
	  
	  String sh = "";
	  String sm ="";
	  String ss = "";
	  if(hour <10) {
	     sh = "0" + String.valueOf(hour);
	  }else {
	     sh = String.valueOf(hour);
	  }
	  if(minute <10) {
	     sm = "0" + String.valueOf(minute);
	  }else {
	     sm = String.valueOf(minute);
	  }
	  if(seconds <10) {
	     ss = "0" + String.valueOf(seconds);
	  }else {
	     ss = String.valueOf(seconds);
	  }
	  
	  return sh +":"+sm+":"+ ss;

	  }
  
  public static String getFirstDayInYear(String date) {
	  String year = date.substring(0,3);
	  return year+"-01-01";
	  }
  
  public static long daysBetween(Date one, Date two) {  
	    long difference =  (one.getTime()-two.getTime())/86400000;  
	    return Math.abs(difference);  
	}  
  

}
