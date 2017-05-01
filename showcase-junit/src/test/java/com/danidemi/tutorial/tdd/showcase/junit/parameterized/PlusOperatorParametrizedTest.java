package com.danidemi.tutorial.tdd.showcase.junit.parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PlusOperatorParametrizedTest {
	
	@Parameters(name="Case: {index}, a={0}, b={1}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(
				new Object[]{1, 1},
				new Object[]{46, 10},
				new Object[]{23, 99}
			);
	}
	
	private Integer a;
	private Integer b;
	
	public PlusOperatorParametrizedTest(Integer a, Integer b) {
		this.a = a;
		this.b = b;
	}
	
	@Test
	public void a_plus_b_is_greater_than_a_and_greater_than_b() {
		assertThat(a+b, is(greaterThan(a)));
		assertThat(a+b, is(greaterThan(b)));
	}
}
