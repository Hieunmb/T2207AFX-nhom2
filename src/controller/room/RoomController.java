package controller.room;

import controller.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class RoomController implements Initializable {
    public ComboBox<String> KindRoom;
    public ComboBox<String> StaffId ;
    public ComboBox<String> RoomType ;
    public ComboBox<String> Status ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> Kr = FXCollections.observableArrayList();
        Kr.add("1-medium");
        Kr.add("2-standard");
        Kr.add("3-trader");
        Kr.add("4-high-class");
        Collections.sort(Kr, ((o1, o2) -> o1.compareTo(o2)));
        KindRoom.setItems(Kr);

        ObservableList<String> Sid = FXCollections.observableArrayList();
        Sid.add("1-Staff");
        Sid.add("2-Staff");
        Sid.add("3-Staff");
        Sid.add("4-Staff");
        Sid.add("5-Staff");
        Sid.add("6-Staff");
        Collections.sort(Sid, ((o1, o2) -> o1.compareTo(o2)));
        StaffId.setItems(Sid);

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
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }
}
