package com.edinarobotics.scouting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class ScoutingUIMain {

	private JFrame frame;
	private static InfoPanel infoPanel;
	private static MasterMetricsPanel masterMetricsPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
							if ("Nimbus".equals(info.getName())) {
								UIManager.setLookAndFeel(info.getClassName());
								break;
							}
						}
					} catch (Exception localException1) {
					}
					ScoutingUIMain window = new ScoutingUIMain();
					window.frame.setVisible(true);
					OverallMetricsPanel.getSubmit().requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScoutingUIMain() {
		initialize();
	}

	private void initialize() {
		this.frame = new JFrame("GreenScout Client");
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(3);

		this.frame.getContentPane().setPreferredSize(new Dimension(1000, 500));
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.getContentPane().setLayout(new BorderLayout());

		infoPanel = new InfoPanel();
		this.frame.getContentPane().add(infoPanel, "East");

		masterMetricsPanel = new MasterMetricsPanel();
		this.frame.getContentPane().add(masterMetricsPanel, "Center");

		masterMetricsPanel.loadNextMatch();
	}

	public static MasterMetricsPanel getMasterMetricsPanel() {
		return masterMetricsPanel;
	}

	public static InfoPanel getInfoPanel() {
		return infoPanel;
	}

}
