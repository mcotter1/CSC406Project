import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateCustomerController {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private TextField ssn;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField address;
    @FXML
    private TextField state;
    @FXML
    private TextField city;
    @FXML
    private TextField zipcode;
    @FXML
    private Label error;
    @FXML
    private Label success;

    @FXML
    void CreateCustomer(ActionEvent event){
        int ssnentry = 0;
        String firstnameentry = null;
        String lastnameentry = null;
        String addressentry = null;
        String cityentry = null;
        String stateentry = null;
        int zipcodeentry = 0;
        if(ssn != null && !ssn.getText().matches(".*[a-zA-Z]+.*")&&!ssn.getText().isBlank()&&ssn.getText().length()==9){
            ssnentry = Integer.parseInt(ssn.getText());
        } else {
            error.setText("One Or More Fields Invalid");
            success.setText("");
            return;
        }
        if(zipcode != null && !zipcode.getText().matches(".*[a-zA-Z]+.*")&&!zipcode.getText().isBlank()&&zipcode.getText().length()==5){
            zipcodeentry = Integer.parseInt(zipcode.getText());
        } else {
            error.setText("One Or More Fields Invalid");
            success.setText("");
            return;
        }
        if(firstname != null && !firstname.getText().isBlank()){
            firstnameentry = firstname.getText();
        } else {
            error.setText("One Or More Fields Invalid");
            success.setText("");
            return;
        }
        if(lastname != null && !lastname.getText().isBlank()){
            lastnameentry = lastname.getText();
        } else {
            error.setText("One Or More Fields Invalid");
            success.setText("");
            return;
        }
        if(address != null && !address.getText().isBlank()){
            addressentry = address.getText();
        } else {
            error.setText("One Or More Fields Invalid");
            success.setText("");
            return;
        }
        if(city != null && !city.getText().isBlank()){
            cityentry = city.getText();
        } else {
            error.setText("One Or More Fields Invalid");
            success.setText("");
            return;
        }
        if(state != null && !state.getText().isBlank()){
            stateentry = state.getText();
        } else {
            error.setText("One Or More Fields Invalid");
            success.setText("");
            return;
        }
        Customer newcustomer = new Customer(ssnentry,addressentry,cityentry,stateentry,zipcodeentry,firstnameentry,lastnameentry);
        App.Customers.add(newcustomer);
        error.setText("");
        success.setText("New Customer Added");

    }
    @FXML
    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Clicked");
    }
}
