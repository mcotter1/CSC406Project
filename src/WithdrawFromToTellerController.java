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

public class WithdrawFromToTellerController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label accountlabel;
    @FXML
    private ComboBox<Account> toaccountbox;
    @FXML
    private TextField transferamount;
    @FXML
    private Label error;
    @FXML
    private Label success;
    private int transferaccountindex;
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
    void BackToTellerOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This function processes a transfer between a savings and checking or between two of the same checking or
     * savings accounts. The main process checks if balance is sufficient and processes the transfer.
     * @param event The action event from this window
     */
    @FXML
    void ProcessTransfer(ActionEvent event){
        //get the relevant accounts
        Account workaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        Account transferaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(transferaccountindex);
        //check if account is same as already selected account
        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex)==App.Customers.get(App.currentcustomerindex).getAccounts().get(transferaccountindex)){
            error.setText("Must select account separate from current");
            success.setText("");
            return;
        }
        //check if transfer account is a valid account to transfer
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(transferaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(transferaccountindex).getAccounttype().equalsIgnoreCase("tmb")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(transferaccountindex).getAccounttype().equalsIgnoreCase("simple savings")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(transferaccountindex).getAccounttype().equalsIgnoreCase("cd")){
            error.setText("Must select Checking or Savings to Transfer");
            success.setText("");
            return;
        }
        //check if input field is blank
        if(transferamount.getText().isBlank()){
            error.setText("Please enter an amount to transfer");
            success.setText("");
            return;
        }
        //if number is valid, enter logic
        if(transferamount != null && !transferamount.getText().matches(".*[a-zA-Z]+.*")&&!transferamount.getText().isBlank()){
            if(Double.parseDouble(transferamount.getText())<=0){
                error.setText("Transfer amount cannot be 0");
                success.setText("");
            }
            //check if amount is too much
            if(Double.parseDouble(transferamount.getText())>workaccount.getBalance()){
                error.setText("Not enough funds");
                success.setText("");
            }
            else{
                Transaction transferaccounttransaction = new Transaction("Transfer To",transferaccount.getAccounttype(),Double.parseDouble(transferamount.getText()),LocalDate.now(),transferaccount.getBalance()+Double.parseDouble(transferamount.getText()));
                if(workaccount.getAccounttype().equalsIgnoreCase("tmb")){
                    Transaction workaccounttransaction = new Transaction("Transfer From",workaccount.getAccounttype(),-(Double.parseDouble(transferamount.getText())), LocalDate.now(),workaccount.getBalance()-Double.parseDouble(transferamount.getText())-1.25);
                    workaccount.setBalance(workaccount.getBalance()-Double.parseDouble(transferamount.getText()));
                    workaccount.setBalance(workaccount.getBalance()-1.25);
                    workaccount.AddTransaction(workaccounttransaction);
                } else {
                    Transaction workaccounttransaction = new Transaction("Transfer From",workaccount.getAccounttype(),-(Double.parseDouble(transferamount.getText())), LocalDate.now(),workaccount.getBalance()-Double.parseDouble(transferamount.getText()));
                    workaccount.setBalance(workaccount.getBalance()-Double.parseDouble(transferamount.getText()));
                    workaccount.AddTransaction(workaccounttransaction);
                }
                transferaccount.setBalance(transferaccount.getBalance()+Double.parseDouble(transferamount.getText()));
                success.setText("Transfer Successful");
                error.setText("");
                transferaccount.AddTransaction(transferaccounttransaction);
                accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
                toaccountbox.setItems(accounts);
                accountlabel.setText(workaccount.toString());
                App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workaccount);
                App.Customers.get(App.currentcustomerindex).getAccounts().set(transferaccountindex,transferaccount);
            }
        }
    }

    /**
     * This function gets the index of the combo box selection
     * @param event the ActionEvent from this window
     */
    @FXML
    void SetTransferAccount(ActionEvent event){
        transferaccountindex = toaccountbox.getSelectionModel().getSelectedIndex();
    }

}
