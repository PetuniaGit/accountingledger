import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class accountingledger {
    // Initialize the scanner.
    static Scanner scan=new Scanner(System.in);

    // Create the variables.
    static LocalDateTime currentTime;
    static DateTimeFormatter fmt;
    static  String  DateTime;
    static LocalDate currentDate = LocalDate.now();
    static int currentMonth = currentDate.getMonthValue();
    static int currentYear = currentDate.getYear();
    static boolean  foundTransaction;


    public static void main(String[] args) {
        homePage();
    }

    // Create the homePage method.
    public static void homePage(){
        // Ask the user what they want to do.
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
    // Create the addDeposit method.
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


        try (BufferedWriter bufwriter = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            bufwriter.write("\n" + DateTime + "|" + depositDescription + "|" + depositVendor + "|" + depositAmount);


            System.out.println("\nDeposit information added to transactions.csv successfully.\n");

        } catch (IOException e) {
            System.out.println("\nAn error occurred while writing to the file: " + e.getMessage() + "\n");
        }
        homePage();

    }
    // Create the makePayment method.
    public static void makePayment() {

        System.out.println("\nPlease enter the payment information:");


        System.out.print("Enter payment description: ");
        String paymentDescription = scan.next();


        System.out.print("Enter payment vendor: ");
        String  paymentVendor = scan.nextLine();
        scan.nextLine();

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
    // Create the ledger method.
    public static void ledger() {
        System.out.print("Please select an option: ");
        System.out.println("Enter 1 to display all transactions");
        System.out.println("Enter 2 to display Deposits");
        System.out.println("Enter 3 to display Payments");
        System.out.println("Enter 4 to display Reports");
        System.out.println("Enter 5 to go back to Home");



        int  ledgerInput = scan.nextInt();


        if (ledgerInput==1) {
            AllTransaction();
        } else if (ledgerInput==2) {
            viewDeposits();
        } else if (ledgerInput==3) {
            viewPayments();
        } else if (ledgerInput==4) {
            viewReports();
        } else if (ledgerInput==5) {
            homePage();
        } else {
            System.out.print("Invalid input. Please try again: ");
            ledgerInput = scan.nextInt();
        }
    }
    public static void AllTransaction() {
        // Prints all entries to the terminal
        String line;
         foundTransaction=false;
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = buffReader.readLine()) != null) {
                System.out.println(line);
                foundTransaction=true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!foundTransaction){
            System.out.println("No transaction found.");}
        ledger();

    }
    // Create the viewDeposits method.
    public static void viewDeposits() {
        String line;
        foundTransaction=false;

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufReader.readLine()) != null) {
                String[] transaction= line.split("\\|");
                if ((Double.parseDouble(transaction[4])) > 0) {
                    System.out.println(line);
                    foundTransaction=true;
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            if(!foundTransaction){
                System.out.println("No transaction found.");}
        ledger();
    }
    // Create the viewPayments method.
    public static void viewPayments() {
        String line;
         foundTransaction=false;
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                if (Double.parseDouble(transaction[4]) < 0){
                    System.out.println(line);
                    foundTransaction=true;
                }
            }
            bufReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!foundTransaction){
            System.out.println("No transaction found.");
        }
        ledger();

    }
    // Create the viewReports method.
    public static void viewReports() {
        System.out.print("Choose an option: ");
        System.out.println("\n Enter 1 to search transactions of current  Month.");
        System.out.println("Enter 2 to search transactions of  Previous Month.");
        System.out.println("Enter 3 to search transactions of current Year.");
        System.out.println("Enter 4 to search transactions of  Previous Year");
        System.out.println("Enter 5 to search  by Vendor");
        System.out.println("Enter 6 to run  Custom Search");
        System.out.println("Enter 7 to go  Back");



        int userInput = scan.nextInt();
        scan.nextLine();


        if (userInput == 1) {
            monthToDate();
        } else if (userInput == 2) {
            previousMonth();

        } else if (userInput == 3) {
            yearToDate();

        } else if (userInput == 4) {
            previousYear();

        } else if (userInput == 5) {
            searchByVendor();

        } else if (userInput == 6) {
            customSearch();
        }

        else if (userInput == 7) {
            ledger();
        }
        else {
            System.out.print("Invalid input. Please try again: ");
            userInput = scan.nextInt();
        }
    }
    // Create monthToDate method.
    public static void monthToDate() {
        String line;
        boolean foundTransaction=false;

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = bufReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Double.parseDouble(date[1]) == currentMonth && Double.parseDouble(date[0]) == currentYear) {
                    System.out.println(line);
                    foundTransaction=true;

                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
                if(!foundTransaction){
                    System.out.println("No transaction found.");}
        viewReports();
    }
    // Create previousMonth method.
    public static void previousMonth() {
        String line;
         foundTransaction=false;
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = buffReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Double.parseDouble(date[1]) == currentMonth-1 && Integer.parseInt(date[0]) == currentYear) {
                    System.out.println(line);
                    foundTransaction=true;
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        if(!foundTransaction){
            System.out.println("No transaction found.");}
        viewReports();

    }
    // Create yearToDate method.
    public static void yearToDate() {
        String line;
         foundTransaction=false;

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("transactions.csv"));

            while ((line = buffReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Integer.parseInt(date[0]) == currentYear) {
                    System.out.println(line);
                    foundTransaction=true;
                }
            }

            buffReader.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        if(!foundTransaction){
            System.out.println("No transaction found.");}
        viewReports();
    }
    // Create previousYear method.
    public static void previousYear() {
        String line;
         foundTransaction=false;

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = buffReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                String[] date = transaction[0].split("-");
                if (Integer.parseInt(date[0]) == currentYear-1) {
                    System.out.println(line);
                    foundTransaction=true;
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        if(!foundTransaction){
            System.out.println("No transaction found.");}
        viewReports();
    }
    // Create searchByVendor method.
    public static void searchByVendor() {
        System.out.print("Please enter the name of the vendor : ");
        String vendor = scan.nextLine();

        String line;
         foundTransaction=false;

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = buffReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                if (transaction[3].equalsIgnoreCase(vendor)) {
                    System.out.println(line);
                    foundTransaction=true;
                }
            }
            buffReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
                    if(!foundTransaction){
                        System.out.println("No transaction found.");}
        viewReports();
    }
    public static void customSearch() {
    }
}
