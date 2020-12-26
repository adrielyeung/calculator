package com.adriel.calculator.app;

import com.adriel.calculator.Calculator;
import com.adriel.calculator.CalculatorFactory;

public class Application {

	public static void main(String[] args) {
        Calculator cal = CalculatorFactory.getCalculator();
    	
        double a = 0*-1;
        System.out.println(a);
        
    	// Insert test cases here
//    	String inp = "0.5*-+1.5";
        
//        System.out.println(cal.eval(inp));
    }

}
