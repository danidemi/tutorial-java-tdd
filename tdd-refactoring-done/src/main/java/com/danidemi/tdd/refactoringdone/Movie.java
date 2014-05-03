package com.danidemi.tdd.refactoringdone;

/**
 * Movie represents the notion of a film. A video store might have several tapes
 * in stock of the same movie. The movie uses a class called a registrar (not
 * shown) as a class to hold instances of movie. I also do this with other
 * classes. I use the message persist to tell an object to save itself to the
 * registrar. I can then retrieve the object, based on its name, with a
 * get(String) method.
 */
public class Movie extends DomainObject {
	
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private int priceCode;

	private Movie(String name, int priceCode) {
		super(name);
		this.priceCode = priceCode;
	}
	
	public static Movie newChildrenMovie(String name) {
		return new Movie(name, CHILDRENS);
	}
	
	public static Movie newRegularMovie(String name) {
		return new Movie(name, REGULAR);
	}
	
	public static Movie newJustReleasedMovie(String name) {
		return new Movie(name, NEW_RELEASE);
	}	
	
	public void beForChildren() {
		this.priceCode = CHILDRENS;
	}
	
	public void beRegular() {
		this.priceCode = REGULAR;
	}	
	
	public int priceCode() {
		return priceCode;
	}

	public void persist() {
		Registrar.add("Movies", this);
	}

	double amount(int daysRented) {
		double thisAmount = 0;
		switch (priceCode()) {
		case Movie.REGULAR:
			thisAmount += 2;
			if (daysRented > 2)
				thisAmount += (daysRented - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			thisAmount += daysRented * 3;
			break;
		case Movie.CHILDRENS:
			thisAmount += 1.5;
			if (daysRented > 3)
				thisAmount += (daysRented - 3) * 1.5;
			break;
		}
		return thisAmount;
	}

	public static Movie get(String name) {
		return (Movie) Registrar.get("Movies", name);
	}
	
}