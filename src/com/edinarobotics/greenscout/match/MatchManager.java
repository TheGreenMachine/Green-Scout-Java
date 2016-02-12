package com.edinarobotics.greenscout.match;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright Elliott Olson (c) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class MatchManager {

    private static List<Match> matches = new ArrayList<Match>();


    public static void saveMatch(Match match) {



    }

    public static void exportMatches() {

        List<Integer> teams = new ArrayList<Integer>();

        for (Match match : matches) {
            if (!teams.contains(match.getTeamNumber())) {
                teams.add(match.getTeamNumber());
            }
        }

        for (int i = 0; i < teams.size(); i++) {

            for (Match match : matches) {

                if (match.getTeamNumber() == teams.get(i)) {

                    System.out.println("Team Number: \tScore: \tMatchId");
                    System.out.println(match.getTeamNumber() +"\t" + match.getScore() + "\t" + match.getMatchNumber() + "\n");

                }

            }

        }

    }


    public static List<Match> getMatches() {
        return matches;
    }
}
