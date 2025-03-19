package protune.view.in.mainzone;

import javafx.scene.layout.StackPane;
import protune.Init;

public class MainZone extends StackPane {
    public MainZone(){
        this.setLayoutX(250);
        this.setLayoutY(10);
        this.setPrefSize(740, 480);
        this.getChildren().addAll(Init.searchPane, Init.localPane, Init.homePane);
    }

    public void clearLocal(){
        Init.homePane.clearLocal();
        Init.localPane.clearLocal();
    }
}
