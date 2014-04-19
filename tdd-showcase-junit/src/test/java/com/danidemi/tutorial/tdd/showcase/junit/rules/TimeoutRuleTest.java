package com.danidemi.tutorial.tdd.showcase.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
public class TimeoutRuleTest {

	@Rule public Timeout timeout = new Timeout(500);
	
	@Test
	public void notSoLongProcess()  {
		
		long numberOfTerms = 4; 
		//long numberOfTerms = Long.MAX_VALUE;
		long total = 0;
		
		for(long i = 0; i< numberOfTerms; i++){
			total = total + (i%2 == 0 ? i : -i);
			Thread.yield();
		}
		
	}
		
}
