package pack.youcode.library;

import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class bookImplementation implements bookInterface {

    Connection con;
    Scanner sc = new Scanner(System.in);


    @Override
    public void addBook(Book book) {

        // Validate book data before inserting into the database
        if (!isValidBookData(book)) {
            System.out.println("Invalid book data. Book not inserted.");
            return;
        }

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




    private boolean isValidBookData(Book book) {
        // Implement your validation rules here
        // For example, you can check if the title is not empty, quantity is positive, etc.
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            System.out.println("Invalid title");
            return false;
        }

        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            System.out.println("Invalid ISBN");
            return false;
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            System.out.println("Invalid Author");
            return false;
        }

        if (book.getQuantity() <= 0) {
            System.out.println("Invalid quantity");
            return false;
        }

        // Add more validation rules as needed

        // If all validation checks pass, return true
        return true;
    }

    @Override
    public void showBooks() {
        con = DatabaseConnection.createDBConnection();
        String query = "SELECT books.title, books.quantity, books.disponible, books.isbn, books.author FROM books";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);


            System.out.println("Title          Quantity     Available books   ISBN         Author");
            while (result.next()) {
                String title = result.getString(1);
                int quantity = result.getInt(2);
                int available = result.getInt(3);
                String isbn = result.getString(4);
                String author = result.getString(5);

                // Use String.format to format each column with a specific width
                String formattedLine = String.format("%-15s %-12d %-18d %-12s %-15s",
                        title, quantity, available, isbn, author);

                System.out.println(formattedLine);
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
    public void updateBook(String ISBN, String newTitle, Integer newQuantity, Integer newAvailable, String newAuthor) {
        con = DatabaseConnection.createDBConnection();

        StringBuilder queryBuilder = new StringBuilder("UPDATE books SET");
        List<Object> parameters = new ArrayList<>();

        if (!newTitle.isEmpty()) {
            queryBuilder.append(" title=?,");
            parameters.add(newTitle);
        }

        if (newQuantity != 0) {
            queryBuilder.append(" quantity=?,");
            parameters.add(newQuantity);
        }

        if (newAvailable != 0) {
            queryBuilder.append(" disponible=?,");
            parameters.add(newAvailable);
        }

        if (!newAuthor.isEmpty()) {
            queryBuilder.append(" author=?,");
            parameters.add(newAuthor);
        }

        // Remove the trailing comma
        queryBuilder.deleteCharAt(queryBuilder.length() - 1);

        queryBuilder.append(" WHERE isbn=?");
        parameters.add(ISBN);

        String query = queryBuilder.toString();

        try {
            PreparedStatement pstm = con.prepareStatement(query);

            // Set the parameter values
            for (int i = 0; i < parameters.size(); i++) {
                Object parameter = parameters.get(i);
                pstm.setObject(i + 1, parameter);
            }

            int count = pstm.executeUpdate();
            if (count != 0) {
                System.out.println("Livre mis à jour avec succès");
            } else {
                System.out.println("Échec de la mise à jour du livre. Le livre avec Isbn " + ISBN + " n'a pas été trouvé.");
            }

            // Make sure to close the PreparedStatement and the connection when you're done.
            pstm.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteBook(String deletBook) {
        con = DatabaseConnection.createDBConnection();

        String query = "delete from books where isbn=?";
        try{
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1,deletBook);

            int count = pstm.executeUpdate();
            if(count != 0){
                System.out.println("book deleted successfully");
            }else{
                System.out.println("This book isn't available");
            }

            pstm.close();
            con.close();

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

            boolean found = false; // Flag to check if any records were found



            //a



            System.out.println("Title          Quantity     Available books   ISBN         Author");
            while (result.next()) {
                String title = result.getString("title");
                int quantity = result.getInt("quantity");
                int available = result.getInt("disponible");
                String isbn = result.getString("isbn");
                String author = result.getString("author");

                // Use String.format to format each column with a specific width
                String formattedLine = String.format("%-15s %-12d %-18d %-12s %-15s",
                        title, quantity, available, isbn, author);

                System.out.println(formattedLine);


                found = true;

            }






            if (!found) {
                System.out.println("No books available for the given search criteria.");
            }


            result.close();
            pstm.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void info(int idOfBorrowedISBN) {
        System.out.println("Enter your name : ");
        String name = sc.nextLine();

        System.out.println("Enter your phone number : ");
        String phone = sc.nextLine();

        System.out.println("Enter your CIN : ");
        String cin = sc.nextLine();


        //System.out.println("Enter member number : ");
        //String member = sc.nextLine();

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


            while (result.next()) {
                String isbn = result.getString("isbn");

                if (isbn.equals(isbnforBorrow)) {
                    bookFound = true;

                   // info();

                    String sql = "SELECT books.id FROM books WHERE isbn = ?";
                    PreparedStatement pstm2 = con.prepareStatement(sql);
                    pstm2.setString(1, isbn);
                    ResultSet result2 = pstm2.executeQuery(); // Execute the prepared statement

                    while (result2.next()) {
                        int idOfBorrowedISBN = result2.getInt("id");
                        System.out.println(idOfBorrowedISBN);


                //test




                        info(idOfBorrowedISBN);




                    }


                }
            }


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




            //

            System.out.println("ISBN----------------TITLE");
            while (result.next()) {
                String isbn = result.getString("isbn");
                String name = result.getString("title");


                System.out.println( isbn + "----------------" + name);


            }
            //











        }catch (Exception ex){
            ex.printStackTrace();


        }



    }


    public void returnBook(String returnedIsbn, String CIN){
        con = DatabaseConnection.createDBConnection();
       // String query = "SELECT books.isbn as isbn1 ,`borrowed_books`.status FROM `books` INNER JOIN `borrowed_books` ON books.id=`borrowed_books`.`book_id`;";
       /* String query = """
            SELECT b.isbn AS isbn1, u.CIN AS cin, bb.status AS status 
                            FROM books AS b 
                            INNER JOIN borrowed_books AS bb ON b.id = bb.book_id 
                            INNER JOIN users AS u ON u.id = bb.user_id 
                            WHERE b.isbn = ? AND u.CIN = ?;
            """;*/

        String query = """
            SELECT books.isbn AS isbn1, users.CIN AS cin, borrowed_books.status AS status 
                            FROM books 
                            INNER JOIN borrowed_books ON books.id = borrowed_books.book_id 
                            INNER JOIN users  ON users.id = borrowed_books.user_id 
                            WHERE books.isbn = ? AND users.CIN = ?;
            """;

        String isbn = "";
        String cin = "";
        String status = "";
        boolean bookFound = false;
        try(PreparedStatement pstm = con.prepareStatement(query);){
            //Statement stmt = con.createStatement();
            pstm.setString(1, returnedIsbn);
            pstm.setString(2, CIN);
            ResultSet result = pstm.executeQuery();


            while (result.next()) {
                System.out.println("while loop");
                isbn = result.getString("isbn1");
                cin = result.getString("cin");
                status = result.getString("status");
                System.out.println(isbn + "   " + cin + "   " + status);
            }
            result.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }


                if (isbn.equals(returnedIsbn) && cin.equals(CIN)){
                    bookFound = true; // Set the flag to true if the book is found
                    if (status.equals("borrowed") || status.equals("lost")){

                        //String query2 = "UPDATE `borrowed_books` SET status=? where isbn=?";
                        //String query2 = "UPDATE `borrowed_books` as bb " +
                              //  "INNER JOIN `books` as b ON bb.book_id = b.id " +
                                //"SET bb.status = ? " +
                                //"WHERE b.isbn = ?";


                        String query2 = """
                                UPDATE `borrowed_books` AS bb  
                                INNER JOIN `books` AS b ON bb.book_id = b.id 
                                INNER JOIN `users` ON users.id = bb.user_id 
                                SET bb.status = ? 
                                WHERE b.isbn = ? AND users.CIN = ? ;
                                """;
                        try (PreparedStatement pstm2 = con.prepareStatement(query2);){
                            pstm2.setString(1, "returned");
                            pstm2.setString(2, returnedIsbn);
                            pstm2.setString(3, CIN);
                            int count = pstm2.executeUpdate();
                            if (count != 0) {
                                System.out.println("status mis à jour avec succès");
                            } else {
                                System.out.println("Échec de la mise à jour du status. ");
                            }
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Returned successfully");
                        System.out.println("returnedIsbn :"+returnedIsbn);
                        System.out.println("isbn in db :"+isbn);



                    }
                    else if(status.equals("returned")){
                        System.out.println("This book is already returned");

                    }else{
                        System.out.println("This book is not in borrowed");

                    }
                }
                else {
                    System.out.println("No book available with this ISBN");

                }
    }


    public void statistics(){

        con = DatabaseConnection.createDBConnection();

        String query = "SELECT books.title  as bookTitle , users.name as userName FROM books \n" +
                "INNER JOIN `borrowed_books` on books.id=`borrowed_books`.`book_id`\n" +
                "INNER JOIN users on users.id=`borrowed_books`.`user_id`  WHERE status=\"borrowed\"";


        String query2 = "SELECT books.title  as bookTitle , users.name as userName FROM books \n" +
                "INNER JOIN `borrowed_books` on books.id=`borrowed_books`.`book_id`\n" +
                "INNER JOIN users on users.id=`borrowed_books`.`user_id`  WHERE status=\"returned\"";

        try {
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet result=pstm.executeQuery();



            PreparedStatement pstm2 = con.prepareStatement(query2);
            ResultSet result2=pstm2.executeQuery();


            System.out.println("Les livre empruntés :\n");
            System.out.println("Title--------------Emprunteur");

            while (result.next()){



                System.out.println(result.getString("bookTitle") +"--------------------"+ result.getString("userName"));
                //System.out.format("%s %s", result.getString("bookTitle"), result.getString("userName"));


            }

            System.out.println("Les livre rendus :\n");
            System.out.println("Title--------------Emprunteur");

            while (result2.next()){



                System.out.println(result2.getString("bookTitle") +"--------------------"+ result2.getString("userName"));
                //System.out.format("%s %s", result2.getString("bookTitle"), result2.getString("userName"));

            }





        }catch (Exception e){
            e.printStackTrace();
        }




    }


}
