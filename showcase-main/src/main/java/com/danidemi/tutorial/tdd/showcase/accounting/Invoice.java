package com.danidemi.tutorial.tdd.showcase.accounting;

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

	public int k(String param) {
		this.noArgPrivate();
		return this.oneArgPrivate(param);
	}

	private int oneArgPrivate(String string) {
		return 0;
	}
	
	private void noArgPrivate() {
		
	}	
	
}
