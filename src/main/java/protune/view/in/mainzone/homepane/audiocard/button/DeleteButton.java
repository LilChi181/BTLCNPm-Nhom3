package protune.view.in.mainzone.homepane.audiocard.button;

import protune.Init;
import protune.controller.inapp.SongListManager;
import protune.view.in.mainzone.homepane.audiocard.LocalAudioCard;

public class DeleteButton extends CardButton{
    public DeleteButton(String iconPath, LocalAudioCard audioCard){
        super(iconPath);
        this.setLayoutX(5);
        this.setLayoutY(5);

        this.setOnMouseClicked(e -> {
            SongListManager.del(audioCard.getdata());
            Init.searchPane.del(audioCard);
            Init.homePane.del(audioCard);
            Init.localPane.del(audioCard);
            if(Init.playBar.isHaveSong() && Init.playBar.getPlayingSong().equals(audioCard.getdata())) Init.playBar.reset();
        });
    }
}
