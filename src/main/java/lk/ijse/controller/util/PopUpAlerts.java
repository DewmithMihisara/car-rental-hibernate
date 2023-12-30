package lk.ijse.controller.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class PopUpAlerts {
    public static void popUps(AlertTypes alertTypes, String title, String test){
        Image img ;
        switch (alertTypes){
            case ERROR ->img=new Image("/img/alerts/close.png");
            case WARNING -> img=new Image("/img/alerts/warning.png");
            case INFORMATION -> img=new Image("/img/alerts/information-button.png");
            case CONFORMATION -> img=new Image("/img/alerts/verified.png");
            default -> throw new IllegalStateException("Unexpected value: " + alertTypes);
        }
        Notifications notificationBuilder=Notifications.create()
                .title(title)
                .text(test)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {}
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
