package pack.youcode.library;

import java.time.LocalDate;

public class borrowedBooks {



    private User user;
    private Book book;
    private LocalDate borrow_date;
    private LocalDate return_date;
    private String status;

    public borrowedBooks(User user, Book book, LocalDate borrow_date, LocalDate return_date, String status) {

        this.user = user;
        this.book = book;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.status = status;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
