package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.controller.util.AlertTypes;
import lk.ijse.controller.util.PopUpAlerts;
import lk.ijse.controller.util.Validation;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.UserTM;

public class UserFormController {
    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Line firstNameLine;
    @FXML
    private TableColumn<?, ?> roleClm;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TableColumn<?, ?> fullNameColm;

    @FXML
    private TableColumn<?, ?> idColm;

    @FXML
    private Label idLbl;

    @FXML
    private Line lastNameLine;

    @FXML
    private TextField pwTxt;

    @FXML
    private Line roleLine;

    @FXML
    private TextField roleTxt;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button svBtn;

    @FXML
    private Button upBtn;

    @FXML
    private TableColumn<?, ?> userNameColm;

    @FXML
    private Line userNameLine;

    @FXML
    private TextField userNameTxt;

    @FXML
    private Line pwLine;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TableView<UserTM> usrTbl;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    boolean firstName, lastName, role, userName, pw;

    @FXML
    void addNewBtnOnAction(ActionEvent event) {
        svBtn.setDisable(false);
        firstNameTxt.setDisable(false);
        lastNameTxt.setDisable(false);
        roleTxt.setDisable(false);
        userNameTxt.setDisable(false);
        pwTxt.setDisable(false);
        firstNameTxt.requestFocus();
        setUserId();
    }

    private void setUserId() {
        idLbl.setText(userBO.getNewUserId());
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        if (userBO.deleteUser(new UserDto(
                idLbl.getText(),
                userNameTxt.getText(),
                pwTxt.getText(),
                roleTxt.getText(),
                firstNameTxt.getText(),
                lastNameTxt.getText()
        ))){
            PopUpAlerts.popUps(AlertTypes.CONFORMATION, "Delete", "User Delete Successfully!");
            fillTbl();
            initUi();
        }else {
            PopUpAlerts.popUps(AlertTypes.ERROR, "Delete", "User Not Delete Successfully!");
        }
    }

    @FXML
    void firstNameTxtOnAction(ActionEvent event) {
        lastNameTxt.requestFocus();
    }

    @FXML
    void lastNameTxtOnAction(ActionEvent event) {
        roleTxt.requestFocus();
    }

    @FXML
    void pwTxtOnAction(ActionEvent event) {
        svBtn.fire();
    }

    @FXML
    void roleTxtOnAction(ActionEvent event) {
        userNameTxt.requestFocus();
    }

    @FXML
    void searchBtnOnAction(ActionEvent event) {
        UserDto userDto=userBO.getUser(searchTxt.getText());
        if(userDto!=null){
            idLbl.setText(userDto.getId());
            firstNameTxt.setText(userDto.getFirstName());
            lastNameTxt.setText(userDto.getLastName());
            roleTxt.setText(userDto.getRole());
            userNameTxt.setText(userDto.getUserName());
            pwTxt.setText(userDto.getPassWord());

            upBtn.setDisable(false);
            deleteBtn.setDisable(false);

            firstNameTxt.setDisable(false);
            lastNameTxt.setDisable(false);
            roleTxt.setDisable(false);
            userNameTxt.setDisable(false);
            pwTxt.setDisable(false);

            addBtn.setDisable(true);
        }else {
            PopUpAlerts.popUps(AlertTypes.ERROR, "Search", "User Not Found!");
        }
        searchTxt.clear();
    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {
        searchBtn.fire();
    }

    @FXML
    void svBtnOnAction(ActionEvent event) {
        if (validation()) {
            if (userBO.saveUser(new UserDto(
                    idLbl.getText(),
                    userNameTxt.getText(),
                    pwTxt.getText(),
                    roleTxt.getText(),
                    firstNameTxt.getText(),
                    lastNameTxt.getText()
                    ))) {
                PopUpAlerts.popUps(AlertTypes.CONFORMATION, "Save", "User Saved Successfully!");
                fillTbl();
                initUi();
            } else {
                PopUpAlerts.popUps(AlertTypes.ERROR, "Save", "User Not Saved Successfully!");
            }
        }
    }

    private boolean validation() {
        firstName=false;
        lastName=false;
        role=false;
        userName=false;
        pw=false;

        firstName= Validation.txtValidation(firstNameTxt,firstNameLine);
        lastName= Validation.txtValidation(lastNameTxt,lastNameLine);
        role= Validation.txtValidation(roleTxt,roleLine);
        userName= Validation.txtValidation(userNameTxt,userNameLine);
        pw= Validation.txtValidation(pwTxt,pwLine);

        return firstName && lastName && role && userName && pw;
    }

    @FXML
    void upBtnOnAction(ActionEvent event) {
        if(validation()){
            if (userBO.updateUser(new UserDto(
                    idLbl.getText(),
                    userNameTxt.getText(),
                    pwTxt.getText(),
                    roleTxt.getText(),
                    firstNameTxt.getText(),
                    lastNameTxt.getText()
            ))){
                PopUpAlerts.popUps(AlertTypes.CONFORMATION, "Update", "User Updated Successfully!");
                initUi();
                fillTbl();
            }else {
                PopUpAlerts.popUps(AlertTypes.ERROR, "Update", "User Not Updated Successfully!");
            }
        }
    }

    @FXML
    void userNameTxtOnAction(ActionEvent event) {
        pwTxt.requestFocus();
    }

    @FXML
    void initialize() {
        initUi();
        setCellValueFactory();
        fillTbl();
    }

    private void fillTbl() {
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();
        for (UserDto userDto : userBO.getAllUsers()) {
            userTMS.add(new UserTM(
                    userDto.getId(),
                    userDto.getFirstName() + " " + userDto.getLastName(),
                    userDto.getRole(),
                    userDto.getUserName()
            ));
        }
        usrTbl.setItems(userTMS);
    }

    private void setCellValueFactory() {
        idColm.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColm.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        roleClm.setCellValueFactory(new PropertyValueFactory<>("role"));
        userNameColm.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    private void initUi() {
        idLbl.setText("");
        firstNameTxt.clear();
        lastNameTxt.clear();
        roleTxt.clear();
        userNameTxt.clear();
        pwTxt.clear();

        firstNameTxt.setDisable(true);
        lastNameTxt.setDisable(true);
        roleTxt.setDisable(true);
        userNameTxt.setDisable(true);
        pwTxt.setDisable(true);

        svBtn.setDisable(true);
        upBtn.setDisable(true);
        deleteBtn.setDisable(true);

        addBtn.setDisable(false);
    }
}
