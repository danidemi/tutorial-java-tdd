package com.danidemi.tdd.refactoring;

public class NewReleasePrice extends Price {

	@Override
	int priceCode() {
		return Movie.NEW_RELEASE;
	}

	@Override
	public double charge(int daysRented) {
        return daysRented * 3;
	}

}
