package com.edinarobotics.greenscout.panel;

import com.edinarobotics.greenscout.GreenScout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.File;

/**
 * Copyright Elliott Olson (c) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class HeaderPanel extends JPanel {

    private static HeaderPanel instance;

    public HeaderPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(new EmptyBorder(4, 4, 4, 4), new MatteBorder(0, 0, 0, 0, Color.BLACK)));

        setPreferredSize(new Dimension(GreenScout.FRAME_WIDTH, 75));

        float[] f = Color.RGBtoHSB(2, 114, 35, null);
        setBackground(Color.getHSBColor(f[0], f[1], f[2]));

        try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("Images/GreenScout.png")).getScaledInstance(372, 38, 1)));
            add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setForeground(Color.WHITE);

    }

    public void refresh() {

    }

    public void reset() {

    }

    public static HeaderPanel getInstance() {
        if (instance == null) {
            instance = new HeaderPanel();
        }
        return instance;
    }
}
