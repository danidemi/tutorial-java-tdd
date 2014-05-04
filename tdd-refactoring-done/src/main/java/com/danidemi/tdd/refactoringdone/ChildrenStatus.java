package com.danidemi.tdd.refactoringdone;

public class ChildrenStatus implements MovieStatus {

	@Override
	public double amount(int daysRented) {
		double thisAmount = 1.5;
		if (daysRented > 3)
			thisAmount += (daysRented - 3) * 1.5;
		return thisAmount;
	}

	@Override
	public int rentalPoint(int daysRented) {
		return 1;
	}

}
