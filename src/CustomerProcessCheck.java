import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.LocalDate;


public class CustomerProcessCheck implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField checknumbertxt;

    @FXML
    private Button Depositbtn;

    @FXML
    private Button backbtn;
    @FXML
    private Label error;
    @FXML
    private Label success;
    private int savingsaccountindex;
    @FXML
    private Label accountlabel;

    /**
     * This function processes a check for a customer when a valid amount is entered. If the amount is too high,
     * the account will either be overdrafted or funds will be taken from a backup savings account
     */
    @FXML
    void ProcessCheck() {
        Account workaccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        if(checknumbertxt.getText().isBlank()){
            error.setText("Please enter an amount to transfer");
            success.setText("");
            return;
        }
        if(checknumbertxt != null && !checknumbertxt.getText().matches(".*[a-zA-Z]+.*")&&!checknumbertxt.getText().isBlank()){
            double workamount = Double.parseDouble(checknumbertxt.getText());
            Savings worksavings = null;
            if(workamount<=0){
                error.setText("Enter valid amount");
                success.setText("");
                return;
            }
            Checking workchecking = (Checking) workaccount;
            if(workchecking.isBackupsavingscheck()){
                for(int i = 0;i<App.Customers.get(App.currentcustomerindex).getAccounts().size();i++){
                    if(App.Customers.get(App.currentcustomerindex).getAccounts().get(i).getAccounttype().equalsIgnoreCase("simple savings")||App.Customers.get(App.currentcustomerindex).getAccounts().get(i).getAccounttype().equalsIgnoreCase("cd")){
                        worksavings = (Savings) App.Customers.get(App.currentcustomerindex).getAccounts().get(i);
                        if (worksavings.getSavingsaccountid().equalsIgnoreCase(workchecking.getSavingsaccountid())){
                            savingsaccountindex = i;
                            break;
                        }
                    }
                }
            }
            Check check = workchecking.getNewchecks().getFirst();
            workchecking.getNewchecks().removeFirst();
            check.setPaymentamount(workamount);
            double addbalance;
            //not gold diamond
            if(!workchecking.isGolddiamondcheck()) {
                //insufficient funds and no backup savings account
                if (check.getPaymentamount() > workchecking.getBalance() && !workchecking.isBackupsavingscheck()) {
                    check.setPaid(false);
                    workchecking.setBalance(workchecking.getBalance()-25);
                    workchecking.getUsedchecks().add(check);
                    error.setText("Check not honored and overdraft of $25 taken");
                    success.setText("");
                    Transaction checkingtransaction = new Transaction("Overdraft","TMB",-25,LocalDate.now(), workchecking.getBalance());
                    workchecking.AddTransaction(checkingtransaction);
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                    accountlabel.setText(workchecking.toString());
                    //insufficient funds and backup savings account
                } else if (check.getPaymentamount() > workchecking.getBalance() && workchecking.isBackupsavingscheck()) {
                    worksavings = (Savings) App.Customers.get(App.currentcustomerindex).getAccounts().get(savingsaccountindex);
                    if(check.getPaymentamount()>(workchecking.getBalance()+worksavings.getBalance())){
                        error.setText("Amount higher than backup and checking balance");
                        success.setText("");
                        check.setPaid(false);
                        workchecking.setBalance(workchecking.getBalance()-25);
                        workchecking.getUsedchecks().add(check);
                        Transaction checkingtransaction = new Transaction("Overdraft","TMB",-25,LocalDate.now(), workchecking.getBalance());
                        workchecking.AddTransaction(checkingtransaction);
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                        accountlabel.setText(workchecking.toString());
                        return;
                    }
                    if(check.getPaymentamount()>worksavings.getBalance()){
                        Transaction savingstransaction = new Transaction("Backup",worksavings.getAccounttype(),-worksavings.getBalance(),LocalDate.now(),0);
                        worksavings.AddTransaction(savingstransaction);
                        double overbalance = check.getPaymentamount()-worksavings.getBalance();
                        worksavings.setBalance(0);
                        workchecking.setBalance(workchecking.getBalance()-overbalance-0.75);
                        Transaction checkingtransaction = new Transaction("Check",workchecking.getAccounttype(),-overbalance,LocalDate.now(),workchecking.getBalance());
                        workchecking.AddTransaction(checkingtransaction);
                        check.setPaid(true);
                        workchecking.getUsedchecks().add(check);
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(savingsaccountindex,worksavings);
                        accountlabel.setText(workchecking.toString());
                        success.setText("Backup Payment Successful");
                        error.setText("");
                        return;
                    }
                    Transaction savingstransaction = new Transaction("Backup",worksavings.getAccounttype(),-workamount,LocalDate.now(),worksavings.getBalance()-workamount);
                    worksavings.setBalance(worksavings.getBalance()-workamount);
                    worksavings.AddTransaction(savingstransaction);
                    check.setPaid(true);
                    workchecking.getUsedchecks().add(check);
                    accountlabel.setText(workchecking.toString());
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(savingsaccountindex,worksavings);
                } else if(check.getPaymentamount()<=workchecking.getBalance()) {
                    check.setPaid(true);
                    workchecking.setBalance(workchecking.getBalance()-workamount);
                    workchecking.getUsedchecks().add(check);
                    Transaction checkingtransaction = new Transaction("Check",workchecking.getAccounttype(),-workamount,LocalDate.now(),workchecking.getBalance());
                    workchecking.AddTransaction(checkingtransaction);
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                    success.setText("Check processed");
                    error.setText("");
                    accountlabel.setText(workchecking.toString());
                }
            }
            //gold diamond account
            else{
                //sufficient funds and maintaining minimum balance
                if((workchecking.getBalance()-check.getPaymentamount())>=5000.0){
                    check.setPaid(true);
                    workchecking.setBalance(workchecking.getBalance()-workamount);
                    Transaction checkingtransaction = new Transaction("Check",workchecking.getAccounttype(),-workamount,LocalDate.now(),workchecking.getBalance());
                    workchecking.getUsedchecks().add(check);
                    workchecking.AddTransaction(checkingtransaction);
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                    success.setText("Check processed");
                    error.setText("");
                    accountlabel.setText(workchecking.toString());
                }
                //sufficient funds but going below minimum balance
                else if((workchecking.getBalance()-check.getPaymentamount())<5000.0 && (workchecking.getBalance()-check.getPaymentamount())>=0) {
                    check.setPaid(true);
                    workchecking.setBalance(workchecking.getBalance()-workamount);
                    Transaction checkingtransaction = new Transaction("Check",workchecking.getAccounttype(),-workamount,LocalDate.now(),workchecking.getBalance());
                    workchecking.getUsedchecks().add(check);
                    workchecking.setAccounttype("TMB");
                    workchecking.setBackupsavingscheck(false);
                    workchecking.AddTransaction(checkingtransaction);
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                    success.setText("Check processed");
                    error.setText("");
                    accountlabel.setText(workchecking.toString());
                } else if (check.getPaymentamount() > workchecking.getBalance() && workchecking.isBackupsavingscheck()) {
                    worksavings = (Savings) App.Customers.get(App.currentcustomerindex).getAccounts().get(savingsaccountindex);
                    if(check.getPaymentamount()>(workchecking.getBalance()+worksavings.getBalance())){
                        error.setText("Amount higher than backup and checking balance");
                        success.setText("");
                        check.setPaid(false);
                        workchecking.setBalance(workchecking.getBalance()-25);
                        workchecking.getUsedchecks().add(check);
                        Transaction checkingtransaction = new Transaction("Overdraft",workchecking.getAccounttype(),-25,LocalDate.now(), workchecking.getBalance());
                        workchecking.AddTransaction(checkingtransaction);
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                        accountlabel.setText(workchecking.toString());
                        return;
                    }
                    if(check.getPaymentamount()>worksavings.getBalance()){
                        Transaction savingstransaction = new Transaction("Backup",worksavings.getAccounttype(),-worksavings.getBalance(),LocalDate.now(),0);
                        worksavings.AddTransaction(savingstransaction);
                        double overbalance = check.getPaymentamount()-worksavings.getBalance();
                        worksavings.setBalance(0);
                        workchecking.setBalance(workchecking.getBalance()-overbalance);
                        if(workchecking.getBalance()<5000){
                            workchecking.setAccounttype("TMB");
                        }
                        Transaction checkingtransaction = new Transaction("Check",workchecking.getAccounttype(),-overbalance,LocalDate.now(),workchecking.getBalance());
                        workchecking.AddTransaction(checkingtransaction);
                        check.setPaid(true);
                        workchecking.getUsedchecks().add(check);
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                        App.Customers.get(App.currentcustomerindex).getAccounts().set(savingsaccountindex,worksavings);
                        accountlabel.setText(workchecking.toString());
                        success.setText("Backup Payment Successful");
                        error.setText("");
                        return;
                    }
                    Transaction savingstransaction = new Transaction("Backup",worksavings.getAccounttype(),-workamount,LocalDate.now(),worksavings.getBalance()-workamount);
                    worksavings.setBalance(worksavings.getBalance()-workamount);
                    worksavings.AddTransaction(savingstransaction);
                    check.setPaid(true);
                    workchecking.getUsedchecks().add(check);
                    accountlabel.setText(workchecking.toString());
                    success.setText("Backup Payment Successful");
                    error.setText("");
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(savingsaccountindex,worksavings);
                }
                else {
                    check.setPaid(false);
                    workchecking.setBalance(workchecking.getBalance()-25);
                    workchecking.getUsedchecks().add(check);
                    Transaction checkingtransaction = new Transaction("Overdraft",workchecking.getAccounttype(),-25,LocalDate.now(),workchecking.getBalance());
                    workchecking.AddTransaction(checkingtransaction);
                    error.setText("Check not honored and overdraft of $25 taken");
                    success.setText("");
                    accountlabel.setText(workchecking.toString());
                    App.Customers.get(App.currentcustomerindex).getAccounts().set(App.currentaccountindex,workchecking);
                }
            }
        } else {
            error.setText("Please enter a valid amount");
            success.setText("");
        }
    }

    /**
     * This function returns to the customer options screen
     * @param event
     * @throws IOException
     */
    @FXML
    void backbtn(ActionEvent event) throws IOException  {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }

    /**
     * This function initializes the account label for the top of the screen
     * @param location
     * @param resources
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        accountlabel.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
    }
}
