
package model;


public class Book {
      
      private Integer bookid;
      private String author;
      private String title;
      private int quantity;
      
      public Book(){}

      public Integer getBookid() {
            return bookid;
      }

      public String getAuthor() {
            return author;
      }

      public String getTitle() {
            return title;
      }

      public int getQuantity() {
            return quantity;
      }

      public void setBookid(Integer bookid) {
            this.bookid = bookid;
      }

      public void setAuthor(String author) {
            this.author = author;
      }

      public void setTitle(String title) {
            this.title = title;
      }

      public void setQuantity(int quantity) {
            this.quantity = quantity;
      }


}
