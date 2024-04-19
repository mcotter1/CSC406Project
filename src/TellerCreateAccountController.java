import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TellerCreateAccountController {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    /**
     * This function goes back to teller select account screen
     * @param event
     * @throws IOException
     */
    @FXML
    public void BackToTellerSelect(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Returning to account selection");
    }

    /**
     * This function goes to teller create checking screen
     * @param event
     * @throws IOException
     */
    @FXML
    void CreateCheckingAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerCreateChecking.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Create Checking Account Teller Button Clicked");
    }

    /**
     * This function goes to teller create savings screen
     * @param event
     * @throws IOException
     */
    @FXML
    void CreateSavingsAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerCreateSavings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Create Savings Account Teller Button Clicked");
    }
}
