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
    public void updateBook(int bookId, String newTitle, int newQuantity, int newAvailable, String newIsbn, String newAuthor) {

        con = DatabaseConnection.createDBConnection();
        String query = "UPDATE books SET title=?, quantity=?, disponible=?, isbn=?, author=? WHERE id=?";

        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, newTitle);
            pstm.setInt(2, newQuantity);
            pstm.setInt(3, newAvailable);
            pstm.setString(4, newIsbn);
            pstm.setString(5, newAuthor);
            pstm.setInt(6, bookId); // Spécifiez l'ID du livre que vous souhaitez mettre à jour

            int count = pstm.executeUpdate();
            if (count != 0) {
                System.out.println("Livre mis à jour avec succès");
            } else {
                System.out.println("Échec de la mise à jour du livre. Le livre avec l'ID " + bookId + " n'a pas été trouvé.");
            }

            // Assurez-vous de fermer le PreparedStatement et la connexion lorsque vous avez terminé.
            pstm.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteBook(int id) {
        con = DatabaseConnection.createDBConnection();
        String query = "delete from books where id=?";
        try{
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,id);

            int count = pstm.executeUpdate();
            if(count != 0){
                System.out.println("book deleted successfully");
            }

        }catch(Exception ex){
            ex.printStackTrace();

        }

    }


    public void searchBook(String searchBy) {
        con = DatabaseConnection.createDBConnection();
        String query = "SELECT * FROM books WHERE author LIKE ? OR title LIKE ?";

        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, "%" + searchBy + "%");
            pstm.setString(2, "%" + searchBy + "%");

            ResultSet result = pstm.executeQuery(); // Execute the prepared statement

            while (result.next()) {
                System.out.format("%s %d %d %s %s%n", result.getString("title"), result.getInt("quantity"), result.getInt("disponible"), result.getString("isbn"), result.getString("author"));
            }

            // Assurez-vous de fermer le ResultSet, le PreparedStatement et la connexion lorsque vous avez terminé.
            result.close();
            pstm.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
