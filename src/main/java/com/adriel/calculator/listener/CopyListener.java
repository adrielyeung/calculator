package com.adriel.calculator.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CopyListener implements ActionListener {
	private JTextField textField;
	private JLabel msgLabel;
	
	public CopyListener(JTextField textField, JLabel msgLabel) {
		this.textField = textField;
		this.msgLabel = msgLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// If error msg (Red colour), don't copy
		if (!Color.RED.equals(msgLabel.getForeground())) {
			textField.setText(msgLabel.getText());
		}
	}

}
