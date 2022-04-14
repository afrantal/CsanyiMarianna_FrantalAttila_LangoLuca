
package control;

public class MainControl {
      
      private DatabaseControl dbCtr;
      private GuiControl guiCtr;
      
      public MainControl(){
            start();
      }

      private void start(){
            dbCtr = new DatabaseControl();
            boolean success = dbCtr.connect();
            guiCtr = new GuiControl(dbCtr, success);
      }

}
