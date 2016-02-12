package com.edinarobotics.greenscout.panel;

import com.edinarobotics.greenscout.GreenScout;
import com.edinarobotics.greenscout.document.JTextFieldLimit;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Copyright Elliott Olson (c) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class InfoPanel extends JPanel {

    private static InfoPanel instance;

    public InfoPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(new EmptyBorder(4, 4, 4, 4), new MatteBorder(0, 0, 0, 0, Color.BLACK)));
        setPreferredSize(new Dimension(200, GreenScout.FRAME_HEIGHT));

        JPanel top = new JPanel(new GridLayout(4, 2));

        JLabel label = new JLabel("Info Panel");
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setAlignmentX(0);
        label.setAlignmentY(0);
        top.add(label);

        top.add(Box.createRigidArea(new Dimension(GreenScout.FRAME_WIDTH / 2, 15)));

        final JLabel teamNumberLabel = new JLabel("Team #: ");
        teamNumberLabel.setFont(teamNumberLabel.getFont().deriveFont(Font.BOLD));
        top.add(teamNumberLabel);

        JTextField teamNumberField = new JTextField(4);
        teamNumberField.setDocument(new JTextFieldLimit(4));
        teamNumberField.setEditable(false);
        teamNumberField.setFocusable(false);
        teamNumberField.setText("1816");
        top.add(teamNumberField);

        JLabel matchNumberLabel = new JLabel("Match #: ");
        matchNumberLabel.setFont(matchNumberLabel.getFont().deriveFont(Font.BOLD));
        top.add(matchNumberLabel);

        JTextField matchNumberField = new JTextField(4);
        matchNumberField.setDocument(new JTextFieldLimit(4));
        matchNumberField.setEditable(false);
        matchNumberField.setFocusable(false);
        matchNumberField.setText("42");
        top.add(matchNumberField);

        JLabel allianceLabel = new JLabel("Alliance: ");
        allianceLabel.setFont(allianceLabel.getFont().deriveFont(Font.BOLD));
        top.add(allianceLabel);

        JTextField allianceField = new JTextField(4);
        allianceField.setDocument(new JTextFieldLimit(3));
        allianceField.setEditable(false);
        allianceField.setFocusable(false);
        allianceField.setBackground(Color.BLUE);
        top.add(allianceField);

        JPanel middle = new JPanel(new GridLayout(4, 1));

        middle.add(Box.createRigidArea(new Dimension(GreenScout.FRAME_WIDTH, 20)));
        middle.add(Box.createRigidArea(new Dimension(GreenScout.FRAME_WIDTH, 20)));

        JLabel settingLabel = new JLabel("Settings Panel");
        settingLabel.setFont(label.getFont().deriveFont(Font.BOLD));
        settingLabel.setAlignmentX(0);
        settingLabel.setAlignmentY(0);
        middle.add(settingLabel);

        middle.add(Box.createRigidArea(new Dimension(GreenScout.FRAME_WIDTH, 15)));

        JButton dirButton = new JButton("Directory");
        dirButton.setPreferredSize(new Dimension(GreenScout.FRAME_WIDTH / 2, 20));
        middle.add(dirButton);

        JButton editButton = new JButton("Edit / Load");
        editButton.setPreferredSize(new Dimension(GreenScout.FRAME_WIDTH / 2, 20));
        middle.add(editButton);

        middle.add(Box.createRigidArea(new Dimension(GreenScout.FRAME_WIDTH, 15)));

        JPanel bottom = new JPanel(new GridLayout(4, 1));

        bottom.add(Box.createRigidArea(new Dimension(GreenScout.FRAME_WIDTH, 10)));
        bottom.add(Box.createRigidArea(new Dimension(GreenScout.FRAME_WIDTH, 10)));

        JButton submitButton = new JButton("Save / Submit");
        submitButton.setFont(new Font("SansSerif", 1, 16));
        submitButton.setPreferredSize(new Dimension(GreenScout.FRAME_WIDTH, 50));
        submitButton.setBounds(0, GreenScout.FRAME_HEIGHT, GreenScout.FRAME_WIDTH, 100);
        bottom.add(submitButton);

        JButton resetButton = new JButton("Reset Match");
        resetButton.setFont(new Font("SansSerif", 1, 16));
        resetButton.setPreferredSize(new Dimension(GreenScout.FRAME_WIDTH, 50));
        resetButton.setBounds(0, GreenScout.FRAME_HEIGHT, GreenScout.FRAME_WIDTH, 100);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FooterPanel.getInstance().reset();
            }
        });

        bottom.add(resetButton);

        add(top, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

    }

    public void refresh() {

    }

    public void reset() {

    }

    public static InfoPanel getInstance() {
        if (instance == null) {
            instance = new InfoPanel();
        }
        return instance;
    }
}
