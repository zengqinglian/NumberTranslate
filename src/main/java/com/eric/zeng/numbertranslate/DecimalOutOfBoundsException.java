package com.eric.zeng.numbertranslate;

public class DecimalOutOfBoundsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DecimalOutOfBoundsException(){
		_errorCode = ErrorCode.DECIMAL_OUT_BOUNDS;
		//to support multi languages - need to config and get msg from db.
		_errorMsg = ErrorCode.DECIMAL_OUT_BOUNDS_MSG;
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
