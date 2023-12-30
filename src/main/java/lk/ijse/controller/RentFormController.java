package lk.ijse.controller;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Callback;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CarBO;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.RentBO;
import lk.ijse.controller.util.AlertTypes;
import lk.ijse.controller.util.PopUpAlerts;
import lk.ijse.controller.util.Validation;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.RentDto;
import lk.ijse.dto.tm.RentTM;

import java.time.LocalDate;
import java.util.List;

public class RentFormController {
    @FXML
    private TextField AdvancedTxt;

    @FXML
    private ComboBox<String> CusIdCmb;

    @FXML
    private Line DepoLine;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> advancedClm;

    @FXML
    private Line advancedLine;
    @FXML
    private Label aligibleLbl;

    @FXML
    private Label availableLbl;

    @FXML
    private TableColumn<?, ?> balanceClm;

    @FXML
    private ComboBox<String> carIdCmb;

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
    private TableView<RentTM> newRentTbl;

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
    private final CarBO carBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CAR);
    private final CustomerBO customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    private final RentBO rentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RENT);
    boolean stDate, endDate, cusId, carId, advanced;

    String id, advance, depo, cus, carCmb;
    LocalDate stDt, endDt;
    {
        id=null;
        advance=null;
        depo=null;
        cus=null;
        carCmb=null;
        stDt=null;
        endDt=null;
    }

    @FXML
    void AdvancedTxtOnAction(ActionEvent event) {
        addBtn.requestFocus();
    }

    @FXML
    void CusIdCmbOnAction(ActionEvent event) {
        CustomerDto customer=customerBO.getCustomer(CusIdCmb.getSelectionModel().getSelectedItem());
        if (customer!=null){
            cusNameLbl.setVisible(true);
            cusNameLbl.setText(customer.getFirstName()+" "+customer.getLastName());
            if (customer.getToReturn()==null){
                aligibleLbl.setVisible(true);
                aligibleLbl.setText("Yes");
            }else {
                aligibleLbl.setVisible(true);
                aligibleLbl.setText("No");
            }
        }
    }

    @FXML
    void addNewBtnOnAction(ActionEvent event) {
        if (validation()){
            if (isCanCus()){
                if (isCanCar()){
                    if(advanceMatch()){
                        CarDto car=carBO.getCar(carIdCmb.getSelectionModel().getSelectedItem());
                        CustomerDto customer=customerBO.getCustomer(CusIdCmb.getSelectionModel().getSelectedItem());
                        long nosDates=endDateDatePicker.getValue().toEpochDay()-stDateDatePicker.getValue().toEpochDay();
                        double total=car.getRate()*nosDates;

                        id=rentBO.getNewCarId();
                        stDt=stDateDatePicker.getValue();
                        endDt=endDateDatePicker.getValue();
                        advance=AdvancedTxt.getText();
                        depo = carNoTxt1.getText();
                        cus = CusIdCmb.getSelectionModel().getSelectedItem();
                        carCmb = carIdCmb.getSelectionModel().getSelectedItem();

                        ObservableList<RentTM>observableList= FXCollections.observableArrayList();
                        observableList.add(new RentTM(
                                id,
                                customer.getFirstName() + " " + customer.getLastName(),
                                car.getNumber(),
                                String.valueOf(car.getRate()),
                                String.valueOf(nosDates),
                                AdvancedTxt.getText(),
                                carNoTxt1.getText(),
                                String.valueOf(total),
                                String .valueOf(total-Double.parseDouble(AdvancedTxt.getText()))
                        ));
                        newRentTbl.setItems(observableList);
//                        initUi();
                        if (newRentTbl.getItems() != null && !newRentTbl.getItems().isEmpty()) {
                            rentBtn.setDisable(false);
                            addBtn.setDisable(true);
                        }
                    }
                }
            }
        }
    }

    @FXML
    void carIdCmbOnAction(ActionEvent event) {
        CarDto car=carBO.getCar(carIdCmb.getSelectionModel().getSelectedItem());
        if (car!=null){
            if (car.getIsRentable()){
                availableLbl.setVisible(true);
                availableLbl.setText("Yes");
                rentBtn.setDisable(false);
                carNoTxt1.setText(car.getDepositAmount().toString());
            }else {
                availableLbl.setVisible(true);
                availableLbl.setText("No");
                rentBtn.setDisable(true);
            }
        }
    }

    @FXML
    void endDateDatePickerOnAction(ActionEvent event) {
        AdvancedTxt.requestFocus();
    }

    @FXML
    void rentBtnOnAction(ActionEvent event) {
        CarDto car=carBO.getCar(carCmb);
        if (rentBO.placeRent(new RentDto(
                id,
                LocalDate.now(),
                stDt,
                endDt,
                Double.parseDouble(advance),
                Double.parseDouble(depo),
                cus,
                car.getCatId(),
                car.getNumber(),
                car.getRate(),
                car.getRate()*(stDt.toEpochDay()-endDt.toEpochDay()),
                true
        ))){
            PopUpAlerts.popUps(AlertTypes.CONFORMATION, "Rent", "CRent Placed Successfully!");
            initUi();
            setNull();
        }else {
            PopUpAlerts.popUps(AlertTypes.ERROR, "Rent", "Rent Not Placed!");
            setNull();
        }
    }
    void setNull(){
        id=null;
        advance=null;
        depo=null;
        cus=null;
        carCmb=null;
        stDt=null;
        endDt=null;
    }

    @FXML
    void stDateDatePickerOnAction(ActionEvent event) {
        endDateDatePicker.setDisable(false);
        setEndDate();
    }

    @FXML
    void initialize(){
        initUi();
        setStartDate();
        setValueFactory();
    }

    private void setStartDate() {
        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }
                };
            }
        };
        stDateDatePicker.setDayCellFactory(callB);
    }
    private void setEndDate() {
        LocalDate startDate = stDateDatePicker.getValue();
        if (startDate != null) {
            endDateDatePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isBefore(startDate) || item.isAfter(startDate.plusDays(29)));
                }
            });
        }
    }

    private void loadCarIds() {
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll(carBO.getIds());
        carIdCmb.setItems(categories);
    }

    private void loadCusIds() {
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll(customerBO.getIds());
        CusIdCmb.setItems(categories);
    }

    private void initUi() {
        stDateDatePicker.getEditor().clear();
        endDateDatePicker.getEditor().clear();
        AdvancedTxt.setText("");
        carNoTxt1.setText("");
        cusNameLbl.setText("");
        availableLbl.setText("");
        aligibleLbl.setText("");
        loadCusIds();
        loadCarIds();
        carIdCmb.getSelectionModel().clearSelection();
        CusIdCmb.getSelectionModel().clearSelection();
        cusNameLbl.setVisible(false);
        availableLbl.setVisible(false);
        rentBtn.setDisable(true);
        aligibleLbl.setVisible(false);
        carNoTxt1.setDisable(true);
        endDateDatePicker.setDisable(true);
        addBtn.setDisable(false);
        clearTable();
    }
    private void clearTable() {
        try {
            ObservableList<RentTM> dataList = newRentTbl.getItems();
            dataList.clear();
        } catch (Exception e) {
            throw e;
        }

    }
    private boolean validation() {
        stDate=false;
        endDate=false;
        cusId=false;
        carId=false;
        advanced=false;

        stDate= Validation.dateValidation(stDateDatePicker);
        endDate=Validation.dateValidation(endDateDatePicker);
        carId=Validation.comboValidation(carIdCmb);
        cusId=Validation.comboValidation(CusIdCmb);
        advanced=Validation.moneyValidation(AdvancedTxt,advancedLine);

        return stDate && endDate && carId && cusId && advanced;
    }
    private boolean isCanCus() {
        if (aligibleLbl.getText().equals("Yes")) {
            return true;
        }else {
            error(aligibleLbl);
            return false;
        }
    }
    private boolean isCanCar() {
        if (availableLbl.getText().equals("Yes")) {
            return true;
        }else {
            error(availableLbl);
            return false;
        }
    }
    private boolean advanceMatch() {
        if (Double.parseDouble(AdvancedTxt.getText())>=Double.parseDouble(carNoTxt1.getText())){
            Validation.shakeLine(advancedLine);
            return false;
        }else {
            return true;
        }
    }
    private void setValueFactory() {
        cusNameClm.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        carNoClm.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        rateClm.setCellValueFactory(new PropertyValueFactory<>("rate"));
        nosDaysClm.setCellValueFactory(new PropertyValueFactory<>("nosDays"));
        advancedClm.setCellValueFactory(new PropertyValueFactory<>("advance"));
        depoClm.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        ttlClm.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        balanceClm.setCellValueFactory(new PropertyValueFactory<>("balanceToPay"));
    }

    private void error(Label label){
        label.setStyle("-fx-text-fill: red");
        Shake shake=new Shake(label);
        shake.setOnFinished(actionEvent -> {
            def(label);
        });
        shake.play();
    }
    public void def(Label label){
        label.setStyle("-fx-text-fill: black");
    }
}
