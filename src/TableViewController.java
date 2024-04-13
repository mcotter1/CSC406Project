import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.beans.binding.Bindings;



public class TableViewController implements Initializable {

    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableView<Account> accountTableView;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> stateColumn;

    @FXML
    private TableColumn<Customer, String> cityColumn;

    @FXML
    private TableColumn<Customer, Integer> zipCodeColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    // This method is used to switch back to the main scene
    // used for the back button in Teller, Manager, and Customer scenes
    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }

    public void switchToTellerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Teller Button Clicked");
    }

    public void switchToManagerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Manager Button Clicked");
    }

    final ObservableList<Customer> data = FXCollections.observableArrayList(
        new Customer(23, "4508 NE 105th St", "Kansas City", "Missouri", 64156, "Gage", "Ross"),
        new Customer(24, "7884 Wild Rose Street", "St Joseph", "Missouri", 69256, "Dakotah", "Profit"),
        new Customer(25, "47 Myers Street", "St Joseph", "Missouri", 66756, "Matthew", "Cotter"),
        new Customer(26, "3 Randall Mill Street", "St Joseph", "Missouri", 64156, "Lukas", "Siemers"),
        new Customer(27, "842 Amerige Drive", "St Joseph", "Missouri", 66156, "Tommy", "Hutton"),
        new Customer(28, "Pembroke Pines", "St Joseph", "Missouri", 64236, "Wyatt", "Wuerfele")
    );


    @FXML
    private void loadAllCustomers()
    {
        System.out.println("called method loadAllCustomers");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'tableview.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'tableview.fxml'.";
        configureTable();
    }

    private void configureTable() {
        idColumn.setCellValueFactory(
            cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getSSN()));
        zipCodeColumn.setCellValueFactory(
            cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getZipcode()));
        firstNameColumn.setCellValueFactory(
            cellData -> Bindings.createStringBinding(() -> cellData.getValue().getFirstname()));
        lastNameColumn.setCellValueFactory(
            cellData -> Bindings.createStringBinding(() -> cellData.getValue().getLastname()));
        stateColumn.setCellValueFactory(
                cellData -> Bindings.createStringBinding(() -> cellData.getValue().getState()));
        cityColumn.setCellValueFactory(
                cellData -> Bindings.createStringBinding(() -> cellData.getValue().getCity()));
        addressColumn.setCellValueFactory(
                cellData -> Bindings.createStringBinding(() -> cellData.getValue().getAddress()));
        tableView.setItems(data);
        assert tableView.getItems() == data;
    }
}