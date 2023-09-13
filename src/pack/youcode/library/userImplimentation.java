package pack.youcode.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

public class userImplimentation implements userIn{

    Connection con;
    @Override
    public int addUser(User user, int idOfBorrowedISBN, Date Bdate, Date Rdate) {
        con = DatabaseConnection.createDBConnection();


        //String query2= "SELECT CIN FROM users";


       // try {
           // Statement stmt = con.createStatement();
           // ResultSet result2 = stmt.executeQuery(query2);

            //while (result2.next()){
              // String Cin = result2.getString(1);
                //if(Cin.equals())
            //}
        //}catch (Exception ex) {
           // ex.printStackTrace();
       // }




        String query = "INSERT INTO users (name, phone_number, CIN) VALUES (?,?,?)";

        try {
            PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPhone());
            pstm.setString(3, user.getCin());

            int count = pstm.executeUpdate();
            if (count != 0) {
                // Get the generated keys (in this case, the user ID)
                ResultSet generatedKeys = pstm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1); // Assuming ID is an integer
                    System.out.println("User added successfully with ID: " + userId);

                    borrowedBooks Bbook = new borrowedBooks();
                    Bbook.setUser(userId);
                    Bbook.setBook(idOfBorrowedISBN);
                    Bbook.setBorrow_date(Bdate);
                    Bbook.setReturn_date(Rdate);



                    borrowedBooksInterface addbook =  new borrowBooksImplimentation();

                    addbook.addBorrowedBooks(Bbook);

                   // return userId;


                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1; // Return -1 to indicate an error
    }




}
