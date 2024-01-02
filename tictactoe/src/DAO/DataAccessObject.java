/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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

}
