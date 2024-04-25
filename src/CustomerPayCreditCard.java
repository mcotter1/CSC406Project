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


/**
 * Controls the functionality for paying credit card bills through a customer interface
 * within a banking application.
 */

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

    /**
     * Initializes the controller by updating the account label with current account information.
     *
     * @param location The location used to resolve relative paths for the root object, or null if not specified.
     * @param resources The resources used to localize the root object, or null if not specified.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Accountlbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }
    /**
     * Processes a credit card payment when the credit button is pressed.
     * Validates the amount and description, and checks that the credit limit is not exceeded.
     *
     * @param event The ActionEvent triggered by clicking the credit button.
     * @throws IOException if the processing fails due to navigation issues.
     */
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
            BilledTransaction billedTransaction = new BilledTransaction(Descriptiontxt.getText(),loanwork.getAccounttype(),workamount, LocalDate.now(),loanwork.getBalance()+workamount, false);
            
            loanwork.setBalance(loanwork.getBalance()+workamount);
            loanwork.AddBilledTransaction(billedTransaction);
            loanwork.setLastpaymentdate(LocalDate.now());
            errorLbl.setText(loanwork.toString());
            App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,loanwork);
            errorLbl.setText("");
            successlbl.setText("Credit Card Payment Successful!");
        }
        Accountlbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());

    }
    /**
     * Navigates back to the customer payment selection screen when the back button is pressed.
     *
     * @param event The ActionEvent triggered by clicking the back button.
     * @throws IOException if the navigation fails due to FXML loading issues.
     */
    @FXML
    void backBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
}
