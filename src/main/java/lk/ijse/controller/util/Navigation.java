package lk.ijse.controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static AnchorPane root;
    public static void navigation(Rout rout,AnchorPane root) throws IOException {
        Navigation.root = root;
        Navigation.root.getChildren().clear();
        Stage window = (Stage) Navigation.root.getScene().getWindow();
        switch (rout) {
            case LOG_IN -> initUi("logInForm.fxml");
            case CAR_CATEGORY ->initUi("categoryForm.fxml");
            case DASH_BOARD -> initUi("dashboardForm.fxml");
            case CAR -> initUi("carsForm.fxml");
            case HOME -> initUi("homeForm.fxml");
            case CUSTOMER -> initUi("customerForm.fxml");
            case USER -> initUi("userForm.fxml");
            case NEW_RENT -> initUi("rentForm.fxml");
            case ACTIVE_RENT -> initUi("activeRentForm.fxml");
        }
    }
    private static void initUi(String location) throws IOException {
        Navigation.root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/views/" + location))));
    }
}
