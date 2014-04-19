package com.danidemi.tutorial.tdd.showcase.junit.matchers;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.StringContains;
import org.junit.Test;


public class HamcrestShowcaseOnStrings {
		
	@Test
	public void testEndsWith() {
		assertThat( "www.domain.com", endsWith(".com"));
	}
	
	@Test
	public void testContainsString() {
		assertThat( "People", StringContains.containsString("op"));
	}
	
	@Test
	public void testIsEmptyOrNullString() {
		assertThat( "", isEmptyOrNullString() );
		assertThat( null, isEmptyOrNullString() );
	}
	
	@Test
	public void testIsEmptyString() {
		assertThat( "", isEmptyString() );
	}
	
	@Test 
	public void testStartsWith() {
		assertThat( 
				"+3902112233",  
				startsWith("+39")
		);
	}
	
}
