package minesweeper.models;


import java.sql.Timestamp;

/**
 * Created by saurabh on 4/7/18.
 */

public class Score {

    public Score(int id, String name, int rows, int cols, int mines, int seconds, Timestamp timestamp) {
        this.ID = id;
        this.name = name;
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.seconds = seconds;
        this.timestamp = timestamp.toString();
    }

    private int ID;
    private int rows;
    private int cols;
    private int mines;
    private int seconds;
    private String name;
    private String timestamp;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
