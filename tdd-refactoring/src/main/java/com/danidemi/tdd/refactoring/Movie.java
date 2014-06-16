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

	private Price priceCode;

	public Movie(String name) {
		super(name);
	}

	public static Movie newNewRelease(String name){
		Movie movie = new Movie(name);
		movie.beNewRelease();
        return movie;
    }
    public static Movie newRegular(String name){
    	Movie movie = new Movie(name);
		movie.beRegular();
        return movie;
    }
    public static Movie newChildrens(String name) {
    	Movie movie = new Movie(name);
		movie.beChildrens();
        return movie;
    }

	public int priceCode() {
		return priceCode.priceCode();
	}

	public void persist() {
		Registrar.add("Movies", this);
	}

	public static Movie get(String name) {
		return (Movie) Registrar.get("Movies", name);
	}
	
	public double charge(int daysRented) {
		return priceCode.charge(daysRented);
    }
	
	public int frequentRenterPoints(int daysRented) {
        if ((priceCode() == Movie.NEW_RELEASE) && daysRented > 1) return 2;
        else return 1;
    }
	
	public void beRegular() {
        priceCode = Price.regular();
    }
    
    public void beNewRelease() {
        priceCode = Price.newRelease();
    }
    
    public void beChildrens() {
        priceCode = Price.childrens();
    }
}