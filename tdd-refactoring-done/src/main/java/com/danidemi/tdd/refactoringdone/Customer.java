package com.danidemi.tdd.refactoringdone;

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
		
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + name() + "\n";
	
		for (Rental rental : rentals) {
			totalAmount += rental.amount();
			frequentRenterPoints += rental.rentalPoints(); 

			// show figures for this rental
			result += "\t" + rental.movieName() + "\t"
					+ String.valueOf(rental.amount()) + "\n";

		}
		
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;

	}
	
	public String htmlStatement() {
		
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "<P>Rental Record for " + name() + "\n";
	
		for (Rental rental : rentals) {
			totalAmount += rental.amount();
			frequentRenterPoints += rental.rentalPoints(); 

			// show figures for this rental
			result += "\t" + rental.movieName() + "\t"
					+ String.valueOf(rental.amount()) + "\n";

		}
		
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points</P>";
		return result;

	}

	public void addRental(Rental arg) {
		rentals.add(arg);
	}

	public static Customer get(String name) {
		return (Customer) Registrar.get("Customers", name);
	}

	public void persist() {
		Registrar.add("Customers", this);
	}


}