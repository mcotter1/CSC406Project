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

public class TellerCreditAccountController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private Label accountlabel;
    @FXML
    private TextField creditamount;
    @FXML
    private Label error;
    @FXML
    private Label success;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }

    /**
     * This Teller function can make payments to loan accounts or deposits to checking and savings accounts when the
     * credit button is clicked in the teller credit menu
     * @param event the click button event
     */
    @FXML
    void Credit(ActionEvent event){
        Account workaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        if(creditamount.getText().isBlank()){
            error.setText("Please enter an amount to transfer");
            success.setText("");
            return;
        }
        if(creditamount != null && !creditamount.getText().matches(".*[a-zA-Z]+.*")&&!creditamount.getText().isBlank()){
            if(workaccount.getAccounttype().equalsIgnoreCase("long term loan")||workaccount.getAccounttype().equalsIgnoreCase("short term loan")||workaccount.getAccounttype().equalsIgnoreCase("Credit Card")){
                Double workamount = Double.parseDouble(creditamount.getText());
                Loan loanwork = (Loan) workaccount;
                Transaction loantransaction = new Transaction("Payment",workaccount.getAccounttype(),-workamount, LocalDate.now(),workaccount.getBalance()-workamount);
                if(workamount>loanwork.getBalance()){
                    error.setText("Payment amount more than balance");
                    success.setText("");
                    return;
                }
                if(workamount>=loanwork.getPaymentamountdue()){
                    loanwork.setMissedpayment(false);
                    loanwork.setPaymentamountdue(0);
                }
                if(workamount<loanwork.getPaymentamountdue()){
                    loanwork.setPaymentamountdue(loanwork.getPaymentamountdue()-workamount);
                }
                loanwork.setBalance(loanwork.getBalance()-workamount);
                loanwork.AddTransaction(loantransaction);
                loanwork.setLastpaymentdate(LocalDate.now());
                accountlabel.setText(loanwork.toString());
                App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,loanwork);
                error.setText("");
                success.setText("Payment Successful");
            } else if(workaccount.getAccounttype().equalsIgnoreCase("simple savings")||workaccount.getAccounttype().equalsIgnoreCase("cd")){
                double workamount = Double.parseDouble(creditamount.getText());
                Savings worksavings = (Savings) workaccount;
                Transaction savingstransaction = new Transaction("Deposit",worksavings.getAccounttype(),workamount,LocalDate.now(),worksavings.getBalance()+workamount);
                worksavings.setBalance(worksavings.getBalance()+workamount);
                worksavings.AddTransaction(savingstransaction);
                error.setText("");
                success.setText("Deposit Successful");
                accountlabel.setText(worksavings.toString());
                App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,worksavings);
            } else {
                double workamount = Double.parseDouble(creditamount.getText());
                Checking workchecking = (Checking) workaccount;
                Transaction checkingtransaction =new Transaction("Deposit",workchecking.getAccounttype(),workamount,LocalDate.now(),workchecking.getBalance()+workamount);
                workchecking.setBalance(workchecking.getBalance()+workamount);
                workchecking.AddTransaction(checkingtransaction);
                error.setText("");
                success.setText("Deposit Successful");
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
