package com.adriel.calculator;

import com.adriel.calculator.exception.OperatorNotDefinedException;
import com.adriel.calculator.util.StringAndBoolReturn;

public class Splitter {
	
	private final String OPERATOR_NOT_DEFINED_MSG = "Operator %s cannot be used in first position, or after another operator.";
	private final String ALPHABET_OPERATOR_MSG = "Alphabet %s cannot be used.";
	private final String OPERATOR_IN_LAST_MSG = "Operator %s cannot be used in last position.";
	private final String MULTI_DECIMAL_MSG = "Multiple decimal points (%s) found in number %s.";
	private final String NO_OPEN_BRACKET_MSG = "Cannot find appropriate opening bracket %s for %s";
	
	protected Splitter() {}
	
	/**
	 * Splits the string expression <b>sum</b> by operators (non-numeric characters), and stores these into an array <b>eval_arr</b>. Another array <b>num_op_arr</b> is used to store whether the string at each position is a numeric (true).
	 * @param sum
	 * @return <b>eval_arr</b>, <b>num_op_arr</b> as new <tt>StringAndBoolReturn</tt> object
	 */
	public StringAndBoolReturn splitNumeric(String sum) {
		// Stores the current value found
		String numStore = "";
		// Two arrays: one stores all values and operators to evaluate, the other stores whether it is a value (true) or an operator (false) in each position
		StringAndBoolReturn splittedExpression = new StringAndBoolReturn(sum.length());
		// Position in array
		int pos = 0;
		// Boolean value to check whether previous char is an operator
		// If previous char is operator, next can only be +/-/digit
		boolean operatorAppeared = true;
		// After each operator, this indicates whether the positive / negaive number indicator has appeared
		boolean posNegAppeared = false;
		// Check whether decimal point has appeared
		boolean pointAppeared = false;
		// Check whether a left bracket equivalent has appeared
		boolean roundParenthesisAppeared = false;
		boolean squareBracketAppeared = false;
		boolean curlyBraceAppeared = false;
		
		for (int i = 0; i < sum.length(); i++) {
			Character c = sum.charAt(i);
			
			if (Character.isDigit(c)) {
				operatorAppeared = false;
				posNegAppeared = false;
				// Still a number, add to storage
				numStore += c;
			} else if (String.valueOf(c).equals(".")) {
				if (pointAppeared) {
					throw new OperatorNotDefinedException(String.format(MULTI_DECIMAL_MSG, String.valueOf(c), numStore));
				} else {
					operatorAppeared = false;
					posNegAppeared = false;
					// Still a number, add to storage
					numStore += c;
					pointAppeared = true;
				}
			} else if (Character.isAlphabetic(c)) {
				throw new OperatorNotDefinedException(String.format(ALPHABET_OPERATOR_MSG, String.valueOf(c)));
			} else if (String.valueOf(c).equals("(")) {
				if (roundParenthesisAppeared) {
					throw new OperatorNotDefinedException(String.format(OPERATOR_NOT_DEFINED_MSG, String.valueOf(c)));
				}
				roundParenthesisAppeared = true;
				splittedExpression.store(String.valueOf(c), false, pos);
				pos++;
			} else if (String.valueOf(c).equals(")")) {
				if (!roundParenthesisAppeared) {
					throw new OperatorNotDefinedException(String.format(NO_OPEN_BRACKET_MSG, "(", String.valueOf(c)));
				}
				roundParenthesisAppeared = false;
				// Store number (there must be a number before closing bracket, for any valid expression)
				splittedExpression.store(numStore, true, pos);
				numStore = "";
				pos++;
				splittedExpression.store(String.valueOf(c), false, pos);
				pos++;
			} else if (String.valueOf(c).equals("[")) {
				if (squareBracketAppeared || roundParenthesisAppeared) {
					throw new OperatorNotDefinedException(String.format(OPERATOR_NOT_DEFINED_MSG, String.valueOf(c)));
				}
				squareBracketAppeared = true;
				splittedExpression.store(String.valueOf(c), false, pos);
				pos++;
			} else if (String.valueOf(c).equals("]")) {
				if (!squareBracketAppeared) {
					throw new OperatorNotDefinedException(String.format(NO_OPEN_BRACKET_MSG, "[", String.valueOf(c)));
				}
				squareBracketAppeared = false;
				// Store number if found
				if (!"".equals(numStore)) {
					splittedExpression.store(numStore, true, pos);
					numStore = "";
					pos++;
				}
				splittedExpression.store(String.valueOf(c), false, pos);
				pos++;
			} else if (String.valueOf(c).equals("{")) {
				if (curlyBraceAppeared || squareBracketAppeared || roundParenthesisAppeared) {
					throw new OperatorNotDefinedException(String.format(OPERATOR_NOT_DEFINED_MSG, String.valueOf(c)));
				}
				curlyBraceAppeared = true;
				splittedExpression.store(String.valueOf(c), false, pos);
				pos++;
			} else if (String.valueOf(c).equals("}")) {
				if (!curlyBraceAppeared) {
					throw new OperatorNotDefinedException(String.format(NO_OPEN_BRACKET_MSG, "{", String.valueOf(c)));
				}
				curlyBraceAppeared = false;
				// Store number if found
				if (!"".equals(numStore)) {
					splittedExpression.store(numStore, true, pos);
					numStore = "";
					pos++;
				}
				splittedExpression.store(String.valueOf(c), false, pos);
				pos++;
			} else {
				if (operatorAppeared) {
					// Operator at first position, don't break
					if (!posNegAppeared && (String.valueOf(c).equals("-") || String.valueOf(c).equals("+"))) {
						posNegAppeared = true;
						numStore += c;
						continue;
					} else {
						throw new OperatorNotDefinedException(String.format(OPERATOR_NOT_DEFINED_MSG, String.valueOf(c)));
					}
				}
				
				if (i == sum.length()-1) {
					throw new OperatorNotDefinedException(String.format(OPERATOR_IN_LAST_MSG, String.valueOf(c)));
				}
				
				// Store number if found
				if (!"".equals(numStore)) {
					splittedExpression.store(numStore, true, pos);
					numStore = "";
					pos++;
				}
				// Store the operator
				splittedExpression.store(String.valueOf(c), false, pos);
				pos++;
				
				operatorAppeared = true;
				pointAppeared = false;
			}
			
			// A number at the end
			if (!"".equals(numStore)) {
				splittedExpression.store(numStore, true, pos);
			}
		}
		
		return splittedExpression;
	}
}
