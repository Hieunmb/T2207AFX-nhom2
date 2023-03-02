package controller.service;

import controller.HomeController;
import daopatern.CusDao;
import daopatern.ServiceDao;
import entities.Customer;
import entities.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServiceController implements Initializable {

    public TableView<Service> tbS;
    public TableColumn<Service, String> sName;
    public TableColumn<Service, String> sPrice;
    public TableColumn<Service, String> sId;
    public TextField txtId;
    public TextField txtPrice;
    public TextField txtName;
    public ComboBox<Service> sFind;
    public Button addBtn;

    public void goToHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home1.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
    }

    public void goToCheckIn(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/checkin/checkin.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
    }

    public void goToRoom(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/room/room.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
    }

    public void goToBill(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/bills/bills2.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
    }

    public void goToCustomerInfo(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/customer_info/customer_info.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 1200, 720));
    }

    public void add(ActionEvent event) {
        try {
            String name = txtName.getText();
            String price = txtPrice.getText();
            if (name.isEmpty() || price.isEmpty()) {
                throw new Exception("Please complete all information");
            }
            Service c1 = new Service(null, name, price);
            ServiceDao cd = ServiceDao.getInstance();
            cd.create(c1);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        refreshForm(null);
        tbS.getItems().setAll(ServiceDao.getInstance().getAll());
        tbS.refresh();
        sFind.getItems().setAll(ServiceDao.getInstance().getAll());
    }

    @FXML
    private void refreshForm(ActionEvent event) {
        txtName.clear();
        txtId.clear();
        txtPrice.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sName.setCellValueFactory(new PropertyValueFactory<>("name"));
        sId.setCellValueFactory(new PropertyValueFactory<>("id"));
        sPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ServiceDao cd = ServiceDao.getInstance();
        ArrayList<Service> list = cd.getAll();
        tbS.getItems().addAll(list);
        tbS.refresh();
        tbS.setOnMouseClicked(event -> {
            Service selectedService = tbS.getSelectionModel().getSelectedItem();
            if (selectedService != null) {
                txtName.setText(selectedService.getName());
                txtId.setText(selectedService.getId());
                txtPrice.setText(selectedService.getPrice());
                addBtn.setDisable(true);
            }
        });
        try {
            ServiceDao serviceDao = ServiceDao.getInstance();
            ArrayList<Service> list1 = serviceDao.getAll();
            sFind.getItems().addAll(list1);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    public void edit(ActionEvent event) {
        Service service = tbS.getSelectionModel().getSelectedItem();
        if (service == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a user to edit.");
            alert.showAndWait();
        } else {
            try {
                service.setName(txtName.getText());
                service.setId(txtId.getText());
                service.setPrice(txtPrice.getText());

                if (service.getName().isEmpty() || service.getId().isEmpty() || service.getPrice().isEmpty()) {
                    throw new Exception("Please complete all information");
                }

                ServiceDao cd = ServiceDao.getInstance();
                cd.update(service);
                refreshForm(null);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Edit service");
                alert.setContentText("Edit success!");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
        tbS.getItems().setAll(ServiceDao.getInstance().getAll());
        tbS.refresh();

    }


    public void delete(ActionEvent event) {
        Service service = tbS.getSelectionModel().getSelectedItem();
        if (service == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a user to delete.");
            alert.showAndWait();
        } else {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm delete");
            confirm.setHeaderText("Confirm delete");
            confirm.setContentText("Are you sure you want to delete this user?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    ServiceDao cd = ServiceDao.getInstance();
                    cd.delete(service);
                    refreshForm(null);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(e.getMessage());
                    alert.show();
                }
            }
        }
        tbS.getItems().setAll(ServiceDao.getInstance().getAll());
        tbS.refresh();
        sFind.getItems().setAll(ServiceDao.getInstance().getAll());
    }

    public void search(ActionEvent event) {
        Service service = sFind.getSelectionModel().getSelectedItem();
        if (service == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a user to find.");
            alert.showAndWait();
        } else {
            try {
                String id = service.getId();
                ServiceDao serviceDao = ServiceDao.getInstance();
                Service c = serviceDao.finds(id);
                ArrayList<Service> list = new ArrayList<>();
                list.add(c);
                tbS.getItems().setAll(list);
                tbS.refresh();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
    }

    public void unselect(ActionEvent event) {
        tbS.getSelectionModel().clearSelection();
        refreshForm(null);
        sFind.getSelectionModel().clearSelection();
        tbS.getItems().setAll(ServiceDao.getInstance().getAll());
        tbS.refresh();
        addBtn.setDisable(false);
    }
}