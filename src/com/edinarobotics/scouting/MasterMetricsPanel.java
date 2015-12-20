package com.edinarobotics.scouting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

public class MasterMetricsPanel extends JPanel {

	public static GeneralMetricsPanel generalMetricsPanel;
	public static MatchMetricsPanel matchMetricsPanel;

	public MasterMetricsPanel() {
		initialize();
	}

	private void initialize() {
		setPreferredSize(new Dimension(837, 500));
		setLayout(new BorderLayout());

		generalMetricsPanel = new GeneralMetricsPanel();
		add(generalMetricsPanel, "North");

		matchMetricsPanel = new MatchMetricsPanel();
		add(matchMetricsPanel, "Center");
	}

	public GeneralMetricsPanel getGeneralMetricsPanel() {
		return generalMetricsPanel;
	}

	public MatchMetricsPanel getMatchMetricsPanel() {
		return matchMetricsPanel;
	}

	public boolean isComplete() {
		if ((!OverallMetricsPanel.getNotePad().getText().equals("Put notes here..."))
				&& (OverallMetricsPanel.getType().getSelectedIndex() != 0)
				&& (!OverallMetricsPanel.getScore().getText().equals(""))
				&& (!OverallMetricsPanel.getInitials().getText().equals(""))) {
			return true;
		}
		return false;
	}

	public void resetAll() {
		generalMetricsPanel.reset();
		matchMetricsPanel.reset();
	}

	public void saveMatch() {
		String initials = OverallMetricsPanel.getInitials().getText();

		String teamNumber = GeneralMetricsPanel.getTeamNumber().getText();

		String matchNumber = GeneralMetricsPanel.getMatchNumber().getText();

		boolean autoMovement = AutoMetricsPanel.getMovement().getSelected().equals("Yes");
		int autoTotes;
		if (AutoMetricsPanel.getTotes().getSelected().equals("Stack")) {
			autoTotes = 4;
		} else {
			if (AutoMetricsPanel.getTotes().getSelected().equals("Three")) {
				autoTotes = 3;
			} else {
				if (AutoMetricsPanel.getTotes().getSelected().equals("Two")) {
					autoTotes = 2;
				} else {
					if (AutoMetricsPanel.getTotes().getSelected().equals("One"))
						autoTotes = 1;
					else
						autoTotes = 0;
				}
			}
		}
		int autoRCs;
		if (AutoMetricsPanel.getRcs().getSelected().equals("Three")) {
			autoRCs = 3;
		} else {
			if (AutoMetricsPanel.getRcs().getSelected().equals("Two")) {
				autoRCs = 2;
			} else {
				if (AutoMetricsPanel.getRcs().getSelected().equals("One"))
					autoRCs = 1;
				else
					autoRCs = 0;
			}
		}
		int autoStepRCs;
		if (AutoMetricsPanel.getStepRCs().getSelected().equals("Four")) {
			autoStepRCs = 4;
		} else {
			if (AutoMetricsPanel.getStepRCs().getSelected().equals("Three")) {
				autoStepRCs = 3;
			} else {
				if (AutoMetricsPanel.getStepRCs().getSelected().equals("Two")) {
					autoStepRCs = 2;
				} else {
					if (AutoMetricsPanel.getStepRCs().getSelected().equals("One"))
						autoStepRCs = 1;
					else
						autoStepRCs = 0;
				}
			}
		}
		ArrayList stacks = new ArrayList(7);
		for (int i = 0; i < 7; i++)
			stacks.add(new TeleopStack(((SpinnerPanel) StackGridPanel.getTotesSpinners().get(i)).getValue(),
					((TogglePanel) StackGridPanel.getRcToggles().get(i)).getValue(),
					((TogglePanel) StackGridPanel.getNoodleToggles().get(i)).getValue()));
		int teleopStepRCs;
		if (TeleopMetricsPanel.getStepRCsPanel().getSelected().equals("Four")) {
			teleopStepRCs = 4;
		} else {
			if (TeleopMetricsPanel.getStepRCsPanel().getSelected().equals("Three")) {
				teleopStepRCs = 3;
			} else {
				if (TeleopMetricsPanel.getStepRCsPanel().getSelected().equals("Two")) {
					teleopStepRCs = 2;
				} else {
					if (TeleopMetricsPanel.getStepRCsPanel().getSelected().equals("One"))
						teleopStepRCs = 1;
					else
						teleopStepRCs = 0;
				}
			}
		}
		int i;
		if (TeleopMetricsPanel.getCoOpPanel().getSelected().equals("Three"))
			i = 3;
		if (TeleopMetricsPanel.getCoOpPanel().getSelected().equals("Three"))
			i = 2;
		int teleopCoOp;
		if (TeleopMetricsPanel.getCoOpPanel().getSelected().equals("Three"))
			teleopCoOp = 1;
		else {
			teleopCoOp = 0;
		}
		boolean teleopCoOpStack = TeleopMetricsPanel.getStack().isSelected();

		String notes = OverallMetricsPanel.getNotePad().getText();
		notes = notes.replaceAll("\\s{2,}", " ");

		String type = OverallMetricsPanel.getType().getSelectedItem().toString();

		int penalties = ((Integer) OverallMetricsPanel.getPenalties().getValue()).intValue();

		String score = OverallMetricsPanel.getScore().getText();

		Match match = new Match(initials, teamNumber, matchNumber, autoMovement, autoTotes, autoRCs, autoStepRCs,
				stacks, teleopStepRCs, teleopCoOp, teleopCoOpStack, notes, type, penalties, score);

		match.saveToFile(InfoPanel.getDirectory());
		ScoutingUIMain.getInfoPanel().refreshList();
	}

