package com.danidemi.tdd.refactoring;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;



public class CustomerTest {
	
	@Rule public ExpectedException asException = ExpectedException.none(); 

	@Test
	public void testRegularFor3Days() {
		
		// given
		Rental rental = mockARental(Movie.REGULAR, "The Empire Strikes Back", 3);
		
		// when
		Customer customer = new Customer("John");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for John\n\tThe Empire Strikes Back\t3.5\nAmount owed is 3.5\nYou earned 1 frequent renter points"));
		
	}
	
	@Test
	public void testRegularFor2Days() {
		
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
	public void testRegularFor1Days() {
		
		// given
		Rental rental = mockARental(Movie.REGULAR, "The Empire Strikes Back", 1);
		
		// when
		Customer customer = new Customer("John");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for John\n\tThe Empire Strikes Back\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points"));
		
	}	
	
	@Test
	public void testLongNewRelease() {
		
		// given
		Rental rental = mockARental(Movie.NEW_RELEASE, "The Empire Strikes Back", 2);
		
		// when
		Customer customer = new Customer("Mark");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for Mark\n\tThe Empire Strikes Back\t6.0\nAmount owed is 6.0\nYou earned 2 frequent renter points"));
		
	}
	
	@Test
	public void testShortNewRelease() {
		
		// given
		Rental rental = mockARental(Movie.NEW_RELEASE, "The Empire Strikes Back", 1);
		
		// when
		Customer customer = new Customer("Mark");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for Mark\n\tThe Empire Strikes Back\t3.0\nAmount owed is 3.0\nYou earned 1 frequent renter points"));
		
	}	
	
	@Test
	public void testShortChildren() {
		
		// given
		Rental rental = mockARental(Movie.CHILDRENS, "Whitesnow", 2);
		
		// when
		Customer customer = new Customer("Mark");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for Mark\n\tWhitesnow\t1.5\nAmount owed is 1.5\nYou earned 1 frequent renter points"));
		
	}
	
	@Test
	public void testLongChildren() {
		
		// given
		Rental rental = mockARental(Movie.CHILDRENS, "Madagascar", 4);
		
		// when
		Customer customer = new Customer("Mark");
		customer.addRental( rental );
		String statement = customer.statement();
		
		// then
		assertThat( statement, equalTo("Rental Record for Mark\n\tMadagascar\t3.0\nAmount owed is 3.0\nYou earned 1 frequent renter points"));
		
	}
	
	@Test
	public void shouldNotAcceptIllegalCategory() {
		
		// given
		Rental rental = mockARental(Integer.MAX_VALUE, "Madagascar", 4);
		Customer customer = new Customer("Mark");
		
		// when
		asException.expect(IllegalArgumentException.class);
		customer.addRental( rental );
				
	}
	
	private Rental mockARental(int priceCode, String title, int daysRented) {
		
		Movie movie = new Movie(title, priceCode);
		Tape tape = new Tape("00000", movie);
		Rental rental = new Rental(tape, daysRented);
		
		return rental;

	}	
	
}
