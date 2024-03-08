import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("Exiting program...");
            });

            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Banking System User Selection");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}