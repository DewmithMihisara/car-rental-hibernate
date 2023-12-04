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
            case CAR_CATEGORY ->initUi("categoryForm.fxml");
            case DASH_BOARD -> initUi("dashboard.fxml");
            case CAR -> initUi("carsForm.fxml");
            case HOME -> initUi("homeForm.fxml");
            case CUSTOMER -> initUi("customerForm.fxml");
        }
    }
    private static void initUi(String location) throws IOException {
        Navigation.root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/views/" + location))));
    }
}
