package com.danidemi.tutorial.tdd.calc;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class CalcTest {

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldShowZeroWhenJustInstantiated() {

        // when
        Calc calc = new Calc();

        // then
        assertDisplayAfterKeyStrokes("0", calc);

    }

    @Ignore("Implement the code to pass it.")
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

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldNotDisplayMoreThanNineDigits() {

        assertDisplayAfterKeyStrokes("98765432", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2');
        assertDisplayAfterKeyStrokes("987654321", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2', '1');
        assertDisplayAfterKeyStrokes("987654321", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2', '1', '1');
        assertDisplayAfterKeyStrokes("987654321", new Calc(), '9', '8', '7', '6', '5', '4', '3', '2', '1', '1');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldDisplayNumberAsSoonItIsTyped(){

        Calc calc = new Calc();
        assertDisplayAfterKeyStrokes("1", calc, '1');
        assertDisplayAfterKeyStrokes("11", calc, '1');
        assertDisplayAfterKeyStrokes("112", calc, '2');
        assertDisplayAfterKeyStrokes("1124", calc, '4');
        assertDisplayAfterKeyStrokes("11246", calc, '6');
        assertDisplayAfterKeyStrokes("112463", calc, '3');
        assertDisplayAfterKeyStrokes("1124637", calc, '7');
        assertDisplayAfterKeyStrokes("11246370", calc, '0');
        assertDisplayAfterKeyStrokes("112463700", calc, '0');
        assertDisplayAfterKeyStrokes("112463700", calc, '1');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldSumTwoPositiveNumbers(){

        Calc calc = new Calc();
        assertDisplayAfterKeyStrokes("1", calc, '2');
        assertDisplayAfterKeyStrokes("1", calc, '+');
        assertDisplayAfterKeyStrokes("3", calc, '3');
        assertDisplayAfterKeyStrokes("5", calc, '=');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldOverflowIfTheResultIsToBigToFitTheDisplay(){

        Calc calc = new Calc();
        assertDisplayAfterKeyStrokes("888888888", calc, '8','8','8','8','8','8','8','8','8');
        assertDisplayAfterKeyStrokes("888888888", calc, '+');
        assertDisplayAfterKeyStrokes("666666666", calc, '6','6','6','6','6','6','6','6','6');
        assertDisplayAfterKeyStrokes("E", calc, '=');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldNotAcceptAnyKeystrokeAfterOverflow(){

        Calc calc = new Calc();

        goOverflowWithASum(calc);

        assertDisplayAfterKeyStrokes("E", calc, '1');
        assertDisplayAfterKeyStrokes("E", calc, '=');
        assertDisplayAfterKeyStrokes("E", calc, '+');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldClearTheOverflow(){

        Calc calc = new Calc();

        goOverflowWithASum(calc);

        assertDisplayAfterKeyStrokes("0", calc, 'C');
        assertDisplayAfterKeyStrokes("1", calc, '1');
        assertDisplayAfterKeyStrokes("1", calc, '+');
        assertDisplayAfterKeyStrokes("2", calc, '2');
        assertDisplayAfterKeyStrokes("3", calc, '=');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldApplyLastSumWithMultipleEqual() {

        Calc calc = new Calc();
        assertDisplayAfterKeyStrokes("3", calc, '3');
        assertDisplayAfterKeyStrokes("3", calc, '+');
        assertDisplayAfterKeyStrokes("22", calc, '2','2');
        assertDisplayAfterKeyStrokes("25", calc, '=');
        assertDisplayAfterKeyStrokes("47", calc, '=');
        assertDisplayAfterKeyStrokes("69", calc, '=');
        assertDisplayAfterKeyStrokes("91", calc, '=');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldSubtract() {

        Calc calc = new Calc();
        assertDisplayAfterKeyStrokes("3", calc, '3');
        assertDisplayAfterKeyStrokes("3", calc, '-');
        assertDisplayAfterKeyStrokes("2", calc, '2');
        assertDisplayAfterKeyStrokes("1", calc, '=');

    }

    @Ignore("Implement the code to pass it.")
    @Test
    public void shouldKeepOnSubtract() {

        Calc calc = new Calc();
        assertDisplayAfterKeyStrokes("12", calc, '1', '2');
        assertDisplayAfterKeyStrokes("12", calc, '-');
        assertDisplayAfterKeyStrokes("5", calc, '5');
        assertDisplayAfterKeyStrokes("7", calc, '=');
        assertDisplayAfterKeyStrokes("2", calc, '=');
        assertDisplayAfterKeyStrokes("-3", calc, '=');
        assertDisplayAfterKeyStrokes("-8", calc, '=');

    }

    private void goOverflowWithASum(Calc calc1) {
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('+');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('9');
        calc1.press('=');
    }


    private void assertDisplayAfterKeyStrokes(String display, Calc calc, Character... keyStrokes) {

        Arrays.asList(keyStrokes).forEach( ks -> calc.press(ks) );
        assertThat( calc.display(), equalTo(display) );

    }

}
