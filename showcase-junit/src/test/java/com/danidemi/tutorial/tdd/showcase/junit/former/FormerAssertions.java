package com.danidemi.tutorial.tdd.showcase.junit.former;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormerAssertions {

	@Test
	public void test() {
		
		assertEquals(Math.PI, 3.14, 0.01);
		assertArrayEquals("Hello".getBytes(), new byte[]{72, 101, 108, 108, 111});
		
		int dividend = 3;
		assertTrue( (12 % dividend) == 0 );
		
		String helloWorldInCapital = "Hello World";
		String helloWorld = "hello world";
		assertNotEquals(helloWorldInCapital, helloWorld);
		assertSame( "Hello World", helloWorldInCapital );

	}

}
