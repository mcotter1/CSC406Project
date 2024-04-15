import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;

public class CustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Label error;

    @FXML
    private PasswordField ssnVerifyText;


    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    public static int currentcustomerindex;
    private boolean found;

    //@FXML
    //private Button verifyBtn;


    @FXML
    void SSNverifyBtnClicked(ActionEvent event) throws IOException {
        if (ssnVerifyText != null && !ssnVerifyText.getText().matches(".*[a-zA-Z]+.*")&&!ssnVerifyText.getText().isBlank()&&ssnVerifyText.getText().length()==9) {
            for (int i = 0; i < App.Customers.size(); i++)
                if (App.Customers.get(i).getSSN() == Integer.parseInt(ssnVerifyText.getText())) {
                    App.currentcustomerindex = i;
                    root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("SSN is corrected and Scene is being Switched");
                    found = true;
                    break;
                }
            if(!found) {
                error.setText("Could not find account");
                System.out.println("Could not find account");
            }
        }
        if (ssnVerifyText.getText().matches(".*[a-zA-Z]+.*")||(ssnVerifyText.getText().length()>9 || ssnVerifyText.getText().length()<9)||ssnVerifyText.getText().isBlank()){
            error.setText("Please enter a valid SSN");
            System.out.println("Please enter a valid SSN");
        }
    }

    @FXML
    void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }

    @FXML
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Customer.fxml'.";

    }

}
