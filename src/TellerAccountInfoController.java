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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TellerAccountInfoController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private TableView<Account> tablebox;
    @FXML
    private TableView<Transaction> transactionbox;
    @FXML
    private Label accountstatus;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")){
            Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Account> checkinglist = FXCollections.observableArrayList();
            checkinglist.addAll(workchecking);
            tablebox.setItems(checkinglist);
            TableColumn<Account,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Account,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            TableColumn<Account,LocalDate> column3= new TableColumn<>("Date Opened");
            column3.setCellValueFactory(new PropertyValueFactory<>("dateopened"));
            TableColumn<Account,String> column4 = new TableColumn<>("Savings ID");
            column4.setCellValueFactory(new PropertyValueFactory<>("savingsaccountid"));
            tablebox.getColumns().setAll(column1,column2,column3,column4);
            ArrayList<Transaction> worktransactions = workchecking.getTransactions();
            ObservableList<Transaction> transactionlist = FXCollections.observableArrayList(worktransactions);
            transactionbox.setItems(transactionlist);
            TableColumn<Transaction,String> tcolumn1 = new TableColumn<>("Type");
            tcolumn1.setCellValueFactory(new PropertyValueFactory<>("transactiontype"));
            TableColumn<Transaction,String> tcolumn2 = new TableColumn<>("Account Type");
            tcolumn2.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Transaction,Double> tcolumn3 = new TableColumn<>("Amount");
            tcolumn3.setCellValueFactory(new PropertyValueFactory<>("amount"));
            TableColumn<Transaction,LocalDate> tcolumn4 = new TableColumn<>("Date");
            tcolumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccurred"));
            TableColumn<Transaction,Double> tcolumn5 = new TableColumn<>("New Balance");
            tcolumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
            transactionbox.getColumns().setAll(tcolumn1,tcolumn2,tcolumn3,tcolumn4,tcolumn5);
        } else if (App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){
            Savings workingsavings = (Savings) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Account> savingslist = FXCollections.observableArrayList();
            savingslist.addAll(workingsavings);
            tablebox.setItems(savingslist);
            TableColumn<Account,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Account,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            TableColumn<Account,LocalDate> column3= new TableColumn<>("Date Opened");
            column3.setCellValueFactory(new PropertyValueFactory<>("dateopened"));
            TableColumn<Account,String> column4 = new TableColumn<>("Savings ID");
            column4.setCellValueFactory(new PropertyValueFactory<>("savingsaccountid"));
            TableColumn<Account,Double> column5 = new TableColumn<>("Interest Rate");
            column5.setCellValueFactory(new PropertyValueFactory<>("interestrate"));
            TableColumn<Account,LocalDate> column6= new TableColumn<>("Date CD Due");
            column6.setCellValueFactory(new PropertyValueFactory<>("CDdue"));
            tablebox.getColumns().setAll(column1,column2,column3,column4,column5,column6);
            ArrayList<Transaction> transactions = workingsavings.getTransactions();
            ObservableList<Transaction> transactionslist = FXCollections.observableArrayList(transactions);
            transactionbox.setItems(transactionslist);
            TableColumn<Transaction,String> tcolumn1 = new TableColumn<>("Type");
            tcolumn1.setCellValueFactory(new PropertyValueFactory<>("transactiontype"));
            TableColumn<Transaction,String> tcolumn2 = new TableColumn<>("Account Type");
            tcolumn2.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Transaction,Double> tcolumn3 = new TableColumn<>("Amount");
            tcolumn3.setCellValueFactory(new PropertyValueFactory<>("amount"));
            TableColumn<Transaction,LocalDate> tcolumn4 = new TableColumn<>("Date");
            tcolumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccurred"));
            TableColumn<Transaction,Double> tcolumn5 = new TableColumn<>("New Balance");
            tcolumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
            transactionbox.getColumns().setAll(tcolumn1,tcolumn2,tcolumn3,tcolumn4,tcolumn5);
        } else if (App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("long term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("short term loan")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("credit card")){
            Loan workloan = (Loan) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Account> loanlist = FXCollections.observableArrayList();
            loanlist.addAll(workloan);
            tablebox.setItems(loanlist);
            TableColumn<Account,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Account,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            TableColumn<Account,Double> column3= new TableColumn<>("Amount Due");
            column3.setCellValueFactory(new PropertyValueFactory<>("paymentamountdue"));
            TableColumn<Account,LocalDate> column4 = new TableColumn<>("Due Date");
            column4.setCellValueFactory(new PropertyValueFactory<>("datepaymentdue"));
            TableColumn<Account,Double> column5 = new TableColumn<>("Interest Rate");
            column5.setCellValueFactory(new PropertyValueFactory<>("interestrate"));
            TableColumn<Account,LocalDate> column6= new TableColumn<>("Last Payment");
            column6.setCellValueFactory(new PropertyValueFactory<>("lastpaymentdate"));
            TableColumn<Account,LocalDate> column7= new TableColumn<>("Notified");
            column7.setCellValueFactory(new PropertyValueFactory<>("notifiedofpayment"));
            TableColumn<Account,String> column8= new TableColumn<>("Loan Type");
            column8.setCellValueFactory(new PropertyValueFactory<>("loantype"));
            TableColumn<Account,Double> column9= new TableColumn<>("Term Length");
            column9.setCellValueFactory(new PropertyValueFactory<>("termlength"));
            TableColumn<Account,String> column10= new TableColumn<>("Collateral");
            column10.setCellValueFactory(new PropertyValueFactory<>("collateral"));
            TableColumn<Account,String> column11= new TableColumn<>("Repayment Plan");
            column11.setCellValueFactory(new PropertyValueFactory<>("repaymentplantype"));
            TableColumn<Account,Double> column12= new TableColumn<>("Credit Limit");
            column12.setCellValueFactory(new PropertyValueFactory<>("creditcardlimit"));
            tablebox.getColumns().setAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column12);
            ArrayList<Transaction> transactions = workloan.getTransactions();
            ObservableList<Transaction> transactionslist = FXCollections.observableArrayList(transactions);
            transactionbox.setItems(transactionslist);
            TableColumn<Transaction,String> tcolumn1 = new TableColumn<>("Type");
            tcolumn1.setCellValueFactory(new PropertyValueFactory<>("transactiontype"));
            TableColumn<Transaction,String> tcolumn2 = new TableColumn<>("Account Type");
            tcolumn2.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Transaction,Double> tcolumn3 = new TableColumn<>("Amount");
            tcolumn3.setCellValueFactory(new PropertyValueFactory<>("amount"));
            TableColumn<Transaction,LocalDate> tcolumn4 = new TableColumn<>("Date");
            tcolumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccurred"));
            TableColumn<Transaction,Double> tcolumn5 = new TableColumn<>("New Balance");
            tcolumn5.setCellValueFactory(new PropertyValueFactory<>("newbalance"));
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
