package com.danidemi.tutorial.tdd.darts;

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
		assertEquals(301, d.score());
		assertFalse(d.isFinished());
	}

	@Test public void shouldCorrectlyScoreANormalThrow(){
		d.dart(20);
		assertEquals(281, d.score());
	}

	@Test public void shouldCountADoubleThrow(){
		d.dart(20, Multiplier.DOUBLE);
		assertEquals(301 - 20*2, d.score());
	}

	@Test public void shouldCountATripleThrow(){
		d.dart(20, Multiplier.TRIPLE);
		assertEquals(301 - 20*3, d.score());
	}

	@Test public void shouldCountTheTurnInitially(){
		assertEquals(1, d.getTurn());
		assertEquals(3, d.dartsLeft());
	}

	@Test public void shouldCountTheTurn(){
	
		d.dart(1);		
		assertEquals(1, d.getTurn());
		assertEquals(2, d.dartsLeft());
		
		d.dart(1);		
		assertEquals(1, d.getTurn());
		assertEquals(1, d.dartsLeft());
		
		d.dart(1);		
		assertEquals(2, d.getTurn());
		assertEquals(3, d.dartsLeft());		
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
