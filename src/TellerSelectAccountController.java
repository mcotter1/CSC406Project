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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import java.net.URL;

public class TellerSelectAccountController implements Initializable {
    @FXML
    private ComboBox<Account> tellercombobox;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Account> accounts = FXCollections.observableArrayList(App.Customers.get(TellerSearchController.currentcustomerindex).getAccounts());
        tellercombobox.setItems(accounts);
        System.out.println(tellercombobox.getItems());
    }
}
