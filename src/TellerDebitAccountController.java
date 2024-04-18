import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TellerDebitAccountController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label accountlabel;
    @FXML
    private TextField debitamount;
    @FXML
    private Label error;
    @FXML
    private Label success;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }

    /**
     * This teller function can withdraw funds from a Savings or Checking account.
     * @param event the action event for clicking debit button
     */
    @FXML
    void Debit(ActionEvent event){
        Account workaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        if(debitamount.getText().isBlank()){
            error.setText("Please enter an amount to transfer");
            success.setText("");
            return;
        }
        if(debitamount != null && !debitamount.getText().matches(".*[a-zA-Z]+.*")&&!debitamount.getText().isBlank()){
            double workamount = Double.parseDouble(debitamount.getText());
            if(workamount<=0){
                error.setText("Enter valid amount");
                success.setText("");
                return;
            }
            if(workaccount.getAccounttype().equalsIgnoreCase("cd")||workaccount.getAccounttype().equalsIgnoreCase("simple savings")){
                Savings worksavings = (Savings) workaccount;
                if(worksavings.getBalance()<workamount){
                    error.setText("Insufficient Funds");
                    success.setText("");
                    return;
                }
                if(worksavings.getAccounttype().equalsIgnoreCase("cd")){
                    if(worksavings.getCDdue().isBefore(LocalDate.now())){
                        Transaction cdtransaction = new Transaction("Withdrawal","CD",workamount,LocalDate.now(),workaccount.getBalance()-workamount-2);
                        worksavings.setBalance(worksavings.getBalance()-workamount-2);
                        worksavings.AddTransaction(cdtransaction);
                        error.setText("Penalty for Withdrawal before CD due date");
                        success.setText("Withdrawal Successful");
                        accountlabel.setText(worksavings.toString());
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,worksavings);
                    } else {
                        Transaction simpletransaction = new Transaction("Withdrawal","Simple Savings",workamount,LocalDate.now(),workaccount.getBalance()-workamount);
                        worksavings.setBalance(worksavings.getBalance()-workamount);
                        worksavings.AddTransaction(simpletransaction);
                        error.setText("Penalty for Withdrawal before CD due date");
                        success.setText("Withdrawal Successful");
                        accountlabel.setText(worksavings.toString());
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,worksavings);
                    }
                } else {
                    Transaction simpletransaction = new Transaction("Withdrawal",worksavings.getAccounttype(),-workamount, LocalDate.now(),worksavings.getBalance()-workamount);
                    worksavings.setBalance(worksavings.getBalance()-workamount);
                    worksavings.AddTransaction(simpletransaction);
                    error.setText("");
                    success.setText("Withdrawal Successful");
                    accountlabel.setText(worksavings.toString());
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,worksavings);
                }
            } else {
                Checking workchecking = (Checking) workaccount;
                if(workchecking.getBalance()<workamount){
                    error.setText("Insufficient Funds");
                    success.setText("");
                    return;
                }
                Transaction checkingtransaction = new Transaction("Withdrawal",workchecking.getAccounttype(),-workamount,LocalDate.now(),workchecking.getBalance()-workamount);
                workchecking.setBalance(workchecking.getBalance()-workamount);
                if(workchecking.getAccounttype().equalsIgnoreCase("tmb")){
                    workchecking.setBalance(workchecking.getBalance()-1.25);
                    checkingtransaction.setNewbalance(checkingtransaction.getNewbalance()-1.25);
                }
                if(workchecking.getAccounttype().equalsIgnoreCase("gold")&&workchecking.getBalance()<5000){
                    workchecking.setAccounttype("TMB");
                    workchecking.setBalance(workchecking.getBalance()-1.25);
                    checkingtransaction.setNewbalance(checkingtransaction.getNewbalance()-1.25);
                }
                workchecking.AddTransaction(checkingtransaction);
                error.setText("");
                success.setText("Withdrawal Successful");
                accountlabel.setText(workchecking.toString());
                App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
            }
        } else {
            error.setText("Please enter a valid amount");
            success.setText("");
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
