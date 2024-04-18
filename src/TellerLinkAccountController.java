import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TellerLinkAccountController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label error;
    @FXML
    private Label success;
    @FXML
    private Label accountlabel;
    @FXML
    private ComboBox<Account> accountbox;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Account> accounts = FXCollections.observableArrayList(App.Customers.get(App.currentcustomerindex).getAccounts());
        accountbox.setItems(accounts);
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }
    @FXML
    void GetSelection(ActionEvent event){
        App.currentaccountindex = accountbox.getSelectionModel().getSelectedIndex();
    }
    @FXML
    void BackToTellerOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
