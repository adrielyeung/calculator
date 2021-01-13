package com.adriel.calculator.listener;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.adriel.calculator.Calculator;

import junit.framework.TestCase;

public class NumberListenerTest extends TestCase {
	
	private NumberListener testNumberListener;
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
		testNumberListener = new NumberListener(mockTextField);
	}
	
	@Test
	public void testNumberListener() {
		try {
    		new NumberListener(mockTextField);
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of NumberListener" + e.getMessage());
    	}
	}
	
	@Test
	public void testActionPerformed() {
		when(mockTextField.getText()).thenReturn("0");
		when(mockTextField.getCaretPosition()).thenReturn(1);
		when(mockEvent.getActionCommand()).thenReturn("1");
		
		testNumberListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockTextField).setText("01");
	}
}
