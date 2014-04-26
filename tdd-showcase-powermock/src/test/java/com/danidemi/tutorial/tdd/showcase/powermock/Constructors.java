package com.danidemi.tutorial.tdd.showcase.powermock;

import static org.junit.Assert.*;

import java.util.Date;

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
@PrepareForTest(Date.class)
public class Constructors {
	
	@Test
	public void mockConstructor() throws Exception {
		
		// given
		Date mockDate = PowerMockito.mock(Date.class);
		PowerMockito.whenNew(Date.class).withNoArguments().thenReturn( mockDate );
		
		when(mockDate.getTime()).thenReturn(0L);

		// when
		long tested = new Date().getTime();
		
		// then
		assertThat( tested, equalTo(0L) );
		
	}
	
}
