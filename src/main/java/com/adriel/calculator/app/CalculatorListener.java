package com.adriel.calculator.app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.adriel.calculator.Calculator;

public class CalculatorListener implements ActionListener {
	
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
