package com.danidemi.tutorial.tdd.showcase.junit.matchers;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.array;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TimeZone;

import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.Test;


public class HamcrestShowcaseOnBasics {
	

	
	// arrays, list, collections
	@Test public void testCollectionsAndArrays() {
		
		Integer fibonacci[] = new Integer[] { 1, 1, 2, 3, 5, 8 };
		
		assertThat(fibonacci, array( is(1), is(1), is(2), is(3), is(5), is(8)) );
		
		assertThat(fibonacci, arrayContaining( is(1), is(1), is(2), is(3), is(5), is(8)) );
		
		assertThat(fibonacci, arrayContainingInAnyOrder( is(1), is(8), is(1), is(3), is(5), is(2)) );
		
		assertThat(fibonacci, arrayWithSize(6) );
		
		assertThat(fibonacci, arrayWithSize( greaterThan(3) ) );
		
		
		assertThat(Arrays.asList( fibonacci ), everyItem(greaterThan(0)));
		
		assertThat(Arrays.asList( fibonacci ), hasItem(equalTo(2)));
		
		assertThat(new String[]{}, emptyArray());
		
		assertThat(Arrays.asList( new String[]{} ), emptyCollectionOf(String.class));
		

		
		
		

		
	}
	
	// matchers combination
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
		
		
		
	}
	
	
	// numbers and Comparables
	@Test public void testNumbers() {
		
		assertThat( Math.PI, closeTo(3.14, .01) );
		
		class Mountain implements Comparable<Mountain> {
			private String name;
			private Integer height;
			public Mountain(String name, int height) {
				super();
				this.name = name;
				this.height = height;
			}
			
			@Override
			public int compareTo(Mountain otherMountain) {
				return this.height.compareTo(otherMountain.height);
			}
			
		};
		
		
		
		Mountain nowshak = new Mountain("Nowshak", 7492);
		Mountain pumarKish = new Mountain("Pumar Kish", 7492);
		Mountain monteBianco = new Mountain("Monte Bianco", 4810);
		assertThat( pumarKish, comparesEqualTo(nowshak) );
		assertThat( nowshak, greaterThan(monteBianco));
		
	}
	
	// basic references equal, not, null
	
	@Test
	public void basicReferences(){
		
		assertThat( null, nullValue() );
		assertThat( "Hello", not(nullValue()));
		
		assertThat( null, nullValue(String.class));
		
		assertThat( new Integer(3), instanceOf(Number.class) );
		assertThat( new Integer(3), isA(Number.class) );
		
		assertThat( 3 + 4, equalTo(7));
		assertThat( 3 + 4, is( equalTo(7) ));
		
		Object o1 = new Object();
		Object o2 = o1;
		assertThat( o1, is(o2) );
		
		
	}

	// strings
	
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
	

	
	
	
	@Test 
	public void testAllOf() {

	}

	// to do
/*	
	@Test
	public void test() {
		Exception e = new Exception();
		assertThat( isException( e ) );
	}
	*/

}
