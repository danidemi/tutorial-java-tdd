package com.danidemi.tutorial.tdd.showcase.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.Matchers.instanceOf;
public class ExceptionRuleTest {

	@Rule public ExpectedException asError = ExpectedException.none();
	
	@Test
	public void expectAnNumberFormatException()  {
		
		asError.expect(NumberFormatException.class);
		Integer.parseInt("xyz");
		
	}
	
	@Test
	public void expectNothing() {
		
		Integer.parseInt("100");
		
	}
	
	@Test
	public void expectARuntimeException() {
		
		asError.expect(instanceOf(RuntimeException.class));
		
		Integer.parseInt("100a");
		
	}
	
}
