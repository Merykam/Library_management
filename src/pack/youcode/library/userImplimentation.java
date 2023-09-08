package pack.youcode.library;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class userImplimentation implements userIn{

    Connection con;
    @Override
    public void addUser(User user) {

        con=DatabaseConnection.createDBConnection();
        String query = "INSERT INTO users (name, phone_number, CIN) VALUES (?,?,?)";

        try{

            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1,user.getName());
            pstm.setString(2,user.getPhone());
            pstm.setString(3,user.getCin());

            int count = pstm.executeUpdate();
            if(count !=0){
                System.out.println("Borrowing successfully");
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }

    }
}
