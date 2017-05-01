package com.danidemi.tutorial.tdd.showcase.junit.rules;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TestNameRuleTest {
	
	@Rule public TestName testId = new TestName();

	@Test
	public void byteMatch() {
		assertEquals("an error occurred in '" + testId.getMethodName() + "'", "x", new String(new byte[]{120}));
	}

}
