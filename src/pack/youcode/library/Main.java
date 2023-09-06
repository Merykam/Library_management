package pack.youcode.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection con = DatabaseConnection.createDBConnection();
        if(con != null){
            //System.out.printf("connection reussite");



            // Créer un scanner pour lire les entrées de l'utilisateur
            Scanner scanner = new Scanner(System.in);

            int choix;
            do {
                System.out.println("Menu :");
                System.out.println("1 - Ajouter un livre");
                System.out.println("2 - Afficher les livres");
                System.out.println("3 - Mettre à jour un livre");
                System.out.println("4 - Supprimer un livre");
                System.out.println("0 - Quitter");
                System.out.print("Choisissez une option : ");

                choix = scanner.nextInt();


                switch (choix) {
                    case 1:
                        scanner.nextLine(); // Nettoyer la ligne restante
                        System.out.print("Titre du livre : ");
                        String titre = scanner.nextLine();
                        System.out.print("Quantité : ");
                        int quantite = scanner.nextInt();
                        System.out.print("Disponible : ");
                        int disponible = scanner.nextInt();
                        scanner.nextLine(); // Nettoyer la ligne restante
                        System.out.print("ISBN : ");
                        String isbn = scanner.nextLine();
                        System.out.print("ID de l'auteur : ");
                        int auteurId = scanner.nextInt();
                    case 2:

                        System.out.println("i showed books");
                        break;
                    case 3:
                        System.out.println("i updated books");
                        break;

                    default:
                        System.out.println("next tiime babe");
                }


            } while (choix != 0);
        }else
            System.out.printf("nooon reussite");
    }
}

