import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TellerCreateCheckingController implements Initializable {
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
    @FXML
    private Label error;
    @FXML
    private Label success;
    @FXML
    private Label customername;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup atm = new ToggleGroup();
        atmno.setToggleGroup(atm);
        atmyes.setToggleGroup(atm);
        atmno.setSelected(true);
        customername.setText(String.format("Customer: %s %s ",App.Customers.get(App.currentcustomerindex).getFirstname(),App.Customers.get(App.currentcustomerindex).getLastname()));
    }
    @FXML
    void Create(ActionEvent event){
        if(!goldortmb.getText().equalsIgnoreCase("tmb")&&!goldortmb.getText().equalsIgnoreCase("gold")){
            error.setText("Must enter TMB or Gold");
            success.setText("");
            return;
        }
        if(initialdeposit != null && !initialdeposit.getText().matches(".*[a-zA-Z]+.*")&&!initialdeposit.getText().isBlank()){
            double depositamount = Double.parseDouble(initialdeposit.getText());
            boolean yesornoatm;
            int ssn = App.Customers.get(App.currentcustomerindex).getSSN();
            if(goldortmb.getText().equalsIgnoreCase("gold")){
                if(depositamount<5000){
                    error.setText("Gold Minimum Balance Is $5000");
                    success.setText("");
                    return;
                }
                if(atmno.isSelected()){
                    yesornoatm = false;
                } else {
                    yesornoatm = true;
                }
                Checking newchecking = new Checking(ssn,"Gold",depositamount,null,0, LocalDate.now(),yesornoatm);
                App.Customers.get(App.currentcustomerindex).AddAccount(newchecking);
                error.setText("");
                success.setText("New Account Added");
            } else {
                if(depositamount<=0){
                    error.setText("Initial Deposit Must Be More Than 0");
                    success.setText("");
                    return;
                }
                if(atmno.isSelected()){
                    yesornoatm = false;
                } else {
                    yesornoatm = true;
                }
                Checking newchecking = new Checking(ssn,"TMB",depositamount,null,0,LocalDate.now(),yesornoatm);
                App.Customers.get(App.currentcustomerindex).AddAccount(newchecking);
                error.setText("");
                success.setText("New Account Added");
            }
        } else {
            error.setText("Enter valid initial deposit");
            success.setText("");
        }
    }
    @FXML
    void BackToTellerSelectAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
