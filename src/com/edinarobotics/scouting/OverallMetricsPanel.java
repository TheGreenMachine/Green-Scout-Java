package com.edinarobotics.scouting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class OverallMetricsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JDynamicTextArea notePad;
    private static JTextField initials;
    private static JTextField score;
    private static JButton submit;
    private static JSpinner penalties;
    private JLabel scoreLabel;
    private JLabel penaltyLabel;
    private JLabel typeLabel;
    private JLabel initialsLabel;
    private SpinnerNumberModel spinmodel;
    private static JComboBox<?> type;

    public OverallMetricsPanel() {
        initialize();
    }

    private void initialize() {
        setPreferredSize(new Dimension(837, 125));
        setLayout(null);

        JLabel vertical = new JLabel("OVERALL");
        vertical.setFont(new Font("SansSerif", 1, 17));
        vertical.setUI(new VerticalLabelUI());

        vertical.setBounds(5, 23, 35, 80);
        add(vertical);

        notePad = new JDynamicTextArea(6, 30);
        notePad.setForeground(Color.GRAY);
        notePad.setLineWrap(true);
        notePad.setDocument(new JTextFieldLimit(250));
        notePad.setBounds(60, 13, 300, 100);
        notePad.setText("Put notes here...");
        add(notePad);

        submit = new JButton("Save / Submit");
        submit.setFont(new Font("SansSerif", 1, 16));
        submit.setBounds(375, 77, 450, 35);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (ScoutingUIMain.getMasterMetricsPanel().isComplete()) {
                    ScoutingUIMain.getMasterMetricsPanel().saveMatch();
                    ScoutingUIMain.getMasterMetricsPanel().resetAll();
                    ScoutingUIMain.getMasterMetricsPanel().loadNextMatch();
                } else {
                    JOptionPane.showMessageDialog(OverallMetricsPanel.this.getParent(), "Match not completed.", "Error",
                            0);
                }
            }
        });
        add(submit);
        submit.requestFocusInWindow();

        this.scoreLabel = new JLabel("Score");
        this.scoreLabel.setFont(new Font("SansSerif", 1, 16));
        this.scoreLabel.setBounds(673, 7, 60, 30);
        add(this.scoreLabel);

        initials = new JTextField(2);
        initials.setDocument(new JTextFieldFilter("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", 2));
        initials.setFont(new Font("SansSerif", 0, 16));
        initials.setHorizontalAlignment(0);
        initials.setBounds(758, 35, 55, 30);
        add(initials);

        this.initialsLabel = new JLabel("Initials");
        this.initialsLabel.setFont(new Font("SansSerif", 1, 16));
        this.initialsLabel.setBounds(761, 7, 60, 30);
        add(this.initialsLabel);

        this.spinmodel = new SpinnerNumberModel(0, 0, 99, 1);
        penalties = new JSpinner(this.spinmodel);
        penalties.setFont(new Font("SansSerif", 0, 16));
        penalties.setBounds(573, 35, 55, 30);
        add(penalties);

        this.penaltyLabel = new JLabel("Penalties");
        this.penaltyLabel.setFont(new Font("SansSerif", 1, 16));
        this.penaltyLabel.setBounds(563, 7, 80, 30);
        add(this.penaltyLabel);

        score = new JTextField();
        score.setDocument(new JTextFieldFilter("0123456789", 3));
        score.setHorizontalAlignment(0);
        score.setFont(new Font("SansSerif", 0, 16));
        score.setBounds(672, 35, 50, 30);
        add(score);

        String[] types = {"", "Feeder", "Landfill", "Topper", "Other"};
        type = new JComboBox(types);
        type.setEditable(false);
        type.setFocusable(false);
        type.setBounds(383, 35, 150, 30);
        type.setFont(new Font("SansSerif", 0, 16));
        add(type);

        this.typeLabel = new JLabel("Robot Type");
        this.typeLabel.setFont(new Font("SansSerif", 1, 16));
        this.typeLabel.setBounds(408, 7, 100, 30);
        add(this.typeLabel);
    }

    public void reset() {
        notePad.setText("Put notes here...");
        notePad.setForeground(Color.GRAY);
        type.setSelectedIndex(0);
        penalties.setValue(Integer.valueOf(0));
        score.setText("");
    }

    public static JButton getSubmit() {
        return submit;
    }

    public static JDynamicTextArea getNotePad() {
        return notePad;
    }

    public static JTextField getInitials() {
        return initials;
    }

    public static JTextField getScore() {
        return score;
    }

    public static JComboBox<?> getType() {
        return type;
    }

    public static JSpinner getPenalties() {
        return penalties;
    }

    protected class JDynamicTextArea extends JTextArea implements FocusListener {
        private static final long serialVersionUID = 1L;

        public JDynamicTextArea(int r, int c) {
            addFocusListener(this);
        }

        public void focusGained(FocusEvent e) {
            if (getText().equals("Put notes here...")) {
                setText("");
                OverallMetricsPanel.notePad.setForeground(Color.BLACK);
            }
        }

        public void focusLost(FocusEvent e) {
        }
    }

}
