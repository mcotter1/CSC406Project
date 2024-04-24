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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerDisplayMessageController implements Initializable {
    private Stage stage; // This is the stage for the scene
    private Scene scene; // This is the scene for the stage
    private static Parent root; // This is the root for the scene
    @FXML
    private TableView<String> messagestable;

    /**
     * This function sets up the messages table
     * @param location
     * @param resources
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> workmessages = App.Customers.get(App.currentcustomerindex).getMessages();
        if(workmessages.isEmpty()){
            messagestable.setVisible(false);
        }
        ObservableList<String> observablemessages = FXCollections.observableArrayList(workmessages);
        messagestable.setItems(observablemessages);
        TableColumn<String, String> messages = new TableColumn<>("Messages");
        messages.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        messagestable.getColumns().setAll(messages);
    }

    /**
     * This function returns back to customer options
     * @param event
     * @throws IOException
     */
    @FXML
    void BackButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CustomerSelectPayment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("ID was successful and Options are displayed");
    }
}
