package minesweeper.models;


/**
 * Created by saurabh on 4/7/18.
 */

public class Score {

    public Score(int id, String name) {
        this.ID = id;
        this.name = name;
    }

    private int ID;
    private String name;

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
}
