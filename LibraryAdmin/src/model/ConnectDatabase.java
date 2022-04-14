
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
      
      private Connection conn;
      private String user;
      private String pass;
      private String database;
      private String url;
      
      public ConnectDatabase(){
            conn = null;
            user = "?user=root";
            pass = "&password";
            database = "library";
            url = "jdbc:mariadb://localhost:3306/";
      }

      public void firstConnect(){
             try {
                  conn = DriverManager.getConnection(url + user + pass);
            }     catch (SQLException ex){
                        System.err.println("Hiba a kapcsolódás során.");
                        ex.printStackTrace();
                  }
      }

      public void connect(){
            //"jdbc:mariadb://localhost:3306/tesztauzem?user=root&password="
            try {
                  conn = DriverManager.getConnection(url + database + user + pass);
            }     catch (SQLException ex){
                        System.err.println("Hiba a kapcsolódás során.");
                        ex.printStackTrace();
                  }
            
      }
      
      public boolean closeConnect(){
            try{
                  conn.close();
                  return true;
            }     catch(SQLException ex){
                        System.err.println("Hiba a lezárás során.");
                        ex.printStackTrace();
                        return false;
                  }
      }


      public Connection getConnection (){
            return conn;
            
      }
      
      
      
}
