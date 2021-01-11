package com.adriel.calculator;

import java.util.Arrays;

import com.adriel.calculator.exception.NonIntegerPowerException;
import com.adriel.calculator.exception.OperatorNotDefinedException;
import com.adriel.calculator.util.StringAndBoolReturn;

/**
 * Calculates an output based on an input string.
 *
 */
public class Calculator {
	
	private final String OPERATOR_INVALID_MSG = "Operator %s is invalid.";
	private final String NO_CLOSE_BRACKET_MSG = "Cannot find appropriate closing bracket %s for %s";
	private final String DECIMAL_POWER_MSG = "Raising to decimal power %s is not supported.";
	
	private Splitter splitter;
	private StringAndBoolReturn sumSplit;
	
	protected Calculator() {
		splitter = SplitterFactory.getSplitter();
	}
	
	/**
	 * Evaluate mathematical expression <b>sum</b> in the form of a string (no space please), supporting the operators '+', '-', '*', '/', '^' and brackets.
	 * @param sum
	 * @return ans
	 */
	public double eval(String sum) {
		sumSplit = splitter.splitNumeric(sum);
		
		// First extract bracket operation and deal with them first
		processBracket("(", ")");
		processBracket("[", "]");
		processBracket("{", "}");
		
		// Process non-bracketed expression
		return evalInOrder(sumSplit);
	}
	
	private void processBracket(String open, String close) {
		int openIndex = 0;
		int closeIndex = -1;
		while (openIndex < sumSplit.getStr_arr().length) {
			closeIndex = -1;
			if (open.equals(sumSplit.getStr_arr()[openIndex])) {
				for (int i = openIndex; i < sumSplit.getStr_arr().length; i++) {
				    if (sumSplit.getStr_arr()[i].equals(close)) {
				    	closeIndex = i;
				        break;
				    }
				}
				if (closeIndex == -1) {
					throw new OperatorNotDefinedException(String.format(NO_CLOSE_BRACKET_MSG, close, open));
				}
				// New expression should have length close - open + 1 - 2 (-2 because we remove the brackets)
				StringAndBoolReturn sumWithinBracket = new StringAndBoolReturn(closeIndex-openIndex-1);
				String[] expressionWithinBracket = Arrays.copyOfRange(sumSplit.getStr_arr(), openIndex+1, closeIndex);
				boolean[] operatorWithinBracket = Arrays.copyOfRange(sumSplit.getBool_arr(), openIndex+1, closeIndex);
				sumWithinBracket.setStr_arr(expressionWithinBracket);
				sumWithinBracket.setBool_arr(operatorWithinBracket);
				double ansWithinBracket = evalInOrder(sumWithinBracket);
				
				// Update arrays of sumSplit with above result (length of bracket expression is close - open + 1, now replaced by 1 spot)
				String[] expressionOutsideBracket = new String[sumSplit.getStr_arr().length-closeIndex+openIndex];
				boolean[] operatorOutsideBracket = new boolean[sumSplit.getStr_arr().length-closeIndex+openIndex];
				
				System.arraycopy(sumSplit.getStr_arr(), 0, expressionOutsideBracket, 0, openIndex);
				System.arraycopy(sumSplit.getBool_arr(), 0, operatorOutsideBracket, 0, openIndex);
				expressionOutsideBracket[openIndex] = String.valueOf(ansWithinBracket);
				operatorOutsideBracket[openIndex] = true;
				System.arraycopy(sumSplit.getStr_arr(), closeIndex+1, expressionOutsideBracket, openIndex+1, sumSplit.getStr_arr().length-closeIndex-1);
				System.arraycopy(sumSplit.getBool_arr(), closeIndex+1, operatorOutsideBracket, openIndex+1, sumSplit.getBool_arr().length-closeIndex-1);
				sumSplit.setStr_arr(expressionOutsideBracket);
				sumSplit.setBool_arr(operatorOutsideBracket);
		    }
			openIndex++;
		}
	}
	
	private double evalInOrder(StringAndBoolReturn expression) {
		// Power has first highest priority
		powerTwoNums(expression);
		
		// Then multiply and divide
		multiDivide(expression);
		
		// Then add and subtract
		addSubtract(expression);
		
		// Check that all values has been operated
		for (int i = 1; i < expression.getStr_arr().length; i++) {
			if (expression.getStr_arr()[i] != null) {
				throw new OperatorNotDefinedException(String.format(OPERATOR_INVALID_MSG, expression.getStr_arr()[i]));
			}
		}
		
		// Final output (will be store in the first element)
		double ans = Double.parseDouble(expression.getStr_arr()[0]);
		return ans;
	}
	
	/**
	 * Look for the previous and next numbers given the index of the operator <b>ind_start</b>.
	 * @param tofind
	 * @param ind_start
	 * @return
	 */
	private int[] findPrevNextNums(StringAndBoolReturn tofind, int ind_start) {
		int ind_1 = ind_start;
		int ind_2 = ind_start;
		
		while (!tofind.getBool_arr()[ind_1]) {
			ind_1--;
		}
		
		while (!tofind.getBool_arr()[ind_2]) {
			ind_2++;
		}
		
		int[] out = {ind_1, ind_2};
		return out;
	}
	
