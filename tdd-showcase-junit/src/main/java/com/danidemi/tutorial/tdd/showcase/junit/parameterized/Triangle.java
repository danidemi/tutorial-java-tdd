package com.danidemi.tutorial.tdd.showcase.junit.parameterized;


class Triangle {

	Triangle( long side1, long side2, long side3 ){
		
		long min = Math.min( Math.min(side1, side2), side3);
		if(min<=0) throw new NotATriangleException();
		
		long max = Math.max( Math.max(side1, side2), side3);
		long sum = side1 + side2 + side3;
		if(max > (sum - max)) throw new NotATriangleException();  
	}
}