package com.adriel.calculator.exception;

public class OperatorNotDefinedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810830455333884552L;

	public OperatorNotDefinedException(String msg) {
		super(msg);
	}
}
