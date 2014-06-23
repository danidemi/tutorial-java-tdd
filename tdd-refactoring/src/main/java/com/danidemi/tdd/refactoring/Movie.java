package com.danidemi.tdd.refactoring;

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

	public Movie(String name, int priceCode) {
		super(name);
		this.priceCode = priceCode;
	}

	public int priceCode() {
		return priceCode;
	}

	public void persist() {
		Registrar.add("Movies", this);
	}

	public static Movie get(String name) {
		return (Movie) Registrar.get("Movies", name);
	}
}