import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ManagerCreateAccountController {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    public void BackToManagerSelect(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Returning to account selection");
    }
    @FXML
    void CreateCheckingAccountManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCreateChecking.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Create Checking Account Teller Button Clicked");
    }
    @FXML
    void CreateSavingsAccountManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCreateSavings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Create Savings Account Teller Button Clicked");
    }
}

