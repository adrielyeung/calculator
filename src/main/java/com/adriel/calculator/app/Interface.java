package com.adriel.calculator.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.adriel.calculator.CalculatorFactory;
import com.adriel.calculator.listener.CalculatorListener;
import com.adriel.calculator.listener.ClearListener;
import com.adriel.calculator.listener.DeleteListener;
import com.adriel.calculator.listener.NumberListener;

public class Interface {
	
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JTextField textField;
	private JButton calculateButton;
	private JPanel numberPanelTop;
	private JPanel numberPanelMiddle;
	private JPanel numberPanelBottom;
	private JLabel msgLabel;
	private CalculatorListener calculatorListener;
	
	protected Interface() {
		frame = new JFrame("Calculator");
		frame.setPreferredSize(new Dimension(1200, 600));
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new GridLayout(0, 1));

		label = new JLabel("Please enter expression to calculate and then press 'Calculate' button below.");
		msgLabel = new JLabel();
		textField = new JTextField(750);
		calculateButton = new JButton("Calculate");
		calculatorListener = new CalculatorListener(textField, msgLabel, CalculatorFactory.getCalculator());
		calculateButton.addActionListener(calculatorListener);
		
		numberPanelTop = new JPanel();
		numberPanelTop.setLayout(new GridLayout(0, 4));
		numberPanelMiddle = new JPanel();
		numberPanelMiddle.setLayout(new GridLayout(0, 4));
		numberPanelBottom = new JPanel();
		numberPanelBottom.setLayout(new GridLayout(0, 4));
		// Generate buttons for numbers, and operators
		numberPanelTop.add(generateNumberButton("("));
		numberPanelTop.add(generateNumberButton(")"));
		numberPanelTop.add(generateDeleteButton("DEL"));
		numberPanelTop.add(generateClearButton("AC"));
		
		numberPanelTop.add(generateNumberButton("["));
		numberPanelTop.add(generateNumberButton("]"));
		numberPanelTop.add(generateNumberButton("{"));
		numberPanelTop.add(generateNumberButton("}"));
		
		numberPanelMiddle.add(generateNumberButton("7"));
		numberPanelMiddle.add(generateNumberButton("8"));
		numberPanelMiddle.add(generateNumberButton("9"));
		numberPanelMiddle.add(generateNumberButton("/"));
		
		numberPanelMiddle.add(generateNumberButton("4"));
		numberPanelMiddle.add(generateNumberButton("5"));
		numberPanelMiddle.add(generateNumberButton("6"));
		numberPanelMiddle.add(generateNumberButton("*"));
		
		numberPanelBottom.add(generateNumberButton("1"));
		numberPanelBottom.add(generateNumberButton("2"));
		numberPanelBottom.add(generateNumberButton("3"));
		numberPanelBottom.add(generateNumberButton("-"));
		
		numberPanelBottom.add(generateNumberButton("0"));
		numberPanelBottom.add(generateNumberButton("00"));
		numberPanelBottom.add(generateNumberButton("."));
		numberPanelBottom.add(generateNumberButton("+"));
		
		panel.add(label);
		panel.add(textField);
		panel.add(numberPanelTop);
		panel.add(numberPanelMiddle);
		panel.add(numberPanelBottom);
		panel.add(calculateButton);
		panel.add(msgLabel);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private JButton generateButton(String value, ActionListener listener) {
		JButton button = new JButton(value);
		button.addActionListener(listener);
		return button;
	}
	
	private JButton generateClearButton(String value) {
		return generateButton(value, new ClearListener(textField, msgLabel));
	}
	
	private JButton generateDeleteButton(String value) {
		return generateButton(value, new DeleteListener(textField, msgLabel));
	}

	private JButton generateNumberButton(String value) {
		return generateButton(value, new NumberListener(textField));
	}
	
}
