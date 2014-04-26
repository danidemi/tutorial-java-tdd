package com.danidemi.tutorial.tdd.showcase.powermock;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isNull;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(String.class)
public class StaticMethods {
	
	@Test
	public void doNotMockAtAll() {
		
		// when
		String resultFromStatic = String.valueOf( new Double(123.43) );

		// then
		// ...the class with static methods is not mocked at all,
		// invocations are obviously taken in charge by the class itself.
		assertThat( resultFromStatic, equalTo("123.43") );
		assertThat( String.format("'%s'", "Hello World"), is("'Hello World'"));
	}	

	@Test
	public void staticMockingAClassMeansMockingAllOfItsStaticMethods() {
		
		// given
		PowerMockito.mockStatic(String.class);
		when(String.valueOf(any(Double.class))).thenReturn("oh");
		
		// when
		String resultFromMockedStatic = String.valueOf( new Double(123.43) );
		
		// then
		// ...all static methods are mocked. This means that as it happens with Mockito,
		// other static methods will return the default value depending on the type, e.g.
		// false for boolean, null for Object, 0 for numbers, etc...
		assertThat( resultFromMockedStatic, equalTo("oh") );
		assertThat( String.format("'%s'", "Hello World"), nullValue());
		
	}
	
	@Test public void spyingAllowsToStubJustOneMethod() {
		
		// given
		PowerMockito.spy(String.class);
		when(String.valueOf(any(Double.class))).thenReturn("oh");
		
		// when
		String resultFromMockedStatic = String.valueOf( new Double(123.43) );
		
		// then
		// ...just the stubbed static method changes
		assertThat( resultFromMockedStatic, equalTo("oh") );
		assertThat( String.format("'%s'", "Hello World"), is("'Hello World'"));		
		
	}

}
