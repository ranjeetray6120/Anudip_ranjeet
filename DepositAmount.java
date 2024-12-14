package projectInheritance.bankApplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class DepositAmountCurrent{
    private double depositAmount;
    private String accountNumber;

    public void depositCurrentAccount() {
        Scanner sc = new Scanner(System.in);

        // Get user input for account number and deposit amount
        System.out.println("Enter Account Number:");
        accountNumber = sc.nextLine();

        System.out.println("Enter Deposit Amount:");
        depositAmount = sc.nextDouble();

        if (depositAmount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        if (updateBalanceInDatabaseCurrent()) {
            System.out.println("Deposit successful.");
            saveTransactionToDatabase();
        } else {
            System.out.println("Failed to deposit. Please try again.");
        }
    }

    private boolean updateBalanceInDatabaseCurrent() {
        boolean isSuccess = false;
        String URL = "jdbc:mysql://localhost:3306/BankDB";
        String USER = "root";
        String PASSWORD = "1234";

        String updateQuery = "UPDATE currentaccount SET Initial_Balance = Initial_Balance + ? WHERE account_number = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setDouble(1, depositAmount);
            preparedStatement.setString(2, accountNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            isSuccess = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    private void saveTransactionToDatabase() {
        String url = "jdbc:mysql://localhost:3306/BankDB";
        String username = "root";
        String password = "1234";

        String insertQuery = "INSERT INTO transactionscurrent (account_number, transaction_type, amount) VALUES (?, 'Deposit', ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, accountNumber);
            preparedStatement.setDouble(2, depositAmount);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Transaction saved to database successfully.");
            } else {
                System.out.println("Failed to save the transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class DepositAmount extends DepositAmountCurrent {
    private double depositAmount;
    private String accountNumber;

    public void depositSavingAccount() {
        Scanner sc = new Scanner(System.in);

        // Get user input for account number and deposit amount
        System.out.println("Enter Account Number:");
        accountNumber = sc.nextLine();

        System.out.println("Enter Deposit Amount:");
        depositAmount = sc.nextDouble();

        if (depositAmount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        if (updateBalanceInDatabase()) {
            System.out.println("Deposit successful.");
            saveTransactionToDatabase();
        } else {
            System.out.println("Failed to deposit. Please try again.");
        }
    }

    private boolean updateBalanceInDatabase() {
        boolean isSuccess = false;
         String URL = "jdbc:mysql://localhost:3306/BankDB";
         String USER = "root";
         String PASSWORD = "1234";

        String updateQuery = "UPDATE savingaccount SET Initial_Balance = Initial_Balance + ? WHERE account_number = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setDouble(1, depositAmount);
            preparedStatement.setString(2, accountNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            isSuccess = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    private void saveTransactionToDatabase() {
        String url = "jdbc:mysql://localhost:3306/BankDB";
        String username = "root";
        String password = "1234";

        String insertQuery = "INSERT INTO transactions (account_number, transaction_type, amount) VALUES (?, 'Deposit', ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, accountNumber);
            preparedStatement.setDouble(2, depositAmount);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Transaction saved to database successfully.");
            } else {
                System.out.println("Failed to save the transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
