import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.LocalDate;


public class CustomerProcessCheck implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField checknumbertxt;

    @FXML
    private Button Depositbtn;

    @FXML
    private Button backbtn;

@FXML
    void Depositbtn() {
        


    }

    @FXML
    void backbtn(ActionEvent event) throws IOException  {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {



    }
}
