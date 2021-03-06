package com.adriel.calculator;

import static org.junit.Assert.assertThrows;

import org.junit.Assert;
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
    
    // Test factory    
    @org.junit.Test
    public void testCreateCalculatorFactory() {
    	try {
    		new CalculatorFactory();
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of CalculatorFactory" + e.getMessage());
    	}
    }
    
    @org.junit.Test
    public void testCreateSplitterFactory() {
    	try {
    		new SplitterFactory();
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of CalculatorFactory" + e.getMessage());
    	}
    }
    
    // Test input number without calculation
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
    
    // Test exception handling
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
    
    @org.junit.Test(expected = NonIntegerPowerException.class)
    public void testInvalidDecimalPowerInput() {
    	String expression = "0.5^1.5";
    	assertThrows(NonIntegerPowerException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidMultipleBracketInput() {
    	String expression = "(1+(2+3)*4)^5";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidEndBracketInput() {
    	String expression = ")(1+2)*3";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testNoEndBracketInput() {
    	String expression = "(1+2*3";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testStackedBracketInput() {
    	String expression = "[1+(2*3+4]-5)/6";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidMultipleSquareBracketInput() {
    	String expression = "[1+[2+3]*4]^5";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidEndSquareBracketInput() {
    	String expression = "][1+2)*3";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidMultipleCurlyBracketInput() {
    	String expression = "{1+{2+3}*4}^5";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidEndCurlyBracketInput() {
    	String expression = "}{1+2)*3";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testInvalidOrderBracketInput() {
    	String expression = "(1.5*[2+3])/4.5";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testCurlyBracketWithRoundParenthesisInput() {
    	String expression = "(1.5*{2+3})/4.5";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    @org.junit.Test(expected = OperatorNotDefinedException.class)
    public void testCurlyBracketWithSquareBracketInput() {
    	String expression = "[1.5*{2+3}]/4.5";
    	assertThrows(OperatorNotDefinedException.class, () -> testCalculator.eval(expression));
    }
    
    // Test simple operations (no bracket)
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
    public void testPowerToZero() {
    	String expression = "3^0";
    	double expected = Math.pow(3,0);
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
    
    // Test complex expressions (with 1 level of bracket)
    @org.junit.Test
    public void testBracketWithSumMultiply() {
    	String expression = "(0.5+1.5)*2.4";
    	double expected = (0.5+1.5)*2.4;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testNoUseBracketWithSubtractDivide() {
    	String expression = "0.5-(1.5/2.4)";
    	double expected = 0.5-(1.5/2.4);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testBracketWithPower() {
    	String expression = "(0.5+1.5)^2";
    	double expected = Math.pow((0.5+1.5),2);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testNoUseBracketWithPower() {
    	String expression = "0.5+(1.5^2)";
    	double expected = 0.5+Math.pow((1.5),2);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testBracketOnSingleNumber() {
    	String expression = "(0.5)+(1.5)*2.4";
    	double expected = 0.5+1.5*2.4;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testMultipleBracketWithSumMultiplySubtract() {
    	String expression = "(0.5+1.5)*2.4/(5.3-3.2)";
    	double expected = (0.5+1.5)*2.4/(5.3-3.2);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    // Test complex expressions (with 2 levels of brackets)
    @org.junit.Test
    public void testSquareBracketWithSumMultiply() {
    	String expression = "[(0.5+1.5)*(2.3-2.4)]*(2.4-6.3)";
    	double expected = ((0.5+1.5)*(2.3-2.4))*(2.4-6.3);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testNoUseSquareBracketWithSubtractDivide() {
    	String expression = "0.5-[(1.5/2.4)]";
    	double expected = 0.5-1.5/2.4;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSquareBracketWithPower() {
    	String expression = "[(0.5+1.5)*2]^3";
    	double expected = Math.pow((0.5+1.5)*2,3);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testNoUseSquareBracketWithPower() {
    	String expression = "0.5+[1.5^2]";
    	double expected = 0.5+Math.pow((1.5),2);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testSquareBracketOnSingleNumber() {
    	String expression = "[(0.5)]+(1.5)*[2.4]";
    	double expected = 0.5+1.5*2.4;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testMultipleLevelSquareBracketWithSumMultiplySubtract() {
    	String expression = "(0.5+1.5)*[2.4/(5.3-3.2)-3.5]";
    	double expected = (0.5+1.5)*(2.4/(5.3-3.2)-3.5);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
 // Test complex expressions (with 3 levels of brackets)
    @org.junit.Test
    public void testCurlyBracketWithSumMultiply() {
    	String expression = "{[(0.5+1.5)*(2.3-2.4)]-3}*{(2.4-6.3)+3}^5";
    	double expected = (((0.5+1.5)*(2.3-2.4))-3)*Math.pow(((2.4-6.3)+3), 5);
    	double actual = testCalculator.eval(expression);
    	// This complex expression has small differences in evaluation, check whether match within tolerance
        assertTrue(expected - actual < 1e-4);
    }
    
    @org.junit.Test
    public void testNoUseCurlyBracketWithSubtractDivide() {
    	String expression = "0.5-{[(1.5/2.4)]}";
    	double expected = 0.5-1.5/2.4;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testCurlyBracketWithPower() {
    	String expression = "{[(0.5+1.5)*2]+3.4}^3";
    	double expected = Math.pow((0.5+1.5)*2+3.4,3);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testNoUseCurlyBracketWithPower() {
    	String expression = "0.5+{1.5^2}";
    	double expected = 0.5+Math.pow((1.5),2);
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
    @org.junit.Test
    public void testCurlyBracketOnSingleNumber() {
    	String expression = "{[(0.5)]}+(1.5)*[2.4]/{3.5}";
    	double expected = 0.5+1.5*2.4/3.5;
    	double actual = testCalculator.eval(expression);
        assertEquals(expected, actual);
    }
    
}
