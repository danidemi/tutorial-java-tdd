package com.danidemi.tutorial.tdd.showcase.powermock;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.danidemi.tutorial.tdd.showcase.accounting.Invoice;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Invoice.class)
public class PrivateMethods {

	@Test(expected=IllegalArgumentException.class)
	public void test() throws Exception {
		
		Method[] methods = Invoice.class.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName() + "(" + Arrays.asList( method.getParameterTypes() ) + ")");
		}
		
		Invoice invoice = PowerMockito.spy( new Invoice(10L, 30) );
		PowerMockito.when(invoice, "z", "ole").thenThrow(new IllegalArgumentException());
		
		invoice.k();
		
		
		PowerMockito.verifyPrivate(invoice).invoke( Invoice.class.getMethod("z", (Class[])null ) ).withNoArguments();
		
	}

}
