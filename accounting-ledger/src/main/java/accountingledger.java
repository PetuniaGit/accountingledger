import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class accountingledger {
    static Scanner scan=new Scanner(System.in);
    static LocalDateTime currentTime;
    static DateTimeFormatter fmt;
    static  String  DateTime;
    static LocalDate currentDate = LocalDate.now();
    static int currentMonth = currentDate.getMonthValue();
    static int currentYear = currentDate.getYear();


    public static void main(String[] args) {
        homePage();
    }


    public static void homePage(){
        System.out.print("What would you like to do? ");
        System.out.println("Enter 1 to Add Deposit");
        System.out.println("Enter 2 Make Payment ");
        System.out.println("Enter 3 to view Ledger");
        System.out.println("Enter 4 to  Exit");

        int userChoice=scan.nextInt();
        scan.nextLine();
        if (userChoice==1) {

            addDeposit();

        } else if (userChoice==2) {

            makePayment();

        } else if (userChoice==3) {

            ledger();

        } else if (userChoice==4) {

            System.exit(0);

        } else {
            System.out.print("Invalid input. Please try again: ");
            userChoice = scan.nextInt();
        }
    }
    public static void addDeposit() {

        System.out.println("\nPlease enter the deposit information:");


        System.out.print(" Please enter deposit description: ");
        String depositDescription = scan.nextLine();
        scan.nextLine();

        System.out.print("Please enter deposit vendor: ");
        String depositVendor = scan.nextLine();
          scan.nextLine();

        System.out.print("Please enter deposit amount: ");
        double depositAmount = scan.nextDouble();
        scan.nextLine();


        LocalDateTime currentTime = LocalDateTime.now();


        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        String  DateTime = currentTime.format(fmt);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write("\n" + DateTime + "|" + depositDescription + "|" + depositVendor + "|" + depositAmount);


            System.out.println("\nDeposit information added to transactions.csv successfully.\n");

        } catch (IOException e) {
            System.out.println("\nAn error occurred while writing to the file: " + e.getMessage() + "\n");
        }
        homePage();

    }
    public static void makePayment() {
        // Ask user for the payment informaiton.
        System.out.println("\nPlease enter the payment information:");

        // Ask user to enter the payment information.
        System.out.print("Enter payment description: ");
        String paymentDescription = scan.next();

        // Ask user to enter payment vendor.
        System.out.print("Enter payment vendor: ");
        String  paymentVendor = scan.nextLine();
        scan.nextLine();
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
    public static void ledger() {
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
            AllTransaction();
            // If user chose D.
        } else if (ledgerInput==2) {

            viewDeposits();
            // If user chose P.
        } else if (ledgerInput==3) {
            // Call ledgerPayments method.
            viewPayments();
            // If user chose R.
        } else if (ledgerInput==4) {
            // Call ledgerReports method.
            viewReports();
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




    public static void AllTransaction() {
        // Prints all entries to the terminal
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ledger();

    }
    public static void viewDeposits() {
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction= line.split("\\|");
                if ((Double.parseDouble(transaction[4])) > 0) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ledger();
    }
    public static void viewPayments() {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                if (Double.parseDouble(transaction[4]) < 0){
                    System.out.println(line);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ledger();

    }
    public static void viewReports() {
        System.out.println("\n1) Month to Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year to Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Custom Search");
        System.out.println("7) Back");

        // Ask user to choose an option.
        System.out.print("Choose an option: ");
        int userInput = scan.nextInt();
        scan.nextLine();

        // If user chose 1.
        if (userInput == 1) {
            // Call monthToDate method.
            monthToDate();
            // If user chose 2.
        } else if (userInput == 2) {
            // Call previousMonth method.
            previousMonth();
            // If user chose 3.
        } else if (userInput == 3) {
            // Call yearToDate method.
            yearToDate();
            // If user chose 4.
        } else if (userInput == 4) {
            // Call previousYear method.
            previousYear();
            // If user chose 5.
        } else if (userInput == 5) {
            // Call searchByVendor method.
            searchByVendor();
            // If user chose 6.
        } else if (userInput == 6) {
            // Call customSearch method.
            customSearch();
        }
        // If user chose 0.
        else if (userInput == 7) {
            ledger();
        }
        else {
            System.out.print("Invalid input. Please try again: ");
            userInput = scan.nextInt();
        }
    }




    public static void monthToDate() {
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Double.parseDouble(date[1]) == currentMonth && Double.parseDouble(date[0]) == currentYear) {
                    System.out.println(line);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewReports();
    }
    public static void previousMonth() {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Double.parseDouble(date[1]) == currentMonth-1 && Integer.parseInt(date[0]) == currentYear) {
                    System.out.println(line);
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        viewReports();

    }
    public static void yearToDate() {
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Integer.parseInt(date[0]) == currentYear) {
                    System.out.println(line);
                }
            }
            bufferedReader.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        viewReports();
    }
    public static void previousYear() {
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Integer.parseInt(date[0]) == currentYear-1) {
                    System.out.println(line);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        viewReports();
    }
    public static void searchByVendor() {
        System.out.print("Please enter the name of the vendor : ");
        String vendor = scan.nextLine();

        String line;
        // Read and query the csv file for entries from that vendor
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens[3].equalsIgnoreCase(vendor)) {
                    System.out.println(line);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void customSearch() {
    }
}
