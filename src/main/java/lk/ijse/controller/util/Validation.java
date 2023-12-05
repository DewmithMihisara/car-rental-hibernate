package lk.ijse.controller.util;

import animatefx.animation.Shake;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Validation {
    static Shake shake;
    public static boolean txtValidation(TextField txt, Line line) {
        if (txt.getText().matches("^$")) {
            shakeLine(line);
        } else {
            defaultLine(line);
            return true;
        }
        return false;
    }
    public static boolean pwValidation(PasswordField pwTxt, Line line) {
        if (pwTxt.getText().matches("^$")) {
            shakeLine(line);
        } else {
            defaultLine(line);
            return true;
        }
        return false;
    }
    public static boolean cNumValidation(TextField txt, Line line) {
        if (txt.getText().matches("[0-9+]+")) {
            defaultLine(line);
            return true;
        } else {
            shakeLine(line);
        }
        return false;
    }
    public static boolean numberValidation(TextField txt, Line line) {
        if (txt.getText().matches("[0-9]+")) {
            defaultLine(line);
            return true;
        } else {
            shakeLine(line);
        }
        return false;
    }
    public static boolean moneyValidation(TextField txt, Line line) {
        if (txt.getText().matches("\\d+(\\.\\d{1,2})?")) {
            defaultLine(line);
            return true;
        } else {
            shakeLine(line);
        }
        return false;
    }
    public static void shakeLine(Line line){
        line.setStroke(Color.RED);
        shake=new Shake(line);
        shake.setOnFinished(actionEvent -> {
            defaultLine(line);
        });
        shake.play();
    }
    public static void defaultLine(Line line){
        line.setStroke(Color.BLACK);
    }

    public static boolean dateValidation(DatePicker date) {
        if (date.getValue()==null){
            shakeDate(date);
        }else {
            return true;
        }
        return false;
    }
    public static boolean comboValidation(ComboBox<String> idCmb) {
        if (idCmb.getValue() == null){
            shakeCmb(idCmb);
        }else {
            return true;
        }
        return false;
    }
    private static void shakeDate(DatePicker date) {
        date.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-width: 2px ;" +
                        "-fx-background-color: tranceparent ;" +
                        "-fx-text-fill : white;"
        );
        shake=new Shake(date);
        shake.setOnFinished(actionEvent -> {
            defaultDate(date);
        });
        shake.play();
    }

    private static void defaultDate(DatePicker date) {
        date.setStyle(
                "-fx-background-color:tranceparent; " +
                        "-fx-text-fill: white"
        );
    }
    private static void shakeCmb(ComboBox<String> idCmb) {
        idCmb.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-width: 2px ;" +
                        "-fx-background-color: tranceparent ;"
        );
        shake=new Shake(idCmb);
        shake.setOnFinished(actionEvent -> {
            defaultDate(idCmb);
        });
        shake.play();
    }
    private static void defaultDate(ComboBox<String> idCmb) {
        idCmb.setStyle(
                "-fx-background-color:tranceparent; "+
                        "-fx-border-color: black; "+
                        "-fx-border-width: 2px ;"
        );
    }
}
