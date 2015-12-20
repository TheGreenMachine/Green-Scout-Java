package com.edinarobotics.scouting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

public class InfoPanel extends JPanel {

    private static JList<Match> list;
    private static DefaultListModel<Match> listModel;
    private JScrollPane listScrollPane;
    private JPanel topPanel;
    private JButton editButton;
    private JButton dirButton;
    private JFileChooser directoryChooser;
    private static File directory;
    private FilenameFilter completedFilter;
    private FilenameFilter todoFilter;

    public InfoPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout(0, 0));

        ArrayList tmplist = new ArrayList();
        tmplist.add(new TeleopStack(5, true, false));

        listModel = new DefaultListModel();

        list = new JList();
        list.setModel(listModel);
        list.setCellRenderer(new MatchCellRenderer());

        list.setSelectionMode(0);
        list.setLayoutOrientation(0);

        this.listScrollPane = new JScrollPane(list);

        add(this.listScrollPane, "Center");

        setPreferredSize(new Dimension(163, 500));
        setBorder(new MatteBorder(0, 1, 0, 0, Color.BLACK));

        this.topPanel = new JPanel();
        this.topPanel.setLayout(new FlowLayout());

        this.editButton = new JButton("Edit / Load");
        this.editButton.setFont(new Font("SansSerif", 0, 11));
        this.editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.showConfirmDialog(InfoPanel.this.getParent(), "All unsaved data will be lost.",
                        "Continue?", 2) == 0)
                    ScoutingUIMain.getMasterMetricsPanel().loadMatch((Match) InfoPanel.list.getSelectedValue());
            }
        });
        this.dirButton = new JButton("Dir");
        this.dirButton.setFont(new Font("SansSerif", 0, 11));
        String userDir = System.getProperty("user.home");
        this.directoryChooser = new JFileChooser(userDir + "\\Desktop");
        this.directoryChooser.setPreferredSize(new Dimension(800, 500));
        this.directoryChooser.setDialogTitle("Choose Directory");
        this.directoryChooser.setFileSelectionMode(1);
        this.directoryChooser.setAcceptAllFileFilterUsed(false);

        if (this.directoryChooser.showOpenDialog(getParent()) == 0) {
            directory = this.directoryChooser.getSelectedFile();
            this.completedFilter = new FilenameFilter() {
                public boolean accept(File directory, String name) {
                    return (name.startsWith("Match")) && (Character.isLetter(name.charAt(name.length() - 5)));
                }
            };
            this.todoFilter = new FilenameFilter() {
                public boolean accept(File directory, String name) {
                    return (name.startsWith("Match")) && (Character.isDigit(name.charAt(name.length() - 5)));
                }
            };
            String[] completed = directory.list(this.completedFilter);
            String[] todo = directory.list(this.todoFilter);

            if (completed != null) {
                for (int i = 0; i < completed.length; i++) {
                    listModel.addElement(new Match(new File(directory + "\\" + completed[i]), true));
                }
            }

            if (todo != null) {
                for (int i = 0; i < todo.length; i++)
                    listModel.addElement(new Match(new File(directory + "\\" + todo[i]), false));
            }
        } else {
            System.exit(0);
        }

        this.dirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.showConfirmDialog(InfoPanel.this.getParent(), "All unsaved data will be lost.",
                        "Continue?", 2) == 0) {
                    if (InfoPanel.this.directoryChooser.showOpenDialog(InfoPanel.this.getParent()) == 0) {
                        InfoPanel.listModel.removeAllElements();
                        InfoPanel.directory = InfoPanel.this.directoryChooser.getSelectedFile();
                        InfoPanel.this.completedFilter = new FilenameFilter() {
                            public boolean accept(File directory, String name) {
                                return (name.startsWith("Match"))
                                        && (Character.isLetter(name.charAt(name.length() - 5)));
                            }
                        };
                        InfoPanel.this.todoFilter = new FilenameFilter() {
                            public boolean accept(File directory, String name) {
                                return (name.startsWith("Match"))
                                        && (Character.isDigit(name.charAt(name.length() - 5)));
                            }
                        };
                        String[] completed = InfoPanel.directory.list(InfoPanel.this.completedFilter);
                        String[] todo = InfoPanel.directory.list(InfoPanel.this.todoFilter);

                        if (completed != null) {
                            for (int i = 0; i < completed.length; i++) {
                                InfoPanel.listModel.addElement(
                                        new Match(new File(InfoPanel.directory + "\\" + completed[i]), true));
                            }
                        }

                        if (todo != null) {
                            for (int i = 0; i < todo.length; i++) {
                                InfoPanel.listModel
                                        .addElement(new Match(new File(InfoPanel.directory + "\\" + todo[i]), false));
                            }
                        }

                        ScoutingUIMain.getMasterMetricsPanel().loadNextMatch();
                    }
                }
            }
        });
        this.topPanel.add(this.dirButton);
        this.topPanel.add(this.editButton);
        this.topPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        add(this.topPanel, "North");
    }

    public void refreshList() {
        listModel.removeAllElements();

        this.completedFilter = new FilenameFilter() {
            public boolean accept(File directory, String name) {
                return (name.startsWith("Match")) && (Character.isLetter(name.charAt(name.length() - 5)));
            }
        };
        this.todoFilter = new FilenameFilter() {
            public boolean accept(File directory, String name) {
                return (name.startsWith("Match")) && (Character.isDigit(name.charAt(name.length() - 5)));
            }
        };
        String[] completed = directory.list(this.completedFilter);
        String[] todo = directory.list(this.todoFilter);

        if (completed != null) {
            for (int i = 0; i < completed.length; i++) {
                listModel.addElement(new Match(new File(directory + "\\" + completed[i]), true));
            }
        }

        if (todo != null)
            for (int i = 0; i < todo.length; i++)
                listModel.addElement(new Match(new File(directory + "\\" + todo[i]), false));
    }

    public static File getDirectory() {
        return directory;
    }

    public static JList<Match> getList() {
        return list;
    }

}
