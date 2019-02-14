package tech.dimas.tennis;

public class TennisGame {
    public static MatchReport buildReport(String points) {
        if (points == null) {
            throw new IllegalArgumentException("points must not be mull!");
        }

        if (points.matches("[^AB]")) {
            throw new IllegalArgumentException("points must only contain strings of A and/or B");
        }

        return MatchReport.build(points);
    }

}
