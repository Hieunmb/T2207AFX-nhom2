package controller.bills;

import controller.HomeController;
import controller.checkin.CheckInController;
import daopatern.BillsDao;
import daopatern.CheckInDao;
import daopatern.CusDao;
import daopatern.RoomDao;
import entities.Bills;
import entities.CheckIn;
import entities.Customer;
import entities.RoomInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

public class BillsController implements Initializable {

    public ComboBox<CheckIn> cbCheckInID;
    public Label lbCusID;
    public Label lbRoomID;
    public TextField lbPrice;
    public DatePicker dpCheckOutDate;
    public ComboBox<String> cbPayment;
    public TableView<Bills> tbBill;
    public TableColumn<Bills, Integer> cID;
    public TableColumn<Bills, Integer> cCusID;
    public TableColumn<Bills, Double> cPrice;
    public TableColumn<Bills, Date> cCheckOutDate;
    public TableColumn<Bills, String> cPayMentType;
    public Text txtPrice;

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
        cCusID.setCellValueFactory(new PropertyValueFactory<>("cus_id"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        cPayMentType.setCellValueFactory(new PropertyValueFactory<>("payments"));

        BillsDao cd = BillsDao.getInstance();
        ArrayList<Bills> list = cd.getAll();
        tbBill.getItems().addAll(list);
        tbBill.refresh();
        tbBill.setOnMouseClicked(event -> {
            Bills billsSelect = tbBill.getSelectionModel().getSelectedItem();
            if (billsSelect != null) {
                CheckIn checkIn = CheckInDao.getInstance().find(billsSelect.getCheckin_id());
                cbCheckInID.setValue(checkIn);
                Instant instant = Instant.ofEpochMilli(billsSelect.getCheckoutDate().getTime());
                LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                dpCheckOutDate.setValue(localDate);
                cbPayment.setValue(billsSelect.getPayments());
                lbPrice.setText(billsSelect.getPrice().toString());
            }
        });

        // combobox checkin_id
        try {
            CheckInDao cd1 = CheckInDao.getInstance();
            ArrayList<CheckIn> list1 = cd1.getAll();
            cbCheckInID.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        cbCheckInID.setOnAction(event -> {
            CheckIn checkInSelect = cbCheckInID.getValue();
            if (checkInSelect != null) {
                lbCusID.setText(checkInSelect.getCustomer_id().toString());
                lbRoomID.setText(checkInSelect.getRoom_id().toString());
                Double price = RoomDao.getInstance().getPriceByRoomTypeId(checkInSelect.getRoom_id());
                lbPrice.setText(String.valueOf(price));
            }
//            try {
//                CheckIn checkInSelect = cbCheckInID.getValue();
//                if (checkInSelect != null) {
//                    lbCusID.setText(checkInSelect.getCustomer_id().toString());
//                    lbRoomID.setText(checkInSelect.getRoom_id().toString());
//                    Double price = RoomDao.getInstance().getPriceByRoomTypeId(checkInSelect.getRoom_id());
//                    long numberofDays = 0;
//                    if (dpCheckOutDate.getValue() == null) {
//                        refreshForm(null);
//                        throw new Exception("Please select a date first!");
//                    } else {
//                        if (checkInSelect.getCheckoutDate().after(java.sql.Date.valueOf(dpCheckOutDate.getValue())))
//                        {
//                            refreshForm(null);
//                            throw new Exception("Check-out date cannot be before check-in date.");
//                        }
//                        numberofDays = ChronoUnit.DAYS.between(checkInSelect.getCheckindate().toLocalDate(), dpCheckOutDate.getValue());
//                        price = price * numberofDays;
//                        lbPrice.setText(String.valueOf(price));
//                    }
//                }
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(e.getMessage());
//                alert.show();
//            }
        });

        // combobox payments
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
        tbBill.getItems().setAll(BillsDao.getInstance().getAll());
        tbBill.refresh();

    }

    public void refreshForm(ActionEvent event) {
        cbCheckInID.getSelectionModel().clearSelection();
        dpCheckOutDate.setValue(null);
        lbPrice.setText(null);
        lbCusID.setText(null);
        lbRoomID.setText(null);
    }

    public void addBill(ActionEvent event) {
        try {
            Integer checkin_id = cbCheckInID.getValue().getId();
            Integer priceBill ;
            String payments = cbPayment.getValue();
            if (dpCheckOutDate.getValue() == null) {
                refreshForm(null);
                throw new Exception("Please select a date first!");
            }
            if (cbCheckInID.getValue().getCheckindate().after(java.sql.Date.valueOf(dpCheckOutDate.getValue()))) {
                refreshForm(null);
                throw new Exception("Check-out date cannot be before check-in date.");
            }

            Date checkOutDate = java.sql.Date.valueOf(dpCheckOutDate.getValue());
            long numberofDays = 0;
            numberofDays = ChronoUnit.DAYS.between(cbCheckInID.getValue().getCheckindate().toLocalDate(), dpCheckOutDate.getValue());
            priceBill = (int) (numberofDays * (int) RoomDao.getInstance().getPriceByRoomTypeId(cbCheckInID.getValue().getRoom_id()));

            Bills bills = new Bills(null, checkin_id, priceBill, checkOutDate, payments);
            BillsDao bd = BillsDao.getInstance();
            bd.create(bills);

            CheckInDao cd = CheckInDao.getInstance();
            // đổi booked thành not booked
            cd.notBooking(cd.find(cbCheckInID.getValue().getId()));
            // xóa checkin
            CheckIn checkIn = cd.find(bills.getCheckin_id());
            cd.delete(checkIn);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        refreshForm(null);
        tbBill.getItems().setAll(BillsDao.getInstance().getAll());
        tbBill.refresh();
        cbCheckInID.getItems().setAll(CheckInDao.getInstance().getAll());
    }
}
