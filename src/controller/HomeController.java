package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class HomeController {
    public static Stage rootStage;

    public void goToManageRoom(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/room/room.fxml"));

        rootStage.setScene(new Scene(root, 1200, 720));
        rootStage.setTitle("Manage Room");
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

    public void goToBill(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/bills/bills2.fxml"));

        rootStage.setScene(new Scene(root, 1200, 720));
        rootStage.setTitle("Bill Details");
    }

    public void goToService(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/service/service.fxml"));

        rootStage.setScene(new Scene(root, 1200, 720));
        rootStage.setTitle("Service");
    }

    public void Exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit the app");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you really want to exit the app?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes){
            // người dùng chọn "Yes"
            Platform.exit();
            System.exit(0);
        } else {
            // người dùng chọn "No" hoặc đóng cửa sổ Alert
        }
    }
}
