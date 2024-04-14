import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TellerOptionsController {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    void TellerBtnClicked(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("TellerSelectAccount.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Teller Button Clicked");
    }
    @FXML
    void AccountStatusDisplayedTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountStatusDisplayedTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account Status Button Clicked");
    }
    @FXML
    void DebitAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DebitAccountTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Debit Account Button Clicked");
    }
    @FXML
    void TransferTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WithdrawFromToTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Transfer Money Button Clicked");
    }
    @FXML
    void CreditAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreditAccountTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Credit Account Button Clicked");
    }
    @FXML
    void DisplayRecentDebitsTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RecentDebitsTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Display Recent Debits Button Clicked");
    }
    @FXML
    void AccountBalanceTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AccountBalanceTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Account balance Button Clicked");
    }
    @FXML
    void showAccountInfoTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TellerAccountInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void CreateAccountTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreateAccountTeller.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Create Account Teller Button Clicked");
    }
}
