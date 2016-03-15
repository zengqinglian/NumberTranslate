package com.eric.zeng.numbertranslate;

public interface NumberTranslator {
	
	public static final double MIN_NUM = -999999;
	public static final double MAX_NUM = 999999;
	public static final int MAX_DECIMAL = 4;
	
	/**
	 * 
	 * @param double d
	 * @return words
	 */
	public String translate(double d) throws NumberOutOfBoundsException,DecimalOutOfBoundsException;
}
