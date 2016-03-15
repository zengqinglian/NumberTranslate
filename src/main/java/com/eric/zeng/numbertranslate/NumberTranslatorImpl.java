package com.eric.zeng.numbertranslate;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberTranslatorImpl implements NumberTranslator {

	@Override
	public String translate(double value) 
		throws NumberOutOfBoundsException,DecimalOutOfBoundsException{
		
		double absValue = Math.abs(value);
		
		long intValue = getIntegerFromNumber(absValue);
		String decimalValue = getDecimalNumberString(absValue);
		
		if(intValue > NumberTranslator.MAX_NUM){
			throw new NumberOutOfBoundsException();
		}
		
		if(decimalValue.length() > NumberTranslator.MAX_DECIMAL){
			throw new DecimalOutOfBoundsException();
		}
		
		String retString = translateInteger(intValue);
		
		if(Integer.parseInt(decimalValue) != 0){
			retString = retString + POINT + translateDecimal(decimalValue);
		}
		
		if(value<0){
			return MINUS + retString;
		}
		
		//remove first space at left
		return retString.trim();
	}
	private String translateInteger(long number){
		
		if (number == 0) {
			return "zero";
		}

		String strNumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		strNumber = df.format(number);

		// XXXnnnnnnnnn 
		int billions = Integer.parseInt(strNumber.substring(0,3));
		// nnnXXXnnnnnn
		int millions  = Integer.parseInt(strNumber.substring(3,6)); 
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(strNumber.substring(6,9)); 
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(strNumber.substring(9,12));    

		String tradBillions;
		switch (billions) {
		    case 0:
		      tradBillions = "";
		      break;
		    case 1 :
		      tradBillions = " one billion";
		      break;
		    default :
		      tradBillions = convertLessThanOneThousand(billions) 
		      + " billions";
		}
		String result =  tradBillions;

		String tradMillions;
		switch (millions) {
		    case 0:
		      tradMillions = "";
		      break;
		    case 1 :
		      tradMillions = " one million";
		      break;
		    default :
		      tradMillions = convertLessThanOneThousand(millions) 
		      + " millions";
		}
		result =  result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		    case 0:
		      tradHundredThousands = "";
		      break;
		    case 1 :
		      tradHundredThousands = " one thousand";
		      break;
		    default :
		      tradHundredThousands = convertLessThanOneThousand(hundredThousands) 
		      + " thousands";
		}
		result =  result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result =  result + tradThousand;

		return result;
	}
	
	private String translateDecimal(String value){
		String retStr = "";
		int length = value.length();
		for(int i=0; i<length; i++){
			retStr += DECIMALNAMES[Integer.parseInt(Character.toString(value.charAt(i)))];
		}
		return retStr;
	}
	
	private String getDecimalNumberString(double value){
		String s = covertNumberToString(value);
		int index = s.indexOf('.');
		return s.substring(index+1);
	}
	
	private long getIntegerFromNumber(double value){
	    String s = covertNumberToString(value);
	    int index = s.indexOf('.');
	    if (index < 0) {
	        return Long.parseLong(s);
	    }
	    String intStr = s.substring(0, index);
	    return Long.parseLong(intStr);
	}
	
	private String covertNumberToString(double value){
		Double.toString(value);
		BigDecimal bigDecimal = new BigDecimal("" + value);
	    return bigDecimal.toPlainString();
	}
	
	private String convertLessThanOneThousand(int number) {
		String retStr="";
		
	    if (number % 100 < 20){
	    	retStr = NUMNAMES[number % 100];
		    number /= 100;
		}
		else{
			retStr = NUMNAMES[number % 10];
		    number /= 10;

		    retStr = TENSNAMES[number % 10] + retStr;
		    number /= 10;
		}
	    
	    if (number == 0){ 
			return retStr;
		}
	    
	    if(retStr == ""){
	    	return NUMNAMES[number] + " hundred";
	    }
	    
	    if(number == 1){
	    	return NUMNAMES[number] + " hundred and" + retStr;
	    }
		
	    return NUMNAMES[number] + " hundreds and" + retStr;
	}
	
	private static final String MINUS = "minus";
	private static final String POINT = " point";
	
	private static final String[] TENSNAMES = {
	    "",
	    " ten",
	    " twenty",
	    " thirty",
	    " forty",
	    " fifty",
	    " sixty",
	    " seventy",
	    " eighty",
	    " ninety"
	  };

	  private static final String[] NUMNAMES = {
	    "",
	    " one",
	    " two",
	    " three",
	    " four",
	    " five",
	    " six",
	    " seven",
	    " eight",
	    " nine",
	    " ten",
	    " eleven",
	    " twelve",
	    " thirteen",
	    " fourteen",
	    " fifteen",
	    " sixteen",
	    " seventeen",
	    " eighteen",
	    " nineteen"
	  };
	  
	  private static final String[] DECIMALNAMES = {
		    " zero",
		    " one",
		    " two",
		    " three",
		    " four",
		    " five",
		    " six",
		    " seven",
		    " eight",
		    " nine"
	 };
}
