package minesweeper.models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by saurabh on 4/8/18.
 */
public class ScoreList {
    private ArrayList<Score> scores;

    public ScoreList() {
        scores = new ArrayList<>();
    }

    public void addScore(Score s) {
        this.scores.add(s);
    }

    public Iterator<Score> getScores() {
        return scores.iterator();
    }
}
