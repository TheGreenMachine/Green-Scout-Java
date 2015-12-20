package com.edinarobotics.scouting;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MatchCellRenderer extends JLabel implements ListCellRenderer<Object> {

	public MatchCellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Match match = (Match) value;

		if (match.isScouted())
			setFont(new Font("SansSerif", 0, 12));
		else {
			setFont(new Font("SansSerif", 3, 12));
		}
		setText(match.toString());
		if (isSelected) {
			setBackground(new Color(57, 105, 138));
			setForeground(Color.WHITE);
		} else {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}
		return this;
	}

}
