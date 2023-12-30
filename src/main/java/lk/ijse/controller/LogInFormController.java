package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.controller.util.CustomAlert;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.Rout;
import lk.ijse.controller.util.Validation;
import lk.ijse.dto.UserDto;

import java.io.IOException;

public class LogInFormController {

    @FXML
    private PasswordField pwTxt;

    @FXML
    private Button logInBtn;
    @FXML
    private AnchorPane root;

    @FXML
    private TextField userNameTxt;

    @FXML
    private Line usrNameLine;

    @FXML
    private Line usrNameLine1;
    boolean usrName, pw;
    {
        usrName = pw = false;
    }
    private final UserBO userBO= (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    @FXML
    void pwTxtOnAction(ActionEvent event) {
        logInBtn.fire();
    }

    @FXML
    void logInBtnOnAction(ActionEvent event) {
        if(validation()){
            UserDto userDto=userBO.getUserByUserName(userNameTxt.getText());
            if(userDto != null) {
                if (userDto.getPassWord().equals(pwTxt.getText())) {
                    try {
                        Navigation.navigation(Rout.DASH_BOARD,root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    new CustomAlert(Alert.AlertType.ERROR, "Error ", "Invalid!", "Invalid password !").show();
                }
            }else {
                new CustomAlert(Alert.AlertType.ERROR, "Error ", "Invalid!", "Invalid user name !").show();
            }
        }
    }

    private boolean validation() {
        usrName= Validation.txtValidation(userNameTxt,usrNameLine);
        pw=Validation.txtValidation(pwTxt,usrNameLine1);
        return usrName && pw;
    }

    @FXML
    void userNameTxtOnAction(ActionEvent event) {
        pwTxt.requestFocus();
    }
}
