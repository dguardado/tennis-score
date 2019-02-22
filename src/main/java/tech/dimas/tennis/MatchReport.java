package tech.dimas.tennis;

import java.util.Objects;

public class MatchReport {

    private final Player playerA = new Player();
    private final Player playerB = new Player();

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

   private void scorePointFor(char scorer) {
        if (scorer == 'A') {
            recordPoint(playerA, playerB);
        } else {
            recordPoint(playerB, playerA);
        }
    }

    private static void recordPoint(Player scorer, Player opponent) {
        scorer.scoreOn(opponent);
        opponent.getScoredOnBy(scorer);
        if (scorer.hasWonGame()) {
            scorer.win();
            opponent.lose();
        }
    }

    static MatchReport build(String points) {
        MatchReport matchReport = new MatchReport();
        points.chars().forEach(scorer -> matchReport.scorePointFor((char) scorer));
        return matchReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null
                || getClass() != o.getClass()) {
            return false;
        }

        MatchReport that = (MatchReport) o;

        return playerA.equals(that.playerA)
                && playerB.equals(that.playerB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerA, playerB);
    }

    @Override
    public String toString() {
        return "MatchReport{" +
                "playerA=" + playerA +
                ", playerB=" + playerB +
                '}';
    }
}
