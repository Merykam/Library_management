package pack.youcode.library;

public interface bookInterface {

    public void addBook(Book book);
    public void showBooks();
    public void updateBook(String ISBN, String newTitle, Integer newQuantity, Integer newAvailable, String newAuthor);
    public void deleteBook(String deletBook);

    public void searchBook(String searchBy);

    public void showBooksIsbn();

    public void borrowBook(String isbn);

    public void returnBook(String isbnReturned, String CIN);

    public void statistics();

    //public void userInfo(String name, String phone, String cin);
}
