package pack.youcode.library;

public interface bookInterface {

    public void addBook(Book book);
    public void showBooks();
    public void updateBook(int bookId, String newTitle, int newQuantity, int newAvailable, String newIsbn, String newAuthor);
    public void deleteBook();
}
