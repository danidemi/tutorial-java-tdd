package com.danidemi.tdd.refactoringdone;

public class JustReleasedStatus implements MovieStatus {

	@Override
	public double amount(int daysRented) {
		return daysRented * 3;
	}

	@Override
	public int rentalPoint(int daysRented) {
		return daysRented > 1 ? 2 : 1;
	}

}
