package pack.youcode.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userImplimentation implements userIn{

    Connection con;
    @Override
    public int addUser(User user) {
        con = DatabaseConnection.createDBConnection();
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


                    borrowedBooksInterface addbook =  new borrowBooksImplimentation();

                    addbook.addBorrowedBooks(Bbook);

                    return userId;


                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1; // Return -1 to indicate an error (you can choose a different approach if needed)
    }




}
