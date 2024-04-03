import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

public class MainSceneController {
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
    void ATMCardBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("VerifyPin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Card Button Clicked");
    }

    @FXML
    void viewCustomerData(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("ManagerDataView.fxml"));
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
    void DisplayRecentDebits(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RecentDebitsTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Display Recent Debits Button Clicked");
    }

    @FXML
    void CreditAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreditAccountTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Account Button Clicked");
    }

    @FXML
    void DebitAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DebitAccountTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Account Button Clicked");
    }

    @FXML
    void Transfer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WithdrawFromTo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Transfer Money Button Clicked");
    }

    @FXML
    void AccountBalance(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountBalanceTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account balance Button Clicked");
    }

    @FXML
    void AccountStatusDisplayed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountStatusDisplayed.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }

}
