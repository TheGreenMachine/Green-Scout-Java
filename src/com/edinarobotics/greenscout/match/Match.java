package com.edinarobotics.greenscout.match;

/**
 * Copyright Elliott Olson (c) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class Match {

    private int teamNumber;
    private int matchNumber;
    private int score;
    private int penalties;
    private Alliance alliance;
    private String notes;
    private String initials;
    private boolean autoMovement;
    private boolean scouted;

    public Match(int teamNumber, int matchNumber, int score, int penalties, Alliance alliance, String notes, String initials,
                 boolean autoMovement, boolean scouted) {
        this.teamNumber = teamNumber;
        this.matchNumber = matchNumber;
        this.score = score;
        this.penalties = penalties;
        this.alliance = alliance;
        this.notes = notes;
        this.initials = initials;
        this.autoMovement = autoMovement;
        this.scouted = scouted;
    }

    public enum Alliance {
        BLUE, RED;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public boolean isAutoMovement() {
        return autoMovement;
    }

    public void setAutoMovement(boolean autoMovement) {
        this.autoMovement = autoMovement;
    }

    public boolean isScouted() {
        return scouted;
    }

    public void setScouted(boolean scouted) {
        this.scouted = scouted;
    }
}
