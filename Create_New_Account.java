package projectInheritance.bankApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Create_New_Account {
    String adhar_no, account_Holder_name, account_no = " ", city;
    Double initial_balance;
    String query;
    Scanner scanner = new Scanner(System.in);

    public void create_Account() {
        System.out.println("Whose type of account create you?");
        System.out.println("1. For Saving account.");
        System.out.println("2. For Current Account.");
        System.out.println("Enter your choice.");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: {

                System.out.println("Enter your name.");
                account_Holder_name = scanner.nextLine();
                System.out.println("Enter the Adhar Number.");
                adhar_no = scanner.nextLine();
                System.out.println("Enter your city.");
                city = scanner.nextLine();
                System.out.println("Enter the first deposit amount.");
                initial_balance = scanner.nextDouble();
                int sum = 0;
                for (int i = 1; i <= 5; i++) {
                    sum += i;
                }
                adhar_no += (int) sum;
                account_no = adhar_no;

                query = "INSERT INTO SavingAccount (account_number, account_Holder_Name, Adhar_no,Initial_Balance ,City) VALUES (?, ?, ?, ?, ?)";
                break;
            }
            case 2: {
                System.out.println("Enter your name.");
                account_Holder_name = scanner.nextLine();
                System.out.println("Enter the Adhar Number.");
                adhar_no = scanner.nextLine();
                System.out.println("Enter your city.");
                city = scanner.nextLine();
                System.out.println("Enter the first deposit amount.");
                initial_balance = scanner.nextDouble();
                int sum = 0;
                for (int i = 1; i <= 7; i++) {
                    sum += i;
                }
                adhar_no += (int) sum;
                account_no = adhar_no;

                query = "INSERT INTO CurrentAccount (account_number, account_Holder_Name, Adhar_no,Initial_Balance,City) VALUES (?, ?, ?, ?,?)";
                break;

            }
            default: {
                System.out.println("Place enter the valid account.");
            }

        }

    }
    public void display_Account () {
        System.out.println("Account Holder Name: " + account_Holder_name);
        System.out.println("Account Number is: " + account_no);
        System.out.println("City: " + city);
        System.out.println("Initial balance: " + initial_balance);
    }


    public void saveToDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, account_no);
            stmt.setString(2, account_Holder_name);
            stmt.setString(3,adhar_no);
            stmt.setDouble(4, initial_balance);
            stmt.setString(5,city);


            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Account details saved to the database successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
    }
//    public static void main(String[] args) {
//        Create_New_Account createNewAccount = new Create_New_Account();
//        createNewAccount.create_Account();
//        createNewAccount.display_Account();
//    }

}