import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TellerController {
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
    @FXML
    private ListView<Account> tellerlistview;
    @FXML
    private TextField ssn;
    @FXML
    private Label error;
    private boolean found;
    @FXML
    void CheckID(ActionEvent event) throws IOException {
        if (ssn != null && !ssn.getText().matches(".*[a-zA-Z]+.*")&&!ssn.getText().isBlank()&&ssn.getText().length()==9) {
            for (int i = 0; i < App.Customers.size(); i++)
                if (App.Customers.get(i).getSSN() == Integer.parseInt(ssn.getText())) {
                    currentcustomerindex = i;
                    root = FXMLLoader.load(getClass().getResource("TellerSelectAccount.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("switching scenes");
                    found = true;
                    break;
                }
            if(!found) {
                error.setText("Could not find account");
                System.out.println("Could not find account");
            }
        }
        if (ssn.getText().matches(".*[a-zA-Z]+.*")||(ssn.getText().length()>9 || ssn.getText().length()<9)||ssn.getText().isBlank()){
            error.setText("Please enter a valid SSN");
            System.out.println("Please enter a valid SSN");
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
