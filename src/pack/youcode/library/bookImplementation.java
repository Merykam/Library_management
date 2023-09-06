package pack.youcode.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bookImplementation implements bookInterface {

    Connection con;
    @Override
    public void addBook(Book book) {

         con=DatabaseConnection.createDBConnection();
        String query = "INSERT INTO books (title, quantity, disponible, isbn, author) VALUES (?,?,?,?,?)";

        try{

            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1,book.getTitle());
            pstm.setInt(2,book.getQuantity());
            pstm.setInt(3,book.getAvailable());
            pstm.setString(4,book.getIsbn());
            pstm.setString(5,book.getAuthor());
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
        con = DatabaseConnection.createDBConnection();
        String query = "SELECT books.title, books.quantity, books.disponible, books.isbn, books.author FROM books";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                System.out.format("%s %d %d %s %s%n", result.getString(1), result.getInt(2), result.getInt(3), result.getString(4), result.getString(5));
                //                ^^                  ^^                  ^^
                // Utilisez %s pour les chaînes de caractères et %d pour les entiers
            }

            // Assurez-vous de fermer le ResultSet, le Statement et la connexion lorsque vous avez terminé.
            result.close();
            stmt.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void updateBook() {

    }

    @Override
    public void deleteBook() {

    }
}
