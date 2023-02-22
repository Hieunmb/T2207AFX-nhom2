package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
    public static Stage rootStage;

    public void goToManageRoom(ActionEvent event) {

    }

    public void goToCheckIn(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/checkin/checkin.fxml"));

        rootStage.setScene(new Scene(root, 1200, 720));
        rootStage.setTitle("Check In");
    }

    public void goToCustomerInfo(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/customer_info/customer_info.fxml"));

        rootStage.setScene(new Scene(root, 1200, 720));
        rootStage.setTitle("Customer Info");
    }

    public void goToBill(ActionEvent event) {

    }

    public void goToService(ActionEvent event) {

    }
}
