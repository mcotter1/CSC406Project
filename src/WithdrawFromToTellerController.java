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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
        toaccountbox.setItems(accounts);
    }
    @FXML
    void BackToTellerOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //This is a function for processing transfers between checking and savings accounts
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
                workaccount.setBalance(workaccount.getBalance()-Double.parseDouble(transferamount.getText()));
                transferaccount.setBalance(transferaccount.getBalance()+Double.parseDouble(transferamount.getText()));
                success.setText("Transfer Successful");
                error.setText("");
                accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
                toaccountbox.setItems(accounts);
                accountlabel.setText(workaccount.toString());
                App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workaccount);
                App.Customers.get(App.currentcustomerindex).getAccounts().set(transferaccountindex,transferaccount);
            }
        }
    }
    @FXML
    void SetTransferAccount(ActionEvent event){
        transferaccountindex = toaccountbox.getSelectionModel().getSelectedIndex();
    }

}
