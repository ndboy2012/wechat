package org.yelp.constant;

/**
 * 短信发送接口常量
 * @author Administrator
 *
 */
public class MessageConstant {

	public static String header = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.smsprocess.friendone.com/\">"
			+ "<soapenv:Header/><soapenv:Body><ws:SendSms><xml>";
	public static String tailer = "</xml></ws:SendSms></soapenv:Body></soapenv:Envelope>";

		public static String body = "&lt;ROOT&gt;&lt;USERID&gt;hfyd&lt;/USERID&gt;&lt;PASSWORD&gt;hfyd.123&lt;/PASSWORD&gt;&lt;LIST&gt;"
				+ "&lt;SMS OPTSN=\"serialNumber\"  SVCNUM=\"mobile\"  SMSCONTENT=\"smsContent\"&gt;"
				+ "&lt;/SMS&gt;&lt;/LIST&gt;&lt;/ROOT&gt;";
		
	public static String serialNumber = "serialNumber";
	
	public static String mobile = "mobile";
	
	public static String smsContent="smsContent";
	
	public static String SmsUser = "hfyd";
	
	public static String SmsPasswod = "hfyd.123";
	
	public static String url = "http://120.209.204.239:18084/SmsInterface/ws/SmsProcessWS?SendSms";
	
	public static String success_code = "0000";
	
}
