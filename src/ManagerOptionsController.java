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
import java.time.LocalDate;
import java.util.List;
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
    @FXML
    private Label customername;

    /**
     * This method is used to initialize the ManagerOptions scene
     * @param location is the location of the scene
     * @param resources is the resources for the scene
     */

    @Override
    public void initialize(URL location, ResourceBundle resources){
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        customername.setText(String.format("Customer: %s %s ",App.Customers.get(App.currentcustomerindex).getFirstname(),App.Customers.get(App.currentcustomerindex).getLastname()));
        List<Account> accounts = App.Customers.get(App.currentcustomerindex).getAccounts();
        
        for (Account account : accounts) {
            if (account instanceof Savings) {
                Savings savingsAccount = (Savings) account;
                if ("CD".equalsIgnoreCase(savingsAccount.getAccounttype())) {
                    if(savingsAccount.getCDdue().isAfter(LocalDate.now())){ // check if the CD is not due
                        // check if the message has already been sent
                        if(App.Customers.get(App.currentcustomerindex).getMessages().contains("A CD account rolls over on " + savingsAccount.getCDdue().toString())){
                            // dont send a message
                        } else {
                            App.Customers.get(App.currentcustomerindex).AddMessage("A CD account rolls over on " + savingsAccount.getCDdue().toString());
                        }
                        System.out.println("CD is not due Your CD account has rolls over on " + savingsAccount.getCDdue().toString());
                    } else { // check if the CD is due
                        // check if the message has already been sent
                        if(App.Customers.get(App.currentcustomerindex).getMessages().contains("A CD account is due for ready renewal")){
                        // dont send a message
                        } else {
                            App.Customers.get(App.currentcustomerindex).AddMessage("A CD account is due for ready renewal");
                        }
                        System.out.println("CD is due");
                    }
                }
            } else {
                continue;
            }
        }
    }

    /**
     * This method is used to switch to the Teller scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */

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

    /**
     * This method is used to switch to the Teller scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */

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

    /**
     * This method is used to switch to the Teller scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */

    @FXML
    void PaperDepositBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("PaperDeposit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Paper Deposit Button Clicked");
    }

    /**
     * This method is used to switch to the CreditCard scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */

    @FXML
    void creditCardBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("CreditCard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Card Button Clicked");
    } 
    
    /**
     * This method is used to switch to the SSNVerify scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */

    @FXML
    void SSNverifyBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("SSN Verify Button Clicked");
    }

    /**
     * This method is used to switch to the ATMCard scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */


    @FXML
    void ATMCardBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("VerifyPin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Card Button Clicked");
    }

    /**
     * This method is used to switch to the Manager scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */
    
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

    /**
     * This method is used to switch back to the main scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }

    /**
     * This method is used to switch to the CreateNewCustomer scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */

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

    /**
     * This method is used to switch to the RecentDebitsManager scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void DisplayRecentDebitsManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RecentDebitsManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Display Recent Debits Button Clicked");
    }

    /**
     * This method is used to switch to the CreditAccountManager scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void CreditAccountManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCreditAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Account Button Clicked");
    }

    /**
     * This method is used to switch to the DebitAccountManager scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void DebitAccountManager(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){
            error.setText("Cannot Debit Loan Account");
        } else {
            root = FXMLLoader.load(getClass().getResource("ManagerDebitAccount.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Debit Account Button Clicked");
        }
    }
    /**
     * This method is used to switch to the AccountBalanceManager scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void AccountBalanceManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountBalanceManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account balance Button Clicked");
    }

    /**
     * This method is used to switch to the AccountStatusDisplayedManager scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void AccountStatusDisplayedManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountStatusDisplayedManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }

    /**
     * This method is used to switch to the ManagerSearchCustomerID scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void ManagerSearchCustomerID(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Manager Options Button Clicked");
    }

    /**
     * This method is used to switch to the ManagerSelectAccount scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void ManagerSelectAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back to Manager Selecet Account");
    }

    @FXML
    void setInterestRate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SetInterest.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to switch to the LinkAccount scene
     * @param event is the event that triggers the method
     * @throws Exception is thrown if the scene is not found
     */

    @FXML
    void LinkAccountButton(ActionEvent event) throws Exception {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")){
            error.setText("Can Only Link Checking");
        } else {
            root = FXMLLoader.load(getClass().getResource("ManagerLinkAccount.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Teller Button Clicked");
        }
    }

    /**
     * This method is used to switch to the processCheckManager scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void processCheckManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("processCheckManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to switch to the AccountInfo scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void showAccountInfoManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerAccountInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to switch to the NewCD scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void newCD(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")){
            error.setText("Account does not support CD");
        } else {
            root = FXMLLoader.load(getClass().getResource("NewCD.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("New CD Button Clicked");
        }
    }

    /**
     * This method is used to switch to the CustomerReviewAccountStatus scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

    @FXML
    void showCustAccountInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerReviewAccountStatus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to switch to the Transfer scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */

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

    /**
     * This method is used to switch to the Stop Payment scene
     * @param event is the event that triggers the method
     * @throws IOException is thrown if the scene is not found
     */
    @FXML
    void StopPayment(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")){
            error.setText("Stop Payment Only For Checking");
        } else {
            root = FXMLLoader.load(getClass().getResource("ManagerStopPayment.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
