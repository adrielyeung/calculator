package com.adriel.calculator.app;

import com.adriel.calculator.Calculator;
import com.adriel.calculator.CalculatorFactory;

public class Application {

	public static void main(String[] args) {
        Calculator cal = CalculatorFactory.getCalculator();
    	
    	// Insert test cases here
    	String inp = "(1.5*[2+3])/4.5";
        
        System.out.println(cal.eval(inp));
    }

}
