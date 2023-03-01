package controller.room;

import controller.HomeController;
import daopatern.CusDao;
import daopatern.RoomDao;
import entities.Customer;
import entities.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class RoomController implements Initializable {
    public ComboBox<String> RoomType ;
    public ComboBox<String> Status ;
    public ComboBox<String> Floor;
    public TableColumn<Room, String> rName;
    public TableColumn<Room , String> rRoomType;
    public TableColumn<Room , String> rStatus;
    public TableColumn<Room , Integer> rRoomRates;
    public TableColumn<Room , String> rFloor;
    public TableView<Room> tbRoom;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rName.setCellValueFactory(new PropertyValueFactory<>("name"));
        rRoomType.setCellValueFactory(new PropertyValueFactory<>("room type"));
        rStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        rRoomRates.setCellValueFactory(new PropertyValueFactory<>("room rates"));
        rFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));

        RoomDao rd = RoomDao.getInstance();
        ArrayList<Room> list =rd.getAll();
        tbRoom.getItems().addAll(list);
        tbRoom.refresh();


        ObservableList<String> Fl = FXCollections.observableArrayList();
        Fl.add("1");
        Fl.add("2");
        Fl.add("3");
        Fl.add("4");
        Fl.add("5");
        Fl.add("6");
        Fl.add("7");
        Collections.sort(Fl, ((o1, o2) -> o1.compareTo(o2)));
        Floor.setItems(Fl);

        ObservableList<String> Rt = FXCollections.observableArrayList();
        Rt.add("1-double bed");
        Rt.add("2-single bed");
        Collections.sort(Rt, ((o1, o2) -> o1.compareTo(o2)));
        RoomType.setItems(Rt);

        ObservableList<String> St = FXCollections.observableArrayList();
        St.add("1-empty");
        St.add("2-full");
        St.add("3-maintenance");
        Collections.sort(St, ((o1, o2) -> o1.compareTo(o2)));
        Status.setItems(St);
    }

    public void gotohome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }

    public void gotoInfoCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/customer_info/customer_info.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }

    public void gotoService(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/service/service.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }

    public void gotoBills(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/bills/bills.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }
}
