import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class establishCDContoller implements Initializable{
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label accountlabel;
    @FXML
    private DatePicker datedueDatePicker;
    @FXML
    private TextField initialdeposit;
    @FXML
    private TextField intrate;
    @FXML
    private Label error;
    @FXML
    private Label success;
    @FXML
    private Label customername;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }

    /**
     * This function creates a CD account
     * @param event the ActionEvent from the window
     */
    @FXML
    void Create(ActionEvent event){
        if(initialdeposit != null && !initialdeposit.getText().matches(".*[a-zA-Z]+.*")&&!initialdeposit.getText().isBlank()) {
            //Account workaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
            double depositamount = Double.parseDouble(initialdeposit.getText());
            if(depositamount<=0){
                error.setText("Must Be Greater Than Zero");
                success.setText("");
                return;
            }
            if(depositamount>App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getBalance()){
                error.setText("Insufficient Funds");
                success.setText("");
                return;
            }
            if(datedueDatePicker.getValue() == null){
                error.setText("Enter Date Due");
                success.setText("");
                return;
            }
            int ssn = App.Customers.get(App.currentcustomerindex).getSSN();
            //if the account is not a savings account 
            if(!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("gold")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("tmb")&&!App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype().equalsIgnoreCase("simple savings")){
                error.setText("Cannot Establish CD from a Loan Account");
                success.setText("");
                return;
            }
            if(depositamount<=0){
                error.setText("Must Be Greater Than Zero");
                success.setText("");
                return;
            }
            if(intrate.getText().isBlank()){
                error.setText("Enter Interest Rate");
                success.setText("");
                return;
            }
            // if(datedueDatePicker.getValue().isBefore(LocalDate.now())){
            //     error.setText("Date Due Must Be After Today");
            //     success.setText("");
            //     return;
            // }
            if(Double.parseDouble(intrate.getText())<=0){
                error.setText("Interest Rate Must Be Greater Than Zero");
                success.setText("");
                return;
            }
            App.savingsidcounter++;
            LocalDate DateDue = datedueDatePicker.getValue();
            double interestrate = (Double.parseDouble(intrate.getText()) / 100);
            Savings CD = new Savings(String.format("SA%d",App.savingsidcounter),ssn,depositamount,interestrate, LocalDate.now(),null,DateDue,false);
            App.Customers.get(App.currentcustomerindex).AddAccount(CD);
            success.setText("Account Created");
            error.setText("");
            //now take the money from the savings account
            App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).setBalance(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getBalance()-depositamount);
            //update the account label
            accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
            //add the transaction to the account
            Transaction newtransaction = new Transaction("Establish CD",App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getAccounttype(),depositamount,LocalDate.now(),App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getBalance());
            App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).AddTransaction(newtransaction);
        } else {
            error.setText("Enter valid initial deposit");
            success.setText("");
        }
    }

    /**
     * This function switches to the main scene
     * @param event the action event for clicking the back button
     * @throws IOException if the scene is not found
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
