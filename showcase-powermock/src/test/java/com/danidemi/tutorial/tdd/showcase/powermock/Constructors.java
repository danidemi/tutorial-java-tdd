package com.danidemi.tutorial.tdd.showcase.powermock;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Date.class)
public class Constructors {
		
	@Test
	public void mockConstructor() throws Exception {
		
		// Mock a constructor to make it return what you want.
		// Extremely useful with code using Date.
		
		// given
		// ...a date "0"
		Date myDate = new Date(0);
		// ...when code instantiate a new Date, return the date "0"
		whenNew(Date.class).withNoArguments().thenReturn( myDate );
		
		// when
		long tested = new Date().getTime();
		
		// then
		assertThat( tested, equalTo(0L) );
		
	}	
	
}
