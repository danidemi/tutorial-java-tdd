package com.danidemi.tutorial.tdd.showcase.junit.matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.TimeZone;

import org.hamcrest.core.StringContains;
import org.junit.Test;


public class HamcrestShowcaseOnMaps {
	
	@Test public void testMaps() {
		
		TimeZone rome = TimeZone.getTimeZone("Europe/Rome");
		TimeZone paris = TimeZone.getTimeZone("Europe/Paris");
		TimeZone tokio = TimeZone.getTimeZone("Asia/Tokio");
		TimeZone newYork = TimeZone.getTimeZone("America/New York");
		
		HashMap<String, TimeZone> city2timezone = new HashMap<String, TimeZone>();
		city2timezone.put("Rome", rome);
		city2timezone.put("Paris", paris);
		city2timezone.put("New York", newYork);
		
		assertThat( city2timezone, hasEntry("Rome", rome) );
		assertThat( city2timezone, hasKey("New York"));
		assertThat( city2timezone, hasKey( StringContains.containsString(" ") ));
		assertThat( city2timezone, hasValue( paris ) );
		assertThat( city2timezone, hasValue( not(is(tokio)) ) );
		
	}
	
}
