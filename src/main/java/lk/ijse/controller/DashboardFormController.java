package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.controller.util.*;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    private AnchorPane bodyRoot;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane root;
    private final UserBO userBO= (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    @FXML
    void initialize() {
        Thread printThread = new Thread(() -> {
            SessionFactoryConfig.getInstance().getSession();
        });
        printThread.start();
    }
    @FXML
    void activeRentBtnOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.ACTIVE_RENT, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try {
            Navigation.navigation(Rout.CUSTOMER, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void homeBtnOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.HOME, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logOutBtnOnAction(ActionEvent event) {
        if (userBO.getAllUsers() != null){
            try {
                Navigation.navigation(Rout.LOG_IN, root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            PopUpAlerts.popUps(AlertTypes.INFORMATION, "Log Out", "There are no user account. Please create a account for activate this feature !");
        }
    }

    @FXML
    void nwRentBtnOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.NEW_RENT, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void settingsBtnOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.USER, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
