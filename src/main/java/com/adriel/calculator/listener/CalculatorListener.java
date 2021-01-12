package com.adriel.calculator.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.adriel.calculator.Calculator;

public class CalculatorListener implements ActionListener {
	
	private final static String NO_INPUT_MSG = "Please enter an expression.";
	
	private JTextField textField;
	private JLabel msgLabel;
	private String expression;
	private Calculator calculator;
	private double answer;
	
	public CalculatorListener(JTextField textField, JLabel msgLabel, Calculator calculator) {
		this.textField = textField;
		this.msgLabel = msgLabel;
		this.calculator = calculator;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		expression = textField.getText();
		if (expression == null || expression.isEmpty()) {
			msgLabel.setForeground(Color.RED);
			msgLabel.setText(NO_INPUT_MSG);
		}
		try {
			answer = calculator.eval(expression);
			msgLabel.setForeground(Color.BLACK);
			msgLabel.setText(String.valueOf(answer));
		} catch (Exception ex) {
			msgLabel.setForeground(Color.RED);
			msgLabel.setText(ex.getMessage());
		}
	}

}
