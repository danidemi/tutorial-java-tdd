package com.danidemi.tdd.refactoring;

public class RegularPrice extends Price {

	@Override
	int priceCode() {
		return Movie.REGULAR;
	}
	
	@Override
	public double charge(int daysRented) {
		double result = 0;
        result += 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
	}

}
