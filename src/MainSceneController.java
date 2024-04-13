import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

public class MainSceneController extends App{
    // This class is the controller for the MainScene.fxml file
    @FXML
    private Button CustomerBtn;
    // This is the button for the Customer scene
    @FXML
    private Button ManagerBtn;
    // This is the button for the Manager scene
    @FXML
    private Button TellerBtn;
    // This is the button for the Teller scene
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    private int currentcustomerindex;
    private int currentaccountindex;
    @FXML
    private ComboBox<Account> tellercombobox;
    @FXML
    private ListView<Account> tellerlistview;

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

    // This method is used to switch to the Teller scene
    @FXML
    void TellerBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Teller Button Clicked");
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
    void insertCardBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Insert Card Button Clicked");
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
        root = FXMLLoader.load(getClass().getResource("ManagerDataView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("View Customer Data Button Clicked");
    }

    @FXML
    void TellerviewCustomerData(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TellerDataView.fxml"));
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

    // Search Customer field, not working yet, if customer is not found then the
    // ErrorCustomerID will be displayed


    @FXML
    void SearchCustomerID(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("ID was successful and Options are displayed");
    }

    @FXML
    void DisplayRecentDebitsTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RecentDebitsTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Display Recent Debits Button Clicked");
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
        root = FXMLLoader.load(getClass().getResource("CreditAccountManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Account Button Clicked");
    }

    @FXML
    void CreditAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreditAccountTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Account Button Clicked");
    }

    @FXML
    void DebitAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DebitAccountTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Account Button Clicked");
    }

    @FXML
    void DebitAccountManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DebitAccountManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Account Button Clicked");
    }

    @FXML
    void TransferTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WithdrawFromToTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Transfer Money Button Clicked");
    }

    @FXML
    void TransferManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WithdrawFromToManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Transfer Money Button Clicked");
    }

    @FXML
    void AccountBalanceTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountBalanceTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account balance Button Clicked");
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
    void AccountStatusDisplayedTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountStatusDisplayedTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
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
        System.out.println("Manager Teller Options Button Clicked");
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
    void processCheckCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("processCheckCust.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showAccountInfoManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerViewAccountInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showAccountInfoTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerAccountInfo.fxml"));
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
}
