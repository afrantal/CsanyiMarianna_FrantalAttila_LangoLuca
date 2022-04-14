
package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DatabaseMetaData;


public class SqlRunner {
      
      private String filePath;
      
      public SqlRunner(String filename){
            this.filePath = "sql/" + filename + ".sql";
      }


      public void checkDataBase(Connection conn){
            String[] sql = getSql();
            Statement stmt = null;
            ResultSet rs = null;
            
            try {
                  DatabaseMetaData meta = conn.getMetaData();
                  rs = meta.getTables(null, "APP", "books", null);
                  if( !rs.next() ){
                        stmt = conn.createStatement();
                        for(int i=0; i<sql.length; i++){
                              stmt.addBatch(sql[i]);
                        }
                        stmt.executeBatch();
                  }
            } catch (SQLException ex) {
                        ex.printStackTrace();
            }
    
      }

      public String[] getSql(){
            
            String[] sql = null;
            
            try{
                  Path path = Path.of(filePath);
                  String content = Files.readString(path);
                  sql = content.split(";");
            } catch(IOException ex){
                  System.err.println("Hiba a beolvasás során.");
                  ex.printStackTrace();
            }
            return sql;
      }

      public ResultSet auth(Connection conn, String[] authData){
            
            String[] sql = getSql();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try{
                  
                  pstmt = conn.prepareStatement(sql[0]);
                  pstmt.setString(1,authData[0]);
                  pstmt.setString(2,authData[1]);
                  rs = pstmt.executeQuery();
            } catch(SQLException ex){
                  ex.printStackTrace();
            }
            return rs;
      }


      public boolean newUser(Connection conn, String[] authData){
            String[] sql = getSql();
            PreparedStatement pstmt = null;
            try{
                  
                  pstmt = conn.prepareStatement(sql[0]);
                  pstmt.setString(1,authData[0]);
                  pstmt.setString(2,authData[1]);
                  pstmt.execute();
                  
                  return true;

            } catch(SQLException ex){
                  ex.printStackTrace();
                  return false;
            }
      }

      public boolean newMember(Connection conn, Client client){
            String[] sql = getSql();
            
            try {
                  PreparedStatement pstmt = null;
                  pstmt = conn.prepareStatement(sql[0] );
                  pstmt.setString(1, client.getName());
                  pstmt.setString(2, client.getPhone());
                  pstmt.setString(3, client.getEmail());
                  pstmt.execute();
                  
                  return true;

            } catch (SQLException ex) {
                  ex.printStackTrace();
                  return false;
            }
      }


      public boolean updMember(Connection conn, Client client){
            String[] sql = getSql();
            
            try {
                  PreparedStatement pstmt = null;
                  pstmt = conn.prepareStatement(sql[0] );
                  pstmt.setString(1, client.getName());
                  pstmt.setString(2, client.getPhone());
                  pstmt.setString(3, client.getEmail());
                  pstmt.setInt(4, client.getMemberid());
                  pstmt.execute();
                  
                  return true;

            } catch (SQLException ex) {
                  ex.printStackTrace();
                  return false;
            }
      }

      public boolean updMemberBal(Connection conn, Client member){
            String[] sql = getSql();
            
            try {
                  PreparedStatement pstmt = null;
                  pstmt = conn.prepareStatement(sql[3] );
                  pstmt.setInt(1, member.getBalance());
                  pstmt.setInt(2, member.getMemberid());
                  pstmt.execute();
                  
                  return true;

            } catch (SQLException ex) {
                  ex.printStackTrace();
                  return false;
            }
      }

      public ResultSet dataTblMember (Connection conn){
            String[] sql = getSql();
            Statement stmt = null;
            ResultSet rs = null;
            try {
                  stmt = conn.createStatement();
                  rs = stmt.executeQuery(sql[0]);
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return rs;
      }


      public boolean newBook(Connection conn, Book book){
            String[] sql = getSql();
            
            try {
                  PreparedStatement pstmt = null;
                  pstmt = conn.prepareStatement(sql[0] );
                  pstmt.setString(1, book.getAuthor());
                  pstmt.setString(2, book.getTitle());
                  pstmt.setInt(3, book.getQuantity());
                  pstmt.execute();
                  
                  return true;

            } catch (SQLException ex) {
                  ex.printStackTrace();
                  return false;
            }
      }

      public boolean updBook(Connection conn, Book book){
            String[] sql = getSql();
            
            try {
                  PreparedStatement pstmt = null;
                  pstmt = conn.prepareStatement(sql[1] );
                  pstmt.setString(1, book.getAuthor());
                  pstmt.setString(2, book.getTitle());
                  pstmt.setInt(3, book.getQuantity());
                  pstmt.setInt(4, book.getBookid());
                  pstmt.execute();
                  
                  return true;

            } catch (SQLException ex) {
                  ex.printStackTrace();
                  return false;
            }
      }


      public ResultSet dataTblBook (Connection conn){
            String[] sql = getSql();
            Statement stmt = null;
            ResultSet rs = null;
            try {
                  stmt = conn.createStatement();
                  rs = stmt.executeQuery(sql[1]);
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return rs;
      }

      public boolean updBookQ(Connection conn, Book book){
            String[] sql = getSql();
            
            try {
                  PreparedStatement pstmt = null;
                  pstmt = conn.prepareStatement(sql[2] );
                  pstmt.setInt(1, book.getQuantity());
                  pstmt.setInt(2, book.getBookid());
                  pstmt.execute();
                  
                  return true;

            } catch (SQLException ex) {
                  ex.printStackTrace();
                  return false;
            }
      }

      public boolean newTransaction(Connection conn, Tran tran){
            String[] sql = getSql();
            
            try {
                  PreparedStatement pstmt = null;
                  pstmt = conn.prepareStatement(sql[0] );
                  pstmt.setInt(1, tran.getDirection());
                  pstmt.setInt(2, tran.getBid());
                  pstmt.setInt(3, tran.getMid());
                  pstmt.execute();
                  
                  return true;

            } catch (SQLException ex) {
                  ex.printStackTrace();
                  return false;
            }
      }


      public ResultSet dataTblTran (Connection conn){
            String[] sql = getSql();
            Statement stmt = null;
            ResultSet rs = null;
            try {
                  stmt = conn.createStatement();
                  rs = stmt.executeQuery(sql[2]);
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return rs;
      }


      public ResultSet dataTblDebit (Connection conn){
            String[] sql = getSql();
            Statement stmt = null;
            ResultSet rs = null;
            try {
                  stmt = conn.createStatement();
                  rs = stmt.executeQuery(sql[3]);
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return rs;
      }


      public boolean updPIN(Connection conn, String[] authData){
            String[] sql = getSql();
            PreparedStatement pstmt = null;
            try{
                  
                  pstmt = conn.prepareStatement(sql[4]);
                  pstmt.setString(1,authData[0]);
                  pstmt.setString(2,authData[1]);
                  pstmt.execute();
                  
                  return true;

            } catch(SQLException ex){
                  ex.printStackTrace();
                  return false;
            }
      
      
      }      


}
