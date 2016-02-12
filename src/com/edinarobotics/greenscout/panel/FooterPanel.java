package com.edinarobotics.greenscout.panel;

import com.edinarobotics.greenscout.GreenScout;
import com.edinarobotics.greenscout.document.JTextFieldLimit;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Copyright Elliott Olson (c) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class FooterPanel extends JPanel {

    private static FooterPanel instance;
    private JTextArea notes;
    private JTextField scoreField;
    private JTextField initialsField;
    private JSpinner penaltiesSpinner;

    public FooterPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 4, 4), new MatteBorder(0, 0, 0, 0, Color.WHITE)));
        setPreferredSize(new Dimension(GreenScout.FRAME_WIDTH, 100));
        setBounds(10, 10, GreenScout.FRAME_WIDTH, 100);

        notes = new JTextArea(4, 60);
        notes.setForeground(Color.GRAY);
        notes.setLineWrap(true);
        notes.setPreferredSize(new Dimension(200, 20));
        notes.setBounds(100, 100, 200, 20);
        notes.setAlignmentX(100);
        notes.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));

        //Placeholder for Notes TextArea
        PromptSupport.setPrompt("Put your notes here.", notes);
        PromptSupport.setFontStyle(Font.BOLD, notes);

        add(notes, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(2, 3));

        JLabel penalties = new JLabel("Penalties");
        panel.add(penalties);

        panel.add(Box.createRigidArea(new Dimension(30, 100)));

        JLabel score = new JLabel("Score");
        panel.add(score);

        panel.add(Box.createRigidArea(new Dimension(30, 100)));

        JLabel initials = new JLabel("Initials");
        panel.add(initials);

        SpinnerNumberModel penaltiesModel = new SpinnerNumberModel(0, 0, 99, 1);
        penaltiesSpinner = new JSpinner(penaltiesModel);
        panel.add(penaltiesSpinner);

        panel.add(Box.createRigidArea(new Dimension(30, 100)));

        scoreField = new JTextField();
        scoreField.setDocument(new JTextFieldLimit(3));
        panel.add(scoreField);

        panel.add(Box.createRigidArea(new Dimension(30, 100)));

        initialsField = new JTextField();
        initialsField.setDocument(new JTextFieldLimit(2));
        panel.add(initialsField);

        add(panel, BorderLayout.EAST);
    }

    public void refresh() {

    }

    public void reset() {
        notes.setText("Put your notes here.");
        scoreField.setText("");
        penaltiesSpinner.setValue(0);
    }

    public static FooterPanel getInstance() {
        if (instance == null) {
            instance = new FooterPanel();
        }
        return instance;
    }

}