	public void loadNextMatch() {
		JList list = InfoPanel.getList();
		ListModel model = list.getModel();

		boolean unscouted = false;
		int i = 0;
		while ((!unscouted) && (i < model.getSize()) && (model.getElementAt(i) != null)) {
			Match tmp = (Match) model.getElementAt(i);
			if (tmp.getInitials().equals("")) {
				unscouted = true;
				loadMatch(tmp);
			}
			i++;
		}

		if (!unscouted)
			OverallMetricsPanel.getSubmit().setEnabled(false);
	}

	public void loadMatch(Match match) {
		OverallMetricsPanel.getSubmit().setEnabled(true);
		GeneralMetricsPanel.getMatchNumber().setText(match.getMatchNumber());
		GeneralMetricsPanel.getTeamNumber().setText(match.getTeamNumber());
		AutoMetricsPanel.getMovement().setSelected(match.isAutoMovement() ? 1 : 0);
		AutoMetricsPanel.getTotes().setSelected(match.getAutoTotes());
		AutoMetricsPanel.getRcs().setSelected(match.getAutoRCs());
		AutoMetricsPanel.getStepRCs().setSelected(match.getAutoStepRCs());
		TeleopMetricsPanel.getStackGridPanel().set(match.getStacks());
		TeleopMetricsPanel.getStepRCsPanel().setSelected(match.getTeleopStepRCs());
		TeleopMetricsPanel.getCoOpPanel().setSelected(match.getTeleopCoOp());
		TeleopMetricsPanel.getStack().setSelected(match.isTeleopCoOpStack());
		OverallMetricsPanel.getNotePad().setText(match.getNotes().equals("") ? "Put notes here..." : match.getNotes());

		if (match.getNotes().equals(""))
			OverallMetricsPanel.getNotePad().setForeground(Color.GRAY);
		else
			OverallMetricsPanel.getNotePad().setForeground(Color.BLACK);

		OverallMetricsPanel.getType().setSelectedItem(match.getType());
		OverallMetricsPanel.getPenalties().setValue(Integer.valueOf(match.getPenalties()));
		OverallMetricsPanel.getScore().setText(match.getScore());
		OverallMetricsPanel.getInitials().setText(match.getInitials());
	}

}
