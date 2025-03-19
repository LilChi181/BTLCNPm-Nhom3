package protune.view.in.playbar.player;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import protune.Init;
import protune.view.in.playbar.MyProgressBar;

import java.io.FileNotFoundException;

public class PlayBackZone extends FlowPane {
    Label timeStamp, songDuration;
    MyProgressBar progressBar;
    MediaPlayer mediaPlayer;
    double totalDuration, currentDuration;
    boolean autoSet = true;
    public PlayBackZone(){
        this.setPrefSize(480, 40);
        this.setAlignment(Pos.CENTER);
        this.setHgap(5);

        timeStamp = new Label("00:00");
        songDuration = new Label("00:00");
        timeStamp.getStyleClass().add("time-label");
        songDuration.getStyleClass().add("time-label");

        progressBar = new MyProgressBar(0, 400, 10);
        progressBar.setDisable(true);

        progressBar.progressProperty().addListener(observable -> timeStamp.setText(timeFormat(progressBar.getProgress() * totalDuration)));

        progressBar.setOnMouseDragged(e -> {
            autoSet = false;
            progressBar.setProgress(Math.max(0, Math.min(400, e.getX())) / 400);
        });

        progressBar.setOnMousePressed(e -> {
            autoSet = false;
            progressBar.setProgress(Math.max(0, Math.min(400, e.getX())) / 400);
        });

        progressBar.setOnMouseReleased(e -> {
            mediaPlayer.seek(Duration.seconds(progressBar.getProgress() * totalDuration));
            autoSet = true;
        });

        this.getChildren().addAll(timeStamp, progressBar, songDuration);
    }

    public void setMedia(MediaPlayer mediaPlayer){
        progressBar.setDisable(false);
        this.mediaPlayer = mediaPlayer;
        this.mediaPlayer.setAutoPlay(true);
        progressBar.setProgress(0);
        this.mediaPlayer.currentTimeProperty().addListener(observable -> {
            currentDuration = mediaPlayer.currentTimeProperty().getValue().toSeconds();
            timeStamp.setText(timeFormat(currentDuration));
            if(autoSet) progressBar.setProgress(currentDuration / totalDuration);
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            try {
                Init.playBar.processWhenEndAudio();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        mediaPlayer.setOnReady(() -> {
            totalDuration = this.mediaPlayer.getMedia().getDuration().toSeconds();
            songDuration.setText(timeFormat(totalDuration));
        });

    }

    private String timeFormat(double second){
        return String.format("%02d:%02d", (int)second / 60, (int)second % 60);
    }

    public void reset(){
        progressBar.setProgress(0);
        songDuration.setText("00:00");
        progressBar.setDisable(true);
    }
}
