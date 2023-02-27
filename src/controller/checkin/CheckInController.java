package controller.checkin;

import controller.HomeController;
import daopatern.CheckInDao;
import entities.CheckIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckInController implements Initializable {

    public ComboBox cdCusID;
    public TextField txtCusName;
    public TextArea txtNote;
    public DatePicker dpCheckIn;
    public DatePicker dpCheckOut;
    public ComboBox cbRoomID;
    public TextField txtRoomName;
    public ComboBox<String> cbStatus;
    public ComboBox<String> cbFloor;
    public ComboBox<String> cbRoomType;
    public TableView<CheckIn> tbCheckIn;
    public TableColumn<CheckIn, Integer> cID;
    public TableColumn<CheckIn, Integer> cCusID;
    public TableColumn<CheckIn, Integer> cRoomID;
    public TableColumn<CheckIn, String> cNote;
    public TableColumn<CheckIn, Date> cCheckInDate;
    public TableColumn<CheckIn, Date> cCheckOutDate;


    public void goToHome(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Home");
    }

    public void goToService(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/service/service.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Service");
    }

    public void goToCustomer(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/customer_info/customer_info.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Customer Info");
    }

    public void goToManageRoom(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/room/room.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Manage Room");
    }

    public void goToBills(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/bills/bills.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Bill Details");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCusID.setCellValueFactory(new PropertyValueFactory<>("nameCus"));
        cRoomID.setCellValueFactory(new PropertyValueFactory<>("nameRoom"));
        cCheckInDate.setCellValueFactory(new PropertyValueFactory<>("checkindate"));
        cCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        cNote.setCellValueFactory(new  PropertyValueFactory<>("note"));

        CheckInDao checkInDao = CheckInDao.getInstance();
        ArrayList<CheckIn> list = checkInDao.getAll();
        tbCheckIn.getItems().addAll(list);
        tbCheckIn.refresh();
    }
}
