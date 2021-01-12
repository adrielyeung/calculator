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

public class DeleteListenerTest extends TestCase {
	
	private DeleteListener testDeleteListener;
	@Mock
	private JTextField mockTextField;
	@Mock
	private JLabel mockLabel;
	@Mock
	private Calculator mockCalculator;
	@Mock
	private ActionEvent mockEvent;
	
	private static final String TEST = "test";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testDeleteListener = new DeleteListener(mockTextField, mockLabel);
	}
	
	@Test
	public void testDeleteListener() {
		try {
    		new DeleteListener(mockTextField, mockLabel);
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of DeleteListener" + e.getMessage());
    	}
	}
	
	@Test
	public void testActionPerformed() {
		when(mockTextField.getText()).thenReturn(TEST);
		testDeleteListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockTextField).setText("tes");
		Mockito.verify(mockLabel).setText("");
	}
	
	@Test
	public void testActionPerformedWithoutString() {
		when(mockTextField.getText()).thenReturn("");
		testDeleteListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockLabel).setText("");
	}
	
	@Test
	public void testActionPerformedNullString() {
		when(mockTextField.getText()).thenReturn(null);
		testDeleteListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockLabel).setText("");
	}
}
