package pack.youcode.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

public class borrowBooksImplimentation implements borrowedBooksInterface{

    Connection con;

    public void addBorrowedBooks(borrowedBooks borrowedBook){


        con=DatabaseConnection.createDBConnection();
        String query = "INSERT INTO borrowed_books (user_id, book_id, borrow_date, return_date, status) VALUES (?,?,?,?,?)";

        try{

            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,borrowedBook.getUser());
            pstm.setInt(2,borrowedBook.getBook());
            //java.sql.Date date = java.sql.Date.valueOf("2001-04-20"); // Date in "yyyy-MM-dd" format
            pstm.setDate(3, borrowedBook.getBorrow_date());
            //java.sql.Date date2 = java.sql.Date.valueOf("2001-04-25"); // Date in "yyyy-MM-dd" format
            pstm.setDate(4, borrowedBook.getReturn_date());
            String status = "borrowed"; // Replace with the appropriate status value
            pstm.setString(5, status);
            int count = pstm.executeUpdate();
            if(count !=0){
                System.out.println("borrowing successfully");
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }






    }
}
