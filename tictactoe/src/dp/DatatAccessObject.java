/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp;

import dto.ContactDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDriver;
/**
 *
 * @author dell
 */
public class DatatAccessObject {
        public static int addUser(ContactDTO contact) throws SQLException
    {
        int result =0;
        //load /register to the driver
        DriverManager.registerDriver(new ClientDriver());
        //connection
        

        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/user_Data","root","root");
        //Statment
        PreparedStatement ps=con.prepareStatement("insert into PhoneIndex (player_Name,password,score,onLine,available)"
                + "values(?, ?, ?, ? ,? )");
        //ps.setInt(1,contact.getPlayerName());
        ps.setString(1, contact.getPlayerName());
        ps.setString(2, contact.getPassword());
        ps.setInt(3, contact.getScore());
        ps.setBoolean(4, contact.isOnLine());
        ps.setBoolean(5, contact.isAvailable());

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
  
    PreparedStatement ps = con.prepareStatement("SELECT * from PhoneIndex ",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    ResultSet result =ps.executeQuery();
    return result;

     
 }
    
}
