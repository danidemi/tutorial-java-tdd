package com.danidemi.tutorial.tdd.web.model;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.JodaTimePermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.*;

@RunWith(PowerMockRunner.class)
public class ActorTest extends Actor {

	@Test
	public void shouldComputeProperAge() {
		
		// given
		Actor tested = new Actor();
		tested.setFirstName("Paul");
		tested.setLastName("Oldman");
		tested.setBirthDate( new DateTime(1940, DateTimeConstants.JANUARY, 12, 0, 0).toDate() );
		
		// when
		int years = tested.getAgeInYears( new DateTime(1970, DateTimeConstants.JANUARY, 12, 0, 0).toDate() );
		
		// then
		assertThat( years, equalTo(30) );
		
	}
	
	@Test
	public void shouldReturnZeroIfCurrentDateIsBeforeBirthDate() {
		
		// given
		Actor tested = new Actor();
		tested.setFirstName("Paul");
		tested.setLastName("Oldman");
		tested.setBirthDate( new DateTime(2010, DateTimeConstants.JANUARY, 1, 0, 0).toDate() );
		
		// when
		int years = tested.getAgeInYears( new DateTime(1995, DateTimeConstants.JANUARY, 12, 0, 0).toDate() );
		
		// then
		assertThat( years, equalTo(0) );
		
	}	

}
