package pack.youcode.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //bookInterface bookObject = new bookImplementation();

        //authorInterface authorObject = new authorImplimentation();

        bookInterface bookObject = new bookImplementation();

        Connection con = DatabaseConnection.createDBConnection();
        if(con != null){
            //System.out.printf("connection reussite");
           Scanner sc = new Scanner(System.in);
            System.out.printf("Welcome to Library management application\n");
            do{
                System.out.println(
                        "1.Add book\n" +
                        "2.Show all books\n" +
                        "3.Update book\n" +
                        "4.Delete book\n"+
                        "5.Search book\n"+
                        "6.Borrow book\n"+
                        "7.Return book\n"+
                        "8-Afficher les statistiques");
                System.out.println("Enter choice : ");

                int choice=sc.nextInt();

                switch (choice){


                    case 1:
                        //Book book = new Book();

                        //get book name from the user
                        System.out.println("Enter book title : ");
                        String title=sc.next();

                        System.out.println("Enter quantity : ");
                        int quantity=sc.nextInt();

                        System.out.println("Enter ISBN : ");
                        String isbn=sc.next();


                        //authorObject.showAuthor();
                        System.out.println("Enter author name: ");
                        String authorname=sc.next();


                        //create object from book class
                        Book book1 = new Book(title,quantity,quantity,isbn,authorname);



                        //arg
                        bookObject.addBook(book1);


                        break;

                    case 2 :
                        bookObject.showBooks();
                        break;
                    case 3 :
                        System.out.println("Enter ISBN to update book : ");
                        sc.nextLine();
                        String ISBN= sc.nextLine();


                        System.out.println("Enter new book title : ");
                        String Newtitle=sc.nextLine();

                        System.out.println("Enter new quantity : ");
                        String sQuantity = sc.nextLine();
                        int Newquantity = 0;
                        if(!sQuantity.isEmpty()){
                            Newquantity = Integer.parseInt(sQuantity);
                        }



                        //sc.nextLine();



                        //authorObject.showAuthor();
                        System.out.println("Enter new author name: ");
                        String Newauthorname=sc.nextLine();


                        bookObject.updateBook(ISBN,Newtitle,Newquantity,Newquantity,Newauthorname);

                        break;
                    case 4:


// Create a Scanner object to read user input
                        Scanner sc2 = new Scanner(System.in);

                        System.out.println("Enter the ISBN to delete book:");
                        String isbnToDelete = sc2.nextLine();


                        bookObject.deleteBook(isbnToDelete);





                        break;

                    case 5:
                        System.out.println("Enter book title or the Author name : ");
                        sc.nextLine();
                        String searchBy = sc.nextLine();

                        bookObject.searchBook(searchBy);
                        break;

                    case 6 :

                        bookObject.showBooksIsbn();

                        System.out.println("Enter book ISBN : ");

                        sc.nextLine();

                        bookObject.borrowBook(sc.nextLine());



                        break;
                    case 7:

                        Scanner sc3 = new Scanner(System.in);
                        System.out.println("Enter ISBN for return :");
                        //sc3.nextLine();
                        String returnedIsbn = sc3.nextLine();

                        System.out.println("Enter User CIN for return :");
                        //sc3.nextLine();
                        String CIN = sc3.nextLine();


                        bookObject.returnBook(returnedIsbn,CIN);


                        break;
                    case 8:
                        bookObject.statistics();
                        break;

                    case 9:
                        System.out.println("Thank youu for using my app");
                        System.exit(0);

                    default:
                        System.out.println("Enter valid choice");
                        break;
                }



            }while (true);



        }else
            System.out.printf("nooon reussite");
    }
}

