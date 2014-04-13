package com.danidemi.tutorial.tdd.web.view;

import org.junit.Test;

import com.danidemi.tutorial.tdd.web.view.FlashMessage.Priority;


public class FlashMessageTest {

	@Test(expected=IllegalArgumentException.class)
	public void messageCannotBeNull() {
		
		// when
		new FlashMessage(Priority.INFO, null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void messageCannotBeEmpty() {
		
		// when
		new FlashMessage(Priority.WARNING, "");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void messageCannotBeBlank() {
		
		// when
		new FlashMessage(Priority.ERROR, "     ");
		
	}		
	
}
