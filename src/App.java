import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class App extends Application {
    public static void main(String[] args) {   // Main method to run the GUI
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Add anything you want to happen when the program starts here
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene(root);
            
            // Set the title of the window
            primaryStage.setTitle("Banking System User Selection");
            primaryStage.setScene(scene);
            primaryStage.show();


            // Prints to console when the program is closed
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("Exiting program...");
            });

        } catch (Exception e) {

        }
    }
}