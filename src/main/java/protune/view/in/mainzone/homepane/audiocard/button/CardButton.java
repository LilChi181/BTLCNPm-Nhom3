package protune.view.in.mainzone.homepane.audiocard.button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class CardButton extends Label {
    public CardButton(String iconPath){
        this.setVisible(false);
        ImageView icon = new ImageView(new Image(new File(iconPath).toURI().toString()));

        this.setGraphic(icon);
        this.setPrefSize(icon.getFitWidth() + 30, icon.getFitWidth() + 30);
        this.setOnMouseEntered(e -> this.getStyleClass().add("btn-mousein"));
        this.setOnMouseExited(e -> this.getStyleClass().remove("btn-mousein"));

        this.getStyleClass().add("card-btn");
        this.getStylesheets().add(getClass().getResource("/stylesheet/songcard.css").toExternalForm());
    }
}
