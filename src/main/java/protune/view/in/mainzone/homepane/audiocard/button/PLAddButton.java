package protune.view.in.mainzone.homepane.audiocard.button;

import protune.Init;
import protune.view.in.mainzone.homepane.audiocard.AudioCard;

public class PLAddButton extends CardButton{

    public PLAddButton(String iconPath, AudioCard audioCard) {
        super(iconPath);

        this.setLayoutX(5);
        this.setLayoutY(125);
        this.setOnMouseClicked(e -> {
            Init.playlistList.setLayoutX(e.getSceneX());
            Init.playlistList.setLayoutY(e.getSceneY());
            Init.playlistList.setVisible(true);
            Init.playlistList.prepare(audioCard.getdata());
        });
    }
}
