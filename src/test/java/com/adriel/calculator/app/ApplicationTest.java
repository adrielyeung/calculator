package com.adriel.calculator.app;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class ApplicationTest extends TestCase {

	@Test
	public void testApplication() {
		try {
    		new Application();
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of Application" + e.getMessage());
    	}
	}
	
	@Test
	public void testMain() {
		try {
    		Application.main(new String[1]);
    	} catch (Exception e) {
    		Assert.fail("Exception in main" + e.getMessage());
    	}
	}
}
