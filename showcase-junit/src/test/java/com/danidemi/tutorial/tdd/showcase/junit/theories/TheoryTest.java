package com.danidemi.tutorial.tdd.showcase.junit.theories;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TheoryTest {
	
	@DataPoints public static Device[] devices(){
		return new Device[]{
				new Radio(),
				new TV()
		};
	}
			
	@Theory
	public void shouldBeOffAfterBeingTurnedOff(Device device){
		
		device.turnOff();
		
		assertThat( device.isOn(), is(false) );
		
	}
	
	@Theory
	public void shouldBeOnAfterBeingTurnedOn(Device device){
		
		device.turnOn();
		
		assertThat( device.isOn(), is(true) );
		
	}	

}
