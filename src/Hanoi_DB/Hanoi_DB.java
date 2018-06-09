package Hanoi_DB;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import java.sql.*;

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
                    + " steps text not null" + ");";

            String createSimTimerTableSQL = "CREATE TABLE IF NOT EXISTS simulationTimer(\n"
                    + " id integer PRIMARY KEY AUTOINCREMENT, \n"
                    + " diskNumber integer not null, \n"
                    + " time integer not null" + ");";

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

    public static void insertSteps(int diskNum, String steps) throws SQLException{
        if(checkDisk(diskNum) == false){
            //System.out.println("Disk Doesn't exist");
            String insertRecord = ("insert into simulations(diskNumber, steps) values (?,?)");

            try(PreparedStatement preparedStatement = conn.prepareStatement(insertRecord)){
                preparedStatement.setInt(1, diskNum);

                preparedStatement.setString(2, steps);

                preparedStatement.executeUpdate();
            }
        }

    }

    public static void insertSimTim(int diskNum, long milliseconds) throws SQLException{

        String insertRecord = ("insert into simulationTimer(diskNumber,time) values(?,?)");

        try(PreparedStatement preparedStatement = conn.prepareStatement(insertRecord)){
            preparedStatement.setInt(1,diskNum);
            preparedStatement.setLong(2,milliseconds);
            preparedStatement.executeUpdate();
        }

    }

    private static boolean checkDisk(int diskNum) throws SQLException{

        boolean exists = false;

        Statement statement = conn.createStatement();
        ResultSet diskResult
                = statement.executeQuery("select count(*) as \"diskCount\" from simulations where diskNumber="
                +String.valueOf(diskNum));

        int resultSize = diskResult.getInt("diskCount");
        System.out.println(resultSize);
        if(resultSize>0){
            exists = true;
        }

        return exists;
    }
}
