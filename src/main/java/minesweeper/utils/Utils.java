package minesweeper.utils;

/**
 * Created by saurabh on 4/8/18.
 */
public class Utils {
    public static boolean validateName(String name) {
        if(name.isEmpty() || !name.matches("[a-zA-Z\\ ]")) {
            return false;
        }
        return true;
    }
}
