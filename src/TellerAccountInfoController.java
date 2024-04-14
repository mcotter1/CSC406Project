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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TellerAccountInfoController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private TableView<Checking> tablebox;
    @FXML
    private TableView<Transaction> transactionbox;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")){
            Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            ObservableList<Checking> checkinglist = FXCollections.observableArrayList();
            checkinglist.addAll(workchecking);
            tablebox.setItems(checkinglist);
            TableColumn<Checking,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Checking,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            TableColumn<Checking,LocalDate> column3= new TableColumn<>("Date Opened");
            column3.setCellValueFactory(new PropertyValueFactory<>("dateopened"));
            TableColumn<Checking,String> column4 = new TableColumn<>("Savings ID");
            column4.setCellValueFactory(new PropertyValueFactory<>("savingsaccountid"));
            tablebox.getColumns().setAll(column1,column2,column3,column4);
            ArrayList<Transaction> worktransactions = workchecking.getTransactions();
            ObservableList<Transaction> transactionlist = FXCollections.observableArrayList(worktransactions);
            transactionbox.setItems(transactionlist);
            TableColumn<Transaction,String> tcolumn1 = new TableColumn<>("Type");
            tcolumn1.setCellValueFactory(new PropertyValueFactory<>("transactiontype"));
            TableColumn<Transaction,String> tcolumn2 = new TableColumn<>("Account Type");
            tcolumn2.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Transaction,Double> tcolumn3 = new TableColumn<>("Amount");
            tcolumn3.setCellValueFactory(new PropertyValueFactory<>("amount"));
            TableColumn<Transaction,LocalDate> tcolumn4 = new TableColumn<>("Date");
            tcolumn4.setCellValueFactory(new PropertyValueFactory<>("dateoccurred"));
            transactionbox.getColumns().setAll(tcolumn1,tcolumn2,tcolumn3,tcolumn4);
        } else if (App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")||App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("cd")){

        }
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
