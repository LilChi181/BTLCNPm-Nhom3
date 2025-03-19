package protune.view.in.playbar.player.control.button;

import protune.Init;
import protune.model.Constant;
import protune.view.in.playbar.player.control.ControlButton;

public class PlayButton extends ControlButton {

    public PlayButton(String iconPath) {
        super(iconPath);
        this.setOnMouseClicked(e -> changeState());
    }

    public void init(){
        setIcon(Constant.pauseIconPath);
    }

    public void changeState(){
        if(Init.playBar.isHaveSong()){
            if(Init.playBar.isPlaying()) {
                Init.playBar.setPlaying(false);
                this.setIcon(Constant.playIconPath);
                Init.playBar.pause();
            }
            else{
                Init.playBar.setPlaying(true);
                this.setIcon(Constant.pauseIconPath);
                Init.playBar.play();
            }
        }
    }
}
