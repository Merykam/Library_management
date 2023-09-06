package pack.youcode.library;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class bookImplementation implements bookInterface {

    Connection con;
    @Override
    public void addBook(Book book) {

         con=DatabaseConnection.createDBConnection();
        String query = "INSERT INTO authors (title, quantity, disponible, isbn, author_id) VALUES (?,?,?,?,?)";

        try{

            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1,book.getTitle());
            pstm.setInt(1,book.getQuantity());
            pstm.setInt(1,book.getAvailable());
            pstm.setString(1,book.getIsbn());
            pstm.setObject(1,book.getAuthor());
            int count = pstm.executeUpdate();
            if(count !=0){
                System.out.println("book inserted successfully");
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }


    }

    @Override
    public void showBooks() {

    }

    @Override
    public void updateBook() {

    }

    @Override
    public void deleteBook() {

    }
}
