package com.eric.zeng.numbertranslate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/*
 * I tried to use mock but the programme too simple and just
 * need to test against one class. Mock object should be used 
 * to create other objects when test current class. eg. when test 
 * object type A, we need to get property from object type B, then 
 * we can mock B. However this case it seems not quite match.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestNumberTranslator {
	
	@Autowired
	NumberTranslator translator; 
	
	@Before
	public void setUp(){
	}
	
	@Test
	public void testNumberOutOfBoundsException(){
		try {
			translator.translate(TEST_NUM_1);
			fail("Should throw exception.");
		}catch (DecimalOutOfBoundsException e) {
			fail("Throws wrong exception.");
		}catch(NumberOutOfBoundsException e){
			assertEquals(ErrorCode.NUM_OUT_BOUNDS,e.getErrorCode());
		}
	}
	
	@Test
	public void testDecimalOutOfBoundsException(){
		try {
			translator.translate(TEST_NUM_2);
			fail("Should throw exception.");
		}catch (NumberOutOfBoundsException e) {
			fail("Throws wrong exception.");
		}catch(DecimalOutOfBoundsException e){
			assertEquals(ErrorCode.DECIMAL_OUT_BOUNDS,e.getErrorCode());
		}
	}
	
	@Test
	public void testTranslateNumber(){
		try{
			assertEquals(RET_WORD_3,translator.translate(TEST_NUM_3));
			assertEquals(RET_WORD_4,translator.translate(TEST_NUM_4));
			assertEquals(RET_WORD_5,translator.translate(TEST_NUM_5));
			assertEquals(RET_WORD_6,translator.translate(TEST_NUM_6));
			assertEquals(RET_WORD_7,translator.translate(TEST_NUM_7));
			assertEquals(RET_WORD_8,translator.translate(TEST_NUM_8));		
			assertEquals(RET_WORD_9,translator.translate(TEST_NUM_9));	
		}catch (NumberOutOfBoundsException e) {
			fail("Throws exception.");
		}catch(DecimalOutOfBoundsException e){
			fail("Throws exception.");
		}
	}
	
	
	private double TEST_NUM_1 = 100000000.999;
	private double TEST_NUM_2 = 3.999999;
	private double TEST_NUM_3 = 3.69;
	private String RET_WORD_3 = "three point six nine";
	private double TEST_NUM_4 = 13.19;
	private String RET_WORD_4 = "thirteen point one nine";
	private double TEST_NUM_5 = 213.29;
	private String RET_WORD_5 = "two hundreds and thirteen point two nine";
	private double TEST_NUM_6 = 3113.49;
	private String RET_WORD_6 = "three thousands one hundred and thirteen point four nine";
	private double TEST_NUM_7 = 42113.79;
	private String RET_WORD_7 = "forty two thousands one hundred and thirteen point seven nine";
	private double TEST_NUM_8 = 151113.69;
	private String RET_WORD_8 = "one hundred and fifty one thousands one hundred and thirteen point six nine";
	private double TEST_NUM_9 = -1243.37;
	private String RET_WORD_9 = "minus one thousand two hundreds and forty three point three seven";
	
}
