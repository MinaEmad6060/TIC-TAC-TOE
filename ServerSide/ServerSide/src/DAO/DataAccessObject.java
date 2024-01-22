package DAO;

import DTO.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.derby.jdbc.ClientDriver;

public class DataAccessObject {

    public static boolean isUserExist(String playerName) throws SQLException {
        String str = "name";
        boolean isExist = false;
        Connection connectToDB;

        DriverManager.registerDriver(new ClientDriver());
        connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/Player", "root", "root");

        PreparedStatement sqlStatement;
        sqlStatement = connectToDB.prepareStatement(
                "SELECT * FROM PLAYER where NAME = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        sqlStatement.setString(1, playerName);
        ResultSet resSet = sqlStatement.executeQuery();

        while (resSet.next()) {
            if (resSet.getString(str).equals(playerName)) {
                isExist = true;
            }
        }

        sqlStatement.close();
        connectToDB.close();
        return isExist;
    }

    public static boolean isUserValid(String playerName, String playerPassword) throws SQLException {
        String str = "password";
        boolean isValid = false;

        Connection connectToDB;
        PreparedStatement sqlStatement;

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

    public static ResultSet getAvailableList() throws SQLException {
        ResultSet resultSet;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Player where  available = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setBoolean(1, true);
        resultSet = ps.executeQuery();
        return resultSet;
    }

    /*
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
     */
    public static ResultSet getAvailable() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        //connection
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/phoneIndex", "root", "root");

        PreparedStatement ps = con.prepareStatement("SELECT player_name from user_Data where available=true and Online=true", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = ps.executeQuery();
        return result;

    }

//    public static int addRecord(Player player) throws SQLException {
//        int result = 0;
//        DriverManager.registerDriver(new ClientDriver());
//        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
//        PreparedStatement ps = con.prepareStatement("INSERT INTO history VALUES (?, ?)");
//        ps.setString(1, player.getPlayerName());
//        ps.setString(2, player.getRecord());
//        result = ps.executeUpdate();
//
//        ps.close();
//        con.close();
//
//        return result;
//    }
    
    
    public static void addRecord(String playerName, String newRecord) throws SQLException {
         Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
            PreparedStatement ps = con.prepareStatement("INSERT INTO PLAYERRECORD (NAME, RECORD) VALUES (?, ?)");
            ps.setString(1, playerName);
            ps.setString(2, newRecord);
            ps.executeUpdate();
            ps.close();
            con.close(); 
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

    public static int getScore(String name) throws SQLException {
        int score = 0;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement pss = con.prepareStatement("select score from player WHERE name = ?");
        pss.setString(1, name);
        ResultSet resSet = pss.executeQuery();
        if (resSet.next()) {
            score = resSet.getInt("SCORE");
        }
        pss.close();
        con.close();
        return score;
    }
    public static int setScore(String name , int score) throws SQLException {
        
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement pss = con.prepareStatement("update player set score = ? WHERE name = ?");
        pss.setInt(1, score);
        pss.setString(2 , name);
        int res = pss.executeUpdate();
        
        pss.close();
        con.close();
        return res;
    }

    //mina
    public static int updateAvailability(String playerName, boolean state) throws SQLException {
        int result = 0;

        DriverManager.registerDriver(new ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player",
                "root", "root");
        PreparedStatement ps = con.prepareStatement(
                "UPDATE PLAYER SET AVAILABLE = ? WHERE NAME = ?");

        ps.setBoolean(1, state);
        ps.setString(2, playerName);
        result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }

    public static int updateUserState(String name) throws SQLException {
        int result = 0;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("UPDATE PLAYER SET ONLINE = ? WHERE NAME = ?");

        ps.setBoolean(1, true);
        ps.setString(2, name);
        result = ps.executeUpdate();
        ps.close();
        con.close();
        return result;
    }

    public static int updateOnlineState(String playerName, boolean state) throws SQLException {
        int result = 0;

        DriverManager.registerDriver(new ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player",
                "root", "root");
        PreparedStatement ps = con.prepareStatement(
                "UPDATE PLAYER SET ONLINE = ? WHERE NAME = ?");

        ps.setBoolean(1, state);
        ps.setString(2, playerName);
        result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }

    public static int getAllUsers() throws SQLException {

        ResultSet resultSet;
        int count = 0;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM PLAYER", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = ps.executeQuery();
        while (resultSet.next()) {
            count++;
        }
        ps.close();
        con.close();
        return count;
    }

    public static int getOnlineUsers() throws SQLException {

        ResultSet resultSet;
        int count = 0;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM PLAYER where online = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setBoolean(1, true);
        resultSet = ps.executeQuery();
        while (resultSet.next()) {
            count++;
        }
        ps.close();
        con.close();
        return count;
    }

    public static int getAvailableUsers() throws SQLException {

        ResultSet resultSet;
        int count = 0;
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM PLAYER where available = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setBoolean(1, true);
        resultSet = ps.executeQuery();
        while (resultSet.next()) {
            count++;
        }
        ps.close();
        con.close();
        return count;
    }
}
