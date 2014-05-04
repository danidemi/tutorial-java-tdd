package com.danidemi.tdd.refactoringdone;

/**
 * The rental class represents a customer renting a movie.
 */
class Rental extends DomainObject {
	
	private Tape tape;
	private int daysRented;

	public Rental(Tape tape, int daysRented) {
		this.tape = tape;
		this.daysRented = daysRented;
	}
	
	public int daysRented() {
		return daysRented;
	}

	public Tape tape() {
		return tape;
	}

	double amount() {
		return this.tape().movie().amount(this.daysRented());
	}

	int rentalPoints() {
		return tape().movie().rentalPoint(this.daysRented());
	}

	public String movieName() {
		return tape().movie().name();
	}

}
