/**
 * Created by saurabh on 3/25/18.
 */

package minesweeper;

import spark.Spark;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String args[]) {
        Spark.port(4567);
        get("/hello", (req, res) -> "Hello, World!!!");
    }
}
