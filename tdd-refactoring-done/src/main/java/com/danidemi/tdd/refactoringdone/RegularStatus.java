package com.danidemi.tdd.refactoringdone;

public class RegularStatus implements MovieStatus {

	@Override
	public double amount(int daysRented) {
		double thisAmount = 2;
		if (daysRented > 2)
			thisAmount += (daysRented - 2) * 1.5;
		return thisAmount;
	}

	@Override
	public int rentalPoint(int daysRented) {
		return 1;
	}

}
