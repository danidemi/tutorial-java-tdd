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
	
	private static final int CHILDRENS = 2;
	private static final int REGULAR = 0;
	private static final int NEW_RELEASE = 1;
	
	private MovieStatus status;
	

	private Movie(String name, MovieStatus initialStatus) {
		super(name);
		this.status = initialStatus;
	}
	
	public static Movie newChildrenMovie(String name) {
		return new Movie(name, new ChildrenStatus());
	}
	
	public static Movie newRegularMovie(String name) {
		return new Movie(name, new RegularStatus());
	}
	
	public static Movie newJustReleasedMovie(String name) {
		return new Movie(name, new JustReleasedStatus());
	}	
	
	public void beForChildren() {
		this.status = new ChildrenStatus();
	}
	
	public void beRegular() {
		this.status = new RegularStatus();
	}	
	
	public void persist() {
		Registrar.add("Movies", this);
	}

	double amount(int daysRented) {
		return status.amount(daysRented);
	}
	
//	double amount(int daysRented) {
//		double thisAmount = 0;
//		switch (priceCode()) {
//		case Movie.REGULAR:
//			thisAmount += 2;
//			if (daysRented > 2)
//				thisAmount += (daysRented - 2) * 1.5;
//			break;
//		case Movie.NEW_RELEASE:
//			thisAmount += daysRented * 3;
//			break;
//		case Movie.CHILDRENS:
//			thisAmount += 1.5;
//			if (daysRented > 3)
//				thisAmount += (daysRented - 3) * 1.5;
//			break;
//		}
//		return thisAmount;
//	}

	int rentalPoint(int daysRented) {
		return this.status.rentalPoint(daysRented);
	}
	
//	int rentalPoint(int daysRented) {
//		int pointToAdd = 0;
//		
//		// add frequent renter points
//		pointToAdd++;
//		// add bonus for a two day new release rental
//		if ((priceCode() == Movie.NEW_RELEASE)
//				&& daysRented > 1)
//			pointToAdd++;
//		return pointToAdd;
//	}

	public static Movie get(String name) {
		return (Movie) Registrar.get("Movies", name);
	}
	
}