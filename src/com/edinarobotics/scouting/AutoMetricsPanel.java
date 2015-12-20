package com.edinarobotics.scouting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class AutoMetricsPanel extends JPanel {

	private static ButtonSetPanel movement;
	private static ButtonSetPanel totes;
	private static ButtonSetPanel rcs;
	private static ButtonSetPanel stepRCs;
	private ArrayList<String> movementStrings;
	private ArrayList<String> totesStrings;
	private ArrayList<String> rcsStrings;
	private ArrayList<String> stepRCsStrings;

	public AutoMetricsPanel() {
		initialize();
	}

	private void initialize() {
		setPreferredSize(new Dimension(837, 100));
		setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		setLayout(null);

		JLabel vertical = new JLabel("AUTO");
		vertical.setFont(new Font("SansSerif", 1, 17));
		vertical.setUI(new VerticalLabelUI());

		vertical.setBounds(5, 23, 35, 50);
		add(vertical);

		this.movementStrings = new ArrayList();
		this.movementStrings.add("No");
		this.movementStrings.add("Yes");

		this.totesStrings = new ArrayList();
		this.totesStrings.add("None");
		this.totesStrings.add("One");
		this.totesStrings.add("Two");
		this.totesStrings.add("Three");
		this.totesStrings.add("Stack");

		this.rcsStrings = new ArrayList();
		this.rcsStrings.add("None");
		this.rcsStrings.add("One");
		this.rcsStrings.add("Two");
		this.rcsStrings.add("Three");

		this.stepRCsStrings = new ArrayList();
		this.stepRCsStrings.add("None");
		this.stepRCsStrings.add("One");
		this.stepRCsStrings.add("Two");
		this.stepRCsStrings.add("Three");
		this.stepRCsStrings.add("Four");

		movement = new ButtonSetPanel(this.movementStrings, "Movement");
		totes = new ButtonSetPanel(this.totesStrings, "Totes");
		rcs = new ButtonSetPanel(this.rcsStrings, "RCs");
		stepRCs = new ButtonSetPanel(this.stepRCsStrings, "Step RCs");

		movement.setBounds(65, 12, 350, 30);
		totes.setBounds(470, 12, 350, 30);
		rcs.setBounds(60, 53, 350, 30);
		stepRCs.setBounds(435, 53, 400, 30);

		add(movement);
		add(totes);
		add(rcs);
		add(stepRCs);
	}

	public void reset() {
		movement.reset();
		totes.reset();
		rcs.reset();
		stepRCs.reset();
	}

	public static ButtonSetPanel getMovement() {
		return movement;
	}

	public static ButtonSetPanel getTotes() {
		return totes;
	}

	public static ButtonSetPanel getRcs() {
		return rcs;
	}

	public static ButtonSetPanel getStepRCs() {
		return stepRCs;
	}

}
