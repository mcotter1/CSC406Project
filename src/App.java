import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * The main class for the application which includes the main driver for pulling Customers
 * from csv or the database file
 */
public class App extends Application {
    public static ArrayList<Customer> Customers = new ArrayList<>();
    public static int currentcustomerindex;
    public static int currentaccountindex;
    public static int savingsidcounter;
    static {
        savingsidcounter = 30;
    }
    /**
     * Main driver for app
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File savingsnumberfile = new File("savingsid.txt");
        if(!savingsnumberfile.exists()){
            Writer wr = new FileWriter("savingsid.txt");
            wr.write(String.valueOf(savingsidcounter));
            wr.close();
        }
        Scanner sc = new Scanner(savingsnumberfile);
        savingsidcounter = Integer.parseInt(sc.next());
        File databasefile = new File("t.tmp");
        if(!databasefile.exists()) {
            File customerfile = new File("Customer_Data_Final.csv");
            Scanner customerscanner = new Scanner(customerfile);
            int iterator = 0;
            String string;
            String[] entries;
            Customer newcustomer;
            ArrayList<Account> allaccounts = new ArrayList<>();
            while (customerscanner.hasNext()) {
                string = customerscanner.nextLine();
                if (iterator == 0) {
                    iterator++;
                    continue;
                }
                entries = string.split(",");
                int ssn = Integer.parseInt(entries[0]);
                String address = entries[1];
                String city = entries[2];
                String state = entries[3];
                int zipcode = Integer.parseInt(entries[4]);
                String firstname = entries[5];
                String lastname = entries[6];
                newcustomer = new Customer(ssn, address, city, state, zipcode, firstname, lastname);
                Customers.add(newcustomer);
            }
            File checkingfile = new File("checking_Final.csv");
            Scanner checkingscanner = new Scanner(checkingfile);
            iterator = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y", Locale.ENGLISH);
            Checking newchecking;
            while (checkingscanner.hasNext()) {
                string = checkingscanner.nextLine();
                if (iterator == 0) {
                    iterator++;
                    continue;
                }
                entries = string.split(",");
                int ssn = Integer.parseInt(entries[0]);
                String accounttype = entries[1];
                double balance = Double.parseDouble(entries[2]);
                String overdraftbackup = entries[3];
                int overdrafts = Integer.parseInt(entries[4]);
                LocalDate dateopened = LocalDate.parse(entries[5], formatter);
                boolean atmcard = Boolean.parseBoolean(entries[6]);
                newchecking = new Checking(ssn, accounttype, balance, overdraftbackup, overdrafts, dateopened, atmcard);
                allaccounts.add(newchecking);
            }
            File loanfile = new File("loans_data_Final.csv");
            Scanner loanscanner = new Scanner(loanfile);
            iterator = 0;
            Loan newloan;
            while (loanscanner.hasNext()) {
                string = loanscanner.nextLine();
                if (iterator == 0) {
                    iterator++;
                    continue;
                }
                entries = string.split(",");
                int ssn = Integer.parseInt(entries[0]);
                double balance = Double.parseDouble(entries[1]);
                double interestrate = Double.parseDouble(entries[2]);
                LocalDate paymentdue = LocalDate.parse(entries[3], formatter);
                LocalDate notifiedpayment = LocalDate.parse(entries[4], formatter);
                double amountdue = Double.parseDouble(entries[5]);
                LocalDate lastpayment = LocalDate.parse(entries[6], formatter);
                boolean missedpayments = Boolean.parseBoolean(entries[7]);
                int termlength = Integer.parseInt(entries[8]);
                String collateral = entries[9];
                String repaymenttype = entries[10];
                String loantype = entries[11];
                double cardlimit = Double.parseDouble(entries[12]);
                newloan = new Loan(ssn, balance, interestrate, paymentdue, notifiedpayment, amountdue, lastpayment, missedpayments, termlength, collateral, repaymenttype, loantype, cardlimit);
                allaccounts.add(newloan);
            }
            File savingsfile = new File("Savings_Data_Final.csv");
            Scanner savingsscanner = new Scanner(savingsfile);
            iterator = 0;
            Savings newsavings;
            while (savingsscanner.hasNext()) {
                string = savingsscanner.nextLine();
                if (iterator == 0) {
                    iterator++;
                    continue;
                }
                entries = string.split(",");
                String accountid = entries[0];
                int ssn = Integer.parseInt(entries[1]);
                double balance = Double.parseDouble(entries[2]);
                double interestrate = Double.parseDouble(entries[3]);
                LocalDate dateopened = LocalDate.parse(entries[4], formatter);
                String passbook = entries[5];
                LocalDate CDdue;
                if (entries[6].equalsIgnoreCase("NA")) {
                    CDdue = null;
                } else CDdue = LocalDate.parse(entries[6], formatter);
                boolean atmcard = Boolean.parseBoolean(entries[7]);
                newsavings = new Savings(accountid, ssn, balance, interestrate, dateopened, passbook, CDdue, atmcard);
                allaccounts.add(newsavings);
            }
            for (int i = 0; i < Customers.size(); i++) {
                for (int j = 0; j < allaccounts.size(); j++) {
                    if (allaccounts.get(j).getSsn() == Customers.get(i).getSSN()) {
                        Customers.get(i).AddAccount(allaccounts.get(j));
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream("t.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Customers);
            oos.close();
        } else {
            FileInputStream fis = new FileInputStream("t.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Customers = (ArrayList<Customer>) ois.readObject();
            ois.close();
        }
        // Main method to run the GUI
        launch(args);
    }

    /**
     * Start method for App
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
            // Add anything you want to happen when the program starts here
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // Set the title of the window
            primaryStage.setTitle("Banking System User Selection");
            primaryStage.setScene(scene);
            primaryStage.show();


            // Prints to console when the program is closed
            primaryStage.setOnCloseRequest(event -> {
                try {
                    Writer wr = new FileWriter("savingsid.txt");
                    wr.write(String.valueOf(savingsidcounter));
                    wr.close();
                }catch(Exception e){

                }
                try {
                    FileOutputStream fos = new FileOutputStream("t.tmp");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(Customers);
                    oos.close();
                }catch(Exception e){
                    System.out.println(e);
                }
                System.out.println("Exiting program...");
            });

        } catch (Exception e) {

        }
    }
}