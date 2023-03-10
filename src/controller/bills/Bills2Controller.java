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
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Bills2Controller extends Component implements Initializable {
    public ComboBox<Customer> cbCheckInID;
    public Label lbCusID;
    public Label lbRoomID;
    public TextField lbPrice;
    public DatePicker dpCheckOutDate;
    public ComboBox<String> cbPayment;
    public TableView<Bills3> tbBill;
    public TableColumn<Bills3, Integer> cID;
    public TableColumn<Bills3, Integer> cCusID;
    public TableColumn<Bills3, Double> cPrice;
    public TableColumn<Bills3, String> cCheckOutDate;
    public TableColumn<Bills3, Date> cCheckInDate;
    public TableColumn<Bills3, Date> cPayMentType;
    public TableColumn<Bills3, String> cStatus;

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
        this.dpCheckOutDate.setDisable(true);
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCusID.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        cCheckInDate.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
        cPayMentType.setCellValueFactory(new PropertyValueFactory<>("payments"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        BillsDao3 bd = BillsDao3.getInstance();
        ArrayList<Bills3> list = bd.getAll();
        tbBill.getItems().addAll(list);
        tbBill.refresh();

        tbBill.setOnMouseClicked(event -> {
            Bills3 bills3Select = tbBill.getSelectionModel().getSelectedItem();
            if (bills3Select != null) {
                lbCusID.setText(bills3Select.getName());
            }
        });

        // combobox customer
        try {
            CusDao cd = CusDao.getInstance();
            ArrayList<Customer> list1 = cd.getAllCusCheckIn();
            cbCheckInID.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        cbCheckInID.setOnAction(event -> {
            Customer customer = cbCheckInID.getValue();
            if (customer != null) {
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
        tbBill.getItems().setAll(BillsDao3.getInstance().getAll());
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
            Float priceBill = null;
            CheckIn checkIn = CheckInDao.getInstance().findByCusID(customer_id);
            String payments = cbPayment.getValue();
            // Tiến hành checkout
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = now.format(formatter);
            String checkOutDate = formatDateTime;

            String checkInDate = checkIn.getCheckindate();
            String status = "unpaid";

            // tính số ngày số giờ
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
            try {
                LocalDateTime datetimeCheckIn = LocalDateTime.parse(checkInDate, formatter2);
                LocalDateTime datetimeCheckOut = LocalDateTime.parse(formatDateTime, formatter1);
                Duration duration = Duration.between(datetimeCheckIn, datetimeCheckOut);

                long numberofDays = duration.toDays();
                long numberofHours = duration.toHours();

//                System.out.println(numberofHours);
//                System.out.println(numberofDays);

                LocalTime currentTime = LocalTime.now();
                LocalTime twelvePM = LocalTime.of(12, 0);

                if (currentTime.isBefore(twelvePM) && numberofDays == 0) {
                    priceBill = (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice();
                } else {
                    if (numberofDays == 0) {
                        priceBill = (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice();
                    } else {
                        if (numberofHours <= 7 && numberofHours <=4 ) {
                            priceBill = (float) ((float) numberofDays * (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() + (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() * 0.5);
                        } else if (numberofHours<=3) {
                            priceBill = (float) ((float) numberofDays * (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() + (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() * 0.3);
                        } else if (datetimeCheckOut.toLocalTime().isBefore(LocalTime.of(14, 0))) {
                            priceBill = (float) ((float) numberofDays * (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() + (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() * 0.3);
                        } else if (datetimeCheckOut.toLocalTime().isBefore(LocalTime.of(18, 0)) && datetimeCheckOut.toLocalTime().isAfter(LocalTime.of(14, 0))) {
                            priceBill = (float) ((float) numberofDays * (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() + (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() * 0.5);
                        } else if (datetimeCheckOut.toLocalTime().isAfter(LocalTime.of(18, 0))) {
                            priceBill = (float) ((float) numberofDays * (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice() + (float) RoomDao.getInstance().find(checkIn.getRoom_id()).getPrice());
                        }
                    }
                }
                Bills2 bills2 = new Bills2(null, customer_id, priceBill, checkInDate, checkOutDate, payments, status);
                BillsDao2 bd = BillsDao2.getInstance();
                bd.create(bills2);

                CheckInDao cd = CheckInDao.getInstance();
                CheckIn checkIn1 = cd.findByCusID(bills2.getCustomer_id());

//             đổi booked thành not booked
                cd.notBooking(checkIn1);
//             xóa checkin
                cd.delete(checkIn1);
            } catch (DateTimeParseException e){
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        refreshForm(null);
        tbBill.getItems().setAll(BillsDao3.getInstance().getAll());
        tbBill.refresh();
        cbCheckInID.getItems().setAll(CusDao.getInstance().getAll());
        cbCheckInID.getItems().setAll(CusDao.getInstance().getAllCusCheckIn());
    }

    public void printBills(ActionEvent event) throws IOException {
        Bills3 bills3select = tbBill.getSelectionModel().getSelectedItem();
        if (bills3select == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a bill to payments.");
            alert.showAndWait();
        } else {
            try {
                BillsDao3 billsDao3 = BillsDao3.getInstance();
                billsDao3.payments(bills3select);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Payment");
                alert.setContentText("You have successfully paid!");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
        refreshForm(null);
        tbBill.getItems().setAll(BillsDao3.getInstance().getAll());
        tbBill.refresh();
        cbCheckInID.getItems().setAll(CusDao.getInstance().getAll());
        cbCheckInID.getItems().setAll(CusDao.getInstance().getAllCusCheckIn());
    }
}
