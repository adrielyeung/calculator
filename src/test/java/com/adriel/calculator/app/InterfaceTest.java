package com.adriel.calculator.app;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class InterfaceTest extends TestCase {
	
	@Test
	public void testCreateInterfaceFactory() {
		try {
	   		new InterfaceFactory();
	   	} catch (Exception e) {
	   		Assert.fail("Exception in creation of InterfaceFactory" + e.getMessage());
	   	}
	}
	
	@Test
	public void testInterface() {
		try {
    		new Interface();
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of Interface" + e.getMessage());
    	}
	}
	
}
