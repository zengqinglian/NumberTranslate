package com.eric.zeng.numbertranslate;

public class NumberOutOfBoundsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NumberOutOfBoundsException(){
		_errorCode = ErrorCode.NUM_OUT_BOUNDS;
		//to support multi languages - need to config and get msg from db.
		_errorMsg = ErrorCode.NUM_OUT_BOUNDS_MSG;
	}
	
	public int getErrorCode() {
		return _errorCode;
	}

	public String getErrorMsg() {
		return _errorMsg;
	}

	private int _errorCode;
	private String _errorMsg;
}
