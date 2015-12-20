package com.edinarobotics.scouting;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class StackGridPanel extends JPanel {

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel totesLabel;
	private JLabel rcLabel;
	private JLabel noodleLabel;
	private Font labelFont;
	private static ArrayList<TogglePanel> rcToggles;
	private static ArrayList<TogglePanel> noodleToggles;
	private static ArrayList<SpinnerPanel> totesSpinners;
	private JPanel blankPanel;

	public StackGridPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new GridLayout(4, 8));

		this.blankPanel = new JPanel();
		this.blankPanel.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));

		this.labelFont = new Font("SansSerif", 1, 14);

		this.label1 = new JLabel("Stack 1");
		this.label1.setAlignmentX(0.5F);
		this.label1.setAlignmentY(0.5F);
		this.label1.setFont(this.labelFont);
		this.label1.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.label1.setHorizontalAlignment(0);

		this.label2 = new JLabel("Stack 2");
		this.label2.setFont(this.labelFont);
		this.label2.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.label2.setHorizontalAlignment(0);

		this.label3 = new JLabel("Stack 3");
		this.label3.setFont(this.labelFont);
		this.label3.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.label3.setHorizontalAlignment(0);

		this.label4 = new JLabel("Stack 4");
		this.label4.setFont(this.labelFont);
		this.label4.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.label4.setHorizontalAlignment(0);

		this.label5 = new JLabel("Stack 5");
		this.label5.setFont(this.labelFont);
		this.label5.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.label5.setHorizontalAlignment(0);

		this.label6 = new JLabel("Stack 6");
		this.label6.setFont(this.labelFont);
		this.label6.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.label6.setHorizontalAlignment(0);

		this.label7 = new JLabel("Stack 7");
		this.label7.setFont(this.labelFont);
		this.label7.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.label7.setHorizontalAlignment(0);

		this.totesLabel = new JLabel("Totes");
		this.totesLabel.setFont(this.labelFont);
		this.totesLabel.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.totesLabel.setHorizontalAlignment(0);

		this.rcLabel = new JLabel("RC?");
		this.rcLabel.setFont(this.labelFont);
		this.rcLabel.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.rcLabel.setHorizontalAlignment(0);

		this.noodleLabel = new JLabel("Noodle?");
		this.noodleLabel.setFont(this.labelFont);
		this.noodleLabel.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		this.noodleLabel.setHorizontalAlignment(0);

		rcToggles = new ArrayList(7);
		noodleToggles = new ArrayList(7);
		totesSpinners = new ArrayList(7);

		for (int i = 0; i < 7; i++) {
			rcToggles.add(new TogglePanel(i, false));
			((TogglePanel) rcToggles.get(i)).setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
			noodleToggles.add(new TogglePanel(i, true));
			((TogglePanel) noodleToggles.get(i)).setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
			totesSpinners.add(new SpinnerPanel());
			((SpinnerPanel) totesSpinners.get(i)).setBorder(new MatteBorder(0, 0, 1, 1, Color.BLACK));
		}

		add(this.blankPanel);
		add(this.label1);
		add(this.label2);
		add(this.label3);
		add(this.label4);
		add(this.label5);
		add(this.label6);
		add(this.label7);
		add(this.totesLabel);
		add((Component) totesSpinners.get(0));
		add((Component) totesSpinners.get(1));
		add((Component) totesSpinners.get(2));
		add((Component) totesSpinners.get(3));
		add((Component) totesSpinners.get(4));
		add((Component) totesSpinners.get(5));
		add((Component) totesSpinners.get(6));
		add(this.rcLabel);
		add((Component) rcToggles.get(0));
		add((Component) rcToggles.get(1));
		add((Component) rcToggles.get(2));
		add((Component) rcToggles.get(3));
		add((Component) rcToggles.get(4));
		add((Component) rcToggles.get(5));
		add((Component) rcToggles.get(6));
		add(this.noodleLabel);
		add((Component) noodleToggles.get(0));
		add((Component) noodleToggles.get(1));
		add((Component) noodleToggles.get(2));
		add((Component) noodleToggles.get(3));
		add((Component) noodleToggles.get(4));
		add((Component) noodleToggles.get(5));
		add((Component) noodleToggles.get(6));
	}

	public void reset() {
		for (TogglePanel t : rcToggles) {
			t.reset();
		}
		for (TogglePanel t : noodleToggles) {
			t.reset();
		}
		for (SpinnerPanel s : totesSpinners)
			s.reset();
	}

	public void set(ArrayList<TeleopStack> list) {
		for (int i = 0; i < 7; i++) {
			((SpinnerPanel) totesSpinners.get(i)).set(((TeleopStack) list.get(i)).getTotes());
			((TogglePanel) rcToggles.get(i)).set(((TeleopStack) list.get(i)).getRC());
			if (!((TogglePanel) rcToggles.get(i)).getBtn().isSelected()) {
				((TogglePanel) noodleToggles.get(i)).getBtn().setEnabled(false);
			}
			((TogglePanel) noodleToggles.get(i)).set(((TeleopStack) list.get(i)).getNoodle());
		}
	}

	public static ArrayList<TogglePanel> getRcToggles() {
		return rcToggles;
	}

	public static ArrayList<TogglePanel> getNoodleToggles() {
		return noodleToggles;
	}

	public static ArrayList<SpinnerPanel> getTotesSpinners() {
		return totesSpinners;
	}

}
