import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

public class MainSceneController extends App{
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
}
