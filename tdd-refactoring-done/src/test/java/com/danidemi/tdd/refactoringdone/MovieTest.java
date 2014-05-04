package com.danidemi.tdd.refactoringdone;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
public class MovieTest {

	@Test
	public void shouldChangeFromNewReleaseToChildrenLongRental() {
		
		// given
		Movie tested = Movie.newJustReleasedMovie("Cinderella");
		
		// when
		double amountAsNewRelease = tested.amount(10);
		tested.beForChildren();
		double amountAsForChildren = tested.amount(10);
		
		// then
		assertThat( amountAsNewRelease, equalTo( 3 * 10.0 ) );
		assertThat( amountAsForChildren, equalTo( 1.5 + 1.5 * 7 ) );
		
	}

	@Test
	public void shouldChangeFromNewReleaseToChildrenShortRental() {
		
		// given
		Movie tested = Movie.newJustReleasedMovie("Cinderella");
		
		// when
		double amountAsNewRelease = tested.amount(2);
		tested.beForChildren();
		double amountAsForChildren = tested.amount(2);
		
		// then
		assertThat( amountAsNewRelease, equalTo( 3.0 * 2.0 ) );
		assertThat( amountAsForChildren, equalTo( 1.5 ) );
		
	}
	
	@Test
	public void shouldChangeFromNewReleaseToRegularShortRental() {
		
		// given
		Movie tested = Movie.newJustReleasedMovie("Star Wars Episode VII");
		
		// when
		double amountAsNewRelease = tested.amount(2);
		tested.beRegular();
		double amountAsRegular = tested.amount(2);
		
		// then
		assertThat( amountAsNewRelease, equalTo( 3.0 * 2.0 ) );
		assertThat( amountAsRegular, equalTo( 2.0 ) );
		
	}
	
	@Test
	public void shouldChangeFromNewReleaseToRegularLongRental() {
		
		// given
		Movie tested = Movie.newJustReleasedMovie("Star Wars Episode VII");
		
		// when
		double amountAsNewRelease = tested.amount(3);
		tested.beRegular();
		double amountAsRegular = tested.amount(3);
		
		// then
		assertThat( amountAsNewRelease, equalTo( 3.0 * 3 ) );
		assertThat( amountAsRegular, equalTo( 2.0 + 1.5 * 1 ) );
		
	}
	
}
