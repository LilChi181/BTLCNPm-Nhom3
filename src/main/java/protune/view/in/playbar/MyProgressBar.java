package protune.view.in.playbar;

import javafx.scene.control.ProgressBar;

public class MyProgressBar extends ProgressBar {
    public MyProgressBar(int progress, int width, int height) {
        this.setProgress(progress);
        this.setPrefSize(width, height);

        this.setOnMouseDragged(e -> this.setProgress(Math.max(0, Math.min(width, e.getX())) / width));
        this.setOnMouseClicked(e -> this.setProgress(Math.max(0, Math.min(width, e.getX())) / width));
    }
}
