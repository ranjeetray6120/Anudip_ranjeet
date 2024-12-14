package projectInheritance.bankApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class WithdrawCurrent{
    private double withdrawAmount;
    private String accountNumber;

    // Method to withdraw funds
    public void withdrawCurrentAccount() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Account Number:");
        accountNumber = sc.nextLine();

        System.out.println("Enter Withdrawal Amount:");
        withdrawAmount = sc.nextDouble();

        if (withdrawAmount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if (updateBalanceInDatabaseCurrent()) {
            System.out.println("Withdrawal successful.");
            saveTransactionToDatabase();
        } else {
            System.out.println("Failed to withdraw. Please try again.");
        }
    }

    private boolean updateBalanceInDatabaseCurrent() {
        boolean isSuccess = false;
        String url = "jdbc:mysql://localhost:3306/BankDB";
        String username = "root";
        String password = "1234";


        String updateQuery = "UPDATE currentaccount SET Initial_Balance = Initial_Balance - ? WHERE account_number = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setDouble(1, withdrawAmount);
            preparedStatement.setString(2, accountNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            isSuccess = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    private void saveTransactionToDatabase()  {
        String url = "jdbc:mysql://localhost:3306/BankDB";
        String username = "root";
        String password = "1234";


        String insertQuery = "INSERT INTO transactionsCurrent (account_number, transaction_type, amount) VALUES (?, 'Withdrawal', ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, accountNumber);
            preparedStatement.setDouble(2, withdrawAmount);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Withdrawal transaction logged successfully.");
            } else {
                System.out.println("Failed to log withdrawal transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


public class WithdrawAmount extends WithdrawCurrent {
    private double withdrawAmount;
    private String accountNumber;

    // Method to withdraw funds
    public void withdrawSavingAccount() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Account Number:");
        accountNumber = sc.nextLine();

        System.out.println("Enter Withdrawal Amount:");
        withdrawAmount = sc.nextDouble();

        if (withdrawAmount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if (updateBalanceInDatabase()) {
            System.out.println("Withdrawal successful.");
            saveTransactionToDatabase();
        } else {
            System.out.println("Failed to withdraw. Please try again.");
        }
    }

    private boolean updateBalanceInDatabase() {
        boolean isSuccess = false;
        String url = "jdbc:mysql://localhost:3306/BankDB";
        String username = "root";
        String password = "1234";


        String updateQuery = "UPDATE savingaccount SET Initial_Balance = Initial_Balance - ? WHERE account_number = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setDouble(1, withdrawAmount);
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


        String insertQuery = "INSERT INTO transactions (account_number, transaction_type, amount) VALUES (?, 'Withdrawal', ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, accountNumber);
            preparedStatement.setDouble(2, withdrawAmount);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Withdrawal transaction logged successfully.");
            } else {
                System.out.println("Failed to log withdrawal transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
