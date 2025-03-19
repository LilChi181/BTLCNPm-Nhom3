package protune.view.in.playbar.player;

import javafx.scene.layout.FlowPane;
import javafx.scene.media.MediaPlayer;
import protune.view.in.playbar.player.control.ControlZone;

public class PlayerZone extends FlowPane {
    PlayBackZone playBackZone = new PlayBackZone();
    ControlZone controlZone = new ControlZone();
    public PlayerZone(){
        this.setPrefSize(480, 90);
        this.getChildren().addAll(playBackZone, controlZone);
    }

    public void setSongPlay(MediaPlayer mediaPlayer){
        controlZone.init();
        playBackZone.setMedia(mediaPlayer);
    }

    public void reset(){
        playBackZone.reset();
        controlZone.init();
    }
}
