
package control;

import java.sql.Connection;
import model.ConnectDatabase;
import java.sql.ResultSet;
import model.SqlRunner;
import java.sql.SQLException;
import java.util.Vector;
import model.Book;
import model.Client;
import model.Tran;

public class DatabaseControl {
      
      private Connection conn;
      private ConnectDatabase connDb;
      
      public DatabaseControl(){
            checkDatabase();
      }

      public void checkDatabase(){
            connDb = new ConnectDatabase();
            connDb.firstConnect();
            conn = connDb.getConnection();
            SqlRunner sqlR = new SqlRunner("CreateDatabase");
            sqlR.checkDataBase(conn);
      }

      protected boolean connect(){
      
            connDb = new ConnectDatabase();
            connDb.connect();
            conn = connDb.getConnection();
            if(conn != null){
                  return true;
            }else{
                  return false;
            }
      }

      protected int auth(String[] authData){
      
            SqlRunner sqlR = new SqlRunner("__AUTHENTICATION__");
            ResultSet rs = sqlR.auth(conn, authData);
            int authResult = 0;
            try{
                  while(rs.next()){
                        authResult = rs.getInt(1);
                  }
            }catch(SQLException ex){
                  ex.printStackTrace();
            }
            return authResult;
      }

      protected boolean newUser(String[] authData){
            SqlRunner sqlR = new SqlRunner("__NEWUSER__");
            boolean success = sqlR.newUser(conn, authData);
            return success;
      }


      protected boolean newMember(Client member){
            SqlRunner sqlR = new SqlRunner("__NEWMEMBER__");
            boolean success = sqlR.newMember(conn, member);
            return success;
      }

      protected boolean newBook(Book book){
            SqlRunner sqlR = new SqlRunner("__NEWBOOK__");
            boolean success = sqlR.newBook(conn, book);
            return success;
      }

      protected boolean newTransaction(Tran tran){
            SqlRunner sqlR = new SqlRunner("__NEWTRANSACTION__");
            boolean success = sqlR.newTransaction(conn, tran);
            return success;
      }

      protected boolean updMember(Client member){
            SqlRunner sqlR = new SqlRunner("__UPDATE__");
            boolean success = sqlR.updMember(conn, member);
            return success;
      }

      protected boolean updMemberBal(Client member){
            SqlRunner sqlR = new SqlRunner("__UPDATE__");
            boolean success = sqlR.updMemberBal(conn, member);
            return success;
      }

      protected Vector<Vector<String>> dataTblMember(){
            ResultSet rs = null;
            SqlRunner sqlR = new SqlRunner("__FILLTABLES__");
            rs = sqlR.dataTblMember(conn);
            Vector<Vector<String>> records = new Vector<>();
            try {
                  while(rs.next()){
                        Vector<String> record = new Vector<>();
                        record.add(String.valueOf(rs.getInt("memberid")));
                        record.add(rs.getString("name"));
                        record.add(rs.getString("phone"));
                        record.add(rs.getString("email"));
                        record.add(String.valueOf(rs.getInt("balance")));
                        records.add(record);
                  }
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return records;
      }

      protected Vector<Vector<String>> dataTblBook(){
            ResultSet rs = null;
            SqlRunner sqlR = new SqlRunner("__FILLTABLES__");
            rs = sqlR.dataTblBook(conn);
            Vector<Vector<String>> records = new Vector<>();
            try {
                  while(rs.next()){
                        Vector<String> record = new Vector<>();
                        record.add(String.valueOf(rs.getInt("bookid")));
                        record.add(rs.getString("author"));
                        record.add(rs.getString("title"));
                        record.add(String.valueOf(rs.getInt("quantity")));
                        records.add(record);
                  }
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return records;
      }

      protected Vector<Vector<String>> dataTblTran(){
            ResultSet rs = null;
            SqlRunner sqlR = new SqlRunner("__FILLTABLES__");
            rs = sqlR.dataTblTran(conn);
            Vector<Vector<String>> records = new Vector<>();
            try {
                  while(rs.next()){
                        Vector<String> record = new Vector<>();
                        record.add(String.valueOf(rs.getInt("transactionid")));
                        record.add(rs.getString("time"));
                        record.add(String.valueOf(rs.getInt("mid")));
                        record.add(rs.getString("name"));
                        record.add(String.valueOf(rs.getInt("bid")));
                        record.add(rs.getString("author"));
                        record.add(rs.getString("title"));
                        record.add(String.valueOf(rs.getInt("direction")));
                        records.add(record);
                  }
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return records;
      }

      protected boolean updBook(Book book){
            SqlRunner sqlR = new SqlRunner("__UPDATE__");
            boolean success = sqlR.updBook(conn, book);
            return success;
      }

      protected boolean updBookQ(Book book){
            SqlRunner sqlR = new SqlRunner("__UPDATE__");
            boolean success = sqlR.updBookQ(conn, book);
            return success;
      }


      protected Vector<Vector<String>> dataTblDebit(){
            ResultSet rs = null;
            SqlRunner sqlR = new SqlRunner("__FILLTABLES__");
            rs = sqlR.dataTblDebit(conn);
            Vector<Vector<String>> records = new Vector<>();
            try {
                  while(rs.next()){
                        Vector<String> record = new Vector<>();
                        record.add(String.valueOf(rs.getInt("mid")));
                        record.add(rs.getString("name"));
                        record.add(String.valueOf(rs.getInt("bid")));
                        record.add(rs.getString("author"));
                        record.add(rs.getString("title"));
                        record.add(String.valueOf(rs.getInt("debit")));
                        records.add(record);
                  }
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
            return records;
      }


      protected boolean updPIN(String[] authData){
            SqlRunner sqlR = new SqlRunner("__UPDATE__");
            boolean success = sqlR.updPIN(conn, authData);
            return success;
      }

      protected void closeConnect(){
            if( conn != null ){
                  connDb.closeConnect();
            }
      }


}
