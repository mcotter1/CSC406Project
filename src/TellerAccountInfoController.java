import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TellerAccountInfoController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private TableView<Checking> tableboxchecking;
    @FXML
    private TableView<Savings> tableboxsavings;
    @FXML
    private TableView<Loan> tableboxloan;
    @FXML
    private TableView<Transaction> transactionbox;
    @FXML
    private Label accountstatus;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        tableboxsavings.setVisible(false);
        tableboxloan.setVisible(false);
        tableboxchecking.setVisible(true);
        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")){
            Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Checking> checkinglist = FXCollections.observableArrayList();
            checkinglist.addAll(workchecking);
            tableboxchecking.setItems(checkinglist);
            TableColumn<Checking,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Checking,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
            column2.setCellFactory(c -> new TableCell<Checking, Double>() {
                @Override
                protected void updateItem(Double balance,
                                          boolean empty) {

                    super.updateItem(balance, empty);

                    if (empty || balance == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(balanceFormat.format(balance));
                    }
                }
            });
            TableColumn<Checking,LocalDate> column3= new TableColumn<>("Date Opened");
            column3.setCellValueFactory(new PropertyValueFactory<>("dateopened"));
            TableColumn<Checking,String> column4 = new TableColumn<>("Savings ID");
            column4.setCellValueFactory(
                    cellData -> new SimpleStringProperty(
                            cellData.getValue().getSavingsaccountid()));
            tableboxchecking.getColumns().setAll(column1,column2,column3,column4);
            ArrayList<Transaction> worktransactions = workchecking.getTransactions();
            ObservableList<Transaction> transactionlist = FXCollections.observableArrayList(worktransactions);
            transactionbox.setItems(transactionlist);
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
            transactionbox.getColumns().setAll(tcolumn1,tcolumn2,tcolumn3,tcolumn4,tcolumn5);
        } else if (App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){
            tableboxloan.setVisible(false);
            tableboxchecking.setVisible(false);
            tableboxsavings.setVisible(true);
            Savings workingsavings = (Savings) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Savings> savingslist = FXCollections.observableArrayList();
            savingslist.addAll(workingsavings);
            tableboxsavings.setItems(savingslist);
            TableColumn<Savings,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Savings,Double> column2 = new TableColumn<>("Balance");
            NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            column2.setCellFactory(c -> new TableCell<Savings, Double>() {
                @Override
                protected void updateItem(Double balance,
                                          boolean empty) {

                    super.updateItem(balance, empty);

                    setGraphic(null);
                    if (empty || balance == null) {
                        setText(null);
                    } else {
                        setText(balanceFormat.format(balance));
                    }
                }
            });
            TableColumn<Savings,LocalDate> column3= new TableColumn<>("Date Opened");
            column3.setCellValueFactory(new PropertyValueFactory<>("dateopened"));
            TableColumn<Savings,String> column4 = new TableColumn<>("Savings ID");
            column4.setCellValueFactory(new PropertyValueFactory<>("savingsaccountid"));
            TableColumn<Savings,Double> column5 = new TableColumn<>("Interest Rate");
            column5.setCellValueFactory(new PropertyValueFactory<>("interestrate"));
            TableColumn<Savings,LocalDate> column6= new TableColumn<>("Date CD Due");
            column6.setCellValueFactory(new PropertyValueFactory<>("CDdue"));
            tableboxsavings.getColumns().setAll(column1,column2,column3,column4,column5,column6);
            ArrayList<Transaction> transactions = workingsavings.getTransactions();
            ObservableList<Transaction> transactionslist = FXCollections.observableArrayList(transactions);
            transactionbox.setItems(transactionslist);
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
            transactionbox.getColumns().setAll(tcolumn1,tcolumn2,tcolumn3,tcolumn4,tcolumn5);
        } else if (App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("long term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("short term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("credit card")){
            tableboxchecking.setVisible(false);
            tableboxsavings.setVisible(false);
            tableboxloan.setVisible(true);
            Loan workloan = (Loan) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Loan> loanlist = FXCollections.observableArrayList();
            loanlist.addAll(workloan);
            tableboxloan.setItems(loanlist);
            TableColumn<Loan,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Loan,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
            column2.setCellFactory(c -> new TableCell<Loan, Double>() {
                @Override
                protected void updateItem(Double balance,
                                          boolean empty) {

                    super.updateItem(balance, empty);

                    setGraphic(null);
                    if (empty || balance == null) {
                        setText(null);
                    } else {
                        setText(balanceFormat.format(balance));
                    }
                }
            });
            TableColumn<Loan,Double> column3= new TableColumn<>("Amount Due");
            column3.setCellValueFactory(new PropertyValueFactory<>("paymentamountdue"));
            TableColumn<Loan,LocalDate> column4 = new TableColumn<>("Due Date");
            column4.setCellValueFactory(new PropertyValueFactory<>("datepaymentdue"));
            TableColumn<Loan,Double> column5 = new TableColumn<>("Interest Rate");
            column5.setCellValueFactory(new PropertyValueFactory<>("interestrate"));
            TableColumn<Loan,LocalDate> column6= new TableColumn<>("Last Payment");
            column6.setCellValueFactory(new PropertyValueFactory<>("lastpaymentdate"));
            TableColumn<Loan,LocalDate> column7= new TableColumn<>("Notified");
            column7.setCellValueFactory(new PropertyValueFactory<>("notifiedofpayment"));
            TableColumn<Loan,String> column8= new TableColumn<>("Loan Type");
            column8.setCellValueFactory(new PropertyValueFactory<>("loantype"));
            TableColumn<Loan,Double> column9= new TableColumn<>("Term Length");
            column9.setCellValueFactory(new PropertyValueFactory<>("termlength"));
            TableColumn<Loan,String> column10= new TableColumn<>("Collateral");
            column10.setCellValueFactory(new PropertyValueFactory<>("collateral"));
            TableColumn<Loan,String> column11= new TableColumn<>("Repayment Plan");
            column11.setCellValueFactory(new PropertyValueFactory<>("repaymentplantype"));
            TableColumn<Loan,Double> column12= new TableColumn<>("Credit Limit");
            column12.setCellValueFactory(new PropertyValueFactory<>("creditcardlimit"));
            tableboxloan.getColumns().setAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column12);
            ArrayList<Transaction> transactions = workloan.getTransactions();
            ObservableList<Transaction> transactionslist = FXCollections.observableArrayList(transactions);
            transactionbox.setItems(transactionslist);
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
            transactionbox.getColumns().setAll(tcolumn1,tcolumn2,tcolumn3,tcolumn4,tcolumn5);
            if(workloan.isMissedpayment()){
                accountstatus.setText("Missed last payment");
            }
        }
    }
    @FXML
    void BackToTellerOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
