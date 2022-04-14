
package model;


public class Client {
      
      private Integer memberid;
      private String name;
      private String phone;
      private String email;
      private Integer balance;
      
      public Client(){}

      public Integer getMemberid() {
            return memberid;
      }

      public String getName() {
            return name;
      }

      public String getPhone() {
            return phone;
      }

      public String getEmail() {
            return email;
      }

       public Integer getBalance() {
            return balance;
      }
      
      public void setMemberid(Integer memberid) {
            this.memberid = memberid;
      }

      public void setName(String name) {
            this.name = name;
      }

      public void setPhone(String phone) {
            this.phone = phone;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public void setBalance(Integer balance) {
            this.balance = balance;
      }

}
