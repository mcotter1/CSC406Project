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

public class CustomerAccountInfoController {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Checking> tableAccountInfo;
    @FXML
    private TableView<Savings> tableAccountInfo2;

    @FXML
    void SSNverifyBtnClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
            Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Checking> checkinglist = FXCollections.observableArrayList();
            checkinglist.addAll(workchecking);
            TableColumn<Checking,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Checking,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
            column2.setCellFactory(c -> new TableCell<Checking, Double>() {
                @Override
                protected void updateItem(Double balance,
                                          boolean empty) {

                    super.updateItem(balance, empty);

                    if (empty || balance == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(balanceFormat.format(balance));
                    }
                }
            });
    }

}
