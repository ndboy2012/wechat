package com.wmsoft.common.tools;

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
	
	public static int achieveDayOfHour() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	/*获取日期中的天*/
	public static int achieveDayOfMonth() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/*
	 * 获取今日最低时间，即今日凌晨的时间点 例如:2014-10-21 00:00:00
	 */
	public static Timestamp achieveDayofLowestTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dd = df.format(new Date())+" 00:00:00";
		System.out.println(dd);
		return Timestamp.valueOf(dd);
	}
	
}
