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
    private TextField Amounttxt;

    @FXML
    private TextField Descriptiontxt;

    @FXML
    private Button Backbtn;

    @FXML
    private Button CreditBtn;
    
    @FXML
    private Label errorLbl;

    @FXML
    private Label successlbl;
    @FXML
    private Label Accountlbl;
    //private int transferaccountindex;


    //Implement button Visability of Credit card acccount validation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Accountlbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());



    }
    //Transaction we have Account, Amount and Description
    //Check that the Credit card is not going over the Limit
    @FXML
    void CreditBtn(ActionEvent event) throws IOException {
        Account workAccount = (App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex));

        Loan loanwork = (Loan) workAccount;

        
        if (Descriptiontxt.getText().isBlank()){
            errorLbl.setText("Please enter a Description!");
            return;
        }
        if(Amounttxt.getText().isEmpty()){
            errorLbl.setText("Transfer amount cannot be 0");
            return;
        }
        Double workamount = Double.parseDouble(Amounttxt.getText());
        if((workamount + loanwork.getBalance()) >= loanwork.getCreditcardlimit()){
            errorLbl.setText("Credit Limit Reached");
        }else{
            //Transaction loantransaction = new Transaction(Descriptiontxt.getText(),loanwork.getAccounttype(),-workamount, LocalDate.now(),loanwork.getBalance()+workamount);
            BilledTransaction billedTransaction = new BilledTransaction(Descriptiontxt.getText(),loanwork.getAccounttype(),workamount, LocalDate.now(),loanwork.getBalance()+workamount, false);
            
            loanwork.setBalance(loanwork.getBalance()+workamount);
            //loanwork.AddTransaction(loantransaction);
            loanwork.AddBilledTransaction(billedTransaction);
            loanwork.setLastpaymentdate(LocalDate.now());
            errorLbl.setText(loanwork.toString());
            App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,loanwork);
            errorLbl.setText("");
            successlbl.setText("Credit Card Payment Successful!");
        }
        Accountlbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());

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
