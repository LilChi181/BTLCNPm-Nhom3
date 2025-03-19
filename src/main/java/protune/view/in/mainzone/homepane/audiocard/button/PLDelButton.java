package protune.view.in.mainzone.homepane.audiocard.button;

import protune.controller.inapp.PlaylistTrackManager;
import protune.view.in.mainzone.homepane.audiocard.PLCard;
import protune.controller.inapp.PlPaneManager;

public class PLDelButton extends CardButton{

    public PLDelButton(String iconPath, PLCard plCard) {
        super(iconPath);
        this.setLayoutX(5);
        this.setLayoutY(125);
        this.setOnMouseClicked(e -> {
            PlPaneManager.delTrack(plCard, plCard.getPlaylistName());
            PlaylistTrackManager.delTrack(plCard.getPlaylistName(), plCard.getdata());
        });
    }
}
