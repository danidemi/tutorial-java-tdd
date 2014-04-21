package com.danidemi.tutorial.tdd.showcase.hamcrest;

public abstract class InvoiceMatchers {
	
	public static final InvoiceIsPayed payed(){
		return new InvoiceIsPayed();
	}
	
	public static final RedefineToString redefineToString() {
		return new RedefineToString();
	}
}
