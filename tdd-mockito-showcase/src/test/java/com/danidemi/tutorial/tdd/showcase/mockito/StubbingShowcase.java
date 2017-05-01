package com.danidemi.tutorial.tdd.showcase.mockito;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class StubbingShowcase {
	
	public @Rule ExpectedException asError = ExpectedException.none();

	@Test
	public void stubAnInterface() {

		Comparator<String> comparator = mock(Comparator.class);
		
		int compare = comparator.compare("s1", "s2");
		
		assertThat( compare, equalTo(0) );
		
	}
	
	@Test
	public void stubANotVoidMethod() {

		Comparator<String> comparator = mock(Comparator.class);
		
		when(comparator.compare("s1", "s2")).thenReturn(1);
		assertThat( comparator.compare("s1", "s2"), equalTo(1) );
		
		when(comparator.compare("s2", "s1")).thenReturn(-1);
		assertThat( comparator.compare("s2", "s1"), equalTo(-1) );
		
		assertThat( comparator.compare("k", "K"), equalTo(0) );
		
	}	
	
	@Test
	public void stubThrowingAnException() {
		
		asError.expect(IllegalArgumentException.class);
		
		Comparator<String> comparator = mock(Comparator.class);
		when(comparator.compare(any(String.class), any(String.class))).thenThrow( new IllegalArgumentException() );
		
		comparator.compare("a", "b");
		
	}
	
	@Test
	public void stubbingProvidingAComplexBehaviour() {
		
		Comparator<String> comparator = mock(Comparator.class);
		when(comparator.compare(any(String.class), any(String.class))).then(new Answer<Integer>() {

			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				return ((String)arguments[0]).length() + ((String)arguments[1]).length(); 
			}
		});
		
		assertThat( comparator.compare("Hello", "World"), equalTo(10) );
		
	}
	
	@Test
	public void stubVoidMethod() {
		
		SimpleDateFormat mock = mock( SimpleDateFormat.class );
		
		doThrow( new IllegalArgumentException() )
		.when( mock )
		.setLenient(any(Boolean.class));
		
		asError.expect(IllegalArgumentException.class);
		
		mock.setLenient(false);
		
	}
		
}
