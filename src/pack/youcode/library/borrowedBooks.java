package pack.youcode.library;

import java.time.LocalDate;

public class borrowedBooks {


    private int user_id;
    private int book_id;
    //private User user;
    //private Book book;
    private LocalDate borrow_date;
    private LocalDate return_date;
    //private String status;


    //public User getUser() {
       // return user;
    //}

    //public void setUser(User user) {
       // this.user = user;
    //}

    //public void setBook(Book book) {
        //this.book = book;
    //}

    //public Book getBook() {
        //return book;
    //}

    enum Statut {
        RETURNED,
        LOST,
        BORROWED
    }

    private Statut statut;

    //public borrowedBooks(int user_id, int book_id, LocalDate borrow_date, LocalDate return_date, String status) {

        //this.user_id = user_id;
        //this.book_id = book_id;
        //this.borrow_date = borrow_date;
        //this.return_date = return_date;
        //this.status = status;

    //}

    public int getUser() {
        return user_id;
    }

   public void setUser(int user_id) {
       this.user_id = user_id;
   }

    public int getBook() {
        return book_id;
    }

    public void setBook(int book_id) {
        this.book_id = book_id;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public Statut getStatus() {
        return statut;
    }

    public void setStatus(Statut statut) {
        this.statut = statut;
    }
}
