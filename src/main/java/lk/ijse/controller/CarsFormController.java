package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CarBO;
import lk.ijse.controller.util.CustomAlert;
import lk.ijse.controller.util.Validation;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.tm.CarTM;

import java.util.List;

public class CarsFormController {

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> brandClm;

    @FXML
    private Line brandLine;

    @FXML
    private TextField brandTxt;

    @FXML
    private Line carNoLine;

    @FXML
    private TextField carNoTxt;

    @FXML
    private TableView<CarTM> carTbl;

    @FXML
    private ComboBox<String> categoryCmbBox;

    @FXML
    private Button deleteBtn;

    @FXML
    private Line depositeLine;

    @FXML
    private TextField depositeTxt;

    @FXML
    private TableColumn<?, ?> idColm;

    @FXML
    private Label idLbl;

    @FXML
    private TableColumn<?, ?> modelClm;

    @FXML
    private Line modelLine;

    @FXML
    private TextField modelTxt;

    @FXML
    private TableColumn<?, ?> noClm;

    @FXML
    private TableColumn<?, ?> rateClm;

    @FXML
    private Line rateLine;

    @FXML
    private TextField rateTxt;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<?, ?> stsClm;

    @FXML
    private Button svBtn;

    @FXML
    private Button upBtn;

    @FXML
    private Line yearLine;

