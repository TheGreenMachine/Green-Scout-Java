package com.edinarobotics.scouting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TeleopMetricsPanel extends JPanel {

    private static StackGridPanel stackGridPanel;
    private static ButtonSetPanel stepRCsPanel;
    private static ButtonSetPanel coOpPanel;
    private static JToggleButton stack;
    private static JToggleButton noneButton;
    private ArrayList<String> stepRCsStrings;
    private ArrayList<String> coOpStrings;
    private Image arrowImg;
    private ImageIcon arrow;
    private JLabel arrowLabel;
    private URL arrowURL;

    public TeleopMetricsPanel() {
        initialize();
    }

    private void initialize() {
        setPreferredSize(new Dimension(837, 225));
        setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        setLayout(null);

        JLabel vertical = new JLabel("TELEOP");
        vertical.setFont(new Font("SansSerif", 1, 17));
        vertical.setUI(new VerticalLabelUI());

        vertical.setBounds(5, 75, 35, 70);
        add(vertical);

        stackGridPanel = new StackGridPanel();
        stackGridPanel.setBounds(75, 10, 720, 140);
        add(stackGridPanel);

        this.stepRCsStrings = new ArrayList(5);
        this.stepRCsStrings.add("None");
        this.stepRCsStrings.add("One");
        this.stepRCsStrings.add("Two");
        this.stepRCsStrings.add("Three");
        this.stepRCsStrings.add("Four");
        stepRCsPanel = new ButtonSetPanel(this.stepRCsStrings, "Step RCs");
        stepRCsPanel.setBounds(15, 160, 375, 60);
        add(stepRCsPanel);

        this.coOpStrings = new ArrayList(4);
        this.coOpStrings.add("None");
        this.coOpStrings.add("One");
        this.coOpStrings.add("Two");
        this.coOpStrings.add("Three");
        coOpPanel = new ButtonSetPanel(this.coOpStrings, "Co-op");
        coOpPanel.setBounds(400, 160, 285, 60);
        add(coOpPanel);

        stack = new JToggleButton("Stack");
        stack.setFocusable(false);
        stack.setBounds(750, 174, 60, 30);
        stack.setEnabled(false);
        add(stack);

        noneButton = coOpPanel.getFirstButton();
        noneButton.setFocusable(false);
        noneButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (TeleopMetricsPanel.noneButton.isSelected()) {
                    TeleopMetricsPanel.stack.setSelected(false);
                    TeleopMetricsPanel.stack.setEnabled(false);
                } else {
                    TeleopMetricsPanel.stack.setEnabled(true);
                }
            }
        });
        this.arrowURL = getClass().getClassLoader().getResource("Images/arrow.png");
        try {
            this.arrowImg = ImageIO.read(new File("Images/arrow.png"));
            this.arrow = new ImageIcon(this.arrowImg);
            this.arrowLabel = new JLabel(this.arrow);
            this.arrowLabel.setBounds(680, 182, 65, 50);
            add(this.arrowLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        stackGridPanel.reset();
        stepRCsPanel.reset();
        coOpPanel.reset();
        stack.setSelected(false);
        stack.setEnabled(false);
    }

    public static StackGridPanel getStackGridPanel() {
        return stackGridPanel;
    }

    public static ButtonSetPanel getStepRCsPanel() {
        return stepRCsPanel;
    }

    public static ButtonSetPanel getCoOpPanel() {
        return coOpPanel;
    }

    public static JToggleButton getStack() {
        return stack;
    }

    public static JToggleButton getNoneButton() {
        return noneButton;
    }

}
