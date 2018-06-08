package Hanoi_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Hanoi_DB {

    public static boolean connected = false;
    private static Connection conn;

    public static void connectDB(){

        try{
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection("jdbc:sqlite:hanoi_data.db");

            Statement statement = conn.createStatement();

            String createSimulationTableSQL = "CREATE TABLE IF NOT EXISTS simulations(\n"
                    + " diskNumber integer PRIMARY KEY, \n"
                    + " steps blob not null" + ");";

            String createSimTimerTableSQL = "CREATE TABLE IF NOT EXISTS simulationTimer(\n"
                    + " id integer PRIMARY KEY, \n"
                    + " diskNumber integer not null, \n"
                    + " time real not null" + ");";

            statement.execute(createSimulationTableSQL);
            statement.execute(createSimTimerTableSQL);

            connected = true;
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void closeConnection() throws SQLException{
        conn.close();
    }
}
