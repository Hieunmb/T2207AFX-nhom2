package controller.customer_info;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerInfoController implements Initializable {

    public ComboBox<String> cbGender;
    public ComboBox<String> cbNationality;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> gt = FXCollections.observableArrayList();
        gt.add("Male");
        gt.add("Female");
        cbGender.setItems(gt);

        ObservableList<String> qt = FXCollections.observableArrayList();
        //Bắc Âu
        qt.add("Danish");
        qt.add("British / English");
        qt.add("Estonian");
        qt.add("Finnish");
        qt.add("Icelandic");
        qt.add("Irish");
        qt.add("Latvian");
        qt.add("Lithuanian");
        qt.add("British / Northern Irish");
        qt.add("Norwegian");
        qt.add("British / Scottish");
        qt.add("Swedish");
        qt.add("British");
        qt.add("British / Welsh");
        //Tây Âu
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Nam Âu
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Đông Âu
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Bắc Mỹ
        qt.add("");
        qt.add("");
        qt.add("");
        //Trung Mỹ và Ca-ri-bê
        qt.add("");
        qt.add("");
        qt.add("");
        //Nam Mỹ
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Tây Á
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Nam và Trung Á
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Đông Á
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Đông Nam Á
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Châu Úc và Thái Bình Dương
        qt.add("");
        qt.add("");
        qt.add("");
        //Bắc và Tây Phi
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Đông Phi
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        //Nam và Trung Phi
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        qt.add("");
        cbNationality.setItems(qt);
    }
}
