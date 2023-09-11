package pack.youcode.library;

public interface bookInterface {

    public void addBook(Book book);
    public void showBooks();
    public void updateBook(int bookId, String newTitle, int newQuantity, int newAvailable, String newIsbn, String newAuthor);
    public void deleteBook(int id);

    public void searchBook(String searchBy);

    public void showBooksIsbn();

    public void borrowBook(String isbn);

    public void returnBook(String isbnReturned);

    public void statistics();

    public void userInfo(String name, String phone, String cin);
}
