import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TellerOptionsController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label accountlabel;
    @FXML
    private Label error;
    @FXML
    private Label customername;

    /**
     * This function initializes the account label and customer name for teller options
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        customername.setText(String.format("Customer: %s %s ",App.Customers.get(App.currentcustomerindex).getFirstname(),App.Customers.get(App.currentcustomerindex).getLastname()));
    }

    /**
     * This function goes back to teller select account
     * @param event
     * @throws Exception
     */
    @FXML
    void TellerBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TellerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Teller Button Clicked");
    }

    /**
     * This function goes to link account screen if checking account is selected
     * @param event
     * @throws Exception
     */
    @FXML
    void LinkAccountButton(ActionEvent event) throws Exception {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")){
            error.setText("Can Only Link Checking");
        } else {
            root = FXMLLoader.load(getClass().getResource("TellerLinkAccount.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Teller Button Clicked");
        }
    }

    /**
     * This function goes to the teller debit account screen
     * @param event
     * @throws IOException
     */
    @FXML
    void DebitAccountTeller(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){
            error.setText("Cannot Debit Loan Account");
        } else {
            root = FXMLLoader.load(getClass().getResource("TellerDebitAccount.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Debit Account Button Clicked");
        }
    }

    /**
     * This function goes to the teller transfer screen
     * @param event
     * @throws IOException
     */
    @FXML
    void TransferTeller(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){
            error.setText("Account does not support transfer");
        } else {
            root = FXMLLoader.load(getClass().getResource("WithdrawFromToTeller.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Transfer Money Button Clicked");
        }
    }

    /**
     * This function goes to the teller credit account screen
     * @param event
     * @throws IOException
     */
    @FXML
    void CreditAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerCreditAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Account Button Clicked");
    }

    /**
     * This function goes to the teller account info screen
     * @param event
     * @throws IOException
     */
    @FXML
    void showAccountInfoTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerAccountInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This function goes to the Teller stop payment screen
     * @param event
     * @throws IOException
     */
    @FXML
    void StopPayment(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")){
            error.setText("Stop Payment Only For Checking");
        } else {
            root = FXMLLoader.load(getClass().getResource("TellerStopPayment.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
