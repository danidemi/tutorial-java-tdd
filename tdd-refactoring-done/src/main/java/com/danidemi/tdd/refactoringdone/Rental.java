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
		
		int daysRented = this.daysRented();
		return this.tape().movie().amount(daysRented);
	}

	int rentalPoints() {
		int pointToAdd = 0;
		
		// add frequent renter points
		pointToAdd++;
		// add bonus for a two day new release rental
		if ((tape().movie().priceCode() == Movie.NEW_RELEASE)
				&& daysRented() > 1)
			pointToAdd++;
		return pointToAdd;
	}

	public String movieName() {
		return tape().movie().name();
	}

}
