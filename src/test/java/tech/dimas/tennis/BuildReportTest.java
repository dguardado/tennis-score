package tech.dimas.tennis;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class BuildReportTest {

    @Test
    void emptyStringHasNoPoints() {
        MatchReport result = TennisGame.buildReport("");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("love", 0),
                        tuple("love", 0));
    }

    @Test
    void stringOf1Scores1Player() {
        MatchReport result = TennisGame.buildReport("A");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("15", 0),
                        tuple("love", 0));
    }

    @Test
    void onePlayerScoresMultiple() {
        MatchReport result = TennisGame.buildReport("BBB");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("love", 0),
                        tuple("40", 0));
    }

    @Test
    void bothPlayerScoreMultiple() {
        MatchReport result = TennisGame.buildReport("ABAB");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("30", 0),
                        tuple("30", 0));
    }

    @Test
    void playerAWins1() {
        MatchReport result = TennisGame.buildReport("AAAA");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("love", 1),
                        tuple("love", 0));
    }

    @Test
    void playerBWinsSeveral() {
        MatchReport result = TennisGame.buildReport("ABBBBBBBBBBBB");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("love", 0),
                        tuple("love", 3));
    }

    @Test
    void playerAScoresAfterLoss() {
        MatchReport result = TennisGame.buildReport("BABBBA");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("15", 0),
                        tuple("love", 1));
    }


    @Test
    void evenDeuce() {
        MatchReport result = TennisGame.buildReport("AAABBB");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("deuce", 0),
                        tuple("deuce", 0));
    }


    @Test
    void playerAAdvantage() {
        MatchReport result = TennisGame.buildReport("AAABBBA");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("advantage", 0),
                        tuple("", 0));
    }


    @Test
    void longDeuce() {
        MatchReport result = TennisGame.buildReport("AAABBBAB");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("deuce", 0),
                        tuple("deuce", 0));
    }


    @Test
    void playerBAdvantage() {
        MatchReport result = TennisGame.buildReport("AAABBBABB");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("", 0),
                        tuple("advantage", 0));
    }

    @Test
    void playerBLongWin() {
        MatchReport result = TennisGame.buildReport("AAABBBABBB");

        assertThat(result)
                .extracting("playerA", "playerB")
                .extracting("score", "games")
                .containsExactly(
                        tuple("love", 0),
                        tuple("love", 1));
    }
}
