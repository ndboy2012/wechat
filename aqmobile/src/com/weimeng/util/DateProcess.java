package com.weimeng.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateProcess {

	public DateProcess() {
		// TODO Auto-generated constructor stub
	}
   
	public static Timestamp geTimestamp() {
		 Date data = new Date();
	     Timestamp timestamp = new Timestamp(data.getTime());
	     return timestamp;
	}
	
	/*
	 * 比较两个日期是否相等
	 */
	public static boolean compareDate(Date today,Date database) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
		String todayStr = sdf.format(today);
		String databaseStr = sdf.format(database);
		if(todayStr.equals(databaseStr)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Date convertString2date(String date) {
		 DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		 Date dat = null;
		 try {
			 dat = fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
	    }
		 return dat;
	}
	
	 /*
	  * 获取某个日期前一天
	  */
	 public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数  
	        Calendar c = Calendar.getInstance();  
	        Date date = null;  
	        try {  
	            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        }  
	        c.setTime(date);  
	        int day = c.get(Calendar.DATE);  
	        c.set(Calendar.DATE, day);  
	  
	        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());  
	        return dayBefore;  
	    }  
}
