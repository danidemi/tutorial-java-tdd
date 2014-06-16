package com.danidemi.tdd.refactoring;

public class ChildrensPrice extends Price {

	@Override
	int priceCode() {
		return Movie.CHILDRENS;
	}

	@Override
	public double charge(int daysRented) {
		double result = 0;
        result += 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
	}

}
