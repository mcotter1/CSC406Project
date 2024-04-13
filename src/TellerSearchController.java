import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TellerSearchController  {
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
    public static int currentcustomerindex;
    private int currentaccountindex;
    @FXML
    private ListView<Account> tellerlistview;
    @FXML
    private TextField ssn;
    @FXML
    void CheckID(ActionEvent event) throws IOException {
        if (ssn != null) {
            for (int i = 0; i < App.Customers.size(); i++)
                if (App.Customers.get(i).getSSN() == Integer.parseInt(ssn.getText())) {
                    currentcustomerindex = i;
                    root = FXMLLoader.load(getClass().getResource("TellerSelectAccount.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("switching scenes");
                    break;
                } else {
                    System.out.println("Invalid entry");
                }
            System.out.println("Could not find ID");
        }
    }
    @FXML
    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }
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
    void TellerviewCustomerData(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TellerDataView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("View Customer Data Button Clicked");
    }
}
