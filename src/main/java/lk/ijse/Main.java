package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/views/dashboardForm.fxml")));
        stage.setScene(scene);
        stage.setTitle("Car Rental System");
        stage.getIcons().add(new Image("/img/logo/mini logo.png"));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}