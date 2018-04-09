/**
 * Created by saurabh on 3/25/18.
 */

package minesweeper;

import com.google.gson.Gson;
import minesweeper.models.ScoreList;
import minesweeper.utils.Utils;
import spark.Request;
import spark.ResponseTransformer;
import spark.Spark;

import static spark.Spark.*;

import java.sql.*;

import minesweeper.models.Score;

class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}


public class MinesweeperAPI {

    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/minesweeper";
        String user = "postgres";
        String password = "example";
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String args[]) {
        Spark.port(4567);

        path("/api", () -> {
            get("/get/topten", (request, response) -> {

                String sql = "SELECT * from scores order by seconds LIMIT 10";

                ScoreList scores = new ScoreList();

                try {
                    Connection conn = connect();
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()) {
                        int ID = rs.getInt("id");
                        int rows = rs.getInt("rows");
                        int cols = rs.getInt("cols");
                        int mines = rs.getInt("mines");
                        int seconds = rs.getInt("seconds");
                        Timestamp timestamp = rs.getTimestamp("timestamp");
                        String name = rs.getString("name");

                        Score score = new Score(ID, name, rows, cols, mines, seconds, timestamp);
                        scores.addScore(score);
                    }
                } catch(SQLException e) {
                    e.printStackTrace();
                }

                response.type("application/json");

                return scores;
            }, new JsonTransformer());


            post("/add/score", (request, response) -> {
                response.type("application/json");

                int id = -1;

                try{

                    Score score = new Gson().fromJson(request.body(), Score.class);

                    int rows = score.getRows();
                    int cols = score.getCols();
                    int noOfMines = score.getMines();
                    int seconds = score.getSeconds();
                    String name = score.getName();
                    
                    id = addScore(name, rows, cols, seconds, noOfMines);

                    if(!Utils.validateName(name)) {
                        response.status(400);
                    }
                } catch(NumberFormatException e) {
                    e.printStackTrace();
                    response.status(400);
                }
                return id;
            });

        });


    }

    private static int addScore(String name, int rows, int cols, int seconds, int noOfMines) {
        int id = 0;
        try {
            Connection conn = connect();
            String sql = "INSERT INTO scores(name, rows, cols, mines, seconds) values(?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setInt(2, rows);
            stmt.setInt(3, cols);
            stmt.setInt(4, noOfMines);
            stmt.setInt(5, seconds);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows > 0) {
                try(ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = Math.toIntExact(rs.getLong(1));
                        System.out.println("ID: " + id + " generated");
                        return id;
                    }
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
