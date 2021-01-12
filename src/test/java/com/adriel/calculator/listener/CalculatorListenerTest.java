package com.adriel.calculator.listener;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adriel.calculator.Calculator;

import junit.framework.TestCase;

public class CalculatorListenerTest extends TestCase {
	
	private CalculatorListener testCalculatorListener;
	@Mock
	private JTextField mockTextField;
	@Mock
	private JLabel mockLabel;
	@Mock
	private Calculator mockCalculator;
	@Mock
	private ActionEvent mockEvent;
	
	private final static String TEST = "test";
	private final static String ERROR = "error";
	private final static double ANSWER = 2.0;
	private final static String MESSAGE = "Message";
	private final static String NO_INPUT_MSG = "Please enter an expression.";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testCalculatorListener = new CalculatorListener(mockTextField, mockLabel, mockCalculator);
	}
	
	@Test
	public void testCalculatorListener() {
		try {
    		new CalculatorListener(mockTextField, mockLabel, mockCalculator);
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of CalculatorListener" + e.getMessage());
    	}
	}
	
	@Test
	public void testActionPerformed() {
		when(mockTextField.getText()).thenReturn(TEST);
		when(mockCalculator.eval(TEST)).thenReturn(ANSWER);
		
		testCalculatorListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockLabel).setText(String.valueOf(ANSWER));
	}
	
	@Test
	public void testActionPerformedWithEmptyInput() {
		when(mockTextField.getText()).thenReturn("");
		
		testCalculatorListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockLabel).setText(String.valueOf(NO_INPUT_MSG));
	}
	
	@Test
	public void testActionPerformedWithNullInput() {
		when(mockTextField.getText()).thenReturn(null);
		
		testCalculatorListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockLabel).setText(String.valueOf(NO_INPUT_MSG));
	}
	
	@Test
	public void testActionPerformedWithError() {
		when(mockTextField.getText()).thenReturn(ERROR);
		when(mockCalculator.eval(ERROR)).thenThrow(new NullPointerException(MESSAGE));
		
		testCalculatorListener.actionPerformed(mockEvent);
		Mockito.verify(mockLabel).setText(MESSAGE);
	}
}