	/** Set result into first position and change others to null.
	 * 
	 * @param toSet
	 * @param range
	 * @param result
	 */
	private void setResult(StringAndBoolReturn toSet, int[] range, String result) {
		// Modify this with brackets
		toSet.setStr_arr(result, range[0]);
		
		// Set everything operated to null
		for (int i = range[0] + 1; i <= range[1]; i++) {
			toSet.setStr_arr(null, i);
		}
		
		toSet.setBool_arr(false, range[1]);
	}
	
	/**
	 * Power two numbers closest to the symbol "^".
	 * @param toPower
	 */
	private void powerTwoNums(StringAndBoolReturn toPower) {
		// To modify with brackets
		while (Arrays.asList(toPower.getStr_arr()).contains("^")) {
			int ind_pow = Arrays.asList(toPower.getStr_arr()).indexOf("^");
			// Test index - to move to index where previous number is found (1) and next (2)
			int[] prevNextNums = findPrevNextNums(toPower, ind_pow);
			
			if (toPower.getStr_arr()[prevNextNums[1]].contains(".")) {
				throw new NonIntegerPowerException(String.format(DECIMAL_POWER_MSG, toPower.getStr_arr()[prevNextNums[1]]));
			}
			
			String powered = evalTwoNums(toPower.getStr_arr()[prevNextNums[0]], toPower.getStr_arr()[prevNextNums[1]], "^");
			
			setResult(toPower, prevNextNums, powered);
		}
	}
	
	/**
	 * Multiply and divide between two numbers closest to the symbol "*" and "/" respectively. 
	 * @param toMultiDivide
	 */
	private void multiDivide(StringAndBoolReturn toMultiDivide) {
		while (Arrays.asList(toMultiDivide.getStr_arr()).contains("*") || Arrays.asList(toMultiDivide.getStr_arr()).contains("/")) {
			int multi_ind = Arrays.asList(toMultiDivide.getStr_arr()).indexOf("*");
			int div_ind = Arrays.asList(toMultiDivide.getStr_arr()).indexOf("/");
			int[] prevNextNums;
			String sym;
			
			if (multi_ind >= 0 && div_ind >= 0) {
				// Find the first one to operate
				if (multi_ind < div_ind) {
					// Smaller index comes first
					prevNextNums = findPrevNextNums(toMultiDivide, multi_ind);
					sym = "*";
				} else {
					prevNextNums = findPrevNextNums(toMultiDivide, div_ind);
					sym = "/";
				}
			} else {
				if (multi_ind >= 0) {
					prevNextNums = findPrevNextNums(toMultiDivide, multi_ind);
					sym = "*";
				} else {
					prevNextNums = findPrevNextNums(toMultiDivide, div_ind);
					sym = "/";
				}
			}
			
			String multidivided = evalTwoNums(toMultiDivide.getStr_arr()[prevNextNums[0]], toMultiDivide.getStr_arr()[prevNextNums[1]], sym);
			
			setResult(toMultiDivide, prevNextNums, multidivided);
		}
	}
	
	/**
	 * Add and subtract between two numbers closest to the symbol "+" and "-" respectively.
	 * @param toAddSubtract
	 */
	private void addSubtract(StringAndBoolReturn toAddSubtract) {
		while (Arrays.asList(toAddSubtract.getStr_arr()).contains("+") || Arrays.asList(toAddSubtract.getStr_arr()).contains("-")) {
			int add_ind = Arrays.asList(toAddSubtract.getStr_arr()).indexOf("+");
			int sub_ind = Arrays.asList(toAddSubtract.getStr_arr()).indexOf("-");
			int[] prevNextNums;
			String sym;
			
			if (add_ind >= 0 && sub_ind >= 0) {
				// Find the first one to operate
				if (add_ind < sub_ind) {
					// Smaller index comes first
					prevNextNums = findPrevNextNums(toAddSubtract, add_ind);
					sym = "+";
				} else {
					prevNextNums = findPrevNextNums(toAddSubtract, sub_ind);
					sym = "-";
				}
			} else {
				if (add_ind >= 0) {
					prevNextNums = findPrevNextNums(toAddSubtract, add_ind);
					sym = "+";
				} else {
					prevNextNums = findPrevNextNums(toAddSubtract, sub_ind);
					sym = "-";
				}
			}
			
			String addsubtracted = evalTwoNums(toAddSubtract.getStr_arr()[prevNextNums[0]], toAddSubtract.getStr_arr()[prevNextNums[1]], sym);
			
			setResult(toAddSubtract, prevNextNums, addsubtracted);
		}
	}
	
	/**
	 * Evaluates a mathematical operation between two numbers a and b, defined by operator <b>op</b>.
	 * @param a
	 * @param b
	 * @param op
	 * @return result
	 */
	private String evalTwoNums(String a, String b, String op) {
		Double aa = Double.parseDouble(a);
		Double bb = Double.parseDouble(b);
		Double result = 1.0;
		
		switch(op) {
		case "+":
			result = aa + bb;
			break;
		
		case "-":
			result = aa - bb;
			break;
			
		case "*":
			result = aa * bb;
			break;
		
		case "/":
			result = aa / bb;
			break;
		
		case "^":
			for (int i = 0; i < bb; i++) {
				result *= aa;
			}
			break;
		}
		
		return Double.toString(result);
	}
	
}
