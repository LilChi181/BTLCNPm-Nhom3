package protune.view.in.playbar.volume;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.MediaPlayer;
import protune.Init;
import protune.model.Constant;
import protune.view.in.playbar.MyProgressBar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VolumeBar extends FlowPane {
    MediaPlayer mediaPlayer;
    MyProgressBar volume;
    public VolumeBar(){
        this.setPrefSize(245, 90);
        this.getStyleClass().add("player-item");
        this.getStyleClass().add("volume-bar");
        this.setHgap(15);

        Image image;
        try {
            image = new Image(new FileInputStream(Constant.volumeIconPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView volumeIcon = new ImageView(image);
        volume = new MyProgressBar(100, 120, 10);

        volume.progressProperty().addListener(observable -> {
            if(Init.playBar.isHaveSong()){
                mediaPlayer.setVolume(volume.getProgress());
            }
        });

        this.getChildren().addAll(volumeIcon, volume);
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.mediaPlayer.setVolume(volume.getProgress());
    }
}
