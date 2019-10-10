package com.fdmgroup.calculator;

import java.util.Arrays;

/**
 * Stores a string array and a boolean array.
 * 
 * @author Adriel.Yeung
 *
 */
class StringAndBoolReturn {
	// Stores all values and operators to evaluate
	private String[] str_arr;
	// Stores whether it is a value (true) or an operator (false) in each position
	private boolean[] bool_arr;

	public StringAndBoolReturn(int len) {
		super();
		this.str_arr = new String[len];
		this.bool_arr = new boolean[len];
	}

	public void setStr_arr(String str, int ind) {
		this.str_arr[ind] = str;
	}

	public void setBool_arr(boolean bool, int ind) {
		this.bool_arr[ind] = bool;
	}

	public String[] getStr_arr() {
		return str_arr;
	}

	public boolean[] getBool_arr() {
		return bool_arr;
	}
}

class OperatorNotDefinedException extends RuntimeException {
	public OperatorNotDefinedException(String msg) {
		super(msg);
	}
}

/**
 * Calculates an output based on an input string.
 *
 */
public class Calculator
{	
	private StringAndBoolReturn store(StringAndBoolReturn toStore, String valOrOp, int ind, boolean isValue) {
		toStore.setStr_arr(valOrOp, ind);
		toStore.setBool_arr(isValue, ind);
		return toStore;
	}
	
	/**
	 * Splits the string expression <b>sum</b> by operators (non-numeric characters), and stores these into an array <b>eval_arr</b>. Another array <b>num_op_arr</b> is used to store whether the string at each position is a numeric (true).
	 * @param sum
	 * @return <b>eval_arr</b>, <b>num_op_arr</b> as new <tt>StringAndBoolReturn</tt> object
	 */
	private StringAndBoolReturn splitNumeric(String sum) {
		// Stores the current value found
		String num_store = "";
		// Two arrays: one stores all values and operators to evaluate, the other stores whether it is a value (true) or an operator (false) in each position
		StringAndBoolReturn out = new StringAndBoolReturn(sum.length());
		// Position in array
		int pos = 0;
		
		for (int i = 0; i < sum.length(); i++) {
			Character c = sum.charAt(i);
			
			if (Character.isDigit(c) || String.valueOf(c).equals(".")) {
				// Still a number, add to storage
				num_store += c;
			} else if (Character.isAlphabetic(c)) {
				throw new OperatorNotDefinedException("Alphabet " + String.valueOf(c) + " cannot be used.");
			} else {
				if (i == 0) {
					// Operator at first position, don't break
					if (String.valueOf(c).equals("-") || String.valueOf(c).equals("+")) {
						num_store += c;
						continue;
					} else {
						throw new OperatorNotDefinedException("Operator " + String.valueOf(c) + " cannot be used in first position.");
					}
				}
				
				if (i == sum.length()-1) {
					throw new OperatorNotDefinedException("Operator " + String.valueOf(c) + " cannot be used in last position.");
				}
				
				// Broken apart by operator
				if (num_store.equals("") == false) {
					// Have a number, store number then operator
					store(out, num_store, pos, true);
					num_store = "";
					pos++;
				}
				// Store the operator
				store(out, String.valueOf(c), pos, false);
				pos++;
			}
			
			// A number at the end
			if (num_store.equals("") == false) {
				store(out, num_store, pos, true);
			}
		}
		
		return out;
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
		
		while (tofind.getBool_arr()[ind_1] == false) {
			ind_1--;
		}
		
		while (tofind.getBool_arr()[ind_2] == false) {
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
		
		default:
			throw new OperatorNotDefinedException("Operator " + op + " is not defined.");
		}
		
		return Double.toString(result);
	}
	
	/**
	 * Evaluate mathematical expression <b>sum</b> in the form of a string (no space please), supporting the operators '+', '-', '*', '/', '^' and brackets.
	 * @param sum
	 * @return ans
	 */
	public double eval(String sum) {
		StringAndBoolReturn sum_split = splitNumeric(sum);
		
		// Add bracket operation here
		
		// Power has next highest priority
		powerTwoNums(sum_split);
		
		// Then multiply and divide
		multiDivide(sum_split);
		
		// Then add and subtract
		addSubtract(sum_split);
		
		// Final output (will be store in the first element)
		double ans = Double.parseDouble(sum_split.getStr_arr()[0]);
		return ans;
	}
	
    public static void main( String[] args )
    {
        Calculator cal = new Calculator();
    	
    	// Insert test cases here
    	String inp = "-15+67*4^3-2.3^5";
        
        System.out.println(cal.eval(inp));
    }
}
