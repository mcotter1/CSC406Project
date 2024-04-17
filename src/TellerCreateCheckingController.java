import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TellerCreateCheckingController {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private TextField goldortmb;
    @FXML
    private TextField initialdeposit;
    @FXML
    private RadioButton atmno;
    @FXML
    private RadioButton atmyes;

}
