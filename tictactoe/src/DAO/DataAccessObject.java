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
    boolean isUserExist(String playerName){
	String str="name";
        boolean isExist = false;
        try {
           
            
            DriverManager.registerDriver(new ClientDriver());
            Connection connectToDB = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/Player", "root", "root");
            PreparedStatement sqlStatement = connectToDB.prepareStatement (
                    "SELECT PNAME FROM user", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet resSet = sqlStatement.executeQuery();
            
            while(resSet.next()){
                if(resSet.getString(str).equals(playerName)){
                    isExist=true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
}


boolean isUserValid(String playerPassword){
	String str="password";
        boolean isValid = false;
        try {
            
            DriverManager.registerDriver(new ClientDriver());
            Connection connectToDB = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/Player", "root", "root");
            PreparedStatement sqlStatement = connectToDB.prepareStatement (
                    "SELECT PPASS FROM user", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet resSet = sqlStatement.executeQuery();
            
            while(resSet.next()){
                if(resSet.getString(str).equals(playerPassword)){
                    isValid=true;
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isValid;
}

public static int addRecord(Player player) throws SQLException{
        int result = 0;
        DriverManager.registerDriver(new ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player" , "root" , "root");
        PreparedStatement ps = con.prepareStatement("INSERT INTO history VALUES (?, ?)");
        ps.setString(1, player.getPlayerName());
        ps.setString(2, player.getRecord());
        result = ps.executeUpdate();
        
        ps.close();
        con.close();
        
        return result;
    }

public static List<Player> getRecords(Player player) throws SQLException {
      
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
    }
}