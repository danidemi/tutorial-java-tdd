package com.danidemi.tutorial.tdd.showcase.powermock;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.danidemi.tutorial.tdd.showcase.accounting.Invoice;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Invoice.class)
public class PrivateMethods {

	@Test(expected=IllegalArgumentException.class)
	public void mockPrivateMethodWithArgs() throws Exception {
				
		// Given
		// ...a spy on the Invoice
		Invoice invoice = PowerMockito.spy( new Invoice(10L, 30) );
		
		// ...it is possible to "partially" mock just the private method "oneArgPrivate" to make it
		// throw an exception when it is invoked with "ole" parameter.
		when(invoice, "oneArgPrivate", "ole").thenThrow(new IllegalArgumentException());
		
		
		
		// When
		invoice.k("ole");
		
		
		
		// Then
		PowerMockito.verifyPrivate(invoice).invoke( Invoice.class.getMethod("z", (Class[])null ) ).withNoArguments();
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void mockPrivateMethodWithoutArgs() throws Exception {
				
		// Given
		// ...a spy on the Invoice
		Invoice invoice = PowerMockito.spy( new Invoice(10L, 30) );
		
		// ...it is possible to "partially" mock just the private method "noArgPrivate" to make it
		// throw an exception when it is invoked. Its invocation is somewhat cumbersome becasue we need to specify
		// 'no args' with an empty array.
		when(invoice, "noArgPrivate", new Object[]{}).thenThrow(new IllegalArgumentException());
		
		// When
		invoice.k("test");
		
		
		PowerMockito.verifyPrivate(invoice).invoke( Invoice.class.getMethod("z", (Class[])null ) ).withNoArguments();
		
	}
	
	@Test
	public void stubbingPrivateMethods() throws Exception {
		
		// Given
		// ...a spy on the Invoice
		Invoice invoice = PowerMockito.spy( new Invoice(10L, 30) );
		
		
		when(invoice, method(Invoice.class, "oneArgPrivate", String.class ))
		.withArguments("test1")
		.thenReturn(1234567);
		
		
		when(invoice, method(Invoice.class, "oneArgPrivate", String.class ))
		.withArguments("test2")
		.thenReturn(123);
		
		// When
		int k1 = invoice.k("test1");
		int k2 = invoice.k("test2");
		
		// Then
		assertThat( k1, is(1234567) );
		assertThat( k2, is(123) );
		
	}
	

	@Test
	public void stubbingPrivateMethodsWithMatchers() throws Exception {
		
		// Given
		// ...a spy on the Invoice
		Invoice invoice = PowerMockito.spy( new Invoice(10L, 30) );
		
		
		when(invoice, method(Invoice.class, "oneArgPrivate", String.class ))
		.withArguments( argThat( either(is("ok")).or(is("ko")) ) )
		.thenReturn(34);
				
		// When
		int k1 = invoice.k("ko");
		int k2 = invoice.k("ok");
		int k3 = invoice.k("kk");
		
		// Then
		assertThat( k1, is(34) );
		assertThat( k2, is(34) );
		assertThat( k3, is(0) );
		
	}

	
}
