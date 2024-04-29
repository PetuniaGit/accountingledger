import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class accountingledger {
    static Scanner scan=new Scanner(System.in);
    static LocalDateTime currentTime;
    static DateTimeFormatter fmt;
    static  String  DateTime;

    public static void homePage(){
        System.out.print("What would you like to do? ");
        System.out.println("Enter 1 to Add Deposit");
        System.out.println("Enter 2 Make Payment ");
        System.out.println("Enter 3 to view Ledger");
        System.out.println("Enter 4 to  Exit");

        int userChoice=scan.nextInt();
        if (userChoice==1) {
            // Call addDeposit method.
            addDeposit();
            // If user chose P.
        } else if (userChoice==2) {
            // Call makePayment method.
            makePayment();
            // If user chose L.
        } else if (userChoice==3) {
            // Call ledger method.
            ledger();
            // If user chose X.
        } else if (userChoice==4) {
            // Quit.
            System.exit(0);
            // If user entered a wrong input.
        } else {
            System.out.print("Invalid input. Please try again: ");
            userChoice = scan.nextInt();
        }
    }
    private static void addDeposit() {
        // Ask user to enter their deposit information.
        System.out.println("\nPlease enter the deposit information:");

        // Ask user to enter the deposit information.
        System.out.print(" Please enter deposit description: ");
        String depositDescription = scan.nextLine();

        // Ask user to enter deposit vendor.
        System.out.print("Please enter deposit vendor: ");
        String depositVendor = scan.nextLine();

        // Ask user to enter deposit amount.
        System.out.print("Please enter deposit amount: ");
        double depositAmount = scan.nextDouble();
        scan.nextLine();

        // Get current date and time.
        LocalDateTime currentTime = LocalDateTime.now();

        // Set the format for the date and time.
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        // Format the date and time.
        String  DateTime = currentTime.format(fmt);

        // Write the deposit information into the csv.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write("\n" + DateTime + "|" + depositDescription + "|" + depositVendor + "|" + depositAmount);

            // Success messsage.
            System.out.println("\nDeposit information added to transactions.csv successfully.\n");
            // If an error occured, print error.
        } catch (IOException e) {
            System.out.println("\nAn error occurred while writing to the file: " + e.getMessage() + "\n");
        }
        homePage();

    }
    private static void makePayment() {
        // Ask user for the payment informaiton.
        System.out.println("\nPlease enter the payment information:");

        // Ask user to enter the payment information.
        System.out.print("Enter payment description: ");
        String paymentDescription = scan.nextLine();

        // Ask user to enter payment vendor.
        System.out.print("Enter payment vendor: ");
        String  paymentVendor = scan.nextLine();

        // Ask user to enter payment amount.
        System.out.print("Enter payment amount (as negative): ");
        Double  paymentAmount = scan.nextDouble();
        scan.nextLine();

        // Get current date and time.
        currentTime = LocalDateTime.now();

        // Set the format for the date and time.
        fmt= DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        // Format the date and time.
        DateTime = currentTime.format(fmt);

        // Write the payment information into the csv.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write("\n" + DateTime + "|" + paymentDescription + "|" + paymentVendor + "|" + paymentAmount);

            // Success messsage.
            System.out.println("\nPayment information added to transactions.csv successfully.\n");
            // If an error occured, print error.
        } catch (IOException e) {
            System.out.println("\nAn error occurred while writing to the file: " + e.getMessage() + "\n");
        }

        // Go back to home menu.
        homePage();
    }
    private static void ledger() {
        System.out.println("Enter 1 to display all transactions");
        System.out.println("Enter 2 to display Deposits");
        System.out.println("Enter 3 to display Payments");
        System.out.println("Enter 4 to display Reports");
        System.out.println("Enter 5 to go back to Home");

        // Ask the user what they want to do.
        System.out.print("Please select an option: ");
        int  ledgerInput = scan.nextInt();

        // If user chose A.
        if (ledgerInput==1) {
            // Call ledgerAll method.
            ledgerAll();
            // If user chose D.
        } else if (ledgerInput==2) {
            // Call ledgerDeposits method.
            ledgerDeposits();
            // If user chose P.
        } else if (ledgerInput==3) {
            // Call ledgerPayments method.
            ledgerPayments();
            // If user chose R.
        } else if (ledgerInput==4) {
            // Call ledgerReports method.
            ledgerReports();
            // If user chose H.
        } else if (ledgerInput==5) {
            // Return to home.
            homePage();
            // If user entered a wrong input.
        } else {
            System.out.print("Invalid input. Please try again: ");
            ledgerInput = scan.nextInt();
        }
    }



    private static void ledgerReports() {
    }

    private static void ledgerPayments() {
    }

    private static void ledgerDeposits() {
    }

    private static void ledgerAll() {
    }


}
