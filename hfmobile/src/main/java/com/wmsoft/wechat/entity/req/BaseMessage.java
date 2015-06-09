package com.wmsoft.wechat.entity.req;


public class BaseMessage {
	// 接收者的open_id
		private String ToUserName;
		// 发送者的open__id
		private String FromUserName;
		// 发送的消息的类型
		private String MsgType;
		// 发送消息的时间
		private Long CreateTime;
		// 消息的ID编号?
		private Long MsgId;

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
