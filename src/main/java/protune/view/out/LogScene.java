package protune.view.out;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import protune.model.Constant;
import protune.view.CreditFooter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogScene extends Scene {

    private final AnchorPane pane;

    public LogScene() throws FileNotFoundException {
        super(new AnchorPane(), Constant.appWidth, Constant.appHeight);
        pane = new AnchorPane();
        setRoot(pane);

        Image img = new Image(new FileInputStream("src/main/resources/img/bg.png"));
        ImageView im = new ImageView(img);

        SignInPane signInPane = new SignInPane();
        SignUpPane signUpPane = new SignUpPane();

        signInPane.setPaneSwitch(signUpPane);
        signUpPane.setPaneSwitch(signInPane);

        this.getStylesheets().add(getClass().getResource("/stylesheet/logScene.css").toExternalForm());
        pane.getChildren().addAll(im, signInPane, signUpPane, new CreditFooter());
        pane.requestFocus();
    }

}
