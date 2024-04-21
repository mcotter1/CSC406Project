import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.LocalDate;




public class CustomerPayCreditCard implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Account> AccountSelection;

    @FXML
    private TextField Amounttxt;

    @FXML
    private TextField Descriptiontxt;

    @FXML
    private Button Backbtn;

    @FXML
    private Button CreditBtn;
    
    @FXML
    private Label errorLbl;

    //private int transferaccountindex;


    //Implement button Visability of Credit card acccount validation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Account> accounts = App.Customers.get(App.currentcustomerindex).getAccounts();
        ObservableList<Account> LoanList = FXCollections.observableArrayList();
        for (int i = 0; i < accounts.size(); i++) { // Loop through all accounts and classify them
        Account account = accounts.get(i);
        if (account instanceof Loan) {
            if(account.getAccounttype().equalsIgnoreCase("Credit Card")){   // Only selects Accounts for the Credit Card! 
            LoanList.add((Loan) account);    //Adding the Accounts to a combinedList
            }
        }
    }
    AccountSelection.setItems(LoanList);    // Only displaying Loan Credit Card
    }
    //Transaction we have Account, Amount and Description
    //Check that the Credit card is not going over the Limit
    @FXML
    void CreditBtn(ActionEvent event) throws IOException {
        Account workAccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        Loan loanwork = (Loan) workAccount;

        if(AccountSelection.getValue()==null){
           errorLbl.setText("Please select an account");
           return;
        }
        if (Descriptiontxt.getText().isBlank()){
            errorLbl.setText("Please enter a Description!");
            return;
        }
        if(Amounttxt.getText().isEmpty()){
            errorLbl.setText("Transfer amount cannot be 0");
            return;
        }
        // If everything is valid we need to add to the balance of the Credit Card account
        Double workamount = Double.parseDouble(Amounttxt.getText());

        if((workamount + loanwork.getBalance()) >= loanwork.getCreditcardlimit()){
            errorLbl.setText("Credit Limit Reached");
        }else{
            Transaction loantransaction = new Transaction(Descriptiontxt.getText(),loanwork.getAccounttype(),-workamount, LocalDate.now(),loanwork.getBalance()+workamount);
            
            loanwork.setBalance(loanwork.getBalance()+workamount);
            loanwork.AddTransaction(loantransaction);
            loanwork.setLastpaymentdate(LocalDate.now());
            errorLbl.setText(loanwork.toString());
            App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,loanwork);
            errorLbl.setText("");
            //workaccount.setBalance(workaccount.getBalance() + Double.parseDouble(Amounttxt.getText())); // Update balance
        }
    }
    @FXML
    void backBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
}
