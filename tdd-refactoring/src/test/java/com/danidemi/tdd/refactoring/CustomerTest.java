package com.danidemi.tdd.refactoring;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void statementShouldReportCompleteInfo() {
		
		// given
		Rental rental = mockARental(Movie.REGULAR, "The Empire Strikes Back", 2);
		
		// when
		Customer customer = new Customer("John");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for John\n\tThe Empire Strikes Back\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points"));
		
	}
	
	@Test
	public void statementShouldReportCompleteInfo2() {
		
		// given
		Rental rental = mockARental(Movie.NEW_RELEASE, "The Empire Strikes Back", 2);
		
		// when
		Customer customer = new Customer("Mark");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for Mark\n\tThe Empire Strikes Back\t6.0\nAmount owed is 6.0\nYou earned 2 frequent renter points"));
		
	}
	
	private Rental mockARental(int type, String title, int daysRented) {
		Rental rental = mock(Rental.class, RETURNS_DEEP_STUBS);
		when(rental.tape().movie().priceCode()).thenReturn(type);
		when(rental.tape().movie().name()).thenReturn(title);
		when(rental.daysRented()).thenReturn(daysRented);
		return rental;
	}	
	
}
