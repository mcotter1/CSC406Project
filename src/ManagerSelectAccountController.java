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
import java.util.ResourceBundle;
import java.net.URL;

public class ManagerSelectAccountController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private ComboBox<Account> managercombobox;
    @FXML
    private Label customername;

    /**
     * Initializes the windows combobox to select accounts
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Account> accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
        managercombobox.setItems(accounts);
        customername.setText(String.format("Customer: %s %s ",App.Customers.get(App.currentcustomerindex).getFirstname(),App.Customers.get(App.currentcustomerindex).getLastname()));
    }

    /**
     * Moves to manager options if the account selection combobox is not null
     * @param event
     * @throws IOException
     */
    @FXML
    void managerOptions(ActionEvent event) throws IOException {
        if(managercombobox.getValue()==null){
            System.out.println("Please select an account");
        } else {
            root = FXMLLoader.load(getClass().getResource("ManagerOptions.fxml"));
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
        App.currentaccountindex = managercombobox.getSelectionModel().getSelectedIndex();
    }

    /**
     * returns to the manager search ssn screen
     * @param event
     * @throws Exception
     */
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
    void CreateAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCreateAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Create Account Button Clicked");
    }
}
