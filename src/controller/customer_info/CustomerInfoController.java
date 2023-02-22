package controller.customer_info;

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
        qt.add("Denmark");
        qt.add("England");
        qt.add("Estonia");
        qt.add("Finland");
        qt.add("Iceland");
        qt.add("Ireland");
        qt.add("Latvia");
        qt.add("Lithuania");
        qt.add("Northern Ireland");
        qt.add("Norway");
        qt.add("Scotland");
        qt.add("Sweden");
        qt.add("United Kingdom");
        qt.add("Wales");
        //Tây Âu
        qt.add("Austria");
        qt.add("Belgium");
        qt.add("France");
        qt.add("Germany");
        qt.add("Netherlands");
        qt.add("Switzerland");
        //Nam Âu
        qt.add("Albania");
        qt.add("Croatia");
        qt.add("Cyprus");
        qt.add("Greece");
        qt.add("Italy");
        qt.add("Portugal");
        qt.add("Serbia");
        qt.add("Slovenia");
        qt.add("Spain");
        //Đông Âu
        qt.add("Belarus");
        qt.add("Bulgaria");
        qt.add("Czech Republic");
        qt.add("Hungary");
        qt.add("Poland");
        qt.add("Romania");
        qt.add("Russia");
        qt.add("Slovakia");
        qt.add("Ukraine");
        //Bắc Mỹ
        qt.add("Canada");
        qt.add("Mexico");
        qt.add("United States");
        //Trung Mỹ và Ca-ri-bê
        qt.add("Cuba");
        qt.add("Guatemala");
        qt.add("Jamaica");
        //Nam Mỹ
        qt.add("Argentina");
        qt.add("Bolivia");
        qt.add("Brazil");
        qt.add("Chile");
        qt.add("Colombia");
        qt.add("Ecuador");
        qt.add("Paraguay");
        qt.add("Peru");
        qt.add("Uruguay");
        qt.add("Venezuela");
        //Tây Á
        qt.add("Georgia");
        qt.add("Iran");
        qt.add("Iraq");
        qt.add("Israel");
        qt.add("Jordan");
        qt.add("Kuwait");
        qt.add("Lebanon");
        qt.add("Palestinian Territories");
        qt.add("Saudi Arabia");
        qt.add("Syria");
        qt.add("Turkey");
        qt.add("Yemen");
        //Nam và Trung Á
        qt.add("Afghanistan");
        qt.add("Bangladesh");
        qt.add("India");
        qt.add("Kazakhstan");
        qt.add("Nepal");
        qt.add("Pakistan");
        qt.add("Sri Lanka");
        //Đông Á
        qt.add("China");
        qt.add("Japan");
        qt.add("Mongolia");
        qt.add("North Korea");
        qt.add("South Korea");
        qt.add("Taiwan");
        //Đông Nam Á
        qt.add("Cambodia");
        qt.add("Indonesia");
        qt.add("Laos");
        qt.add("Malaysia");
        qt.add("Myanmar");
        qt.add("Philippines");
        qt.add("Singapore");
        qt.add("Thailand");
        qt.add("Vietnam");
        //Châu Úc và Thái Bình Dương
        qt.add("Australia");
        qt.add("Fiji");
        qt.add("New Zealand");
        //Bắc và Tây Phi
        qt.add("Algeria");
        qt.add("Egypt");
        qt.add("Ghana");
        qt.add("Ivory Coast");
        qt.add("Libya");
        qt.add("Morocco");
        qt.add("Nigeria");
        qt.add("Tunisia");
        //Đông Phi
        qt.add("Ethiopia");
        qt.add("Kenya");
        qt.add("Somalia");
        qt.add("Sudan");
        qt.add("Tanzania");
        qt.add("Uganda");
        //Nam và Trung Phi
        qt.add("Angola");
        qt.add("Botswana");
        qt.add("Democratic Republic of the Congo");
        qt.add("Madagascar");
        qt.add("Mozambique");
        qt.add("Namibia");
        qt.add("South Africa");
        qt.add("Zambia");
        qt.add("Zimbabwe");
        cbNationality.setItems(qt);
    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/home1.fxml"));
        HomeController.rootStage.setScene(new Scene(root,1200,720));
    }
}
