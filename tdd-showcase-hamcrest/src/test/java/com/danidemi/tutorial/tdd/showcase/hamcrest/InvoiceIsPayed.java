package com.danidemi.tutorial.tdd.showcase.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.danidemi.tutorial.tdd.showcase.accounting.Invoice;

class InvoiceIsPayed extends TypeSafeMatcher<Invoice> {

	@Override
	public void describeTo(Description description) {
		description.appendText("supposed to be payed");
	}

	@Override
	protected boolean matchesSafely(Invoice invoice) {
		return invoice.hasBeenPayed();
	}
	
	
	
}