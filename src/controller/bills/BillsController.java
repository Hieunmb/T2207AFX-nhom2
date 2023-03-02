package controller.bills;

import controller.HomeController;
import daopatern.BillsDao;
import daopatern.CusDao;
import entities.Bills;
import entities.Customer;
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
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class BillsController implements Initializable {
    public ComboBox<Customer> cbCustomer;
    public TextField txtPrice;
    public DatePicker dpCheckOutDate;
    public ComboBox<String> cbPayment;
    public TableView<Bills> tbBill;
    public TableColumn<Bills, Integer> cID;
    public TableColumn<Bills, Integer> cCusID;
    public TableColumn<Bills, Double> cPrice;
    public TableColumn<Bills, Date> cCheOutDate;
    public TableColumn<Bills, String> cPayM;
    public ComboBox<Bills> cbFind;

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
        cCusID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cPayM.setCellValueFactory(new PropertyValueFactory<>("payment"));
        cCheOutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutdate"));

        BillsDao billsDao = BillsDao.getInstance();
        ArrayList<Bills> list = billsDao.getAll();
        tbBill.getItems().addAll(list);
        tbBill.refresh();
        tbBill.setOnMouseClicked(event -> {
            Bills selectedBills = tbBill.getSelectionModel().getSelectedItem();
            if (selectedBills != null){
                Customer customer = CusDao.getInstance().find(selectedBills.getCustomer_id());
                cbCustomer.setValue(customer);
                txtPrice.setText(String.valueOf(selectedBills.getPrice()));
                cbPayment.setValue(selectedBills.getPayment());
                dpCheckOutDate.setValue(selectedBills.getCheckoutdate().toLocalDate());
            }
        });

        try {
            CusDao cd = CusDao.getInstance();
            ArrayList<Customer> list2 = cd.getAll();
            cbCustomer.getItems().addAll(list2);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }

        try {
            BillsDao bd1 = BillsDao.getInstance();
            ArrayList<Bills> list1 = bd1.getAll();
            cbFind.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }



        ObservableList<String> pm = FXCollections.observableArrayList();
        pm.add("Cash");
        pm.add("Credit");
        pm.add("Transfer");
        Collections.sort(pm, ((o1, o2) -> o1.compareTo(o2)));
        cbPayment.setItems(pm);
    }

    public void searchBill(ActionEvent event) {
        Bills bills = cbFind.getSelectionModel().getSelectedItem();
        if(bills == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Waring");
            alert.setContentText("Please select bills infor");
            alert.showAndWait();
        }else {
            try {
                Integer id = bills.getId();
                BillsDao billsDao = BillsDao.getInstance();
                Bills b = billsDao.find(id);
                ArrayList<Bills> list = new ArrayList<>();
                list.add(b);
                tbBill.getItems().setAll(list);
                tbBill.refresh();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
    }

    public void addBill(ActionEvent event) {
        try {
            Integer customer_id = cbCustomer.getValue().getId();
            Double price = Double.valueOf(txtPrice.getText());
            String payment = cbPayment.getValue();
            Date checkoutDate = Date.valueOf(dpCheckOutDate.getValue());
            if(customer_id == null || price == null || payment == null || checkoutDate == null) {
                throw new Exception("Please complete all information");
            }

            Bills bills1 = new Bills( null, customer_id, price, payment, checkoutDate);
            BillsDao bd = BillsDao.getInstance();
            bd.create(bills1);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        refrehForm(null);
        tbBill.getItems().setAll(BillsDao.getInstance().getAll());
        tbBill.refresh();
    }

    public void editBill(ActionEvent event) {
        Bills bill = tbBill.getSelectionModel().getSelectedItem();
        if(bill == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a user to edit.");
            alert.showAndWait();
        }else {
            try{
                bill.setCustomer_id(cbCustomer.getValue().getId());
                bill.setPrice(Double.valueOf(txtPrice.getText()));
                bill.setPayment(cbPayment.getValue());
                bill.setCheckoutdate(Date.valueOf(dpCheckOutDate.getValue()));
//                if(bill.getCustomer_id() == null || bill.getPrice() == null || bill.getPayment() == null || bill.getCheckoutdate() = null){
//                    throw new Exception("Please complete all information");
//                }

                BillsDao bd = BillsDao.getInstance();
                Bills bills = bd.find(bill.getId());
                bd.update(bills);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Edit bills");
                alert.setContentText("Edit cuccess!");
                alert.show();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
    }

    public void deleteBill(ActionEvent event) {
        Bills bill = tbBill.getSelectionModel().getSelectedItem();
        if (bill == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a bill to edit.");
            alert.showAndWait();
        } else {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm delete");
            confirm.setHeaderText("Confirm delete");
            confirm.setContentText("Are you sure you want to delete this checkin?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try{
                    BillsDao bd = BillsDao.getInstance();
                    bd.delete(bill);
                    refrehForm(null);
                }catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(e.getMessage());
                    alert.show();
                }
            }
            tbBill.getItems().setAll(BillsDao.getInstance().getAll());
            tbBill.refresh();
        }
    }

    public void refrehForm(ActionEvent event) {
        cbCustomer.setValue(null);
        txtPrice.clear();
        cbPayment.getSelectionModel().clearSelection();
        dpCheckOutDate.setValue(null);
    }

    public void Print(ActionEvent event) {
    }

    public void unselect(ActionEvent event) {
        tbBill.getSelectionModel().clearSelection();
        refrehForm(null);
        cbFind.getSelectionModel().clearSelection();
        tbBill.getItems().setAll(BillsDao.getInstance().getAll());
        tbBill.refresh();
    }
}
