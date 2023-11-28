package lk.ijse.Controller;

import javafx.fxml.Initializable;
import lk.ijse.configaration.SessionFactoryConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread printThread = new Thread(() -> {
            SessionFactoryConfig.getInstance().getSession();
        });
        printThread.start();
    }
}
