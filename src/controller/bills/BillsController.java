package controller.bills;

import controller.HomeController;
import daopatern.BillsDao;
import entities.Bills;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

public class BillsController implements Initializable {
    public TextField txtCusID;
    public TextField txtPrice;
    public DatePicker dpCheckOutDate;
    public ComboBox<String> cbPayment;
    public TableView<Bills> tbBill;
    public TableColumn<Bills, Integer> cID;
    public TableColumn<Bills, Integer> cCusID;
    public TableColumn<Bills, Integer> cPrice;
    public TableColumn<Bills, Date> cCheOutDate;
    public TableColumn<Bills, String> cPayM;

    public void goToHome(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Home");
    }

    public void goToCheckin(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/checkin/checkin.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Check In");
    }

    public void goToService(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/service/service.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Service");
    }

    public void goToManageRoom(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/room/room.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Manage Room");
    }

    public void goToCustomer(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/customer_info/customer_info.fxml"));

        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Customer Info");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCusID.setCellValueFactory(new PropertyValueFactory<>("cus_id"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cPayM.setCellValueFactory(new PropertyValueFactory<>("payment"));
        cCheOutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutdate"));

        BillsDao billsDao = BillsDao.getInstance();
        ArrayList<Bills> list = billsDao.getAll();
        tbBill.getItems().addAll(list);
        tbBill.refresh();

        ObservableList<String> pm = FXCollections.observableArrayList();
        pm.add("Cash");
        pm.add("Credit");
        pm.add("Transfer");
        Collections.sort(pm, ((o1, o2) -> o1.compareTo(o2)));
        cbPayment.setItems(pm);
    }
}
