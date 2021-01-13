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
		String currentText = textField.getText();
		int caretPosition = textField.getCaretPosition();
		if (currentText != null && !currentText.isEmpty()) {
			String currentTextFirst = currentText.substring(0, textField.getCaretPosition()-1);
			String currentTextLast = currentText.substring(textField.getCaretPosition());
			textField.setText(currentTextFirst + currentTextLast);
			textField.setCaretPosition(caretPosition-1);
		}
		msgLabel.setText("");
	}

}
