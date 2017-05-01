package com.danidemi.tutorial.tdd.showcase.junit.theories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PlusOperatorTheoryTest {

	@DataPoints
	public static int[] positiveIntegers() {
		return new int[] { -1, 1, 10, 1234567, -5986 };
	}

	@Theory
	public void a_plus_b_is_greater_than_a_and_greater_than_b(Integer a, Integer b) {
		assumeTrue(a >0 && b > 0);
		
		assertThat(a+b, is(greaterThan(a)));
		assertThat(a+b, is(greaterThan(b)));
	}
}
