package projectInheritance.bankApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GatDataFromDBMS {

     String accountNo,accountHolder,adharNumber,city;
     double balance;
    Scanner scanner = new Scanner(System.in);
    public void fetchSavingAccountDetails() {
        System.out.println("Enter the adhar number");
        String adhar_no= scanner.nextLine();
        String query = "SELECT account_number, account_Holder_Name,Adhar_no, Initial_Balance,City FROM SavingAccount WHERE Adhar_no = ?";

            try (
                    Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

            ) {
                preparedStatement.setString(1, adhar_no); // Set the Aadhar Number parameter
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                     accountNo = resultSet.getString("account_number");
                     accountHolder = resultSet.getString("account_Holder_Name");
                     adharNumber = resultSet.getString("Adhar_no");
                     balance = resultSet.getDouble("Initial_Balance");
                     city = resultSet.getString("City");

                    System.out.println("_________________________________________________");
                    System.out.println("Account No: " + accountNo );
                    System.out.println(" Account Holder: "+accountHolder);
                    System.out.println("Adhar Number: "+adharNumber);
                    System.out.println("Initial_Balance: "+balance);
                    System.out.println("City: "+city);
                    System.out.println("__________________________________________________");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void fetchCurrentAccountDetails() {
        System.out.println("Enter the adhar number");
        String adhar_no= scanner.nextLine();
        String query = "SELECT account_number, account_Holder_Name,Adhar_no, Initial_Balance,City FROM CurrentAccount WHERE Adhar_no = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            preparedStatement.setString(1, adhar_no); // Set the Aadhar Number parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                 accountNo = resultSet.getString("account_number");
                 accountHolder = resultSet.getString("account_Holder_Name");
                 adharNumber = resultSet.getString("Adhar_no");
                 balance = resultSet.getDouble("Initial_Balance");
                 city = resultSet.getString("City");

                System.out.println("_________________________________________________");
                System.out.println("Account No: " + accountNo );
                System.out.println(" Account Holder: "+accountHolder);
                System.out.println("Adhar Number: "+adharNumber);
                System.out.println("Initial_Balance: "+balance);
                System.out.println("City: "+city);
                System.out.println("__________________________________________________");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
