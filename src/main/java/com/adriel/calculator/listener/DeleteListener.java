package com.adriel.calculator.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteListener implements ActionListener {
	private JTextField textField;
	private JLabel msgLabel;
	
	public DeleteListener(JTextField textField, JLabel msgLabel) {
		this.textField = textField;
		this.msgLabel = msgLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String currentString = textField.getText();
		if (currentString != null && !currentString.isEmpty()) {
			textField.setText(currentString.substring(0, currentString.length()-1));
		}
		msgLabel.setText("");
	}

}
