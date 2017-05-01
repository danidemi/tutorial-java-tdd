package com.danidemi.tutorial.tdd.showcase.junit.matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Test;


public class HamcrestShowcaseOnCompositions {
		
	@Test public void testCollections() {
		
		assertThat( 
				"+39 02112233",  
				Matchers.allOf(
						startsWith("+39"), 
						stringContainsInOrder( Arrays.asList( new String[]{ " ", "0" } ) ) 
				)
		);
		
		
		//		A	gennaio		
		//		B	febbraio		
		//		C	marzo		
		//		D	aprile		
		//		E	maggio
		//		H	giugno
		//		L	luglio
		//		M	agosto
		//		P	settembre
		//		R	ottobre
		//		S	novembre
		//		T	dicembre
		String identificationCode = "XXXYYY60A01F123Z";
		assertThat( String.valueOf((identificationCode.charAt(8))), 
				anyOf(
						is("A"), is("B"), is("C"), is("D"),
						is("E"), is("H"), is("L"), is("M"),
						is("P"), is("R"), is("S"), is("T")
					)
				);
		
		String myFriend = "tom";
		assertThat( myFriend, either(is("tom")).or(is("jerry")) );
		
		Double percentage = 0.12;
		assertThat( percentage, both(greaterThan(0.0)).and(lessThanOrEqualTo(1.0)) );
		
		
		
	}
	
}
