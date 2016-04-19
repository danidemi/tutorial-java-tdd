package com.danidemi.tutorial.tdd.calc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class CalcTest {

    @Test
    public void shouldShowZeroWhenJustInstantiated() {

        // when
        Calc calc = new Calc();

        // then
        assertDisplayAfterKeyStrokes("0", calc);

    }

    @Test
    public void shouldDisplayTheNumberComposedByKeystrokes() {

        assertDisplayAfterKeyStrokes("0", new Calc(), '0');
        assertDisplayAfterKeyStrokes("0", new Calc(), '0', '0');
        assertDisplayAfterKeyStrokes("0", new Calc(), '0', '0', '0');
        assertDisplayAfterKeyStrokes("1", new Calc(), '1');
        assertDisplayAfterKeyStrokes("23", new Calc(), '2', '3');
        assertDisplayAfterKeyStrokes("45", new Calc(), '0', '0', '4', '5');
        assertDisplayAfterKeyStrokes("9999999", new Calc(), '9', '9', '9', '9', '9', '9', '9');

    }

    @Test
    public void shouldNotDisplayMoreThanNineDigits() {

        assertDisplayAfterKeyStrokes("98765432", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2');
        assertDisplayAfterKeyStrokes("987654321", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2', '1');
        assertDisplayAfterKeyStrokes("987654321", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2', '1', '1');
        assertDisplayAfterKeyStrokes("987654321", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2', '1', '1');

    }

    private void assertDisplayAfterKeyStrokes(String display, Calc calc, Character... keyStrokes) {

        Arrays.asList(keyStrokes).forEach( ks -> calc.press(ks) );
        assertThat( calc.display(), equalTo(display) );
    }

}
