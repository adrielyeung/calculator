package com.adriel.calculator.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class NumberListener implements ActionListener {
	
	private JTextField textField;

	public NumberListener(JTextField textField) {
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String currentText = textField.getText();
		textField.setText(currentText + e.getActionCommand());
	}

}
