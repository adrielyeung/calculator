package com.adriel.calculator.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClearListener implements ActionListener {
	private JTextField textField;
	private JLabel msgLabel;
	
	public ClearListener(JTextField textField, JLabel msgLabel) {
		this.textField = textField;
		this.msgLabel = msgLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textField.setText("");
		msgLabel.setText("");
	}

}
