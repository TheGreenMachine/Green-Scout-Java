package com.edinarobotics.scouting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Match {

    private String initials;
    private String notes;
    private String type;
    private String score;
    private String teamNumber;
    private String matchNumber;
    private boolean autoMovement;
    private boolean teleopCoOpStack;
    private boolean scouted;
    private int autoTotes;
    private int autoRCs;
    private int autoStepRCs;
    private int teleopStepRCs;
    private int teleopCoOp;
    private int penalties;
    private ArrayList<TeleopStack> stacks;

    public Match(String initials, String teamNumber, String matchNumber, boolean autoMovement, int autoTotes,
                 int autoRCs, int autoStepRCs, ArrayList<TeleopStack> stacks, int teleopStepRCs, int teleopCoOp,
                 boolean teleopCoOpStack, String notes, String type, int penalties, String score) {
        this.initials = initials.toUpperCase();
        this.teamNumber = teamNumber;
        this.matchNumber = matchNumber;
        this.autoMovement = autoMovement;
        this.autoTotes = autoTotes;
        this.autoRCs = autoRCs;
        this.autoStepRCs = autoStepRCs;
        this.stacks = stacks;
        this.teleopStepRCs = teleopStepRCs;
        this.teleopCoOp = teleopCoOp;
        this.teleopCoOpStack = teleopCoOpStack;
        this.notes = notes;
        this.type = type;
        this.penalties = penalties;
        this.score = score;
    }

    public Match(File file, boolean scouted) {
        this.scouted = scouted;
        if (scouted) {
            try {
                BufferedReader reader = Files.newBufferedReader(file.toPath(), null);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", new Object[]{e});
            }
        } else {
            this.initials = "";
            String path = file.getPath().toString();
            this.teamNumber = path.substring(path.indexOf("-", path.length() - 11) + 2, path.length() - 4);
            this.matchNumber = path.substring(path.indexOf(" ", path.length() - 16) + 1,
                    path.indexOf("-", path.length() - 11) - 1);
            this.autoMovement = false;
            this.autoTotes = 0;
            this.autoRCs = 0;
            this.autoStepRCs = 0;
            ArrayList stacksArray = new ArrayList(7);
            for (int i = 0; i < 7; i++)
                stacksArray.add(new TeleopStack(0, false, false));
            this.stacks = stacksArray;
            this.teleopStepRCs = 0;
            this.teleopCoOp = 0;
            this.teleopCoOpStack = false;
            this.notes = "";
            this.type = "";
            this.penalties = 0;
            this.score = "";
        }
    }

    public void saveToFile(File dir) {
        Path path = Paths.get(
                dir + "\\Match " + this.matchNumber + " - " + this.teamNumber + " - " + this.initials + ".txt",
                new String[0]);
        Path skeletonPath = Paths.get(dir + "\\Match " + this.matchNumber + " - " + this.teamNumber + ".txt",
                new String[0]);
        this.scouted = true;

        try {
            Files.deleteIfExists(skeletonPath);
            Files.deleteIfExists(path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(path, null, new OpenOption[0]);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", new Object[]{e});
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void isScouted(boolean scouted) {
        this.scouted = scouted;
    }

    public boolean isScouted() {
        return this.scouted;
    }

    public String toString() {
        if (!this.scouted)
            return "Match " + this.matchNumber + " - " + this.teamNumber;
        return "Match " + this.matchNumber + " - " + this.teamNumber + " - " + this.initials;
    }

    public String getInitials() {
        return this.initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isAutoMovement() {
        return this.autoMovement;
    }

    public void setAutoMovement(boolean autoMovement) {
        this.autoMovement = autoMovement;
    }

    public boolean isTeleopCoOpStack() {
        return this.teleopCoOpStack;
    }

    public void setTeleopCoOpStack(boolean teleopCoOpStack) {
        this.teleopCoOpStack = teleopCoOpStack;
    }

    public String getTeamNumber() {
        return this.teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getMatchNumber() {
        return this.matchNumber;
    }

    public void setMatchNumber(String matchNumber) {
        this.matchNumber = matchNumber;
    }

    public int getAutoTotes() {
        return this.autoTotes;
    }

    public void setAutoTotes(int autoTotes) {
        this.autoTotes = autoTotes;
    }

    public int getAutoRCs() {
        return this.autoRCs;
    }

    public void setAutoRCs(int autoRCs) {
        this.autoRCs = autoRCs;
    }

    public int getAutoStepRCs() {
        return this.autoStepRCs;
    }

    public void setAutoStepRCs(int autoStepRCs) {
        this.autoStepRCs = autoStepRCs;
    }

    public int getTeleopStepRCs() {
        return this.teleopStepRCs;
    }

    public void setTeleopStepRCs(int teleopStepRCs) {
        this.teleopStepRCs = teleopStepRCs;
    }

    public int getTeleopCoOp() {
        return this.teleopCoOp;
    }

    public void setTeleopCoOp(int teleopCoOp) {
        this.teleopCoOp = teleopCoOp;
    }

    public int getPenalties() {
        return this.penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<TeleopStack> getStacks() {
        return this.stacks;
    }

    public void setStacks(ArrayList<TeleopStack> stacks) {
        this.stacks = stacks;
    }

}
