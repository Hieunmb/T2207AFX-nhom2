package controller.checkin;

import controller.HomeController;
import daopatern.CheckInDao;
import daopatern.CusDao;
import daopatern.RoomDao;
import entities.*;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class CheckInController implements Initializable {

    public ComboBox<Customer> cdCusID;
    public TextArea txtNote;
    public DatePicker dpCheckIn;
    public DatePicker dpCheckOut;
    public ComboBox<RoomInfo> cbRoomID;
    public TableView<CheckIn> tbCheckIn;
    public TableColumn<CheckIn, Integer> cID;
    public TableColumn<CheckIn, Customer> cCusID;
    public TableColumn<CheckIn, Room> cRoomID;
    public TableColumn<CheckIn, String> cNote;
    public TableColumn<CheckIn, Date> cCheckInDate;
    public TableColumn<CheckIn, Date> cCheckOutDate;
    public Label lbCusName;
    public Label lbRoomName;
    public Label lbStatus;
    public Label lbFloor;
    public Label lbRoomType;
    public Button addBtn;
    public ComboBox<CheckIn> cbSearch;


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
        HomeController.rootStage.setTitle("Customer");
    }

    public void goToManageRoom(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/room/room.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Manage Room");
    }

    public void goToBills(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/bills/bills2.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Bills");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCusID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        cRoomID.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        cCheckInDate.setCellValueFactory(new PropertyValueFactory<>("checkindate"));
        cCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        cNote.setCellValueFactory(new  PropertyValueFactory<>("note"));

        CheckInDao checkInDao = CheckInDao.getInstance();
        ArrayList<CheckIn> list = checkInDao.getAll();
        tbCheckIn.getItems().addAll(list);
        tbCheckIn.refresh();

        // select tableview
        tbCheckIn.setOnMouseClicked(event -> {
            CheckIn checkInSelect = tbCheckIn.getSelectionModel().getSelectedItem();
            if (checkInSelect!=null) {
                Customer customer = CusDao.getInstance().find(checkInSelect.getCustomer_id());
                cdCusID.setValue(customer);
                RoomInfo roomInfo = RoomDao.getInstance().find(checkInSelect.getRoom_id());
                cbRoomID.setValue(roomInfo);
                dpCheckIn.setValue(checkInSelect.getCheckindate().toLocalDate());
                dpCheckOut.setValue(checkInSelect.getCheckoutDate().toLocalDate());
                txtNote.setText(checkInSelect.getNote());
                addBtn.setDisable(true);
                lbCusName.setText(customer.getName());
                lbRoomName.setText(roomInfo.getName());
                lbStatus.setText(roomInfo.getStatus());
                lbFloor.setText(roomInfo.getFloor());
                lbRoomType.setText(roomInfo.getRoomtype());
            }
        });

        // combobox customerID
        try {
            CusDao cd = CusDao.getInstance();
            ArrayList<Customer> list2 = cd.getAll();
            cdCusID.getItems().addAll(list2);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        cdCusID.setOnAction(event -> {
            Customer customerSelect = cdCusID.getValue();
            if (customerSelect !=null) {
                lbCusName.setText(customerSelect.getName());
            }
        });

        // combobox roomID
        try {
            RoomDao rd = RoomDao.getInstance();
            ArrayList<RoomInfo> list1 = rd.getAllNotBooked();
            cbRoomID.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        cbRoomID.setOnAction(event -> {
            RoomInfo roomInfoSelect = cbRoomID.getValue();
            if (roomInfoSelect !=null) {
                lbRoomName.setText(roomInfoSelect.getName());
                lbStatus.setText(roomInfoSelect.getStatus());
                lbFloor.setText(roomInfoSelect.getFloor());
                lbRoomType.setText(roomInfoSelect.getRoomtype());
            }
        });

        // combobox searchCheckIn
        try {
            CheckInDao checkInDao1 = CheckInDao.getInstance();
            ArrayList<CheckIn> list1 = checkInDao1.getAll();
            cbSearch.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }

    }

    public void unSelect(ActionEvent event) {
        tbCheckIn.getSelectionModel().clearSelection();
        refreshForm(null);
        tbCheckIn.getItems().setAll(CheckInDao.getInstance().getAll());
        tbCheckIn.refresh();
        addBtn.setDisable(false);
        cbSearch.getSelectionModel().clearSelection();
    }

    public void searchCheckIn(ActionEvent event) {
        CheckIn checkInSelect = cbSearch.getSelectionModel().getSelectedItem();
        if (checkInSelect == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a checkin to edit.");
            alert.showAndWait();
        } else {
            try {
                Integer id = checkInSelect.getId();
                CheckInDao cd = CheckInDao.getInstance();
                CheckIn checkIn = cd.find(id);
                ArrayList<CheckIn> list = new ArrayList<>();
                list.add(checkIn);
                tbCheckIn.getItems().setAll(list);
                tbCheckIn.refresh();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
    }

    public void refreshForm(ActionEvent event) {
        cdCusID.setValue(null);
        cbRoomID.setValue(null);
        dpCheckOut.setValue(null);
        dpCheckIn.setValue(null);
        txtNote.clear();
        lbCusName.setText(null);
        lbFloor.setText(null);
        lbStatus.setText(null);
        lbRoomType.setText(null);
        lbRoomName.setText(null);
    }

    public void addCheckIn(ActionEvent event) {
        try {
            Integer cus_id = cdCusID.getValue().getId();
            Integer room_id = cbRoomID.getValue().getId();
            Date checkIn = Date.valueOf(dpCheckIn.getValue());
            Date checkOut = Date.valueOf(dpCheckOut.getValue());
            String note = txtNote.getText();
            if (cus_id == null || room_id == null || checkIn == null || checkOut == null) {
                throw new Exception("Please complete all information");
            }
            if (checkIn.toLocalDate().compareTo(checkOut.toLocalDate())>=0) {
                throw new Exception("Check-out date cannot be before check-in date.");
            }
            CheckIn checkIn1 = new CheckIn(null, cus_id, room_id, checkIn, checkOut, note);
            CheckInDao cd = CheckInDao.getInstance();
            cd.create(checkIn1);
            cd.booking(checkIn1);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        refreshForm(null);
        tbCheckIn.getItems().setAll(CheckInDao.getInstance().getAll());
        tbCheckIn.refresh();
        cbRoomID.getItems().setAll(RoomDao.getInstance().getAllNotBooked());
        cbSearch.getItems().setAll(CheckInDao.getInstance().getAll());
    }

    public void editCheckIn(ActionEvent event) {
        CheckIn checkInSelect = tbCheckIn.getSelectionModel().getSelectedItem();
        if (checkInSelect == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a checkin to edit.");
            alert.showAndWait();
        } else {
            try {
                checkInSelect.setCustomer_id(cdCusID.getValue().getId());
                checkInSelect.setRoom_id(cbRoomID.getValue().getId());
                checkInSelect.setCheckindate(Date.valueOf(dpCheckIn.getValue()));
                checkInSelect.setCheckoutDate(Date.valueOf(dpCheckOut.getValue()));
                checkInSelect.setNote(txtNote.getText());
                if (checkInSelect.getCustomer_id() == null|| checkInSelect.getRoom_id() == null || checkInSelect.getCheckindate() == null || checkInSelect.getCheckoutDate() == null) {
                    throw new Exception("Please complete all information");
                }
                if (checkInSelect.getCheckindate().after(checkInSelect.getCheckoutDate())) {
                    throw new Exception("Check-out date cannot be before check-in date.");
                }
                CheckInDao cd = CheckInDao.getInstance();
                CheckIn checkIn = cd.find(checkInSelect.getId());
                cd.notBooking(checkIn);
                cd.update(checkInSelect);
                cd.booking(checkInSelect);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Edit checkin");
                alert.setContentText("Edit cuccess!");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
        refreshForm(null);
        tbCheckIn.getItems().setAll(CheckInDao.getInstance().getAll());
        tbCheckIn.refresh();
        cbRoomID.getItems().setAll(RoomDao.getInstance().getAllNotBooked());
    }

    public void deleteCheckIn(ActionEvent event) {
        CheckIn checkInSelect = tbCheckIn.getSelectionModel().getSelectedItem();
        if (checkInSelect == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a checkin to edit.");
            alert.showAndWait();
        } else {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm delete");
            confirm.setHeaderText("Confirm delete");
            confirm.setContentText("Are you sure you want to delete this checkin?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CheckInDao cd = CheckInDao.getInstance();
                    cd.notBooking(checkInSelect);
                    cd.delete(checkInSelect);
                    refreshForm(null);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(e.getMessage());
                    alert.show();
                }
            }
        }
        tbCheckIn.getItems().setAll(CheckInDao.getInstance().getAll());
        tbCheckIn.refresh();
        cbRoomID.getItems().setAll(RoomDao.getInstance().getAllNotBooked());
        cbSearch.getItems().setAll(CheckInDao.getInstance().getAll());
    }
}
