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

public class CustomerATMWindow implements Initializable {



    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private Button Backbtn;         //Back Button
    @FXML
    private RadioButton custom;   //Per Defined Selection for Radio button 1 
    @FXML
    private RadioButton Dollar10;   //Per Defined Selection for Radio button 1 
    @FXML
    private RadioButton Dollar75;   //Per Defined Selection for Radio button 4 
    @FXML
    private RadioButton Dollar100;   //Per Defined Selection for Radio button 4 
    @FXML
    private RadioButton Dollar20;   //Per Defined Selection for Radio button 2 
    @FXML
    private RadioButton Dollar50;   //Per Defined Selection for Radio button 3 
    @FXML
    private TextField DollarCustom; //Custom Amount of Dollar selection
    @FXML
    private Button Withdrawbtn;     //Withdraw button
    @FXML
    private Label errorLbl;


    @FXML
    private Label AccountInfolbl;

    private int transferaccountindex;
    private double amount;

    @Override
    public void initialize(URL location, ResourceBundle resources){
      AccountInfolbl.setText(App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).toString());
        
      ToggleGroup amountToggleGroup = new ToggleGroup();
      Dollar10.setToggleGroup(amountToggleGroup);
      Dollar20.setToggleGroup(amountToggleGroup);
      Dollar50.setToggleGroup(amountToggleGroup);
      Dollar75.setToggleGroup(amountToggleGroup);
      Dollar100.setToggleGroup(amountToggleGroup);
      custom.setToggleGroup(amountToggleGroup);
  
      // Setup a listener for when the custom radio button is selected
      custom.selectedProperty().addListener((observable, oldValue, newValue) -> {
          DollarCustom.setDisable(!newValue);
          if (newValue) {
              errorLbl.setText(""); // Clear the error when custom is selected
          }
      });
  
      // Add a focus lost listener to the custom amount TextField to validate the amount
      DollarCustom.focusedProperty().addListener((observable, oldValue, newValue) -> {
          if (!newValue && custom.isSelected()) { // Focus lost on the custom TextField
              try {
                  String text = DollarCustom.getText().trim();
                  amount = text.isEmpty() ? 0.0 : Double.parseDouble(text);
                  errorLbl.setText(""); // Clear error message if parse is successful or text is empty
              } catch (NumberFormatException e) {
                  amount = 0.0;
                  errorLbl.setText("Invalid custom amount"); // Display error message
              }
          }
      });
    }

    private void toggleCustomAmountField() {
        DollarCustom.setDisable(!custom.isSelected());
        getAmount();
    }

    @FXML
    void getAmount() {
            if (Dollar10.isSelected()) amount = 10;
            else if (Dollar20.isSelected()) amount = 20;
            else if (Dollar50.isSelected()) amount = 50;
            else if (Dollar75.isSelected()) amount = 75;
            else if (Dollar100.isSelected()) amount = 100;
    }


    @FXML
    void Withdraw(ActionEvent event) throws IOException {
        Account account = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
        if(account.getAccounttype().equalsIgnoreCase("tmb")||account.getAccounttype().equalsIgnoreCase("gold")) {
            Checking workchecking = (Checking) account;
            if(!workchecking.isATMcard()){
                errorLbl.setText("Account does not hold ATM card");
                return;
            }
            //Withdraw at this date < 2
            if (workchecking.getATMwithdrawalfrequency() < 2) {
                if (custom.isSelected()) {
                    String text = DollarCustom.getText().trim();
                    if (!text.isEmpty()) {
                        try {
                            amount = Double.parseDouble(text);
                            errorLbl.setText("");
                        } catch (NumberFormatException e) {
                            amount = 0.0;
                            errorLbl.setText("Invalid custom amount");
                            return;
                        }
                    }
                }
                // Check if the amount is valid
                if (amount <= 0) {
                    errorLbl.setText("Please enter a valid amount to withdraw");
                    return;
                } else {
                    System.out.println("This is the amount: " + amount);
                }

                // Check if there are enough funds in the account
                if (amount > App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getBalance()) {
                    errorLbl.setText("Not enough funds");
                    return;
                }

                workchecking.setBalance(workchecking.getBalance() - amount); // Update balance
                if(workchecking.getAccounttype().equalsIgnoreCase("gold")&&workchecking.getBalance()<5000){
                    workchecking.setAccounttype("TMB");
                    workchecking.setGolddiamondcheck(false);
                }
                workchecking.ATMwithdrawalfrequency++;
                Transaction ATMTransaction = new Transaction("Withdraw", workchecking.getAccounttype(), -amount, LocalDate.now(), workchecking.getBalance() - amount);
                workchecking.AddTransaction(ATMTransaction);
                App.Customers.get(App.currentaccountindex).getAccounts().set(App.currentaccountindex, workchecking);
                AccountInfolbl.setText(workchecking.toString());
            } else {
                errorLbl.setText("2");
            }
        } else {
            Savings worksavings = (Savings) account;
            if(!worksavings.isATMcard()){
                errorLbl.setText("Account does not hold ATM card");
                return;
            }
            //Withdraw at this date < 2
            if (worksavings.getATMwithdrawalfrequency() < 2) {
                if (custom.isSelected()) {
                    String text = DollarCustom.getText().trim();
                    if (!text.isEmpty()) {
                        try {
                            amount = Double.parseDouble(text);
                            errorLbl.setText("");
                        } catch (NumberFormatException e) {
                            amount = 0.0;
                            errorLbl.setText("Invalid custom amount");
                            return;
                        }
                    }
                }
                // Check if the amount is valid
                if (amount <= 0) {
                    errorLbl.setText("Please enter a valid amount to withdraw");
                    return;
                } else {
                    System.out.println("This is the amount: " + amount);
                }

                // Check if there are enough funds in the account
                if (amount > App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex).getBalance()) {
                    errorLbl.setText("Not enough funds");
                    return;
                }

                worksavings.setBalance(worksavings.getBalance() - amount); // Update balance
                worksavings.ATMwithdrawalfrequency++;
                Transaction ATMTransaction = new Transaction("Withdraw", worksavings.getAccounttype(), -amount, LocalDate.now(), worksavings.getBalance() - amount);
                worksavings.AddTransaction(ATMTransaction);
                App.Customers.get(App.currentaccountindex).getAccounts().set(App.currentaccountindex, worksavings);
                AccountInfolbl.setText(worksavings.toString());
            } else {
                errorLbl.setText("2");
            }
        }
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
