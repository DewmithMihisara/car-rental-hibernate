package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Line;

public class RentFormController {
    @FXML
    private TextField AdvancedTxt;

    @FXML
    private ComboBox<?> CusIdCmb;

    @FXML
    private Line DepoLine;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> advancedClm;

    @FXML
    private Line advancedLine;

    @FXML
    private Label availableLbl;

    @FXML
    private TableColumn<?, ?> balanceClm;

    @FXML
    private ComboBox<?> carIdCmb;

    @FXML
    private TableColumn<?, ?> carNoClm;

    @FXML
    private TextField carNoTxt1;

    @FXML
    private Label cusNameLbl;

    @FXML
    private TableColumn<?, ?> cusNameClm;

    @FXML
    private TableColumn<?, ?> depoClm;

    @FXML
    private DatePicker endDateDatePicker;

    @FXML
    private TableView<?> newRentTbl;

    @FXML
    private TableColumn<?, ?> nosDaysClm;

    @FXML
    private TableColumn<?, ?> rateClm;

    @FXML
    private Button rentBtn;

    @FXML
    private DatePicker stDateDatePicker;

    @FXML
    private TableColumn<?, ?> ttlClm;

    @FXML
    void AdvancedTxtOnAction(ActionEvent event) {

    }

    @FXML
    void CusIdCmbOnAction(ActionEvent event) {

    }

    @FXML
    void DepoAmountTxtOnAction(ActionEvent event) {

    }

    @FXML
    void addNewBtnOnAction(ActionEvent event) {

    }

    @FXML
    void carIdCmbOnAction(ActionEvent event) {

    }

    @FXML
    void endDateDatePickerOnAction(ActionEvent event) {

    }

    @FXML
    void rentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void stDateDatePickerOnAction(ActionEvent event) {

    }

}
