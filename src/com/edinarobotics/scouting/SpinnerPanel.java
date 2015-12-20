package com.edinarobotics.scouting;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SpinnerPanel extends JPanel {

	private JSpinner spinner;
	private SpinnerNumberModel spinnerModel;

	public SpinnerPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new FlowLayout());

		this.spinnerModel = new SpinnerNumberModel(0, 0, 6, 1);
		this.spinner = new JSpinner(this.spinnerModel);

		Component[] comps = this.spinner.getEditor().getComponents();
		for (Component component : comps) {
			component.setFocusable(false);
		}

		this.spinner.setAlignmentY(0.5F);
		add(this.spinner);
	}

	public int getValue() {
		return Integer.parseInt(this.spinner.getValue().toString());
	}

	public void set(int i) {
		this.spinner.setValue(Integer.valueOf(i));
	}

	public void reset() {
		this.spinner.setValue(Integer.valueOf(0));
	}

}
