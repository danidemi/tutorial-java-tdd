package com.danidemi.tutorial.tdd.darts;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.danidemi.tutorial.tdd.darts.Darts;
import com.danidemi.tutorial.tdd.darts.Darts.Multiplier;


public class DartsTest {
	
	private Darts d;

	@Before public void setUpGame(){
		d = new Darts();		
	}
	
	@Test public void aGameShouldStartAt301(){
		assertThat(d.score(), equalTo(301) );
		assertThat(d.isFinished(), is(false));
	}

	@Test public void shouldCorrectlyScoreANormalThrow(){
		d.dart(20);
		assertThat(d.score(), is(281));
	}

	@Test public void shouldCountADoubleThrow(){
		d.dart(20, Multiplier.DOUBLE);
		assertThat(d.score(), is(301 - 20*2));
	}

	@Test public void shouldCountATripleThrow(){
		d.dart(20, Multiplier.TRIPLE);
		assertThat(d.score(), is(301 - 20*3));
	}

	@Test public void shouldCountTheTurnInitially(){
		assertThat(d.getTurn(), is(1));
		assertThat(d.dartsLeft(), is(3));
	}

	@Test public void shouldCountTheTurn(){
	
		d.dart(1);		
		assertThat(d.getTurn(), is(1));
		assertThat(d.dartsLeft(), is(2));
		
		d.dart(1);		
		assertThat(d.getTurn(), is(1));
		assertThat(d.dartsLeft(), is(1));
		
		d.dart(1);		
		assertThat(d.getTurn(), is(2));
		assertThat(d.dartsLeft(), is(3));		
	}

	@Test public void shouldGoBustReaching1(){
		
		for(int i=0; i<3; i++){
			d.dart(20, Multiplier.TRIPLE);			
		}
		// 121
		
		d.dart(20, Multiplier.TRIPLE);
		d.dart(20, Multiplier.TRIPLE);
		// 1 --> Bust!
		
		assertEquals(121, d.score());
		assertEquals(3, d.getTurn());
		assertEquals(3, d.dartsLeft());
		
	}

	@Test public void shouldGoBustAboveZero(){
	
		for(int i=0; i<3; i++){
			d.dart(20, Multiplier.TRIPLE);			
		}
		// 121
		
		d.dart(15, Multiplier.TRIPLE); // 76 
		d.dart(15, Multiplier.TRIPLE); // 31
		d.dart(20, Multiplier.TRIPLE); // -29 Bust!
		
		assertEquals(121, d.score());
		assertEquals(3, d.getTurn());
		assertEquals(3, d.dartsLeft());		
	
		
	}

	@Test public void shouldCompleteAGameWithADouble(){

		for(int i=0; i<3; i++){
			d.dart(20, Multiplier.TRIPLE);			
		}
		// 121
		
		d.dart(17, Multiplier.TRIPLE); // 70
		d.dart(20, Multiplier.TRIPLE); // 10
		d.dart(5, Multiplier.DOUBLE); // 0
		
		assertTrue(d.isFinished());
		
	}
	
	
}
