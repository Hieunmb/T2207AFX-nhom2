package controller.checkin;

import controller.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CheckInController {

    public void goToHome(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Home");
    }
}
