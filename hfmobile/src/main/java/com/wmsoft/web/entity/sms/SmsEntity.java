package com.wmsoft.web.entity.sms;
public class SmsEntity {

	private String mobile;         //待发短信号码
	private String msmContent;     //待发短信内容
	private String serialNumber;   //待发短信流水号

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsmContent() {
		return msmContent;
	}

	public void setMsmContent(String msmContent) {
		this.msmContent = msmContent;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
