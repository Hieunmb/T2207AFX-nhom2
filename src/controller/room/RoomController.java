package controller.room;

import controller.HomeController;
import daopatern.CusDao;
import daopatern.RoomDao;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class RoomController implements Initializable {
    public ComboBox<RoomType> cbRoomType;
    public ComboBox<String> cbStatus;
    public ComboBox<Floor> cbFloor;
    public TextField txtName;
    public TextField txtPrice;
    public TableView<RoomInfo> tbRoom;
    public TableColumn<RoomInfo, Integer> cId;
    public TableColumn<RoomInfo, String> cName;
    public TableColumn<RoomInfo, String> cRoomType;
    public TableColumn<RoomInfo, String> cStatus;
    public TableColumn<RoomInfo, String> cFloor;
    public TableColumn<RoomInfo, Double> cPrice;
    public ComboBox<RoomInfo> rdFind;

    public void unselect(ActionEvent event) {
        tbRoom.getSelectionModel().clearSelection();
        refresh(null);
//        cbFind.getSelectionModel().clearSelection();
        tbRoom.getItems().setAll(RoomDao.getInstance().getAll());
        tbRoom.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cRoomType.setCellValueFactory(new PropertyValueFactory<>("roomtype"));
        cFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        RoomDao rd = RoomDao.getInstance();
        ArrayList<RoomInfo> list = rd.getAll();
        tbRoom.getItems().addAll(list);
        tbRoom.refresh();
        tbRoom.setOnMouseClicked(event -> {
            RoomInfo roomInfoSelect = tbRoom.getSelectionModel().getSelectedItem();
            if (roomInfoSelect != null) {
                txtName.setText(roomInfoSelect.getName());
                cbStatus.setValue(roomInfoSelect.getStatus());
                txtPrice.setText(String.valueOf(roomInfoSelect.getPrice()));
//                cbRoomType.setValue();
            }
        });


        try {
            RoomDao rd1 = RoomDao.getInstance();
            ArrayList<Floor> list2 = rd1.getAllFloor();
            cbFloor.getItems().addAll(list2);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }

        // roomtype
        try {
            RoomDao rd1 = RoomDao.getInstance();
            ArrayList<RoomType> list1 = rd1.getAllRoomType();
            cbRoomType.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }

        try {
            RoomDao roomDao = RoomDao.getInstance();
            ArrayList<RoomInfo> list3 = roomDao.getAll();
            rdFind.getItems().addAll(list3);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }

        ObservableList<String> St = FXCollections.observableArrayList();
        St.add("booked");
        St.add("not booked");
        Collections.sort(St, ((o1, o2) -> o1.compareTo(o2)));
        cbStatus.setItems(St);
    }

    public void gotohome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Home");
    }

    public void gotoInfoCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/customer_info/customer_info.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Customer");
    }

    public void gotoService(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/service/service.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Service");
    }

    public void goToCheckIn(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/checkin/checkin.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
        HomeController.rootStage.setTitle("Check In");
    }

    public void goToBill(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/bills/bills2.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
    }

    public void addRoom(ActionEvent event) {

    }

    public void editRoom(ActionEvent event) {

    }
    //unselect thong tin tren bang
    public void refresh(ActionEvent event) {
        txtName.setText(null);
        cbRoomType.getSelectionModel().clearSelection();
        cbFloor.getSelectionModel().clearSelection();
        cbStatus.getSelectionModel().clearSelection();
        txtPrice.setText(null);
    }

    public void deleteRoom(ActionEvent event) {

    }

    public void searchRoom(ActionEvent event) {
        RoomInfo room = rdFind.getSelectionModel().getSelectedItem();
        if (room == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a user to find.");
            alert.showAndWait();
        } else {
            try {
                Integer id = room.getId();
                RoomDao roomdao = RoomDao.getInstance();
                RoomInfo r = roomdao.find(id);
                ArrayList<RoomInfo> list = new ArrayList<>();
                list.add(r);
                tbRoom.getItems().setAll(list);
                tbRoom.refresh();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
    }
}
