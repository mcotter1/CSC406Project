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

    //Still needs work for the Different Accounts


    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene

    @FXML
    private ComboBox<Account> AccountSelection;   //Custom Account Selection just like thomases screen for teller and manager

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

    private int transferaccountindex;
    private double amount;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ArrayList<Account> accounts = App.Customers.get(App.currentcustomerindex).getAccounts();
        ObservableList<Account> combinedList = FXCollections.observableArrayList();      // Observable lists for different account types
        ObservableList<Checking> checkingList = FXCollections.observableArrayList();
        ObservableList<Savings> savingsList = FXCollections.observableArrayList();
        ObservableList<Loan> loanList = FXCollections.observableArrayList();
    
        // Loop through all accounts and classify them
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account instanceof Checking) {
                combinedList.add((Checking) account);   //Adding the Accounts to a combinedlist
            } else if (account instanceof Savings) {
                combinedList.add((Savings) account);    //Adding the Accounts to a combinedList
            }
        }

        AccountSelection.setItems(combinedList);    // Only displaying Checking and Savings

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
            else if (custom.isSelected()) amount = Double.parseDouble(DollarCustom.getText());
    }


    @FXML
    void Withdraw(ActionEvent event) throws IOException {
        Account workAccount = App.Customers.get(App.currentcustomerindex).getAccounts().get(App.currentaccountindex);
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
        if (amount > workAccount.getBalance()) {
            errorLbl.setText("Not enough funds");
            return;
        }

        workAccount.setBalance(workAccount.getBalance() - amount); // Update balance
    
        // Load the next scene
        Parent root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
