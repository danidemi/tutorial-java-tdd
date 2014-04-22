package com.danidemi.tutorial.tdd.showcase.mockito;

import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.argThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertThat;

public class VerifyShowcase {

	@Test 
	public void verifyExactInvocation() {
		
		List myList = mock(List.class);
		
		myList.add("Hello");
		myList.add("World");
		
		verify(myList).add("World");
		verify(myList).add("Hello");
		
	}
	
	@Test 
	public void verifyInvocationWihtMatchers() {
		
		List myList = mock(List.class);
		
		myList.add("Hello");
		
		verify(myList).add(argThat(equalToIgnoringCase("HELLO")));
		
	}	
	
	@Test public void verifyMultipleInvocations() {
		
		List myList = mock(List.class);
		
		myList.add("Hello");
		myList.add("Hello");
		myList.add("Hello");
		
		verify(myList, times(3)).add("Hello");
		
		List myList2 = mock(List.class);
		
		myList2.add("Hello");
		myList2.add("Hello");
		myList2.add("Hello");
		
		verify(myList, atLeastOnce()).add("Hello");
		
		List myList3 = mock(List.class);
		
		myList3.add("Hello");
		myList3.add("Hello");
		myList3.add("Hello");
		
		verify(myList, atMost(10)).add("Hello");		
		
		
	}
	
	@Test public void verifyTimeout() {
		
		final List myList = mock(List.class);
		
		Thread thread = new Thread( new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				myList.add("Hello");
			}
		} );
		thread.start();
		
		verify(myList, timeout(3000)).add("Hello");
		
	}
	
	@Test public void verifyInOrder() {
		
		final List myList = mock(List.class);
		
		myList.add("Hello");
		myList.add("World");
		
		InOrder inOrder = inOrder(myList);
		inOrder.verify(myList).add("Hello");
		inOrder.verify(myList).add("World");
		
	}
	
	@Test public void verifyComplexArgument() {
		
		final List myList = mock(List.class);
		ArgumentCaptor<String> stringArg = ArgumentCaptor.forClass(String.class);
		
		myList.add("Hello");
		myList.add("World");
		
		verify(myList, times(2)).add( stringArg.capture() );
		
		List<String> allValues = stringArg.getAllValues();
		
		assertThat( allValues.get(0), equalTo("Hello") );
		assertThat( allValues.get(1), equalTo("World") );
		
	}
	

	
}
