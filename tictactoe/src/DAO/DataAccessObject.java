/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;


/**
 *
 * @author minae
 */
public class DataAccessObject {
    boolean isUserExist(String playerName){
	String str="name";
        boolean isExist = false;
        try {
           
            
            DriverManager.registerDriver(new ClientDriver());
            Connection connectToDB = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
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
                    "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
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
}
