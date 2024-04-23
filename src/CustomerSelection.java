import java.net.URL;
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

    @FXML
    void ATMCardBtnClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerReviewAccountStatus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }

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

    @FXML
    void showTransactionBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerTransaction.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Customer Transaction Clicked");
    }

    @FXML
    void PaperDeposit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerPaperDeposit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Customer Select Payment Clicked");
    }
    //Paper Deposit for all Accounts, Just cash Deposit

    @FXML
    void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }
    @FXML
    void DisplayMessages(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CustomerDisplayMessage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
       AccountInfo.setText( "Welcome " + App.Customers.get(App.currentcustomerindex).getFirstname().toString() + " " + App.Customers.get(App.currentcustomerindex).getLastname().toString() + " Please make a Selection!");
    }

}
