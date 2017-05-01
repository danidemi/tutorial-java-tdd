package com.danidemi.tutorial.tdd.showcase.junit.exceptions;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.instanceOf;

import java.text.ParseException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestExceptions {
	
	@Test
	public void doNotParseANumber0() {
		
		try{
			Integer.parseInt("abc");
			fail("Exception expected");
		}catch(NumberFormatException nfe){
			
		}catch(Exception e){
			fail("Wrong Exception");
		}
		
	}
	
	@Test(expected=NumberFormatException.class)
	public void doNotParseANumber1() {
		
		Integer.parseInt("abc");
		
	}
	
	@Test
	public void doNotParseANumber2() {
		
		Exception e = null;
		try{
			Integer.parseInt("abc");
		}catch(Exception ee){
			e = ee;
		}
		
		assertThat( e, instanceOf(NumberFormatException.class));
		
	}	
	
	@Rule public ExpectedException asError = ExpectedException.none();
	
	@Test
	public void doNotParseANumber3() {

		asError.expect(NumberFormatException.class);
		Integer.parseInt("abc");
		
	}	
	
}
