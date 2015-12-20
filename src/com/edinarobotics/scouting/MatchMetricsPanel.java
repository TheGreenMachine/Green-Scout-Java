package com.edinarobotics.scouting;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MatchMetricsPanel extends JPanel {

	public static AutoMetricsPanel autoMetricsPanel;
	public static TeleopMetricsPanel teleopMetricsPanel;
	public static OverallMetricsPanel overallMetricsPanel;

	public MatchMetricsPanel() {
		initialize();
	}

	private void initialize() {
		setPreferredSize(new Dimension(837, 450));
		setLayout(new BorderLayout());

		autoMetricsPanel = new AutoMetricsPanel();
		add(autoMetricsPanel, "North");

		teleopMetricsPanel = new TeleopMetricsPanel();
		add(teleopMetricsPanel, "Center");

		overallMetricsPanel = new OverallMetricsPanel();
		add(overallMetricsPanel, "South");
	}

	public AutoMetricsPanel getAutoMetricsPanel() {
		return autoMetricsPanel;
	}

	public TeleopMetricsPanel getTeleopMetricsPanel() {
		return teleopMetricsPanel;
	}

	public OverallMetricsPanel getOverallMetricsPanel() {
		return overallMetricsPanel;
	}

	public void reset() {
		autoMetricsPanel.reset();
		teleopMetricsPanel.reset();
		overallMetricsPanel.reset();
	}

}
