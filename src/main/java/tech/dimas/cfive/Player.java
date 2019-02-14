package tech.dimas.cfive;

import java.util.Objects;

public class Player {

    private static final String[] BASE_SCORES = new String[] { "love", "15", "30", "40" };
    private static final String[] DEUCE_SCORES = new String[] { "", "deuce", "advantage" };

    private int score;
    private int games;
    private int lead;

    public String getScore() {
        return isInDeuce()
                ? DEUCE_SCORES[lead + 1]
                : BASE_SCORES[score];
    }

    public int getGames() {
        return games;
    }

    void scoreOn(Player opponent) {
        score++;
        lead = score - opponent.score;
        opponent.lead = -lead;
    }

    public boolean hasWonGame() {
        return score > 3 && lead > 1;
    }

    public void win() {
        games++;
        score = 0;
    }

    public void lose() {
        score = 0;
    }

    private boolean isInDeuce() {
        return score >= 3 && lead < 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;

        return score == player.score
                && games == player.games;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, games);
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", games=" + games +
                '}';
    }
}
