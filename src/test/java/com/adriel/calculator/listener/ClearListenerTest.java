package com.adriel.calculator.listener;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adriel.calculator.Calculator;

import junit.framework.TestCase;

public class ClearListenerTest extends TestCase {
	
	private ClearListener testClearListener;
	@Mock
	private JTextField mockTextField;
	@Mock
	private JLabel mockLabel;
	@Mock
	private Calculator mockCalculator;
	@Mock
	private ActionEvent mockEvent;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testClearListener = new ClearListener(mockTextField, mockLabel);
	}
	
	@Test
	public void testClearListener() {
		try {
    		new ClearListener(mockTextField, mockLabel);
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of ClearListener" + e.getMessage());
    	}
	}
	
	@Test
	public void testActionPerformed() {
		testClearListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockTextField).setText("");
		Mockito.verify(mockLabel).setText("");
	}
}
