package ru.nsu.fit.g15205.gorlin;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;


public class ParserTests extends TestCase {
    @Test
    public void testCorrectSumEasyCase() throws Exception {
        Reader reader = new StringReader("4+5");
        int actual = new Parser(reader).calculate();
        int expected = 9;

        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectSumComplexCase() throws Exception {
        Reader reader = new StringReader("4+5+6+7+12");

        int actual = new Parser(reader).calculate();
        int expected = 34;

        assertEquals(expected, actual);
    }

    @Test
    public void testIncorrectDoubleSum() throws Exception {
        Reader reader = new StringReader("2++2");

        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }

    @Test
    public void testIncorrectPlusAtBeginning() throws Exception {
        Reader reader = new StringReader("+2+4");

        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }


    @Test
    public void testCorrectSubEasyCase() throws Exception {
        Reader reader = new StringReader("5-4");
        int actual = new Parser(reader).calculate();
        int expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectSubSumComplexCase() throws Exception {
        Reader reader = new StringReader("4-5+6-7+12");
        int actual = new Parser(reader).calculate();
        int expected = 10;

        assertEquals(expected, actual);
    }


    @Test
    public void testCorrectMinusAtBeginning() throws Exception {
        Reader reader = new StringReader("-2+4");
        assertEquals(2, new Parser(reader).calculate());
    }


    @Test
    public void testIncorrectSubSum() throws Exception {
        Reader reader = new StringReader("2-+2");

        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }

    @Test
    public void testCorrectSub() throws Exception {
        Reader reader = new StringReader("2--2");
        assertEquals(4, new Parser(reader).calculate());
    }

    @Test
    public void testCorrectMult() throws Exception {
        Reader reader = new StringReader("5*2");
        assertEquals(10, new Parser(reader).calculate());
    }

    @Test
    public void testCorrectNegativeFirstMult() throws Exception {
        Reader reader = new StringReader("-5*2");
        assertEquals(-10, new Parser(reader).calculate());
    }

    @Test
    public void testCorrectNegativeSecondMult() throws Exception {
        Reader reader = new StringReader("5*-2");
        assertEquals(-10, new Parser(reader).calculate());
    }

    @Test
    public void testCorrectComplexOperations() throws Exception {
        Reader reader = new StringReader("5*-2+4*-2--20");
        assertEquals(2, new Parser(reader).calculate());
    }

    @Test
    public void testCorrectParenthesesOrder() throws Exception {
        Reader reader = new StringReader("4*(5+4-7)");
        assertEquals(8, new Parser(reader).calculate());
    }

    @Test
    public void testIncorrectDoubleMult() throws Exception {
        Reader reader = new StringReader("4**(5+4-7)");


        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }

    @Test
    public void testCorrectDivEasyCase() throws Exception {
        Reader reader = new StringReader("4/3");
        assertEquals(1, new Parser(reader).calculate());
    }

    @Test
    public void testDivisionByZero() throws Exception {
        Reader reader = new StringReader("4/0");


        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }

    @Test
    public void testDivisionMultComplex() throws Exception {
        Reader reader = new StringReader("4/3+5/2+6*4+4/(2-1)");
        assertEquals(31, new Parser(reader).calculate());
    }

    @Test
    public void testPowerSimpleCase() throws Exception {
        Reader reader = new StringReader("4^3");
        assertEquals(64, new Parser(reader).calculate());
    }

    @Test
    public void testPowerNegativeCase() throws Exception {
        Reader reader = new StringReader("4^-3");
        assertEquals(0, new Parser(reader).calculate());
    }

    @Test
    public void testPowerZeroCase() throws Exception {
        Reader reader = new StringReader("4^0");
        assertEquals(1, new Parser(reader).calculate());
    }

    @Test
    public void testIncorrectDoublePowerCase() throws Exception {
        Reader reader = new StringReader("4^^3");

        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }

    @Test
    public void testPowerWithParenthesesCase() throws Exception {
        Reader reader = new StringReader("4^(4+2*(1+1))");
        assertEquals(65536, new Parser(reader).calculate());
    }

    @Test
    public void testParenthesesSimpleCase() throws Exception {
        Reader reader = new StringReader("(2+4)");
        assertEquals(6, new Parser(reader).calculate());
    }

    @Test
    public void testIncorrectParenthesesNotEnoughCase() throws Exception {
        Reader reader = new StringReader("((2+4)");

        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }

    @Test
    public void testIncorrectParenthesesTooManyCase() throws Exception {
        Reader reader = new StringReader("(2+4))");

        try {
            new Parser(reader).calculate();
            assertTrue(false);
        } catch (ParserException pe) {
            assertTrue(true);
        }
    }

    @Test
    public void testVeryComplexCase() throws Exception {
        Reader reader = new StringReader("4+5*(6^2-35*7+200-4*-2*(5^2))");
        assertEquals(959, new Parser(reader).calculate());
    }
}
