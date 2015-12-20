package com.edinarobotics.scouting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class GeneralMetricsPanel extends JPanel {

	private static JTextField teamNumber;
	private static JTextField matchNumber;
	private JLabel tLabel;
	private JLabel mLabel;
	private JLabel greenScoutLabel;
	private ImageIcon greenScout;
	private Image greenScoutImg;
	private URL greenScoutURL;
	private Font fontLabel;
	private Font fontArea;

	public GeneralMetricsPanel() {
		initialize();
	}

	private void initialize() {
		setPreferredSize(new Dimension(837, 50));
		setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		setLayout(new FlowLayout());

		this.fontLabel = new Font("SansSerif", 1, 12);
		this.fontArea = new Font("SansSerif", 0, 14);

		this.tLabel = new JLabel("Team Number");
		this.tLabel.setFont(this.fontLabel);
		this.mLabel = new JLabel("Match Number");
		this.mLabel.setFont(this.fontLabel);

		teamNumber = new JTextField(4);
		teamNumber.setDocument(new JTextFieldLimit(4));
		teamNumber.setEditable(false);
		teamNumber.setFocusable(false);
		teamNumber.setFont(this.fontArea);

		matchNumber = new JTextField(3);
		matchNumber.setDocument(new JTextFieldLimit(3));
		matchNumber.setEditable(false);
		matchNumber.setFocusable(false);
		matchNumber.setFont(this.fontArea);

		add(this.tLabel);
		add(teamNumber);
		add(Box.createRigidArea(new Dimension(40, 0)));
		add(this.mLabel);
		add(matchNumber);
		add(Box.createRigidArea(new Dimension(70, 0)));

		this.greenScoutURL = getClass().getClassLoader().getResource("Images/GreenScout.png");
		try {
			this.greenScoutImg = ImageIO.read(new File("Images/GreenScout.png")).getScaledInstance(372, 38, 1);
			this.greenScout = new ImageIcon(this.greenScoutImg);
			this.greenScoutLabel = new JLabel(this.greenScout);
			add(this.greenScoutLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		teamNumber.setText("");
		matchNumber.setText("");
	}

	public static JTextField getMatchNumber() {
		return matchNumber;
	}

	public static JTextField getTeamNumber() {
		return teamNumber;
	}

}
