package com.danidemi.tutorial.tdd.showcase.junit.matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.array;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Test;


public class HamcrestShowcaseOnCollectionsAndArrays {
	
	// arrays, list, collections
	@Test public void testCollectionsAndArrays() {
		
		Integer fibonacci[] = new Integer[] { 1, 1, 2, 3, 5, 8 };
		
		assertThat(fibonacci, array( is(1), is(1), is(2), is(3), is(5), is(8)) );
		
		assertThat(fibonacci, arrayContaining( is(1), is(1), is(2), is(3), is(5), is(8)) );
		
		assertThat(fibonacci, arrayContainingInAnyOrder( is(1), is(8), is(1), is(3), is(5), is(2)) );
		
		assertThat(fibonacci, arrayWithSize(6) );
		
		assertThat(fibonacci, arrayWithSize( greaterThan(3) ) );
		
		
		assertThat(Arrays.asList( fibonacci ), everyItem(greaterThan(0)));
		
		assertThat(Arrays.asList( fibonacci ), hasItem(equalTo(2)));
		
		assertThat(new String[]{}, emptyArray());
		
		assertThat(Arrays.asList( new String[]{} ), emptyCollectionOf(String.class));
		
	}
		
}
