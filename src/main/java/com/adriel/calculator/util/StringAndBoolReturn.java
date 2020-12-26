package com.adriel.calculator.util;

import java.util.Arrays;

/**
 * Stores a string array and a boolean array.
 * 
 * @author Adriel.Yeung
 *
 */
public class StringAndBoolReturn {
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

		@Override
		public String toString() {
			return "StringAndBoolReturn [str_arr=" + Arrays.toString(str_arr) + ", bool_arr="
					+ Arrays.toString(bool_arr) + "]";
		}
}
