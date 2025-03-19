package protune.view.in.navbar.item;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NavItem extends Label {

    public NavItem(String iconPath, String content) throws FileNotFoundException {
        setText(content);
        this.setWrapText(true);
        setIcon(this, new Image(new FileInputStream(iconPath)));
        this.setHeight(100);
        this.getStyleClass().add("nav-items");
        this.setOnMouseEntered(e -> this.getStyleClass().add("nav-items-mousein"));
        this.setOnMouseExited(e -> this.getStyleClass().remove("nav-items-mousein"));
    }

    private void setIcon(Label label, Image icon){
        ImageView imageView = new ImageView(icon);
        label.setGraphic(imageView);
    }
}
