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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();

        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")){
        Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        ArrayList<Transaction> worktransactions = workchecking.getTransactions();
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
            protected void updateItem(Double amount,
                                      boolean empty) {

                super.updateItem(amount, empty);

                setGraphic(null);
                if (empty || amount == null) {
                    setText(null);
                }else{{
                    setText(balanceFormat.format(amount));
                }
                    
                    
                }
            }
        });
        TableColumn<Transaction,Double> tColumn4 = new TableColumn<>("Date");
        tColumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccured"));
        TableColumn<Transaction,Double> tColumn5 = new TableColumn<>("New Balance");
        tColumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
        transactionTable.getColumns().setAll(tColumn1,tColumn2,tColumn3,tColumn4,tColumn5);
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
            protected void updateItem(Double amount,
                                      boolean empty) {

                super.updateItem(amount, empty);

                setGraphic(null);
                if (empty || amount == null) {
                    setText(null);
                }else{
                    setText(balanceFormat.format(amount));
                }
            }
        });
        TableColumn<Transaction,Double> tColumn4 = new TableColumn<>("Date");
        tColumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccured"));
        TableColumn<Transaction,Double> tColumn5 = new TableColumn<>("New Balance");
        tColumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
        transactionTable.getColumns().setAll(tColumn1,tColumn2,tColumn3,tColumn4,tColumn5);

    }else if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("long term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("short term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("credit card")){
        Loan workLoan = (Loan) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        ArrayList<Transaction> worktransactions = workLoan.getTransactions();
        ObservableList<Transaction> transactionlist = FXCollections.observableArrayList(worktransactions);
        transactionTable.setItems(transactionlist);
        TableColumn<Transaction,String> tColumn1 = new TableColumn<>("Account Type");
        tColumn1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
        TableColumn<Transaction,Double> tColumn2 = new TableColumn<>("Balance");
        tColumn2.setCellValueFactory(new PropertyValueFactory<>("balance"));
        tColumn2.setCellFactory(c -> new TableCell<Transaction, Double>() {
                @Override
                protected void updateItem(Double balance,
                                          boolean empty) {

                    super.updateItem(balance, empty);

                    setGraphic(null);
                    if (empty || balance == null) {
                        setText(null);
                    }else {
                        setText(balanceFormat.format(balance));
                    }
                }
            });
            TableColumn<Transaction,Double> tColumn3 = new TableColumn<>("Amount Due");
            tColumn3.setCellValueFactory(new PropertyValueFactory<>("paymentamountdue"));
            tColumn3.setCellFactory(c -> new TableCell<Transaction,Double>(){
                @Override
                protected void updateItem(Double paymentamountdue,boolean empty) {
                    super.updateItem(paymentamountdue, empty);
                    setGraphic(null);
                    if (empty || paymentamountdue == null) {
                        setText(null);
                    }else {
                        setText(balanceFormat.format(paymentamountdue));
                    }
                }
            });
            TableColumn<Transaction,LocalDate> tcolumn4 = new TableColumn<>("Due Date");
            tcolumn4.setCellValueFactory(new PropertyValueFactory<>("datepaymentdue"));
            TableColumn<Transaction,Double> tcolumn5 = new TableColumn<>("Interest Rate");
            tcolumn5.setCellValueFactory(new PropertyValueFactory<>("interestrate"));
            TableColumn<Transaction,LocalDate> tcolumn6= new TableColumn<>("Last Payment");
            tcolumn6.setCellValueFactory(new PropertyValueFactory<>("lastpaymentdate"));
            TableColumn<Transaction,LocalDate> tcolumn7= new TableColumn<>("Notified");
            tcolumn7.setCellValueFactory(new PropertyValueFactory<>("notifiedofpayment"));
            TableColumn<Transaction,String> tcolumn8= new TableColumn<>("Loan Type");
            tcolumn8.setCellValueFactory(new PropertyValueFactory<>("loantype"));
            TableColumn<Transaction,Double> tcolumn9= new TableColumn<>("Term Length");
            tcolumn9.setCellValueFactory(new PropertyValueFactory<>("termlength"));
            TableColumn<Transaction,String> tcolumn10= new TableColumn<>("Collateral");
            tcolumn10.setCellValueFactory(new PropertyValueFactory<>("collateral"));
            TableColumn<Transaction,String> tcolumn11= new TableColumn<>("Repayment Plan");
            tcolumn11.setCellValueFactory(new PropertyValueFactory<>("repaymentplantype"));
            TableColumn<Transaction,Double> tcolumn12= new TableColumn<>("Credit Limit");
            tcolumn12.setCellValueFactory(new PropertyValueFactory<>("creditcardlimit"));
            transactionTable.getColumns().setAll(tColumn1,tColumn2,tColumn3,tcolumn4,tcolumn5,tcolumn6,tcolumn7,tcolumn8,tcolumn9,tcolumn10,tcolumn11,tcolumn12);   
        }
    }
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
