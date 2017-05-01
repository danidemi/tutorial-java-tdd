package com.danidemi.tutorial.tdd.showcase.hamcrest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.danidemi.tutorial.tdd.showcase.accounting.Invoice;

import static com.danidemi.tutorial.tdd.showcase.hamcrest.InvoiceMatchers.*;


public class InvoiceTest {
	
	@Test
	public void newInvoiceShouldNotBePayed() {
		
		// given
		// ...a new invoice
		Invoice tested = new Invoice(12L, 3400);
		
		// then
		// ...it should not be payed
		assertThat(tested, not( payed() ));
		
	}
	
	@Test
	public void payedInvoiceShouldBePayed() {
		
		// given
		// ...a new invoice
		Invoice tested = new Invoice(12L, 3400);
		
		// when
		tested.pay();
		
		// then
		// ...it should not be payed
		assertThat(tested, payed());
		
	}	
	
	@Test
	public void shouldRedefineToString() {

		
		// given
		// ...a new invoice
		Invoice tested = new Invoice(12L, 3400);
				
		// then
		// ...it should not be payed
		assertThat(tested, redefineToString());
		
	}
	
	
	
}
