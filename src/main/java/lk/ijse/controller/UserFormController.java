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
import lk.ijse.controller.util.CustomAlert;
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
                firstNameTxt.getText(),
                lastNameTxt.getText(),
                roleTxt.getText(),
                userNameTxt.getText(),
                pwTxt.getText()
        ))){
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","User Deleted","User Delete Successfully!").show();
            fillTbl();
            initUi();
        }else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","User Not Deleted","User Not Delete Successfully!").show();
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
            new CustomAlert(Alert.AlertType.ERROR,"Search","User Not Found","User Not Found!").show();
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
                    firstNameTxt.getText(),
                    lastNameTxt.getText(),
                    roleTxt.getText(),
                    userNameTxt.getText(),
                    pwTxt.getText()))) {
                new CustomAlert(Alert.AlertType.CONFIRMATION, "Save", "User Saved", "User Save Successfully!").show();
                fillTbl();
                initUi();
            } else {
                new CustomAlert(Alert.AlertType.ERROR, "Save", "User Not Saved", "User Not Save Successfully!").show();
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
                    firstNameTxt.getText(),
                    lastNameTxt.getText(),
                    roleTxt.getText(),
                    userNameTxt.getText(),
                    pwTxt.getText()
            ))){
                new CustomAlert(Alert.AlertType.CONFIRMATION, "Update ", "Updated !", "User Update successful !").show();
                initUi();
                fillTbl();
            }else {
                new CustomAlert(Alert.AlertType.ERROR, "Update ", "Not Update !", "Update not successful !").show();
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
                    userDto.getFirstName() + " " + userDto.getFirstName(),
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
    }
}
