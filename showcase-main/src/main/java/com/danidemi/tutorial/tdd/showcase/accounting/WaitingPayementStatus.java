package com.danidemi.tutorial.tdd.showcase.accounting;

public class WaitingPayementStatus implements InvoiceStatus {

	private Invoice master;

	public WaitingPayementStatus(Invoice invoice) {
		this.master = invoice;
	}

	@Override
	public void pay() {
		master.transitionTo( new PayedStatus(master) );
	}

	@Override
	public boolean hasBeenPayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
