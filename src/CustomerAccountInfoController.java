import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerAccountInfoController implements Initializable{
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Checking> CheckingInfo;
    @FXML
    private TableView<Savings> SavingsInfo;
    @FXML
    private TableView<Loan> LoanInfo;

    @FXML
    private Label CheckingNonExisiting;
    @FXML
    private Label SavingNonExisiting;
    @FXML
    private Label LoanNonExisiting;

    @FXML
    void SSNverifyBtnClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Account> accounts = App.Customers.get(App.currentcustomerindex).getAccounts();

        // Observable lists for different account types
        ObservableList<Checking> checkingList = FXCollections.observableArrayList();
        ObservableList<Savings> savingsList = FXCollections.observableArrayList();
        ObservableList<Loan> loanList = FXCollections.observableArrayList();
    
        // Loop through all accounts and classify them
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account instanceof Checking) {
                checkingList.add((Checking) account);
            } else if (account instanceof Savings) {
                savingsList.add((Savings) account);
            } else if (account instanceof Loan) {
                loanList.add((Loan) account);
            }
        }
        
    
        // Set the items for each table
        if (!checkingList.isEmpty()) {
            CheckingInfo.setItems(checkingList);
            TableColumn<Checking,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Checking,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
            TableColumn<Checking,LocalDate> column3= new TableColumn<>("Date Opened");
            column3.setCellValueFactory(new PropertyValueFactory<>("dateopened"));
            TableColumn<Checking,String> column4 = new TableColumn<>("Savings ID");
            column4.setCellValueFactory(
                    cellData -> new SimpleStringProperty(
                            cellData.getValue().getSavingsaccountid()));
            CheckingInfo.getColumns().setAll(column1,column2,column3,column4);
        }else{
            CheckingInfo.setVisible(false);
            CheckingNonExisiting.setText("No Checking Account found");
        }
        if (!savingsList.isEmpty()) {
            SavingsInfo.setItems(savingsList);
            TableColumn<Savings,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Savings,Double> column2 = new TableColumn<>("Balance");
            NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            TableColumn<Savings,LocalDate> column3= new TableColumn<>("Date Opened");
            column3.setCellValueFactory(new PropertyValueFactory<>("dateopened"));
            TableColumn<Savings,String> column4 = new TableColumn<>("Savings ID");
            column4.setCellValueFactory(new PropertyValueFactory<>("savingsaccountid"));
            TableColumn<Savings,Double> column5 = new TableColumn<>("Interest Rate");
            column5.setCellValueFactory(new PropertyValueFactory<>("interestrate"));
            TableColumn<Savings,LocalDate> column6= new TableColumn<>("Date CD Due");
            column6.setCellValueFactory(new PropertyValueFactory<>("CDdue"));
            SavingsInfo.getColumns().setAll(column1,column2,column3,column4,column5,column6);
        }else{
        SavingsInfo.setVisible(false);
        SavingNonExisiting.setText("No Savings Account found");
        }
        if (!loanList.isEmpty()) {
            LoanInfo.setItems(loanList);
            TableColumn<Loan,String> column1 = new TableColumn<>("Acc Type");
            column1.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
            TableColumn<Loan,Double> column2 = new TableColumn<>("Balance");
            column2.setCellValueFactory(new PropertyValueFactory<>("balance"));
            NumberFormat balanceFormat = NumberFormat.getCurrencyInstance();
            TableColumn<Loan,Double> column3= new TableColumn<>("Amount Due");
            column3.setCellValueFactory(new PropertyValueFactory<>("paymentamountdue"));
            TableColumn<Loan,LocalDate> column4 = new TableColumn<>("Due Date");
            column4.setCellValueFactory(new PropertyValueFactory<>("datepaymentdue"));
            TableColumn<Loan,Double> column5 = new TableColumn<>("Interest Rate");
            column5.setCellValueFactory(new PropertyValueFactory<>("interestrate"));
            TableColumn<Loan,LocalDate> column6= new TableColumn<>("Last Payment");
            column6.setCellValueFactory(new PropertyValueFactory<>("lastpaymentdate"));
            TableColumn<Loan,LocalDate> column7= new TableColumn<>("Notified");
            column7.setCellValueFactory(new PropertyValueFactory<>("notifiedofpayment"));
            TableColumn<Loan,String> column8= new TableColumn<>("Loan Type");
            column8.setCellValueFactory(new PropertyValueFactory<>("loantype"));
            TableColumn<Loan,Double> column9= new TableColumn<>("Term Length");
            column9.setCellValueFactory(new PropertyValueFactory<>("termlength"));
            TableColumn<Loan,String> column10= new TableColumn<>("Collateral");
            column10.setCellValueFactory(new PropertyValueFactory<>("collateral"));
            TableColumn<Loan,String> column11= new TableColumn<>("Repayment Plan");
            column11.setCellValueFactory(new PropertyValueFactory<>("repaymentplantype"));
            TableColumn<Loan,Double> column12= new TableColumn<>("Credit Limit");
            column12.setCellValueFactory(new PropertyValueFactory<>("creditcardlimit"));
            LoanInfo.getColumns().setAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column12);
        }else{
        LoanInfo.setVisible(false);
        LoanNonExisiting.setText("No Loan Account found");
        }
    }
};
