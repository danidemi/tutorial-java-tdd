package com.danidemi.tdd.refactoring;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

class Customer extends DomainObject {
	
	private List<Rental> rentals = new ArrayList<>();	
	
	public Customer(String name) {
		super(name);
	}

	public String statement() {
		Iterator<Rental> rentalsIt = rentals.iterator();
		String result = "Rental Record for " + name() + "\n";
		while (rentalsIt.hasNext()) {
			Rental each = rentalsIt.next();

			// show figures for this rental
			result += "\t" + each.tape().movie().name() + "\t"
					+ String.valueOf(each.charge()) + "\n";

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(charge()) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints())
				+ " frequent renter points";
		return result;

	}
	
	public String htmlStatement() {
		StringBuilder buffer = new StringBuilder("<h1>Rental Record for <b>" + name() + "</b></h1>\n");
		for (Rental rental : rentals) {
			buffer.append("\t<p>" + rental.tape().movie().name() + "\t"
					+ String.valueOf(rental.charge()) + "</p>\n");
		}
		buffer.append("<p>Amount owed is " + String.valueOf(charge()) + "</p>\n");
		buffer.append("<p>You earned " + String.valueOf(frequentRenterPoints())
				+ " frequent renter points</p>");
		return buffer.toString();
	}

	public void addRental(Rental arg) {
		if(arg.tape().movie().priceCode() < 0 || arg.tape().movie().priceCode() > 3){
			throw new IllegalArgumentException("Wrong movie price code");
		}
		rentals.add(arg);
	}

	public static Customer get(String name) {
		return (Customer) Registrar.get("Customers", name);
	}

	public void persist() {
		Registrar.add("Customers", this);
	}
	
	private double charge(){
        double result = 0;
        for (Rental rental : rentals) {
        	result += rental.charge();
		}
        return result;
    }
	
	private int frequentRenterPoints() {
		int result = 0;
		for (Rental rental : rentals) {
			result += rental.frequentRenterPoints();
		}
		return result;
	}
	
}