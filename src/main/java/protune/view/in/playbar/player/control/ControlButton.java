package protune.view.in.playbar.player.control;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ControlButton extends Label {
    protected Image icon;
    public ControlButton(String iconPath){
        this.setPrefSize(40, 35);
        this.setIcon(iconPath);
        this.getStyleClass().add("control-label");
    }

    protected void setIcon(String iconPath){
        try {
            icon = new Image(new FileInputStream(iconPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView imageView = new ImageView(icon);
        this.setGraphic(imageView);
    }
}
