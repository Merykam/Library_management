package pack.youcode.library;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class authorImplimentation implements authorInterface{

    Connection con;
    @Override
    public void addAuthor(Author author) {
        con=DatabaseConnection.createDBConnection();
        String query = "INSERT INTO authors (name) VALUES (?)";

        try{

            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1,author.getName());
            int count = pstm.executeUpdate();
            if(count !=0){
                System.out.println("author inserted successfully");
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }


    }


    public void showAuthor(){

        con= DatabaseConnection.createDBConnection();
        String query = "select * from authors";

        try{
            statement stmt= con.createStatement();
            resultSet stmt.executeQuery(query);

        }catch (Exception ex){
            ex.printStackTrace();

        }

    }
}
