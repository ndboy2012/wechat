package com.wmsoft.wechat.entity.resp;


public class BaseMessage {

	// 发�?者的ID
	private String ToUserName;
	// �?��者的ID
	private String FromUserName;
	// 消息的类�?
	private String MsgType;
	// 消息创建的时�?
	private Long CreateTime;
	// 消息的ID�?
	private Long MsgId;
	// �?x0001被标志时，星标刚收到的消�?
	private int FuncFlag;

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

}
