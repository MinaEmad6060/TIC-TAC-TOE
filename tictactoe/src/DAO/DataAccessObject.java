
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

    public static int addUser(Player contact) throws SQLException
    {
        int result =0;
        //load /register to the driver
        DriverManager.registerDriver(new ClientDriver());
        //connection
        

        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/phoneIndex","root","root");
        //Statment
        PreparedStatement ps=con.prepareStatement("insert into PhoneIndex (player_Name)"
                + "values(?)");
        ps.setString(1,contact.getPlayerName());
        
         //process the result
         result =ps.executeUpdate();
         ps.close();
         con.close();
          return result;
    }
      public static int addPassword(Player contact) throws SQLException
    {
        int result =0;
        //load /register to the driver
        DriverManager.registerDriver(new ClientDriver());
        //connection
        

        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/phoneIndex","root","root");
        //Statment
        PreparedStatement ps=con.prepareStatement("insert into user_Data (password)"
                + "values(?)");
        ps.setString(1,contact.getPassword());
        
         //process the result
         result =ps.executeUpdate();
         ps.close();
         con.close();
          return result;
    }
       public static ResultSet getAvailable() throws SQLException{
       DriverManager.registerDriver(new ClientDriver());
        //connection
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/phoneIndex","root","root");
          
    PreparedStatement ps = con.prepareStatement("SELECT player_name from user_Data where available=true and Online=true",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    ResultSet result =ps.executeQuery();
    return result;
           
     
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



public static int updateScore(Player player) throws SQLException {
        int result = 0;
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Player", "root", "root");
             PreparedStatement ps = con.prepareStatement("UPDATE user SET totalScore = ? WHERE username = ?")) {

            ps.setInt(1, player.getTotalScore());
            ps.setString(2, player.getPlayerName());
            result = ps.executeUpdate();

        }catch (SQLException ex) {
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

