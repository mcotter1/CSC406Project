import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;

    /**
     * Controller class responsible for handling customer selections for different banking transactions.
     * This class enables customers to navigate to various transaction interfaces depending on their account type.
     */
public class CustomerSelection implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Label errorLbl;

    @FXML
    private Label AccountInfo;


    /**
     * Handles navigation to the credit card payment interface if the selected account is a credit card.
     * 
     * @param event The ActionEvent triggered by the credit card payment button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void CustomerPayCreditCard(ActionEvent event) throws IOException {
        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("Credit Card")){
            root = FXMLLoader.load(getClass().getResource("CustomerPayCreditCard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Pay with Credit Card Button Clicked");
        }else{
        errorLbl.setText("Must have a Credit Card Account Selected");
        }
    }


     /**
     * Handles navigation to the ATM card review interface.
     * 
     * @param event The ActionEvent triggered by the ATM card button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void ATMCardBtnClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerReviewAccountStatus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }


    /**
     * Handles navigation based on the account type when the credit card button is clicked.
     * This method checks if the current account type is one of the specified types suitable for ATM operations.
     * If the account type matches ("simple savings", "TMB", or "Gold"), it navigates to the ATM window interface.
     * Otherwise, it displays an error message indicating that a savings account must be selected.
     *
     * @param event The ActionEvent triggered by clicking the credit card button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void creditCardBtnClicked(ActionEvent event) throws IOException {
        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings") || App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("TMB") || App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("Gold")){
        root = FXMLLoader.load(getClass().getResource("CustomerATMWindow.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }else{
        errorLbl.setText("Must be an Savings Account Selected");
    }
    }


    /**
     * Handles navigation to the check processing interface if the selected account type is suitable for checks.
     * 
     * @param event The ActionEvent triggered by the process check button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void processCheckCustomer(ActionEvent event) throws IOException {
        if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")){
            errorLbl.setText("Check only for checking account");
        } else {
            root = FXMLLoader.load(getClass().getResource("CustomerProcessCheck.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Account Status Button Clicked");
        }
    }


    /**
     * Handles navigation to the customer transaction interface.
     * 
     * @param event The ActionEvent triggered by the transaction button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void showTransactionBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerTransaction.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Customer Transaction Clicked");
    }


    /**
     * Handles navigation to the paper deposit interface.
     * 
     * @param event The ActionEvent triggered by the paper deposit button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void PaperDeposit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerPaperDeposit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Customer Select Payment Clicked");
    }


    /**
     * Handles navigation back to the main account selection interface.
     * 
     * @param event The ActionEvent triggered by the back button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }

    /**
     * Handles navigation to the message display interface.
     * 
     * @param event The ActionEvent triggered by the display messages button.
     * @throws IOException If there is an error loading the scene.
     */
    @FXML
    void DisplayMessages(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CustomerDisplayMessage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }
    
/**
     * Initializes the controller class by setting up account-related messages and checks for CD maturity.
     * 
     * @param location The location used to resolve relative paths for the root object, or null if not specified.
     * @param resources The resources used to localize the root object, or null if not specified.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
       AccountInfo.setText( "Welcome " + App.Customers.get(App.currentcustomerindex).getFirstname().toString() + " " + App.Customers.get(App.currentcustomerindex).getLastname().toString() + " Please make a Selection!");
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

}
