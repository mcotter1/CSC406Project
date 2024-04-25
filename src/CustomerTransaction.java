import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;


    /**
     * Controller class for managing the display of account transactions within a banking application.
     * It allows the user to view detailed transaction history based on account type, including Checking,
     * Savings, and Loan accounts.
     */
public class CustomerTransaction implements Initializable{
    
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Transaction> transactionTable;


    /**
     * Initializes the scene by populating the transaction table based on the type of account selected.
     * It uses different account types from the current customer to show relevant transactions with appropriate formatting.
     *
     * @param location The location used to resolve relative paths for the root object, or null if not specified.
     * @param resources The resources used to localize the root object, or null if not specified.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
        //Just Cheking Accounts
        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")){
        Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        ArrayList<Transaction> worktransactions = workchecking.getTransactions();
        ObservableList<Transaction> transactionlist = FXCollections.observableArrayList(worktransactions);
            transactionTable.setItems(transactionlist);
            TableColumn<Transaction,String> tcolumn1 = new TableColumn<>("Type");
            tcolumn1.setCellValueFactory(new PropertyValueFactory<>("transactiontype"));
            TableColumn<Transaction,String> tcolumn2 = new TableColumn<>("Account Type");
            tcolumn2.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Transaction,Double> tcolumn3 = new TableColumn<>("Amount");
            tcolumn3.setCellValueFactory(new PropertyValueFactory<>("amount"));
            tcolumn3.setCellFactory(c -> new TableCell<Transaction, Double>() {
                @Override
                protected void updateItem(Double amount,
                                          boolean empty) {

                    super.updateItem(amount, empty);

                    setGraphic(null);
                    if (empty || amount == null) {
                        setText(null);
                    } else {
                        setText(balanceFormat.format(amount));
                    }
                }
            });
            TableColumn<Transaction,LocalDate> tcolumn4 = new TableColumn<>("Date");
            tcolumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccurred"));
            TableColumn<Transaction,Double> tcolumn5 = new TableColumn<>("New Balance");
            tcolumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
            tcolumn5.setCellFactory(c -> new TableCell<Transaction, Double>() {
                @Override
                protected void updateItem(Double newbalance,
                                          boolean empty) {

                    super.updateItem(newbalance, empty);

                    setGraphic(null);
                    if (empty || newbalance == null) {
                        setText(null);
                    } else {
                        setText(balanceFormat.format(newbalance));
                    }
                }
            });
            transactionTable.getColumns().setAll(tcolumn1,tcolumn2,tcolumn3,tcolumn4,tcolumn5);


            //Savings Accont
    }else if (App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){
        Savings worksavings = (Savings) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        ArrayList<Transaction> worktransactions = worksavings.getTransactions();
        ObservableList<Transaction> transactionlist = FXCollections.observableArrayList(worktransactions);
        transactionTable.setItems(transactionlist);
        TableColumn<Transaction,String> tColumn1 = new TableColumn<>("Type");
        tColumn1.setCellValueFactory(new PropertyValueFactory<>("transactiontype"));
        TableColumn<Transaction,String> tColumn2 = new TableColumn<>("Account Type");
        tColumn2.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
        TableColumn<Transaction,Double> tColumn3 = new TableColumn<>("Amount");
        tColumn3.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tColumn3.setCellFactory(c -> new TableCell<Transaction, Double>() {
            @Override
            protected void updateItem(Double amount,boolean empty) {

                super.updateItem(amount, empty);

                setGraphic(null);
                if (empty || amount == null) {
                    setText(null);
                }else{
                    setText(balanceFormat.format(amount));
                }
            }
        });
        TableColumn<Transaction,LocalDate> tColumn4 = new TableColumn<>("Date");
        tColumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccurred"));
        TableColumn<Transaction,Double> tColumn5 = new TableColumn<>("New Balance");
        tColumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
        tColumn3.setCellFactory(c -> new TableCell<Transaction, Double>() {
            @Override
            protected void updateItem(Double newblance,boolean empty) {

                super.updateItem(newblance, empty);

                setGraphic(null);
                if (empty || newblance == null) {
                    setText(null);
                }else{
                    setText(balanceFormat.format(newblance));
                }
            }
        });
        transactionTable.getColumns().setAll(tColumn1,tColumn2,tColumn3,tColumn4,tColumn5);

        //Loan Accounts
    }else if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("long term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("short term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("credit card")){
        Loan workLoan = (Loan) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        ArrayList<Transaction> worktransactions = workLoan.getTransactions();
        ObservableList<Transaction> transactionlist = FXCollections.observableArrayList(worktransactions);
        transactionTable.setItems(transactionlist);
        TableColumn<Transaction,String> tColumn1 = new TableColumn<>("Type");
        tColumn1.setCellValueFactory(new PropertyValueFactory<>("transactiontype"));
        TableColumn<Transaction,String> tColumn2 = new TableColumn<>("Account Type");
        tColumn2.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
        TableColumn<Transaction,Double> tColumn3 = new TableColumn<>("Amount");
        tColumn3.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tColumn3.setCellFactory(c -> new TableCell<Transaction, Double>() {
            @Override
            protected void updateItem(Double amount,boolean empty) {

                super.updateItem(amount, empty);

                setGraphic(null);
                if (empty || amount == null) {
                    setText(null);
                }else{
                    setText(balanceFormat.format(amount));
                }
            }  
        });
        TableColumn<Transaction,LocalDate> tColumn4 = new TableColumn<>("Date");
        tColumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccurred"));
        TableColumn<Transaction,Double> tColumn5 = new TableColumn<>("New Balance");
        tColumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
        transactionTable.getColumns().setAll(tColumn1,tColumn2,tColumn3,tColumn4,tColumn5);
        }
    }


     /**
     * Returns to the payment selection screen when the back button is pressed.
     * 
     * @param event The ActionEvent that triggered this method.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void Back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }
}


// Credit Card is not working yet
