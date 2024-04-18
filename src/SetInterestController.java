import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SetInterestController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label accountlabel;
    @FXML
    private ComboBox<Account> toaccountbox;
    @FXML
    private TextField interestamount;
    @FXML
    private Label error;
    @FXML
    private Label success;
    private int interestaccountindex;
    private ObservableList<Account> accounts;

    /**
     * This function initializes the combobox and account label
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
        toaccountbox.setItems(accounts);
    }

    /**
     * Returns to teller options
     * @param event the ActionEvent from the window
     * @throws IOException
     */
    @FXML
    void BackToManagerOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   
    @FXML
    void ChangeInterestRate(ActionEvent event){
        Account interestaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(interestaccountindex);

        if (interestaccount instanceof Loan) {
            Loan interestloan = (Loan) interestaccount;

            if (toaccountbox.getValue() == null) {
                error.setText("Please select an account");
                success.setText("");
                return;
            }
            //check if input field is blank
            if(interestamount.getText().isBlank()){
                error.setText("Please enter an interest rate");
                success.setText("");
                return;
            }
            //if number is valid, enter logic
            if(interestamount != null && !interestamount.getText().matches(".*[a-zA-Z]+.*")&&!interestamount.getText().isBlank()){
                if(Double.parseDouble(interestamount.getText())<=0){ 
                    error.setText("Amount cannot be less than 0");
                    success.setText("");
                    return;
                } else {
                    //Transaction interesttransaction = new Transaction("Set Interest Rate",interestaccount.getAccounttype(),Double.parseDouble(interestamount.getText()),LocalDate.now(),interestaccount.getBalance());
                    interestloan.setInterestrate(Double.parseDouble(interestamount.getText()) / 100);
                    success.setText("Interest Rate Set");
                    error.setText("");
                    //interestaccount.AddTransaction(interesttransaction);
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(interestaccountindex,interestaccount);
                }
            }
            
        } else {
            error.setText("Selected account is not a loan account or there is no account selected.");
            success.setText("");
            return;
        }
    }

    /**
     * This function gets the index of the combo box selection
     * @param event the ActionEvent from this window
     */
    @FXML
    void SetInterestAccount(ActionEvent event){
        interestaccountindex = toaccountbox.getSelectionModel().getSelectedIndex();
    }

}
