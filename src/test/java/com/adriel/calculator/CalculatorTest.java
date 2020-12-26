package com.adriel.calculator;

import static org.junit.Assert.assertThrows;

import org.junit.Before;

import java.lang.Math;

import com.adriel.calculator.exception.NonIntegerPowerException;
import com.adriel.calculator.exception.OperatorNotDefinedException;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for calculator.
 */
public class CalculatorTest extends TestCase {
	
	private Calculator testCalculator;
	@Before
	public void setUp() {
		testCalculator = CalculatorFactory.getCalculator();
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CalculatorTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static junit.framework.Test suite() {
        return new TestSuite(CalculatorTest.class);
    }
    
    @org.junit.Test
    public void testInputNumber_ReturnNumber() {
    	String expression = "0";
    	Integer actual = (int) Math.round(testCalculator.eval(expression));
        assertEquals(expression, actual.toString());
    }
    
    @org.junit.Test
    public void testInputDecimalNumber_ReturnNumber() {
    	String expression = "0.0";
    	Double actual = testCalculator.eval(expression);
        assertEquals(expression, actual.toString());
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidAlphabeticInput() {
    	String expression = "0a";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test
    public void testInputNegativeNumber_ReturnNumber() {
    	String expression = "-5.0";
    	Double actual = testCalculator.eval(expression);
        assertEquals(expression, actual.toString());
    }
    
    @org.junit.Test
    public void testInputNumberWithPlusSign_ReturnNumber() {
    	String expression = "+5.0";
    	Double actual = testCalculator.eval(expression);
        assertEquals(expression.substring(1), actual.toString());
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidFirstCharacterInput() {
    	String expression = "%0";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidLastCharacterInput() {
    	String expression = "0+";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidSecondOperatorInput() {
    	String expression = "0+*1";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidFirstOperatorInput() {
    	String expression = "0@1";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidMultiplePosNegIndicators() {
    	String expression = "0.5*-+1.5";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidMultiplePointInput() {
    	String expression = "0.1.2";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
//  TODO To be deleted after decimal power feature is supported
    @org.junit.Test(expected = NonIntegerPowerException.class)
    public void testInvalidDecimalPowerInput() {
    	String expression = "0.5^1.5";
    	assertThrows(NonIntegerPowerException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test
    public void testSimpleSum() {
    	String expression = "0+1";
    	double expected = 0+1;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSimpleSumWithDecimal() {
    	String expression = "0.0+1.5";
    	double expected = 0.0+1.5;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumNegativeNumber() {
    	String expression = "0+-1";
    	double expected = 0-1;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumNegativeNumberWithDecimal() {
    	String expression = "0.5-+1.5";
    	double expected = 0.5-1.5;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumThreeNumbers() {
    	String expression = "0+1+2";
    	double expected = 0+1+2;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumThreeNumbersWithDecimal() {
    	String expression = "0.0+1.5+2.6";
    	double expected = 0.0+1.5+2.6;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumAndDifference() {
    	String expression = "0.0+1.5-2.6";
    	double expected = 0.0+1.5-2.6;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testDifferenceAndSum() {
    	String expression = "0.0-1.5+2.6";
    	double expected = 0.0-1.5+2.6;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSimpleMultiply() {
    	String expression = "0*1";
    	double expected = 0*1;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSimpleMultiplyWithDecimal() {
    	String expression = "0.1*1.5";
    	double expected = 0.1*1.5;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testMultiplyNegativeNumber() {
    	String expression = "5*-1";
    	double expected = 5*-1;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testMultiplyNegativeNumberWithDecimal() {
    	String expression = "0.5*-1.5";
    	double expected = 0.5*-1.5;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testMultiplyThreeNumbers() {
    	String expression = "3*1*2";
    	double expected = 3*1*2;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testMultiplyThreeNumbersWithDecimal() {
    	String expression = "0.5*1.5*2.6";
    	double expected = 0.5*1.5*2.6;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testMultiplyAndDivide() {
    	String expression = "0.5*1.5/2.6";
    	double expected = 0.5*1.5/2.6;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testDivideAndMultiply() {
    	String expression = "0.3/1.5*2.6";
    	double expected = 0.3/1.5*2.6;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSimplePower() {
    	String expression = "3^2";
    	double expected = Math.pow(3,2);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testPowerTwoTimes() {
    	String expression = "3^2^3";
    	double expected = Math.pow(Math.pow(3,2),3);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumMultiply() {
    	String expression = "3+1*2";
    	double expected = 3+1*2;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumMultiplyWithDecimal() {
    	String expression = "0.3+1.5*2.7";
    	double expected = 0.3+1.5*2.7;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumMultiplyNegativeNumber() {
    	String expression = "2*-1";
    	double expected = 2*-1;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumMultiplyNegativeNumberWithDecimal() {
    	String expression = "0.5*-1.5";
    	double expected = 0.5*-1.5;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumMultiplySubtract() {
    	String expression = "7+1*2-3";
    	double expected = 7+1*2-3;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumMultiplySubtractWithNegativeNumber() {
    	String expression = "7.1+1.5*-2.6-3.1";
    	double expected = 7.1+1.5*-2.6-3.1;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumDivideSubtract() {
    	String expression = "0.5+1.5/2.6-3.3";
    	double expected = 0.5+1.5/2.6-3.3;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumDivideSubtractWithNegativeNumber() {
    	String expression = "0.5+1.5/-2.6-3.3";
    	double expected = 0.5+1.5/-2.6-3.3;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumPowerDivideSubtract() {
    	String expression = "0.5^3+1.5/2.6-3.3";
    	double expected = Math.pow(0.5, 3)+1.5/2.6-3.3;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSumPowerDivideSubtractWithNegativeNumber() {
    	String expression = "0.5+1.5/-2.6^5-3.3";
    	double expected = 0.5+1.5/Math.pow(-2.6, 5)-3.3;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
}
