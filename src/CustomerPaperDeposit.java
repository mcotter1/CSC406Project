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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CustomerPaperDeposit implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    public static int currentcustomerindex;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AccountTypelbl;

    @FXML
    private Label errorLbl;

    @FXML
    private Label confirmLbl;

    @FXML
    private TextField Amounttxt;

    @FXML
    private Button Backbtn;

    @FXML
    private Button Depositbtn;

    @FXML
    public void Backbtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }
    @FXML
    public void Depositbtn(ActionEvent event) throws IOException {
        //Basic Layout and it updated the accounts, if Credit card or loan we need to redo minimum payment        
        if(Amounttxt.getText().isEmpty()){
            errorLbl.setText("Transfer amount cannot be empty");
            return;
        }
        Double workamount = Double.parseDouble(Amounttxt.getText());
        Account workAccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);


        if(workAccount.getAccounttype().equalsIgnoreCase("credit card") || workAccount.getAccounttype().equalsIgnoreCase("long term loan")){
            Loan loanworking = (Loan) workAccount;
            Transaction loanTransaction = new Transaction("Payment",workAccount.getAccounttype(),+workamount, LocalDate.now(),workAccount.getBalance()+workamount);
            loanworking.setPaymentamountdue(loanworking.getPaymentamountdue() - workamount); //set the current amount due
            loanworking.setBalance(loanworking.getBalance()-workamount);
            loanworking.AddTransaction(loanTransaction);
            loanworking.setLastpaymentdate(LocalDate.now());
            Amounttxt.setText("");
            confirmLbl.setText("Payment Successfull!");
            AccountTypelbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        }else{
            Account workAccountelse = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            Transaction simpletransaction = new Transaction("Deposit",workAccountelse.getAccounttype(),+workamount, LocalDate.now(),workAccountelse.getBalance()+workamount);
            workAccountelse.setBalance(workAccountelse.getBalance() + workamount);
            workAccountelse.AddTransaction(simpletransaction);
            Amounttxt.setText("");
            confirmLbl.setText("Deposit Successfull!");
            AccountTypelbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        //Done
        AccountTypelbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());

    }
}
