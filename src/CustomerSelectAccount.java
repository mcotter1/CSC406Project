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
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;


        /**
         * Controller class that manages account selection for customers within a banking application.
         * This class allows customers to select an account for further actions such as deposits, withdrawals, or payments.
         */
public class CustomerSelectAccount implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private ComboBox<Account> tellercombobox;

    /**
     * Initializes the windows combobox to select accounts
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Account> accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
        tellercombobox.setItems(accounts);
    }

    /**
     * Moves to teller options if the account selection combobox is not null
     * @param event
     * @throws IOException
     */
    @FXML
    void TellerOptions(ActionEvent event) throws IOException {
        if(tellercombobox.getValue()==null){
            System.out.println("Please select an account");
        } else {
            root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("ID was successful and Options are displayed");
        }
    }

    /**
     * gets the current account index from combobox
     * @param event
     */
    @FXML
    void GetSelection(ActionEvent event){
        App.currentaccountindex = tellercombobox.getSelectionModel().getSelectedIndex();
    }
    @FXML
    void TellerBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Teller Button Clicked");
    }
    @FXML
    void CreateAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerCreateAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Teller Button Clicked");
    }
    @FXML
    void ReviewAccounts(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerReviewAccountStatus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }
}
