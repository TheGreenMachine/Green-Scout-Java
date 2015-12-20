package com.edinarobotics.scouting;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonSetPanel extends JPanel {

	private ArrayList<JToggleButton> buttonList;
	private ButtonGroup buttonGroup;
	private JLabel label;

	public ButtonSetPanel(ArrayList<String> buttonNames, String label) {
		this.buttonList = new ArrayList();
		for (String s : buttonNames) {
			this.buttonList.add(new JToggleButton(s));
		}

		this.label = new JLabel(label + "   ");
		initialize();
	}

	private void initialize() {
		setLayout(new GridBagLayout());
		this.label.setFont(new Font("SansSerif", 1, 14));
		add(this.label);

		this.buttonGroup = new ButtonGroup();

		boolean first = true;

		for (JToggleButton tb : this.buttonList) {
			if (first) {
				tb.setSelected(true);
				first = false;
			} else {
				tb.setSelected(false);
			}
			tb.setFont(new Font("SansSerif", 0, 12));
			tb.setFocusable(false);
			this.buttonGroup.add(tb);
			add(tb);
		}
	}

	public String getSelected() {
		for (JToggleButton tb : this.buttonList) {
			if (tb.isSelected())
				return tb.getText();
		}
		return "";
	}

	public void setSelected(int i) {
		((JToggleButton) this.buttonList.get(i)).setSelected(true);
	}

	public JToggleButton getFirstButton() {
		return (JToggleButton) this.buttonList.get(0);
	}

	public void reset() {
		boolean first = true;

		for (JToggleButton tb : this.buttonList)
			if (first) {
				tb.setSelected(true);
				first = false;
			} else {
				tb.setSelected(false);
			}
	}

}
