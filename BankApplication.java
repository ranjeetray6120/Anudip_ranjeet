package projectInheritance.bankApplication;


import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {


        // Test database connection first
        DatabaseConnection.testDatabaseConnection();

        System.out.println("___________________________________________");
        System.out.println("Welcome to Ranjeet Bank.");
        Scanner sc = new Scanner(System.in);
        String account_Holder,account_No = " ";
        double balance, withdraw_amount, deposit_amount;

        while (true) {
            System.out.println("1. For Create a new account.");
            System.out.println("2. for Saving Account.");
            System.out.println("3. for Current Account.");
            System.out.println("4. for exit.");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer after reading an integer

            switch (choice) {
                case 1:{
                            Create_New_Account createNewAccount = new Create_New_Account();
                            createNewAccount.create_Account();
                            createNewAccount.display_Account();
                            createNewAccount.saveToDatabase();
                         break;
                }
                case 2: {
                    System.out.println("_____________________________________");
                    System.out.println("Welcome to Saving Account.");
                    System.out.println("1. Check Balance.");
                    System.out.println("2. Deposit Amount.");
                    System.out.println("3. Withdraw Amount.");
                    System.out.println("4. Exit.");
                    System.out.println("Enter Your Choice:");

                    int choiceA = sc.nextInt();
                    sc.nextLine();

                    switch (choiceA) {
                        case 1: {
                            GatDataFromDBMS gatDataFromDBMS = new GatDataFromDBMS();
                            gatDataFromDBMS.fetchSavingAccountDetails();
                            break;
                        }
                        case 2: {
                            DepositAmount depositAmount = new DepositAmount();
                            depositAmount.depositSavingAccount();
                            break;
                        }
                        case 3: {
                            WithdrawAmount withdrawAmount = new WithdrawAmount();
                            withdrawAmount.withdrawSavingAccount();
                            break;
                        }
                        case 4:
                            System.out.println("Exiting to main menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("_____________________________________");
                    System.out.println("Welcome to Current Account.");
                    System.out.println("1. Check Balance.");
                    System.out.println("2. Deposit Amount.");
                    System.out.println("3. Withdraw Amount.");
                    System.out.println("4. Exit.");
                    System.out.println("Enter Your Choice:");

                    int choiceA = sc.nextInt();
                    sc.nextLine();

                    switch (choiceA) {
                        case 1: {
                            GatDataFromDBMS gatDataFromDBMS = new GatDataFromDBMS();
                            gatDataFromDBMS.fetchCurrentAccountDetails();
                            break;
                        }
                        case 2: {
                            DepositAmountCurrent depositAmount = new DepositAmountCurrent();
                            depositAmount.depositCurrentAccount();
                            break;
                        }
                        case 3: {
                            WithdrawCurrent withdrawCurrent = new WithdrawAmount();
                            withdrawCurrent.withdrawCurrentAccount();
                            break;
                        }
                        case 4:
                            System.out.println("Exiting to main menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Exiting the application.");
                    System.exit(0);
                }
                default: {
                    System.out.println("Please enter a valid option. Try again.");
                }
            }
        }
    }
}
