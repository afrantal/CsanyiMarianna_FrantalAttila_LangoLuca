
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Book;
import model.Client;
import model.Tran;
import view.LoginFrame;
import view.MainFrame;


public class GuiControl  implements ActionListener{
      
      private LoginFrame loginFrm;
      private MainFrame mainFrm;
      private DatabaseControl dbCtr;
      
      
      public GuiControl(DatabaseControl dbCtr, boolean success){
            this.dbCtr = dbCtr;
            start(success);
            bindActionListener();
      }

      private void bindActionListener(){
            loginFrm.getloginBtn().addActionListener(this);
            loginFrm.getRegBtn().addActionListener(this);
            loginFrm.getcancelBtn().addActionListener(this);
            mainFrm.getnewMemberBtn().addActionListener(this);
            mainFrm.getmodMemberBtn().addActionListener(this);
            mainFrm.getcancelMemberBtn().addActionListener(this);
            mainFrm.getnewBookBtn().addActionListener(this);
            mainFrm.getmodBookBtn().addActionListener(this);
            mainFrm.getcancelBookBtn().addActionListener(this);
            mainFrm.getnewTrBtn().addActionListener(this);
            mainFrm.getcancelTrBtn().addActionListener(this);
            mainFrm.getBtnPIN().addActionListener(this);
            mainFrm.getExitBtn().addActionListener(this);
      }
      
      private void start(boolean success){
            mainFrm = new MainFrame();
            mainFrm.setLocationRelativeTo(null);
            if(success){
                  mainFrm.setstatusLbl("Adatbázis kapcsolódva.");
            }else{
                  mainFrm.setstatusLbl("Nincs adatbázis kapcsolat.");
            }
            mainFrm.setVisible(true);
            loginFrm = new LoginFrame();
            loginFrm.setLocationRelativeTo(null);
            loginFrm.setVisible(true);
      }
      
      private void authenticate(){
            String userName = loginFrm.getusernameTf().getText();
            char[] pass = loginFrm.getpasswordTf().getPassword();
            String password = "";
            for(int i = 0; i < pass.length; i++){
                  password += pass[i];
            }
            
            String[] authData = {userName, password};
            int authResult = dbCtr.auth(authData);
            if(authResult == 1){
                  loginFrm.dispose();
                  mainFrm.setuserLbl(userName);
                  dataTblMember();
                  dataTblBook();
                  dataTblTran();
                  dataTblDebit();
            }else{
                  loginFrm.setstatusLbl("Hibás adatok!");
            }
      }

      private void newUser(){
            String userName = loginFrm.getusernameTf().getText();
            char[] pass = loginFrm.getpasswordTf().getPassword();
            String password = "";
            for(int i = 0; i < pass.length; i++){
                  password += pass[i];
            }
            
            String[] authData = {userName, password};
            boolean authResult = dbCtr.newUser(authData);
            if( authResult ){
                  loginFrm.setstatusLbl("Sikeres regisztráció.");
            }else{
                  loginFrm.setstatusLbl("Hiba: már létező felhasználónév!");
            }
      }


      private void clearTextFields(){
            loginFrm.setusernameTf("");
            loginFrm.setpasswordTf("");
            mainFrm.setnameTf("");
            mainFrm.setphoneTf("");
            mainFrm.setemailTf("");
            mainFrm.setauthorTf("");
            mainFrm.settitleTf("");
            mainFrm.setquantityTf("");
            mainFrm.setbidTf("");
            mainFrm.setmidTf("");
            mainFrm.setComboMember("0");
            mainFrm.setComboBook("0");
            mainFrm.getnewBookBtn().setEnabled(true);
            mainFrm.getmodBookBtn().setEnabled(false);
            mainFrm.getnewMemberBtn().setEnabled(true);
            mainFrm.getmodMemberBtn().setEnabled(false);
            mainFrm.getnewTrBtn().setEnabled(false);
      }

      private void clearLogFields(){
            loginFrm.setusernameTf("");
            loginFrm.setpasswordTf("");
      }

      private void clearMemberFields(){
            mainFrm.setnameTf("");
            mainFrm.setphoneTf("");
            mainFrm.setemailTf("");
            mainFrm.setmidTf("");
            mainFrm.setBalanceLbl("");
            mainFrm.setComboMember("0");
            mainFrm.getnewMemberBtn().setEnabled(true);
            mainFrm.getmodMemberBtn().setEnabled(false);
            mainFrm.getnewTrBtn().setEnabled(false);
      }      

      private void clearBookFields(){
            mainFrm.setauthorTf("");
            mainFrm.settitleTf("");
            mainFrm.setquantityTf("");
            mainFrm.setbidTf("");
            mainFrm.setComboBook("0");
            mainFrm.getnewBookBtn().setEnabled(true);
            mainFrm.getmodBookBtn().setEnabled(false);
            mainFrm.getnewTrBtn().setEnabled(false);
      }

      
      private void dataTblMember(){
            Vector<Vector<String>> records = new Vector<>();
            records = dbCtr.dataTblMember();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Tag kód");
            columnNames.add("Név");
            columnNames.add("Telefon");
            columnNames.add("E-mail");
            columnNames.add("Tartozás");
            
            DefaultTableModel model = new DefaultTableModel(records, columnNames);
            mainFrm.getTblMember().setModel(model);
      }

