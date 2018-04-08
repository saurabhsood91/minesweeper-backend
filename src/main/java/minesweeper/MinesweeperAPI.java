/**
 * Created by saurabh on 3/25/18.
 */

package minesweeper;

import com.google.gson.Gson;
import minesweeper.models.ScoreList;
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
        get("/", (request, response) -> {

            String sql = "SELECT * from scores";

            ScoreList scores = new ScoreList();

            try {
                Connection conn = connect();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()) {
                   int ID = rs.getInt("id");
                   String name = rs.getString("name");
                   Score score = new Score(ID, name);
                   scores.addScore(score);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }

            response.type("application/json");

            return scores;
        }, new JsonTransformer());
    }
}
