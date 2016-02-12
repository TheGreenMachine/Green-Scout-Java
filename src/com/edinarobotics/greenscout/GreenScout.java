package com.edinarobotics.greenscout;

import com.apple.eawt.Application;
import com.apple.eawt.FullScreenUtilities;
import com.edinarobotics.greenscout.panel.FooterPanel;
import com.edinarobotics.greenscout.panel.HeaderPanel;
import com.edinarobotics.greenscout.panel.InfoPanel;
import com.edinarobotics.greenscout.panel.MatchPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Copyright Elliott Olson (c) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class GreenScout extends JFrame {

    public static final int FRAME_WIDTH = 1100;
    public static final int FRAME_HEIGHT = 600;

    public static void main(String[] args) {
        GreenScout greenScout = new GreenScout();
        greenScout.setVisible(true);
    }

    public GreenScout() {
        initialize();
    }

    private void initialize() {
        setName("GreenScout Client");
        setResizable(false);
        setDefaultCloseOperation(3);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        FullScreenUtilities.setWindowCanFullScreen(this, true);

        try {
            Application application = Application.getApplication();
            application.setDockIconImage(ImageIO.read(new File("Images/OneEye.png")));
        } catch (Exception e){
            e.printStackTrace();
        }

        getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new InfoPanel(), BorderLayout.WEST);
        add(new MatchPanel(), BorderLayout.CENTER);
        add(new FooterPanel(), BorderLayout.SOUTH);

        pack();
    }

}
