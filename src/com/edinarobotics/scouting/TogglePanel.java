package com.edinarobotics.scouting;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TogglePanel extends JPanel {

	private JToggleButton btn;
	private int loc;
	private boolean noodle;

	public TogglePanel(int i, boolean isNoodle) {
		this.loc = i;
		this.noodle = isNoodle;
		initialize();
	}

	private void initialize() {
		setLayout(new FlowLayout());

		this.btn = new JToggleButton("No");
		this.btn.setFocusable(false);
		this.btn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (TogglePanel.this.btn.isSelected())
					TogglePanel.this.btn.setText("Yes");
				else
					TogglePanel.this.btn.setText("No");
			}
		});
		if (!this.noodle) {
			this.btn.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if (TogglePanel.this.btn.isSelected()) {
						TeleopMetricsPanel.getStackGridPanel();
						((TogglePanel) StackGridPanel.getNoodleToggles().get(TogglePanel.this.loc)).getBtn()
								.setEnabled(true);
					} else {
						TeleopMetricsPanel.getStackGridPanel();
						((TogglePanel) StackGridPanel.getNoodleToggles().get(TogglePanel.this.loc)).getBtn()
								.setSelected(false);
						TeleopMetricsPanel.getStackGridPanel();
						((TogglePanel) StackGridPanel.getNoodleToggles().get(TogglePanel.this.loc)).getBtn()
								.setEnabled(false);
					}
				}
			});
		}
		add(this.btn);
	}

	public JToggleButton getBtn() {
		return this.btn;
	}

	public int getLoc() {
		return this.loc;
	}

	public boolean getValue() {
		return this.btn.isSelected();
	}

	public void set(boolean selected) {
		this.btn.setSelected(selected);
	}

	public void reset() {
		this.btn.setSelected(false);
		this.btn.setText("No");
	}

}
