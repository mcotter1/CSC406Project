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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerLinkAccountController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label error;
    @FXML
    private Label success;
    @FXML
    private Label accountlabel;
    @FXML
    private ComboBox<Account> accountbox;
    private int savingsindex;

    /**
     * This function initializes the combobox for choosing an account and the account label
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Account> accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
        accountbox.setItems(accounts);
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }

    /**
     * This function gets the savings account selection for linking
     * @param event
     */
    @FXML
    void GetSelection(ActionEvent event){
        savingsindex = accountbox.getSelectionModel().getSelectedIndex();
    }

    /**
     * This function links a chosen savings account to the selected checking account
     * @param event
     */
    @FXML
    void Link(ActionEvent event){
        Account workaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        Account worksavingsAccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(savingsindex);
        if(worksavingsAccount.getAccounttype().equalsIgnoreCase("simple savings")){
            Checking workchecking = (Checking) workaccount;
            Savings worksavings = (Savings) worksavingsAccount;
            workchecking.setOverdraftbackupnumber(worksavings.getSavingsaccountid());
            error.setText("");
            success.setText(String.format("Checking linked to account: %s",worksavings.getSavingsaccountid()));
            App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
        } else {
            error.setText("Must select simple savings to link");
            success.setText("");
        }
    }

    /**
     * This function goes back to manager options
     * @param event
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
}
