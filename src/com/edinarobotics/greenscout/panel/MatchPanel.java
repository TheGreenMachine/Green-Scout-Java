package com.edinarobotics.greenscout.panel;

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
public class MatchPanel extends JPanel {

    private static MatchPanel instance;

    public MatchPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(new EmptyBorder(4, 4, 4, 4), new MatteBorder(0, 0, 0, 0, Color.BLACK)));

        JPanel top = new JPanel();
        JLabel label = new JLabel("This is a top title");
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        top.add(label);

        JPanel panel = new JPanel(new GridLayout(1, 2));

        add(top, BorderLayout.NORTH);
    }

    public void refresh() {

    }

    public void reset() {

    }

    public static MatchPanel getInstance() {
        if (instance == null) {
            instance = new MatchPanel();
        }
        return instance;
    }

}
