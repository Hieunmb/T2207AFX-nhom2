package controller.bills;

import controller.HomeController;
import daopatern.*;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Bills2Controller extends Component implements Initializable{
    public ComboBox<Customer> cbCheckInID;
    public Label lbCusID;
    public Label lbRoomID;
    public TextField lbPrice;
    public DatePicker dpCheckOutDate;
    public ComboBox<String> cbPayment;
    public TableView<Bills2> tbBill;
    public TableColumn<Bills2, Integer> cID;
    public TableColumn<Bills2, Integer> cCusID;
    public TableColumn<Bills2, Double> cPrice;
    public TableColumn<Bills2, Date> cCheckOutDate;
    public TableColumn<Bills2, String> cPayMentType;

    public void goToHome(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Home");
    }

    public void goToCheckin(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/checkin/checkin.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Check In");
    }

    public void goToService(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/service/service.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Service");
    }

    public void goToManageRoom(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/room/room.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Manage Room");
    }

    public void goToCustomer(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/customer_info/customer_info.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Customer Info");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCusID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        cPayMentType.setCellValueFactory(new PropertyValueFactory<>("payments"));

        BillsDao2 bd = BillsDao2.getInstance();
        ArrayList<Bills2> list = bd.getAll();
        tbBill.getItems().addAll(list);
        tbBill.refresh();

        tbBill.setOnMouseClicked(event -> {
            Bills2 bills2Select = tbBill.getSelectionModel().getSelectedItem();
            if (bills2Select != null) {

            }
        });

        // combobox customer
        try {
            CusDao cd = CusDao.getInstance();
            ArrayList<Customer> list1 = cd.getAll();
            cbCheckInID.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        cbCheckInID.setOnAction(event -> {
            Customer customer = cbCheckInID.getValue();
            if (customer!=null) {
                lbCusID.setText(customer.getName());
                CheckIn checkIn = CheckInDao.getInstance().findByCusID(customer.getId());
                RoomInfo roomInfo = RoomDao.getInstance().find(checkIn.getRoom_id());
                lbPrice.setText(String.valueOf(roomInfo.getPrice()));
                lbRoomID.setText(roomInfo.getName());

            }
        });
        ObservableList<String> pm = FXCollections.observableArrayList();
        pm.add("Cash");
        pm.add("Bank card");
        Collections.sort(pm, ((o1, o2) -> o1.compareTo(o2)));
        cbPayment.setItems(pm);
    }

    public void unSelect(ActionEvent event) {
        tbBill.getSelectionModel().clearSelection();
        refreshForm(null);
        cbCheckInID.getSelectionModel().clearSelection();
        tbBill.getItems().setAll(BillsDao2.getInstance().getAll());
        tbBill.refresh();
    }

    public void refreshForm(ActionEvent event) {
        cbCheckInID.setValue(null);
        dpCheckOutDate.setValue(null);
        lbPrice.setText(null);
        lbCusID.setText(null);
        lbRoomID.setText(null);
        cbPayment.setValue(null);
    }

    public void addBill(ActionEvent event) {
        try {
            Integer customer_id = cbCheckInID.getValue().getId();
            Integer priceBill ;
            CheckIn checkIn = CheckInDao.getInstance().findByCusID(customer_id);
            String payments = cbPayment.getValue();
            if (dpCheckOutDate.getValue() == null) {
                refreshForm(null);
                throw new Exception("Please select a date first!");
            }
            if (checkIn.getCheckindate().after(java.sql.Date.valueOf(dpCheckOutDate.getValue()))) {
                refreshForm(null);
                throw new Exception("Check-out date cannot be before check-in date.");
            }

            Date checkOutDate = java.sql.Date.valueOf(dpCheckOutDate.getValue());
            long numberofDays = 0;
            numberofDays = ChronoUnit.DAYS.between(checkIn.getCheckindate().toLocalDate(), dpCheckOutDate.getValue());
            priceBill = (int) (numberofDays * (int) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice());

            Bills2 bills2 = new Bills2(null, customer_id, priceBill, checkOutDate, payments);
            BillsDao2 bd = BillsDao2.getInstance();
            bd.create(bills2);

            CheckInDao cd = CheckInDao.getInstance();
            CheckIn checkIn1 = cd.findByCusID(bills2.getCustomer_id());
            System.out.println(bills2.getCustomer_id());
            System.out.println(checkIn1.getId());
//             đổi booked thành not booked
            cd.notBooking(checkIn1);
//             xóa checkin
            cd.delete(checkIn1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        refreshForm(null);
        tbBill.getItems().setAll(BillsDao2.getInstance().getAll());
        tbBill.refresh();
        cbCheckInID.getItems().setAll(CusDao.getInstance().getAll());
    }

    public void printBills(ActionEvent event) throws IOException {

    }
}
