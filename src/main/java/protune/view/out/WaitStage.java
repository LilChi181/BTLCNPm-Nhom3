package protune.view.out;

import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WaitStage extends Stage {
    public WaitStage(){
        this.setTitle("Loading...");
        ProgressIndicator progressIndicator = new ProgressIndicator();
        BorderPane pane = new BorderPane();
        pane.setCenter(progressIndicator);

        Scene scene = new Scene(pane, 350, 200);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
    }
}
