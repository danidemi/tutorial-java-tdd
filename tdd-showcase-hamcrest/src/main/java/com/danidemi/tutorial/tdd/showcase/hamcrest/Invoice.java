package com.danidemi.tutorial.tdd.showcase.hamcrest;

public class Invoice {
	
	private InvoiceStatus status;
	private Long number;
	private long amount;
	
	public Invoice(Long number, long amount) {
		super();
		this.number = number;
		this.amount = amount;
		this.status = new WaitingPayementStatus(this);
	}

	public void pay(){
		status.pay();
	}
	
	public boolean hasBeenPayed() {
		return status.hasBeenPayed();
	}

	void transitionTo(InvoiceStatus nextStatus) {
		this.status = nextStatus;
	}
	
	@Override
	public String toString() {
		return "Invoice #" + number;
	}
	
}
