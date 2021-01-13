package com.adriel.calculator.listener;

import java.awt.Color;
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

public class CopyListenerTest extends TestCase {
	
	private final static String TEST = "test";
	
	private CopyListener testCopyListener;
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
		testCopyListener = new CopyListener(mockTextField, mockLabel);
	}
	
	@Test
	public void testCopyListener() {
		try {
    		new CopyListener(mockTextField, mockLabel);
    	} catch (Exception e) {
    		Assert.fail("Exception in creation of CopyListener" + e.getMessage());
    	}
	}
	
	@Test
	public void testActionPerformed() {
		when(mockLabel.getForeground()).thenReturn(Color.BLACK);
		when(mockLabel.getText()).thenReturn(TEST);
		testCopyListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockTextField).setText(TEST);
	}
	
	@Test
	public void testActionPerformedForErrorMessage() {
		when(mockLabel.getForeground()).thenReturn(Color.RED);
		when(mockLabel.getText()).thenReturn(TEST);
		testCopyListener.actionPerformed(mockEvent);
		
		Mockito.verify(mockTextField, never()).setText("");
	}
}
