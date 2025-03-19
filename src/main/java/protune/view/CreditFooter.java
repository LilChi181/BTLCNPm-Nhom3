package protune.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import protune.model.Constant;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.awt.Desktop;
import java.net.URISyntaxException;

public class CreditFooter extends FlowPane {
    public CreditFooter() throws FileNotFoundException {
        this.setHgap(15);
        this.setPrefSize(500, 100);
        this.setLayoutY(550);
        this.setLayoutX(80);

        Label lb = new Label("Contact:");
        lb.setId("credit-label");

        Label githubIcon = new Label();
        githubIcon.setGraphic(new ImageView(new Image(new FileInputStream(Constant.githubIconPath))));
        githubIcon.setId("credit-icon");
        githubIcon.setOnMouseClicked(e -> openLink(Constant.githubLink));

        Label facebookIcon = new Label();
        facebookIcon.setGraphic(new ImageView(new Image(new FileInputStream(Constant.facebookIconPath))));
        facebookIcon.setId("credit-icon");
        facebookIcon.setOnMouseClicked(e -> openLink(Constant.facebookLink));

        this.getStylesheets().add(getClass().getResource("/stylesheet/credit.css").toExternalForm());
        getChildren().addAll(lb, githubIcon, facebookIcon);
    }

    private void openLink(String link){
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
