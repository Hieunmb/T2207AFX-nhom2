package controller.service;

import controller.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ServiceController {

    public void goToHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }

    public void goToCheckIn(ActionEvent event) throws  Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/checkin/checkin.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }

    public void goToRoom(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/room/room.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }

    public void goToBill(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/bills/bills.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }

    public void goToCustomerInfo(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/customer_info/customer_info.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }
}