      private void dataTblBook(){
            Vector<Vector<String>> records = new Vector<>();
            records = dbCtr.dataTblBook();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Könyv kód");
            columnNames.add("Szerző");
            columnNames.add("Cím");
            columnNames.add("Készlet");
            
            DefaultTableModel model = new DefaultTableModel(records, columnNames);
            mainFrm.getTblBook().setModel(model);
      }

      private void dataTblTran(){
            Vector<Vector<String>> records = new Vector<>();
            records = dbCtr.dataTblTran();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Tranzakció kód");
            columnNames.add("Időpont");
            columnNames.add("Tag kód");
            columnNames.add("Név");
            columnNames.add("Könyv kód");
            columnNames.add("Szerző");
            columnNames.add("Cím");
            columnNames.add("Irány");
            
            DefaultTableModel model = new DefaultTableModel(records, columnNames);
            mainFrm.getTblTran().setModel(model);
      }


      private void dataTblDebit(){
            Vector<Vector<String>> records = new Vector<>();
            records = dbCtr.dataTblDebit();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Tag kód");
            columnNames.add("Név");
            columnNames.add("Könyv kód");
            columnNames.add("Szerző");
            columnNames.add("Cím");
            columnNames.add("Tartozás");
            
            DefaultTableModel model = new DefaultTableModel(records, columnNames);
            mainFrm.getTblDebit().setModel(model);
      }

      private void setMemberData(){
           if( mainFrm.chk_All_MData() ){
            
                  Client member = new Client();
                  member.setName(mainFrm.getnameTf().getText());
                  member.setPhone(mainFrm.getphoneTf().getText());
                  member.setEmail(mainFrm.getemailTf().getText());

                  boolean success = dbCtr.newMember(member);
                  if(success){
                        mainFrm.setstatusLbl("Tag rögzítve.");
                        JOptionPane.showMessageDialog(null,"Tag rögzítve.");
                  }else{
                        mainFrm.setstatusLbl("Hiba a tag rögzítése során.");
                        JOptionPane.showMessageDialog(null,"Hiba a tag rögzítése során.");
                  }

                  mainFrm.fillComboMember();
                  mainFrm.getnewMemberBtn().setEnabled(false);
                  mainFrm.getmodMemberBtn().setEnabled(true);
                  mainFrm.setMaxComboMember();
                  mainFrm.setBalanceLbl("0");
           }else{
                 JOptionPane.showMessageDialog(null,"A Név, Telefon, E-mail mezők nem lehetnek üresek!");
           }
      }


      private void updMemberData(){
           Client member = new Client();
           member.setName(mainFrm.getnameTf().getText());
           member.setPhone(mainFrm.getphoneTf().getText());
           member.setEmail(mainFrm.getemailTf().getText());
           member.setMemberid(Integer.parseInt(mainFrm.getComboMember()));
           
           boolean success = dbCtr.updMember(member);
           if(success){
                 mainFrm.setstatusLbl("Tag sikeres frissítése.");
                 JOptionPane.showMessageDialog(null,"Tag sikeres frissítése.");
           }else{
                 mainFrm.setstatusLbl("Hiba a tag frissítése során.");
                 JOptionPane.showMessageDialog(null,"Hiba a tag frissítése során.");
           }
      
      }

      private void updMemberBalData(){
           if( mainFrm.chk_All_TData() ){
            
                  Client member = new Client();
                  member.setBalance( Integer.parseInt(mainFrm.getBalanceLbl().getText()) + Integer.parseInt(mainFrm.gettrDirTf().getText())  );
                  member.setMemberid(Integer.parseInt(mainFrm.getComboMember()));

                  boolean success = dbCtr.updMemberBal(member);
                  if(success){
                        mainFrm.setstatusLbl("Sikeres tranzakció, készlet frissítve.");
                  }else{
                        mainFrm.setstatusLbl("Sikertelen tranzakció, készlet változatlan");
                  }
           }
      }


      private void setBookData(){
           if( mainFrm.chk_All_BData() ){
            
                  Book book = new Book();
                  book.setAuthor(mainFrm.getauthorTf().getText());
                  book.setTitle(mainFrm.gettitleTf().getText());
                  book.setQuantity(Integer.parseInt(mainFrm.getquantityTf().getText()));

                  boolean success = dbCtr.newBook(book);
                  if(success){
                        mainFrm.setstatusLbl("Könyv rögzítve.");
                        JOptionPane.showMessageDialog(null,"Könyv rögzítve.");
                  }else{
                        mainFrm.setstatusLbl("Hiba a könyv rögzítése során.");
                        JOptionPane.showMessageDialog(null,"Hiba a könyv rögzítése során.");
                  }

                  mainFrm.fillComboBook();
                  mainFrm.getnewBookBtn().setEnabled(false);
                  mainFrm.getmodBookBtn().setEnabled(true);
                  mainFrm.setMaxComboBook();
      
            }else{
                  JOptionPane.showMessageDialog(null,"A Szerző, Cím, Készlet mezők nem lehetnek üresek!");
           }
       }
      