    @FXML
    private TextField yearTxt;
    private final CarBO carBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CAR);

    boolean carNo,brand,model,year,rate, deposit,category;

    @FXML
    void brandTxtOnAction(ActionEvent event) {
        modelTxt.requestFocus();
    }

    @FXML
    void carNoTxtOnAction(ActionEvent event) {
        brandTxt.requestFocus();
    }

    @FXML
    void depositeTxtOnAction(ActionEvent event) {
        svBtn.fire();
    }

    @FXML
    void modelTxtOnAction(ActionEvent event) {
        yearTxt.requestFocus();
    }

    @FXML
    void rateTxtOnAction(ActionEvent event) {
        categoryCmbBox.requestFocus();
    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {
        searchBtn.fire();
    }

    @FXML
    void yearTxtOnAction(ActionEvent event) {
        rateTxt.requestFocus();
    }

    @FXML
    void svBtnOnAction(ActionEvent event) {
        if(validation()){
            if (carBO.addCar(new CarDto(
                    idLbl.getText(),
                    carNoTxt.getText(),
                    brandTxt.getText(),
                    modelTxt.getText(),
                    Integer.parseInt(yearTxt.getText()),
                    Double.parseDouble(rateTxt.getText()),
                    categoryCmbBox.getSelectionModel().getSelectedItem(),
                    true,
                    Double.parseDouble(depositeTxt.getText())

            ))){
                new Alert(Alert.AlertType.CONFIRMATION,"Car Added Successfully").show();
                initUi();
                fillTable();
            }else {
                new Alert(Alert.AlertType.WARNING,"Car Added Failed").show();
            }
        }
    }

    @FXML
    void upBtnOnAction(ActionEvent event) {
        if(validation()){
            if (carBO.updateCar(new CarDto(
                    idLbl.getText(),
                    carNoTxt.getText(),
                    brandTxt.getText(),
                    modelTxt.getText(),
                    Integer.parseInt(yearTxt.getText()),
                    Double.parseDouble(rateTxt.getText()),
                    categoryCmbBox.getSelectionModel().getSelectedItem(),
                    true,
                    Double.parseDouble(depositeTxt.getText())

            ))){
                new CustomAlert(Alert.AlertType.CONFIRMATION, "Update ", "Updated !", "Car Update successful !").show();
                initUi();
                fillTable();
            }else {
                new CustomAlert(Alert.AlertType.ERROR, "Update ", "Not Update !", "Update not successful !").show();
            }
        }
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        if (carBO.deleteCar(new CarDto(
                idLbl.getText(),
                carNoTxt.getText(),
                brandTxt.getText(),
                modelTxt.getText(),
                Integer.parseInt(yearTxt.getText()),
                Double.parseDouble(rateTxt.getText()),
                categoryCmbBox.getSelectionModel().getSelectedItem(),
                true,
                Double.parseDouble(depositeTxt.getText())
        ))){
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","Car Deleted","Car Delete Successfully!").show();
            fillTable();
            initUi();
        }else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","Car Not Deleted","Car Not Delete Successfully!").show();
        }
    }

    @FXML
    void searchBtnOnAction(ActionEvent event) {
        CarDto carDto=carBO.getCar(searchTxt.getText());
        if(carDto!=null){
            idLbl.setText(carDto.getId());
            carNoTxt.setText(carDto.getNumber());
            brandTxt.setText(carDto.getBrand());
            modelTxt.setText(carDto.getModel());
            yearTxt.setText(String.valueOf(carDto.getYear()));
            rateTxt.setText(String.valueOf(carDto.getRate()));
            categoryCmbBox.getSelectionModel().select(getCmbIndex(categoryCmbBox,carDto.getCatId()));
            depositeTxt.setText(String.valueOf(carDto.getDepositAmount()));
            upBtn.setDisable(false);
            deleteBtn.setDisable(false);
            carNoTxt.setDisable(false);
            brandTxt.setDisable(false);
            modelTxt.setDisable(false);
            yearTxt.setDisable(false);
            rateTxt.setDisable(false);
            depositeTxt.setDisable(false);
            categoryCmbBox.setDisable(false);
            svBtn.setDisable(true);
        }else {
            new CustomAlert(Alert.AlertType.ERROR,"Search","Car Not Found","Car Not Found!").show();
        }
        searchTxt.clear();
    }
    @FXML
    void addNewBtnOnAction(ActionEvent event) {
        carNoTxt.setDisable(false);
        brandTxt.setDisable(false);
        modelTxt.setDisable(false);
        yearTxt.setDisable(false);
        rateTxt.setDisable(false);
        depositeTxt.setDisable(false);
        categoryCmbBox.setDisable(false);
        svBtn.setDisable(false);

        setCarId();
    }
    @FXML
    void initialize() {
        setCellValueFactory();
        fillTable();
        initUi();
        setCategory();
    }
    private void setCarId() {
        idLbl.setText(carBO.getNewCarId());
    }
    private void setCategory() {
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll(carBO.getCategories());
        categoryCmbBox.setItems(categories);
    }

    private void initUi() {
        idLbl.setText("");
        carNoTxt.clear();
        brandTxt.clear();
        modelTxt.clear();
        yearTxt.clear();
        rateTxt.clear();
        depositeTxt.clear();
        carNoTxt.setDisable(true);
        brandTxt.setDisable(true);
        modelTxt.setDisable(true);
        yearTxt.setDisable(true);
        rateTxt.setDisable(true);
        depositeTxt.setDisable(true);
        categoryCmbBox.setDisable(true);
        svBtn.setDisable(true);
        upBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    private void fillTable() {
        ObservableList<CarTM> carTMS = FXCollections.observableArrayList();
        for (CarDto carDto : carBO.getAll()) {
            carTMS.add(new CarTM(
                    carDto.getId(),
                    carDto.getNumber(),
                    carDto.getBrand(),
                    carDto.getModel(),
                    carDto.getRate(),
                    carDto.getIsRentable()
            ));
        }
        carTbl.setItems(carTMS);
    }

    private void setCellValueFactory() {
        idColm.setCellValueFactory(new PropertyValueFactory<>("id"));
        noClm.setCellValueFactory(new PropertyValueFactory<>("number"));
        brandClm.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelClm.setCellValueFactory(new PropertyValueFactory<>("model"));
        rateClm.setCellValueFactory(new PropertyValueFactory<>("rate"));
        stsClm.setCellValueFactory(new PropertyValueFactory<>("toRent"));
    }
    private boolean validation(){
        carNo=false;
        brand=false;
        model=false;
        year=false;
        rate=false;
        deposit=false;
        category=false;

        carNo= Validation.txtValidation(carNoTxt,carNoLine);
        brand=Validation.txtValidation(brandTxt,brandLine);
        model=Validation.txtValidation(modelTxt,modelLine);
        year=Validation.txtValidation(yearTxt,yearLine);
        year=Validation.numberValidation(yearTxt,yearLine);
        rate=Validation.txtValidation(rateTxt,rateLine);
        rate=Validation.moneyValidation(rateTxt,rateLine);
        deposit=Validation.txtValidation(depositeTxt,depositeLine);
        deposit=Validation.moneyValidation(depositeTxt,depositeLine);
        category=Validation.comboValidation(categoryCmbBox);
        return carNo && brand && model && year && rate && deposit && category;
    }
    int getCmbIndex(ComboBox<String> cmb, String value) {
        List<String> cmbList = cmb.getItems();
        for (int i = 0; i < cmbList.size(); i++) {
            if (cmbList.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
