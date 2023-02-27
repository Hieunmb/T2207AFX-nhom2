import controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            HomeController.rootStage = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("resources/home1.fxml"));
            primaryStage.setScene(new Scene(root, 1200, 720));
            primaryStage.setTitle("Home");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
