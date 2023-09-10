package pack.youcode.library;

import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.Date;
import java.util.Scanner;

public class bookImplementation implements bookInterface {

    Connection con;
    Scanner sc = new Scanner(System.in);


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

    public void userInfo(String name, String phone, String cin){










    }
    public void info(int idOfBorrowedISBN) {
        System.out.println("Enter your name : ");
        String name = sc.nextLine();

        System.out.println("Enter your phone number : ");
        String phone = sc.nextLine();

        System.out.println("Enter your CIN : ");
        String cin = sc.nextLine();

        System.out.println("Enter borrow date (yyyy-MM-dd): ");
        String Bdate = sc.nextLine();

// Parse the input string into a LocalDate
        LocalDate borrowLocalDate = LocalDate.parse(Bdate);

// Convert the LocalDate to a java.util.Date
        java.util.Date borrowDate = java.sql.Date.valueOf(borrowLocalDate);

        System.out.println("Enter return date (yyyy-MM-dd): ");
        String Rdate = sc.nextLine();

// Parse the input string into a LocalDate
        LocalDate returnLocalDate = LocalDate.parse(Rdate);

// Convert the LocalDate to a java.util.Date
        java.util.Date returnDate = java.sql.Date.valueOf(returnLocalDate);

// Now, you can use borrowDate and returnDate as java.util.Date objects





        // userInfo(name,phone,cin);

        User user1 = new User(name, phone, cin);


        userIn user = new userImplimentation();

        user.addUser(user1,idOfBorrowedISBN,borrowDate,returnDate);

    }

    public void borrowBook(String isbnforBorrow) {
        con = DatabaseConnection.createDBConnection();
        String query = "SELECT isbn FROM books";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            boolean bookFound = false; // Flag to track if the book is found

            // Loop through the ResultSet to access and display ISBN values
            while (result.next()) {
                String isbn = result.getString("isbn");

                if (isbn.equals(isbnforBorrow)) {
                    bookFound = true; // Set the flag to true if the book is found

                   // info();

                    String sql = "SELECT books.id FROM books WHERE isbn = ?";
                    PreparedStatement pstm2 = con.prepareStatement(sql);
                    pstm2.setString(1, isbn);
                    ResultSet result2 = pstm2.executeQuery(); // Execute the prepared statement

                    while (result2.next()) {
                        int idOfBorrowedISBN = result2.getInt("id");
                        System.out.println(idOfBorrowedISBN);


                        info(idOfBorrowedISBN);

                        //borrowedBooks Bbook = new borrowedBooks();
                        //Bbook.setBook(idOfBorrowedISBN);

                        //borrowedBooksInterface addbook =  new borrowBooksImplimentation();

                        //addbook.addBorrowedBooks(Bbook);


                    }

                    // You may choose to break here if you want to stop searching after finding the book
                }
            }

            // Check if the book was not found in the loop
            if (!bookFound) {
                System.out.println("No book available with this ISBN");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //idOfBorrowedISBN





    }



    public void showBooksIsbn(){


        con = DatabaseConnection.createDBConnection();
        String query="select isbn, title from books";

        try{
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);




            // Loop through the ResultSet to access and display isbn values
            while (result.next()) {
                String isbn = result.getString("isbn");
                String name = result.getString("title");
                System.out.println("ISBN: " + isbn + "\n Book title :" + name);


            }






        }catch (Exception ex){
            ex.printStackTrace();


        }



    }


}
