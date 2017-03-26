package com.horizon.resources.exception;

public class HnException extends Exception {

	private static final long serialVersionUID = -1403063093185293907L;

	private String errorCode;
	private String errorMessage;

	public HnException(String errorCode) {
		this.errorCode = errorCode;
	}

	public HnException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMessage = errorMsg;
	}

	public HnException(String errorCode, String errorMsg, Throwable root) {
		super(root);
		this.errorCode = errorCode;
		this.errorMessage = errorMsg;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public Throwable fillInStackTrace() {
		return null;
	}

}
