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

public class ManagerOptionsController implements Initializable{
    // This is the button for the Manager scene
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private Label accountlabel;
    @FXML
    private Label error;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }

    // This method is used to switch to the Customer scene
    @FXML
    void CustomerBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Customer Button Clicked");
    }

    // This method is used to switch to the Manager scene
    @FXML
    void ManagerBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Manager Button Clicked");
    }

    @FXML
    void PaperDepositBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("PaperDeposit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Paper Deposit Button Clicked");
    }

    @FXML
    void creditCardBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("CreditCard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Card Button Clicked");
    }

    @FXML
    void SSNverifyBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("SSN Verify Button Clicked");
    }

    @FXML
    void ATMCardBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("VerifyPin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Card Button Clicked");
    }

    @FXML
    void ManagerviewCustomerData(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("ManagerAccountInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("View Customer Data Button Clicked");
    }


    // This method is used to switch back to the main scene
    // used for the back button in Teller, Manager, and Customer scenes
    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }

    // Opens a new window to createa new customer with all the data fields
    @FXML
    void CreateNewCustomer(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("CreateNewCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Create New Customer Button Clicked");
    }
    @FXML
    void DisplayRecentDebitsManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RecentDebitsManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Display Recent Debits Button Clicked");
    }

    @FXML
    void CreditAccountManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCreditAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Account Button Clicked");
    }
    @FXML
    void DebitAccountManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerDebitAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Account Button Clicked");
    }
    
    @FXML
    void AccountBalanceManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountBalanceManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account balance Button Clicked");
    }
    @FXML
    void AccountStatusDisplayedManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountStatusDisplayedManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }

    @FXML
    void ManagerSearchCustomerID(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Manager Options Button Clicked");
    }

    @FXML
    void ManagerCreateNewCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCreateNewCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Manager Create New Customer Button Clicked");
    }

    @FXML
    void setInterestRate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SetInterest.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void linkAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LinkingAccounts.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void processCheckManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("processCheckManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showAccountInfoManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerAccountInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void newCD(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("NewCD.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showCustAccountInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerReviewAccountStatus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void TransferManager(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){
            error.setText("Account does not support transfer");
        } else {
            root = FXMLLoader.load(getClass().getResource("WithdrawFromToManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Transfer Money Button Clicked");
        }
    }

}
