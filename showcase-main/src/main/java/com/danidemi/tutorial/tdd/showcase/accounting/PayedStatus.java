package com.danidemi.tutorial.tdd.showcase.accounting;

public class PayedStatus implements InvoiceStatus {

	private Invoice master;

	public PayedStatus(Invoice master) {
		this.master = master;
	}

	@Override
	public void pay() {
		throw new IllegalStateException("Already payed");
	}

	@Override
	public boolean hasBeenPayed() {
		return true;
	}

}
