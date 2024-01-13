package DAO;

import DTO.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

public class DataAccessObject {

    public static boolean isUserExist(String playerName) {
        String str = "name";
        boolean isExist = false;
        try {
            DriverManager.registerDriver(new ClientDriver());
            Connection connectToDB = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/Player", "root", "root");
            PreparedStatement sqlStatement = connectToDB.prepareStatement(
                    "SELECT * FROM PLAYER where NAME = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlStatement.setString(1, playerName);
            ResultSet resSet = sqlStatement.executeQuery();

            while (resSet.next()) {
                if (resSet.getString(str).equals(playerName)) {
                    isExist = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }

    public static boolean isUserValid(String playerName, String playerPassword) throws SQLException {
        String str = "password";
        boolean isValid = false;
        Connection connectToDB = null;
        PreparedStatement sqlStatement = null;
        try {
            DriverManager.registerDriver(new ClientDriver());
            connectToDB = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/Player", "root", "root");
            sqlStatement = connectToDB.prepareStatement(
                    "SELECT password from player where name = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlStatement.setString(1, playerName);
            ResultSet resSet = sqlStatement.executeQuery();
            while (resSet.next()) {
                if (resSet.getString(str).equals(playerPassword)) {
                    isValid = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        sqlStatement.close();
        connectToDB.close();
        return isValid;
    }

    //edit m 
    public static int addUser(String playerName, String password) throws SQLException {
        int result = 0;
        int score = 0;
        DriverManager.registerDriver(new ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("INSERT INTO player (name, password,score,available,online) VALUES (?,?,?,?,?)");
        ps.setString(1, playerName);
        ps.setString(2, password);
        ps.setInt(3, score);
        ps.setBoolean(4, false);
        ps.setBoolean(5, false);
        result = ps.executeUpdate();
        ps.close();
        con.close();
        return result;
    }

    public static ResultSet getAvailable() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        //connection
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/phoneIndex", "root", "root");

        PreparedStatement ps = con.prepareStatement("SELECT player_name from user_Data where available=true and Online=true", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = ps.executeQuery();
        return result;

    }

    public static List<String> getAvailableList() throws SQLException {
        ResultSet resultSet;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Player where  available = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setBoolean(1, true);
        resultSet = ps.executeQuery();
        List<String> availableList = new ArrayList<>();
        while (resultSet.next()) {

            String availablePlayers = resultSet.getString("Name") + ":" + resultSet.getString("score");
            availableList.add(availablePlayers);
        }
        ps.close();
        con.close();
        return availableList;
    }

    public static int addRecord(Player player) throws SQLException {
        int result = 0;
        DriverManager.registerDriver(new ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("INSERT INTO history VALUES (?, ?)");
        ps.setString(1, player.getPlayerName());
        ps.setString(2, player.getRecord());
        result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }


    /*public static List<Player> getRecords(Player player) throws SQLException {
      
        ResultSet resultSet;
        String pName = player.getPlayerName();
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player" , "root" , "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM history where playerName like ?" , ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_READ_ONLY);
        ps.setString(1,pName);
        resultSet = ps.executeQuery();
        
        List<Player> recordsList = new ArrayList<>();
        while (resultSet.next()) {
        Player playerRecord = new Player(resultSet.getString("playerName") , resultSet.getString("record"));
        recordsList.add(playerRecord);
    }
      
        ps.close();
        con.close();
        
        return recordsList;
    }*/
    //method to get all records
    public static List<String> getRecords(String playerName) throws SQLException {
        ResultSet resultSet;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM PLAYERRECORD WHERE NAME = ?  ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, playerName);
        resultSet = ps.executeQuery();
        List<String> recordsList = new ArrayList<>();
        while (resultSet.next()) {
            String record = resultSet.getString("RECORD");
            recordsList.add(record);
        }
        ps.close();
        con.close();
        return recordsList;
    }

    public static int updateScore(Player player) throws SQLException {
        int result = 0;
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
                PreparedStatement ps = con.prepareStatement("UPDATE user SET totalScore = ? WHERE username = ?")) {

            ps.setInt(1, player.getTotalScore());
            ps.setString(2, player.getPlayerName());
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return result;
    }

    public static int updateUserState(Player player) throws SQLException {
        int result = 0;
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
                PreparedStatement ps = con.prepareStatement("UPDATE user SET available = ? WHERE username = ?")) {

            ps.setBoolean(1, player.isAvailable());
            ps.setString(2, player.getPlayerName());
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return result;
    }

}
