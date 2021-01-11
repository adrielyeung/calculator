package com.adriel.calculator.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.adriel.calculator.CalculatorFactory;

public class Interface {
	
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private JLabel msgLabel;
	private CalculatorListener calculatorListener;
	
	protected Interface() {
		frame = new JFrame("Calculator");
		frame.setPreferredSize(new Dimension(800, 300));
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new GridLayout(0, 1));

		label = new JLabel("Please enter expression to calculate and then press 'Calculate' button below.");
		msgLabel = new JLabel();
		textField = new JTextField(750);
		button = new JButton("Calculate");
		calculatorListener = new CalculatorListener(textField, msgLabel, CalculatorFactory.getCalculator());
		button.addActionListener(calculatorListener);
		panel.add(label);
		panel.add(textField);
		panel.add(button);
		panel.add(msgLabel);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
}