      private void updBookData(){
           Book book = new Book();
           book.setAuthor(mainFrm.getauthorTf().getText());
           book.setTitle(mainFrm.gettitleTf().getText());
           book.setQuantity(Integer.parseInt(mainFrm.getquantityTf().getText()));
           book.setBookid(Integer.parseInt(mainFrm.getComboBook()));
           
           boolean success = dbCtr.updBook(book);
           if(success){
                 mainFrm.setstatusLbl("Könyv adatok frissítve.");
                 JOptionPane.showMessageDialog(null,"Könyv adatok frissítve.");
           }else{
                 mainFrm.setstatusLbl("Hiba a könyv frissítése során.");
                 JOptionPane.showMessageDialog(null,"Hiba a könyv frissítése során.");
           }
      
      }      

      private void updBookQData(){
           if( mainFrm.chk_All_TData() ){
            
                  Book book = new Book();
                  book.setQuantity( Integer.parseInt(mainFrm.getquantityTf().getText()) + Integer.parseInt(mainFrm.gettrDirTf().getText())  );
                  book.setBookid(Integer.parseInt(mainFrm.getComboBook()));

                  boolean success = dbCtr.updBookQ(book);
                  if(success){
                        mainFrm.setstatusLbl("Sikeres tranzakció, készlet frissítve.");
                  }else{
                        mainFrm.setstatusLbl("Sikertelen tranzakció, készlet változatlan");
                  }
           }

      }   


      private void setTransactionData(){
           if( mainFrm.chk_All_TData() ){
            
                  Tran tran = new Tran();
                  tran.setDirection(Integer.parseInt(mainFrm.gettrDirTf().getText()));
                  tran.setBid(Integer.parseInt(mainFrm.getbidTf().getText()));
                  tran.setMid(Integer.parseInt(mainFrm.getmidTf().getText()));

                  boolean success = dbCtr.newTransaction(tran);
                  if(success){
                        mainFrm.setstatusLbl("Sikeres tranzakció rögzítés.");
                        JOptionPane.showMessageDialog(null,"Sikeres tranzakció rögzítés.");
                  }else{
                        mainFrm.setstatusLbl("Hiba a tranzakció rögzítése során.");
                        JOptionPane.showMessageDialog(null,"Hiba a tranzakció rögzítése során.");
                  }

                   mainFrm.getnewTrBtn().setEnabled(false);
      
           }else{
                 JOptionPane.showMessageDialog(null,"A könyv nincs készleten!");
           }
           
      }

      private void updPIN(){
            String userName = mainFrm.getuserLbl().getText();
            char[] pass = mainFrm.getPfPIN().getPassword();
            String password = "";
            for(int i = 0; i < pass.length; i++){
                  password += pass[i];
            }
            
            String[] authData = {password, userName };
            boolean authResult = dbCtr.updPIN(authData);
            if( authResult ){
                  mainFrm.setstatusLbl("Sikeres jelszó csere.");
                  JOptionPane.showMessageDialog(null,"Sikeres jelszó csere.");
                  mainFrm.setPfPIN("");
                  mainFrm.getBtnPIN().setEnabled(false);
            }else{
                  mainFrm.setstatusLbl("Hiba a jelszócsere közben!");
                  JOptionPane.showMessageDialog(null,"Hiba a jelszócsere közben!");
            }
      }


      private void exit(){
            dbCtr.closeConnect();
            System.exit(0);
      }      


      @Override
      public void actionPerformed(ActionEvent event) {
            if(event.getSource() == loginFrm.getloginBtn() ){
                  authenticate();
            }else if(event.getSource() == loginFrm.getRegBtn()){
                  newUser();
            }else if(event.getSource() == loginFrm.getcancelBtn()){
                  clearLogFields();
            }else if(event.getSource() == mainFrm.getnewMemberBtn() ){
                  setMemberData();
                  dataTblMember();
            }else if(event.getSource() == mainFrm.getmodMemberBtn() ){
                  updMemberData();
                  dataTblMember();
            }else if(event.getSource() == mainFrm.getcancelMemberBtn() ){
                  clearMemberFields();
            }else if(event.getSource() == mainFrm.getnewBookBtn() ){
                  setBookData();
                  dataTblBook();
            }else if(event.getSource() == mainFrm.getmodBookBtn() ){
                  updBookData();
                  dataTblBook();
            }else if(event.getSource() == mainFrm.getcancelBookBtn() ){
                  clearBookFields();
            }else if(event.getSource() == mainFrm.getcancelTrBtn() ){
                  clearTextFields();
            }else if(event.getSource() == mainFrm.getnewTrBtn() ){
                  setTransactionData();
                  updBookQData();
                  updMemberBalData();
                  dataTblTran();
                  dataTblDebit();
                  dataTblBook();
                  dataTblMember();
            }else if(event.getSource() == mainFrm.getBtnPIN()){
                  updPIN();
            }else if(event.getSource() == mainFrm.getExitBtn()){
                  exit();
            }
      }

}
