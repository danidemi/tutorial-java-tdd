package com.danidemi.tutorial.tdd.showcase.junit.parameterized;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TriangleTest {
	
	public @Rule ExpectedException asError = ExpectedException.none();
	
	@Parameters(name="Case: {index}, not expected to be a triangle with sides {0}, {1}, {2}")
	public static Iterable<Object[]> parameters() {
		return Arrays.asList(
			new Object[]{10, 7, 7},
			new Object[]{7, 10, 7},
			new Object[]{7, 7, 10}
		);
	}

	private long side1;
	private long side2;
	private long side3;
	
	public TriangleTest( long side1, long side2, long side3 ) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	@Test
	public void validTriangle() {
		
		try{
			new Triangle(side1, side2, side3);
		}catch(NotATriangleException nate){
			fail();
		}
			
	}

}
