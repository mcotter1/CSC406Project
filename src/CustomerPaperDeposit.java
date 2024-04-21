import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CustomerPaperDeposit implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    public static int currentcustomerindex;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AccountTypelbl;

    @FXML
    private TextField Amounttxt;

    @FXML
    private Button Backbtn;

    @FXML
    private Button Depositbtn;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        //We want to Initialize the Label for the specific Account and possibly balance
        AccountTypelbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts()+"");

    }
}
