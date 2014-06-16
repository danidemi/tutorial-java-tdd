package com.danidemi.tdd.refactoring;

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
	
	public int frequentRenterPoints() {
        return tape().movie().frequentRenterPoints(daysRented);
    }
	
	public double charge() {
        return tape.movie().charge(daysRented);
    }

}
