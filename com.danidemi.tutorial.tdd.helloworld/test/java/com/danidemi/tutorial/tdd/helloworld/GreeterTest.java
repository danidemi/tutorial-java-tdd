package com.danidemi.tutorial.tdd.helloworld;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreeterTest {

	@Test
	public void shouldSayHelloToTheWorld() {
		Greeter greeter = new Greeter();
		String hello = greeter.sayHello();
		assertEquals("Hello World!", hello);
	}

}
