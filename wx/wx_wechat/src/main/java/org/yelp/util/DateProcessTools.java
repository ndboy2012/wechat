package org.yelp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateProcessTools {
      
	public DateProcessTools() {
		// TODO Auto-generated constructor stub
	}
   
	public static Timestamp geTimestamp() {
		 Date data = new Date();
	     Timestamp timestamp = new Timestamp(data.getTime());
	     return timestamp;
	}
	
	/*
	 * �Ƚ����������Ƿ����
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
	
	/*��ȡ�����е���*/
	public static int achieveDayOfMonth() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/*
	 * ��ȡ�������ʱ�䣬�������賿��ʱ��� ����:2014-10-21 00:00:00
	 */
	public static Timestamp achieveDayofLowestTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dd = df.format(new Date())+" 00:00:00";
		System.out.println(dd);
		return Timestamp.valueOf(dd);
	}
}
