package com.janmokrackiservletproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDbAccess extends DbAccess {

    public boolean UserExists(String username, String password) {
         try {
             Class.forName("org.sqlite.JDBC");
             String hashedPass = PasswordHashing.HashPassword(password);
             Connection connection = DriverManager.getConnection(jdbcURL);
             Statement statement = connection.createStatement();
             String query = "SELECT * FROM Users WHERE login = '" + username +"';";
             ResultSet result = statement.executeQuery(query);
             while (result.next()) {
                 if (username.equals(result.getString("login")) && hashedPass.equals(result.getString("pass")))
                     return true;
                 else
                     return false;
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return false;
     }

    public boolean AddUser(String username, String password){
         try {
             Class.forName("org.sqlite.JDBC");
             String hashedPass = PasswordHashing.HashPassword(password);
             Connection connection = DriverManager.getConnection(jdbcURL);
             Statement statement = connection.createStatement();
             String query = "INSERT INTO Users VALUES ('"+username+"', '"+hashedPass+"', '"+ 1 +"');";
             statement.executeUpdate(query);
             return true;
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
    }
}
