package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.Rout;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    private AnchorPane bodyRoot;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane root;

    @FXML
    void activeRentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void carBtnOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.CAR, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void carCategoryBtnOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.CAR_CATEGORY, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cusBtnOnAction(ActionEvent event) {

    }

    @FXML
    void homeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void logOutBtnOnAction(ActionEvent event) {

    }

    @FXML
    void nwRentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void settingsBtnOnAction(ActionEvent event) {

    }

}
