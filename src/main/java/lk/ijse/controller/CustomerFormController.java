package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.controller.util.CustomAlert;
import lk.ijse.controller.util.Validation;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerTM;

public class CustomerFormController {
    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> addressColm;

    @FXML
    private Line cityLine;

    @FXML
    private TextField cityTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private Line destrictLine;

    @FXML
    private TextField districtTxt;

    @FXML
    private Line emailLine;

    @FXML
    private TextField emailTxt;

    @FXML
    private Line firstNameLine;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TableColumn<?, ?> idColm;

    @FXML
    private Label idLbl;

    @FXML
    private Line lastNameLine;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TableColumn<?, ?> mobileColm;

    @FXML
    private Line mobileLine;
    @FXML
    private TableColumn<?, ?> mailClm;

    @FXML
    private TextField mobileTxt;

    @FXML
    private Line postalCodeLine;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTxt;

    @FXML
    private Line streetLine;

    @FXML
    private TextField streetTxt;
    @FXML
    private TableColumn<?, ?> nameClm;

    @FXML
    private Button svBtn;

    @FXML
    private Button upBtn;
    @FXML
    private TableView<CustomerTM> custTbl;

    @FXML
    private Line usrNameLine;

    @FXML
    private TextField usrNameTxt;
    private final CustomerBO customerBO= (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    boolean urName,firstName,lastName,street,city,district,postalCode,email,mobile;

    @FXML
    void cityTxtOnAction(ActionEvent event) {
        districtTxt.requestFocus();
    }

    @FXML
    void districtTxtOnAction(ActionEvent event) {
        postalCodeTxt.requestFocus();
    }

    @FXML
    void emailTxtOnAction(ActionEvent event) {
        mobileTxt.requestFocus();
    }

    @FXML
    void firstNameTxtOnAction(ActionEvent event) {
        lastNameTxt.requestFocus();
    }

    @FXML
    void lastNameTxtOnAction(ActionEvent event) {
        streetTxt.requestFocus();
    }

    @FXML
    void mobileTxtOnAction(ActionEvent event) {
        svBtn.fire();
    }

    @FXML
    void postalCodeTxtOnAction(ActionEvent event) {
        emailTxt.requestFocus();
    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {
        searchBtn.fire();
    }

    @FXML
    void streetTxtOnAction(ActionEvent event) {
        cityTxt.requestFocus();
    }

    @FXML
    void usrNameTxtOnAction(ActionEvent event) {
        firstNameTxt.requestFocus();
    }
    @FXML
    void svBtnOnAction(ActionEvent event) {
        if(validation()){
            if(customerBO.saveCustomer(new CustomerDto(
                    idLbl.getText(),
                    usrNameTxt.getText(),
                    emailTxt.getText(),
                    firstNameTxt.getText(),
                    lastNameTxt.getText(),
                    streetTxt.getText(),
                    cityTxt.getText(),
                    districtTxt.getText(),
                    postalCodeTxt.getText(),
                    mobileTxt.getText()
            ))){
                new CustomAlert(Alert.AlertType.CONFIRMATION, "Update ", "Updated !", "Customer Update successful !").show();
                fillTbl();
                initUi();
            }else{
                new CustomAlert(Alert.AlertType.ERROR, "Update ", "Not Update !", "Update not successful !").show();
            }
        }
    }
    @FXML
    void upBtnOnAction(ActionEvent event) {
        if(validation()){
            if (customerBO.updateCustomer(new CustomerDto(
                    idLbl.getText(),
                    usrNameTxt.getText(),
                    emailTxt.getText(),
                    firstNameTxt.getText(),
                    lastNameTxt.getText(),
                    streetTxt.getText(),
                    cityTxt.getText(),
                    districtTxt.getText(),
                    postalCodeTxt.getText(),
                    mobileTxt.getText()
            ))){
                new CustomAlert(Alert.AlertType.CONFIRMATION, "Update ", "Updated !", "Customer Update successful !").show();
                initUi();
                fillTbl();
            }else {
                new CustomAlert(Alert.AlertType.ERROR, "Update ", "Not Update !", "Update not successful !").show();
            }
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        if (customerBO.deleteCustomer(new CustomerDto(
                idLbl.getText(),
                usrNameTxt.getText(),
                emailTxt.getText(),
                firstNameTxt.getText(),
                lastNameTxt.getText(),
                streetTxt.getText(),
                cityTxt.getText(),
                districtTxt.getText(),
                postalCodeTxt.getText(),
                mobileTxt.getText()
        ))){
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","Customer Deleted","Customer Delete Successfully!").show();
            fillTbl();
            initUi();
        }else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","Customer Not Deleted","Customer Not Delete Successfully!").show();
        }
    }
    @FXML
    void searchBtnOnAction(ActionEvent event) {
        CustomerDto customerDto=customerBO.getCustomer(searchTxt.getText());
        if(customerDto!=null){
            idLbl.setText(customerDto.getId());
            usrNameTxt.setText(customerDto.getUserName());
            emailTxt.setText(customerDto.getEmail());
            firstNameTxt.setText(customerDto.getFirstName());
            lastNameTxt.setText(customerDto.getLastName());
            streetTxt.setText(customerDto.getStreet());
            cityTxt.setText(customerDto.getCity());
            districtTxt.setText(customerDto.getDistrict());
            postalCodeTxt.setText(customerDto.getPostalCode());
            mobileTxt.setText(customerDto.getMobile());

            upBtn.setDisable(false);
            deleteBtn.setDisable(false);
            usrNameTxt.setDisable(false);
            firstNameTxt.setDisable(false);
            lastNameTxt.setDisable(false);
            streetTxt.setDisable(false);
            cityTxt.setDisable(false);
            districtTxt.setDisable(false);
            postalCodeTxt.setDisable(false);
            emailTxt.setDisable(false);
            mobileTxt.setDisable(false);

            addBtn.setDisable(true);
        }else {
            new CustomAlert(Alert.AlertType.ERROR,"Search","Customer Not Found","Customer Not Found!").show();
        }
        searchTxt.clear();
    }

    @FXML
    void addNewBtnOnAction(ActionEvent event) {
        usrNameTxt.setDisable(false);
        firstNameTxt.setDisable(false);
        lastNameTxt.setDisable(false);
        streetTxt.setDisable(false);
        cityTxt.setDisable(false);
        districtTxt.setDisable(false);
        postalCodeTxt.setDisable(false);
        emailTxt.setDisable(false);
        mobileTxt.setDisable(false);

        svBtn.setDisable(false);

        usrNameTxt.requestFocus();

        setCusId();
    }

    private void setCusId() {
        idLbl.setText(customerBO.getNextId());
    }

    @FXML
    void initialize() {
        initUi();
        fillTbl();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        idColm.setCellValueFactory(new PropertyValueFactory<>("id"));
        mailClm.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileColm.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressColm.setCellValueFactory(new PropertyValueFactory<>("address"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void fillTbl() {
        ObservableList<CustomerTM>customerTMS= FXCollections.observableArrayList();
        for(CustomerDto customerDto:customerBO.getAllCustomers()){
            String name=customerDto.getFirstName()+" "+customerDto.getLastName();
            String address=customerDto.getStreet()+","+customerDto.getCity()+","+customerDto.getDistrict();
            customerTMS.add(new CustomerTM(
                    customerDto.getId(),
                    name,
                    address,
                    customerDto.getMobile(),
                    customerDto.getEmail()
            ));
        }
        custTbl.setItems(customerTMS);
    }

    private void initUi() {
        idLbl.setText("");
        usrNameTxt.setDisable(true);
        firstNameTxt.setDisable(true);
        lastNameTxt.setDisable(true);
        streetTxt.setDisable(true);
        cityTxt.setDisable(true);
        districtTxt.setDisable(true);
        postalCodeTxt.setDisable(true);
        emailTxt.setDisable(true);
        mobileTxt.setDisable(true);

        svBtn.setDisable(true);
        upBtn.setDisable(true);
        deleteBtn.setDisable(true);

        usrNameTxt.clear();
        firstNameTxt.clear();
        lastNameTxt.clear();
        streetTxt.clear();
        cityTxt.clear();
        districtTxt.clear();
        postalCodeTxt.clear();
        emailTxt.clear();
        mobileTxt.clear();

        addBtn.setDisable(false);
    }
    private boolean validation() {
        urName=false;
        firstName=false;
        lastName=false;
        street=false;
        city=false;
        district=false;
        postalCode=false;
        email=false;
        mobile=false;

        urName= Validation.txtValidation(usrNameTxt,usrNameLine);
        firstName=Validation.txtValidation(firstNameTxt,firstNameLine);
        lastName=Validation.txtValidation(lastNameTxt,lastNameLine);
        street=Validation.txtValidation(streetTxt,streetLine);
        city=Validation.txtValidation(cityTxt,cityLine);
        district=Validation.txtValidation(districtTxt,destrictLine);
        postalCode=Validation.numberValidation(postalCodeTxt,postalCodeLine);
        email=Validation.txtValidation(emailTxt,emailLine);
        mobile=Validation.cNumValidation(mobileTxt,mobileLine);

        return urName && firstName && lastName && street && city && district && postalCode && email && mobile;
    }
}
