package protune.view.in.mainzone.homepane.audiocard;

import protune.model.AudioData;
import protune.model.Constant;
import protune.view.in.mainzone.homepane.audiocard.button.PLDelButton;

public class PLCard extends AudioCard{

    PLDelButton plDelButton;

    String playlistName;

    public PLCard(AudioData audioData, String plName) {
        super(audioData);
        this.ID = audioData.getID();
        playlistName = plName;
        plDelButton = new PLDelButton(Constant.plDelIconPath, this);
        btnManager.getBtnList().removeLast(); // remove add icon
        btnManager.add(plDelButton);

        imageZone.getChildren().remove(2); // remove add icon
        imageZone.getChildren().add(plDelButton);
    }

    public String getPlaylistName(){ return playlistName; }
}
