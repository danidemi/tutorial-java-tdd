package com.danidemi.tutorial.tdd.coverage;

import org.junit.Test;

public class IllusionTest {

	@Test
	public void test1() {
		new Illusion().illude(2, 3);
	}
	
	@Test
	public void test2() {
		new Illusion().illude(-2, -3);
	}	

}
