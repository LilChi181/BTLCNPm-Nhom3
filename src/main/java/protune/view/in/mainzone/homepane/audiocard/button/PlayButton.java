package protune.view.in.mainzone.homepane.audiocard.button;

import protune.Init;
import protune.view.in.mainzone.homepane.audiocard.AudioCard;

import java.io.FileNotFoundException;

public class PlayButton extends CardButton{
    public PlayButton(String iconPath, AudioCard audioCard) {
        super(iconPath);
        this.setLayoutX(125);
        this.setLayoutY(125);
        this.setOnMouseClicked(e -> {
            try {
                Init.playBar.setSongPlay(audioCard.getdata());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
