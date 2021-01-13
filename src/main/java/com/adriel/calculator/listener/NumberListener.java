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
		int caretPosition = textField.getCaretPosition();
		String currentTextFirst = currentText.substring(0, caretPosition);
		String currentTextLast = currentText.substring(caretPosition);
		textField.setText(currentTextFirst + e.getActionCommand() + currentTextLast);
		textField.setCaretPosition(caretPosition+1);
	}

}
