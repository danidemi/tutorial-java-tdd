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
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentalsIt = rentals.iterator();
		String result = "Rental Record for " + name() + "\n";
		while (rentalsIt.hasNext()) {
			double thisAmount = 0;
			Rental each = rentalsIt.next();

			// determine amounts for each line
			switch (each.tape().movie().priceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (each.daysRented() > 2)
					thisAmount += (each.daysRented() - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.daysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (each.daysRented() > 3)
					thisAmount += (each.daysRented() - 3) * 1.5;
				break;

			}
			totalAmount += thisAmount;

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.tape().movie().priceCode() == Movie.NEW_RELEASE)
					&& each.daysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			result += "\t" + each.tape().movie().name() + "\t"
					+ String.valueOf(thisAmount) + "\n";

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;

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


}