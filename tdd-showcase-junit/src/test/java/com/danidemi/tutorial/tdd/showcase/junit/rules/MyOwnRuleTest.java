package com.danidemi.tutorial.tdd.showcase.junit.rules;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.Description;
public class MyOwnRuleTest {
		
	static class AllowMaxSkips extends org.junit.rules.TestWatcher {
		
		private static int countTests = 0;
		private int maxSkips = 0;
				
		public AllowMaxSkips(int maxSkips) {
			super();
			this.maxSkips = maxSkips;
		}
		
		@Override
		protected void skipped(AssumptionViolatedException e,
				Description description) {
					
			countTests++;
			
			if(countTests > maxSkips){
				throw new AssertionError("Too many skips");
			}
		}
	}
	
	@Rule public AllowMaxSkips maxSkips = new AllowMaxSkips(4);
	
	@Test
	public void notSoLongProcess()  {
		Assume.assumeTrue(false);		
	}
	
	@Test
	public void notSoLongProcess2()  {
		Assume.assumeTrue(false);		
	}
	
	@Test
	public void notSoLongProcess3()  {
		Assume.assumeTrue(false);
	}
	
	@Test
	public void notSoLongProcess4()  {
		Assume.assumeTrue(false);				
	}	
		
}
