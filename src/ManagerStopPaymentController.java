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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerStopPaymentController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label accountlabel;
    @FXML
    private TextField checknumber;
    @FXML
    private Label error;
    @FXML
    private Label success;
    @FXML
    private TableView<Check> checkstable;
    @FXML
    private Label nochecks;

    /**
     * This function initializes a check table for this screen to view customer's checks for this account
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        nochecks.setText("No Checks");
        nochecks.setVisible(false);
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        ArrayList<Check> usedchecks = workchecking.getUsedchecks();
        ObservableList<Check> checkObservableList = FXCollections.observableArrayList(usedchecks);
        checkstable.setItems(checkObservableList);
        TableColumn<Check,Integer> column1 = new TableColumn<>("Check Number");
        column1.setCellValueFactory(new PropertyValueFactory<>("checknumber"));
        TableColumn<Check,Double> column2 = new TableColumn<>("Amount");
        column2.setCellValueFactory(new PropertyValueFactory<>("paymentamount"));
        NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
        column2.setCellFactory(c -> new TableCell<Check, Double>() {
            @Override
            protected void updateItem(Double paymentamount,
                                      boolean empty) {

                super.updateItem(paymentamount, empty);

                if (empty || paymentamount == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(balanceFormat.format(paymentamount));
                }
            }
        });
        TableColumn<Check,Boolean> column3 = new TableColumn<>("Paid Out");
        column3.setCellValueFactory(new PropertyValueFactory<>("paid"));
        checkstable.getColumns().setAll(column1,column2,column3);
        if(usedchecks.isEmpty()){
            checkstable.setVisible(false);
            nochecks.setVisible(true);
        }
    }

    /**
     * This function takes input from the screen for check number and puts a stop order on the check
     * that the input number matches
     * @param event
     */
    @FXML
    void StopPayment(ActionEvent event){
        boolean found = false;
        Checking workchecking = (Checking) App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        if(checknumber != null && !checknumber.getText().matches(".*[a-zA-Z]+.*")&&!checknumber.getText().isBlank()&&Integer.parseInt(checknumber.getText())>0){
            int checkint = Integer.parseInt(checknumber.getText());
            for(int i=0;i<workchecking.getUsedchecks().size();i++){
                if(checkint == workchecking.getUsedchecks().get(i).getChecknumber() && workchecking.getUsedchecks().get(i).isPaid()){
                    workchecking.getUsedchecks().get(i).setPaid(false);
                    double amount = workchecking.getUsedchecks().get(i).getPaymentamount();
                    workchecking.setBalance(workchecking.getBalance()+amount-35);
                    success.setText("Check Stopped");
                    error.setText("");
                    found = true;
                    accountlabel.setText(workchecking.toString());
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                    break;
                }
            }
            if(!found){
                error.setText("Could Not Find Check");
                success.setText("");
            }
        } else {
            error.setText("Please Enter Valid Check Number");
            success.setText("");
        }
    }

    /**
     * This function returns to Manager options
     * @param event
     * @throws IOException
     */
    @FXML
    void BackToManagerOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
