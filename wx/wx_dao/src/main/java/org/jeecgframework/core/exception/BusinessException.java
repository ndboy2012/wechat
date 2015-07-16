package org.jeecgframework.core.exception;


public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String exMsg;

	public BusinessException(String message) {
		super(message);
		this.exMsg = message;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getExMsg() {
		return exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}

}
