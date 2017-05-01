package com.danidemi.tutorial.tdd.showcase.junit.matchers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.StringContains;
import org.hamcrest.number.IsCloseTo;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

import static org.junit.matchers.JUnitMatchers.isException;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.array;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;


public class HamcrestShowcaseOnNumbers {
		
	@Test public void testNumbers() {
		
		assertThat( Math.PI, closeTo(3.14, .01) );
		
	}
	
	@Test public void testComparables() {	
		
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
	
}
